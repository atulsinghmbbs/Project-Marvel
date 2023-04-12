import { MyAxios } from "./helper";

import axios from "axios";

export const signUp = (user) => {

    return axios.post("http://localhost:8888/haarmk/signup", user).then((respo)=>respo.data)

}

export const loginWithJWT = (user) => {

    return axios.post("http://localhost:8888/haarmk/login", user).then((respo)=>respo.data)

}