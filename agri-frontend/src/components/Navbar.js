import React, { useState, useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import './Navbar.css';

const Navbar = () => {
  const [user, setUser] = useState(null);
  const navigate = useNavigate();

  // Check if the user is logged in (e.g., from localStorage)
  useEffect(() => {
    const userData = JSON.parse(localStorage.getItem('user')); // Assuming user data is stored here
    if (userData) {
      setUser(userData);
    }
  }, []);

  const handleLogout = () => {
    localStorage.removeItem('user'); // Clear user data on logout
    localStorage.removeItem('token'); // Clear JWT token
    setUser(null);
    navigate('/login'); // Redirect to login page
  };

  return (
    <nav className="navbar">
    <div className="navbar-brand">
  <Link to="/" className="brand-link">
    <img src="/images/logo.webp" alt="AgriMandi Logo" className="brand-logo" />
    <span className="brand-name">AgriMandi</span>
  </Link>
</div>


      <ul className="navbar-links">
        <li><Link to="/">Home</Link></li>
        <li><Link to="/products">Products</Link></li>
        <li><Link to="/add-product">Add Product</Link></li>

        {/* If user is logged in, show Profile, Welcome message, and Logout */}
        {/* {user ? ( */}
          {/* <> */}
            {/* <li><Link to="/profile">Profile</Link></li> */}
            {/* <li><span>Welcome, {user.name}</span></li> */}
            {/* <li><button onClick={handleLogout}>Logout</button></li> */}
          {/* </> */}
        {/* ) : ( */}
          {/* // If no user is logged in, show Login and Register links */}
          {/* <> */}
            {/* <li><Link to="/login">Login</Link></li> */}
            {/* <li><Link to="/register">Register</Link></li> */}
          {/* </> */}
        {/* )} */}
        <li><Link to="/profile">Profile</Link></li>
        {/* <li><span>Welcome, {user.name}</span></li> */}
        {/* <li><button onClick={handleLogout}>Logout</button></li> */}
        <li><Link to="/login">Login</Link></li>
        <li><Link to="/register">Register</Link></li>
      </ul>
    </nav>
  );
};

export default Navbar;
