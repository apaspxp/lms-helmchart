import React, { useState, useEffect } from "react";
import Popup from 'reactjs-popup';

export default function Sample(props) {

    function handleSample(event) {
        event.preventDefault();
        console.log("sample...")
    }

	return (<>
		<div className="container-fluid">
            <Popup 
                trigger={<button className="btn admin-dashboard-button">Sample</button>} 
                modal
                closeOnDocumentClick={false}
                closeOnEscape={false}>
                {close => (
                    <div style={{padding:"20px"}}>
                    <form id="registerForm" onSubmit={handleSample}>
                        <h4 style={{textAlign:"center", marginBottom:"10px"}}>Sarthak's Sample Popup</h4>
                        <div style={{textAlign:"center", marginTop:"20px"}}>
                            <input className="btn btn-success m-2" type="submit" value="Confirm" />
                            <button className="btn btn-danger m-2" onClick={close}>Cancel</button>
                        </div>
                    </form>
                    </div>
                )}
            </Popup>
		</div>
	</>)

}

