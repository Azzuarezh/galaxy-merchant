'use strict';

// tag::vars[]
import React, { useState, useEffect } from 'react';
import ReactDOM from 'react-dom'
import client from './client' 
import Dashboard from './components/Dashboard'
// end::vars[]

// tag::app[]
export default function App(){ 
	const [materials,setMaterials] = useState([]);

	useEffect(() => {
		// Update the document title using the browser API
		client({method: 'GET', path: '/api/materials'}).done(response => {
			setMaterials(response.entity._embedded.materials);
		})
	  });

		return (
			<Dashboard materials={materials}/>
		)	
}

// end::app[]

// tag::render[]
ReactDOM.render(
	<App />,
	document.getElementById('react')
)
// end::render[]
