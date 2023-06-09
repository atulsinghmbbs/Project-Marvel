import React, { useEffect, useState } from 'react'
import { useSelector } from 'react-redux'
import { useDispatch } from 'react-redux'
import { removeFromCart } from './redux/action'
import "./Checkout.css"
import { bakendBaseUrl, bakendHeader } from './BaseUrl'
import { NavLink } from 'react-router-dom'
import { Link } from 'react-router-dom'
import { updateItem } from './redux/action'
import Lottie from 'react-lottie';
import animationData from './lotties/cart.json';
import Loader from './CartAnimate'
import CartAnimate from './CartAnimate'




const Checkout = () => {


    const dispatch = useDispatch()

    const select = useSelector((state) => state.cartData)
    console.log("selector checkout-page", select.cartData);

    const [updatedReduxData, setUpdatedReduxData] = useState([])
    const [loading, setLoading] = useState(true)
    const [totalPay, setTotalPay] = useState('')
    const [razorpayOrderId, setRazorpayOrderId] = useState("")


    const [sendYearsToBackend, setSendYearsToBackend] = useState("")



    function removeDomain(productId) {
        console.log("id", productId)
        dispatch(removeFromCart(productId))
    }


    const getCart = async () => {
        try {

            const response = await fetch(`${bakendBaseUrl}/carts/`, {
                method: 'GET',
                headers: bakendHeader,
            })
            const cart = await response.json()
            setUpdatedReduxData(cart)
            setLoading(false)
        } catch (error) {
            console.log(error)
        }
    }
    console.log("updated redux store", updatedReduxData);
    console.log("years", updatedReduxData.items)

    const handleSelectChange = (event, id) => {
        dispatch(updateItem({ event: event, id: id }))
    }

    //         const getYearsUpdates = async () => {
    //             try {
    // 
    //                 const response = await fetch(`${bakendBaseUrl}/carts/change-item-qty?cartItemId=${id}&qty=${event.target.value}`, {
    //                     method: 'PUT',
    //                     headers: bakendHeader
    //                 })
    //                 const year = await response.json()
    //                 setSendYearsToBackend(year)
    //                 setLoading(false)
    //                 console.log("years", year);
    //             } catch (error) {
    //                 console.log(error)
    //                 getYearsUpdates()
    //             }
    //         }
    // 
    //     }

    useEffect(() => {
        getCart()
        window.scroll(0, 0)
    }, [select])



    return (
        <div style={{ marginTop: 90 }} >
            <p className='mycart-heading'>Your Cart</p>
            {!loading ? (updatedReduxData.items.map((item, i) => (
                <div className='checkout-main-container' key={i}>
                    <div className='unique-name-and-price'>
                        <h2>{item.product.uniqueName}</h2>
                        <h5 className='price-item'>{item.price}</h5>
                    </div>
                    <div className='years-and-remove-btn'>
                        <select value={item.qty} onChange={(e) => handleSelectChange(e, item.id)}>
                            <option value="1">year 1</option>
                            <option value="2">year 2</option>
                            <option value="3">year 3</option>
                            <option value="4">year 4</option>
                            <option value="5">year 5</option>
                            <option value="6">year 6</option>
                            <option value="7">year 7</option>
                            <option value="8">year 8</option>
                            <option value="9">year 9</option>
                            <option value="10">year 10</option>
                        </select>
                        <button className='remove-btn' onClick={() => removeDomain(item.id)}>Remove</button>
                    </div>
                </div>
            ))) :

                (<p></p>)
            }

            {!loading ? (
                <div className='order-summary-wrapper'>
                    <p>Order Summary</p>
                    <hr />
                    <h4>Subtotal:{updatedReduxData.subTotal}</h4>
                    <p className='text-center mt-3 text-primary '>Subtotal does not include applicable taxes.</p>
                    <p className='text-center mt-2 text-primary '>You are getting the best price we got!</p>
                    <Link to='/checkoutform'>
                        <button>Place Order</button>
                    </Link>
                </div>
            )
                :
                (<CartAnimate />)
            }
        </div>
    )

}

export default Checkout;
