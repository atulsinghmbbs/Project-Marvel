import { MyAxios } from "./helper";

export const signUp = (user) => {

    return MyAxios.post("/register", user).then((respo)=>respo.data)

}