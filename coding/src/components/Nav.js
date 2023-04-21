import "./Nav.css"
import Login from './Login'
import { useState } from 'react'
import Signup from './Signup';
import { NavLink } from "react-router-dom";
import { useAuth0 } from "@auth0/auth0-react";
import { signUp } from "../services/userService";


const Nav = () => {
  const [isOpen, setIsOpen] = useState(false)

  const { isAuthenticated, logout, user } = useAuth0()
  console.log("user data ", user)
  console.log("current user details", user)
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

    <div className='wrapper'>
      <div className='logo'>
        <NavLink to="/">
          <img src="https://cdn.cookielaw.org/logos/dd6b162f-1a32-456a-9cfe-897231c7763c/4345ea78-053c-46d2-b11e-09adaef973dc/Netflix_Logo_PMS.png" />
        </NavLink>
      </div>
      <div className='nav-items'>
        <ul>
          <NavLink to="/AboutPage"><li>About</li></NavLink>
          <NavLink to="/BlogPage"><li>Blog</li></NavLink>
          <li>Feedback</li>
          <li>Services</li>
        </ul>
      </div>

      <div className='sign-up-details'>
        <ul>
          <li>
            <NavLink to="/Signup">
              {isAuthenticated ? "" : (<button className="sign-up-btn" onClick={() => setIsOpen(!isOpen)}>Sign up</button>)}
            </NavLink>

          </li>
        </ul>
        {isAuthenticated ? (<button className='btn-log-out' onClick={(e) => logout()}>logout</button>) : (<Login />)}

        {isAuthenticated && <img className='user-image' src={user.picture} />}
        <div className="menu-icon" onClick={() => setIsOpen(!isOpen)}>
      <i className={isOpen ? "fas fa-times" : "fas fa-bars"}></i>


      </div>
    </div>
    {isOpen && (
    <div className="nav-items-mobile">
      <ul>
        <NavLink to="/AboutPage"><li>About</li></NavLink>
        <NavLink to="/BlogPage"><li>Blog</li></NavLink>
        <li>Feedback</li>
        <li>Services</li>
      </ul>
    </div>
  )}
</div>
  )
}

export default Nav


