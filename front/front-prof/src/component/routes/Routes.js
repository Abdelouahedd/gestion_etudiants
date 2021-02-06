import React, {lazy} from "react";
import {Route, Switch} from "react-router-dom";


const MainS = lazy(() => import("../main/Main"));



const Routes = () => (
    <Switch>
        <Route path='/' component={MainS} exact/>
    </Switch>
);
export default Routes;