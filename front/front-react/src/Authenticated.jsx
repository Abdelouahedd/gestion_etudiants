import React from 'react'
import { Route, Router, Switch } from 'react-router-dom'
import history from './helper/history'
import Home from "./component/home/Home";

export default () => (
    <Router history={history}>
        <Switch>
            <Route path='/' component={Home} />
        </Switch>
    </Router>
)