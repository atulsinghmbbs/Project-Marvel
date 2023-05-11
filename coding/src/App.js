import React from 'react';
import { BrowserRouter, Route, Routes } from "react-router-dom";
import AboutPage from './components/AboutPage';
import BlogPage from './components/BlogPage';
import Domain from './components/Domain';
import Footer from './components/Footer';
import Header from './components/Header';
import LoginWithMe from './components/LoginWithMe';
import Nav from './components/Nav';
import Slider from './components/Slider';
import Templates from './components/Templates';

import Signup from './components/Signup';
import RazPay from './components/RazPay';
import Checkout from './components/Checkout';
import DisplayFeedback from './components/DisplayFeedback';
import DomainAvalibility from './components/DomainAvalibility';
import EmailVerification from './components/EmailVerification';
import FeedbackPanel from './components/FeedbackPanel';
import ResetPassword from './components/ResetPassword';
import SearchDomain from './components/SearchDomain';
import StarRating from './components/StarRating';
import UserPanel from './components/UserPanel';
import OrderSummary from './components/OrderSummary';
import UserOrder from './components/UserOrder';
import ActiveServices from './components/ActiveServices';
import DNSService from './components/DNSService';




import CheckoutForm from './components/CheckoutForm';






const App = () => {
  return (
    <BrowserRouter>
      <Nav />
      <Routes>
        <Route path='/' element={
          <React.Fragment>
            <Header />
            <Slider />
            <Templates />
            <SearchDomain />
          </React.Fragment>
        } />
        <Route path='Domain' element={<Domain />} />
        <Route path='LoginWithMe' element={<LoginWithMe />} />
        <Route path='AboutPage' element={<AboutPage />} />
        <Route path='BlogPage' element={<BlogPage />} />
        <Route path='Signup' element={<Signup />} />
        <Route path='DomainAvalibility' element={<DomainAvalibility />} />
        <Route path='EmailVerification' element={<EmailVerification />} />

        <Route path='razorpay' element={<RazPay />} />
        <Route path='FeedbackPanel' element={<FeedbackPanel />} />
        <Route path='DisplayFeedback' element={<DisplayFeedback />} />
        <Route path='Checkout' element={<Checkout />} />
        <Route path='ordersummary' element={<OrderSummary />} />
        <Route path='razorpay' element={<RazPay />} />
        <Route path='UserPanel' element={<UserPanel />}></Route>
        <Route path='UserOrder' element={<UserOrder />}></Route>
        <Route path='checkoutform' element={<CheckoutForm />}></Route>
        <Route path='reset-password' element={<ResetPassword />}></Route>
        <Route path='ActiveServices' element={<ActiveServices />}></Route>
        <Route path='DNSService' element={<DNSService />}></Route>



      </Routes>
      <Footer />
    </BrowserRouter>

  )
}

export default App