import React, { useState } from 'react'
import { useAuth0 } from "@auth0/auth0-react";
import "./Login.css"

import { NavLink } from 'react-router-dom';

import { signUp } from '../services/userService';



const Login = (props) => {


    return (
        <div className='user-data'>
            <NavLink to="/LoginWithMe">
                <button className='btn-log-in'>Log in</button>
            </NavLink>
        </div>
    )
   
      
}

export default Login