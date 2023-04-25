

//log in
// import { GoogleLogin } from "react-google-login"


// const clientId = "530760682193-sofqsmesd12559btv57726375g42ljit.apps.googleusercontent.com"


// function Login() {

//     const onSuccess = (res) => {
//         console.log("Login success! current user:", res.profileObj);
//     }

//     const onFailure = (res) => {
//         console.log("Login Failed!", res)
//     }

//     return (
//         <div id="signButton">
//             <GoogleLogin
//                 clientId={clientId}
//                 buttonText="Login"
//                 onSuccess={onSuccess}
//                 onFailure={onFailure}
//                 cookiePolicy={'single_host_origin'}
//                 isSignedIn={true}
//             />

//         </div>
//     )
// }



// export default Login;

// ========================================================================================

// log out

// import { GoogleLogout } from "react-google-login"

// const clientId = "530760682193-sofqsmesd12559btv57726375g42ljit.apps.googleusercontent.com"

// function Logout() {

//     const onSuccess = () => {
//         console.log("log out successfully")
//     }

//     return (
//         <div id="signOutButton">
//             <GoogleLogout
//                 clientId={clientId}
//                 buttonText={"Logout"}
//                 onLogoutSuccess={onSuccess}
//             />
//         </div>
//     )
// }

// export default Logout;



// sign up form data 


// google wali cssss btn

// .continue-with-google-btn{
//     width: 300px;
//     padding: 13px;
//     border-radius: 8px;
//     font-size: 20px;
//     border: 1px solid grey;
//     cursor: pointer;
//     background: #000;
//     color: #000;
//     position: relative;
//     left: 58.2rem;
//     margin-top: 40px;
//     color: white;
//     transition: .3s;
    
// }

// .continue-with-google-btn:hover{
//     background-color: #FFF;
//     color: black;
//     transition: .3s;

// } 

// -------------------------------------------------------------------------

// reducer 

// export const cartData = (state = { cartData: [] }, action) => {
//     switch (action.type) {
//         case ADD_TO_CART:
//             console.log("add to cart reducer wala", action);
//             return { ...state, cartData: [action.data, ...state.cartData] };

//         case REMOVE_FROM_CART:
//             console.log("remove in reducer", action);
//             return {
//                 ...state,
//                 cartData: state.cartData.filter(
//                     (item) => item.domainName !== action.payload.domainName
//                 ),
//             };

//         default:    
//             return state;

//     }
// }

// -------------------------------------------

// fetch(bakendBaseUrl + `/carts/add-domain?domainName=${select.cartData}`, {
//     method: 'PUT',
//     headers: bakendHeader,
// })
//     .then((response) => response.json())
//     .then((json) => console.log("ressss", json))
//     .catch((err) => console.log("error in checkout", err))


// ----------------------------------------------------------------------

//domainAvality wala
    //     let resultText;
    //     if (!isLoading && domainResult.result.purchasable === true) {
    //         resultText =
    //             <div className='available'>
    //                 <p className='item'>This is available</p>
    //                 <div className="price">
    //                     <i class="fa-sharp fa-solid fa-dollar-sign"></i><p>{price}</p>
    //                 </div>
    //                 <button>Buy Now</button>
    //             </div>
    //     } else {
    //         resultText = <p className='not-available'>This domain is not available <br /> Some domains are given below, you can select</p>;
    //     }
    //     return resultText
    // }



