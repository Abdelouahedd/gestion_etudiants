import React from 'react'
import {Route, Router, Switch} from 'react-router-dom'
import history from './helper/history'
import Login from "./studentComponents/login/Login";

export default () => (
    <Router history={history}>
        <Switch>
            <Route path='/login' component={Login}/>
        </Switch>
    </Router>
)
