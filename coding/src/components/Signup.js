import React from 'react'
import "./Signup.css"
import { Alert } from "react-bootstrap";
import { useState } from 'react';
import { signUp } from '../services/userService';
import Animation from './Animation';
import { colors } from '@mui/material';


function Signup() {


    const [formData, setFormData] = useState({
        firstName: "",
        lastName: "",
        email: "",
        password: "",
        confirmPassword: "",
    });
    const [error, setError] = useState(null);
    const [PasswordsMatch, setPasswordsMatch] = useState()

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
            console.log(formData);
            setError(null);
        } else {
            setError("Passwords do not match");
        }
    };


    console.log("data")

    //console.log("datafjskf", event)

    console.log("==============================================================")


    if (formData.password !== formData.confirmPassword) {
        setPasswordsMatch(false);
        console.log("Password and confirm password should be same...")
    } else {
        // Handle form submission here
        signUp(formData).then((resp) => {

            console.log(resp);
            console.log("succesfully signUp.....")
            console.log("=========================================================================")

        }).catch((err) => {

            console.log(err);
            console.log("signup failed....")
            console.log("=========================================================================")


        })

    }
    console.log("**********************************************************")

    //console.log(formData)

    return (
        <div className="bg-color" style={{ marginTop: 10 }}>
            <form onSubmit={handleSubmit}>
                {error && <Alert variant="danger">{error}</Alert>}

                <div className='sign-up-wrapper'>
                    <h2 text-3xl font-semibold text-gray-900>Create an account</h2>
                    <p>Already have an account?</p>
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
                    </div>

                    <button type="submit" className='btn-sign-in' >Submit</button>
                    <Animation />


                </div>
            </form>
        </div >
    )
}
export default Signup;


