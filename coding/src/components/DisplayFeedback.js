import React, { useEffect, useState } from 'react'
import "./DisplayFeedback.css"


const DisplayFeedback = () => {

    const [message, setMessage] = useState("")

    const url = "https://jsonplaceholder.typicode.com/comments";

    async function fetchData() {
        const data = fetch(url).then((response) => response.json())
            .then((getData) => console.log(setMessage(getData[0].body)))
            
    }

    useEffect(() => {
        fetchData()
    })



    return (
        <div className='display-feedback'>
            <marquee direction="left" value={message}>
                <div><img src="" alt="" />{message}</div>
            </marquee>
        </div>
    )
}

export default DisplayFeedback
