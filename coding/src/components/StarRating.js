import React, { useState } from "react";
import { FaStar } from "react-icons/fa";

const Star = ({ selected = false, onSelect }) => (
  <FaStar color={selected ? "yellow" : "lightgray"} onClick={onSelect} />
);

const StarRating = () => {
  const [rating, setRating] = useState(0);
  const [hoverRating, setHoverRating] = useState(0);

  const handleSelect = (rating) => {
    setRating(rating);
  };
  console.log("scda", rating)

  return (
    <div>
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
      <p>{rating} stars</p>
    </div>
  );
};

export default StarRating;
