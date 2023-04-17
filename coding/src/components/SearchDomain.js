import "./SearchDomain.css"
import React from 'react'
import { useState } from "react"
import { useNavigate } from "react-router-dom"

const SearchDomain = () => {

    const [inputData, setInputData] = useState("")

    const navigate = useNavigate()

    // =======================================================================
    // const [domainData, setDomainData] = useState([])


    // const getDomainData = () => {
    //     const getData = fetch("http://localhost:8888/domains/search?searchTerm=hi")
    //         .then((res) => res.json())
    //         .then((data) => console.log(setDomainData(data)))
    // }

    // useEffect(() => {
    //     getDomainData()
    //     console.log("Your api data", domainData)
    // }, [])
    // ======================================================================

    console.log("this is InputData", inputData);

    function submitDomain() {
        navigate("./DomainAvalibility", { state: { inputData: inputData } })
    }


    return (
        <div className="search-domain-wrapper">
            <div className="domain-heading">
                <h1>Search Your domain here</h1>
            </div>

            <div className="domain-input-field">
                <input type="text" value={inputData} onChange={(e) => setInputData(e.target.value)} placeholder="Seach Your domain Here" />
            </div>
            <div className="domain-search-btn">
                <button onClick={submitDomain}>Search</button>
            </div>

        </div>
    )
}
export default SearchDomain

