import { Route, Switch } from "react-router-dom";
import NotFound from '../views/NotFound/NotFound';
import ProfileRoute from './directory/ProfileRoute';
import DefaultRoute from "./directory/DefaultRoute";
import LoginRoute from "./directory/LoginRoute";
import HomeRoute from "./directory/HomeRoute";
import SupportRoute from "./directory/SupportRoute";
import AdminRoute from "./directory/AdminRoute";


//All Routes
const ROUTES = [DefaultRoute, AdminRoute, LoginRoute, HomeRoute, ProfileRoute, SupportRoute];

export default ROUTES;

//Utility
export function RouteWithSubRoutes(route) {
    return (
        <Route
            path={route.path}
            exact={route.exact}
            render={props => <route.component {...props} routes={route.routes} />}
        />
    );
}

export function RenderRoutes({ routes }) {
    return (
        <Switch>
            {routes.map((route, i) => {
                return <RouteWithSubRoutes key={route.key} {...route} />;
            })}
            <Route component={NotFound} />
        </Switch>
    );
}