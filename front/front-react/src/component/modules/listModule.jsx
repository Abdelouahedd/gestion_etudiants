import React, { useState, useCallback, useEffect } from 'react'
import {  message } from 'antd';
import {  Menu } from 'antd';
import { SwapRightOutlined, MenuUnfoldOutlined, SendOutlined, CaretRightOutlined } from '@ant-design/icons';
import * as Icon from 'react-feather';
import axios from 'axios';
import { BASE_URL } from "../../config/config"

const { SubMenu } = Menu;

export default function ListModule() {

    const getListNiveau = useCallback(
        () => {
            axios.get(`${BASE_URL}api/filiere/list`)
                .then(res => {
                    if (res.status === 200) {
                        message.success("Data retrieved");
                        setData(res.data);
                    } else {
                        message.error("Error server ", res.data.message);
                    }
                });
        },
        [],
    )

    
    const [data, setData] = useState([]);


    useEffect(() => {
        getListNiveau()
    }, [getListNiveau]);



    return (
        <div id="layoutSidenav_content">
            <main>
                <header className="page-header page-header-dark bg-gray-500">
                    <div className="container">
                        <div className="page-header-content pt-4">
                            <div className="row align-items-center justify-content-center">
                                <div className="col-auto mt-4">
                                    <h1 className="page-header-title">
                                        <div className="page-header-icon">
                                            <Icon.Users className="feather-xl text-white-50" />
                                        </div>
                           List des Modules
                        </h1>
                                </div>
                            </div>
                        </div>
                    </div>
                </header>
                <div className="container mt-5">
                    <div className="row">
                        <div className="col-xxl-12 col-xl-12">
                            <div className="card mb-4">
                                <div className="card-body">
                                    <div className="datatable">
                                        {
                                            data.map(el =>

                                                <Menu
                                                    key={el.id}
                                                    mode="inline"
                                                    style={{ height: '100%', borderRight: 0 }}
                                                    title={el.nomFormation}
                                                >
                                                    <SubMenu
                                                        key={el.id}
                                                        icon={<MenuUnfoldOutlined />}
                                                        title={el.niveau}
                                                    >
                                                        {
                                                            el.niveaus.map(niv =>
                                                                <SubMenu
                                                                    key={niv.id}
                                                                    icon={<SendOutlined />}
                                                                    title={niv.niveau}
                                                                >
                                                                    {
                                                                        niv.semestres.map(sem =>
                                                                            <SubMenu
                                                                                key={sem.id}
                                                                                icon={<CaretRightOutlined />}
                                                                                title={sem.semestre}
                                                                            >
                                                                                {
                                                                                    sem.lmodules.map(mod =>
                                                                                        <Menu.Item key={mod.id}><SwapRightOutlined />{mod.libelle}</Menu.Item>
                                                                                    )
                                                                                }
                                                                            </SubMenu>
                                                                        )
                                                                    }
                                                                </SubMenu>
                                                            )
                                                        }
                                                    </SubMenu>
                                                </Menu>
                                            )
                                        }
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </main>

        </div >
    )
}
