import React from 'react'
import "./AboutPage.css"
import { NavLink } from 'react-router-dom'

const AboutPage = () => {
    return (
        <div className='about-image'>
            <h1 className='about-heading'>About Us</h1>
            <p className='para-1'>Our goal is to provide every customer with a fully-featured hosting package, backed by top customer service and powered by renewable green energy.</p>
            <br />
            <p className='para-2'>Our goal is to provide every customer with a fully-featured hosting package, backed by top customer service and powered by renewable green energy.</p>

            <div>
                <div className='our-website-heading-parent'>
                <h1 className='text-center text-success our-website-heading'>
                    Our Website benefits and features that we provide include:-
                </h1>
                </div>
                <div className='hr-parent'>
                    <hr className='hr ' />
                </div>
            <div className="about-icon-wrapper">
                <div className='image-and-title-wrapper'>
                    <img src="./images/free-domain-hosting.png" alt="" height="120" width="100" />
                    <h3>Free Domain Hosting</h3>
                </div>
                <div className='image-and-title-wrapper'>
                    <img src="./images/service-and-reliability.png" alt="" height="100" width="100" />
                    <h3>Security and Reliability</h3>
                </div>
                <div className='image-and-title-wrapper'>
                    <img src="./images/Easy-to-use.png" alt="" height="100" width="100" />
                    <h3>Easy to use design </h3>
                </div>
                <div className='image-and-title-wrapper'>
                    <img src="./images/guranteeduuptime.png" alt="" height="100" width="100" />
                    <h3>Guarante Uptime</h3>
                </div>
                <div className='image-and-title-wrapper'>
                    <img src="./images/advance-feature.png" alt="" height="100" width="100" />
                    <h3>Advance Features</h3>
                </div>
                <div className='image-and-title-wrapper'>
                    <img src="./images/target.png" alt="" height="100" width="100" />
                    <h3>And Much More</h3>
                </div>
            </div>
            {/* ----------------get started here */}

            <div className='get-started-about'>
                <p>We know how important your website is to you and for your business.Give us a chance to take  care of you.</p>

                <NavLink to="/Domain">
                    <button className='about-start-btn'>Get Started</button>
                </NavLink>

            </div>
        </div>
        </div>
    )
}

export default AboutPage