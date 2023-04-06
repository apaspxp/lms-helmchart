import './App.css';
import React, { useState } from "react";
import { useHistory } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import 'font-awesome/css/font-awesome.min.css';
import ROUTES, { RenderRoutes } from './routes/Routes';


function App() {

    const history = useHistory();
    const [loading, setLoading] = useState(false);
    const [empId, setEmpId] = useState(localStorage.getItem("empId"));

    const setEmployeeIdToLC = (e) =>{
        localStorage.setItem("empId", e.target.value ) ;
        setEmpId(e.target.value);
    }

    function logout() {
        localStorage.removeItem("token");
        window.location.reload();
        history.push("/home");
    }

    return (
        <div className="App">
            <a href="/home/directory">All routes</a>        

            <div style={{float:'right'}}>
                <input  className='form-control-sm' value={empId} onChange={setEmployeeIdToLC} placeholder="empId"/> 
            </div>    

            {localStorage.getItem("token") != null ?
                <button onClick={logout}>Log Out</button>
            : ""} <br/>

            <RenderRoutes routes={ROUTES} loader={setLoading}/>
            
            {loading?<div className="loader"></div>:''}


        </div>
    );
}

export default App;


