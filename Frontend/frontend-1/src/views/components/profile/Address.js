import React, { useState, useEffect } from "react";

export default function Address({loader}) {

	const [newAddresMode, udateNewAddresMode] = useState(false);


	const [fetchedAddress, setFetchedAddress] = useState([
		{
			name: 'abc',
			addressLine1: 'ab-11',
			addressLine2: 'Street No-1',
			locality: 'abc',
			pincode: '000000',
			mobile: '+911234567890',
			addressType: 'Home',
		},
		{
			name: 'xyz',
			addressLine1: 'xy-11',
			addressLine2: 'Street No-1',
			locality: 'abc xyz',
			pincode: '000000',
			mobile: '+911234567890',
			addressType: 'Home',
		},
	]);

	const [name, updateName] = useState();
	const [addressLine1, updateAddressLine1] = useState();
	const [addressLine2, updateAddressLine2] = useState();
	const [locality, updateLocality] = useState();
	const [pincode, updatePincode] = useState();
	const [mobile, updateMobile] = useState();
	const [addressType, updateAddressType] = useState();

	const [editIndex, updateEditIndex] = useState(undefined);


	function handleSaveNewAddress() {
		if (name && addressLine1 && addressLine2 && locality && pincode && mobile && addressType) {
			const newAddr = {
				name: name,
				addressLine1: addressLine1,
				addressLine2: addressLine2,
				locality: locality,
				pincode: pincode,
				mobile: mobile,
				addressType: addressType,
			}
			fetchedAddress.push(newAddr);
			udateNewAddresMode(false);
		}
	}

	function handleAddNewAddress() {
		clearAllAddressFields();
		udateNewAddresMode(true);
	}

	function handleEditAddress(id) {
		udateNewAddresMode(false);
		updateEditIndex(id);
		console.log(id);

		let addr = fetchedAddress[id];
		updateName(addr.name);
		updateAddressLine1(addr.addressLine1);
		updateAddressLine2(addr.addressLine2);
		updateLocality(addr.locality);
		updatePincode(addr.pincode);
		updateMobile(addr.mobile);
		updateAddressType(addr.addressType);
	}

	function clearAllAddressFields() {
		updateName('');
		updateAddressLine1('');
		updateAddressLine2('');
		updateLocality('');
		updatePincode('');
		updateMobile('');
		updateAddressType('');
	}

	function updateAddress(id) {
		if (name && addressLine1 && addressLine2 && locality && pincode && mobile && addressType) {
			const newAddr = {
				name: name,
				addressLine1: addressLine1,
				addressLine2: addressLine2,
				locality: locality,
				pincode: pincode,
				mobile: mobile,
				addressType: addressType,
			}
			let updatedAddress = [...fetchedAddress];
			updatedAddress.splice(id, 1, newAddr);
			setFetchedAddress(updatedAddress);
			updateEditIndex(undefined);
		}
	}

	const handleDeleteAddress = (id) => {
		console.log(id)
		let updatedAddress = [...fetchedAddress];
		updatedAddress.splice(id, 1);
		console.log(updatedAddress)
		setFetchedAddress(updatedAddress);
		updateEditIndex(undefined);
	}


	return (<>
		<div className="container-fluid" style={{ marginBottom: "40px" }}>

			<h4 style={{ marginTop: "40px" }}>Address</h4>
			{fetchedAddress.map((address, i) => <div style={{ border: "1px solid black", marginBottom: "10px", width: "300px", padding: "10px" }} key={i}>

				{editIndex != i ? <>
					<p>{address.name}</p>
					<p>{address.addressLine1}</p>
					<p>{address.addressLine2}</p>
					<p>{address.locality}</p>
					<p>{address.pincode}</p>
					<p>{address.mobile}</p>
					<p>{address.addressType}</p>
				</>
					: <>
						<input className="form-control" type="text" placeholder="Full Name" value={name} style={{ width: "200px" }} onChange={e => updateName(e.target.value)} />
						<input className="form-control" type="text" placeholder="Address Line 1" value={addressLine1} style={{ width: "200px" }} onChange={e => updateAddressLine1(e.target.value)} />
						<input className="form-control" type="text" placeholder="Address Line 2" value={addressLine2} style={{ width: "200px" }} onChange={e => updateAddressLine2(e.target.value)} />
						<input className="form-control" type="text" placeholder="Locality" value={locality} style={{ width: "200px" }} onChange={e => updateLocality(e.target.value)} />
						<input className="form-control" type="text" placeholder="Mobile" value={mobile} style={{ width: "200px" }} onChange={e => updateMobile(e.target.value)} />
						<input className="form-control" type="text" placeholder="Pincode" value={pincode} style={{ width: "200px" }} onChange={e => updatePincode(e.target.value)} />
						<input className="form-control" type="text" placeholder="Address Type" value={addressType} style={{ width: "200px" }} onChange={e => updateAddressType(e.target.value)} />
						<button className="btn btn-success" onClick={updateAddress}>Update</button>
					</>}

				{editIndex == undefined ?
					<span className="text-info" onClick={e => handleEditAddress(e.target.id)} id={i} >Edit
						<i className="fa fa-edit" style={{ marginLeft: "5px", cursor: "pointer" }} aria-hidden="true"></i>
					</span>
					: ''}

				<span className="text-danger" onClick={e => handleDeleteAddress(e.target.id)} style={{ marginLeft: "10px" }} id={i}>Delete
					<i className="fa fa-trash" style={{ marginLeft: "5px", cursor: "pointer" }} aria-hidden="true"></i>
				</span>

			</div>)}

			{newAddresMode ? <div>
				<input className="form-control" type="text" placeholder="Full Name" value={name} style={{ width: "200px" }} onChange={e => updateName(e.target.value)} />
				<input className="form-control" type="text" placeholder="Address Line 1" value={addressLine1} style={{ width: "200px" }} onChange={e => updateAddressLine1(e.target.value)} />
				<input className="form-control" type="text" placeholder="Address Line 2" value={addressLine2} style={{ width: "200px" }} onChange={e => updateAddressLine2(e.target.value)} />
				<input className="form-control" type="text" placeholder="Locality" value={locality} style={{ width: "200px" }} onChange={e => updateLocality(e.target.value)} />
				<input className="form-control" type="text" placeholder="Mobile" value={mobile} style={{ width: "200px" }} onChange={e => updateMobile(e.target.value)} />
				<input className="form-control" type="text" placeholder="Pincode" value={pincode} style={{ width: "200px" }} onChange={e => updatePincode(e.target.value)} />
				<input className="form-control" type="text" placeholder="Address Type" value={addressType} style={{ width: "200px" }} onChange={e => updateAddressType(e.target.value)} />
				<button className="btn btn-success" onClick={handleSaveNewAddress}>Save Address</button>
			</div>
				: <></>}

			<button disabled={newAddresMode} className="btn btn-outline-success" onClick={handleAddNewAddress}>Add New Address</button>

		</div>
	</>)

}

