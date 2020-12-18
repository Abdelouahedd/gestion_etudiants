import React, { useState } from 'react';
import * as Icon from 'react-feather';
import { Link } from 'react-router-dom';

function SideBar(props) {

    const [user] = useState({
        nom: "Admin",
        prenom: "ADMIN",
        email: "admin@gmail.com"
    });

    return (
        <div id="layoutSidenav_nav">
            <nav className="sidenav shadow-right sidenav-light">
                <div className="sidenav-menu">
                    <div className="nav accordion" id="accordionSidenav">
                        <div className="sidenav-menu-heading">Core</div>
                        {/* Dashbord home */}
                        <Link className="nav-link collapsed" to="/">
                            <div className="nav-link-icon">
                                <Icon.Activity size={15} />
                            </div>
                            Dashboards
                        </Link>
                        {/* Gestion users */}
                        <div className="sidenav-menu-heading">Management</div>
                        <Link className="nav-link collapsed" to="/users" >
                            <div className="nav-link-icon">
                                {/* <Icon.Grid size={15} /> */}
                                <Icon.Users size={15} />
                            </div>
                            Users Management
                        </Link>


                        {/* Gestion group */}
                        <Link className="nav-link collapsed" to="/com">
                            <div className="nav-link-icon">
                                <i className="fa fa-users"></i>
                            </div>
                            Groups management
                        </Link>

                    </div>
                </div>
                <div className="sidenav-footer">
                    <div className="sidenav-footer-content">
                        <div className="sidenav-footer-subtitle">Logged in as:</div>
                        <div className="sidenav-footer-title">{user.nom + " " + user.prenom}</div>
                    </div>
                </div>
            </nav>
        </div>
    );
}

export default SideBar;