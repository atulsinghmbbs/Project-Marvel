import React, { useState } from 'react'
import "./ResetPassword.css"

const ResetPassword = () => {
    const url = window.location.href;

    const token = url.split("=")
    const finalToken = token[1]

    const [password, setPassword] = useState("")
    const [confirmPassword, setConfirmPassword] = useState("")
    const [matchPassword, setMatchPassword] = useState(true)



    function handleSubmit(e) {
        e.preventDefault();
        if (password === confirmPassword) {
            setMatchPassword(true);

            fetch('https://jsonplaceholder.typicode.com/posts', {
                method: 'POST',
                body: JSON.stringify({
                    finalToken: finalToken,
                    pass: password
                }),
                headers: {
                    'Content-type': 'application/json; charset=UTF-8',
                },
            })
                .then((response) => response.json())
                .then((json) => console.log(json));
            console.log("Passwords match")
        } else {
            setMatchPassword(false);
            console.log("Passwords do not match")
        }
    }



    return (
        <div className='password'>
            <h3>Reset Password</h3>
            <form className='input' onSubmit={handleSubmit}>
                <input type="text" value={password} placeholder='Enter your new password' onChange={(e) => setPassword(e.target.value)} />
                <input type="password" value={confirmPassword} placeholder='Re-Enter your new password' onChange={(e) => setConfirmPassword(e.target.value)} />
                {!matchPassword && <p className="error">Passwords do not match</p>}
                <button type='submit'>Submit</button>
            </form>
        </div>
    )
}

export default ResetPassword
