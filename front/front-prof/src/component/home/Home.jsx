import React, {useEffect} from 'react'


import Header from "../shared/header/Header";
import SideBarStudent from "../shared/Side-bar/SideBar";
import Routes from "../routes/Routes";


export default function Home() {
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
