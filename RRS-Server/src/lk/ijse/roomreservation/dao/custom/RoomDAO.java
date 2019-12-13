package lk.ijse.roomreservation.dao.custom;

import lk.ijse.roomreservation.dao.CrudDAO;
import lk.ijse.roomreservation.entity.Room;

import java.util.ArrayList;

public interface RoomDAO extends CrudDAO<Room,Integer> {
    ArrayList<Room> searchByAC(boolean b) throws Exception;
    ArrayList<Room> searchByCapacity(int c) throws Exception;
    ArrayList<Room> search(Room r) throws Exception;
}
