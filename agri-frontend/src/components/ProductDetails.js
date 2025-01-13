import React, { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import axios from 'axios';
import './ProductDetails.css';

const ProductDetail = () => {
  const navigate = useNavigate();
  const { id } = useParams();
  const [product, setProduct] = useState(null);

  useEffect(() => {
    const fetchProduct = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/api/products/${id}`);
        setProduct(response.data);
      } catch (err) {
        console.error('Failed to fetch product details:', err);
      }
    };
    fetchProduct();
  }, [id]);

  if (!product) {
    return <div>Loading...</div>;
  }

  return (
    <div>
      <h2>Product Details</h2>
      <h3>{product.name}</h3>
      <p><strong>Description:</strong> {product.description}</p>
      <p><strong>Price:</strong> ₹{product.price}</p>
      <p><strong>Quantity:</strong> {product.quantity}</p>
      <p><strong>Farmer:</strong> {product.user?.name || 'Unknown'}</p>
      <p><strong>Farmer Email:</strong> {product.user?.email || 'Not provided'}</p>
      <p><strong>Listing Date:</strong> {new Date(product.listingDate).toLocaleDateString()}</p>

      {/* Add additional fields here */}
      <p><strong>Crop Type:</strong> {product.cropType || 'Not provided'}</p>
      <p><strong>Soil Type:</strong> {product.soilType || 'Not provided'}</p>
      <p><strong>Watering Requirements:</strong> {product.wateringRequirements || 'Not provided'}</p>
      <p><strong>Fertilizer Used:</strong> {product.fertilizerUsed || 'Not provided'}</p>
      <p><strong>Pest Control Methods:</strong> {product.pestControlMethods || 'Not provided'}</p>
      <p><strong>Organic Certification:</strong> {product.organicCertification || 'Not provided'}</p>
      <p><strong>Climate Requirements:</strong> {product.climateRequirements || 'Not provided'}</p>
      <p><strong>Growth Cycle:</strong> {product.growthCycle || 'Not provided'}</p>
      <p><strong>Packaging Type:</strong> {product.packagingType || 'Not provided'}</p>
      <p><strong>Storage Conditions:</strong> {product.storageConditions || 'Not provided'}</p>
      <p><strong>Shelf Life:</strong> {product.shelfLife || 'Not provided'}</p>
      <p><strong>Sustainability Practices:</strong> {product.sustainabilityPractices || 'Not provided'}</p>
      <p><strong>Farmer Location:</strong> {product.farmerLocation || 'Not provided'}</p>
      <p><strong>Harvest Date:</strong> {product.harvestDate ? new Date(product.harvestDate).toLocaleDateString() : 'Not provided'}</p>
      <p><strong>Planting Date:</strong> {product.plantingDate ? new Date(product.plantingDate).toLocaleDateString() : 'Not provided'}</p>

      {/* Optional: Display product image */}
      {product.productImage && <img src={product.productImage} alt={product.name} />}

      <button onClick={() => navigate('/products')}>Back to Products</button>
    </div>
  );
};

export default ProductDetail;
