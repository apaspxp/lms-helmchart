import React, { useState, useEffect } from "react";
import './Home.css';

export default function Home(props) {
	
	return (<>
		<div className="container-fluid">
			Hello From Home

			<button onClick={() => props.loader(true)}>Click Me!</button>

		</div>
	</>)

}
