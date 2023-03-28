import './App.css';
import React from "react";
import { useHistory } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import 'font-awesome/css/font-awesome.min.css';
import ROUTES, { RenderRoutes } from './routes/Routes';


function App() {

    const history = useHistory();

    function logout() {
        localStorage.removeItem("token");
        window.location.reload();
        history.push("/home");
    }

    return (
        <div className="App">
            <a href="/home/directory">All routes</a>

            {localStorage.getItem("token") != null ?
                <button onClick={logout}>Log Out</button>
            : ""} <br/>

            <RenderRoutes routes={ROUTES} />
        </div>
    );
}

export default App;


