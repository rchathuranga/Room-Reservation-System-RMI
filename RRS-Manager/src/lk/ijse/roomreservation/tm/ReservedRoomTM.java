package lk.ijse.roomreservation.tm;

import java.time.LocalDate;

public class ReservedRoomTM {
    private int roomid;
    private boolean withAC;
    private LocalDate startDate;
    private LocalDate endDate;

    public ReservedRoomTM(int roomid, boolean withAC, LocalDate startDate, LocalDate endDate) {
        this.roomid = roomid;
        this.withAC = withAC;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getRoomid() {
        return roomid;
    }

    public void setRoomid(int roomid) {
        this.roomid = roomid;
    }

    public boolean isWithAC() {
        return withAC;
    }

    public void setWithAC(boolean withAC) {
        this.withAC = withAC;
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
