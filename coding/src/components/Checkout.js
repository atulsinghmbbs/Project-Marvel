import React, { useEffect, useState } from 'react'
import { useSelector } from 'react-redux'
import { useDispatch } from 'react-redux'
import { removeFromCart } from './redux/action'
import "./Checkout.css"
import { bakendBaseUrl, bakendHeader } from './BaseUrl'
import { NavLink, useNavigate } from 'react-router-dom'



const Checkout = () => {

    const navigate = useNavigate()

    const [modalIsOpen, setModalIsOpen] = useState(false);

    const select = useSelector((state) => state.cartData)
    console.log("selector checkout-page", select.cartData);

    const [updatedReduxData, setUpdatedReduxData] = useState([])
    const [loading, setLoading] = useState(true)
    const [totalPay, setTotalPay] = useState('')
    const [selectedYear, setSelectedYear] = useState("1")
    const [razorpayOrderId, setRazorpayOrderId] = useState("")



    const dispatch = useDispatch()

    function removeDomain(productId) {
        console.log("id", productId)
        dispatch(removeFromCart(productId))
    }
    // const total = Math.round(select.cartData.reduce((acc, domain) => acc + domain.domainPrice, 0));


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


    //total payment and years wali api

    const handleSelectChange = (event) => {
        setSelectedYear(event.target.value);
    }
    // console.log("selected year", selectedYear);

    // const getTotalPayment = async () => {
    //     try {
    //         const response = await fetch(`${bakendBaseUrl}/orders/addOrder`, {
    //             method: 'POST',
    //             body: JSON.stringify(
    //                 {
    //                     "products": [
    //                         {
    //                             "qty": 1,
    //                             "productId": "33",

    //                         }
    //                     ]
    //                 }
    //             ),
    //             headers: bakendHeader,
    //         })
    //         const payment = await response.json()
    //         setTotalPay(payment)
    //         setRazorpayOrderId(payment)
    //         setLoading(false)
    //     } catch (error) {
    //         console.log(error)
    //     }
    // }
    // console.log("orderTotal", totalPay);
    // console.log('RazorPayId', razorpayOrderId.razorpayOrderId) //this id send to razor payment

    // function sendOrderId() {
    //     navigate("/razorpay", { state: { razorpayOrderId: razorpayOrderId.razorpayOrderId, totalPay: totalPay } })

    // }

    useEffect(() => {
        getCart()
        // getTotalPayment()
        window.scroll(0, 0)
    }, [selectedYear])


    try {
        return (
            <div style={{ marginTop: 0 }} >
                <h1 className='mycart-heading' style={{ fontSize: 100 }}>Your Cart</h1>
                <div>
                    {!loading ? (updatedReduxData.items.map((item, i) => (

                        <div className='main-container' key={i}>
                            <h2>{item.product.uniqueName}</h2>
                            <h5 className='price'>{item.price}</h5>
                            <select value={selectedYear} onChange={handleSelectChange}>
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
                            <button onClick={() => removeDomain(item.id)}>Remove</button>
                        </div>
                    ))) :

                        (<p>You don't have any items in your cart</p>)
                    }

                    {!loading ? (<div className='order-summary-wrapper'>
                        <p>Order Summary</p>
                        <hr />
                        <h4>Subtotal:{updatedReduxData.totalSum}</h4>
                        {/* <button onClick={sendOrderId}>Continue To Pay</button> */}
                    </div>)
                        :
                        (<p>Domain is loading</p>)
                    }
                    {/* <div className='order-summary-wrapper'>
                    <p>Order Summary</p>
                    <hr />
                    <h4>Subtotal:{totalPay}</h4>
                    <NavLink to='/razorpay'>
                        <button>Continue To Pay</button>
                    </NavLink> */}
                    {/* </div> */}
                </div>
                <NavLink to='/CheckoutForm'>
                    <button>add address</button>
                </NavLink>
            </div >

        )
    } catch (error) {
        <div>
            <h1 className='error text-center'>Please Refresh The Page</h1>
            <button className='refresh-btn' onClick={() => window.location.reload()}>Refresh</button>
        </div>

    }



    //<div className='order-summary-wrapper'>
    //     <p>Order Summary</p>
    //     <hr />
    //     <h4>Subtotal:  {total}</h4>
    //     <button onClick={()=>setbtnpop(true)}>Continue To Pay</button>
    //     {/* <NavLink to="/RazPay"><button>Continue To Pay</button></NavLink> */}
    //     <RazPay trigger={btnpopup}></RazPay>
    // </div>

}

export default Checkout;
