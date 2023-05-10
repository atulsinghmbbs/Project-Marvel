import React from 'react';
import "./Animation.css"
import Lottie from 'react-lottie';
import animationData from './lotties/login.json';


export default function Animatelogin() {
    const defaultOptions = {
        loop: true,
        autoplay: true,
        animationData: animationData,
        rendererSettings: {
            preserveAspectRatio: "xMidYMid slice"
        }
    };

    return (
        <div className='lottie-animation' style={{ display: 'flex', justifyContent: 'center', marginLeft: '-1400px', marginTop: '-400px' }}>
            <Lottie
                options={defaultOptions}
                height={400}
                width={400}
            />
        </div>
    );
}

