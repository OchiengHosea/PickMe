package com.example.pickme.objects;

import com.mapbox.geojson.Point;

public class PickUpPoint {
    private String name;
    private Point location;

    public PickUpPoint() {
    }

    public PickUpPoint(Point location) {
        this.location = location;
    }

    public PickUpPoint(String name, Point location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }


}
