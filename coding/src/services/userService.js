

import axios from "axios";

export const signUp = (user) => {

    return axios.post("http://localhost:8888/haarmk/signup", user).then((respo)=>respo.data)

}

export  const loginWithJWT = (user) => {

    return axios.post("http://localhost:8888/haarmk/login", user).then((resp)=>resp.data)

}

// export default function loginWithJWT(){
//     const http =axios.create({
//         baseURL:"http://localhost:8888/haarmk",
//         headers:{
//             "Content-type":"application/json"
//         }
//     })
//     return http;
// }