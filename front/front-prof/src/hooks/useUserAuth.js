import { useCallback, useContext, useEffect, useState } from 'react';
import { Context } from "../context/userContext";
import { BASE_URL } from "../config/config"

import axios from "axios";
import setAuthToken from '../helper/setAuthToken';
import history from '../helper/history';
import {setInfo, setLogin} from '../actions/userActions';
import jwtDecode from "jwt-decode";

export const useUserAuthentication = () => {

    const { user, dispatch } = useContext(Context);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(true);

    const getUserByEmail = useCallback(
        async (email) => {
            await axios.get(`${BASE_URL}api/users/user?email=${email}`)
                .then(async res => {
                    await dispatch(setInfo(res.data));
                    window.localStorage.setItem('user', JSON.stringify(res.data))
                })
                .catch((err) => console.log(err));
        }, []);

    const fetchUser = useCallback(
        async () => {
            try {
                const token = window.localStorage.getItem('token')
                if (token) {
                    await axios({
                        method: 'GET',
                        url: `${BASE_URL}api/users/refreshToken`,
                        headers: {
                            'Content-Type': 'application/json',
                            'x-auth-token': `Bearer ${token}`,
                        },
                    }).then(
                        async res => {
                            if (res.data.status === 403) {
                                history.push('/login');
                            }
                            setAuthToken(token)
                            dispatch(setLogin(res.data.jwt));
                            var decode = await jwtDecode(res.data.jwt);
                            await getUserByEmail(decode.sub)
                            setLoading(false)
                        }
                    );
                } else {
                    history.push('/login');
                }
            } catch (err) {
                setError(err);
            } finally {
                setLoading(false);
            }
        }, [dispatch]);

    useEffect(() => {
        if (user.isLoggedIn === false) {
            fetchUser()
        }
        return () => setLoading(false)
    }, [fetchUser, user.isLoggedIn])

    return {
        error,
        loading,
        isLoggedIn: user.isLoggedIn,
    }
}