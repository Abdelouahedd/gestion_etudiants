import React, { useCallback, useEffect, useState } from 'react'
import { Table, message, Tag, } from 'antd';
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
            axios.get(`${BASE_URL}api/filiere/list`)
                .then(res => {
                    console.log("List filiere ");
                    console.log(res.data);
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
                    } else {
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
                                            rowKey="id"
                                        >
                                            <Column
                                                title="ID"
                                                dataIndex="id"
                                                key="id"
                                                width={200}
                                            />
                                            <Column
                                                title="Filiere"
                                                dataIndex="nomFormation"
                                                key="nomFormation"
                                                className="text-uppercase font-weight-bolder"
                                                width={550}
                                                render={record => <p>{(record)}</p>}
                                            />
                                            <Column
                                                title="Niveau"
                                                dataIndex="niveaus"
                                                key="niveau"
                                                width="250"
                                                render={(record => (
                                                    record.map(n =>
                                                        <Tag color="blue" key={n.id}>
                                                            {n.niveau.toUpperCase()}
                                                        </Tag>
                                                    )
                                                ))}
                                            />

                                            {/* <Column
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
