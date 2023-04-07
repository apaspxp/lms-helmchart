import React, { useState, useEffect } from "react";
import Address from "./Address";
import PersonalInfo from './PersonalInfo';

export default function Profile({loader}) {


	return (<>
		<div className="container-fluid">
			<h1>Profile</h1>
			<PersonalInfo />
			<Address />
		</div>
	</>)

}

