import "./SearchDomain.css"
import React from 'react'
import { useState } from "react"
import { NavLink } from "react-router-dom"
// import { createContext } from "react"
// import { Provider } from "react"
import DomainAvalibility from "./DomainAvalibility"

const SearchDomain = () => {


    // const InputDataContext = createContext()


    const [inputData, setInputData] = useState("")

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


    function handleSubmit(e) {
        e.preventDefault()
        { <DomainAvalibility inputData={inputData} /> }
    }



    return (
        <div className="search-domain-wrapper">
            <div className="domain-heading">
                <h1>Search Your domain here</h1>
            </div>

            <div className="domain-input-field">
                <form action="" onSubmit={handleSubmit}>
                    <input type="text" value={inputData} onChange={(e) => setInputData(e.target.value)} placeholder="Seach Your domain Here" />
                </form>
            </div>
            <div className="domain-search-btn">
                <NavLink inputData={inputData} to="/DomainAvalibility">
                    <button>Search</button>
                </NavLink>
            </div>
            {/* <DomainAvalibility inputData={inputData}  */}

        </div>
    )
}
export default SearchDomain

