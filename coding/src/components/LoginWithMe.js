import React, { useState } from "react";
import { useAuth0 } from "@auth0/auth0-react";
import "./LoginWithMe.css"
import { NavLink, json } from "react-router-dom";
import { bakendHeader, bakendBaseUrl } from "./BaseUrl";


// import { loginWithJWT } from "../services/userService";
// import  NavLink  from "react-router-dom";



function LoginWithMe() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [loginToken, setLoginToken] = useState("")

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
                localStorage.setItem("loginToken", json.token);
                console.log("Take your token:", localStorage.getItem("loginToken"));
            });
    }



    return (
        <>
            <div style={{ marginTop: 60 }} className="login-page-container">
                <div className="log-in-wrapper">
                    <h1 className="login-page-heading">Welcome Back!</h1>

                    <form onSubmit={handleSubmit} className="form">
                        <label className="login-label">
                            <input type="email" value={email} onChange={(event) => setEmail(event.target.value)} placeholder="Enter User Name" className="login-input" />
                        </label>
                        <br />
                        <label className="login-label">
                            <input type="password" value={password} onChange={(event) => setPassword(event.target.value)} placeholder="Enter Password" className="login-input" lassName="login-input" />
                        </label>

                        <NavLink to="/EmailVerification">
                            <p className=" forgot-password text-primary" style={{ fontWeight: 600 }}>Forgot Password?</p>
                        </NavLink>

                        <button type="submit" className="login-withme-submit-btn">Submit</button>
                        <br />
                        <button onClick={() => loginWithRedirect()} className="continue-with-google-btn" ><i class="fa-brands fa-google"></i>Sign in with Google</button>
                    </form>
                    <br />

                </div>
            </div>
        </>
    );
}

export default LoginWithMe;
