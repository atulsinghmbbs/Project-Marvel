import "./Login.css"

import { NavLink } from 'react-router-dom';



const Login = () => {



    return (
        <div className='user-data'>
            <NavLink to="/LoginWithMe">
                <button className='btn-log-in'>Log in</button>
            </NavLink>
        </div>
    )

}

export default Login