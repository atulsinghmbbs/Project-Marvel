import React, { useEffect, useState } from 'react'
import "./ActiveServices.css"
import { json } from 'react-router-dom'

const ActiveServices = () => {
    const [data, setData] = useState([])

    const baseUrl = "http://192.168.1.50:8888"
    const onLoad = (event) => {
        fetch(baseUrl + "/service/", {
            method: "GET",
            headers: {
                'Content-type': 'application/json; charset=UTF-8',
                'Authorization': 'Bearer eyJhbGciOiJIUzM4NCJ9.eyJpc3MiOiJoYWFybWsiLCJzdWIiOiJKV1QgVG9rZW4iLCJpYXQiOjE2ODMwMTMxNDEsImV4cCI6MTY4MzA5OTU0MSwiYXV0aG9yaXRpZXMiOiJST0xFX1VTRVIiLCJ1c2VybmFtZSI6IkhJUEw0In0.bqZp0X6KFPjWE4t2WrcM6efPSaOAycAEtv459cZHoMHnCtap392174PRdogDsnS6'
            }
        }).then((response) => response.json())
            .then((json) => { setData(json) })
    }

    useEffect(() => {
        onLoad();
    }, [])

    console.log(data)







    return (
        <div>
            {data && data.map((service) => (
                <div className='Active-Services'>
                    <div className="domain-service">
                        <div className="domain">
                            <div className="domain-name">
                                <label htmlFor='1'>Domain name : </label>
                                <input type="text" id="1" aria-readonly value={service.product.uniqueName} />
                            </div>
                            <div className="domain-status">
                                <label htmlFor='2'>Status : </label>
                                <input type="text" id='2' aria-readonly value={service.status}/>
                            </div>
                            <button type='submit'>Click For DNS</button>
                        </div>


                        <div className="service">
                            <div className="product-name">
                                <label htmlFor="3">Product Name : </label>
                                <input type="text" id='3' aria-readonly value={service.product.displayName}/>
                            </div>
                            <div className="product-status">
                                <label htmlFor="4">Status : </label>
                                <input type="text" id='4' aria-readonly value={service.status} />
                            </div>
                            <div className="created">
                                <label htmlFor="5">Created At : </label>
                                <input type="text" id='5' aria-readonly value={service.details.domain.createDate}/>
                            </div>
                            <div className="expire">
                                <label htmlFor="6">Valid Till : </label>
                                <input type="text" id='6' aria-readonly value={service.details.domain.expireDate}/>
                            </div>
                            <button type='submit'>See All Details</button>
                        </div>
                    </div>

                </div>

            ))}
        </div>
    )
}

export default ActiveServices
