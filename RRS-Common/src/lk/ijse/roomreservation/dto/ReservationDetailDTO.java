package lk.ijse.roomreservation.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class ReservationDetailDTO implements Serializable {
    private int id;
    private int reservationId;
    private int roomId;
    private LocalDate startDate;
    private LocalDate endDate;

    public ReservationDetailDTO() {
    }

    public ReservationDetailDTO(int reservationId, int roomId, LocalDate startDate, LocalDate endDate) {
        this.reservationId = reservationId;
        this.roomId = roomId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public ReservationDetailDTO(int id, int reservationId, int roomId, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.reservationId = reservationId;
        this.roomId = roomId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
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
