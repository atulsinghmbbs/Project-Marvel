import React from 'react'
import { useAuth0 } from "@auth0/auth0-react";
import createAuth0Client from '@auth0/auth0-spa-js';
import "./Login.css"


const Login = () => {

    const { user, loginWithRedirect, isAuthenticated, logout } = useAuth0()

    console.log("current user details", user)


    return (
        <div className='user-data'>
            {/* {isAuthenticated && <h3 className='user-name'>{user.name}</h3>} */}
            {isAuthenticated && <img className='user-image' src={user.picture} />}
            {isAuthenticated ? (
                <button className='btn-log' onClick={(e) => logout()}>Log out</button>
            ) : (
                <button className='btn-log' onClick={(e) => loginWithRedirect()}>Log in</button>
            )}

        </div>
    )
}

export default Login