import React, { useState, useEffect } from "react";
import './AdminDashboard.css';
import DashboardCard from "./DashboardCard";
import dashboardFields from  "./DashboardFields"

export default function AdminDashboard(props) {

	return (<>
		<div className="container-fluid admin-dashboard">
			<h4 style={{textAlign:"center", marginBottom:"50px", color:"#62666b", textDecoration: "underline"}} >ADMIN DASHBOARD</h4>
			
			<div className="row admin-dashboard-layout">
				{dashboardFields.map((data, i) => 
					<DashboardCard
						key={data.key}
						title={data.title}
						cardBody={data.cardBody}
					/>
				)}
			</div>
			
		</div>
	</>)
}


