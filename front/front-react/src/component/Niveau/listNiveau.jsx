import React, { useCallback, useEffect, useState } from 'react'
import { Table, Button, Popconfirm, message, } from 'antd';
import * as Icon from 'react-feather';

import axios from 'axios';
import { BASE_URL } from "../../config/config"

export default function ListNiveau() {

    const { Column } = Table;
    const [data, setData] = useState([]);
    const [pagination, setPagination] = useState({
        current: 1,
        pageSize: 3,
    });


    const getListNiveau = useCallback(
        () => {
            axios.get(`${BASE_URL}api/niveau/`)
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
    useEffect(() => {
        getListNiveau()
    }, [getListNiveau]);




    const deleteNiveau = useCallback(
        async (id) => {
            await axios.delete(`${BASE_URL}api/niveau/${id}`)
                .then(res => {
                    console.log(res);
                    if (res.status === 200) {
                        var newData = data.filter(item => item.id !== id);
                        setData(newData);
                        message.success("Filiere bien suprimer");
                    }else{
                        message.error("Error server ", res.data.message);
                    }
                });
        },
        [data],
    )


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
                                   List des Filiere
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
                                            rowKey={record => record.id}
                                        >
                                            <Column title="ID" dataIndex="id" key="id" />

                                            <Column
                                                title="Filiere"
                                                dataIndex="filiere"
                                                key="filiere"
                                                className="text-uppercase font-weight-bolder"
                                                render={record => <p>{record.nomFormation}</p>}
                                            />
                                            <Column
                                                title="Niveau"
                                                dataIndex="niveau"
                                                key="niveau"
                                            />

                                            <Column
                                                title="Action"
                                                key="action"
                                                render={(record) => (
                                                    <Popconfirm title="Sure to delete?"
                                                        onConfirm={() => deleteNiveau(record.id)}
                                                    >
                                                        <Button size="middle"
                                                            danger
                                                            type="primary"
                                                        >
                                                            <i className="fa fa-trash" aria-hidden="true"></i>
                                                        </Button>
                                                    </Popconfirm>
                                                )}
                                            />
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
