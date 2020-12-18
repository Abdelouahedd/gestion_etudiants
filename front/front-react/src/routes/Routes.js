import React, { lazy } from "react";
import { Route, Switch } from "react-router-dom";


const Main = lazy(() => import("../component/main/Main"));

const Routes = () => (
    <Switch>
        <Route path='/' component={Main} exact />
    </Switch>
);
export default Routes;