import React, { lazy } from "react";
import { Route, Switch } from "react-router-dom";


const Main = lazy(() => import("../component/main/Main"));
const ListEtudiant = lazy(() => import("../component/etudiants/listEtudiant"));
const AjouEtudiant = lazy(() => import("../component/etudiants/AjouEtudiant"));
const ListProfesseurs = lazy(() => import("../component/professeurs/listProfesseurs"));
const AjouterProf = lazy(() => import("../component/professeurs/AjouterProf"));

const Routes = () => (
    <Switch>
        <Route path='/' component={Main} exact />
        <Route path='/listEtudiant' component={ListEtudiant} exact />
        <Route path='/ajouEt' component={AjouEtudiant} exact />
        <Route path='/listProfs' component={ListProfesseurs} exact />
        <Route path='/ajouProf' component={AjouterProf} exact />
    </Switch>
);
export default Routes;