import React, {lazy} from "react";
import {Route, Router, Switch} from "react-router-dom";
import history from "../../helper/history";


const MainS = lazy(() => import("../main/Main"));

const RoutesStudent = () => (

        <Route path='/main' component={MainS} exact/>

);
export default RoutesStudent;