import React from 'react'
import { useState, useEffect } from 'react'

const DomainAvalibility = ({ inputData }) => {

    console.log("kjhkj", inputData)



    const [domainData, setDomainData] = useState([])


    const getDomainData = () => {
        const getData = fetch("http://localhost:8888/domains/search?searchTerm=hi")
            .then((res) => res.json())
            .then((data) => console.log(setDomainData(data)))
    }

    useEffect(() => {
        getDomainData()
        console.log("Your api data", domainData)
    }, [])


    return (
        <div style={{ marginTop: 100 }}>
            <h1>hello:{inputData}</h1>
            {/* {domainData.map((domain) => (
                <h2>{domain}</h2>
            ))} */}
        </div>
    )
}

export default DomainAvalibility