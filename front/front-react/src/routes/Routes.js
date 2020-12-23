import React, { lazy } from "react";
import { Route, Switch } from "react-router-dom";


const Main = lazy(() => import("../component/main/Main"));
const ListEtudiant = lazy(() => import("../component/etudiants/listEtudiant"));
const AjouEtudiant = lazy(() => import("../component/etudiants/AjouEtudiant"));
const ListProfesseurs = lazy(() => import("../component/professeurs/listProfesseurs"));
const AjouterProf = lazy(() => import("../component/professeurs/AjouterProf"));
const ListFilire = lazy(() => import("../component/filiere/listFiliere"));
const AjouFilire = lazy(() => import("../component/filiere/AjouFiliere"));
const ListNiveau = lazy(() => import("../component/Niveau/listNiveau"));
const AjouterNiveau = lazy(() => import("../component/Niveau/ajouterNiveau"));
const ListSemestre = lazy(() => import("../component/semestre/listSemestre"));
const AjouterSemestre = lazy(() => import("../component/semestre/ajouterSemestre"));

const Routes = () => (
    <Switch>
        <Route path='/' component={Main} exact />
        <Route path='/listEtudiant' component={ListEtudiant} exact />
        <Route path='/ajouEt' component={AjouEtudiant} exact />
        <Route path='/listProfs' component={ListProfesseurs} exact />
        <Route path='/ajouProf' component={AjouterProf} exact />
        <Route path='/listFiliere' component={ListFilire} exact />
        <Route path='/ajoutFiliere' component={AjouFilire} exact />
        <Route path='/listNiveau' component={ListNiveau} exact />
        <Route path='/ajoutNiveau' component={AjouterNiveau} exact />
        <Route path='/listSemestre' component={ListSemestre} exact />
        <Route path='/ajoutSemestre' component={AjouterSemestre} exact />
    </Switch>
);
export default Routes;