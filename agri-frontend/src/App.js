import React from 'react';

import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

import Navbar from './components/Navbar';
import Home from './pages/Home';
import ProductList from './components/ProductList';
import AddProduct from './components/AddProduct';
import Register from './components/Register'; // Corrected import
import Login from './components/Login';
import Profile from './components/Profile';
import ProductDetail from './components/ProductDetails';


const App = () => {
  return (
    <Router>
      <Navbar />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/products" element={<ProductList />} />
        <Route path="/add-product" element={<AddProduct />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        {/* <Route path="/profile" element={<Profile />} /> */}
        <Route path="/profile" element={<Profile />} />
        <Route path="/product/:id" element={<ProductDetail />} /> {/* Route for Product Detail */}
      </Routes>
    </Router>
  );
};

export default App;
