import React, {useState, useEffect} from 'react';
import * as Icon from 'react-feather';
import {Link} from 'react-router-dom';
import {Menu} from 'antd';
import {MailOutlined, UsergroupDeleteOutlined, SettingOutlined} from '@ant-design/icons';
import {Info} from "react-feather";

const {SubMenu} = Menu;

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
                        <Menu
                            style={{height: '79vh', marginTop: 20, overflowY: "hidden"}}
                            defaultOpenKeys={['dash']}
                            mode="inline"
                            className="sidenav-menu-heading"
                            aria-orientation="vertical"
                        >
                            <Menu.Item key="dash" icon={<Icon.Activity size={20}/>}>
                                <Link to="/">
                                    DashBord
                                </Link>
                            </Menu.Item>
                            <SubMenu key="fl" icon={<SettingOutlined/>} title="Les modules ">

                            </SubMenu>
                            <SubMenu key="serv" icon={<MailOutlined/>} title="Gestion de demande">
                                <Menu.Item key="9">
                                    <Link to="/ajDemande">
                                        Demander un service
                                    </Link>
                                </Menu.Item>
                            </SubMenu>
                        </Menu>
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