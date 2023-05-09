var url = {
    local: 'http://localhost:8000/',
    attendance_service: 'http://localhost:8002/'
}

export const apiConfig = {
    downloadBlankTemplate: url.local + 'api/download_blank_template',
    uploadHolidayCalendar: url.local + 'api/uploadHolidayCalendar',
    swipeIn: url.attendance_service + 'lms-attendance-service/api/swipe?option=in',
    swipeOut: url.attendance_service + 'lms-attendance-service/api/swipe?option=out',
}

