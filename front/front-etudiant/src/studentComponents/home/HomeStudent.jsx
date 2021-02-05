import React, {useState, useEffect} from 'react'


import RoutesStudent from "../routes/RoutesStudent";
import Header from "../shared/header/Header";
import SideBarStudent from "../shared/Side-bar/SideBarStudent";


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
                <RoutesStudent/>
            </div>
        </div>
    )
}
