import React, { useCallback, useEffect, useState, } from 'react'
import * as Icon from 'react-feather';
import { Form, Row, Col, Input, Button, Select, message } from 'antd';
import axios from 'axios';
import { BASE_URL } from "../../config/config"

export default function AjouterNiveau() {

    const { Option } = Select;

    const [form] = Form.useForm();

    const [filieres, setFilieres] = useState([]);

    const onFinish = useCallback(
        (values) => {
            axios({
                method: "POST",
                url: `${BASE_URL}api/niveau/`,
                data: JSON.stringify(values),
                headers: {
                    'Content-Type': 'application/json;charset=utf-8'
                }
            })
                .then(res => {
                    console.log(res);
                    if (res.status === 200) {
                        message.success("Niveau bien ajouter");
                    } else {
                        message.error("Error server ", res.data.message);
                    }
                });
        },
        []
    )

    const getListFiliere = useCallback(
        () => {
            axios.get(`${BASE_URL}api/filiere/list`)
                .then(res => {
                    if (res.status === 200) {
                        setFilieres(res.data);
                    }
                    message.error("Error server ", res.data.message);
                });
        },
        [],
    )
    useEffect(() => {
        getListFiliere()
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
                                            {/* <i className="fa fa-user text-white-50" aria-hidden="true"></i> */}
                                            <Icon.Users className="feather-xl text-white-50" />
                                        </div>
                           Ajouter Niveau
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
                                            <Col span={12} offset={6} key="1">
                                                <Form.Item
                                                    name="niveau"
                                                    label="Niveau"
                                                    rules={[{ required: true, message: 'Niveau required!!' },]}
                                                >
                                                    <Input size="middle" placeholder="1 er année" className="form-control py-2" />
                                                </Form.Item>
                                            </Col>
                                            <Col span={12} offset={6} key="2">
                                                <Form.Item
                                                    name="filiere"
                                                    label="Filiere"
                                                    rules={[{ required: true, message: 'Filiere required!!' },]}
                                                >
                                                    <Select
                                                        showSearch
                                                        placeholder="SELECT FILIERE"
                                                        optionFilterProp="children"
                                                    >
                                                        {filieres.map(f => <Option key={f.id} value={f.id}>{f.nomFormation}</Option>)}
                                                    </Select>
                                                </Form.Item>
                                            </Col>
                                            <Row>
                                                <Col span={24} style={{ textAlign: 'right' }}>
                                                    <Button type="primary" htmlType="submit">
                                                        Ajouter
                                            </Button>
                                                    <Button
                                                        style={{ margin: '0 8px' }}
                                                        onClick={() => {
                                                            form.resetFields();
                                                        }}
                                                    >
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
