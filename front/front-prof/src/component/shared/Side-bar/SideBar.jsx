import React, {useState, useEffect, useCallback} from 'react';
import * as Icon from 'react-feather';
import {Link} from 'react-router-dom';
import {Menu} from 'antd';
import {MailOutlined, UsergroupDeleteOutlined, SettingOutlined} from '@ant-design/icons';
import {Info} from "react-feather";
import axios from "axios";
import {BASE_URL} from "../../../config/config";

const {SubMenu} = Menu;

function SideBar(props) {


    const [user] = useState(JSON.parse(window.localStorage.getItem('user')))

    const [matieres, setMatieres] = useState([]);

    const getMatire = useCallback(() => {
        axios.get(`${BASE_URL}api/elementModule/prof/${user.id}`)
            .then(res => setMatieres(res.data))
    }, [user.id]);

    useEffect(() => {
        getMatire();
    }, [getMatire])

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
                            <SubMenu key="fl" icon={<SettingOutlined/>} title="Les matiéres ">
                                {
                                    matieres.map(m =>
                                        <Menu.Item key={m.id}>
                                            <Link to={{
                                                pathname: `/matiere/${m.id}`,
                                                state: m
                                            }}>
                                                {m.nomElement}
                                            </Link>
                                        </Menu.Item>
                                    )
                                }
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