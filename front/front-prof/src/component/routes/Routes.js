import React, {lazy} from "react";
import {Route, Switch} from "react-router-dom";


const MainS = lazy(() => import("../main/Main"));
const Matiére = lazy(() => import("../matiere/Matiére"));



const Routes = () => (
    <Switch>
        <Route path='/' component={MainS} exact/>
        <Route path="/matiere/:id" component={Matiére} exact/>
    </Switch>
);
export default Routes;