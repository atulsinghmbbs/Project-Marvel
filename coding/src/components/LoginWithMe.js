import React, { useState } from "react";
import { useAuth0 } from "@auth0/auth0-react";
import "./LoginWithMe.css"
import { NavLink, json } from "react-router-dom";
import { bakendHeader, bakendBaseUrl } from "./BaseUrl";


import { loginWithJWT } from "../services/userService";




function LoginWithMe() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [loginToken, setLoginToken] = useState("")
    const [expirationTime, setExpirationTime] = useState("")

    const { user, loginWithRedirect } = useAuth0()
    console.log("current user details", user)
    console.log(loginWithRedirect);


    //const {http}= loginWithJWT();

    const handleSubmit = (event) => {
        event.preventDefault();
        console.log('email', email);
        console.log('password', password);


        // const loginFormData = { "username": email, "password": password }

        //     loginWithJWT(loginFormData).then((resp) => {

        //         console.log(resp);
        //         console.log("login successfully.....")
        //         console.log("=========================================================================")
        //         alert("login finally done and dusted")
        //     }).catch((err) => {

        //         console.log(err);
        //         console.log("login failed....")
        //         console.log("=========================================================================")
        //         alert("login finally kabhi aesa na ho")


        //     })
        //     console.log("========================Akash yadav ===========================")

        // };

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



    return (
        <>
            <div style={{ marginTop: 60 }} className="background-image">
                <div className="log-in-wrapper">
                    <h1 className="login-page-heading">Project Marvel</h1>

                    <form onSubmit={handleSubmit} className="form">
                        <label className="login-label">
                            User ID
                            <br />
                            <input type="email" value={email} onChange={(event) => setEmail(event.target.value)} placeholder="Enter User Id" className="login-input" />
                        </label>
                        <br />
                        <label className="login-label">
                            Password
                            <br />
                            <input type="password" value={password} onChange={(event) => setPassword(event.target.value)} placeholder="Enter Password" className="login-input" lassName="login-input" />
                        </label>

                        <NavLink to="/EmailVerification">
                            <p className=" forgot-password text-primary" style={{ fontWeight: 600 }}>Forgot Password?</p>
                        </NavLink>

                        <button type="submit" className="login-withme-submit-btn">Submit</button>
                    </form>
                    <button onClick={() => loginWithRedirect()} className="continue-with-google-btn" ><i class="fa-brands fa-google"></i>Sign in with Google</button>
                    <br />

                </div>
            </div>
        </>
    );
}

export default LoginWithMe;
