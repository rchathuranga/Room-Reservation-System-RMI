package lk.ijse.roomreservation.service.custom;


import lk.ijse.roomreservation.bo.BOFactory;
import lk.ijse.roomreservation.bo.custom.ManagerBO;
import lk.ijse.roomreservation.dto.*;
import lk.ijse.roomreservation.observer.Observer;
import lk.ijse.roomreservation.observer.ObserverImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ManagerServiceImpl extends UnicastRemoteObject implements ManagerService {

    ManagerBO managerBO= (ManagerBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.MANAGERS);

    public ManagerServiceImpl() throws RemoteException {
    }


    @Override
    public boolean reserveRooms(ReservationDTO reservationDTO) throws Exception {
        return managerBO.reserveRooms(reservationDTO);
    }

    @Override
    public boolean addRoom(RoomDTO roomDTO) throws Exception {
        return managerBO.addRoom(roomDTO);
    }

    @Override
    public boolean updateRoom(RoomDTO roomDTO) throws Exception {
        return managerBO.updateRoom(roomDTO);
    }

    @Override
    public boolean deleteRoom(int i) throws Exception {
        return managerBO.deleteRoom(i);
    }

    @Override
    public RoomDTO searchRoom(int id) throws Exception {
        return managerBO.searchRoom(id);
    }

    @Override
    public ArrayList<RoomDTO> getSearchRoom(RoomDTO roomDTO) throws Exception {
        return null;
    }

    @Override
    public ArrayList<RoomDTO> getAllRooms() throws Exception {
        return managerBO.getAllRooms();
    }

    @Override
    public ArrayList<MealPackageDTO> getAllMealPackage() throws Exception {
        return managerBO.getAllMealPackage();
    }

    @Override
    public CustomerDTO searchCustomer(String s) throws Exception {
        return managerBO.searchCustomer(s);
    }

    @Override
    public ArrayList<RoomDTO> searchRoomByAC(boolean b) throws Exception {
        return managerBO.searchRoomByAC(b);
    }

    @Override
    public ArrayList<RoomDTO> searchRoomByCapacity(int i) throws Exception {
        return managerBO.searchRoomByCapacity(i);
    }

    @Override
    public ArrayList<CustomDTO> getAvailableRooms(CustomDTO customDTO) throws Exception {
        return managerBO.getAvailableRooms(customDTO);
    }

    @Override
    public boolean addMealPackage(MealPackageDTO mealPackageDTO) throws Exception {
        return managerBO.addMealPackage(mealPackageDTO);
    }

    @Override
    public boolean updateMealPackage(MealPackageDTO mealPackageDTO) throws Exception {
        return managerBO.updateMealPackage(mealPackageDTO);
    }

    @Override
    public boolean deleteMealPackage(int i) throws Exception {
        return managerBO.deleteMealPackage(i);
    }

    @Override
    public MealPackageDTO searchMealPackage(int i) throws Exception {
        return managerBO.searchMealPackage(i);
    }

    @Override
    public void register(Observer observer) throws Exception {
        new ObserverImpl().register(observer);
    }

    @Override
    public void unregister(Observer observer) throws Exception {
        new ObserverImpl().unregister(observer);
    }

    @Override
    public void notifyObservers() throws Exception {
        new ObserverImpl().notifyObservers();
    }
}
