import React, { useState, useEffect } from "react";
import './AdminDashboard.css';
import DashboardCard from "./DashboardCard";

import CreateEmployee from './template/CreateEmployee';
import HolidayCalendar from './template/HolidayCalendar';
import ChangeManager from './template/ChangeManager';
import ChangeStatus from './template/ChangeStatus';
import Sample from './template/Sample';
import SwipeInOut from "../../profile/SwipeInOut";

export default function AdminDashboard({loader}) {
	
	return (<>
		<div className="container-fluid admin-dashboard">
			<h4 style={{textAlign:"center", marginBottom:"50px", color:"#62666b", textDecoration: "underline"}} >ADMIN DASHBOARD</h4>
			
			<div className="row admin-dashboard-layout">
				<DashboardCard
					key="addEmployee"
					title="Add Employee"
					cardBody={<CreateEmployee 
						loader = {loader}
					/>}
				/>
				<DashboardCard
					key="changeStatus"
					title="Change status"
					cardBody={<ChangeStatus 
						loader = {loader}
					/>}
				/>
				<DashboardCard
					key="changeManager"
					title="Change Manager"
					cardBody={<ChangeManager 
						loader = {loader}
					/>}
				/>
				<DashboardCard
					key="holidayCalendar"
					title="Holiday Calendar"
					cardBody={<HolidayCalendar 
						loader = {loader}
					/>}
				/>
				<DashboardCard
					key="sample"
					title="Sample"
					cardBody={<Sample 
						loader = {loader}
					/>}
				/>
				<DashboardCard
					key="SwipeInOut"
					title="Swipe In And Out"
					cardBody={<SwipeInOut 
						loader = {loader}
					/>}
				/>
			</div>
			
		</div>
	</>)
}


