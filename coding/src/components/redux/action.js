import { ADD_TO_CART } from "./Constant";

export const addToCart = (data) => {
    console.log("data in action", data);
    return {
        type: ADD_TO_CART,
        data: data,
    }
}

export const removeFromCart = (domainName) => ({
    type: "REMOVE_FROM_CART",
    payload: {
        domainName: domainName,
    },
});