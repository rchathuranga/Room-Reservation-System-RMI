package lk.ijse.roomreservation.bo.custom.impl;

import lk.ijse.roomreservation.bo.custom.ManagerBO;
import lk.ijse.roomreservation.dao.DAOFactory;
import lk.ijse.roomreservation.dao.custom.*;
import lk.ijse.roomreservation.db.DBConnection;
import lk.ijse.roomreservation.dto.*;
import lk.ijse.roomreservation.entity.*;

import java.sql.Connection;
import java.util.ArrayList;

public class ManagerBOImpl implements ManagerBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    RoomDAO roomDAO = (RoomDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ROOM);
    ReservationDAO reservationDAO = (ReservationDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.RESERVATION);
    ReservationDetailDAO reservationDetailDAO = (ReservationDetailDAO)
            DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.RESERVATIONDETAIL);
    MealPackageDAO mealPackageDAO = (MealPackageDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.MEALPACKAGE);
    QuaryDAO quaryDAO = (QuaryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.QUARY);

    @Override
    public boolean reserveRooms(ReservationDTO r) {
        try (Connection con = DBConnection.getInstance().getConnection()) {
            con.setAutoCommit(false);
            customerDAO.setConnection(con);
            if (r.getCustomer().isExist()) {
                return reservationProcess(r, con);
            } else {
                CustomerDTO cus = r.getCustomer();
                boolean cusAdded = customerDAO.add(new Customer(cus.getNic(),
                        cus.getName(), cus.getContact(), cus.getAddress()));
                if (cusAdded) {
                    return reservationProcess(r, con);
                }
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean reservationProcess(ReservationDTO r, Connection con) throws Exception {
        reservationDAO.setConnection(con);
        boolean addReservation = reservationDAO.add(new Reservation(
                r.getId(), r.getCustomer().getNic(), r.getNoOFAdult(), r.getNoOfKids(), r.getPackageId()
        ));
        int reserveID = reservationDAO.getLastID();

        reservationDetailDAO.setConnection(con);
        boolean completed = false;
        if (addReservation) {
            for (ReservationDetailDTO rsd : r.getReservations()) {
                completed = reservationDetailDAO.add(new ReservationDetail(
                        rsd.getId(), reserveID, rsd.getRoomId(), rsd.getStartDate(), rsd.getEndDate()
                ));
            }
        }
        if (completed) {
            con.commit();
            return true;
        }
        con.rollback();
        con.setAutoCommit(true);
        return false;
    }

    @Override
    public boolean addRoom(RoomDTO r) throws Exception {
        try (Connection con = DBConnection.getInstance().getConnection()) {
            roomDAO.setConnection(con);
            return roomDAO.add(new Room(r.getId(), r.isWithAC(), r.getCapacity()));
        }
    }

    @Override
    public boolean updateRoom(RoomDTO room) throws Exception {
        try (Connection con = DBConnection.getInstance().getConnection()) {
            roomDAO.setConnection(con);
            return roomDAO.update(new Room(room.getId(), room.isWithAC(), room.getCapacity()));
        }
    }

    @Override
    public boolean deleteRoom(int id) throws Exception {
        try (Connection con = DBConnection.getInstance().getConnection()) {
            roomDAO.setConnection(con);
            return roomDAO.delete(id);
        }
    }

    @Override
    public RoomDTO searchRoom(int id) throws Exception{
        try (Connection con= DBConnection.getInstance().getConnection()) {
            roomDAO.setConnection(con);
            Room search = roomDAO.search(id);
            if(search!=null) {
                return new RoomDTO(search.getId(), search.isWithAC(), search.getCapacity());
            }
            return null;
        }
    }

    @Override
    public ArrayList<RoomDTO> getAllRooms() throws Exception {
        ArrayList<RoomDTO> arrayList = new ArrayList<>();
        try (Connection con = DBConnection.getInstance().getConnection()) {
            roomDAO.setConnection(con);
            ArrayList<Room> all = roomDAO.getAll();

            for (Room r : all) {
                arrayList.add(new RoomDTO(
                        r.getId(), r.isWithAC(), r.getCapacity()
                ));
            }
        }
        return arrayList;
    }

    @Override
    public ArrayList<MealPackageDTO> getAllMealPackage() throws Exception {
        ArrayList<MealPackageDTO> arrayList = new ArrayList<>();
        try (Connection con = DBConnection.getInstance().getConnection()) {
            mealPackageDAO.setConnection(con);
            ArrayList<MealPackage> all = mealPackageDAO.getAll();
            for (MealPackage mp : all) {
                arrayList.add(new MealPackageDTO(mp.getId(), mp.getPackageName(), mp.getBreakfast(),
                        mp.getLunch(), mp.getDinner()));
            }
        }
        return arrayList;
    }

    @Override
    public CustomerDTO searchCustomer(String s) throws Exception {
        CustomerDTO customer = null;
        try (Connection con = DBConnection.getInstance().getConnection()) {
            customerDAO.setConnection(con);
            Customer search = customerDAO.search(s);
            if (null != search) {
                customer = new CustomerDTO(search.getNic(),
                        search.getName(), search.getContact(),
                        search.getAddress(), true);
            }
        }
        return customer;
    }

    @Override
    public ArrayList<RoomDTO> searchRoomByAC(boolean b) throws Exception {
        ArrayList<RoomDTO> arrayList = new ArrayList<>();
        try (Connection con = DBConnection.getInstance().getConnection()) {
            roomDAO.setConnection(con);
            ArrayList<Room> all = roomDAO.searchByAC(b);

            for (Room r : all) {
                arrayList.add(new RoomDTO(
                        r.getId(), r.isWithAC(), r.getCapacity()
                ));
            }
        }
        return arrayList;
    }

    @Override
    public ArrayList<RoomDTO> searchRoomByCapacity(int c) throws Exception {
        ArrayList<RoomDTO> arrayList = new ArrayList<>();
        try (Connection con = DBConnection.getInstance().getConnection()) {
            roomDAO.setConnection(con);
            ArrayList<Room> all = roomDAO.searchByCapacity(c);
            for (Room r : all) {
                arrayList.add(new RoomDTO(
                        r.getId(), r.isWithAC(), r.getCapacity()
                ));
            }
        }
        return arrayList;
    }

    @Override
    public ArrayList<CustomDTO> getAvailableRooms(CustomDTO c) throws Exception {
        ArrayList<CustomDTO> arrayList = new ArrayList<>();
        try (Connection con = DBConnection.getInstance().getConnection()) {
            quaryDAO.setConnection(con);
            ArrayList<Custom> allAvailableRooms = quaryDAO.getAllAvailableRooms(
                    new Custom(c.isWithAC(), c.getCapacity(), c.getStartDate(), c.getEndDate())
            );
            for (Custom cus : allAvailableRooms) {
                arrayList.add(new CustomDTO(
                        cus.getRoomId(), cus.isWithAC(), cus.getCapacity()
                ));
            }
        }
        return arrayList;
    }

    public boolean addMealPackage(MealPackageDTO meal) throws Exception {
        try (Connection con = DBConnection.getInstance().getConnection()) {
            mealPackageDAO.setConnection(con);
            return mealPackageDAO.add(new MealPackage(
                    meal.getId(), meal.getPackageName(),
                    meal.getBreakfast(), meal.getLunch(),
                    meal.getDinner())
            );
        }
    }

    @Override
    public boolean updateMealPackage(MealPackageDTO pack) throws Exception {
        try (Connection con = DBConnection.getInstance().getConnection()) {
            mealPackageDAO.setConnection(con);
            return mealPackageDAO.update(new MealPackage(pack.getId(),pack.getPackageName(),
                    pack.getBreakfast(),pack.getLunch(),pack.getDinner()));
        }
    }

    @Override
    public boolean deleteMealPackage(int id) throws Exception {
        try (Connection con = DBConnection.getInstance().getConnection()) {
            mealPackageDAO.setConnection(con);
            return mealPackageDAO.delete(id);
        }
    }

    @Override
    public MealPackageDTO searchMealPackage(int id) throws Exception {
        try (Connection con = DBConnection.getInstance().getConnection()) {
            mealPackageDAO.setConnection(con);
            MealPackage search = mealPackageDAO.search(id);
            if(search!=null) {
                return new MealPackageDTO(search.getId(), search.getPackageName(),
                        search.getBreakfast(), search.getLunch(), search.getDinner());
            }
            return null;
        }
    }
}
