import React, { useState, useEffect } from "react";
import Popup from 'reactjs-popup';
import { Link } from 'react-router-dom';


export default function HolidayCalendar(props) {

    const [selectedFile, setSelectedFile] = useState(null);

    function handleDownloadHolidayCalendar(event) {
        event.preventDefault();
        console.log("downloading holiday calendar...")
        // axios.get("api/downloadHolidayCalendar");
    }

    const onFileChange = event => {
        setSelectedFile(event.target.files[0]);
    };

    const onFileUpload = (event) => {
        event.preventDefault();
        const formData = new FormData();
        formData.append(
          "myFile",
          selectedFile,
          selectedFile.name
        );
        console.log(selectedFile);
        // axios.post("api/admin/uploadHolidayCalendar", formData);
    };

    const downloadSampleFile = ()=> { 
        console.log("downloading sample excel...")
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
                            <button className="btn btn-danger m-2" onClick={close}>Close</button>
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
                    <form id="registerForm" onSubmit={onFileUpload}>
                        <h4 style={{textAlign:"center", marginBottom:"30px"}}>Upload Holiday Calendar</h4>
                        <div style={{textAlign:"left"}}>
                            Please upload a holiday calendar xlsx file. &nbsp; 
                            <Link to="#" href="#" onClick={downloadSampleFile}>Download Sample Excel</Link>
                        </div>
                        <div className="mt-3"> <input type="file" onChange={onFileChange}/></div>
                        <div style={{textAlign:"center", marginTop:"10px"}}>
                            <input className="btn btn-success m-2" type="submit" value="Upload" disabled={selectedFile==null}/>
                            <button className="btn btn-danger m-2" onClick={close}>Cancel</button>
                        </div>
                    </form>
                    </div>
                )}
            </Popup>
		</div>
	</>)

}

