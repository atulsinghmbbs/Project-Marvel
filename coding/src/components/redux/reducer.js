import { ADD_TO_CART } from "./Constant";
import { REMOVE_FROM_CART } from "./Constant";
import { CLEAR_CART } from "./Constant";
// export const cartData = (state = [], action) => {
//     switch (action.type) {
//         case ADD_TO_CART:
//             console.log("add to cart reducer wala", action);
//             return [action.data, ...state]

//         case REMOVE_FROM_CART:
//             console.log("remove in reducer", action);
//             return {
//                 ...state,
//                 cartData: state.cartData ? state.cartData.filter(
//                     (item) => item.domainName !== action.payload.domainName
//                 ) : [],
//             };

//         default:
//             return state;

//     }
// }

//

export const cartData = (state = { cartData: [] }, action) => {
    switch (action.type) {
        case ADD_TO_CART:
            console.log("add to cart reducer wala", action);
            return { ...state, cartData: [action.data, ...state.cartData] };

        case REMOVE_FROM_CART:
            console.log("remove in reducer", action);
            return {
                ...state,
                cartData: state.cartData.filter(
                    (item) => item.domainName !== action.payload.domainName
                ),
            };

        default:
            return state;
    }
};
//



