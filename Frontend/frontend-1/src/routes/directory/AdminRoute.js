
import AdminDashboard from "../../views/components/admin/dashboard/AdminDashboard";
import AdminHome from "../../views/components/admin/home/AdminHome";
import { RenderRoutes } from "../Routes";

const SupportRoute = {
    path: "/admin",
    key: "ADMIN",
    component: props => {
        return <RenderRoutes {...props} />;
    },
    routes: [
        {
            path: "/admin",
            key: "ADMIN_ROOT",
            exact: true,
            component: AdminHome
        },
        {
            path: "/admin/dashboard",
            key: "ADMIN_DASHBOARD_ROOT",
            exact: true,
            component: AdminDashboard
        }
    ],
}

export default SupportRoute;