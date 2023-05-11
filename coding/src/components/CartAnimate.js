import React from 'react';
import "./Animation.css"
import Lottie from 'react-lottie';
import animationData from './lotties/cart.json';


export default function CartAnimate() {
    const defaultOptions = {
        loop: true,
        autoplay: true,
        animationData: animationData,
        rendererSettings: {
            preserveAspectRatio: "xMidYMid slice"
        }
    };

    return (
        <div style={{ display: 'flex', marginLeft: '100px', marginBottom: '1800px' }}>
            <Lottie
                options={defaultOptions}
                height={400}
                width={400}
            />
        </div>
    );
}

