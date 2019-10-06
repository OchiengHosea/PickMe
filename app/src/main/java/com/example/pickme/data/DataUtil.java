package com.example.pickme.data;

import android.location.Location;

import com.example.pickme.R;
import com.example.pickme.objects.PickUpPoint;
import com.example.pickme.objects.Reservation;
import com.example.pickme.objects.Route;
import com.example.pickme.objects.Terminus;
import com.example.pickme.objects.Vehicle;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.LineString;
import com.mapbox.geojson.Point;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class DataUtil {
    public static ArrayList<Vehicle> vehicles;
    public static ArrayList<Reservation> reservations;
    public static ArrayList<Terminus> getAllCbdTerminals() {
        return new ArrayList<Terminus>(Arrays.asList(
                new Terminus(1, 12, "Afya Center", Point.fromLngLat(36.4834, 0.2345)),
                new Terminus(1, 12, "Mfaangano Street", Point.fromLngLat(36.3834, 0.3245)),
                new Terminus(1, 12, "Fire Station", Point.fromLngLat(36.4334, 0.1345)),
                new Terminus(1, 12, "Odeon", Point.fromLngLat(36.4134, 0.3545))
        ));
    }

    public static ArrayList<Terminus> getAllLocalTerminals() {
        return new ArrayList<Terminus>(Arrays.asList(
                new Terminus(1, 12, "Thika", Point.fromLngLat(36.7834, 0.2545)),
                new Terminus(1, 12, "Langata", Point.fromLngLat(36.6834, 0.2145)),
                new Terminus(1, 12, "Babadogo", Point.fromLngLat(36.5834, 0.1745)),
                new Terminus(1, 12, "Ruiru", Point.fromLngLat(36.2834, 0.2745))
        ));
    }

    public static void initVehicles() {
        vehicles =  new ArrayList<Vehicle>(Arrays.asList(
                new Vehicle("Kenya Mpya", "KCF 245G", R.drawable.kenyampya, "Marto", getCbdTerminus(2), getLocalTerminus(0), 80.00f, getVehicleRoute("Nairobi(CBD) - Thika"), 55),
                new Vehicle("Manchester", "KBF 146A", R.drawable.manchester, "Ngugi", getCbdTerminus(1), getLocalTerminus(0), 100.00f, getVehicleRoute("Nairobi(CBD) - Thika"),16),
                new Vehicle("Lucky Transporters", "KAF 355K",  R.drawable.luckytransporters, "Mutua", getCbdTerminus(0), getLocalTerminus(1), 70.00f, getVehicleRoute("Nairobi(CBD) - Langata"), 14),
                new Vehicle("Lopha Travellers", "KCB 115P", R.drawable.manchester, "Ojwang", getCbdTerminus(3), getLocalTerminus(3), 50.00f, getVehicleRoute("Nairobi(CBD) - Juja"), 45)
        ));
    }

    public static ArrayList<PickUpPoint> cbdToThikaPickUpPoints() {
        return new ArrayList<>(Arrays.asList(
           new PickUpPoint("CBD",Point.fromLngLat(36.4345,0.4343)),
                new PickUpPoint("Juja",Point.fromLngLat(36.3345,0.2343)),
                new PickUpPoint("High Point",Point.fromLngLat(36.43545,0.4243)),
                new PickUpPoint("Mangu",Point.fromLngLat(36.3545,0.6243)),
                new PickUpPoint("Witeithie",Point.fromLngLat(36.43545,0.4243)),
                new PickUpPoint("Jomoko",Point.fromLngLat(36.44545,0.1243)),
                new PickUpPoint("Cravers",Point.fromLngLat(36.43545,0.6243)),
                new PickUpPoint("Junction",Point.fromLngLat(36.13545,0.5243)),
                new PickUpPoint("Thika",Point.fromLngLat(36.3245,0.6243))

        ));
    }

    public static ArrayList<PickUpPoint> cbdToJujaPickUpPoints() {
        return new ArrayList<>(Arrays.asList(
                new PickUpPoint("CBD",Point.fromLngLat(36.4345,0.4343)),
                new PickUpPoint("AllSops",Point.fromLngLat(36.4345,0.2943)),
                new PickUpPoint("Githurai",Point.fromLngLat(36.1345,0.3043)),
                new PickUpPoint("Clay Products",Point.fromLngLat(36.1545,0.2430)),
                new PickUpPoint("KU",Point.fromLngLat(36.4845,0.3283)),
                new PickUpPoint("Juja",Point.fromLngLat(36.13545,0.6243)),
                new PickUpPoint("Sewage",Point.fromLngLat(36.3345,0.3043))
        ));
    }

    public static ArrayList<PickUpPoint> cbdToLangataPickUpPoints() {
        return new ArrayList<>(Arrays.asList(
                new PickUpPoint("CBD",Point.fromLngLat(36.4345,0.4343)),
                new PickUpPoint("Nyayo",Point.fromLngLat(36.2345,0.3643)),
                new PickUpPoint("Wislon",Point.fromLngLat(36.43145,0.5243)),
                new PickUpPoint("Dam",Point.fromLngLat(36.31545,0.4243)),
                new PickUpPoint("Carnivore",Point.fromLngLat(36.4545,0.3243)),
                new PickUpPoint("Langata",Point.fromLngLat(36.53545,0.6243))

        ));
    }

    public static ArrayList<Point> getPickUpPointGeoms(ArrayList<PickUpPoint> pickUpPointArrayList) {
        ArrayList<Point> points = new ArrayList<>();
        Iterator<PickUpPoint> pickUpPointIterator = pickUpPointArrayList.iterator();
        while (pickUpPointIterator.hasNext()){
            PickUpPoint pkPt = pickUpPointIterator.next();
            points.add(pkPt.getLocation());
        }

        return  points;
    }

    public static Route getVehicleRoute(String routeName) {
        LineString route1Geom;
        String routeId = "";
        switch (routeName) {
            case "Nairobi(CBD) - Langata": {
                route1Geom = LineString.fromLngLats(getPickUpPointGeoms(cbdToLangataPickUpPoints()));
                routeId = "15B";
            }
            break;
            case "Nairobi(CBD) - Juja": {
                route1Geom = LineString.fromLngLats(getPickUpPointGeoms(cbdToJujaPickUpPoints()));
                routeId = "236";
            }
            break;
            default: {
                route1Geom = LineString.fromLngLats(getPickUpPointGeoms(cbdToThikaPickUpPoints()));
                routeId = "237";
            }
        }
        return new Route(routeId, route1Geom, routeName);
    }

    public static Terminus getCbdTerminus(int id){
        return  getAllCbdTerminals().get(id);
    }

    public static Terminus getLocalTerminus(int id){
        return  getAllLocalTerminals().get(id);
    }

    public static ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    public static void setVehicles(ArrayList<Vehicle> vehicles) {
        DataUtil.vehicles = vehicles;
    }

    public static void updateVehicle(Vehicle vehicle) {
        int index = vehicles.indexOf(vehicle);
        DataUtil.vehicles.set(index, vehicle);
    }
}
