import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import './Profile.css';

const Profile = () => {
  const [userDetails, setUserDetails] = useState({});
  const [userProducts, setUserProducts] = useState([]);
  const [errorMessage, setErrorMessage] = useState('');
  const [profileImage, setProfileImage] = useState('/default-profile.jpg'); // Default image
  const navigate = useNavigate();

  useEffect(() => {
    const fetchUserDetails = async () => {
      try {
        const token = localStorage.getItem('token');
        if (!token) {
          navigate('/login');
          return;
        }

        const response = await axios.get('http://localhost:8080/api/auth/login', {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });

        if (response.data.user) {
          const user = response.data.user;
          setUserDetails(user);

          // Check if a custom image exists for this user
          const savedImage = localStorage.getItem(`profileImage_${user.id}`);
          setProfileImage(savedImage || 'images/profile.jpeg'); // Use saved or default image

          fetchUserProducts(user.id);
        } else {
          setErrorMessage(response.data.message || 'Failed to fetch user details.');
        }
      } catch (error) {
        setErrorMessage(
          error.response?.data?.message || 'An error occurred. Please try again.'
        );
      }
    };

    const fetchUserProducts = async (userId) => {
      try {
        const response = await axios.get(`http://localhost:8080/api/products/user/${userId}`);
        setUserProducts(response.data);
      } catch (error) {
        setErrorMessage(
          error.response?.data?.message || 'Failed to fetch user products.'
        );
      }
    };

    fetchUserDetails();
  }, [navigate]);

  const handleAddProduct = () => {
    navigate('/add-product');
  };

  const handleImageChange = async (event) => {
    const file = event.target.files[0];
    if (file) {
      const formData = new FormData();
      formData.append('image', file);

      try {
        const response = await axios.post('http://localhost:5000/upload', formData, {
          headers: { 'Content-Type': 'multipart/form-data' },
        });
        const imageUrl = response.data.url;

        // Remove old image from local storage
        const oldImage = localStorage.getItem(`profileImage_${userDetails.id}`);
        if (oldImage && oldImage !== 'default-profile-image-url') {
          // Optional: You can make a server request here to delete the file if the backend supports it
        }

        // Update local storage with the new image URL
        localStorage.setItem(`profileImage_${userDetails.id}`, imageUrl);
        setProfileImage(imageUrl);
      } catch (error) {
        console.error('Error uploading image:', error);
      }
    }
  };

  const handleLogout = () => {
    // Clear the token and user details from localStorage
    localStorage.removeItem('token');
    localStorage.removeItem(`profileImage_${userDetails.id}`);
    localStorage.removeItem('user');

    // Redirect to login page
    navigate('/login');
  };

  return (
    <div className="profile-container left-aligned">
      <h2>Profile</h2>
      {errorMessage && <p className="error-message">{errorMessage}</p>}
      {!errorMessage && (
        <div>
          {/* Profile Image with Update Button */}
          <div className="profile-image-container">
            <div className="image-wrapper">
              <img src={profileImage} alt="Profile" className="profile-image" />
              <div className="overlay">
                <button
                  className="update-photo-button"
                  onClick={() => document.getElementById('imageUpload').click()}
                >
                  Update Photo
                </button>
                <input
                  id="imageUpload"
                  type="file"
                  accept="image/*"
                  onChange={handleImageChange}
                  style={{ display: 'none' }}
                />
              </div>
            </div>
          </div>

          <p><strong>ID:</strong> {userDetails.id || 'N/A'}</p>
          <p><strong>Name:</strong> {userDetails.name || 'N/A'}</p>
          <p><strong>Email:</strong> {userDetails.email || 'N/A'}</p>
          <p><strong>Role:</strong> {typeof userDetails.role === 'object' ? JSON.stringify(userDetails.role) : userDetails.role || 'N/A'}</p>
          <button className="add-product-button" onClick={handleAddProduct}>
            Add Product
          </button>

          {/* Logout Button */}
          <button className="logout-button" onClick={handleLogout}>
            Logout
          </button>

          <h3>Products</h3>
          {userProducts.length > 0 ? (
            <div>
              {userProducts.map((product) => (
                <div className="product-card" key={product.id}>
                  <h4>{product.name}</h4>
                  <p><strong>Price:</strong> ₹{product.price}</p>
                  <p><strong>Quantity:</strong> {product.quantity}</p>
                  <p><strong>Description:</strong> {product.description}</p>
                  <p><strong>Listed on:</strong> {product.listingDate}</p>
                </div>
              ))}
            </div>
          ) : (
            <p>No products found for this user.</p>
          )}
        </div>
      )}
    </div>
  );
};

export default Profile;
