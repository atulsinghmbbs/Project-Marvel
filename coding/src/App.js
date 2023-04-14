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
import SearchDomain from './components/SearchDomain';



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
      </Routes>
      <Footer />
    </BrowserRouter>

  )
}

export default App