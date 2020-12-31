import React, { useCallback, useEffect } from 'react'
import * as Icon from 'react-feather';
import { Form, Row, Col, Input, Button, Select, message } from 'antd';
import axios from 'axios';
import { BASE_URL } from "../../config/config"

export default function AjouterSemestre() {
    const [form] = Form.useForm();

    const [filiere, setFiliere] = React.useState();
    const [niveau, setNiveau] = React.useState();

    const [niveaux, setNiveaux] = React.useState([]);
    const [filieres, setFilieres] = React.useState([]);

    const { Option } = Select;




    const onFinish = useCallback(
        (values) => {
            form.resetFields();
            setFiliere();
            setNiveau();
            axios({
                method: "POST",
                url: `${BASE_URL}api/semestre/`,
                data: JSON.stringify(values),
                headers: {
                    'Content-Type': 'application/json;charset=utf-8'
                }
            })
                .then(res => {
                    console.log(res);
                    if (res.status === 200) {
                        message.success("Semestre bien ajouter");
                    } else {
                        message.error("Error server ", res.data.message);
                    }
                });
        },
        [form]
    );


    const getListFiliere = useCallback(
        () => {
            axios.get(`${BASE_URL}api/filiere/list`)
                .then(res => {
                    if (res.status === 200) {
                        setFilieres(res.data);
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
                                        Ajouter Semestre
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
                                        <Form
                                            form={form}
                                            name="advanced_search"
                                            className="ant-advanced-search-form"
                                            onFinish={onFinish}
                                            layout="vertical"
                                        >
                                            <Col span={12} offset={6} key="0">
                                                <Form.Item
                                                    name="filiere"
                                                    label="Filiere"
                                                    rules={[{ required: true, message: 'Filiere required!!' },]}
                                                >
                                                    <Select
                                                        showSearch
                                                        placeholder="SELECT FILIERE"
                                                        optionFilterProp="children"
                                                        onChange={
                                                            (v) => {
                                                                setFiliere(v);
                                                                var selectedF = filieres.filter(f => f.id === v);
                                                                setNiveaux(selectedF[0].niveaus);
                                                            }}
                                                    >
                                                        {
                                                            filieres.map(fil =>
                                                                (<Option key={fil.id} value={fil.id}>{fil.nomFormation}</Option>)
                                                            )
                                                        }
                                                    </Select>
                                                </Form.Item>
                                            </Col>
                                            {
                                                filiere ?
                                                    <Col span={12} offset={6} key="1">
                                                        <Form.Item
                                                            name="niveau"
                                                            label="Niveau"
                                                            rules={[{ required: true, message: 'Niveau required!!' },]}
                                                        >
                                                            <Select
                                                                showSearch
                                                                placeholder="SELECT Niveau"
                                                                optionFilterProp="children"
                                                                onChange={(v) => setNiveau(v)}
                                                            >
                                                                {
                                                                    niveaux.map(n =>
                                                                        (<Option key={n.id} value={n.id}>{n.niveau}</Option>)
                                                                    )
                                                                }
                                                            </Select>
                                                        </Form.Item>
                                                    </Col>
                                                    : null
                                            }
                                            {
                                                niveau ? (<Col span={12} offset={6} key="2">
                                                    <Form.Item
                                                        name="semestre"
                                                        label="Semestre"
                                                        rules={[{ required: true, message: 'Semestre required!!' },]}
                                                    >
                                                        <Input size="middle" placeholder="1 er semestre" className="form-control py-2" />
                                                    </Form.Item>
                                                </Col>)
                                                    : null
                                            }


                                            <Row>
                                                <Col span={24} style={{ textAlign: 'right' }}>
                                                    <Button type="primary" htmlType="submit">
                                                        Ajouter
                                                    </Button>
                                                    <Button
                                                        style={{ margin: '0 8px' }}
                                                        onClick={() => form.resetFields()}>
                                                        Reset
                                                    </Button>
                                                </Col>
                                            </Row>
                                        </Form>
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
