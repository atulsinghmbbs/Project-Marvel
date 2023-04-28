

export const bakendBaseUrl = "http://192.168.1.43:8888"
export const bakendHeader = {
    'Content-type': 'application/json; charset=UTF-8',
    'Authorization': `Bearer ${localStorage.getItem("loginToken")}`
}


//

// localStorage.setItem('token', token);
// localStorage.setItem('expirationTime', expirationTime);


// const getToken = async () => {
//     const token = localStorage.getItem('token');
//     const expirationTime = localStorage.getItem('expirationTime');

//     if (!token || !expirationTime || Date.now() > Number(expirationTime)) {
//         // Token has expired or doesn't exist, refresh token
//         const newToken = await refreshToken(); // Send a request to the backend to refresh the token
//         const newExpirationTime = Date.now() + 24 * 60 * 60 * 1000; // Calculate the expiration time of the new token
//         localStorage.setItem('token', newToken);
//         localStorage.setItem('expirationTime', newExpirationTime);
//         return newToken;
//     }
//     // Token is still valid, return it
//     return token;
// };

