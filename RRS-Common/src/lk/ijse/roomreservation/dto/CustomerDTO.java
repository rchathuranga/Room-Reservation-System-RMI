package lk.ijse.roomreservation.dto;

import java.io.Serializable;

public class CustomerDTO implements Serializable {
    private String nic;
    private String name;
    private String contact;
    private String address;
    private boolean isExist;

    public CustomerDTO() {
    }

    public CustomerDTO(String nic) {
        this.nic = nic;
    }

    public CustomerDTO(String nic, String name, String contact, String address) {
        this.nic = nic;
        this.name = name;
        this.contact = contact;
        this.address = address;
    }

    public CustomerDTO(String nic, String name, String contact, String address, boolean isExist) {
        this.nic = nic;
        this.name = name;
        this.contact = contact;
        this.address = address;
        this.isExist = isExist;
    }

    public boolean isExist() {
        return isExist;
    }

    public void setExist(boolean exist) {
        isExist = exist;
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
