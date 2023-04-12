
import React from 'react'
import { useState } from 'react';

import "./Signup.css"

import { signUp } from '../services/userService';


function Signup() {
    const [formData, setFormData] = useState({
        firstName: '',
        lastName: '',
        email: '',
        password: '',
        confirmPassword: ''
    });

    const [passwordsMatch, setPasswordsMatch] = useState(true);

    const handleChange = (event) => {
        const { name, value } = event.target;
        setFormData({ ...formData, [name]: value });
    }

    const handleSubmit = (event) => {
        event.preventDefault();

        console.log("data", event)

        //console.log("datafjskf", event)

        console.log("==============================================================")
      

        if (formData.password !== formData.confirmPassword) {
            setPasswordsMatch(false);
            console.log("Password and confirm password should be same...")
        } else {
            // Handle form submission here
            signUp(formData).then((resp)=>{

                console.log(resp);
                console.log("succesfully signUp.....")
                console.log("=========================================================================")

            }).catch((err)=>{

                console.log(err);
                console.log("signup failed....")
                console.log("=========================================================================")


            })
            
        }
        console.log("**********************************************************")

        //console.log(formData)
        
    }

    return (
        <form className="form-wrapper container mt-5" onSubmit={handleSubmit}>
            <div className="row justify-content-center">
                <div className="col-md-12">
                    <div className="form-group">
                        <label htmlFor="firstName">First Name</label>
                        <input type="text" className="form-control" name="firstName" value={formData.firstName} onChange={handleChange} />
                    </div>
                    <div className="form-group">
                        <label htmlFor="lastName">Last Name</label>
                        <input type="text" className="form-control" name="lastName" value={formData.lastName} onChange={handleChange} />
                    </div>
                    <div className="form-group">
                        <label htmlFor="email">Email Address</label>
                        <input type="email" className="form-control" name="email" value={formData.email} onChange={handleChange} />
                    </div>
                    <div className="form-group">
                        <label htmlFor="password">Password</label>
                        <input type="password" className="form-control" name="password" value={formData.password} onChange={handleChange} />
                    </div>
                    <div className="form-group">
                        <label htmlFor="confirmPassword">Confirm Password</label>
                        <input type="password" className="form-control" name="confirmPassword" value={formData.confirmPassword} onChange={handleChange} />
                        {!passwordsMatch && <small className="text-danger">Passwords do not match</small>}
                    </div>
                    <br />
                    <button type="submit" className="btn btn-primary col-md-12">Submit</button>
                </div>
            </div>
        </form>
    );
}

export default Signup;

