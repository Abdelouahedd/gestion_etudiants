import React from 'react'
import * as Icon from 'react-feather';
import { Form, Row, Col, Input, Button, Select } from 'antd';

export default function AjouterNiveau() {

    const [form] = Form.useForm();
    const { Option } = Select;

    const onFinish = values => {
        console.log('Received values of form: ', values);
    };
    function onChange(value) {
        console.log(`selected ${value}`);
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
                                                    name="Niveau"
                                                    label="Niveau"
                                                    rules={[{ required: true, message: 'Niveau required!!' },]}
                                                >
                                                    <Input size="middle" placeholder="1 er année" className="form-control py-2" />
                                                </Form.Item>
                                            </Col>
                                            <Col span={12} offset={6} key="2">
                                                <Form.Item
                                                    name="Filiere"
                                                    label="Filiere"
                                                    rules={[{ required: true, message: 'Filiere required!!' },]}
                                                >
                                                    <Select
                                                        showSearch
                                                        placeholder="SELECT FILIERE"
                                                        optionFilterProp="children"
                                                        onChange={onChange}
                                                    >
                                                        <Option value="1">GI</Option>
                                                        <Option value="2">GII</Option>
                                                        <Option value="3">BIG DATA</Option>
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
