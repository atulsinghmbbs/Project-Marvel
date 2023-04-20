import React, { useEffect } from 'react'
import { useSelector } from 'react-redux'
import { useDispatch } from 'react-redux'
import { removeFromCart } from './redux/action'

const Checkout = () => {

    const select = useSelector((state) => state.cartData)
    console.log("selector checkout-page", select.cartData);

    const dispatch = useDispatch()


    function removeDomain(domainName) {
        dispatch(removeFromCart(domainName))
    }


    useEffect(() => {
        window.scroll(0, 0)
    })

    return (
        <div style={{ marginTop: 100 }}>
            <h1>This is checkout page</h1>
            <div>
                {Array.isArray(select.cartData) && select.cartData.map((domain, i) => (
                    <div key={i}>
                        <h2>{domain.domainName}</h2>
                        <h5>{domain.domainPrice}</h5>
                        <button onClick={() => removeDomain(domain.domainName)}>Remove</button>
                    </div>
                ))}
            </div>
        </div>
    )
}

export default Checkout