import React, { useCallback, useEffect } from 'react'
import * as Icon from 'react-feather';
import { Form, Row, Col, Input, Button, Select, message } from 'antd';
import axios from 'axios';
import { BASE_URL } from "../../config/config"

export default function AjouterModule() {
    const [form] = Form.useForm();

    const [filieres, setFilieres] = React.useState([]);
    const [niveaux, setNiveaux] = React.useState([]);
    const [semestres, setSemestres] = React.useState([]);

    const [filiere, setFiliere] = React.useState();
    const [niveau, setNiveau] = React.useState();
    const [semestre, setSemestre] = React.useState();

    const { Option } = Select;


    const onFinish = useCallback(
        (values) => {
            resetForm();
            const module = {
                semestre: semestre,
                libelle: values.libelle
            }
            console.log("Module ", module);
            axios({
                method: "POST",
                url: `${BASE_URL}api/module/`,
                data: JSON.stringify(module),
                headers: {
                    'Content-Type': 'application/json;charset=utf-8'
                }
            })
                .then(res => {
                    console.log(res);
                    if (res.status === 200) {
                        message.success("Module bien ajouter");
                    } else {
                        message.error("Error server ", res.data.message);
                    }
                });
        },
        [resetForm, semestre]
    )


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
    )
    useEffect(() => {
        console.log("Use Effect run ");
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
                                            <Icon.Users className="feather-xl text-white-50" />
                                        </div>
                                    Ajouter Module
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
                                                        onChange={(v) => {
                                                            setFiliere(v);
                                                            const selecteF = filieres.filter(f => f.id === v);
                                                            setNiveaux(selecteF[0].niveaus);
                                                        }
                                                        }
                                                    >
                                                        {
                                                            filieres.map(
                                                                f =>
                                                                    (<Option key={f.id} value={f.id}>{f.nomFormation}</Option>)
                                                            )
                                                        }
                                                    </Select>
                                                </Form.Item>
                                            </Col>
                                            {
                                                filiere ?
                                                    <Col span={12} offset={6} key="1">
                                                        <Form.Item
                                                            name="Niveau"
                                                            label="Niveau"
                                                            rules={[{ required: true, message: 'Niveau required!!' },]}
                                                        >
                                                            <Select
                                                                showSearch
                                                                placeholder="SELECT Niveau"
                                                                optionFilterProp="children"
                                                                onChange={(v) => {
                                                                    setNiveau(v);
                                                                    const selecteF = filieres.filter(f => f.id === filiere);
                                                                    const selecteN = selecteF[0].niveaus.filter(
                                                                        n => (n.id === v)
                                                                    );
                                                                    setSemestres(selecteN[0].semestres)
                                                                }
                                                                }
                                                            >
                                                                {
                                                                    niveaux.map(
                                                                        f =>
                                                                            (<Option key={f.id} value={f.id}>{f.niveau}</Option>)
                                                                    )
                                                                }
                                                            </Select>
                                                        </Form.Item>
                                                    </Col>
                                                    : null
                                            }
                                            {
                                                niveau ?
                                                    <Col span={12} offset={6} key="3">
                                                        <Form.Item
                                                            name="Semestre"
                                                            label="semestre"
                                                            rules={[{ required: true, message: 'Semestre required!!' },]}
                                                        >
                                                            <Select
                                                                showSearch
                                                                placeholder="SELECT Semestre"
                                                                optionFilterProp="children"
                                                                onChange={(v) => setSemestre(v)}
                                                            >
                                                                {
                                                                    semestres.map(
                                                                        f =>
                                                                            (<Option key={f.id} value={f.id}>{f.semestre}</Option>)
                                                                    )
                                                                }
                                                            </Select>
                                                        </Form.Item>
                                                    </Col>
                                                    : null
                                            }
                                            {
                                                semestre ? (<Col span={12} offset={6} key="2">
                                                    <Form.Item
                                                        name="libelle"
                                                        label="Module"
                                                        rules={[{ required: true, message: 'Module required!!' },]}
                                                    >
                                                        <Input size="middle" placeholder="Génie logiciel" className="form-control py-2" />
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

    function resetForm() {
        form.resetFields();
        setFiliere();
        setNiveau();
        setSemestre();
    }
}
