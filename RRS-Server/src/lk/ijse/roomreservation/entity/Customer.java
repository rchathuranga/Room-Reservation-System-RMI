package lk.ijse.roomreservation.entity;

public class Customer {
    private String nic;
    private String name;
    private String contact;
    private String address;

    public Customer() {
    }

    public Customer(String nic, String name, String contact, String address) {
        this.nic = nic;
        this.name = name;
        this.contact = contact;
        this.address = address;
    }

    public Customer(String nic) {
        this.nic = nic;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
