import React, { useState, useEffect } from "react";
import './AdminHome.css';
import Popup from 'reactjs-popup';
import 'reactjs-popup/dist/index.css';

export default function AdminHome({loader}) {
	return (<>
		<div className="container-fluid">
		<Popup trigger={<button> Trigger</button>} position="right center">
    <div>Popup content here !!</div>
  </Popup>
		</div>
	</>)

}


