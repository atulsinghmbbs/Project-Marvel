import React, { useEffect, useState } from 'react'
import { NavLink } from 'react-router-dom'
import { bakendBaseUrl, bakendHeader } from './BaseUrl'
import "./ActiveServices.css"
import { useNavigate } from 'react-router-dom'
import Lottie from 'react-lottie';
import animationData from './lotties/loader.json';
import Loader from './Loader'
import axios from 'axios'

const ActiveServices = () => {
    const [loading, setLoading] = useState(true)
    const [data, setData] = useState([])
    const navigate = useNavigate()

    const onLoad = () => {
        fetch(bakendBaseUrl + "/services/", {
            method: "GET",
            headers: bakendHeader
        }).then((response) => response.json())
            .then((json) => {
                if (Array.isArray(json)) {
                    setData(json);
                    setLoading(false);
                } else {
                    throw new Error("Invalid data format");
                }

            })
            .catch((error) => {
                window.alert("Data is not present in active services")
            })
    }

    useEffect(() => {
        onLoad();
    }, [])



    return (
        <div className='active-ser-main'>
            {!loading ? (data && data.map((service) => (
                <div className='Active-Services'>
                    <div className="domain-service">
                        <div className="active-domain">
                            <div className="domain-name">
                                <label htmlFor='1'>Domain name : </label>
                                <input type="text" id="1" aria-readonly value={service.product.uniqueName} />
                            </div>
                            <div className="domain-status">
                                <label htmlFor='2'>Status : </label>
                                <input type="text" id='2' aria-readonly value={service.status} />
                            </div>

                            <button type='submit' onClick={() => navigate("/DNSService", { state: { serviceId: service.id } })}>Click For DNS</button>
                        </div>


                        <div className="service">
                            <div className="product-name">
                                <label htmlFor="3">Product Name : </label>
                                <input type="text" id='3' aria-readonly value={service.product.displayName} />
                            </div>
                            <div className="product-status">
                                <label htmlFor="4">Status : </label>
                                <input type="text" id='4' aria-readonly value={service.status} />
                            </div>

                            {(service.details !== null) ? (
                                <div className="created">
                                    <label htmlFor="5">Created At : </label>
                                    <input type="text" id='5' aria-readonly value={service.details.domain.createDate} />
                                </div>
                            ) : (
                                <div>
                                    <p>details is not avaliable</p>
                                </div>
                            )}

                            {(service.details !== null) ? (
                                <div className="expire">
                                    <label htmlFor="6">Valid Till : </label>
                                    <input type="text" id='6' aria-readonly value={service.details.domain.expireDate} />
                                </div>
                            ) : (
                                <div>
                                    <p>details is not avaliable</p>
                                </div>
                            )}

                            <button type='submit'>See All Details</button>
                        </div>
                    </div>

                </div>

            ))) : (
                <Loader />
            )}
        </div>
    )
}

export default ActiveServices
