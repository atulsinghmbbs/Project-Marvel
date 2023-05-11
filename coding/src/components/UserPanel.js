// import React, { useEffect, useState } from 'react'
// import { NavLink } from 'react-router-dom'
// import "./UserPanel.css"
//
// const UserPanel = () => {
//
//     const [name, setName] = useState("")
//     const baseUrl = "http://192.168.1.50:8888"
//     const baseImageUrl = baseUrl + "/static/images/"
//     const onLoad = (event) => {
//         fetch(baseUrl + "/orders/getAllOrders", {
//             method: 'GET',
//             headers: {
//                 'Content-type': 'application/json; charset=UTF-8',
//                 'Authorization': 'Bearer eyJhbGciOiJIUzM4NCJ9.eyJpc3MiOiJoYWFybWsiLCJzdWIiOiJKV1QgVG9rZW4iLCJpYXQiOjE2ODI5MjgyODUsImV4cCI6MTY4MzAxNDY4NSwiYXV0aG9yaXRpZXMiOiJST0xFX1VTRVIiLCJ1c2VybmFtZSI6IkhJUEwzIn0.MfSsSJS3JencJaDcmgvZSzNeTg0AttB2Bpicw8OqLqH_XPZ4o3GnxX5ud_ASGuPD'
//             },
//         }).then((response) => response.json())
//             .then((json) => { setName(json) });
//     }
//
//     useEffect(() => {
//         onLoad()
//     }, [])
//
//
//
//
//
//     //upload an image
//     const [showpopup, setpopup] = useState(false);
//     function handleClick() {
//         setpopup(true);
//     }
//
//     function handleUploadImg(event) {
//         event.preventDefault();
//         const file = event.target.elements.image.files[0];
//         const formData = new FormData();
//         formData.append("image", file);
//         setpopup(false);
//         fetch(baseUrl + "/users/upload-pic", {
//             method: "POST",
//             body: formData,
//             headers: {
//                 'Content-Type': 'multipart/form-data',
//                 'Authorization': 'Bearer eyJhbGciOiJIUzM4NCJ9.eyJpc3MiOiJoYWFybWsiLCJzdWIiOiJKV1QgVG9rZW4iLCJpYXQiOjE2ODMwMTMxNDEsImV4cCI6MTY4MzA5OTU0MSwiYXV0aG9yaXRpZXMiOiJST0xFX1VTRVIiLCJ1c2VybmFtZSI6IkhJUEw0In0.bqZp0X6KFPjWE4t2WrcM6efPSaOAycAEtv459cZHoMHnCtap392174PRdogDsnS6',
//             },
//         })
//             .then((response) => response.json())
//             .then((json) => {
//                 window.alert("image uploaded sucessfully....")
//                 console.log(json)
//             })
//             .catch(error => {
//                 window.alert("Something went wrong.....")
//             })
//
//     }
//
//
//
//
//     return (
//         <div className="user-main">
//             <div className="user-nav">
//                 <div className='logo'>HAARMK</div>
//                 <div className='user-dashboard'>{name.firstName} DASHBOARD</div>
//                 <NavLink to="/ActiveServices" className="reset-nav">
//                     <div className='status'>Active Services</div>
//                 </NavLink>
//             </div>
//
//
//             <div className="user-home">
//                 <div className="user-menu">
//                     <div className="upper-box">
//                         <NavLink to="/" className="reset-nav">
//                             <div className='home one'>Home</div>
//                         </NavLink>
//                         <div className='transactions one'>Transactions</div>
//                         <NavLink to="/UserOrder" className="reset-nav">
//                             <div className='order one'>Order</div>
//                         </NavLink>
//                         <NavLink to="/ResetPassword" className="reset-nav">
//                             <div className='reset-password one'>Reset Password</div>
//                         </NavLink>
//                         <NavLink to="/LoginWithMe" className="reset-nav">
//                             <div className='subscirption one'>Logout</div>
//                         </NavLink>
//                     </div>
//                     <div className="lower-box">
//                         <button>Renew</button>
//                     </div>
//                 </div>
//
//
//
//
//                 <div className="card">
//
//
//                     <div className="both-card">
//                         <div className="card1 card-design">
//                             <div className='profile'>Profile</div>
//                             <div className='edit'>
//                                 <div className='pic'>
//                                     <img src={baseImageUrl + name.image} alt="" onClick={handleClick} />
//                                     {showpopup && (
//                                         <div className="popup">
//                                             <form onSubmit={handleUploadImg}>
//                                                 <input type="file" name="image" />
//                                                 <button type="submit">Upload</button>
//                                             </form>
//                                         </div>
//                                     )}
//                                 </div>
//                                 <div className='name' aria-readonly>{name.firstName}{" "}{name.lastName}</div>
//                                 <div className='email' aria-readonly>{name.email}</div>
//                                 <button>Edit Profile</button>
//                             </div>
//
//                             <div className='box'>
//                                 <div className='order box-design'>Services</div>
//                                 <div className='domain box-design'>Domain</div>
//                                 <div className='transactions box-design'></div>
//                             </div>
//                         </div>
//
//
//                         <div className="card2 card-design">
//                             <div className="latest-transactions">Latest Transactions</div>
//                             <div className="table">
//                                 <table class="table">
//                                     <thead>
//                                         <tr>
//                                             <th scope="col">UserName</th>
//                                             <th scope="col">First</th>
//                                             <th scope="col">Last</th>
//                                             <th scope="col">Handle</th>
//                                         </tr>
//                                     </thead>
//                                     <tbody>
//                                         <tr>
//                                             <th scope="row">1</th>
//                                             <td>Mark</td>
//                                             <td>Otto</td>
//                                             <td>@mdo</td>
//                                         </tr>
//                                         <tr>
//                                             <th scope="row">2</th>
//                                             <td>Jacob</td>
//                                             <td>Thornton</td>
//                                             <td>@fat</td>
//                                         </tr>
//                                         <tr>
//                                             <th scope="row">3</th>
//                                             <td>Jacob</td>
//                                             <td>Thornton</td>
//                                             <td>@fat</td>
//                                         </tr>
//                                         <tr>
//                                             <th scope="row">4</th>
//                                             <td>Jacob</td>
//                                             <td>Thornton</td>
//                                             <td>@fat</td>
//                                         </tr>
//                                         <tr>
//                                             <th scope="row">5</th>
//                                             <td>Jacob</td>
//                                             <td>Thornton</td>
//                                             <td>@fat</td>
//                                         </tr>
//                                     </tbody>
//                                 </table>
//                             </div>
//                         </div>
//                     </div>
//
//                     <div className="terms">
//                         <div className="copyright">
//                             Copyright Reserved @2023
//                         </div>
//                         <div className="privacy">
//                             Terms and Conditions
//                         </div>
//                     </div>
//                 </div>
//             </div>
//         </div>
//     )
// }
//
// export default UserPanel

