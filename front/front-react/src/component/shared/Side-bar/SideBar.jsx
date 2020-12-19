import React, { useState } from 'react';
import * as Icon from 'react-feather';
import { Link } from 'react-router-dom';
import { Menu } from 'antd';
import { MailOutlined, UsergroupDeleteOutlined, SettingOutlined } from '@ant-design/icons';

const { SubMenu } = Menu;
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
                            style={{ height: '70%', marginTop: 20, overflowY: "hidden" }}
                            defaultOpenKeys={['dash']}
                            mode="inline"
                            className="sidenav-menu-heading"
                            aria-orientation="vertical"
                        >
                            <Menu.Item key="dash" icon={<Icon.Activity size={20} />} >
                                <Link to="/">
                                    DashBord
                                </Link>
                            </Menu.Item>
                            <SubMenu key="users"
                                icon={<UsergroupDeleteOutlined style={{ fontSize: '22px' }} />}
                                title="Gestion des utilisateur"
                            >
                                <Menu.ItemGroup key="et" title="Etudiant" >
                                    <Menu.Item key="1">
                                        <Link to="/listEtudiant">
                                            List des etudiant
                                        </Link>
                                    </Menu.Item>
                                    <Menu.Item key="2">
                                        <Link to="/ajouEt">
                                            Ajouter Etudiant
                                        </Link>
                                    </Menu.Item>
                                </Menu.ItemGroup>
                                <Menu.ItemGroup key="pr" title="Professeur">
                                    <Menu.Item key="3">
                                        <Link to="/listProfs">
                                            List des professeurs
                                        </Link>
                                    </Menu.Item>
                                    <Menu.Item key="4">
                                        <Link to="/ajouProf">
                                            Ajouter Professeur
                                        </Link>
                                    </Menu.Item>
                                </Menu.ItemGroup>
                            </SubMenu>
                            <SubMenu key="fl" icon={<SettingOutlined />} title="Gestion des Filiere ">
                                <Menu.Item key="5">
                                    List des Fliere
                                </Menu.Item>
                                <Menu.Item key="6">Ajouter Filiere</Menu.Item>
                                <SubMenu key="niv" title="Niveau">
                                    <Menu.Item key="7">List Niveau</Menu.Item>
                                    <Menu.Item key="8">Ajouter niveau</Menu.Item>
                                </SubMenu>
                                <SubMenu key="sem" title="Semestre">
                                    <Menu.Item key="7">List Semestre</Menu.Item>
                                    <Menu.Item key="8">Ajouter Semestre</Menu.Item>
                                </SubMenu>
                                <SubMenu key="mod" title="Module">
                                    <Menu.Item key="7">List Module</Menu.Item>
                                    <Menu.Item key="8">Ajouter Module</Menu.Item>
                                    <SubMenu key="sub6" title="Element Module">
                                        <Menu.Item key="7">List element</Menu.Item>
                                        <Menu.Item key="8">Ajouter element</Menu.Item>
                                    </SubMenu>
                                </SubMenu>
                            </SubMenu>
                            <SubMenu key="serv" icon={<MailOutlined />} title="Gestion de demande">
                                <Menu.Item key="9">
                                    List des demandes
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