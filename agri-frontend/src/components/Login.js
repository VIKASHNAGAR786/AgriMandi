import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate, Link } from 'react-router-dom';
import './Login.css'; // Import the CSS file

const Login = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [errorMessage, setErrorMessage] = useState('');
  const navigate = useNavigate();

  const handleLogin = async (event) => {
    event.preventDefault();

    try {
      // Sending email and password as JSON in the request body
      const response = await axios.post('http://localhost:8080/api/auth/login', {
        email,
        password,
      });

      if (response.data.token) {
        // Save token to localStorage
        localStorage.setItem('token', response.data.token);

        // Navigate based on role
        const role = response.data.role;
        if (role === 'FARMER') {
          navigate('/profile');
        } else {
          navigate('/profile');
        }
      } else {
        // Handle unexpected response without a token
        setErrorMessage(response.data.message || 'Login failed');
      }
    } catch (error) {
      // Handle errors and set error message
      setErrorMessage(
        error.response?.data?.message || 'An error occurred. Please try again.'
      );
    }
  };

  return (
    <div className="login-container">
      <h2>Login</h2>
      <form onSubmit={handleLogin}>
        <input
          type="email"
          placeholder="Email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          className="login-input"
          required
        />
        <input
          type="password"
          placeholder="Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          className="login-input"
          required
        />
        <button type="submit" className="login-button">
          Login
        </button>
      </form>
      {errorMessage && <p className="error-message">{errorMessage}</p>}
      <p className="register-prompt">
        Don't have an account?{' '}
        <Link to="/register" className="register-link">
          Register here
        </Link>
      </p>
    </div>
  );
};

export default Login;
