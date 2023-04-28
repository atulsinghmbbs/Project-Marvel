import React from 'react'
import { useState, useEffect } from 'react'
import { useLocation } from 'react-router-dom'
import "./DomainAvalibility.css"
import { addToCart } from './redux/action'
import { useDispatch } from 'react-redux'
import { bakendBaseUrl } from './BaseUrl'
import { bakendHeader } from './BaseUrl'
import { error } from 'jquery'


const DomainAvalibility = () => {

    const [domainResult, setDomainResult] = useState([])
    const [isLoading, setLoading] = useState(true)
    const [price, setPrice] = useState("")
    const [newPrice, setNewPrice] = useState("")

    const [changeCartIcon, setChangeCartIcon] = useState(true)

    const [error, setError] = useState("")

    const [isLoadingError, setIsLoadingError] = useState("")



    const dispatch = useDispatch()

    const location = useLocation()
    console.log("location wala data ", location.state);

    const API_KEY = 'RXPtbysguCADwc7fsCTVkzKaq4rWRO0M';


    const getDomainData = async () => {
        try {
            const getData = await fetch(`${bakendBaseUrl}/domains/search?searchTerm=${location.state.inputData}`)
                .then((res) => res.json())
                .then((data) => setDomainResult(data))
            // .catch((err) => console.log("error", err))
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
    }, [location.state.inputData])

    useEffect(() => {
        if (domainResult.result) {
            setNewPrice(domainResult.result.purchasePrice)
        }
    }, [domainResult])

    console.log("new price", newPrice)



    useEffect(() => {
        fetch(`https://api.apilayer.com/exchangerates_data/convert?to=INR&from=USD&amount=${newPrice}`, {
            headers: {
                'apiKey': API_KEY
            }
        })
            .then((response) => response.json())
            .then((data) => console.log(setPrice(data)))

    }, [newPrice],)
    console.log("price", price)

    function resultText() {
        let resultText
        if (!isLoading) {
            if (domainResult.result && domainResult.result.purchasable === true) {
                resultText = (
                    <div className='available'>
                        <p className='item'>This is available</p>
                        <div className="price">
                            <i className="fa-sharp fa-solid fa-dollar-sign"></i><p>{price.result}</p>
                        </div>
                        <button>Buy Now</button>
                    </div>
                );
            } else {
                resultText = (
                    <p className='not-available'>This domain is not available <br /> Some domains are given below, you can select</p>
                );
            }
        } else {
            return resultText;
        }
    }

    function changeIcon() {
        setChangeCartIcon(false)
    }



    return (
        <div style={{ marginTop: 100 }}>
            <div className="result-box">
                <h1>
                    {!isLoading ? resultText() : ""}
                </h1>
            </div>

            <div className='suggestion-box'>

                {!isLoading ? (
                    domainResult.suggestions.map((item, i) => (
                        <div key={i}>
                            <div className='suggestion'>
                                <h3 className='suggestion-heading'>{item.domainName}</h3>
                                <p className='item-price'>{item.purchasePrice}</p>

                                {changeCartIcon ? (

                                    <i className="fa-solid fa-cart-plus icon" onClick={() => {
                                        dispatch(addToCart({ domainName: item.domainName, domainPrice: item.purchasePrice }));
                                        changeIcon()
                                    }}
                                    ></i>
                                ) : (<button>Add To Cart </button>)}
                            </div>
                        </div>
                    ))
                ) : (
                    <p className='no-suggestion text-center'>We Are Searching Please Wait..</p>
                )}
            </div>
        </div>
    )


}

<<<<<<< HEAD
export default DomainAvalibility;
=======
export default DomainAvalibility
>>>>>>> 7e8e0be1d121bc2b636bd61b1aabe4dd007bf2de
