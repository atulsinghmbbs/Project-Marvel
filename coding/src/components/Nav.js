import "./Nav.css"
import Login from './Login'
import { useState } from 'react'
import Signup from './Signup';
import { NavLink } from "react-router-dom";
import { useAuth0 } from "@auth0/auth0-react";
import { useMediaQuery } from "@mui/material";


const Nav = () => {
  const [isOpen, setIsOpen] = useState(false)
  const [showNavItems, setShowNavItems] = useState(false)

  const isMobile = useMediaQuery('(max-width: 600px)');

  const { isAuthenticated, logout, user } = useAuth0()
  console.log("user data ", user)

  console.log("current user details", user)
  // console.log(props.loginWithRedirect);

  if (isAuthenticated) {
    const userGoogleData = {
      "firstName": user.given_name,
      "lastName": user.family_name,
      "email": user.email,
      "password": null,

    }
    //   signUp(userGoogleData).then((resp) => {

    //     console.log(resp);
    //     console.log("succesfully signUp.....")
    //     console.log("=========================================================================")

    //   }).catch((err) => {

    //     console.log(err);
    //     console.log("signup failed....")
    //     console.log("=========================================================================")


    //   })
    // } else {
    //   console.log("Invalid User credentials...")
  }

  function showItems() {
    setShowNavItems(!showNavItems)
  }


  return (

    <div className='wrapper'>
      <div className='logo'>
        <NavLink to="/">
          <img src="https://cdn.cookielaw.org/logos/dd6b162f-1a32-456a-9cfe-897231c7763c/4345ea78-053c-46d2-b11e-09adaef973dc/Netflix_Logo_PMS.png" />
        </NavLink>
      </div>
      {isMobile ? (<h1 style={{ marginRight: 100 }}><i onClick={showItems} class="fa-solid fa-bars"></i></h1>)
        : (
          <div>
            <div className='nav-items'>
              <ul>
                <NavLink to="/AboutPage" style={{ textDecoration: 'none' }}><li>About</li></NavLink>
                <NavLink to="/BlogPage" style={{ textDecoration: 'none' }} ><li>Blog</li></NavLink>
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

              <NavLink to="/Checkout">
                <i style={{ cursor: "pointer", marginLeft: 40 }} className="cart-icon fa-solid fa-cart-plus"></i>
              </NavLink>
            </div>
          </div>

        )
      }
    </div>
  )
}

export default Nav




// -------------------------------====================================================







// import "./Nav.css"
// import Login from './Login'
// import { useState } from 'react'
// import Signup from './Signup';
// import { NavLink } from "react-router-dom";
// import { useAuth0 } from "@auth0/auth0-react";
// import { useMediaQuery } from "@mui/material";


// const Nav = () => {
//   const [isOpen, setIsOpen] = useState(false)
//   const [showNavItems, setShowNavItems] = useState(false)

//   const isMobile = useMediaQuery('(max-width: 600px)');

//   const { isAuthenticated, logout, user } = useAuth0()
//   console.log("user data ", user)

//   console.log("current user details", user)
//   // console.log(props.loginWithRedirect);

//   if (isAuthenticated) {
//     const userGoogleData = {
//       "firstName": user.given_name,
//       "lastName": user.family_name,
//       "email": user.email,
//       "password": null,

//     }
//     //   signUp(userGoogleData).then((resp) => {

//     //     console.log(resp);
//     //     console.log("succesfully signUp.....")
//     //     console.log("=========================================================================")

//     //   }).catch((err) => {

//     //     console.log(err);
//     //     console.log("signup failed....")
//     //     console.log("=========================================================================")


//     //   })
//     // } else {
//     //   console.log("Invalid User credentials...")
//   }

//   function showItems() {
//     setShowNavItems(!showNavItems)
//   }


//   return (

//     <div className='wrapper'>
//       <div className='logo'>
//         <NavLink to="/">
//           <img src="https://cdn.cookielaw.org/logos/dd6b162f-1a32-456a-9cfe-897231c7763c/4345ea78-053c-46d2-b11e-09adaef973dc/Netflix_Logo_PMS.png" />
//         </NavLink>
//       </div>
//       {isMobile ? (<h1 style={{ marginRight: 100 }}><i onClick={showItems} class="fa-solid fa-bars"></i></h1>)
//         : (
//           <div>
//             <div className='nav-items'>
//               <ul>
//                 <NavLink to="/AboutPage" style={{ textDecoration: 'none' }}><li>About</li></NavLink>
//                 <NavLink to="/BlogPage" style={{ textDecoration: 'none' }} ><li>Blog</li></NavLink>
//                 <li>Feedback</li>
//                 <li>Services</li>
//               </ul>
//             </div>

//             <div className='sign-up-details'>
//               <ul>
//                 <li>
//                   <NavLink to="/Signup">
//                     {isAuthenticated ? "" : (<button className="sign-up-btn" onClick={() => setIsOpen(!isOpen)}>Sign up</button>)}
//                   </NavLink>

//                 </li>
//               </ul>
//               {isAuthenticated ? (<button className='btn-log-out' onClick={(e) => logout()}>logout</button>) : (<Login />)}

//               {isAuthenticated && <img className='user-image' src={user.picture} />}

//               <NavLink to="/Checkout">
//                 <i style={{ cursor: "pointer", marginLeft: 40 }} className="cart-icon fa-solid fa-cart-plus"></i>
//               </NavLink>
//             </div>
//           </div>

//         )
//       }
//     </div>
//   )
// }

// export default Nav