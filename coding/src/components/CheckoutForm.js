import React, { useState } from 'react'
import "./CheckoutForm.css"
import countrydata from "./Countrydata.json"



const CheckoutForm = () => {

    const [countryid, setCountryid] = useState('')
    const [state, setState] = useState([])
    const [stateid, setStateid] = useState('')

    const [firstName, setFirstName] = useState('')


    const handlecountry = (e) => {
        try {
            const getcountryId = e.target.value;
            setCountryid(getcountryId)
            console.log(getcountryId)
            const getStatedata = countrydata.find(country => country.country_id === getcountryId).states;
            setState(getStatedata)
        } catch (error) {
            alert("please enter a valid country")
        }

    }



    const handlestate = (e) => {
        const stateid = e.target.value;
        setStateid(stateid)
        console.log(stateid)
    }



    const handlesubmit = (e) => {
        e.preventDefault();
        console.log(firstName);
    }


    return (
        <div style={{ marginTop: 100 }}>
            <h1 className='text-center mb-4'>Checkout</h1>
            <div className="border mx-auto">
                <h2>Billing Information</h2>
                <p>We use this information to register products, secure your identity and calculate taxes and fees.</p>
                <p>All fields required unless otherwise stated.</p>

                <div className="drop-down">
                    <select className='form-states' name='country' onChange={(e) => handlecountry(e)}>
                        <option value="">Select Country</option>
                        {
                            countrydata.map((getcountry, index) => (
                                <option value={getcountry.country_id} key={index}>{getcountry.country_name}</option>
                            ))
                        }
                    </select>
                </div>

                <div className="name">
                    <form action="" onSubmit={handlesubmit}>
                        <input type="text" value={firstName} onChange={(e) => setFirstName(e.target.value)} placeholder='First Name' required />
                        <input type="text" placeholder='Last Name' />
                    </form>
                </div>

                <div className="mobile">
                    <form action="">
                        <div className="number-group">
                            <input type="text" placeholder="Enter your mobile number" />
                        </div>
                        {/* <div class="form-check">
                            <input type="checkbox" className="form-check-input" id="exampleCheck1" />
                            <label className="form-check-label" for="exampleCheck1">Notify me on whatsapp</label>
                        </div> */}
                    </form>
                </div>

                <div className="last-two">
                    <form action="">
                        <div className="last">
                            <input type="text" placeholder="Building / Society" />
                        </div>
                        <div className="last">
                            <input type="text" placeholder="Landmark / Street Name" />
                        </div>
                    </form>
                </div>




                <div className="pos-state">
                    <form action="" className='drop-down'>
                        <input type="text" id="postal" placeholder='Enter your postal code' />
                    </form>

                    <div className="drop-down">
                        <select class="form-states" name='states' onChange={(e) => handlestate(e)}>
                            <option>States</option>
                            {
                                state.map((getstate, index) => (
                                    <option value={getstate.state_id} key={index}>{getstate.state_name}</option>
                                ))
                            }
                        </select>
                    </div>
                </div>




                <div className="last-three">
                    <form action="">
                        <div className="last">
                            <input type="text" placeholder="City" />
                        </div>
                        <div className="last">
                            <input type="text" placeholder="Organization" />
                        </div>
                        <div className="last">
                            <input type="text" placeholder="GSTIN" />
                        </div>
                    </form>
                </div>
                <p className='tax'>For correct tax calculation</p>
                <button className='btn'>Save</button>
            </div>

        </div>
    )
}

export default CheckoutForm
