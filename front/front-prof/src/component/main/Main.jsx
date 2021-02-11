import React, {useState, useCallback, useEffect} from "react";
import * as Icon from "react-feather";
import Footer from "../shared/footer/Footer";
import {Pie, Line} from "react-chartjs-2";
import axios from "axios";
import {BASE_URL} from "../../config/config";
import {message} from "antd";


export default function MainS() {
    const {REACT_APP_URL} = process.env;

    const [id, setId] = useState("");
    const [absenceByModule, setAbsenceByModule] = useState([]);
    const [noteByModule, setNoteByModule] = useState([]);


    const getAbsenceByModule = useCallback(
        () => {
            axios.get(`${BASE_URL}api/absence/dash`)
                .then((res) => {
                    if (res.status === 200) {
                        setAbsenceByModule(res.data)
                    } else {
                        message.error("Error server ", res.data.message);
                    }
                }).catch((err) => message.error("Error server ", err.message))

        }, [id]);

    const getNoteByModule = useCallback(
        () => {
            axios.get(`${BASE_URL}api/notes/dash`)
                .then(({data, status}) => {
                    status === 200 ? setNoteByModule(data)
                        : message.error("Error server ", data.message);
                }).catch((err) => message.error("Error server ", err.message))
        }, [id]);


    const getId = useCallback(async () => {
        let user = await JSON.parse(window.localStorage.getItem('user'));
        setId(user?.id);
    }, [])


    useEffect(() => {
        getId();
        getAbsenceByModule();
        getNoteByModule();
    }, [getAbsenceByModule, getNoteByModule, getId]);

    const dataAbsence = {
        labels: absenceByModule?.map(nAbsM => nAbsM?.module),
        datasets: [
            {
                label: 'Nombre des absences par module',
                data: absenceByModule.map((nbr) => nbr?.nbrAbsence),
                fill: true,
                backgroundColor: 'rgba(75,192,192,0.4)',
                borderColor: 'rgba(75,192,192,1)',
            }
        ]
    }

    const dataPie = {
        labels: noteByModule?.map(el => el?.nomElement),
        datasets: [
            {
                label: 'Les notes par element module ',
                data: noteByModule?.map(el => el?.noteModule),
                fill: false,
                borderColor: 'white',
                backgroundColor: ['red', 'blue', 'rgba(75,192,192,0.4)'],

            }
        ]
    }


    return (
        <div id="layoutSidenav_content">
            <main>
                <header className="page-header page-header-dark bg-gradient-primary-to-secondary pb-10">
                    <div className="container">
                        <div className="page-header-content pt-4">
                            <div className="row align-items-center justify-content-between">
                                <div className="col-auto mt-4">
                                    <h1 className="page-header-title">
                                        <div className="page-header-icon"><Icon.Activity size={25}/></div>
                                        Dashboard
                                    </h1>
                                </div>
                            </div>
                        </div>
                    </div>
                </header>
                <div className="container mt-n10">
                    <div className="row">
                        <div className="col-xxl-12 col-xl-12 mb-4">
                            <div className="card h-100">
                                <div className="card-body h-100 d-flex flex-column justify-content-center py-5 py-xl-4">
                                    <div className="row align-items-center">
                                        <div className="col-xl-8 col-xxl-12">
                                            <div className="text-center px-4 mb-4 mb-xl-0 mb-xxl-4">
                                                <h1 className="text-primary">Welcome Back!</h1>
                                                <p className="text-gray-700 mb-0">It&apos;s time to get started! View
                                                    new opportunities now, or continue on your previous work.</p>
                                            </div>
                                        </div>

                                        <div className="col-xl-4 col-xxl-12 text-center">
                                            <img className="img-fluid"
                                                 src={`${REACT_APP_URL}/assets/img/freepik/at-work-pana.svg`}
                                                 style={{maxWidth: "26rem"}}
                                                 alt="at-work img"
                                            />
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>


                    <div className="row">
                        <div className="col-xxl-6 col-xl-6 mb-4 ">
                            <div className="card card-header-actions h-100">
                                <div className="card-header">
                                    Les absences par module
                                </div>
                                <div className="card-body">
                                    <Line
                                        data={dataAbsence}
                                        width={100}
                                        height={300}
                                        options={{maintainAspectRatio: false}}
                                    />
                                </div>
                            </div>
                        </div>

                        <div className="col-xxl-6 col-xl-6 mb-4">
                            <div className="card card-header-actions h-100">
                                <div className="card-header">
                                    Les notes de chaque elemnt de module
                                </div>
                                <div className="card-body">
                                    <Pie
                                        data={dataPie}
                                        width={100}
                                        height={300}
                                        options={{maintainAspectRatio: false}}
                                    />
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </main>

            <Footer/>
        </div>
    )
}