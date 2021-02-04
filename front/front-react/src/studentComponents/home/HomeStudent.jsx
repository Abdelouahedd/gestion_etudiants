import React, {useState, useEffect} from 'react'

import Header from "../../component/shared/header/Header";
import Routes from "../../routes/Routes";
import SideBarStudent from "../shared/SideBarStudent";


export default function HomeStudent() {
    //show and hide side bar
    const toggelSideBar = () => {
        document.querySelector('body').classList.toggle('sidenav-toggled')
    }

    //add event listener to id of side bar
    useEffect(() => {
        document.getElementById("sidebarToggle").addEventListener('click', toggelSideBar)
    }, []);

    return (
        <div>
            <Header/>
            <div id="layoutSidenav">
                <SideBarStudent/>
                <Routes/>
            </div>
        </div>
    )
}
