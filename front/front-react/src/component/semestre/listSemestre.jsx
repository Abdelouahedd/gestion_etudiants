import React, { useCallback, useEffect, useState } from 'react'
import { Table, message, Tag } from 'antd';
import * as Icon from 'react-feather';
import axios from 'axios';
import { BASE_URL } from "../../config/config"

export default function ListSemestre() {

    const { Column } = Table;
    const [data, setData] = useState([]);
    const [pagination, setPagination] = useState({
        current: 1,
        pageSize: 3,
    });

    const getListFiliere = useCallback(
        () => {
            axios.get(`${BASE_URL}api/filiere/list`)
                .then(res => {
                    if (res.status === 200) {
                        setData(res.data);
                    } else {
                        message.error("Error server ", res.data.message);
                    }
                });
        },
        [],
    );

    useEffect(() => {
        getListFiliere();
    }, [getListFiliere]);

    

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
                               List des Semestres
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
                                        <Table
                                            dataSource={data}
                                            pagination={pagination}
                                            onChange={e => setPagination(e)}
                                            size="middle"
                                            key="id"
                                        >
                                            <Column title="ID" dataIndex="id" key="id" />

                                            <Column
                                                title="Filiere"
                                                dataIndex="nomFormation"
                                                key="nomFormation"
                                                className="text-uppercase font-weight-bolder"
                                            />
                                            <Column
                                                title="Niveau"
                                                dataIndex="niveaus"
                                                key="niveaus"
                                                render={(record => (
                                                    record.map(n =>
                                                        <Tag color="blue" key={n.id}>
                                                            {n.niveau.toUpperCase()}
                                                        </Tag>
                                                    )
                                                ))}
                                            />
                                            <Column
                                                title="Semestre"
                                                dataIndex="niveaus"
                                                key="semestres"
                                                render={
                                                    (record => (
                                                        record.map(
                                                            n => n.semestres.map(
                                                                s =>
                                                                    <Tag color="blue" key={s.id}>
                                                                        {s.semestre}
                                                                    </Tag>
                                                            )
                                                        )
                                                    )
                                                    )
                                                }
                                            />

                                            {/* <Column
                                                title="Action"
                                                key="action"
                                                render={(record) => (
                                                    <Popconfirm title="Sure to delete?"
                                                        onConfirm={() => deleteNiveau(record.key)}
                                                    >
                                                        <Button size="middle"
                                                            danger
                                                            type="primary"
                                                        >
                                                            <i className="fa fa-trash" aria-hidden="true"></i>
                                                        </Button>
                                                    </Popconfirm>
                                                )}
                                            /> */}
                                        </Table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </main>

        </div>
    )
}
