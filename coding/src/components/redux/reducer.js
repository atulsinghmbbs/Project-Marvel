import { ADD_TO_CART } from "./Constant";
import { REMOVE_FROM_CART } from "./Constant";


export const cartData = (state = { cartData: [] }, action) => {
    switch (action.type) {
        case ADD_TO_CART:
            console.log("add to cart reducer wala", action.backendData.products);
            return { ...state, cartData: [action.backendData, ...state.cartData] };

        case REMOVE_FROM_CART:
            console.log("remove in reducer", action);
            const removedProductId = action.payload.productId;
            return {
                ...state,
                cartData: state.cartData.filter(
                    (item) => item.productId !== removedProductId
                ),
            };
        default:
            return state;
    }
};
