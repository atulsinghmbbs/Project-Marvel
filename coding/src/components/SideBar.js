import "./SideBar.css"
import React from 'react'
import { useState } from "react";
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap-icons/font/bootstrap-icons.css';


function SideBar() {

    const [showNav, setShowNav] = useState(true)

    return <div className={`body-area${showNav ? ' body-pd' : ''}`}>
        <header className={`header${showNav ? ' body-pd' : ''}`} style={{ marginTop: 200 }}>
            <div className="header_toggle">
                <i
                    className={`bi ${showNav ? 'bi-x' : 'bi-list'}`}
                    onClick={() => setShowNav(!showNav)} />
            </div>
        </header>
        <div className={`l-navbar${showNav ? ' show' : ''}`}>
            <nav className="nav">
                <div>
                    <a className="nav_logo">
                        <i class="bi bi-house"></i>
                        <span className="nav_logo-name">Home</span>
                    </a>
                    <a className="nav_logo">
                        <i class="bi bi-file-person"></i>
                        <span className="nav_logo-name">About Us</span>
                    </a>
                    <div className="nav_list">
                        <a className="nav_link">
                            <i class="bi bi-speedometer2"></i>
                            <span className="nav_name">Dashboard</span>
                        </a>
                        <a className="nav_link">
                            <i class="bi bi-cart-check"></i>
                            <span className="nav_name">My Cart</span>
                        </a>
                        <a className="nav_link">
                            <i class="bi bi-chat-heart"></i>
                            <span className="nav_name">Live Chat</span>
                        </a>
                        <a className="nav_link">
                            <i class="bi bi-gear-wide-connected"></i>
                            <span className="nav_name">Setting</span>
                        </a>
                    </div>
                </div>
                <a className="nav_link">
                    <i className='bi bi-box-arrow-left nav_icon' /><span className="nav_name">Log Out</span>
                </a>
            </nav>
        </div>
    </div>
}

export default SideBar;
