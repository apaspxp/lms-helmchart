import React from "react";
import { Link } from "react-router-dom";
import ROUTES from '../../routes/Routes';

function AllRoutes() {
    return (
        <div>
            <div style={{ flex: 0.3, backgroundColor: "#f2f2f2", textAlign: "left" }}>
                {displayRouteMenu(ROUTES)}
            </div>
        </div>
    );
}

export default AllRoutes;


function displayRouteMenu(routes) {
    function singleRoute(route) {
        return (
            <li key={route.key}>
                <Link to={route.path}>
                    {route.key} ({route.path})
                </Link>
            </li>
        );
    }
    return (
        <ul>
            {routes.map(route => {
                //sub-routes
                if (route.routes) {
                    return (
                        <React.Fragment key={route.key}>
                            {singleRoute(route)}
                            {displayRouteMenu(route.routes)}
                        </React.Fragment>
                    );
                }

                //single route
                return singleRoute(route);
            })}
        </ul>
    );
}