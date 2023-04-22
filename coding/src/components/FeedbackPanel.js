// import React, { useState } from 'react';
// import "./FeedbackPanel.css"


// function FeedbackPanel() {
//     const [feedback, setFeedback] = useState('');
//     const [rating, setRating] = useState('')

//     const handleSubmit = (event) => {
//         event.preventDefault();

//         fetch('http://192.168.1.61:8888/feedbacks/', {
//             method: 'POST',
//             body: JSON.stringify({
//                 rating:5,
//                 comment:feedback
//             }),
//             headers: {
//                 'Content-type': 'application/json; charset=UTF-8',
//                 'Authorization': 'Bearer eyJhbGciOiJIUzM4NCJ9.eyJpc3MiOiJoYWFybWsiLCJzdWIiOiJKV1QgVG9rZW4iLCJpYXQiOjE2ODIwNzA5MzksImV4cCI6MTY4MjA3ODkzOSwiYXV0aG9yaXRpZXMiOiJST0xFX1VTRVIiLCJ1c2VybmFtZSI6IkhJUEwxIn0.NeKI6B8ZPynDR1psBS6m11wFa_4peRR9mF6V_SgGrQaDuoSdFmSLcpw7JF3mbaGH'
//             },


//         }).then((response)=>response.json())
//         .then((json)=>console.log(json))

//         console.log(feedback)
//         console.log(rating)

//     };

//     return (
//         <div className='feedbackpanel'>
//             <form onSubmit={handleSubmit}>
//                 <input type="text" required value={feedback} onChange={(event) => setFeedback(event.target.value)} />
//                 <button type="submit">Submit</button>
//             </form>
//         </div>

//     );
// }

// export default FeedbackPanel;

import React, { useState } from 'react';
import "./FeedbackPanel.css"
import { FaStar } from "react-icons/fa";

const Star = ({ selected = false, onSelect }) => (
    <FaStar color={selected ? "yellow" : "lightgray"} onClick={onSelect} size={30}/>
);

const FeedbackPanel = () => {
    const [feedback, setFeedback] = useState('');
    const [rating, setRating] = useState(0);
    const [hoverRating, setHoverRating] = useState(0);

    const handleSelect = (ratingValue) => {
        setRating(ratingValue);
    };

    const handleSubmit = (event) => {
        event.preventDefault();

        fetch('http://192.168.1.61:8888/feedbacks/', {
            method: 'POST',
            body: JSON.stringify({
                rating: rating,
                comment: feedback
            }),
            headers: {
                'Content-type': 'application/json; charset=UTF-8',
                'Authorization': 'Bearer eyJhbGciOiJIUzM4NCJ9.eyJpc3MiOiJoYWFybWsiLCJzdWIiOiJKV1QgVG9rZW4iLCJpYXQiOjE2ODIwNzk3MzcsImV4cCI6MTY4MjA4NzczNywiYXV0aG9yaXRpZXMiOiJST0xFX1VTRVIiLCJ1c2VybmFtZSI6IkhJUEwxIn0.lhgkZNWKNIxrHWo_EDU3CBRj13qAXqBcvAsVZY4uQnUJUtv5RHvMhUwUoIL3Q8CT'
            }
        }).then((response) => response.json())
            .then((json) => console.log(json));

        console.log(feedback);
        console.log(rating);
    };







    return (
        <div className="feedback">
            <div>
                <form onSubmit={handleSubmit} className='feedback-form'>
                    <p>Rate Us</p>
                    <div className='star'>
                        {[...Array(5)].map((_, index) => {
                            const ratingValue = index + 1;
                            return (
                                <Star
                                    key={ratingValue}
                                    selected={ratingValue <= (hoverRating || rating)}
                                    onSelect={() => handleSelect(ratingValue)}
                                    onMouseEnter={() => setHoverRating(ratingValue)}
                                    onMouseLeave={() => setHoverRating(0)}
                                />
                            );
                        })}
                    </div>
                    <div className='input-field'>
                        <input type="text" required value={feedback} onChange={(event) => setFeedback(event.target.value)} />
                        <button type="submit">Submit</button>
                    </div>
                </form>
            </div>
        </div>
    );
};

export default FeedbackPanel;

