import "./Nav.css"
import Login from './Login'
import { useState } from 'react'
import { Popup } from 'reactjs-popup';
import Signup from './Signup';



const Nav = () => {
  const [isOpen, setIsOpen] = useState(false)

  return (

    <div className='wrapper'>
      <div className='logo'>
        <img src="https://cdn.cookielaw.org/logos/dd6b162f-1a32-456a-9cfe-897231c7763c/4345ea78-053c-46d2-b11e-09adaef973dc/Netflix_Logo_PMS.png" />
      </div>
      <div className='nav-items'>
        <ul>
          <li>About</li>
          <li>Blog</li>
          <li>Feedback</li>
          <li>Services</li>
        </ul>
      </div>

      <div className='sign-up-details'>
        <ul>
          <li>
            <button className="sign-up-btn" onClick={() => setIsOpen(!isOpen)}>Sign up</button>
            <Popup open={isOpen} onClose={() => setIsOpen(!isOpen)}>
              <Signup />
            </Popup>
          </li>
        </ul>
      </div>
      <Login />
    </div>
  )
}

export default Nav