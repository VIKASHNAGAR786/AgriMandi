import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './AddProduct.css';

const AddProduct = () => {
  const [product, setProduct] = useState({
    name: '',
    price: '',
    quantity: '',
    description: '',
    listingDate: '',
    user_id: '',
    selectedProduct: '',
    cropType: '',
    soilType: '',
    wateringRequirements: '',
    fertilizerUsed: '',
    pestControlMethods: '',
    organicCertification: '',
    climateRequirements: '',
    growthCycle: '',
    packagingType: '',
    storageConditions: '',
    shelfLife: '',
    sustainabilityPractices: '',
    productImage: '',
    farmerLocation: '',
    harvestDate: '',
    plantingDate: ''
  });

  const [errorMessage, setErrorMessage] = useState('');
  const [productList, setProductList] = useState([]); // Store the list of product names
  const [searchQuery, setSearchQuery] = useState(''); // To filter products by name

  // Fetch product names from the API on component mount
  useEffect(() => {
    const fetchProductNames = async () => {
      try {
        const response = await axios.get('http://localhost:8080/api/product-names'); // Endpoint to fetch product names
        const products = response.data;

        // Assuming the response is an array of product objects or strings
        setProductList(products); // Set the product list with the fetched product names
      } catch (error) {
        console.error('Error fetching product names:', error);
        setErrorMessage('Failed to load product names.');
      }
    };

    fetchProductNames();
  }, []);

  // Handle changes in the input fields
  const handleChange = (e) => {
    const { name, value } = e.target;
    setProduct({
      ...product,
      [name]: value,
    });
  };

  // Handle submit
  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!product.user_id || !product.selectedProduct) {
      alert('Please enter a valid user ID and select a product.');
      return;
    }

    try {
      await axios.post(
        'http://localhost:8080/api/products',
        {
          name: product.selectedProduct, // Send the selected product name
          price: product.price,
          quantity: product.quantity,
          description: product.description,
          listingDate: product.listingDate,
          user: { id: product.user_id },
          cropType: product.cropType,
          soilType: product.soilType,
          wateringRequirements: product.wateringRequirements,
          fertilizerUsed: product.fertilizerUsed,
          pestControlMethods: product.pestControlMethods,
          organicCertification: product.organicCertification,
          climateRequirements: product.climateRequirements,
          growthCycle: product.growthCycle,
          packagingType: product.packagingType,
          storageConditions: product.storageConditions,
          shelfLife: product.shelfLife,
          sustainabilityPractices: product.sustainabilityPractices,
          productImage: product.productImage,
          farmerLocation: product.farmerLocation,
          harvestDate: product.harvestDate,
          plantingDate: product.plantingDate
        },
        {
          headers: { 'Content-Type': 'application/json' },
        }
      );

      alert('Product added successfully!');
      setProduct({
        name: '',
        price: '',
        quantity: '',
        description: '',
        listingDate: '',
        user_id: '',
        selectedProduct: '',
        cropType: '',
        soilType: '',
        wateringRequirements: '',
        fertilizerUsed: '',
        pestControlMethods: '',
        organicCertification: '',
        climateRequirements: '',
        growthCycle: '',
        packagingType: '',
        storageConditions: '',
        shelfLife: '',
        sustainabilityPractices: '',
        productImage: '',
        farmerLocation: '',
        harvestDate: '',
        plantingDate: ''
      });
    } catch (error) {
      console.error('Error adding product:', error);
      alert('Failed to add product.');
      setErrorMessage(error.response?.data?.message || 'An error occurred.');
    }
  };

  // Filter products based on search query
  const filteredProducts = productList.filter((prod) => {
    // Check if prod is an object and contains 'name', otherwise assume it's a string
    const productName = typeof prod === 'object' && prod.name ? prod.name : prod;
    return productName.toLowerCase().includes(searchQuery.toLowerCase());
  });

  return (
    <div className="add-product-container">
      <h1>Add Product </h1>
      <form onSubmit={handleSubmit} className="add-product-form">
        <div className="form-group">
          <label htmlFor="user_id">User  ID</label>
          <input
            type="number"
            name="user_id"
            id="user_id"
            value={product.user_id}
            onChange={handleChange}
            required
            placeholder="Enter your user ID (e.g., 12345)"
          />
        </div>
        <div className="form-group">
          <label htmlFor="selectedProduct">Product Name</label>
          <input
            type="text"
            name="searchQuery"
            id="searchQuery"
            value={searchQuery}
            onChange={(e) => setSearchQuery(e.target.value)} // Update search query
            placeholder="Search for a product (e.g., Tomato)"
          />
          <select
            name="selectedProduct"
            id="selectedProduct"
            value={product.selectedProduct}
            onChange={handleChange}
            required
          >
            <option value="">Select a product</option>
            {filteredProducts.map((prod, index) => {
              const productName = typeof prod === 'object' && prod.name ? prod.name : prod;
              return (
                <option key={index} value={productName}>
                  {productName}
                </option>
              );
            })}
          </select>
        </div>
        <div className="form-group">
          <label htmlFor="price">Price</label>
          <input
            type="number"
            name="price"
            id="price"
            value={product.price}
            onChange={handleChange}
            required
            placeholder="Enter price in USD (e.g., 10.99)"
          />
        </div>
        <div className="form-group">
          <label htmlFor="quantity">Quantity</label>
          <input
            type="number"
            name="quantity"
            id="quantity"
            value={product.quantity}
            onChange={handleChange}
            required
            placeholder="Enter quantity available (e.g., 100)"
          />
        </div>
        <div className="form-group">
          <label htmlFor="description">Description</label>
          <textarea
            name="description"
            id="description"
            value={product.description}
            onChange={handleChange}
            required
            placeholder="Provide a brief description of the product"
          />
        </div>
        <div className="form-group">
          <label htmlFor="listingDate">Listing Date</label>
          <input
            type="date"
            name="listingDate"
            id="listingDate"
            value={product.listingDate}
            onChange={handleChange}
            required
          />
        </div>

        {/* Additional Fields */}
        <div className="form-group">
          <label htmlFor="cropType">Crop Type</label>
          <input
            type="text"
            name="cropType"
            id="cropType"
            value={product.cropType}
            onChange={handleChange}
            placeholder="e.g., Vegetable, Fruit"
          />
        </div>
        <div className="form-group">
          <label htmlFor="soilType">Soil Type</label>
          <input
            type="text"
            name="soilType"
            id="soilType"
            value={product.soilType}
            onChange={handleChange}
            placeholder="e.g., Loamy, Sandy"
          />
        </div>
        <div className="form-group">
          <label htmlFor="wateringRequirements">Watering Requirements</label>
          <input
            type="text"
            name="wateringRequirements"
            id="wateringRequirements"
            value={product.wateringRequirements}
            onChange={handleChange}
            placeholder="e.g., Weekly, Daily"
          />
        </div>
        <div className="form-group">
          <label htmlFor="fertilizerUsed">Fertilizer Used</label>
          <input
            type="text"
            name="fertilizerUsed"
            id="fertilizerUsed"
            value={product.fertilizerUsed}
            onChange={handleChange}
            placeholder="e.g., Organic, Chemical"
          />
        </div>
        <div className="form-group">
          <label htmlFor="pestControlMethods">Pest Control Methods</label>
          <input
            type="text"
            name="pestControlMethods"
            id="pestControlMethods"
            value={product.pestControlMethods}
            onChange={handleChange}
            placeholder="e.g., Insecticides, Natural Predators"
          />
        </div>
        <div className="form -group">
          <label htmlFor="organicCertification">Organic Certification</label>
          <input
            type="text"
            name="organicCertification"
            id="organicCertification"
            value={product.organicCertification}
            onChange={handleChange}
            placeholder="e.g., USDA Organic, None"
          />
        </div>

        {/* Additional Fields for Climate and Agricultural Details */}
<div className="form-group">
  <label htmlFor="climateRequirements">Climate Requirements</label>
  <input
    type="text"
    name="climateRequirements"
    id="climateRequirements"
    value={product.climateRequirements}
    onChange={handleChange}
    placeholder="e.g., Tropical, Dry"
  />
</div>

<div className="form-group">
  <label htmlFor="growthCycle">Growth Cycle</label>
  <input
    type="text"
    name="growthCycle"
    id="growthCycle"
    value={product.growthCycle}
    onChange={handleChange}
    placeholder="e.g., 6 months, Annual"
  />
</div>

<div className="form-group">
  <label htmlFor="packagingType">Packaging Type</label>
  <input
    type="text"
    name="packagingType"
    id="packagingType"
    value={product.packagingType}
    onChange={handleChange}
    placeholder="e.g., Box, Bag"
  />
</div>

<div className="form-group">
  <label htmlFor="storageConditions">Storage Conditions</label>
  <input
    type="text"
    name="storageConditions"
    id="storageConditions"
    value={product.storageConditions}
    onChange={handleChange}
    placeholder="e.g., Cool and Dry, Refrigerated"
  />
</div>

<div className="form-group">
  <label htmlFor="shelfLife">Shelf Life</label>
  <input
    type="text"
    name="shelfLife"
    id="shelfLife"
    value={product.shelfLife}
    onChange={handleChange}
    placeholder="e.g., 3 months, 1 year"
  />
</div>

<div className="form-group">
  <label htmlFor="sustainabilityPractices">Sustainability Practices</label>
  <input
    type="text"
    name="sustainabilityPractices"
    id="sustainabilityPractices"
    value={product.sustainabilityPractices}
    onChange={handleChange}
    placeholder="e.g., Water Conservation, Organic Farming"
  />
</div>

<div className="form-group">
  <label htmlFor="farmerLocation">Farmer Location</label>
  <input
    type="text"
    name="farmerLocation"
    id="farmerLocation"
    value={product.farmerLocation}
    onChange={handleChange}
    placeholder="e.g., Jaipur, Rajasthan"
  />
</div>

<div className="form-group">
  <label htmlFor="harvestDate">Harvest Date</label>
  <input
    type="date"
    name="harvestDate"
    id="harvestDate"
    value={product.harvestDate}
    onChange={handleChange}
  />
</div>

<div className="form-group">
  <label htmlFor="plantingDate">Planting Date</label>
  <input
    type="date"
    name="plantingDate"
    id="plantingDate"
    value={product.plantingDate}
    onChange={handleChange}
  />
</div>


        {/* Optional Fields */}
        <div className="form-group">
          <label htmlFor="productImage">Product Image URL</label>
          <input
            type="text"
            name="productImage"
            id="productImage"
            value={product.productImage}
            onChange={handleChange}
            placeholder="Enter image URL (e.g., http://example.com/image.jpg)"
          />
        </div>

        <button type="submit" className="submit-button">Add Product</button>
      </form>

      {errorMessage && <p className="error-message">{errorMessage}</p>}
    </div>
  );
};

export default AddProduct;