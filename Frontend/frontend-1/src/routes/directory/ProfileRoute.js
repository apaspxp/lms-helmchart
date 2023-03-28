
import Profile from "../../views/components/profile/Profile";
import { RenderRoutes } from "../Routes";
import { Redirect } from "react-router-dom";

const ProfileRoute = {
    path: "/profile",
    key: "PROFILE",
    component: props => {
        if (!localStorage.getItem("token")) {
            alert("You need to log in to access profile");
            return <Redirect to={{ pathname: "/login", backPath: ProfileRoute.path }} />
        }
        return <RenderRoutes {...props} />;
    },
    routes: [
        {
            path: "/profile",
            key: "PROFILE_ROOT",
            exact: true,
            component: Profile,
        },
        {
            path: "/profile/innerpage",
            key: "PROFILE_OTHER",
            exact: true,
            component: () => <h1>Profile other</h1>,
        },
    ],
}

export default ProfileRoute;