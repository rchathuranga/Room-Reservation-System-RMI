package lk.ijse.roomreservation.bo.custom;

import lk.ijse.roomreservation.bo.SuperBO;
import lk.ijse.roomreservation.dto.*;

import java.util.ArrayList;

public interface ManagerBO extends SuperBO {

    CustomerDTO searchCustomer(String s)throws Exception;

    boolean reserveRooms(ReservationDTO reservation) throws Exception;

    boolean addRoom(RoomDTO room) throws Exception;
    boolean updateRoom(RoomDTO room) throws Exception;
    boolean deleteRoom(int id) throws Exception;
    RoomDTO searchRoom(int id) throws Exception;
    ArrayList<RoomDTO> getAllRooms() throws Exception;
    ArrayList<RoomDTO> searchRoomByAC(boolean b) throws Exception;
    ArrayList<RoomDTO> searchRoomByCapacity(int c) throws Exception;
    ArrayList<CustomDTO> getAvailableRooms(CustomDTO c) throws Exception;

    boolean addMealPackage(MealPackageDTO mealPackage) throws Exception;
    boolean updateMealPackage(MealPackageDTO mealPackage) throws Exception;
    boolean deleteMealPackage(int id) throws Exception;
    MealPackageDTO searchMealPackage(int id) throws Exception;
    ArrayList<MealPackageDTO> getAllMealPackage() throws Exception;
}
