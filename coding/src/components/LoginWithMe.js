import React, { useState } from "react";
import { useAuth0 } from "@auth0/auth0-react";
import Nav from "./Nav";
import { loginWithJWT } from "../services/userService";


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

        const loginFormData ={"username":email, "password":password}

        loginWithJWT(loginFormData).then((resp)=>{

            console.log(resp);
            console.log("login successfully.....")
            console.log("=========================================================================")
            alert("login finally done and dusted")
        }).catch((err)=>{

            console.log(err);
            console.log("login failed....")
            console.log("=========================================================================")
            alert("login finally kabhi aesa na ho")


        })
        console.log("========================Akash yadav ===========================")

    };



    return (
        <>
            <h1 style={{ marginTop: 100 }}>this is login page</h1>

            <form onSubmit={handleSubmit} style={{ display: "flex", flexDirection: "column", alignItems: "center" }}>
                <label>
                    User ID:
                    <input type="email" value={email} onChange={(event) => setEmail(event.target.value)} />
                </label>
                <label>
                    Password:
                    <input type="password" value={password} onChange={(event) => setPassword(event.target.value)} />
                </label>
                <button type="submit">Submit</button>
            </form>
            <button onClick={() => loginWithRedirect()}>Continue with Google</button>
        </>
    );
}

export default LoginWithMe;
