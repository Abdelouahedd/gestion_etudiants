import React from 'react'
import { Route, Redirect } from 'react-router-dom';

const PrivateRoute = ({ component: Component, ...rest }) => {
    let token = localStorage.getItem('token');
    return (
        <Route
            {...rest}
            render={props => {
                if (token) {
                    return <Component {...props} />;
                } else {
                    return (
                        <Redirect
                            to={{ pathname: "/login", state: { from: props.location } }}
                        />
                    );
                }
            }}
        />
    )
}

export default PrivateRoute
