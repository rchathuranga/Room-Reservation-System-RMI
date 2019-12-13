package lk.ijse.roomreservation.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class ReservationDTO implements Serializable {
    private int id;
    private CustomerDTO customer;
    private int noOFAdult;
    private int noOfKids;
    private ArrayList<ReservationDetailDTO> reservations;
    private int packageId;


    public ReservationDTO() {
    }

    public ReservationDTO(CustomerDTO customer, int noOFAdult, int noOfKids, ArrayList<ReservationDetailDTO> reservations, int packageId) {
        this.customer = customer;
        this.noOFAdult = noOFAdult;
        this.noOfKids = noOfKids;
        this.reservations = reservations;
        this.packageId = packageId;
    }

    public ReservationDTO(int id, CustomerDTO customer, int noOFAdult, int noOfKids, ArrayList<ReservationDetailDTO> reservations, int packageId) {
        this.id = id;
        this.customer = customer;
        this.noOFAdult = noOFAdult;
        this.noOfKids = noOfKids;
        this.reservations = reservations;
        this.packageId = packageId;
    }

    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public int getNoOFAdult() {
        return noOFAdult;
    }

    public void setNoOFAdult(int noOFAdult) {
        this.noOFAdult = noOFAdult;
    }

    public int getNoOfKids() {
        return noOfKids;
    }

    public void setNoOfKids(int noOfKids) {
        this.noOfKids = noOfKids;
    }

    public ArrayList<ReservationDetailDTO> getReservations() {
        return reservations;
    }

    public void setReservations(ArrayList<ReservationDetailDTO> reservations) {
        this.reservations = reservations;
    }
}
