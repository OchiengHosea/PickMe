package com.example.pickme.objects;

import com.mapbox.geojson.Point;

public class Terminus {
    private Integer id;
    private String name;
    private Point terminusPoint;
    

    public Terminus() {
    }

    public Terminus(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Terminus(Integer id, Integer routeNumber, String name, Point terminusPoint) {
        this.id = id;
        this.name = name;
        this.terminusPoint = terminusPoint;
    }

    public Terminus(String name) {
        this.name = name;
    }

    public Terminus(Point terminusPoint) {
        this.terminusPoint = terminusPoint;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Point getTerminusPoint() {
        return terminusPoint;
    }

    public void setTerminusPoint(Point terminusPoint) {
        this.terminusPoint = terminusPoint;
    }

    @Override
    public String toString() {
        return "Terminus{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", terminusPoint=" + terminusPoint +
                '}';
    }
}
