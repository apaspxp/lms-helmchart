import React, { useState } from "react";
import { Redirect, useHistory } from "react-router-dom";
import './Login.css';

export default function Login({location, loader}) {

    const [username, updateUserName] = useState("");
    const [password, updatePassword] = useState("");
    const [backPath, updateBackPath] = useState(location.backPath ? location.backPath : "/home");
    const history = useHistory();

    console.log(backPath);

    //Login
    function handleLogin() {
        localStorage.setItem("token", username + password);
        history.push(backPath);
    }

    //Redirect if already authentiated
    if (localStorage.getItem("token")) {
        alert("You're already authenticated in localStorage and being redirected into the app.");
        return <Redirect to={backPath} />;
    }

    return (
        <div style={{ padding: 50 }}>
            <h1>Log In</h1>
            <div>
                <div className="text-primary">Any username password will work</div>
                <label>Username</label>
                <input value={username} onChange={e => updateUserName(e.target.value)} /><br />
                <label>Password</label>
                <input value={password} onChange={e => updatePassword(e.target.value)} type="password" /> <br />
                <button disabled={!username || !password} onClick={handleLogin}>
                    Log In
                </button>
            </div>
        </div>
    );

}