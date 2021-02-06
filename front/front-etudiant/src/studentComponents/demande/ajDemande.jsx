import React, {useCallback, useEffect, useState} from 'react';
import * as Icon from "react-feather";
import {Button, Col, Form, Input, message, Row} from "antd";
import axios from "axios";
import {BASE_URL} from "../../config/config";


const {TextArea} = Input;

const AjDemande = () => {

    const [form] = Form.useForm();
    const [id, setId] = useState("");

    const getId = useCallback(async () => {
        let user = await JSON.parse(window.localStorage.getItem('user'));
        setId(user?.id);
    }, []);

    useEffect(() => {
        getId();
    }, [getId]);

    const sendService = useCallback(async (service) => {
        axios({
            url: `${BASE_URL}api/services`,
            method: "POST",
            data: JSON.stringify(service),
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            }
        }).then(({status}) => {
            status === 200 ?
                message.success("Serviec est envoyé") :
                message.error("Error lors d'envoie du demande");
        })
    }, []);


    const onFinish = async (values) => {
        form.resetFields();
        console.log('Received values of form: ', values);
        const service = {
            idEtudiant: id,
            contenue: values.contenue
        }
        await sendService(service);
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
                                            <Icon.Users className="feather-xl text-white-50"/>
                                        </div>
                                        Demander un service
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
                                                    name="contenue"
                                                    label="Contenue"
                                                    rules={[{required: true, message: 'Contenue required!!'},]}
                                                >
                                                    <TextArea autoSize={{minRows: 8, maxRows: 40}}/>
                                                </Form.Item>
                                                <Row>
                                                    <Col span={24} style={{textAlign: 'right'}}>
                                                        <Button type="primary" htmlType="submit">
                                                            Envoyer
                                                        </Button>
                                                    </Col>
                                                </Row>
                                            </Col>
                                        </Form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </main>

        </div>
    );
};

export default AjDemande;