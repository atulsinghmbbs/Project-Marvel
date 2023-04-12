import React, { useState } from "react";
import { useAuth0 } from "@auth0/auth0-react";
import Nav from "./Nav";

function LoginWithMe() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const { user, loginWithRedirect } = useAuth0()
    console.log("current user details", user)
    console.log(loginWithRedirect);


    const handleSubmit = (event) => {
        event.preventDefault();
        console.log("Email:", email, "Password:", password);
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