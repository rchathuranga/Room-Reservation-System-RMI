package lk.ijse.roomreservation.entity;

public class Room {
    private int id;
    private boolean isWithAC;
    private int capacity;

    public Room() {
    }

    public Room(int id, boolean isWithAC, int capacity) {
        this.id = id;
        this.isWithAC = isWithAC;
        this.capacity = capacity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isWithAC() {
        return isWithAC;
    }

    public void setWithAC(boolean withAC) {
        this.isWithAC = withAC;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
