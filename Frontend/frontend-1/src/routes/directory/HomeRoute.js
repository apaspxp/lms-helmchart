
import AllRoutes from "../../views/components/Directory";
import Home from "../../views/components/home/Home";
import { RenderRoutes } from "../Routes";

const HomeRoute = {
    path: "/home",
    key: "HOME",
    component: props => {
        return <RenderRoutes {...props} />;
    },
    routes: [
        {
            path: "/home",
            key: "HOME_ROOT",
            exact: true,
            component: Home,
        },
        {
            path: "/home/directory",
            key: "ROUTES_ROOT",
            exact: true,
            component: AllRoutes
        }
    ],
}

export default HomeRoute;