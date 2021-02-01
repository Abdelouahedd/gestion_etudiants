import React, {useState, useRef, useCallback, useEffect} from 'react'
import {Table, Tag, Space, Button, Popconfirm, Input, message} from 'antd';
import * as Icon from 'react-feather';
import Highlighter from 'react-highlight-words';
import {SearchOutlined} from '@ant-design/icons';
import axios from "axios";
import {BASE_URL} from "../../config/config";

export default function ListProfesseurs() {

    const searchInput = useRef();

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


    const {Column} = Table;

    const dataS = [
        {

            id: '1',
            nom: 'Ennouri',
            prenom: 'ABDELOUAHED',
            email: 'abdelouahed@gmail.com',
            Cin: "G1ekjef955",
            tags: 'PROF',
        },
        {

            id: '2',
            nom: 'Ahmed',
            prenom: 'mohamed',
            email: 'ahmed@gmail.com',
            Cin: "G13ddd22dm55",
            tags: 'PROF',
        },
        {

            id: '3',
            nom: 'Abdo',
            prenom: 'ABDELOUAHED',
            email: 'ennouri@gmail.com',
            Cin: "kddddd",
            tags: 'PROF',
        }
    ];

    const [data, setData] = useState(dataS);
    const [searchText, setSearchText] = useState("");
    const [searchedColumn, setSearchedColumn] = useState("");
    const [pagination, setPagination] = useState({
        current: 1,
        pageSize: 4,
    })

    const deleteEtudiant = (key) => {
        var dataSource = [...dataS];
        dataSource = dataSource.filter((item) => item.key !== key);
        setData(dataSource);
    }

    const getListProfesseur = useCallback(
        () => {
            axios.get(`${BASE_URL}api/users/prof/listProf`)
                .then(res => {
                    console.log(res.data);
                    if (res.status === 200) {
                        message.success("Data retrieved");
                         setData(res.data);
                    } else {
                        message.error("Error server ", res.data.message);
                    }
                });
        },
        [],
    );

    useEffect(() => {
        getListProfesseur();
    }, [getListProfesseur]);


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
                                            <Icon.Users className="feather-xl text-white-50"/>
                                        </div>
                                        List des professeurs
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
                                            dataSource={data}
                                            pagination={pagination}
                                            onChange={e => setPagination(e)}
                                            size="middle"
                                            rowKey={record => record.id}
                                        >
                                            <Column
                                                title="ID"
                                                dataIndex="id"
                                                key="id"
                                            />
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
                                                title="Email"
                                                dataIndex="email"
                                                key="email"
                                                {...getColumnSearchProps('email')}
                                            />
                                            <Column
                                                title="Cin"
                                                dataIndex="cin"
                                                key="cin"
                                                {...getColumnSearchProps('cin')}
                                            />
                                            <Column
                                                title="Tags"
                                                dataIndex="role"
                                                key="rome"
                                                render={tag => (
                                                    <Tag color="green" key={tag}>
                                                        {tag}
                                                    </Tag>
                                                )}
                                            />

                                            <Column
                                                title="Action"
                                                key="action"
                                                render={(text, record) => (
                                                    <Popconfirm title="Sure to delete?"
                                                                onConfirm={() => deleteEtudiant(record.id)}
                                                    >
                                                        <Button size="middle"
                                                                danger
                                                                type="primary"
                                                        >
                                                            <i className="fa fa-trash" aria-hidden="true"></i>
                                                        </Button>
                                                    </Popconfirm>
                                                )}
                                            />
                                        </Table>,
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
