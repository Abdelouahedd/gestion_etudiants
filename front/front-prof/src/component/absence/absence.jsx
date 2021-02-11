import React, {useState, useRef, useCallback, useEffect} from 'react'
import {Table, Space, Button, Popconfirm, Input, message, Row, Col} from 'antd';
import * as Icon from 'react-feather';
import Highlighter from 'react-highlight-words';
import {SearchOutlined} from '@ant-design/icons';
import axios from 'axios';
import {BASE_URL} from '../../config/config';
import {useParams} from "react-router";



const {Column} = Table;

function Absence(props) {


    const searchInput = useRef();
    const [searchText, setSearchText] = useState("");
    const [searchedColumn, setSearchedColumn] = useState("");
    const getColumnSearchProps = dataIndex => ({

        filterDropdown: ({setSelectedKeys, selectedKeys, confirm, clearFilters}) => (
            <div style={{padding: 8}}>
                <Input
                    ref={searchInput}
                    placeholder={`Search ${dataIndex}`}
                    value={selectedKeys[0]}
                    onChange={e => setSelectedKeys(e.target.value ? [e.target.value] : [])}
                    onPressEnter={() => handleSearch(selectedKeys, confirm, dataIndex)}
                    style={{width: 188, marginBottom: 8, display: 'block'}}
                />
                <Space>
                    <Button
                        type="primary"
                        onClick={() => handleSearch(selectedKeys, confirm, dataIndex)}
                        icon={<SearchOutlined/>}
                        size="small"
                        style={{width: 90}}
                    >
                        Search
                    </Button>
                    <Button onClick={() => handleReset(clearFilters)} size="small" style={{width: 90}}>
                        Reset
                    </Button>
                </Space>
            </div>
        ),
        filterIcon: filtered => <SearchOutlined style={{color: filtered ? '#1890ff' : undefined}}/>,
        onFilter: (value, record) =>
            record[dataIndex]
                ? record[dataIndex].toString().toLowerCase().includes(value.toLowerCase())
                : '',
        onFilterDropdownVisibleChange: visible => {
            if (visible) {
                setTimeout(() => searchInput.current.select(), 100);
            }
        },
        return: text =>
            searchedColumn === dataIndex ?
                (<Highlighter
                    highlightStyle={{backgroundColor: '#ffc069', padding: 0}}
                    searchWords={[searchText]}
                    autoEscape
                    textToHighlight={text ? text.toString() : ''}
                />)
                :
                (text),
    });

    const handleSearch = (selectedKeys, confirm, dataIndex) => {
        confirm();
        setSearchText(selectedKeys[0]);
        setSearchedColumn(dataIndex)
    };

    const handleReset = clearFilters => {
        clearFilters();
        setSearchText("")
    };


    const {id} = useParams();
    const [etudiants, setEtudiants] = useState([]);

    const addAbsence = async (idEt) => {
        const data = {
            idEtudiant: idEt,
            idElementModule: id,
            seance: 1
        }
        console.log("data to send ---> ", data);
        await axios({
            method: "POST",
            url: `${BASE_URL}api/absence/`,
            data: JSON.stringify(data),
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            }
        }).then(res => {
            if (res.status === 200)
                message.success("Absence est ajouter pour cette seance")
        }).catch((err) => {
            console.error(err)
        })
    }

    const getStuent = useCallback(async () => {
        axios.get(`${BASE_URL}api/users/etudiants/${id}`)
            .then(res => setEtudiants(res.data))
            .catch((err) => message.error(err.message))
    }, [id]);

    useEffect(() => {
        getStuent();
    }, [getStuent])


    const [pagination, setPagination] = useState({
        current: 1,
        pageSize: 8,
    })

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
                                        Gestion d'absence
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
                                            dataSource={etudiants}
                                            pagination={pagination}
                                            onChange={e => setPagination(e)}
                                            size="middle"
                                        >
                                            <Column title="ID" dataIndex="id" key="id"/>
                                            <Column
                                                title="Nom"
                                                dataIndex="nom"
                                                key="nom"
                                                {...getColumnSearchProps('nom')}

                                            />
                                            <Column
                                                title="Prenom"
                                                dataIndex="prenom"
                                                key="prenom"
                                                {...getColumnSearchProps('prenom')}
                                            />
                                            <Column
                                                title="Cne"
                                                dataIndex="cne"
                                                key="cne"
                                                {...getColumnSearchProps('cne')}
                                            />
                                            <Column
                                                title="Action"
                                                key="action"
                                                render={(text, record) => (
                                                    <Row gutter={8}>
                                                        <Col span={6}>
                                                            <Popconfirm title="Sure to delete?"
                                                                        onConfirm={() => addAbsence(record.id)}>
                                                                <button size="middle"
                                                                        className="btn btn-success btn-icon btn-xs">
                                                                    <i className="fa fa-plus"
                                                                       aria-hidden="true"/>
                                                                </button>
                                                            </Popconfirm>
                                                        </Col>
                                                        <Col span={6}>
                                                            {/* <Button size="middle" danger type="primary"
                                                                    icon={<i className="fa fa-times-circle"
                                                                             aria-hidden="true"/>}/>*/}
                                                        </Col>
                                                    </Row>

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
    );
}

export default Absence;