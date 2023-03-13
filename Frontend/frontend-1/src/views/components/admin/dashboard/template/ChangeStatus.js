import React, { useState, useEffect } from "react";
import Popup from 'reactjs-popup';

export default function ChangeStatus(props) {

    function handleChangeStatus(event) {
        event.preventDefault();
        console.log("change status...")
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
                    
                    <form id="registerForm" onSubmit={handleChangeStatus}>
                        <h4 style={{textAlign:"center", marginBottom:"10px"}}>Change Status</h4>
                        
                        <div style={{textAlign:"center", marginTop:"20px"}}>
                            <input className="btn btn-success m-2" type="submit" value="Update" />
                            <button className="btn btn-danger m-2" onClick={close}>Cancel</button>
                        </div>

                    </form>
                 </div>
                )}
            </Popup>
		</div>
	</>)

}

