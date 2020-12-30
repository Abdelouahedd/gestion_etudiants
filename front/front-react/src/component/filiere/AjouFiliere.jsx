import React, { useCallback, useState } from 'react'
import * as Icon from 'react-feather';
import { Form, Row, Upload, message, Col, Input, Button } from 'antd';
import { InboxOutlined } from '@ant-design/icons';
import axios from 'axios';
import { BASE_URL } from "../../config/config"
const { Dragger } = Upload;




export default function AjouFiliere() {
    const [form] = Form.useForm();

    const [file, setfile] = useState();

    const props = {
        name: 'description',
        multiple: false,
        onChange(info) {
            console.log(info.file);
            setfile(info.file)

        },
        beforeUpload: f => {
            setfile(f);
            return false;
        },
        file,
    };

    const onFinish = useCallback(
        async (values) => {
            var formData = new FormData();
            values.description.fileList.forEach(f => formData.append('description', f.originFileObj));
            formData.append("nomFormation", values.nomFormation);
            await axios.post(`${BASE_URL}api/filiere/`, formData, {
                headers: {
                    "Content-Type": "multipart/form-data",
                },
            }).then(res => {
                console.log(res);
                if (res.data.status === 403) {
                    message.error('ERROR lors d\'evoie du fichier');
                } else {    
                    message.success("Filiere" + values.nomFormation + "bien ajouter");
                }
            }).catch(err => {
                console.log(err);
                message.error('ERROR lors d\'evoie du fichier');
            });
        },
        [],
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
                                            {/* <i className="fa fa-user text-white-50" aria-hidden="true"></i> */}
                                            <Icon.Users className="feather-xl text-white-50" />
                                        </div>
                               Ajouter filiere
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
                                            {/* <Row gutter={24}> */}
                                            <Col span={12} offset={6} key="1">
                                                <Form.Item
                                                    name="nomFormation"
                                                    label="Nom du formation"
                                                    rules={[{ required: true, message: 'Nom du formation required!!' },]}
                                                >
                                                    <Input size="middle" className="form-control py-2" />
                                                </Form.Item>
                                            </Col>
                                            <Col span={12} offset={6} key="2">
                                                <Form.Item
                                                    name="description"
                                                    label="Description du formation"
                                                    rules={[{ required: true, message: 'Description required!!' },]}
                                                >
                                                    <Dragger  {...props} >
                                                        <p className="ant-upload-drag-icon">
                                                            <InboxOutlined />
                                                        </p>
                                                        <p className="ant-upload-text">Clicker ou bien glisser votre fichier </p>
                                                    </Dragger>
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
