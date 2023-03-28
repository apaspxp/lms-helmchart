import React, { useState, useEffect } from "react";

export default function PersonalInfo(props) {

	const [editMode, updateEditMode] = useState(false);


	const [profileData, setProfileData] = useState({
		name: 'ABC DEF',
		gender: 'Male',
		countryCode: '+91',
		mobile: 1234567890,
		country: 'India',
		emailId: 'abc@def.com',
		picUrl: 'https://images.pexels.com/photos/771742/pexels-photo-771742.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1',
	});

	const [name, updateName] = useState(profileData.name);
	const [gender, updateGender] = useState(profileData.gender);
	const [countryCode, updateCountryCode] = useState(profileData.countryCode);
	const [mobile, updateMobile] = useState(profileData.mobile);
	const [country, updateCountry] = useState(profileData.country);
	const [emailId, updateEmailId] = useState(profileData.emailId);
	const [picUrl, updatePicUrl] = useState(profileData.picUrl);

	function handleEditProfile() {
		updateEditMode(true);
	}

	function handleUpdateProfile() {
		updateEditMode(false);
		const newProfileData = {
			name: name,
			gender: gender,
			countryCode: countryCode,
			mobile: mobile,
			country: country,
			emailId: emailId,
			picUrl: picUrl
		}
		setProfileData(newProfileData);
	}


	return (<>
		<div className="container-fluid">
			<h4>Personal Information</h4>

			<img src={profileData.picUrl} alt="Girl in a jacket" width="80px" />
			<br /><br />

			{editMode ? <>
				<input className="form-control" type="text" value={name} style={{ width: "200px" }} onChange={e => updateName(e.target.value)} />
				<input className="form-control" type="text" value={countryCode} style={{ width: "200px" }} onChange={e => updateCountryCode(e.target.value)} />
				<input className="form-control" type="phone" value={mobile} style={{ width: "200px" }} onChange={e => updateMobile(e.target.value)} />
				<input className="form-control" type="text" value={emailId} style={{ width: "200px" }} onChange={e => updateEmailId(e.target.value)} />
				<input className="form-control" type="text" value={country} style={{ width: "200px" }} onChange={e => updateCountry(e.target.value)} />
			</>
				: <>
					<p>{profileData.name}</p>
					<p>{profileData.countryCode + ' ' + profileData.mobile}</p>
					<p>{profileData.emailId}</p>
					<p>{profileData.country}</p>
				</>}

			<button disabled={editMode} className="btn btn-outline-dark" onClick={handleEditProfile}>Edit</button>
			<button disabled={!editMode} className="btn btn-outline-dark" onClick={handleUpdateProfile}>Update</button>


		</div>
	</>)

}

