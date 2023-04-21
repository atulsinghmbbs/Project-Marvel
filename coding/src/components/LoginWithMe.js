import React, { useState } from "react";
import { useAuth0 } from "@auth0/auth0-react";

import "./LoginWithMe.css"


import Nav from "./Nav";
import { loginWithJWT } from "../services/userService";
import { Hidden } from "@mui/material";


function LoginWithMe() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const { user, loginWithRedirect } = useAuth0()
    console.log("current user details", user)
    console.log(loginWithRedirect);


    //const {http}= loginWithJWT();

    const handleSubmit = (event) => {
        event.preventDefault();
        console.log("Email: ====", email, "Password:", password);



        // http.post('/login',{userName:email,password:password}).then((resp)=>{
        //     console.log(resp.data);

        //     console.log("0000000000000000000000000000000000000000000000000000000000000000000")
        // })

        const loginFormData = { "username": email, "password": password }

        loginWithJWT(loginFormData).then((resp) => {

            console.log(resp);
            console.log("login successfully.....")
            console.log("=========================================================================")
            alert("login finally done and dusted")
        }).catch((err) => {

            console.log(err);
            console.log("login failed....")
            console.log("=========================================================================")
            alert("login finally kabhi aesa na ho")


        })
        console.log("========================Akash yadav ===========================")

    };



    return (
        <>
            <div style={{ marginTop: 60 }}>
                <div className="background-image">
                    <div className="log-in-wrapper">
                        <h1 className="login-page-heading">Project Marvel</h1>
                        <form onSubmit={handleSubmit}>
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

                            <p className=" forgot-password text-primary" style={{ fontWeight: 600 }}>Forgot Password?</p>

                            <button type="submit" className="login-withme-submit-btn">Submit</button>
                        </form>
                        <button onClick={() => loginWithRedirect()} className="continue-with-google-btn" ><i class="fa-brands fa-google"></i>Sign in with Google</button>
                        <br />
                    </div>
                </div>
            </div>
        </>
    );
}

export default LoginWithMe;
