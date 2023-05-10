import React from 'react'
import { useEffect } from 'react'
import "./Domain.css"


const Domain = () => {

    useEffect(() => {
        window.scroll(0, 0)
    }, [])

    return (
        <>
            <div className="pricing-wrapper">
                {/* free list */}
                <div className="pricing-wrapper-margin">
                    <div className="price-list-free text-center">

                        <p className='mt-4 free-heading '>Basic</p>
                        <p className='free-package'>Essential Features</p>

                        <div className='free-list'>
                            <img className='checked-image' src="./images/checked.png" alt="" />
                            <p className='plans'>100mb Storage</p>
                        </div>
                        <div className='free-list'>
                            <img className='checked-image' src="./images/checked.png" alt="" />
                            <p className='plans'>Free SSl Certificate</p>
                        </div>
                        <div className='free-list'>
                            <img className='checked-image' src="./images/checked.png" alt="" />
                            <p className='plans'>Free Private DNS</p>
                        </div>
                        <div className='free-list'>
                            <img className='checked-image' src="./images/checked.png" alt="" />
                            <p className='plans'>Limited Bandwith</p>
                        </div>
                        <div className='free-list'>
                            <img className='checked-image' src="./images/remove.png" alt="" />
                            <p className='plans'>Unlimited Traffic</p>
                        </div>
                        <div className='free-list'>
                            <img className='checked-image' src="./images/remove.png" alt="" />
                            <p className='plans'>Customiazble Wordpress</p>
                        </div>
                        <div className='move-free-btn'>
                            <button className='mt-4 btn-for-free'>Try for Free</button>
                        </div>
                    </div>


                    {/* premium-list ===============================================================*/}

                    <div className="price-list-premimum text-center">
                        <div className='premium-bg'><p className='premium-header'>Recommended by Experts</p></div>
                        <p className='mt-4 premium-heading'>Premium</p>
                        <p className='premium-package'>Advance Features</p>
                        <div className=' rupppe-icon'>
                            <p className='ruppee'>₹149</p>

                            <p className='text-primary'>Per Month</p>
                        </div>

                        <div className='premium-list'>
                            <img className='checked-image' src="./images/checked.png" alt="" />
                            <p className='plans'>Unlimited Storage</p>
                        </div>
                        <div className='premium-list'>
                            <img className='checked-image' src="./images/checked.png" alt="" />
                            <p className='plans'>Free SSD Storage</p>
                        </div>
                        <div className='premium-list'>
                            <img className='checked-image' src="./images/checked.png" alt="" />
                            <p className='plans'>Unlimited Emails</p>
                        </div>
                        <div className='premium-list'>
                            <img className='checked-image' src="./images/checked.png" alt="" />
                            <p className='plans'>Unlimited Bandwith</p>
                        </div>
                        <div className='premium-list'>
                            <img className='checked-image' src="./images/checked.png" alt="" />
                            <p className='plans'>Customiazable Wordpress</p>
                        </div>
                        <div className='premium-list'>
                            <img className='checked-image' src="./images/checked.png" alt="" />
                            <p className='plans'>Security &Reliability</p>
                        </div>
                        <div className='move-premium'>
                            <button className='mt-4 btn-for-premium'>Buy Premium</button>
                        </div>
                    </div>
                </div>
            </div>
        </>
    )

}
export default Domain;



