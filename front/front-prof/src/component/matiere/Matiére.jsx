import React, {useEffect, useState, useCallback} from 'react';
import {useLocation, useParams} from "react-router";
import * as Icon from 'react-feather';
import {List, Card, Form, Row, Upload, message, Col, Input, Button} from 'antd';
import {InboxOutlined,FilePdfOutlined} from '@ant-design/icons';
import axios from 'axios';
import {BASE_URL} from "../../config/config"


const Matiére = () => {

    const {Dragger} = Upload;

    const [form] = Form.useForm();
    const {id} = useParams();
    let location = useLocation();

    const [file, setfile] = useState();
    const [data, setData] = useState([]);
    const [gridConfig] = useState({
        gutter: 16,
        xs: 1,
        sm: 2,
        md: 4,
        lg: 4,
        xl: 6,
        xxl: 3,
    });

    useEffect(() => {
        console.log(location.state.cours)
        setData(location.state.cours);
    }, [location.state]);

    const props = {
        name: 'contenue',
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
            console.log("values --> ",values);
            const formData = new FormData();
            values.contenue.fileList.forEach(f => formData.append('contenue', f.originFileObj));
            formData.append("titreCour", values.titreCour);
            formData.append("nbrHeure", values.nbrHeure);
            formData.append("elementId", location.state.id);
            console.log("Formadata ---> ",formData);
            for (var key of formData.entries()) {
                console.log(key[0] + ', ' + key[1]);
            }
            await axios.post(`${BASE_URL}api/cour/`, formData, {
                headers: {
                    "Content-Type": "multipart/form-data",
                },
            }).then(res => {
                console.log(res);
                if (res.data.status === 403) {
                    message.error('ERROR lors d\'evoie du fichier');
                } else {
                    setData([...data, res.data]);
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
                                            <Icon.Users className="feather-xl text-white-50"/>
                                        </div>
                                        List des cours
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
                                                    name="titreCour"
                                                    label="Titre cour"
                                                    rules={[{required: true, message: 'Titre cour required!!'},]}
                                                >
                                                    <Input size="middle" className="form-control py-2"/>
                                                </Form.Item>
                                            </Col>
                                            <Col span={12} offset={6} key="0">
                                                <Form.Item
                                                    name="nbrHeure"
                                                    label="Nombre heure"
                                                    rules={[{required: true, message: 'nbrHeure required!!'},]}
                                                >
                                                    <Input type="number" size="middle" className="form-control py-2"
                                                           value={1}/>
                                                </Form.Item>
                                            </Col>
                                            <Col span={12} offset={6} key="2">
                                                <Form.Item
                                                    name="contenue"
                                                    label="contenue du formation"
                                                    rules={[{required: true, message: 'contenue required!!'},]}
                                                >
                                                    <Dragger  {...props} >
                                                        <p className="ant-upload-drag-icon">
                                                            <InboxOutlined/>
                                                        </p>
                                                        <p className="ant-upload-text">Clicker ou bien glisser votre
                                                            fichier </p>
                                                    </Dragger>
                                                </Form.Item>
                                            </Col>
                                            <Row>
                                                <Col span={24} style={{textAlign: 'right'}}>
                                                    <Button type="primary" htmlType="submit">
                                                        Ajouter
                                                    </Button>
                                                    <Button
                                                        style={{margin: '0 8px'}}
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
                    <div className="row">
                        <div className="col-xxl-12 col-xl-12">
                            <div className="card mb-4">
                                <div className="card-body">
                                    <div className="datatable">
                                        <List
                                            grid={gridConfig}
                                            dataSource={data}
                                            renderItem={item => (
                                                <List.Item>
                                                    <Card title={item?.titreCour}>
                                                        <FilePdfOutlined />  <a href={item?.contenue}>{item?.contenue}</a>
                                                    </Card>
                                                </List.Item>
                                            )}
                                        />
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

export default Matiére;