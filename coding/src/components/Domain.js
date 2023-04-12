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
                <div className="price-list-free text-center">
                    <img className='image-logo' src="./images/price-logo.png" alt="" height="100px" width="100px" />
                    <p className='mt-4 free-heading '>Free Web Hosting</p>
                    <p className='free-package'>Perfect Package for Personal Website</p>
                    <button className='mt-4 btn-for-free'>Try for Free</button>

                    <div className='free-list'>
                        <img className='checked-image' src="./images/checked.png" alt="" />
                        <p className='plans'>Our Domain</p>
                    </div>
                    <div className='free-list'>
                        <img className='checked-image' src="./images/checked.png" alt="" />
                        <p className='plans'>Our Domain</p>
                    </div>
                    <div className='free-list'>
                        <img className='checked-image' src="./images/checked.png" alt="" />
                        <p className='plans'>Our Domain</p>
                    </div>
                    <div className='free-list'>
                        <img className='checked-image' src="./images/checked.png" alt="" />
                        <p className='plans'>Our Domain</p>
                    </div>
                    <div className='free-list'>
                        <img className='checked-image' src="./images/remove.png" alt="" />
                        <p className='plans'>Our Domain</p>
                    </div>
                    <div className='free-list'>
                        <img className='checked-image' src="./images/remove.png" alt="" />
                        <p className='plans'>Our Domain</p>
                    </div>

                </div>

                {/* premium-list ===============================================================*/}

                <div className="price-list-premimum text-center">
                    <img className='image-logo' src="./images/price-logo.png" alt="" height="100px" width="100px" />
                    <p className='mt-4 premium-heading'>Premium Web Hosting</p>
                    <div className='d-flex rupppe-icon'>
                        <img className='ruppee-img' src="./images/ruppe.png" alt="" height="32px" width="32px" /><p className='ruppee'>149</p>

                    </div>

                    <p className='text-primary'>Per Month</p>
                    <button className='mt-4 btn-for-premium'>Buy Premium</button>
                    <div className='premium-list'>
                        <img className='checked-image' src="./images/checked.png" alt="" />
                        <p className='plans'>Our Domain</p>
                    </div>
                    <div className='premium-list'>
                        <img className='checked-image' src="./images/checked.png" alt="" />
                        <p className='plans'>Our Domain</p>
                    </div>
                    <div className='premium-list'>
                        <img className='checked-image' src="./images/checked.png" alt="" />
                        <p className='plans'>Our Domain</p>
                    </div>
                    <div className='premium-list'>
                        <img className='checked-image' src="./images/checked.png" alt="" />
                        <p className='plans'>Our Domain</p>
                    </div>
                    <div className='premium-list'>
                        <img className='checked-image' src="./images/checked.png" alt="" />
                        <p className='plans'>Our Domain</p>
                    </div>
                    <div className='premium-list'>
                        <img className='checked-image' src="./images/checked.png" alt="" />
                        <p className='plans'>Our Domain</p>
                    </div>
                </div>
            </div>

        </>
    )

}
{/* <img src="./images/price-logo.png" alt="" height="100px" width="100px" /> */ }


{/* <img src="./images/checked.png" alt="" height="32" widt="32" /> */ }

export default Domain;



