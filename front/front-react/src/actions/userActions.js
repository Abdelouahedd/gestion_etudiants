

export const setLogin = (token) => {
    return {
        type: "SAVE_USER",
        payload: token,
    };
}


export const getInfo = (userInfo) => {
    return {
        type: "SAVE_USER",
        payload: userInfo
    };
}


export const logout = () => {
    return {
        type: "LOGOUT",
    };
}