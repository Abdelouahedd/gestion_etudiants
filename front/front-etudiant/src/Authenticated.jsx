import React from 'react'
import {Route, Router, Switch} from 'react-router-dom'
import history from './helper/history'
import HomeStudent from "./studentComponents/home/HomeStudent";


export default () => (
    <Router history={history}>
        <Switch>
            <Route path='/' component={HomeStudent} exact/>
        </Switch>
    </Router>
)