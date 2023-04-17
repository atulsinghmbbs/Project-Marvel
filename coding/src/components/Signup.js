import React from 'react'
import "./Signup.css"
import { Alert } from "react-bootstrap";
import { useState } from 'react';

function Signup() {


    const [formData, setFormData] = useState({
        firstName: "",
        lastName: "",
        email: "",
        password: "",
        confirmPassword: "",
    });
    const [error, setError] = useState(null);

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

    return (
        <div style={{ marginTop: 80 }}>
            <form onSubmit={handleSubmit}>
                {error && <Alert variant="danger">{error}</Alert>}
                <div className='sign-up-wrapper'>
                    <h1>Project-Marvel</h1>
                    <img src="" alt="" />
                    <div>
                        <label className="signup-label">
                            First Name
                            <br />
                            <input type="text" name="firstName" value={formData.firstName} className="signup-input" placeholder='Enter First Name' onChange={handleInputChange} />
                        </label>
                    </div>
                    <div>
                        <label className="signup-label">
                            Last Name
                            <br />
                            <input type="text" name="lastName" value={formData.lastName} className="signup-input" placeholder='Enter Last Name' onChange={handleInputChange} />
                        </label>
                    </div>
                    <div>
                        <label className="signup-label">
                            Email
                            <br />
                            <input type="email" name="email" value={formData.email} className="signup-input" placeholder='Enter Email' onChange={handleInputChange} />
                        </label>
                    </div>
                    <div>
                        <label className="signup-label">
                            Password
                            <br />
                            <input type="password" name="password" value={formData.password} className="signup-input" placeholder='Enter password' onChange={handleInputChange} />
                        </label>
                    </div>
                    <div>
                        <label className="signup-label">
                            Confirm Password
                            <br />
                            <input type="password" name="confirmPassword" placeholder='Confirm Password' value={formData.confirmPassword} className="signup-input" onChange={handleInputChange} />
                        </label>
                    </div>
                    <button type="submit" className='btn-sign-in' >Submit</button>
                </div>
            </form>

        </div>
    )
}
export default Signup;

