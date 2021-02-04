

export const setLogin = (token) => {
    return {
        type: "SAVE_USER",
        payload: token,
    };
}


export const setInfo = (userInfo) => {
    return {
        type: "GET_USER",
        payload: userInfo
    };
}


export const logout = () => {
    return {
        type: "LOGOUT",
    };
}