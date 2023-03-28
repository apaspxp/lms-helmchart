
import Support from "../../views/components/support/Support";
import { RenderRoutes } from "../Routes";

const SupportRoute = {
    path: "/support",
    key: "SUPPORT",
    component: props => {
        return <RenderRoutes {...props} />;
    },
    routes: [
        {
            path: "/support",
            key: "SUPPORT_ROOT",
            exact: true,
            component: Support
        }
    ],
}

export default SupportRoute;