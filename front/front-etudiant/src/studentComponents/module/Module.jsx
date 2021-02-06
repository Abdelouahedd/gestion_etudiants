import React, {useEffect} from 'react';
import {useParams} from "react-router";

const Module = () => {
    const {id} = useParams();

    useEffect(() => {
        console.log("Id module --> ", id);
    }, [])

    return (
        <div>
            <h1>Module id {id}</h1>
        </div>
    );
};

export default Module;