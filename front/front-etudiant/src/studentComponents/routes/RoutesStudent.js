import React, {lazy} from "react";
import {Route, Switch} from "react-router-dom";


const MainS = lazy(() => import("../main/Main"));
const Module = lazy(() => import( "../module/Module"));
const ajDemande = lazy(() => import( "../demande/ajDemande"));


const RoutesStudent = () => (
    <Switch>
        <Route path='/' component={MainS} exact/>
        <Route path="/module/:id" component={Module} exact/>
        <Route path="/ajDemande" component={ajDemande} exact/>
    </Switch>
);
export default RoutesStudent;