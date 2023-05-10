import React from 'react'
import "./Footer.css"




const Footer = () => {

    return (

        <div className="container-fluid footer-container bg-dark footer-container">
            <div className="row h-50 justify-content-between">
                <div className="col-lg-3 fs-3 text-xl text-white mt-4">
                    Smart Website Solutions for small bussiness. Be part of the family!
                </div>
                <div className="col-lg-2 text-white mt-3">
                    <ul className='list-unstyled first-list-items'>
                        <li>About Us</li>
                        <li>Contact</li>
                        <li>Green Hosting</li>
                        <li>Affilates</li>
                        <li>Service Guarante</li>
                        <li>Customer</li>
                        <li>Testimonial</li>
                        <li>Careers</li>
                    </ul>
                </div>
                <div className="col-lg-2 text-white mt-3 second-list-heading custumer-heading">
                    Custumer Tools
                    <div className="row">
                        <div className="col-lg-12">
                            <ul className='list-unstyled second-list-items'>
                                <li>My Account</li>
                                <li>Customer Care Center</li>
                                <li>Knowledge Base</li>
                                <li>Blog</li>
                                <li>Network Status</li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div className="col-lg-2 text-white mt-3 third-list-heading">
                    Web hosting
                    <div className="row">
                        <div className="col-lg-12">
                            <ul className='list-unstyled third-list-items'>
                                <li>Site hosting</li>
                                <li>Optimiszed WordPress</li>
                                <li>VPS hosting</li>
                                <li>Reseller Hosting</li>
                            </ul>
                        </div>
                    </div>
                </div>

            </div>
            <div className="row">
                <div className="col-lg-2 fs-3 text-white mt-1">
                    Follow Us
                </div>
                <div className="row">
                    <div className="col-lg-2 mb-3">

                        <a href="https://www.facebook.com/HAARMKinfotech"><i class="fa-brands fa-square-facebook text-white fs-4 m-2">
                        </i></a>
                        <a href="https://www.linkedin.com/company/haarmk-infotech-pvt-ltd/">
                            <i class="fa-brands fa-linkedin  text-white fs-4 m-2 ">
                            </i>
                        </a>
                        <a href="https://instagram.com/haarmk.infotech?igshid=YmMyMTA2M2Y="><i class="fa-brands fa-square-instagram  text-white fs-4 m-2"></i></a>

                    </div>
                </div>
            </div>
        </div>

    )
}

export default Footer