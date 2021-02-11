import React, {lazy} from "react";
import {Route, Switch} from "react-router-dom";


const MainS = lazy(() => import("../main/Main"));
const Matiére = lazy(() => import("../matiere/Matiére"));
const Absence = lazy(() => import("../absence/absence"));
const Note = lazy(() => import("../note/note"));


const Routes = () => (
    <Switch>
        <Route path='/' component={MainS} exact/>
        <Route path="/matiere/:id" component={Matiére} exact/>
        <Route path="/absence/:id" component={Absence} exact/>
        <Route path="/note/:id" component={Note} exact/>
    </Switch>
);
export default Routes;