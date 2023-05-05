import React, { useState } from 'react'
import "./EmailVerification.css"
import { bakendBaseUrl } from './BaseUrl'



const EmailVerification = () => {

    const [email, setEmail] = useState("")

    const [verificationStatus, setVerficationStatus] = useState("")

    const [disableButton, setDisableButton] = useState(true)



    function handleSubmit(e) {
        try {
            e.preventDefault()
            fetch(`${bakendBaseUrl}/auth/reset-password-request`, {
                method: 'POST',
                body: JSON.stringify({
                    email: email
                }),
                headers: {
                    'Content-type': 'application/json; charset=UTF-8',
                },
            })
                .then((response) => response.json())
                .then((json) => {
                    setVerficationStatus(json)
                    setDisableButton(false)
                })
        } catch (error) {
            console.log(error.message);
        }
    }

    console.log("backend response", verificationStatus);

    if (verificationStatus.status === "Success") {
        alert("Check Your Email For Reset Password")
        window.location.href = "/";
    } else {
        console.log('you are doing something wrong');
    }


    const timing = setInterval(() => {
        setDisableButton(true)
    }, 5000)

    clearInterval("clear everything", timing)






    return (
        <div className="forget-bg">
            <div className='emailverification'>
                <form onSubmit={handleSubmit}>
                    <input type="email" placeholder='Enter your email' value={email} onChange={(e) => { setEmail(e.target.value) }} />
                    <button type='submit' disabled={!disableButton} >Send Verification Link</button>
                </form>

            </div>
        </div>

    )
}

export default EmailVerification
