import React from 'react'
import { useLocation } from 'react-router-dom'
import { useState } from 'react'
import { useEffect } from 'react'
import "./OrderSummary.css"
import { useNavigate } from 'react-router-dom'

const OrderSummary = () => {

    const location = useLocation()
    const [razorPayAllData, setRazorPayAllData] = useState("")
    const [loading, setLoading] = useState(true)

    const navigate = useNavigate('')


    useEffect(() => {
        try {
            setRazorPayAllData(location.state.razorPayId)
            setLoading(false)
        } catch (error) {
            <h1>Something went wrong</h1>
        }
    }, [])

    console.log("razorpay whole data ", razorPayAllData);

    function sendOrderIdToRazorPay() {
        navigate("/razorpay", { state: { razorPayAllData: razorPayAllData } })
    }


    return (
        <div style={{ marginTop: 100 }}>
            <h1 className='bill-address-heading'>Your Billing Address</h1>
            {!loading ? (
                <div>
                    <div className='order-sum'>
                        <h1 className=' mb-4' >Order Summary</h1>
                        <hr />
                        <h2>Sub Total:{razorPayAllData.subTotal}</h2>
                        <h2>Tax: {razorPayAllData.tax}</h2>
                        <h2>Total:{razorPayAllData.total}</h2>
                        <div className='text-center'>

                            <button className='payment-btn' onClick={sendOrderIdToRazorPay}>Ready To Pay</button>
                        </div>
                    </div>

                    <div className='bill-details'>
                        <p className='billing-address-info'>Billing Information</p>
                        <p className='first-name'>{razorPayAllData.billingAddress.firstName}</p>
                        <div className='other-info'>
                            <p>{razorPayAllData.billingAddress.addressLine1}</p>,
                            <p>{razorPayAllData.billingAddress.addressLine2}</p>,
                            <p>{razorPayAllData.billingAddress.country}</p>,
                            <p>{razorPayAllData.billingAddress.state}</p>,
                            <p>{razorPayAllData.billingAddress.city}</p>,
                            <p>{razorPayAllData.billingAddress.lastName}</p>,
                            <p>{razorPayAllData.billingAddress.phone}</p>,
                            <p>{razorPayAllData.billingAddress.postalCode}</p>,
                            <p>{razorPayAllData.billingAddress.gstin}</p>,
                        </div>
                    </div>
                </div>
            ) : (
                <h2>something went wrong</h2>
            )}
        </div>
    )
}

export default OrderSummary;    