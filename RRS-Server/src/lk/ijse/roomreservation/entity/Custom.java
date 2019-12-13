package lk.ijse.roomreservation.entity;

import java.time.LocalDate;

public class Custom {
    private int roomId;
    private boolean withAC;
    private int capacity;
    private LocalDate startDate;
    private LocalDate endDate;

    public Custom(int roomId, boolean withAC, int capacity) {
        this.roomId = roomId;
        this.withAC = withAC;
        this.capacity = capacity;
    }

    public Custom(boolean withAC, int capacity, LocalDate startDate, LocalDate endDate) {
        this.withAC = withAC;
        this.capacity = capacity;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Custom(int roomId, boolean withAC, int capacity, LocalDate startDate, LocalDate endDate) {
        this.roomId = roomId;
        this.withAC = withAC;
        this.capacity = capacity;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Custom() {
    }


    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
