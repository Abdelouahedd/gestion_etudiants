import React from 'react'
import { Route, Router, Switch } from 'react-router-dom'
import history from './helper/history'
import Login from "./component/Authentication/loginForm/Login";

export default () => (
    <Router history={history} >
        <Switch>
            <Route path='/login' component={Login} />
        </Switch>
    </Router>
)
