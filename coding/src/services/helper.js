import axios from "axios";
export const Base_URL = 'http://localhost:8888';
export const MyAxios = axios.create({baseUrl:Base_URL});
