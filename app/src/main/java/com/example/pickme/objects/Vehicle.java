package com.example.pickme.objects;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Vehicle implements Parcelable {
    private String saccoName;
    private String regNumber;
    private String nameOfConductor;
    private Terminus origin;
    private Terminus destination;
    private Float farePrice;
    private Route route;
    private Integer totalCapacity;
    private Integer noOfPassOnBoard;
    private Integer noOfBookings;
    private Integer imageUrl;
    private ArrayList<PickUpPoint> pickUpPoints;

    protected Vehicle(Parcel in) {
        saccoName = in.readString();
        regNumber = in.readString();
        nameOfConductor = in.readString();
        if (in.readByte() == 0) {
            farePrice = null;
        } else {
            farePrice = in.readFloat();
        }
        if (in.readByte() == 0) {
            imageUrl = null;
        } else {
            imageUrl = in.readInt();
        }
    }

    public static final Creator<Vehicle> CREATOR = new Creator<Vehicle>() {
        @Override
        public Vehicle createFromParcel(Parcel in) {
            return new Vehicle(in);
        }

        @Override
        public Vehicle[] newArray(int size) {
            return new Vehicle[size];
        }
    };

    public String getStringRepr(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.saccoName).append("\t").append(this.regNumber).append("\n");
        stringBuilder.append("Route: ").append(this.route.getRouteName()).append("\n");
        stringBuilder.append("Fare : ").append(this.farePrice);
        return stringBuilder.toString();
    }

    public String getSaccoName() {
        return saccoName;
    }

    public void setSaccoName(String saccoName) {
        this.saccoName = saccoName;
    }

    public String getNumberPlate() {
        return regNumber;
    }

    public void setNumberPlate(String regNumber) {
        this.regNumber = regNumber;
    }

    public Integer getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Integer imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getNameOfConductor() {
        return nameOfConductor;
    }

    public void setNameOfConductor(String nameOfConductor) {
        this.nameOfConductor = nameOfConductor;
    }

    public Terminus getOrigin() {
        return origin;
    }

    public void setOrigin(Terminus origin) {
        this.origin = origin;
    }

    public Terminus getDestination() {
        return destination;
    }

    public void setDestination(Terminus destination) {
        this.destination = destination;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public Float getFarePrice() {
        return farePrice;
    }

    public void setFarePrice(Float farePrice) {
        this.farePrice = farePrice;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Integer getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(Integer totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    public Integer getNoOfPassOnBoard() {
        return noOfPassOnBoard;
    }

    public void setNoOfPassOnBoard(Integer noOfPassOnBoard) {
        this.noOfPassOnBoard = noOfPassOnBoard;
    }

    public ArrayList<PickUpPoint> getPickUpPoints() {
        return pickUpPoints;
    }

    public void setPickUpPoints(ArrayList<PickUpPoint> pickUpPoints) {
        this.pickUpPoints = pickUpPoints;
    }

    public Integer getNoOfBookings() {
        return noOfBookings;
    }

    public void setNoOfBookings(Integer noOfBookings) {
        this.noOfBookings = noOfBookings;
    }

    public Vehicle(String saccoName, String regNumber, String nameOfConductor) {
        this.saccoName = saccoName;
        this.regNumber = regNumber;
        this.nameOfConductor = nameOfConductor;
        this.noOfBookings = 0;
    }

    public Vehicle(String saccoName, String regNumber, String nameOfConductor, Terminus origin,
                   Terminus destination, Route route) {
        this.saccoName = saccoName;
        this.regNumber = regNumber;
        this.nameOfConductor = nameOfConductor;
        this.origin = origin;
        this.destination = destination;
        this.route = route;
        this.noOfBookings = 0;
    }

    public Vehicle(String saccoName, String regNumber,
                   Integer imageUrl, String nameOfConductor, Terminus origin,
                   Terminus destination, Float farePrice, Route route,
                   Integer totalCapacity) {
        this.saccoName = saccoName;
        this.regNumber = regNumber;
        this.imageUrl = imageUrl;
        this.nameOfConductor = nameOfConductor;
        this.origin = origin;
        this.destination = destination;
        this.farePrice = farePrice;
        this.route = route;
        this.totalCapacity = totalCapacity;
        this.noOfPassOnBoard = Math.round(this.totalCapacity/3 + 2);
        this.noOfBookings = 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(saccoName);
        dest.writeString(regNumber);
        dest.writeString(nameOfConductor);
        if (farePrice == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeFloat(farePrice);
        }
        if (imageUrl == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(imageUrl);
        }
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "saccoName='" + saccoName + '\'' +
                ", regNumber='" + regNumber + '\'' +
                ", nameOfConductor='" + nameOfConductor + '\'' +
                ", origin=" + origin +
                ", destination=" + destination +
                ", farePrice=" + farePrice +
                ", route=" + route +
                ", totalCapacity=" + totalCapacity +
                ", noOfPassOnBoard=" + noOfPassOnBoard +
                ", imageUrl=" + imageUrl +
                ", pickUpPoints=" + pickUpPoints +
                '}';
    }
}
