import React from 'react'
import { Menu } from 'antd';

export default function Header() {



    return (
        <>
            <Menu className="bg-gray-800" mode="horizontal" >
                <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
                    <div className="flex items-center justify-between h-16">
                        <div className="flex items-center">
                            <div className="flex-shrink-0">
                                <img className="h-8 w-8" src="https://tailwindui.com/img/logos/workflow-mark-indigo-500.svg" alt="Workflow" />
                            </div>
                        </div>
                        <div className="ml-3 relative">
                            <div className="ml-10 flex items-baseline space-x-4">
                                <Menu.Item className="bg-gray-900 text-white px-4 py-2 rounded-md text-sm font-medium"
                                    // icon={<LoginOutlined />}
                                    title="Login"
                                    key="login"

                                >
                                    Login
                                </Menu.Item>
                            </div>
                        </div>
                    </div>
                </div>
            </Menu>
        </>
    )
}
