
import './Templates.css'
import React from 'react'
import { NavLink } from 'react-router-dom'

const Templates = () => {
    return (
        <div className='template-body'>
            <div className="template-heading">
                <p>Choose a Free Stunning Template</p>
            </div>
            <div className='explore-heading'>
                <p>Explore 100s of Templates Designed for Bussiness Like Yours</p>
            </div>
            <div className='parent-template-btn'>
                <NavLink to="/Domain">
                    <button className='template-btn'>Get Started</button>
                </NavLink >
            </div>

            <div className='template-image-wrapper'>
                <div className='template-img-and-title'>
                    <img src="./images/pc.jpg" height="220px" width="400" alt="" />
                    <h3>Marketing</h3>
                </div>
                <div className='template-img-and-title'>
                    <img src="./images/drink.jpg" height="220px" width="400" alt="" />
                    <h3>Online Store</h3>
                </div>
                <div className='template-img-and-title'>
                    <img src="./images/store.jpg" height="220px" width="400" alt="" />
                    <h3>Bussiness</h3>
                </div>
                <div className='template-img-and-title'>
                    <img src="./images/health.jpg" height="220px" width="400" alt="" />
                    <h3>Health care</h3>
                </div>
            </div>

            {/* <div className="upper-two-img">
                <img className='upper-image' src="./images/pc.jpg" height="220px" width="400" alt="" />
                <img className='upper-image' src="./images/drink.jpg" height="220px" width="400" alt="" />
            </div>
            <div className="upper-img-naming">
                <h3 className='naming-upper-first-image'>Marketing</h3>
                <h3 className='naming-upper-second-image'>Online Store</h3>
            </div>

            <div className="lower-two-img">
                <img className='lower-image' src="./images/store.jpg" height="220px" width="400" alt="" />
                <img className='lower-image' src="./images/health.jpg" height="220px" width="400" alt="" />
            </div>
            <div className="lower-img-naming">
                <h3 className='naming-lower-first-image'>Bussiness</h3>
                <h3 className='naming-lower-second-image'>Health Care</h3>
            </div> */}

            {/* -------------after images */}

            <div className="template-second-heading">
                {/* <p>What Our Client Say?</p> */}
            </div>
            <div className="client-say-heading">
                {/* <p>Read What Our Client Say!</p> */}
            </div>
        </div>

    )
}

export default Templates






