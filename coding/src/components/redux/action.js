import { ADD_TO_CART } from "./Constant";
import { REMOVE_FROM_CART } from "./Constant";
import { bakendBaseUrl } from "../BaseUrl";
import { bakendHeader } from "../BaseUrl";

const sendDataToBackend = async (domain) => {
    console.log("add to backend", domain);
    const response = await fetch(`${bakendBaseUrl}/carts/add-domain?domainName=${domain.domainName}`, {
        method: 'PUT',
        headers: bakendHeader,
    });
    const backendData = await response.json();

    console.log('Data sent to the backend:', backendData);
    return backendData
}

export const addToCart = (data) => {
    console.log("data in action", data);
    const filterData = sendDataToBackend(data)
    return {
        type: ADD_TO_CART,
        backendData: filterData,
    }
}


// const removeDataToBackend = async (domainName) => {
//     console.log("remove to backend", domainName);
//     const response = await fetch(`${bakendBaseUrl}/carts/remove-item?=${domainName}`, {
//         method: 'PUT',
//         headers: bakendHeader,
//     })
//     const data = await response.json();
//     console.log('Date remove to the backend', data);
// }

export const removeFromCart = (domainName) => ({
    type: REMOVE_FROM_CART,
    payload: {
        domainName: domainName,
        // removeDataToBackend(domainName)
    },
});


