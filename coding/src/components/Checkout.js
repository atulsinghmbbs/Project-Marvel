import React, { useEffect, useState } from 'react'
import { useSelector } from 'react-redux'
import { useDispatch } from 'react-redux'
import { removeFromCart } from './redux/action'
import "./Checkout.css"
import { bakendBaseUrl, bakendHeader } from './BaseUrl'



const Checkout = () => {

    const [dom, setDom] = useState("")

    const select = useSelector((state) => state.cartData)
    console.log("selector checkout-page", select.cartData);


    const dispatch = useDispatch()

    function removeDomain(domainName) {
        dispatch(removeFromCart(domainName))
    }

    const total = Math.round(select.cartData.reduce((acc, domain) => acc + domain.domainPrice, 0));

    useEffect(() => {
        window.scroll(0, 0)
    })

    return (
        <div style={{ marginTop: 100 }} >
            <h1 className='mycart-heading'>Your Cart</h1>
            <div>
                {Array.isArray(select.cartData) && select.cartData.map((domain, i) => (
                    <div key={i}>
                        <div className="main-container">
                            <h2>{domain.domainName}</h2>
                            <h5 className='price'>{domain.domainPrice}</h5>
                            <button onClick={() => removeDomain(domain.domainName)}>Remove</button>
                        </div>
                    </div>
                ))}
                <div className='order-summary-wrapper'>
                    <p>Order Summary</p>
                    <hr />
                    <h4>Subtotal:  {total}</h4>
                    <button>Continue To Pay</button>
                </div>
            </div>
        </div>
    )
}

export default Checkout