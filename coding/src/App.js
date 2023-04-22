import Nav from './components/Nav';
import Header from './components/Header';
import Slider from './components/Slider';
import Templates from './components/Templates';
import { Route, Routes } from "react-router-dom";
import Domain from './components/Domain';
import Footer from './components/Footer';
import { BrowserRouter } from 'react-router-dom';
import React from 'react'
import LoginWithMe from './components/LoginWithMe';
import AboutPage from './components/AboutPage';
import BlogPage from './components/BlogPage';

import Signup from './components/Signup';
// import SideBar from './components/SideBar';

import SearchDomain from './components/SearchDomain';
import DomainAvalibility from './components/DomainAvalibility';
import Checkout from './components/Checkout';
import RazPay from './components/RazPay';
import ResetPassword from './components/ResetPassword';
import EmailVerification from './components/EmailVerification';
import FeedbackPanel from './components/FeedbackPanel';
import DisplayFeedback from './components/DisplayFeedback';
import StarRating from './components/StarRating';




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
            <RazPay/>
            <FeedbackPanel/>
            <DisplayFeedback/>
          </React.Fragment>
        } />

        <Route path='Domain' element={<Domain />} />
        <Route path='LoginWithMe' element={<LoginWithMe />} />
        <Route path='AboutPage' element={<AboutPage />} />
        <Route path='BlogPage' element={<BlogPage />} />

        <Route path='Signup' element={<Signup />} />

        <Route path='DomainAvalibility' element={<DomainAvalibility />} />
        <Route path='Checkout' element={<Checkout />} />
        <Route path='EmailVerification' element={<EmailVerification />} />

      </Routes>
      <Footer />
      <ResetPassword/>
<StarRating/>
    </BrowserRouter>

  )
}

export default App