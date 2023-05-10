import React from 'react'
import "./Header.css"
import { NavLink } from 'react-router-dom'

const Header = () => {
    return (
        <div className='header-image'>
            <p className='header-heading'>Free & Secure</p>
            <p className='header-heading-1'>    Web hosting</p>
            <p className='header-caption'>Free Hosting <span><i className="line-icon fi fi-br-tally-1"></i></span>Domain Registration   <span><i className="line-icon fi fi-br-tally-1"></i></span>Website Builder</p>
            <NavLink to="/Domain" style={{ textDecoration: 'none' }}>
                <button className='header-start-btn'>Get Started</button>
            </NavLink>
        </div>
    )
}

export default Header