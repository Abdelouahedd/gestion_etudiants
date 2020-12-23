import React from 'react'
import * as Icon from 'react-feather';
import { Form, Row, Upload, message, Col, Input, Button } from 'antd';
import { UploadOutlined, InboxOutlined } from '@ant-design/icons';

const { Dragger } = Upload;


const props = {
    name: 'description',
    multiple: false,
    // action: 'https://www.mocky.io/v2/5cc8019d300000980a055e76',
    onChange(info) {
        const { status } = info.file;
        if (status !== 'uploading') {
            console.log(info.file, info.fileList);
        }
        if (status === 'done') {
            message.success(`${info.file.name} file uploaded successfully.`);
        } else if (status === 'error') {
            message.error(`${info.file.name} file upload failed.`);
        }
    },
};

export default function AjouFiliere() {
    const [form] = Form.useForm();

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
                                                    <Dragger  {...props}>
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

class AliyunOSSUpload extends React.Component {
    state = {
        OSSData: {},
    };

    async componentDidMount() {
        await this.init();
    }

    init = async () => {
        try {
            const OSSData = await this.mockGetOSSData();

            this.setState({
                OSSData,
            });
        } catch (error) {
            message.error(error);
        }
    };

    // Mock get OSS api
    // https://help.aliyun.com/document_detail/31988.html
    mockGetOSSData = () => ({
        dir: 'user-dir/',
        expire: '1577811661',
        host: '//www.mocky.io/v2/5cc8019d300000980a055e76',
        accessId: 'c2hhb2RhaG9uZw==',
        policy: 'eGl4aWhhaGFrdWt1ZGFkYQ==',
        signature: 'ZGFob25nc2hhbw==',
    });

    onChange = ({ fileList }) => {
        const { onChange } = this.props;
        console.log('Aliyun OSS:', fileList);
        if (onChange) {
            onChange([...fileList]);
        }
    };

    onRemove = file => {
        const { value, onChange } = this.props;

        const files = value.filter(v => v.url !== file.url);

        if (onChange) {
            onChange(files);
        }
    };

    getExtraData = file => {
        const { OSSData } = this.state;

        return {
            key: file.url,
            OSSAccessKeyId: OSSData.accessId,
            policy: OSSData.policy,
            Signature: OSSData.signature,
        };
    };

    beforeUpload = async file => {
        const { OSSData } = this.state;
        const expire = OSSData.expire * 1000;

        if (expire < Date.now()) {
            await this.init();
        }

        const suffix = file.name.slice(file.name.lastIndexOf('.'));
        const filename = Date.now() + suffix;
        file.url = OSSData.dir + filename;

        return file;
    };

    render() {
        const { value } = this.props;
        const props = {
            name: 'file',
            fileList: value,
            action: this.state.OSSData.host,
            onChange: this.onChange,
            onRemove: this.onRemove,
            data: this.getExtraData,
            beforeUpload: this.beforeUpload,
        };
        return (
            <Upload {...props}>
                <Button icon={<UploadOutlined />}>Click to Upload</Button>
            </Upload>
        );
    }
}

