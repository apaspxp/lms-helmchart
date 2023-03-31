import React, { useState, useEffect } from "react";
import Popup from 'reactjs-popup';
import { Link } from 'react-router-dom';
import { apiConfig } from '../../../../../api/api.config';
import axios from "axios";
import FileSaver from 'file-saver';
import Swal from 'sweetalert2'
import './template.css';

export default function HolidayCalendar(props) {

    const [selectedFile, setSelectedFile] = useState(null);
    const [message, setMessage] = useState('');

    function handleDownloadHolidayCalendar(event) {
        event.preventDefault();
        console.log("downloading holiday calendar...")
    }

    const onFileChange = event => {
        setSelectedFile(event.target.files[0]);
        setMessage("");
    };

    const onFileUpload = (event) => {
        setMessage("");
        props.loader(true);
        event.preventDefault();
        const formData = new FormData();
        formData.append(
          "file",
          selectedFile
        );
        console.log(selectedFile);
        const url = apiConfig.uploadHolidayCalendar + '/Hyderabad/2023';
        axios.post(url, formData).then((response) => {
            console.log(response);
            props.loader(false);
            successTrigger(response.data);
        }).catch((response) => {
            console.error("Could not upload the holiday calendar excel report.", response);
            props.loader(false);
            failureTrigger("Could not upload the holiday calendar excel report. Error Code : "+response.code)
        });
    };

    const downloadSampleFile = ()=> { 
        setMessage("");
        props.loader(true);
        console.log("downloading sample excel...")
        axios.get(apiConfig.downloadBlankTemplate, {responseType: 'blob'}).then((response) => {
            FileSaver.saveAs(response.data, "Holiday_calendar_template.xlsx");
            props.loader(false);
            successTrigger("Holiday_calendar_template.xlsx Downloaded Successfully");
        }).catch((response) => {
            console.error("Could not download the excel report from the backend.", response);
            props.loader(false);
            failureTrigger("Could not download the holiday calendar sample excel file. Error code : "+response.code)
        });
    }

    const successTrigger = (message)=> {
         Swal.fire(
            'Success!',
            message,
            'success'
        )
    }
    
    const failureTrigger = (message)=> {
        Swal.fire(
           'Failed!',
           message,
           'error'
       )
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
                        

                        <div style={{textAlign:"center", marginTop:"15px"}}>
                            <div><span className="text-warning" style={{fontFamily: "monospace", fontSize:"14px"}}>{message}</span></div>
                            <input className="btn btn-success m-2" type="submit" value="Upload" disabled={selectedFile==null}/>
                            <button className="btn btn-danger m-2" onClick={()=>{
                                close(); 
                                setMessage('')
                                props.loader(false);
                                setSelectedFile(null);
                            }}>Cancel</button>
                        </div>
                        
                    </form>
                    </div>
                )}
            </Popup>
		</div>
	</>)

}

