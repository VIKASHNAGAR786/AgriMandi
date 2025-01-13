import React, { useState } from "react";
import axios from "axios";
import "./Register.css"; // Optional, for styling

const Register = () => {
  const [user, setUser] = useState({
    name: "",
    email: "",
    password: "",
    role: "FARMER", // Default role
  });

  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");
  const [isLoading, setIsLoading] = useState(false);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setUser({
      ...user,
      [name]: value,
    });
  };

  const validateForm = () => {
    if (!user.name.trim() || !user.email.trim() || !user.password.trim()) {
      setError("All fields are required.");
      return false;
    }
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(user.email)) {
      setError("Please enter a valid email.");
      return false;
    }
    if (user.password.length < 6) {
      setError("Password must be at least 6 characters long.");
      return false;
    }
    return true;
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");
    setSuccess("");

    if (!validateForm()) return;

    setIsLoading(true); // Start loading
    const payload = {
      name: user.name,
      email: user.email,
      password: user.password,
      roles: [{ name: user.role }],
    };

    try {
      const response = await axios.post("http://localhost:8080/api/users", payload);
      setSuccess("Registration successful! You can now log in.");
      setUser({ name: "", email: "", password: "", role: "FARMER" }); // Reset form
    } catch (err) {
      if (err.response) {
        setError(err.response.data.message || "Registration failed.");
      } else {
        setError("Error occurred while registering. Please try again.");
      }
    } finally {
      setIsLoading(false); // Stop loading
    }
  };

  return (
    <div className="register-container">
      <h2>Register</h2>

      {error && <div className="error-message" role="alert">{error}</div>}
      {success && <div className="success-message" role="alert">{success}</div>}

      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="name">Name:</label>
          <input
            type="text"
            id="name"
            name="name"
            value={user.name}
            onChange={handleChange}
            required
            aria-required="true"
            aria-label="Enter your full name"
          />
        </div>

        <div className="form-group">
          <label htmlFor="email">Email:</label>
          <input
            type="email"
            id="email"
            name="email"
            value={user.email}
            onChange={handleChange}
            required
            aria-required="true"
            aria-label="Enter your email address"
          />
        </div>

        <div className="form-group">
          <label htmlFor="password">Password:</label>
          <input
            type="password"
            id="password"
            name="password"
            value={user.password}
            onChange={handleChange}
            required
            aria-required="true"
            aria-label="Enter a secure password"
          />
        </div>

        <div className="form-group">
          <label htmlFor="role">Role:</label>
          <select
            id="role"
            name="role"
            value={user.role}
            onChange={handleChange}
            required
            aria-required="true"
            aria-label="Select your role"
          >
            <option value="FARMER">Farmer</option>
            <option value="BUYER">Buyer</option>
            <option value="ADMIN">Admin</option>
          </select>
        </div>

        <button type="submit" disabled={isLoading}>
          {isLoading ? "Registering..." : "Register"}
        </button>
      </form>
    </div>
  );
};

export default Register;
