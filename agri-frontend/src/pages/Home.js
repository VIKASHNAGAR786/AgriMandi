import React from 'react';
import { useNavigate } from 'react-router-dom';
import './Home.css';

function Home() {
  const navigate = useNavigate();

  const handleGetStartedClick = () => {
    navigate('/register');
  };

  return (
    <div className="home-container">
      <header className="home-header">
        <h1>Welcome to AgriMandi</h1>
        <p class="intro-text">
    🌱 <strong>AgriMandi</strong> is your <span class="highlight">gateway</span> to a direct connection between farmers and buyers—bringing fresh produce to your doorstep with just a click. 🚜🤝
</p>

      </header>
<section className="features-section">
  <h2>Why Choose AgriMandi?</h2>
  <div className="features-list">
    <div className="features-column">
      <div className="feature-item">
        <img src="/images/3.jpg" alt="Direct Market" className="feature-image" />
        <p>🌾 <strong>Direct Market Access:</strong> Empowering farmers to showcase their produce directly to buyers.</p>
      </div>
      <div className="feature-item">
        <img src="/images/2.jpg" alt="Easy Negotiations" className="feature-image" />
        <p>💬 <strong>Easy Negotiations:</strong> Transparent communication between farmers and consumers for better deals.</p>
      </div>
    </div>
    <div className="features-column">
      <div className="feature-item">
        <img src="/images/6.jpeg" alt="Secure Payments" className="feature-image" />
        <p>📊 <strong>Efficient Transactions:</strong> Simplified buying and selling process with secure payments.</p>
      </div>
      <div className="feature-item">
        <img src="/images/5.jpeg" alt="Global Reach" className="feature-image" />
        <p>🌍 <strong>Broader Reach:</strong> Expand your market beyond local boundaries.</p>
      </div>
    </div>
  </div>
</section>



      
      <section className="how-it-works">
        <h2>How It Works</h2>
        <div className="steps-container">
          <div className="step">
            <img src="/images/register.jpg" alt="Register" className="step-image" />
            <h3>1. Register</h3>
            <p>Create your account as a farmer or buyer to get started.</p>
          </div>
          <div className="step">
            <img src="/images/list.webp" alt="List Products" className="step-image" />
            <h3>2. Showcase Products</h3>
            <p>Farmers can list their products with descriptions and prices.</p>
          </div>
          <div className="step">
            <img src="/images/list.webp" alt="Connect" className="step-image" />
            <h3>3. Connect</h3>
            <p>Buyers can browse and negotiate directly with farmers.</p>
          </div>
          <div className="step">
            <img src="/images/trade.jpeg" alt="Trade" className="step-image" />
            <h3>4. Trade</h3>
            <p>Finalize deals and complete transactions seamlessly.</p>
          </div>
        </div>
      </section>

      <section className="testimonials-section">
  <h2>What Our Users Say</h2>
  <div className="testimonials-container">
    <div className="testimonial">
      <p>"AgriMandi has revolutionized the way I sell my produce. The direct connection with buyers has increased my income!"</p>
      <h4>- Ramesh Kumar, Farmer</h4>
    </div>
    <div className="testimonial">
      <p>"Buying fresh produce directly from farmers has never been easier. I love the transparency and quality."</p>
      <h4>- Anjali Sharma, Buyer</h4>
    </div>
  </div>
</section>

<section className="impact-section">
  <h2>Our Impact</h2>
  <div className="impact-stats">
    <div className="stat">
      <h3>10,000+</h3>
      <p>Farmers Empowered</p>
    </div>
    <div className="stat">
      <h3>50,000+</h3>
      <p>Transactions Completed</p>
    </div>
    <div className="stat">
      <h3>20+</h3>
      <p>States Covered</p>
    </div>
  </div>
</section>

<section className="blog-section">
  <h2>Latest Insights</h2>
  <div className="blog-container">
    <div className="blog-post">
      <img src="/images/blog1.jpg" alt="Blog Post 1" className="blog-image" />
      <h4>5 Tips for Selling Produce Online</h4>
      <p>Learn how to optimize your listings and attract more buyers.</p>
      <a href="/blog/1">Read More</a>
    </div>
    <div className="blog-post">
      <img src="/images/blog2.jpg" alt="Blog Post 2" className="blog-image" />
      <h4>The Future of Digital Agriculture</h4>
      <p>Discover how technology is shaping the agricultural industry.</p>
      <a href="/blog/2">Read More</a>
    </div>
  </div>
</section>

<section className="cta-section">
  <h2>Ready to Start?</h2>
  <p>Sign up today and experience the benefits of connecting directly with farmers and buyers.</p>
  <button className="cta-btn" onClick={() => navigate('/register')}>Join Now</button>
</section>


<section className="faq-section">
  <h2>Frequently Asked Questions</h2>
  <div className="faq-container">
    <div className="faq-item">
      <h4>How do I register?</h4>
      <p>Click the "Get Started" button and fill out the registration form to create an account.</p>
    </div>
    <div className="faq-item">
      <h4>Is there a fee for using AgriMandi?</h4>
      <p>No, registering and using the platform is completely free for both farmers and buyers.</p>
    </div>
    <div className="faq-item">
      <h4>How are payments handled?</h4>
      <p>Payments are securely processed directly between farmers and buyers via integrated payment systems.</p>
    </div>
  </div>
</section>

      <footer className="home-footer">
        <h2>Join AgriMandi Today!</h2>
        <p>
          Be part of the revolution in agriculture. Together, we can build a more sustainable and
          efficient food supply chain.
        </p>
        <button className="get-started-btn" onClick={handleGetStartedClick}>
          Get Started
        </button>
      </footer>
    </div>
  );
}

export default Home;
