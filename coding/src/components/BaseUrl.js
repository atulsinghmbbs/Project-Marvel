

export const bakendBaseUrl = "http://192.168.1.50:8888"
export const bakendHeader = {
    'Content-type': 'application/json; charset=UTF-8',
    'Authorization': `Bearer ${localStorage.getItem("loginToken")}`
}   
