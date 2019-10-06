package com.example.pickme.objects;

import java.util.Date;

public class Reservation {
    private Vehicle vehicle;
    private PickUpPoint pickUpPoint;
    private Long dateOfReservation;
    private Integer numberOfSits;
    private String status;
    private Float totalCash;

    public Reservation() {
    }

    public Reservation(Vehicle vehicle, PickUpPoint pickUpPoint, Integer numberOfSits) {
        this.vehicle = vehicle;
        this.pickUpPoint = pickUpPoint;
        this.numberOfSits = numberOfSits;
        this.status = "Initialized";
        this.dateOfReservation = new Date().getTime();
        this.totalCash = this.vehicle.getFarePrice() * this.numberOfSits;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Integer getNumberOfSits() {
        return numberOfSits;
    }

    public void setNumberOfSits(Integer numberOfSits) {
        this.numberOfSits = numberOfSits;
        this.totalCash = this.vehicle.getFarePrice() * this.numberOfSits;
    }

    public PickUpPoint getPickUpPoint() {
        return pickUpPoint;
    }

    public void setPickUpPoint(PickUpPoint pickUpPoint) {
        this.pickUpPoint = pickUpPoint;
    }

    public Long getDateOfReservation() {
        return dateOfReservation;
    }

    public void setDateOfReservation(Long dateOfReservation) {
        this.dateOfReservation = dateOfReservation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Float getTotalCash() {
        return totalCash;
    }

    public void setTotalCash(Float totalCash) {
        this.totalCash = totalCash;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "vehicle=" + vehicle +
                ", pickUpPoint=" + pickUpPoint +
                ", dateOfReservation=" + dateOfReservation +
                ", numberOfSits=" + numberOfSits +
                ", status='" + status + '\'' +
                ", totalCash=" + totalCash +
                '}';
    }
}
