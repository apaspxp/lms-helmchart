package com.lms.attendanceservice.service.interfaces;

import com.lms.attendanceservice.responses.Response;
import com.lms.attendanceservice.responses.SwipeResponse;

public interface ISwipeService {
    public Response<SwipeResponse> swipe(String swipeOption);
}
