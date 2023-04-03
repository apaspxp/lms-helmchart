import React, { useState, useEffect } from "react";
import Popup from 'reactjs-popup';
import Swal from 'sweetalert2'

export default function ChangeManager(props) {
    
    const [empId, setEmpId] = useState('');
    const [empName, setEmpName] = useState('');

    const [oldManagerId, setOldManagerId] = useState('');
    const [oldManagerName, setOldManagerName] = useState('');

    const [newManagerId, setNewManagerId] = useState('');
    const [newManagerName, setNewManagerName] = useState('');


    const handleChangeManager = (event) => {
        event.preventDefault();

        if(!empId || !empName || !oldManagerId || !oldManagerName || !newManagerId || !newManagerName){
            return;
        }

        console.log("change manager...")

        Swal.fire({
            title: 'Are you sure?',
            text: `Manager of ${empName} (${empId}) will be changed from ${oldManagerName} (${oldManagerId}) to ${newManagerName} (${newManagerId})`,
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: `Update ${newManagerName} as New Manager`
          }).then((result) => {
            if (result.isConfirmed) {
              Swal.fire(
                'Updated!',
                '',
                'success'
              )
              setEmpId('');
              setEmpName('');
              setOldManagerId('');
              setOldManagerName('');
              setNewManagerId('');
              setNewManagerName('');
            }
          })
    }

    const searchEmployee = (event) => {
        if(!empId){
            return;
        }
        console.log("searching employee")
        setEmpName("Sarthak Mehta");
        setOldManagerName("Narendra Modi");
        setOldManagerId(99);
    }

    const searchManager = (event) => {
        if(!newManagerId){
            return;
        }
        console.log("searching new manager")
        setNewManagerName("Yogi");
    }

	return (<>
		<div className="container-fluid">
        <Popup 
                trigger={<button className="btn admin-dashboard-button">Update</button>} 
                modal
                closeOnDocumentClick={false}
                closeOnEscape={false}>
                {close => (
                    <div style={{padding:"20px"}}>
                    
                    <form id="registerForm" onSubmit={handleChangeManager}>
                        <h4 style={{textAlign:"center", marginBottom:"30px"}}>Change Manager</h4>
                        
                        <div className="input-group mb-3">
                            <input type="text" className="form-control" placeholder="Employee ID" value={empId} onChange={e => setEmpId(e.target.value)}/>
                            <div className="input-group-append" onClick={searchEmployee}>
                                <span className="input-group-text" id="basic-addon2">
                                    &nbsp;<i className="fa fa-search" aria-hidden="true"></i>
                                </span>
                            </div>
                        </div>
                        
                        <div className="input-group mb-3">
                            <input type="text" className="form-control" placeholder="New Manager ID" value={newManagerId} onChange={e => setNewManagerId(e.target.value)}/>
                            <div className="input-group-append" onClick={searchManager}>
                                <span className="input-group-text" id="basic-addon2">
                                    &nbsp;<i className="fa fa-search" aria-hidden="true"></i>
                                </span>
                            </div>
                        </div>

                  

                        <div style={{textAlign:"center", marginTop:"20px"}}>

                            <div className="mb-2 text-danger" style={{textDecoration:"line-through"}}>
                                <span>{empName}</span>&nbsp;
                                {empName?<i className="fa  fa-long-arrow-right" aria-hidden="true"></i>:''}
                                &nbsp;<span>{oldManagerName}</span>
                            </div>

                            <div className="mb-2 text-success">
                                <span>{empName}</span>&nbsp;
                                {empName?<i className="fa  fa-long-arrow-right" aria-hidden="true"></i>:''}
                                &nbsp;<span>{newManagerName}</span>
                            </div>

                            <input className="btn btn-success m-2" type="submit" value="Map" 
                            disabled={!empId || !empName || !oldManagerId || !oldManagerName || !newManagerId || !newManagerName}/>
                            <button className="btn btn-danger m-2" onClick={close}>Close</button>

                        </div>

                    </form>
                 </div>
                )}
            </Popup>
		</div>
	</>)

}

