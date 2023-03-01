import React, { useState } from "react";
import { Redirect, useHistory } from "react-router-dom";
import './Register.css';

export default function Register(props) {
  const [enableOTP, updateEnableOTP] = useState(false);
  const [enableAdditionalDetails, updateEnableAdditionalDetails] = useState(false);
  const [enableSuccessMsg, updateEnableSuccessMsg] = useState(false);

  const history = useHistory();
  const [backPath, updateBackPath] = useState(props.location.backPath ? props.location.backPath : "/home");

  const [mobile, updateMobile] = useState();
  const [emailId, updateEmailId] = useState();
  const [mobileOTP, updateMobileOTP] = useState();
  const [name, updateName] = useState();
  const [pincode, updatePincode] = useState();


  //Redirect if already registered
  if (localStorage.getItem("token") && !enableSuccessMsg) {
    alert("You're already registered in localStorage and being redirected into the app.");
    return <Redirect to={backPath} />;
  }


  function handleSendOTP(event) {
    event.preventDefault();
    if (mobile) {
      updateEnableOTP(true);
      document.getElementById("sendOTPForm").reset();
    }
  }

  function handleVerifyOTP(event) {
    event.preventDefault();
    if (mobileOTP) {
      updateEnableAdditionalDetails(true);
      document.getElementById("verifyOTPForm").reset();
    }
  }


  function handleRegister(event) {
    event.preventDefault();
    if (name && pincode) {
      updateEnableSuccessMsg(true)
      localStorage.setItem("token", name);
    }
  }


  return (
    <div style={{ padding: 50 }}>
      <h1>Register</h1>

      {!enableAdditionalDetails ? <>
        {!enableOTP ? <>
          <form id="sendOTPForm" onSubmit={handleSendOTP}>
            <h5>Enter mobile number to register</h5>
            <input className="form-control" type="text" placeholder='Mobile Number' value={mobile} style={{ width: "200px" }} onChange={e => updateMobile(e.target.value)} />
            <input className="btn btn-success" type="submit" value="Send OTP" />
          </form>
        </> : <>
          <form id="verifyOTPForm" onSubmit={handleVerifyOTP}>
            <h5>Enter OTP sent to your mobile number : {mobile}</h5>
            <input className="form-control" type="text" placeholder='OTP' value={mobileOTP} style={{ width: "200px" }} onChange={e => updateMobileOTP(e.target.value)} />
            <input className="btn btn-success" type="submit" value="Verify OTP" />
          </form>
        </>}
      </> : <>
        {!enableSuccessMsg ? <>
          <form id="registerForm" onSubmit={handleRegister}>
            <h5>Verified! Please help with additional details to serve you better</h5>
            <input className="form-control" type="text" placeholder='Name' value={name} style={{ width: "200px" }} onChange={e => updateName(e.target.value)} />
            <input className="form-control" type="text" placeholder='Pincode' value={pincode} style={{ width: "200px" }} onChange={e => updatePincode(e.target.value)} />
            <input className="form-control" type="text" placeholder='Email Id (Optional)' value={emailId} style={{ width: "200px" }} onChange={e => updateEmailId(e.target.value)} />
            <button className="btn btn-success" onClick={handleRegister}>Register</button>
          </form>
        </> : <>
          <h5>Now your account is ready</h5>
          <a href={backPath}>Click here to explore</a>

        </>}

      </>}


    </div>
  );
}