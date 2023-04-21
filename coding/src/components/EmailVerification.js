import React, { useState } from 'react'
import "./EmailVerification.css"



const EmailVerification = () => {

    const [email, setEmail] = useState("")


    function handleSubmit(e) {
        e.preventDefault()

        fetch('http://localhost:8888/auth/reset-password-request', {
            method: 'POST',
            body: JSON.stringify({
                email: email
            }),
            headers: {
                'Content-type': 'application/json; charset=UTF-8',
            },
        })
            .then((response) => response.json())
            .then((json) => console.log(json));
    }




    return (
        <div className="forget-bg">
            <div className='emailverification'>
                <form onSubmit={handleSubmit}>
                    <input type="email" placeholder='Enter your email' value={email} onChange={(e) => { setEmail(e.target.value) }} />
                    <button type='submit'>Send Verification Link</button>
                </form>

            </div>
        </div>

    )
}

export default EmailVerification
