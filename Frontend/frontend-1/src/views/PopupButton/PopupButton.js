import React, { useState, useEffect } from "react";
import './PopupButton.css';
import Popup from 'reactjs-popup';

export function PopupButton(props){

	const [close, setClose] = useState();

    return <Popup 
		trigger={<button className="btn admin-dashboard-button">{props.button}</button>} 
		modal
		closeOnDocumentClick={false}
		closeOnEscape={false}>
			{close => (
				<div>
					<div>
						{props.modalBody}
					</div>
				</div>
			)}
    </Popup>
}