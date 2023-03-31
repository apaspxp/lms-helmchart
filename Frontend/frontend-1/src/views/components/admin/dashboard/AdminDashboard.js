import React, { useState, useEffect } from "react";
import './AdminDashboard.css';
import DashboardCard from "./DashboardCard";

import CreateEmployee from './template/CreateEmployee';
import HolidayCalendar from './template/HolidayCalendar';
import ChangeManager from './template/ChangeManager';
import ChangeStatus from './template/ChangeStatus';
import Sample from './template/Sample';

export default function AdminDashboard(props) {
	// props.loader(true)
	return (<>
		<div className="container-fluid admin-dashboard">
			<h4 style={{textAlign:"center", marginBottom:"50px", color:"#62666b", textDecoration: "underline"}} >ADMIN DASHBOARD</h4>
			
			<div className="row admin-dashboard-layout">
				<DashboardCard
					key="addEmployee"
					title="Add Employee"
					cardBody={<CreateEmployee 
						loader = {props.loader}
					/>}
				/>
				<DashboardCard
					key="changeStatus"
					title="Change status"
					cardBody={<ChangeStatus 
						loader = {props.loader}
					/>}
				/>
				<DashboardCard
					key="changeManager"
					title="Change Manager"
					cardBody={<ChangeManager 
						loader = {props.loader}
					/>}
				/>
				<DashboardCard
					key="holidayCalendar"
					title="Holiday Calendar"
					cardBody={<HolidayCalendar 
						loader = {props.loader}
					/>}
				/>
				<DashboardCard
					key="sample"
					title="Sample"
					cardBody={<Sample 
						loader = {props.loader}
					/>}
				/>
			</div>
			
		</div>
	</>)
}


