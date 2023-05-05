import React from 'react'
import { useState, useEffect } from 'react'
import { useLocation } from 'react-router-dom'
import "./DomainAvalibility.css"
import { addToCart } from './redux/action'
import { useDispatch } from 'react-redux'
import { bakendBaseUrl } from './BaseUrl'
import { bakendHeader } from './BaseUrl'
import { NavLink } from 'react-router-dom'
import Lottie from 'react-lottie';
import animationData from './lotties/loader.json';
import Loader from './Loader'


const DomainAvalibility = () => {

    const [domainResult, setDomainResult] = useState([])
    const [isLoading, setLoading] = useState(true)
    // const [newPrice, setNewPrice] = useState("")



    const [error, setError] = useState("")

    const [isLoadingError, setIsLoadingError] = useState("")




    const dispatch = useDispatch()

    const location = useLocation()
    console.log("location wala data ", location.state);

    const API_KEY = 'RXPtbysguCADwc7fsCTVkzKaq4rWRO0M';


    const getDomainData = async () => {
        try {
            const getData = await fetch(`${bakendBaseUrl}/domains/search?searchTerm=${location.state.inputData}`, {
                headers: bakendHeader
            })
                .then((res) => res.json())
                .then((data) => setDomainResult(data))
            setLoading(false)
        } catch (error) {
            console.log("new error", error)
            setIsLoadingError(false)
            setError(error)
        }
    }
    console.log("Your Result data", domainResult)



    useEffect(() => {
        window.scroll(0, 0)
        getDomainData()
    }, [location.state.inputData],)



    function resultTextt() {
        try {
            let resultText;
            if (domainResult.result.purchasable === true) {
                resultText = (
                    <div>
                        <p className='item text-center'>This is available</p>
                        <div className='available'>
                            <div className="price">
                                <p>{domainResult.result.domainName}</p>
                                <p>{domainResult.result.purchasePrice}</p>
                                {!domainResult.result.isPresentInCart ? (
                                    <div>
                                        <button onClick={() => {
                                            dispatch(addToCart({ domainName: domainResult.result.domainName, domainPrice: domainResult.result.purchasePrice }));
                                            domainResult.result.isPresentInCart = true
                                            setDomainResult({ ...domainResult, result: { ...domainResult.result } })
                                        }}
                                        >Buy Now</button>
                                    </div>

                                ) : (
                                    <NavLink to="/Checkout">
                                        <button className='continue-btn'>continue</button>
                                    </NavLink>
                                )
                                }
                            </div>
                        </div>
                    </div>
                );
            } else {
                resultText = (
                    <div>
                        <p className='not-available text-center'>This domain is not available Some domains are given below, you can select</p>
                        <p className='similar-domain text-center'>Some similar domains are available </p>
                    </div>
                );
            }
            return resultText;
        } catch (error) {
            console.log("why this error is coming", error)
        }
    }

    return (
        <div style={{ marginTop: 100 }}>
            <div className="result-box">

                <h1>
                    {!isLoading ? resultTextt() : ""}
                </h1>
            </div>

            <div className='suggestion-box'>

                {!isLoading ? (
                    domainResult.suggestions.map((item, i) => (
                        <div key={i}>
                            <div className='suggestion'>
                                <h3 className='suggestion-heading'>{item.domainName}</h3>
                                <p className='item-price'>{item.purchasePrice}</p>

                                {!item.isPresentInCart ? (
                                    <i className="fa-solid fa-cart-plus icon" onClick={() => {
                                        console.log("items", item)
                                        dispatch(addToCart({ domainName: item.domainName, domainPrice: item.purchasePrice }));
                                        item.isPresentInCart = true
                                        setDomainResult({ ...domainResult, suggestions: [...domainResult.suggestions] })
                                    }}
                                    ></i>
                                ) : (
                                    <NavLink to="/Checkout">
                                        <button className='continue-btn'>continue</button>
                                    </NavLink>
                                )
                                }
                            </div>
                        </div>
                    ))
                ) : (
                    <Loader />
                )}
            </div>
        </div>
    )


}

export default DomainAvalibility;
