import React, { useState } from 'react'
import { useAuth0 } from "@auth0/auth0-react";
import "./Login.css"

import { NavLink } from 'react-router-dom';

import { signUp } from '../services/userService';



const Login = (props) => {

    // const { user, loginWithRedirect, isAuthenticated, logout } = useAuth0()


    console.log("current user details", props.user)
    // console.log(props.loginWithRedirect);

    if(isAuthenticated){
        const userGoogleData = {
            "firstName":user.given_name,
            "lastName":user.family_name,
            "email":user.email,
            "password":null,
            
        }
        signUp(userGoogleData).then((resp)=>{

            console.log(resp);
            console.log("succesfully signUp.....")
            console.log("=========================================================================")

        }).catch((err)=>{

            console.log(err);
            console.log("signup failed....")
            console.log("=========================================================================")


        })
    }else{
        console.log("Invalid User credentials...")
    }

    return (
        <div className='user-data'>
            <NavLink to="/LoginWithMe">
                <button className='btn-log-in'>Log in</button>
            </NavLink>
        </div>
    )
   
      
}

export default Login