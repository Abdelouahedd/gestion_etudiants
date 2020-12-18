import React from 'react'
import Footer from '../shared/footer/Footer'
import * as Icon from 'react-feather';

export default function Main() {
    const { REACT_APP_URL } = process.env;
    return (
        <div id="layoutSidenav_content">
            <main>
                <header className="page-header page-header-dark bg-gradient-primary-to-secondary pb-10">
                    <div className="container">
                        <div className="page-header-content pt-4">
                            <div className="row align-items-center justify-content-between">
                                <div className="col-auto mt-4">
                                    <h1 className="page-header-title">
                                        <div className="page-header-icon"><Icon.Activity size={25} /></div>
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
                                                style={{ maxWidth: "26rem" }}
                                                alt="at-work img"
                                            />
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="row">
                        <div className="col-xxl-3 col-lg-3">
                            <div className="card bg-primary text-white mb-4">
                                <div className="card-body">
                                    <div className="d-flex justify-content-between align-items-center">
                                        <div className="mr-3">
                                            <div className="text-white-95 small">Number of users</div>
                                            <div className="text-lg font-weight-bold">100</div>
                                        </div>
                                        <i className="fa fa-user-circle fa-2x text-white-50" aria-hidden="true"></i>
                                    </div>
                                </div>

                            </div>
                        </div>
                        <div className="col-xxl-3 col-lg-3">
                            <div className="card bg-warning text-white mb-4">
                                <div className="card-body">
                                    <div className="d-flex justify-content-between align-items-center">
                                        <div className="mr">
                                            <div className="text-white-95 small">Number of communities</div>
                                            <div className="text-lg font-weight-bold">20</div>
                                        </div>
                                        <i className="fa fa-users fa-2x text-white-50" aria-hidden="true"></i>
                                    </div>
                                </div>

                            </div>
                        </div>
                        <div className="col-xxl-3 col-lg-3 ">
                            <div className="card bg-success text-white mb-4">
                                <div className="card-body">
                                    <div className="d-flex justify-content-between align-items-center">
                                        <div className="mr-3">
                                            <div className="text-white-95 small">Number of posts</div>
                                            <div className="text-lg font-weight-bold">10</div>
                                        </div>
                                        <img className="feather-xl" style={{ fill: "red", opacity: "50%" }}
                                            src={`${REACT_APP_URL}/assets/img/share-post.svg`} alt="Posts" />
                                    </div>
                                </div>

                            </div>
                        </div>
                        <div className="col-xxl-3 col-lg-3 ">
                            <div className="card bg-danger text-white mb-4">
                                <div className="card-body">
                                    <div className="d-flex justify-content-between align-items-center">
                                        <div className="mr-3">
                                            <div className="text-white-95 small">New Requests</div>
                                            <div className="text-lg font-weight-bold">10</div>
                                        </div>
                                        <i className="fa fa-user-plus feather-xl text-white-50" aria-hidden="true"></i>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>

                    <div className="row">
                        <div className="col-xxl-6 col-xl-6 mb-4 " >
                            <div className="card card-header-actions h-100">
                                <div className="card-header">
                                    Recent Activity
                            </div>
                                <div className="card-body">

                                </div>
                            </div>
                        </div>

                        <div className="col-xxl-6 col-xl-6 mb-4">
                            <div className="card card-header-actions h-100">
                                <div className="card-header">
                                    Number of Users added grouped by date
                            </div>
                                <div className="card-body">

                                </div>
                            </div>
                        </div>
                    </div>

                    <div className="card mb-4">
                        <div className="card-header">
                            <div className="row align-items-center ml-auto">
                                <div className="col-8 ">
                                    Personnel Management
                            </div>
                                <div className="col-4">
                                    <form className="form-inline mr-auto d-none d-md-block">
                                        <div className="input-group input-group-joined input-group-solid">
                                            <input className="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search ..." />
                                            <div className="input-group-append">
                                                <div className="input-group-text">
                                                    <Icon.Search size={15} />
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div className="card-body">
                            <div className="datatable">

                            </div>
                        </div>
                    </div>
                </div>
            </main>

            <Footer />
        </div >
    )
}
