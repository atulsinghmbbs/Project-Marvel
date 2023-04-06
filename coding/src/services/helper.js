import axios from "axios";
export const Base_URL = 'haarmk.com:3306/haarmkco_test_db';
export const MyAxios = axios.create({baseUrl:Base_URL});
