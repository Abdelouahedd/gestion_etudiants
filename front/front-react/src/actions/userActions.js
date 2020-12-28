

export const setLogin = (token) => {
    window.localStorage.setItem('token', token);
    return {
        type: "SAVE_USER",
        payload: token
    };
}



export const logout = () => {
    return {
        type: "LOGOUT",
    };
}