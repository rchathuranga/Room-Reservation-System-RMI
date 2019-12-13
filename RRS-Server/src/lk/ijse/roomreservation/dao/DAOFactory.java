package lk.ijse.roomreservation.dao;

import lk.ijse.roomreservation.dao.custom.impl.*;

public class DAOFactory {
    private DAOFactory(){}
    private static DAOFactory daoFactory;

    public static DAOFactory getInstance(){
        return (null==daoFactory)? daoFactory=new DAOFactory():daoFactory;
    }

    public enum DAOTypes{
        CUSTOMER,ROOM,RESERVATION,RESERVATIONDETAIL,MEALPACKAGE,QUARY
    }

    public SuperDAO getDAO(DAOTypes types){
        switch (types){
            case CUSTOMER:return new CustomerDAOImpl();
            case ROOM:return new RoomDAOImpl();
            case RESERVATION:return new ReservationDAOImpl();
            case RESERVATIONDETAIL:return new ReservationDetailDAOImpl();
            case MEALPACKAGE:return new MealPackageDAOImpl();
            case QUARY:return new QuaryDAOImpl();
            default:return null;
        }
    }
}
