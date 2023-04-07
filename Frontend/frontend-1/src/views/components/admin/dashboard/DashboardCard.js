import React, { useState, useEffect } from "react";
import Popup from 'reactjs-popup';
import './DashboardCard.css';

export default function DashboardCard({title, cardBody}) {

	return (
    <>
        <div className="col-3 admin-dashboard-card">
            <div className="admin-dashboard-card-content">
                <div className="admin-dashboard-card-title">{title}</div>
                <hr className="admin-card-line"/>						
                <div className="admin-dashboard-card-buttons">
                    {cardBody}
                </div>
            </div>
		</div>
	</>
    )
}
