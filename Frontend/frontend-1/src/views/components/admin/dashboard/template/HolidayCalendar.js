import React, { useState, useEffect } from "react";
import Popup from 'reactjs-popup';

export default function HolidayCalendar(props) {

    function handleDownloadHolidayCalendar(event) {
        event.preventDefault();
        console.log("downloading holiday calendar...")
    }

    function handleUploadHolidayCalendar(event) {
        event.preventDefault();
        console.log("uploading holiday calendar...")
    }


	return (<>
		<div className="container-fluid">
            <Popup 
                trigger={<button className="btn admin-dashboard-button">Download</button>} 
                modal
                closeOnDocumentClick={false}
                closeOnEscape={false}>
                {close => (
                    <div style={{padding:"20px"}}>
                    <form id="registerForm" onSubmit={handleDownloadHolidayCalendar}>
                        <h4 style={{textAlign:"center", marginBottom:"10px"}}>Download Holiday Calendar</h4>
                        <div style={{textAlign:"center", marginTop:"20px"}}>
                            <input className="btn btn-success m-2" type="submit" value="Download" />
                            <button className="btn btn-danger m-2" onClick={close}>Cancel</button>
                        </div>
                    </form>
                    </div>
                )}
            </Popup>
            <Popup 
                trigger={<button className="btn admin-dashboard-button">Upload</button>} 
                modal
                closeOnDocumentClick={false}
                closeOnEscape={false}>
                {close => (
                    <div style={{padding:"20px"}}>
                    <form id="registerForm" onSubmit={handleUploadHolidayCalendar}>
                        <h4 style={{textAlign:"center", marginBottom:"10px"}}>Upload Holiday Calendar</h4>
                        <div style={{textAlign:"center", marginTop:"20px"}}>
                            <input className="btn btn-success m-2" type="submit" value="Upload" />
                            <button className="btn btn-danger m-2" onClick={close}>Cancel</button>
                        </div>
                    </form>
                    </div>
                )}
            </Popup>
		</div>
	</>)

}

