package lk.ijse.roomreservation.dao.custom;

import lk.ijse.roomreservation.dao.SuperDAO;
import lk.ijse.roomreservation.entity.Custom;

import java.util.ArrayList;

public interface QuaryDAO extends SuperDAO {
    ArrayList<Custom> getAllAvailableRooms(Custom c) throws Exception;
}
