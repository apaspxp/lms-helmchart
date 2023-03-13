import React, { useState, useEffect } from "react";
import { PopupButton } from "../../../../PopupButton/PopupButton";
import Popup from 'reactjs-popup';

export default function CreateEmployee(props) {

    const [empFName, setEmpFName] = useState('');
    const [empMName, setEmpMName] = useState('');
    const [empLName, setEmpLName] = useState('');

    const [empDept, setEmpDept] = useState('');
    const [empEmail, setEmpEmail] = useState('');
    const [empMobile, setEmpMobile] = useState('');
    const [empLoc, setEmpLoc] = useState('');

    function handleAddEmployee(event) {
        event.preventDefault();
        console.log("adding employee...")
    }

      
	return (<>
		<div className="container-fluid">

            <Popup 
                trigger={<button className="btn admin-dashboard-button">create</button>} 
                modal
                closeOnDocumentClick={false}
                closeOnEscape={false}>
                {close => (
                    <div style={{padding:"20px"}}>
                    
                    <form id="registerForm" onSubmit={handleAddEmployee}>
                        <h4 style={{textAlign:"center", marginBottom:"10px"}}>Add Employee</h4>
                        <label>Full Name</label>
                        <div className="input-group" style={{ width: "300px" }}>
                            <input type="text" aria-label="First" className="form-control" placeholder='First' value={empFName} onChange={e => setEmpFName(e.target.value)}/>
                            <input type="text" aria-label="Middle" className="form-control" placeholder='Middle' value={empMName} onChange={e => setEmpMName(e.target.value)}/>
                            <input type="text" aria-label="Last" className="form-control" placeholder='Last' value={empLName} onChange={e => setEmpLName(e.target.value)}/>
                        </div>
                
                        <label className="mt-2">Department</label>
                        <div className="input-group flex-nowrap" style={{ width: "300px" }}>
                            <input className="form-control" type="text" placeholder='Enter Department' value={empDept} onChange={e => setEmpDept(e.target.value)} />
                        </div>

                        <label className="mt-2">Email</label>
                        <div className="input-group flex-nowrap" style={{ width: "300px" }}>
                            {/* <span className="input-group-text" id="addon-wrapping">@</span> */}
                            <input className="form-control" type="text" placeholder='Enter Email' value={empEmail} onChange={e => setEmpEmail(e.target.value)} />
                        </div>

                        <label className="mt-2">Mobile</label>
                        <div className="input-group flex-nowrap" style={{ width: "300px" }}>
                            <input className="form-control" type="text" placeholder='Enter Mobile' value={empMobile} onChange={e => setEmpMobile(e.target.value)} />
                        </div>

                        <label className="mt-2">Location</label>
                        <div className="input-group flex-nowrap" style={{ width: "300px" }}>
                            <input className="form-control" type="text" placeholder='Enter Location' value={empLoc} onChange={e => setEmpLoc(e.target.value)} />
                        </div>                

                        <div style={{textAlign:"center", marginTop:"20px"}}>
                            <input className="btn btn-success m-2" type="submit" value="Add Employee" />
                            <button className="btn btn-danger m-2" onClick={close}>Cancel</button>
                        </div>

                    </form>
                 </div>
                )}
            </Popup>

		</div>
	</>)
}

