import React, {useCallback, useEffect} from 'react'
import * as Icon from 'react-feather';
import {Form, Row, Col, Input, Button, Select, message} from 'antd';
import axios from 'axios';
import {BASE_URL} from "../../config/config"

const {Option} = Select;

export default function AjouterElModule() {

    const [form] = Form.useForm();

    const [filieres, setFilieres] = React.useState([]);
    const [niveaux, setNiveaux] = React.useState([]);
    const [semestres, setSemestres] = React.useState([]);
    const [modules, setModules] = React.useState();
    const [proffesseurs, setProffesseurs] = React.useState();


    const [filiere, setFiliere] = React.useState();
    const [niveau, setNiveau] = React.useState();
    const [module, setModule] = React.useState();
    const [semestre, setSemestre] = React.useState();
    const [proffesseur, setProffesseur] = React.useState();

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
    const getListProfesseur = useCallback(
        () => {
            axios.get(`${BASE_URL}api/users/prof/listProf`)
                .then(res => {
                    console.log(res.data);
                    if (res.status === 200) {
                        setProffesseurs(res.data);
                    } else {
                        message.error("Error server ", res.data.message);
                    }
                });
        },
        [],
    );

    useEffect(() => {
        getListFiliere();
        getListProfesseur();
    }, [getListFiliere, getListProfesseur]);


    const addElemntMdule = useCallback(
        (elemnt) => {
            axios({
                method: "POST",
                url: `${BASE_URL}api/elementModule`,
                data: JSON.stringify(elemnt),
                headers: {
                    'Content-Type': 'application/json;charset=utf-8'
                }
            })
                .then(res => {
                    console.log(res.data);
                    if (res.status === 200) {
                        message.success("Element Added");
                    } else {
                        message.error("Error server ", res.data.message);
                    }
                });
        },
        [],
    )


    const onFinish = async (values) => {
        form.resetFields();
        setModule();
        setSemestre();
        setNiveau();
        setFiliere();
        setProffesseur();
        console.log('Received values of form: ', values);
        await addElemntMdule(values);
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
                                        Ajouter Element de module
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
                                                    rules={[{required: true, message: 'Filiere required!!'},]}
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
                                                                    (<Option key={f.id}
                                                                             value={f.id}>{f.nomFormation}</Option>)
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
                                                            rules={[{required: true, message: 'Niveau required!!'},]}
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
                                                                            (<Option key={f.id}
                                                                                     value={f.id}>{f.niveau}</Option>)
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
                                                            name="semestre"
                                                            label="semestre"
                                                            rules={[{required: true, message: 'Semestre required!!'},]}
                                                        >
                                                            <Select
                                                                showSearch
                                                                placeholder="SELECT Semestre"
                                                                optionFilterProp="children"
                                                                onChange={(v) => {
                                                                    setSemestre(v);
                                                                    const selecteF = filieres.filter(f => f.id === filiere);
                                                                    const selecteN = selecteF[0].niveaus.filter(n => (n.id === niveau));
                                                                    const selectedS = selecteN[0].semestres.filter(s => s.id === v);
                                                                    setModules(selectedS[0].lmodules);
                                                                }
                                                                }
                                                            >
                                                                {
                                                                    semestres.map(
                                                                        f =>
                                                                            (<Option key={f.id}
                                                                                     value={f.id}>{f.semestre}</Option>)
                                                                    )
                                                                }
                                                            </Select>
                                                        </Form.Item>
                                                    </Col>
                                                    : null
                                            }
                                            {
                                                semestre ?
                                                    <Col span={12} offset={6} key="3">
                                                        <Form.Item
                                                            name="module"
                                                            label="Module"
                                                            rules={[{required: true, message: 'Module required!!'},]}
                                                        >
                                                            <Select
                                                                showSearch
                                                                placeholder="SELECT Module"
                                                                optionFilterProp="children"
                                                                onChange={(v) => setModule(v)}
                                                            >
                                                                {
                                                                    modules.map(
                                                                        f =>
                                                                            (<Option key={f.id}
                                                                                     value={f.id}>{f.libelle}</Option>)
                                                                    )
                                                                }
                                                            </Select>
                                                        </Form.Item>
                                                    </Col>
                                                    : null
                                            }
                                            {
                                                module ?
                                                    <Col span={12} offset={6} key="0">
                                                        <Form.Item
                                                            name="prof"
                                                            label="professeur"
                                                            rules={[{required: true, message: 'Prof required!!'},]}
                                                        >
                                                            <Select
                                                                showSearch
                                                                placeholder="SELECT Prof"
                                                                optionFilterProp="children"
                                                                onChange={(v) => setProffesseur(v)
                                                                }
                                                            >
                                                                {
                                                                    proffesseurs.map(
                                                                        f =>
                                                                            (<Option key={f.cin}
                                                                                     value={f.cin}>{f.nom}</Option>)
                                                                    )
                                                                }
                                                            </Select>
                                                        </Form.Item>
                                                    </Col> : null

                                            }
                                            {
                                                proffesseur ? (
                                                        <Col span={12} offset={6} key="2">
                                                            <Form.Item
                                                                name="nomElement"
                                                                label="Element Module"
                                                                rules={[{
                                                                    required: true,
                                                                    message: 'Element Module required!!'
                                                                },]}
                                                            >
                                                                <Input size="middle" placeholder="Génie logiciel"
                                                                       className="form-control py-2"/>
                                                            </Form.Item>
                                                        </Col>)
                                                    : null
                                            }
                                            <Row>
                                                <Col span={24} style={{textAlign: 'right'}}>
                                                    <Button type="primary" htmlType="submit">
                                                        Ajouter
                                                    </Button>
                                                    <Button
                                                        style={{margin: '0 8px'}}
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
