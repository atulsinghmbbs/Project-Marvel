import React, { useState } from 'react'
import "./CheckoutForm.css"
import countrydata from "./../Countrydata.json"



const CheckoutForm = () => {

    const [countryid, setCountryid] = useState('')
    const [state, setState] = useState([])
    const [stateid, setStateid] = useState('')

    //for json
    const [countryName, setCountryName] = useState("")
    const [firstName, setFirstName] = useState("")
    const [lastName, setLastName] = useState("")
    const [mobileNo, setMobileNo] = useState("")
    const [building, setBuilding] = useState("")
    const [landmark, setLandmark] = useState("")
    const [postal, setPostalCode] = useState("")
    const [stateName, setStateName] = useState("")
    const [city, setCity] = useState("")
    const [organization, setOragnization] = useState("")
    const [gstin, setgstIn] = useState("")




    //post the data too the database
    function handleSubmit(e) {
        e.preventDefault();
        fetch('http://192.168.1.43:8888/address/', {
            method: 'POST',
            body: JSON.stringify({
                firstName: firstName,
                lastName: lastName,
                addressLine1: building,
                addressLine2: landmark,
                city: city,
                state: stateName,
                phone: mobileNo,
                fax: "string",
                email: "string",
                organization: organization,
                gstin: gstin,
                postalCode: postal,
                country: {
                    name: countryName
                }

            }),
            headers: {
                'Content-type': 'application/json; charset=UTF-8',
            },
        })
        .then((response) => response.json())
        .then((json) => console.log(json));
    }





    //get the countryid states and country name
    const handlecountry = (e) => {
        try {
            const getcountryId = e.target.value;
            setCountryid(getcountryId)

            const getStatedata = countrydata.find(country => country.country_id === getcountryId).states;
            setState(getStatedata)

            const getCountryName = countrydata.find(country => country.country_id === getcountryId).country_name
            setCountryName(getCountryName);

        } catch (error) {
            alert("please enter a valid country")
        }

    }



    //find state id and set state name
    const handlestate = (e) => {
        const getStateid = e.target.value;
        setStateid(getStateid)
        const selectedState = state.find(s => s.state_id === getStateid);
        setStateName(selectedState.state_name);
    }



    return (
        <div>
            <h1>Checkout</h1>
            <div className="border">
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
                    <form action="">
                        <input type="text" value={firstName} onChange={(e) => setFirstName(e.target.value)} placeholder='First Name' required/>
                        <input type="text" value={lastName} onChange={(e) => setLastName(e.target.value)} placeholder='Last Name' />
                    </form>
                </div>

                <div className="mobile">
                    <form action="">
                        <div className="number-group">
                            <input type="text" placeholder="Enter your mobile number" value={mobileNo} onChange={(e) => setMobileNo(e.target.value)} />
                        </div>
                        <div class="form-check">
                            <input type="checkbox" className="form-check-input" id="exampleCheck1" />
                            <label className="form-check-label" for="exampleCheck1">Notify me on mail</label>
                        </div>
                    </form>
                </div>



                <div className="last-two">
                    <form action="">
                        <div className="last">
                            <input type="text" value={building} onChange={(e) => setBuilding(e.target.value)} placeholder="Building / Society" />
                        </div>
                        <div className="last">
                            <input type="text" value={landmark} onChange={(e) => setLandmark(e.target.value)} placeholder="Landmark / Street Name" />
                        </div>
                    </form>
                </div>




                <div className="pos-state">
                    <form action="" className='drop-down'>
                        <input type="text" value={postal} onChange={(e) => setPostalCode(e.target.value)} placeholder='Enter your postal code' />
                    </form>

                    <div className="drop-down">
                        <select class="form-states" onChange={(e) => handlestate(e)}>
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
                            <input type="text" value={city} onChange={(e) => setCity(e.target.value)} placeholder="City" />
                        </div>
                        <div className="last">
                            <input type="text" value={organization} onChange={(e) => setOragnization(e.target.value)} placeholder="Organization" />
                        </div>
                        <div className="last">
                            <input type="text" value={gstin} onChange={(e) => setgstIn(e.target.value)} placeholder="GSTIN" />
                        </div>
                    </form>
                </div>
                <p className='tax'>For correct tax calculation</p>
                <button type='submit' onClick={handleSubmit} className='btn'>save</button>
            </div>

        </div>
    )
}

export default CheckoutForm
