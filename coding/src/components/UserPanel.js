import React from 'react'
import "./UserPanel.css"
import { useEffect } from 'react'

const UserPanel = () => {


    const onLoad = () => {
        // event.preventDefault();
        const baseUrl="http://localhost:8888/users/"
        fetch(baseUrl, {
            method: 'GET',
            headers: {
                
                'Content-type': 'application/json; charset=UTF-8',
                'Authorization': 'Bearer eyJhbGciOiJIUzM4NCJ9.eyJpc3MiOiJoYWFybWsiLCJzdWIiOiJKV1QgVG9rZW4iLCJpYXQiOjE2ODI0MDkxODUsImV4cCI6MTY4MjQ5NTU4NSwiYXV0aG9yaXRpZXMiOiJST0xFX1VTRVIiLCJ1c2VybmFtZSI6IkhJUEwzIn0.OsdkcIXBro9Ug-SmqeylJStlnudSjp0DipO0bXlo6EyeFkmBi2EmsQIudx2WCFM-'
            },
        }).then((response) => response.json())
            .then((json) => console.log("ghfdgsu", json));
    }

    useEffect(() => {
        onLoad()
    }, [])



    return (
        <div className="user-main">
            <div className="user-nav">
                <div className='logo'>HAARMK</div>
                <div className='user-dashboard'>USER DASHBOARD</div>
                <div className='status'>Active</div>
            </div>



            <div className="user-home">
                <div className="user-menu">
                    <div className="upper-box">
                        <div className='home one'>Home</div>
                        <div className='transactions one'>Transactions</div>
                        <div className='order one'>Order</div>
                        <div className='reset-password one'>Reset Password</div>
                        <div className='subscirption one'>Logout</div>
                    </div>
                    <div className="lower-box">
                        <button>Upgrade</button>
                    </div>

                </div>




                <div className="card">


                    <div className="both-card">
                        <div className="card1 card-design">
                            <div className='profile'>Profile</div>
                            <div className='edit'>
                                <div className='pic'></div>
                                <div className='phone' aria-readonly>8585484</div>
                                <div className='email' aria-readonly>.com</div>
                                <button>Edit Profile</button>
                            </div>

                            <div className='box'>
                                <div className='order box-design'>Total Order</div>
                                <div className='domain box-design'>Active Domain</div>
                                <div className='transactions box-design'>Last Transaction</div>
                            </div>
                        </div>


                        <div className="card2 card-design">
                            <div className="latest-transactions">Latest Transactions</div>
                            <div className="table">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th scope="col">UserName</th>
                                            <th scope="col">First</th>
                                            <th scope="col">Last</th>
                                            <th scope="col">Handle</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <th scope="row">1</th>
                                            <td>Mark</td>
                                            <td>Otto</td>
                                            <td>@mdo</td>
                                        </tr>
                                        <tr>
                                            <th scope="row">2</th>
                                            <td>Jacob</td>
                                            <td>Thornton</td>
                                            <td>@fat</td>
                                        </tr>
                                        <tr>
                                            <th scope="row">3</th>
                                            <td>Jacob</td>
                                            <td>Thornton</td>
                                            <td>@fat</td>
                                        </tr>
                                        <tr>
                                            <th scope="row">4</th>
                                            <td>Jacob</td>
                                            <td>Thornton</td>
                                            <td>@fat</td>
                                        </tr>
                                        <tr>
                                            <th scope="row">5</th>
                                            <td>Jacob</td>
                                            <td>Thornton</td>
                                            <td>@fat</td>
                                        </tr>
                                        <tr>
                                            <th scope="row">6</th>
                                            <td>Jacob</td>
                                            <td>Thornton</td>
                                            <td>@fat</td>
                                        </tr>
                                        <tr>
                                            <th scope="row">7</th>
                                            <td>Jacob</td>
                                            <td>Thornton</td>
                                            <td>@fat</td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                    <div className="terms">
                        <div className="copyright">
                            Copyright Reserved @2023
                        </div>
                        <div className="privacy">
                            Terms and Conditions
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default UserPanel
