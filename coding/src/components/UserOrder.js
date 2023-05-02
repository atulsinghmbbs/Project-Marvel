import React, { useEffect, useState } from 'react'
import "./UserOrder.css";


const UserOrder = () => {
    const [userDeatils, setUserDetails] = useState("")
    const baseUrl = "http://192.168.1.50:8888"
    const onLoad = (event) => {
        fetch(baseUrl + "/users/", {
            method: 'GET',
            headers: {
                'Content-type': 'application/json; charset=UTF-8',
                'Authorization': 'Bearer eyJhbGciOiJIUzM4NCJ9.eyJpc3MiOiJoYWFybWsiLCJzdWIiOiJKV1QgVG9rZW4iLCJpYXQiOjE2ODMwMTMxNDEsImV4cCI6MTY4MzA5OTU0MSwiYXV0aG9yaXRpZXMiOiJST0xFX1VTRVIiLCJ1c2VybmFtZSI6IkhJUEw0In0.bqZp0X6KFPjWE4t2WrcM6efPSaOAycAEtv459cZHoMHnCtap392174PRdogDsnS6'
            },
        }).then((response) => response.json())
            .then((json) => { setUserDetails(json.orders) });
    }

    useEffect(() => {
        onLoad()
    }, [])






    return (
        <div>
            {userDeatils && userDeatils.map((order) => (
                <div className="user-order">
                    <div className="order-details">
                        <div className="orderid">
                            <label htmlFor="1">
                                Order Id :
                            </label>
                            <input type="text" id='1' value={order.id} className='user-input' area-readOnly />
                        </div>
                        <div className="order-time">
                            <label htmlFor="2">
                                Order Time :
                            </label>
                            <input type="text" id='2' value={order.createdAt} className='user-input' area-readOnly />
                        </div>
                    </div>


                    <div className="payment">
                        <div className="payment-id">
                            <label htmlFor="3">Transaction Id : </label>
                            <input type="text" id="3" value={order.razorpayOrderId} className='user-input' area-readOnly />
                        </div>
                        <div className="payment-amount">
                            <label htmlFor="3">Total Amount : </label>
                            <input type="text" id="3" value={order.total} className='user-input' area-readOnly />
                        </div>
                    </div>



                    <div className="item-details">
                        <h5>Item Details</h5>
                        <h5>Payment Status :
                            <input type="text" value={order.status} className='user-input'/>
                        </h5>
                    </div>
                    {order.items && order.items.map((item) => (
                        <div key={item.id} className='item-class'>
                            <div className="itemid-name">
                                <div className="item-id">
                                    <label htmlFor="6">Item Id : </label>
                                    <input type="text" id='6' value={item.id} className='user-input'/>
                                </div>
                                <div className="item-name">
                                    <label htmlFor="7">Item Name : </label>
                                    <input type="text" id='7' value={item.product.displayName} className='user-input'/>
                                </div>

                            </div>
                            <div className="qty-price">
                                <div className="qty">
                                    <label htmlFor="8">Item Quantity : </label>
                                    <input type="text" id='8' value={item.qty} className='user-input'/>
                                </div>
                                <div className="price">
                                    <label htmlFor="9">Item Price : </label>
                                    <input type="text" id='9' value={item.price} className='user-input'/>
                                </div>
                            </div>
                            <div className="underline">
                            </div>
                        </div>

                    ))

                    }
                </div>

            ))}

        </div>
    )
}

export default UserOrder
