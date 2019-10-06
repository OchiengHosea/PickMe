package com.example.pickme.objects;

import com.mapbox.geojson.LineString;

public class Route {
    private String routeId;
    private LineString routeGeom;
    private String routeName;

    public Route(String routeId, LineString routeGeom, String routeName) {
        this.routeId = routeId;
        this.routeGeom = routeGeom;
        this.routeName = routeName;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public LineString getRouteGeom() {
        return routeGeom;
    }

    public void setRouteGeom(LineString routeGeom) {
        this.routeGeom = routeGeom;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    @Override
    public String toString() {
        return "Route{" +
                "routeId='" + routeId + '\'' +
                ", routeGeom=" + routeGeom +
                ", routeName='" + routeName + '\'' +
                '}';
    }
}
