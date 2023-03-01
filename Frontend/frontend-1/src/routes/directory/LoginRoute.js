
import Login from "../../views/components/login/Login";
import Register from "../../views/components/register/Register";
import { RenderRoutes, RouteWithSubRoutes } from "../Routes";

const LoginRoute = {
    path: "/login",
    key: "LOGIN",
    component: props => {
        return <RenderRoutes {...props} />;
    },
    routes: [
        {
            path: "/login",
            key: "LOGIN_ROOT",
            exact: true,
            component: Login,
        },
        {
            path: "/login/register",
            key: "LOGIN_REGISTER",
            exact: true,
            component: Register,
        },
    ],
}

export default LoginRoute;