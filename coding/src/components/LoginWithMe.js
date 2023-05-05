import React, { useState } from "react";
import { useAuth0 } from "@auth0/auth0-react";
import "./LoginWithMe.css"
import { NavLink, json } from "react-router-dom";
import { bakendHeader, bakendBaseUrl } from "./BaseUrl";
import { useEffect } from "react";
import Animatelogin from "./Animatelogin";


function LoginWithMe() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [loginToken, setLoginToken] = useState("")
    const [expirationTime, setExpirationTime] = useState("")

    const [loginError, setLoginError] = useState("")

    const [loading, setLoading] = useState(true)



    const { user, loginWithRedirect } = useAuth0()
    console.log("current user details", user)
    console.log(loginWithRedirect);


    //const {http}= loginWithJWT();

    const handleSubmit = (event) => {
        event.preventDefault();
        console.log('email', email);
        console.log('password', password);

        fetch(`${bakendBaseUrl}/auth/login`, {
            method: 'POST',
            body: JSON.stringify({
                email: email,
                password: password

            }),
            headers: {
                'Content-type': 'application/json; charset=UTF-8',
            },
        })
            .then((response) => response.json())
            .then((json) => {
                setLoginToken(json.token);
                setExpirationTime(json.expiresAt)
                localStorage.setItem("loginToken", json.token);
                localStorage.setItem("expirationTime", expirationTime)
                console.log("Take your token:", localStorage.getItem("loginToken"));
                console.log("your Expiration time:", localStorage.getItem("expirationTime"));
            });
    }

    console.log('token', loginToken)
    console.log('expiry time', expirationTime)

    const redirectToHome = () => {
        if (loginToken === localStorage.getItem("loginToken")) {
            window.location.href = "/";
        } else {
            setLoginError("Please write the correct Password and Email");
        }
    }



    useEffect(() => {
        if (loginToken !== localStorage.getItem("loginToken")) {
            setLoading(false);
        } else {
            redirectToHome();
        }
    }, [loginToken]);



    return (
        <>
            <div style={{ marginTop: 60 }} className="background-image">
                <div className="log-in-wrapper">
                    <h1 className="login-page-heading">Project Marvel</h1>

                    <form onSubmit={handleSubmit} className="form">
                        <br />
                        <input type="email" value={email} onChange={(event) => setEmail(event.target.value)} placeholder=" User ID" className="login-input" />
                        <br />
                        <br />
                        <input type="password" value={password} onChange={(event) => setPassword(event.target.value)} placeholder="Enter Password" className="login-input" lassName="login-input" />

                        <NavLink to="/EmailVerification">
                            <p className=" forgot-password text-primary" style={{ fontWeight: 600 }}>Forgot Password?</p>
                        </NavLink>

                        {!loading ? <h6 className="text-center text-danger">{loginError}</h6> : ""}
                        <button type="submit" onClick={redirectToHome} className="login-withme-submit-btn">Submit</button>
                    </form>
                    <button onClick={() => loginWithRedirect()} className="continue-with-google-btn" ><i class="fa-brands fa-google"></i>Sign in with Google</button>
                    <br />
                    <Animatelogin />

                </div>
            </div>
        </>
    )
}

export default LoginWithMe;


// ////////////////////////ketan js
//
// import React, { useState } from "react";
// import { useAuth0 } from "@auth0/auth0-react";
// import "./LoginWithMe.css"
// import { NavLink, json } from "react-router-dom";
// import { bakendHeader, bakendBaseUrl } from "./BaseUrl";
//
//
// import Lottie from 'react-lottie';
// import animationData from './lotties/login.json';
// import Loader from './Animatelogin'
// import Animatelogin from "./Animatelogin";
//
//
//
// function LoginWithMe() {
//     const [email, setEmail] = useState("");
//     const [password, setPassword] = useState("");
//     const [loginToken, setLoginToken] = useState("")
//
//     const { user, loginWithRedirect } = useAuth0()
//     console.log("current user details", user)
//     console.log(loginWithRedirect);
//
//
//
//
//     const handleSubmit = (event) => {
//         event.preventDefault();
//         console.log('email', email);
//         console.log('password', password);
//
//
//
//
//
//
//         fetch(`${bakendBaseUrl}/auth/login`, {
//             method: 'POST',
//             body: JSON.stringify({
//                 email: email,
//                 password: password
//
//             }),
//             headers: {
//                 'Content-type': 'application/json; charset=UTF-8',
//             },
//         })
//             .then((response) => response.json())
//             .then((json) => {
//                 setLoginToken(json.token);
//                 localStorage.setItem("loginToken", json.token);
//                 console.log("Take your token:", localStorage.getItem("loginToken"));
//             });
//     }
//
//
//
//     return (
//         <>
//             <div id="page-123" style={{ marginTop: 60 }} className="login-page-container">
//                 <div className="log-in-wrapper">
//                     <h1 className="login-page-heading">Welcome Back!</h1>
//
//                     <form onSubmit={handleSubmit} className="form">
//                         <label className="login-label">
//                             <input type="email" value={email} onChange={(event) => setEmail(event.target.value)} placeholder=" User Name" className="login-input" />
//                         </label>
//                         <br />
//                         <label className="login-label">
//                             <input type="password" value={password} onChange={(event) => setPassword(event.target.value)} placeholder=" Password" className="login-input" lassName="login-input" />
//                         </label>
//
//                         <NavLink to="/EmailVerification">
//                             <p className=" forgot-password text-primary" style={{ fontWeight: 600 }}>Forgot Password?</p>
//                         </NavLink>
//
//                         <button type="submit" className="login-withme-submit-btn">Login</button>
//                         <br />
//                         <h4>      ----------or----------</h4>
//                         <button onClick={() => loginWithRedirect()} className="continue-with-google-btn" ><i class="fa-brands fa-google"></i>Sign in with Google</button>
//
//                     </form>
//                     <br />
//                     <Animatelogin />
//
//                 </div>
//             </div>
//         </>
//     );
// }
//
// export default LoginWithMe;
