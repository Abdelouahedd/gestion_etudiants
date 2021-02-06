import React, {useEffect, useState} from 'react';
import {useLocation, useParams} from "react-router";
import {Collapse} from 'antd';
import * as Icon from 'react-feather';

const {Panel} = Collapse;
const Module = () => {

    const {id} = useParams();
    let location = useLocation();

    const [elementModules, setElementModules] = useState([]);

    useEffect(() => {
        console.log(location.state.elementModules)
        setElementModules(location.state.elementModules);
        console.log(elementModules)
    }, [location.state])

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

                                        {
                                            elementModules?.map(el =>
                                                <Collapse
                                                    collapsible="header"
                                                    expandIconPosition="right"
                                                    key={el?.module?.id}
                                                    collapsible={el.cours.length === 0 ? "disabled" : ""}

                                                >
                                                    {
                                                        <Panel
                                                            header={el?.nomElement}
                                                            key={el?.id}
                                                        >{
                                                            el?.cours.map(cour => <h1>{cour?.titreCour}</h1>)
                                                        }
                                                        </Panel>

                                                    }
                                                </Collapse>
                                            )
                                        }
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

export default Module;
