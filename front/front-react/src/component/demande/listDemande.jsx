import React from 'react'
import { Collapse, Space } from 'antd';
import * as Icon from 'react-feather';

const { Panel } = Collapse;

const text = `
  A dog is a type of domesticated animal.
  Known for its loyalty and faithfulness,
  it can be found as a welcome guest in many households across the world.
`;

export default function ListDemande() {
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
                                        List des demandes
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
                                        {/* <Space direction="vertical" > */}
                                        <Collapse
                                            collapsible="header"
                                            defaultActiveKey={['1']}
                                            expandIconPosition="right"
                                        >
                                            <Panel header="This panel can only be collapsed by clicking text" key="1" >
                                                <p>{text}</p>
                                            </Panel>
                                        </Collapse>
                                        <Collapse
                                            collapsible="header"
                                            defaultActiveKey={['1']}
                                            expandIconPosition="right"
                                        >
                                            <Panel header="This panel can't be collapsed" key="1">
                                                <p>
                                                    {text}
                                                        Lorem ipsum dolor sit amet consectetur adipisicing elit. Repellat beatae consequatur aspernatur error, saepe quibusdam. Quis quos at pariatur libero facilis voluptatum sequi! Accusantium maiores, sequi laudantium doloribus reprehenderit at!
                                                    </p>
                                            </Panel>
                                        </Collapse>
                                        {/* </Space> */}
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
