import React from 'react'
import "./Nav.css"
import Login from './Login'
import { useState } from 'react'
import { Popup } from 'reactjs-popup';



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
        </ul>
      </div>

      <div className='sign-up-details'>
        <ul>
          <li>
            <button onClick={() => setIsOpen(!isOpen)}>Sign up</button>
            <Popup open={isOpen} onClose={() => setIsOpen(!isOpen)}>
              <form>
                fdsjk
              </form>
            </Popup>
          </li>
        </ul>
      </div>
      <Login />
    </div>
  )
}

export default Nav