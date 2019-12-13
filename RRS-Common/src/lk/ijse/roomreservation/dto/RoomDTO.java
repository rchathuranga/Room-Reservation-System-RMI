package lk.ijse.roomreservation.dto;

import java.io.Serializable;

public class RoomDTO implements Serializable {
    private int id;
    private boolean withAC;
    private int capacity;

    public RoomDTO() {
    }

    public RoomDTO(boolean withAC, int capacity) {
        this.withAC = withAC;
        this.capacity = capacity;
    }

    public RoomDTO(int id, boolean withAC, int capacity) {
        this.id = id;
        this.withAC = withAC;
        this.capacity = capacity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isWithAC() {
        return withAC;
    }

    public void setWithAC(boolean withAC) {
        this.withAC = withAC;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
