import React, { useState } from 'react'
import * as Icon from 'react-feather';
import { Form, Row, Col, Input, Button } from 'antd';
import { DownOutlined, UpOutlined } from '@ant-design/icons';

export default function AjouEtudiant() {
    const [form] = Form.useForm();

    const inputs = [
        {
            name: "email",
            label: "Email",
            rules: [
                { required: true, message: 'email required!!' },
                { type: 'email', message: 'Incorrect email!' }
            ]
        },
        {
            label: "Mot de passe",
            name: "password",
            rules: [{ required: true, message: 'mot de passe required !' }]
        },
        {
            name: "nom",
            label: "Nom",
            rules: [{ required: true, message: 'Nom required!!' },]
        },
        {
            name: "prenom",
            label: "Prenom",
            rules: [{ required: true, message: 'Prenom required!!' },]
        },
        {
            name: "cne",
            label: "CNE",
            rules: [{ required: true, message: 'cne required!!' },]
        },
        {
            name: "niveau",
            label: "Niveau",
            rules: [{ required: true, message: 'niveau required!!' },]
        },
        {
            name: "role",
            label: "Role",
            value: "ETUDIANT",
            rules: [{ required: true, message: 'Role required!!' },]
        },
    ];

    const getFields = () => {
        const children = [];
        for (let i = 0; i < inputs.length; i++) {
            children.push(
                inputs[i].name === "role" ?
                    (<Col span={12} key={i}>
                        <Form.Item
                            name={inputs[i].name}
                            label={inputs[i].label}
                            rules={inputs[i].rules}
                            initialValue="ETUDIANT"
                        >
                            <Input size="middle" className="form-control py-2" readOnly value="ETUDIANT" />
                        </Form.Item>
                    </Col>)
                    :
                    (<Col span={12} key={i}>
                        <Form.Item
                            name={inputs[i].name}
                            label={inputs[i].label}
                            rules={inputs[i].rules}
                        >
                            {
                                inputs[i].name === "password" ?
                                    <Input.Password size="middle" className="form-control py-2" /> :
                                    <Input size="middle" className="form-control py-2" />
                            }
                        </Form.Item>
                    </Col>)
            );
        }
        return children;
    };

    const onFinish = values => {
        console.log('Received values of form: ', values);
    };
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
                                   Ajouter un etudiant
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
                                            <Row gutter={24}>{getFields()}</Row>
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