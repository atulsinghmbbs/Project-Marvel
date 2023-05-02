import { ADD_TO_CART } from "./Constant";
import { REMOVE_FROM_CART } from "./Constant";
import { bakendBaseUrl } from "../BaseUrl";
import { bakendHeader } from "../BaseUrl";

export const addToCart = (data) => {
    console.log("data in action", data);
    return async (dispatch) => {
        const response = await fetch(`${bakendBaseUrl}/carts/add-domain?domainName=${data.domainName}`, {
            method: 'PUT',
            headers: bakendHeader,
        })
        const backendData = await response.json();
        console.log('Data send to backend', backendData);
        dispatch({
            type: ADD_TO_CART,
            backendData: backendData
        })
    }
}


export const removeFromCart = (productId) => {
    return async (dispatch) => {
        const response = await fetch(`${bakendBaseUrl}/carts/remove-item?productId=${productId}`, {
            method: 'PUT',
            headers: bakendHeader,
        })
        const removeFrombackend = await response.json();
        console.log('Data remove from backend', removeFrombackend);
        dispatch({
            type: REMOVE_FROM_CART,
            payload: {
                removeFrombackend: removeFrombackend,
                productId: productId
            }
        })
    }
}



