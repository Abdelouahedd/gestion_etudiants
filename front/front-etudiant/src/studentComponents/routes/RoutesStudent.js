import React, {lazy} from "react";
import {Route} from "react-router-dom";


const MainS = lazy(() => import("../main/Main"));

const RoutesStudent = () => (

        <Route path='/' component={MainS} exact/>

);
export default RoutesStudent;