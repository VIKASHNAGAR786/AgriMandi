import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom'; // Import Link from react-router-dom
import './ProductList.css';
import '@fortawesome/fontawesome-free/css/all.min.css';


const ProductList = () => {
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');
  const [searchQuery, setSearchQuery] = useState('');

  useEffect(() => {
    fetchProducts();
  }, []);

  const fetchProducts = async () => {
    try {
      const response = await axios.get('http://localhost:8080/api/products');
      setProducts(response.data);
      setLoading(false);
    } catch (err) {
      setError('Failed to fetch products. Please try again.');
      setLoading(false);
    }
  };

  const filteredProducts = products.filter((product) =>
    product.name.toLowerCase().includes(searchQuery.toLowerCase()) ||
    product.description.toLowerCase().includes(searchQuery.toLowerCase())
  );

  if (loading) {
    return (
      <div className="loading">
        <div className="spinner"></div>
        <p>Loading products...</p>
      </div>
    );
  }

  if (error) {
    return (
      <div className="error">
        <p>{error}</p>
        <button onClick={fetchProducts}>Retry</button>
      </div>
    );
  }

  return (
    <div className="product-list">
      <h2>Our Fresh Products</h2>
      <div className="search-bar">
  <input
    type="text"
    placeholder="Search for a product"
    value={searchQuery}
    onChange={(e) => setSearchQuery(e.target.value)}
  />
  <span className="search-icon">
    <i className="fas fa-search"></i>
  </span>
</div>


      {filteredProducts.length === 0 ? (
        <p className="no-products">No products found matching your search query. Please try again!</p>
      ) : (
        <div className="products">
          {filteredProducts.map((product) => (
            <div key={product.id} className="product-card">
              <h3>🌾 {product.name || 'N/A'}</h3>
              <p><strong>Price:</strong> ₹{product.price || 'N/A'}</p>
              <p><strong>Quantity:</strong> {product.quantity || 'N/A'}</p>
              <p>{product.description || 'No description available'}</p>
              <p><strong>Listed on:</strong> {product.listingDate || 'N/A'}</p>
              <p><strong>Farmer:</strong> {product.farmer?.name || 'Unknown'}</p>
              
              {/* Link to ProductDetail page */}
              <Link to={`/product/${product.id}`} className="view-details-button">View Details</Link>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default ProductList;
