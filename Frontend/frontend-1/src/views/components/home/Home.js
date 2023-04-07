import React, { useState, useEffect } from "react";
import './Home.css';

export default function Home({loader}) {
	
	return (<>
		<div className="container-fluid">
			Hello From Home

			<button onClick={() => loader(true)}>Click Me!</button>

		</div>
	</>)

}
