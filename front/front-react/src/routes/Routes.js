import React, { lazy } from "react";
import { Route, Switch } from "react-router-dom";


const Main = lazy(() => import("../component/main/Main"));
const ListEtudiant = lazy(() => import("../component/etudiants/listEtudiant"));
const AjouEtudiant = lazy(() => import("../component/etudiants/AjouEtudiant"));

const Routes = () => (
    <Switch>
        <Route path='/' component={Main} exact />
        <Route path='/listEtudiant' component={ListEtudiant} exact />
        <Route path='/ajouEt' component={AjouEtudiant} exact />
    </Switch>
);
export default Routes;