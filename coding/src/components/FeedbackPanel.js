import React, { useState } from 'react';
import { FaStar } from "react-icons/fa";
import "./FeedbackPanel.css";

const Star = ({ selected = false, onSelect }) => (
    <FaStar color={selected ? "yellow" : "lightgray"} onClick={onSelect} size={30} />
);

const FeedbackPanel = () => {
    const [feedback, setFeedback] = useState('');
    const [rating, setRating] = useState(0);
    const [hoverRating, setHoverRating] = useState(0);

    const handleSelect = (ratingValue) => {
        setRating(ratingValue);
    };

    const handleSubmit = async (event) => {
        event.preventDefault();

        try {
            const response = await fetch('http://localhost:8888/feedbacks/', {
                method: 'POST',
                body: JSON.stringify({
                    rating: rating,
                    comment: feedback
                }),
                headers: {
                    'Content-type': 'application/json; charset=UTF-8',
                    'Authorization': 'Bearer eyJhbGciOiJIUzM4NCJ9.eyJpc3MiOiJoYWFybWsiLCJzdWIiOiJKV1QgVG9rZW4iLCJpYXQiOjE2ODI0MDkxODUsImV4cCI6MTY4MjQ5NTU4NSwiYXV0aG9yaXRpZXMiOiJST0xFX1VTRVIiLCJ1c2VybmFtZSI6IkhJUEwzIn0.OsdkcIXBro9Ug-SmqeylJStlnudSjp0DipO0bXlo6EyeFkmBi2EmsQIudx2WCFM-'
                }
            });

            const json = await response.json();
            console.log(json);
        } catch (error) {
            console.error(error);
        }

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




