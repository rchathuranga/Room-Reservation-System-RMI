package lk.ijse.roomreservation.dao.custom;

import lk.ijse.roomreservation.dao.CrudDAO;
import lk.ijse.roomreservation.entity.Reservation;

public interface ReservationDAO extends CrudDAO<Reservation,Integer> {
    int getLastID() throws Exception;
}
