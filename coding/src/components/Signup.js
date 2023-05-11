import React from 'react';
import "./Signup.css";
import { Alert } from "react-bootstrap";
import { useState, useEffect } from 'react';
import Animation from './Animation';
import { bakendBaseUrl, bakendHeader } from "./BaseUrl"
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function Signup() {

    const [signupData, setSignupData] = useState("")

    const navigate = useNavigate()

    const [loading, setLoading] = useState(true)

    const [formData, setFormData] = useState({
        firstName: "",
        lastName: "",
        email: "",
        password: "",
        confirmPassword: "",
    });

    const [PasswordsMatch, setPasswordsMatch] = useState(true);

    const handleInputChange = (event) => {
        setFormData({
            ...formData,
            [event.target.name]: event.target.value,
        });

    };

    const handleSubmit = (event) => {
        event.preventDefault();

        if (
            formData.firstName &&
            formData.lastName &&
            formData.email &&
            formData.password &&
            formData.confirmPassword &&
            formData.password === formData.confirmPassword
        ) {
            console.log("Form Data", formData);
            axios.post(`${bakendBaseUrl}/auth/signup`,
                {
                    firstName: formData.firstName,
                    lastName: formData.lastName,
                    email: formData.email,
                    password: formData.password

                }
            ).then(data => {
                setSignupData(data);
                console.log("backend data", signupData);
                setLoading(false);
                navigate("/", { state: { signupData: signupData } });
            });

        } else if (formData.password !== formData.confirmPassword) {
            setPasswordsMatch(false)
        } else {
            console.log("Form Data", formData);
        }
    };


    // if (loading === false) {
    //     navigate("/", { state: { signupData: signupData } })
    // }



    return (

        <div className="background-image-signup" style={{ marginTop: 900 }}>
            <form onSubmit={handleSubmit} className='sign-root'>
                <div className='sign-up-wrapper'>
                    <h2 text-3xl font-semibold text-gray-900>Create an account</h2>
                    <p>Already have an account?</p>
                    <img src="" alt="" />
                    <div>
                        <label className="signup-label">

                            <br />
                            <input type="text" name="firstName" value={formData.firstName} className="signup-input" placeholder=' First Name' onChange={handleInputChange} />
                        </label>
                    </div>
                    <div>
                        <label className="signup-label">

                            <br />
                            <input type="text" name="lastName" value={formData.lastName} className="signup-input" placeholder='Last Name' onChange={handleInputChange} />
                        </label>
                    </div>
                    <div>
                        <label className="signup-label">

                            <br />
                            <input type="email" name="email" value={formData.email} className="signup-input" placeholder='  Email' onChange={handleInputChange} />
                        </label>
                    </div>
                    <div>
                        <label className="signup-label">

                            <br />
                            <input type="password" name="password" value={formData.password} className="signup-input" placeholder=' Password' onChange={handleInputChange} />
                        </label>
                    </div>
                    <div>
                        <label className="signup-label">

                            <br />
                            <input type="password" name="confirmPassword" placeholder='  Confirm Password' value={formData.confirmPassword} className="signup-input" onChange={handleInputChange} />
                        </label>
                        {PasswordsMatch ? "" : (<h6 className='text-danger text-center'>password doesnot match</h6>)}
                    </div>
                    <button type="submit" className='btn-sign-in' >Submit</button>
                    <Animation />
                </div>

            </form>

        </div>
    )
}


export default Signup;

