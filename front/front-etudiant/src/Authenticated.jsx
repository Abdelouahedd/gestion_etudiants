import React from 'react'
import { Route, Router, Switch } from 'react-router-dom'
import history from './helper/history'
import Home from "./component/home/Home";
import HomeStudent from "./studentComponents/home/HomeStudent";

export default () => (
    <Router history={history}>
        <Switch>
            <Route path='/' component={Home} exact/>
            <Route path='/Student' component={HomeStudent} exact />
        </Switch>
    </Router>
)