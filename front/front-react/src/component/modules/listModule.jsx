import React, { useState } from 'react'
import { Table, Button, Popconfirm } from 'antd';
import * as Icon from 'react-feather';

export default function ListModule() {
    const dataS = [
        {
            key: 1,
            id: 1,
            libelle: "Administration resaux et system",
            semestre: "1 er semestre",
            niveau: "3 eme anneé",
            filiere: "GI",
        },
        {
            key: 2,
            id: 2,
            libelle: "Genie logiciel",
            niveau: "3 eme anneé",
            filiere: "GI",
            semestre: "1 er semestre",
        }
    ]
    const { Column } = Table;
    const [data, setData] = useState(dataS);
    const [pagination, setPagination] = useState({
        current: 1,
        pageSize: 3,
    })


    const deleteNiveau = (key) => {
        var dataSource = [...dataS];
        dataSource = dataSource.filter((item) => item.key !== key);
        setData(dataSource);
    }
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
                                        <Table
                                            dataSource={data}
                                            pagination={pagination}
                                            onChange={e => setPagination(e)}
                                            size="middle"
                                        >
                                            <Column title="ID" dataIndex="id" key="id" />

                                            <Column
                                                title="Filiere"
                                                dataIndex="filiere"
                                                key="filiere"
                                                className="text-uppercase font-weight-bolder"
                                            />
                                            <Column
                                                title="Niveau"
                                                dataIndex="niveau"
                                                key="niveau"
                                            />
                                            <Column
                                                title="Semestre"
                                                dataIndex="semestre"
                                                key="semestre"
                                            />
                                            <Column
                                                title="Module"
                                                dataIndex="libelle"
                                                key="module"
                                            />

                                            <Column
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
