import React from 'react'
import { Route, Redirect } from 'react-router-dom';

const PrivateRoute = ({ component: Component, ...rest }) => {
    let token = localStorage.getItem('jwtInfo');
    return (
        <Route
            {...rest}
            render={props => {
                return <Component {...props} />;
                // if (token) {
                //     return <Component {...props} />;
                // } else {
                //     return (
                //         <Redirect
                //             to={{ pathname: "/sign", state: { from: props.location } }}
                //         />
                //     );
                // }
            }}
        />
    )
}

export default PrivateRoute