//-------------------------------------------------------------------------


import React, { useEffect, useState } from 'react'
import { NavLink } from 'react-router-dom'
import "./UserPanel.css"
import { bakendBaseUrl, bakendHeader } from './BaseUrl'
import axios from 'axios'


const UserPanel = () => {

    const [userDetails, setUserDetails] = useState("")
    // const baseUrl = "http://192.168.1.50:8888/"
    const baseImageUrl = bakendBaseUrl + "/static/images/"
    const onLoad = (event) => {
        fetch(bakendBaseUrl + "/users/", {
            method: 'GET',
            headers: bakendHeader,
        }).then((response) => response.json())
            .then((json) => { setUserDetails(json) });
    }

    useEffect(() => {
        onLoad()
    }, [])




    //upload an image
    const [showpopup, setpopup] = useState(false);
    function handleClick() {
        setpopup(true);
    }


    function handleUploadImg(event) {
        event.preventDefault();
        const file = event.target.elements.image.files[0];
        const formData = new FormData();
        formData.append("document", file);
        setpopup(false);

        axios
            .post(bakendBaseUrl + "/users/upload-pic", formData, {
                headers: {
                    ...bakendHeader,
                    "Content-Type": "multipart/form-data",
                },
            })
            .then((response) => {
                window.location.reload();
            })
            .catch((error) => {
                console.log("Something went wrong try again...");
            });
    }






    return (
        <div className="user-main">
            <div className="user-nav">
                <div className='logo'>HAARMK</div>
                <div className='user-dashboard'>{userDetails.firstName} DASHBOARD</div>
                <NavLink to="/ActiveServices" className="reset-nav">
                    <div className='status'>Active Services</div>
                </NavLink>
            </div>


            <div className="user-home">
                <div className="user-menu">
                    <div className="upper-box">
                        <NavLink to="/" className="reset-nav">
                            <div className='home one'>Home</div>
                        </NavLink>
                        <div className='transactions one'>Transactions</div>
                        <NavLink to="/UserOrder" className="reset-nav">
                            <div className='order one'>Order</div>
                        </NavLink>
                        <NavLink to="/ResetPassword" className="reset-nav">
                            <div className='reset-password one'>Reset Password</div>
                        </NavLink>
                        <NavLink to="/LoginWithMe" className="reset-nav">
                            <div className='subscirption one'>Logout</div>
                        </NavLink>
                    </div>
                    <div className="lower-box">
                        <button>Renew</button>
                    </div>
                </div>




                <div className="card">


                    <div className="both-card">
                        <div className="card1 card-design">
                            <div className='profile'>Profile</div>
                            <div className='edit'>
                                <div className='pic'>
                                    <img src={baseImageUrl + userDetails.image} alt="" onClick={handleClick} />
                                    {showpopup && (
                                        <div className="popup">
                                            <form onSubmit={handleUploadImg}>
                                                <input type="file" name="image" />
                                                <button type="submit">Upload</button>
                                            </form>
                                        </div>
                                    )}
                                </div>
                                <div className='name' aria-readonly>{userDetails.firstName}{" "}{userDetails.lastName}</div>
                                <div className='email' aria-readonly>{userDetails.email}</div>
                                <button>Edit Profile</button>
                            </div>

                            <div className='box'>
                                <div className='order box-design'>Services</div>
                                <div className='domain box-design'>Domain</div>
                                <div className='transactions box-design'></div>
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
