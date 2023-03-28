import CreateEmployee from './template/CreateEmployee';
import HolidayCalendar from './template/HolidayCalendar';
import ChangeManager from './template/ChangeManager';
import ChangeStatus from './template/ChangeStatus';
import Sample from './template/Sample';

const dashboardFields = [
    {
        key: "addEmployee",
        title: "Add Employee",
        cardBody: <CreateEmployee/>
    }, {
        key: "changeStatus",
        title: "Change status",
        cardBody: <ChangeStatus/>

    }, {
        key: "changeManager",
        title: "Change Manager",
        cardBody: <ChangeManager/>
    }, {
        key: "holidayCalendar",
        title: "Holiday Calendar",
        cardBody: <HolidayCalendar/>
    }, {
        key: "sample",
        title: "Sample",
        cardBody: <Sample/>
    }
]

export default dashboardFields;
