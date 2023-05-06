import React, { useState } from 'react';
import axios from 'axios';

function SwipeInOut() {
  const [isSwipedIn, setIsSwipedIn] = useState(false);

  const handleSwipeIn = () => {
    axios.post('http://localhost:8002/lms-attendance-service/api/swipe?option=in')
      .then(response => {
        console.log(response.data);
        setIsSwipedIn(true);
      })
      .catch(error => {
        console.log(error);
      });
  }

  
  const handleSwipeOut = () => {
    axios.post('http://localhost:8002/lms-attendance-service/api/swipe?option=out')
      .then(response => {
        console.log(response.data);
        setIsSwipedIn(false);
      })
      .catch(error => {
        console.log(error);
      });
  }

  return (
    <>
      {isSwipedIn ? (
        <button className="btn btn-success m-2" onClick={handleSwipeOut}>Swipe Out</button>
      ) : (
        <button className="btn btn-success m-2" onClick={handleSwipeIn}>Swipe In</button>
      )}
    </>
  );
}

export default SwipeInOut;
