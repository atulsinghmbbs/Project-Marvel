import React from 'react'
import { useAuth0 } from "@auth0/auth0-react";
import "./Login.css"
import { signUp } from '../services/userService';


const Login = () => {

    const { user, loginWithRedirect, isAuthenticated, logout } = useAuth0()

    console.log("current user details", user)


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
            {/* {isAuthenticated && <h3 className='user-name'>{user.name}</h3>} */}
            {isAuthenticated && <img className='user-image' src={user.picture} />}
            {isAuthenticated ? (
                <button className='btn-log-out' onClick={(e) => logout()}>Log out</button>
            ) : (
                <button className='btn-log-in' onClick={(e) => loginWithRedirect()}>Log in</button>
            )}

        </div>
    )
   
      
}

export default Login