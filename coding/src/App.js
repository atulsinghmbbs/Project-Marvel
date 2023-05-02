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

import CheckoutForm from './components/CheckoutForm';

import FeedbackPanel from './components/FeedbackPanel';
import ResetPassword from './components/ResetPassword';
import SearchDomain from './components/SearchDomain';
import StarRating from './components/StarRating';
import UserPanel from './components/UserPanel';
import OrderSummary from './components/OrderSummary';




const App = () => {
  return (
    <BrowserRouter>
      <Nav />
      {/* <SideBar /> */}
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
        <Route path='CheckoutForm' element={<CheckoutForm />} />


        <Route path='FeedbackPanel' element={<FeedbackPanel />} />
        <Route path='DisplayFeedback' element={<DisplayFeedback />} />
        <Route path='Checkout' element={<Checkout />} />
        <Route path='checkoutform' element={<CheckoutForm />} />
        <Route path='ordersummary' element={<OrderSummary />} />
        <Route path='razorpay' element={<RazPay />} />

      </Routes>
      <Footer />

      {/* <CheckoutForm /> */}
      {/* <RazPay/> */}
      {/* <Checkout/> */}
      {/* <ResetPassword /> */}
      {/* <UserPanel /> */}
    </BrowserRouter>

  )
}

export default App