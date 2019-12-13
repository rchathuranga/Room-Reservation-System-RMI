package lk.ijse.roomreservation.entity;

public class Reservation {
    private int id;
    private String customerNIC;
    private int noOFAdult;
    private int noOfKids;
    private int packageId;


    public Reservation() {
    }

    public Reservation(int id, String customerNIC, int noOFAdult, int noOfKids, int packageId) {
        this.id = id;
        this.customerNIC = customerNIC;
        this.noOFAdult = noOFAdult;
        this.noOfKids = noOfKids;
        this.packageId = packageId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerNIC() {
        return customerNIC;
    }

    public void setCustomerNIC(String customerNIC) {
        this.customerNIC = customerNIC;
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

    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }
}
