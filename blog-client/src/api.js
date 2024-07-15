import axios from "axios";

const userAuthAPI = axios.create({
    baseURL: "http://localhost:8081/api",
    headers: {
        "Content-Type": "application/json",
    },
});

const postAPI = axios.create({
    baseURL: "http://localhost:8082/api",
    headers: {
        "Content-Type": "application/json",
    },
});

const commentAPI = axios.create({
    baseURL: "http://localhost:8083/api",
    headers: {
        "Content-Type": "application/json",
    },
});

export { userAuthAPI, postAPI, commentAPI };