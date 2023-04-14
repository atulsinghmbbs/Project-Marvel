import "./SearchDomain.css"
import React from 'react'
import { useState, useEffect } from "react"

const SearchDomain = () => {


    const [domainData, setDomainData] = useState([])


    const getDomainData = () => {
        const fetch = fetch("").then((res) => res.json())
            .then((data) => console.log(setDomainData(data)))
    }

    useEffect(() => {
        getDomainData()
        console.log("Your api data", domainData)
    }, [])





    return (
        <div className="search-domain-wrapper">
            <div className="domain-heading">
                <h1>Search Your domain here</h1>
            </div>

            <div className="domain-input-field">
                <input type="text" placeholder="Seach Your domain Here" />
            </div>
            <div className="domain-search-btn">
                <button>Search</button>
            </div>

        </div>
    )
}

export default SearchDomain