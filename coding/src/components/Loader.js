import React from 'react';
import "./Animation.css"
import Lottie from 'react-lottie';
import animationData from './lotties/loader.json';

export default function Loader() {
    const defaultOptions = {
        loop: true,
        autoplay: true,
        animationData: animationData,
        rendererSettings: {
            preserveAspectRatio: "xMidYMid slice"
        }
    };

    return (
        <div style={{ display: 'flex', justifyContent: 'center' }}>
            <Lottie
                options={defaultOptions}
                height={400}
                width={400}
            />
        </div>
    );
}