import React, { useState } from 'react';
import { apiConfig } from '../../../api/api.config';
import axios from 'axios';

function SwipeInOut({loader}) {
  const [isSwipedIn, setIsSwipedIn] = useState(false);

  const handleSwipeIn = () => {
    axios.post(apiConfig.swipeIn)
      .then(response => {
        console.log(response.data);
        setIsSwipedIn(true);
        loader(false);
      })
      .catch(error => {
        console.log(error);
        loader(false);
      });
  }

  
  const handleSwipeOut = () => {
    axios.post(apiConfig.swipeOut)
      .then(response => {
        console.log(response.data);
        setIsSwipedIn(false);
        loader(false);
      })
      .catch(error => {
        console.log(error);
        loader(false);
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
