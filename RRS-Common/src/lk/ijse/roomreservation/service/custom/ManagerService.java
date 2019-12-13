package lk.ijse.roomreservation.service.custom;

import lk.ijse.roomreservation.dto.*;
import lk.ijse.roomreservation.service.SuperService;

import java.util.ArrayList;

public interface ManagerService extends SuperService {

    CustomerDTO searchCustomer(String nic) throws Exception;

    boolean reserveRooms(ReservationDTO reservation) throws Exception;

    boolean addRoom(RoomDTO room) throws Exception;
    boolean updateRoom(RoomDTO room) throws Exception;
    boolean deleteRoom(int id) throws Exception;
    RoomDTO searchRoom(int id) throws Exception;
    ArrayList<RoomDTO> getAllRooms() throws Exception;
    ArrayList<RoomDTO> getSearchRoom(RoomDTO room) throws Exception;
    ArrayList<RoomDTO> searchRoomByAC(boolean b) throws Exception;
    ArrayList<RoomDTO> searchRoomByCapacity(int c) throws Exception;
    ArrayList<CustomDTO> getAvailableRooms(CustomDTO c) throws Exception;

    boolean addMealPackage(MealPackageDTO mealPackage) throws Exception;
    boolean updateMealPackage(MealPackageDTO mealPackage) throws Exception;
    boolean deleteMealPackage(int id) throws Exception;
    MealPackageDTO searchMealPackage(int id) throws Exception;
    ArrayList<MealPackageDTO> getAllMealPackage() throws Exception;
}
