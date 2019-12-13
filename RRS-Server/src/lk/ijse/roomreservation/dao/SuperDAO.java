package lk.ijse.roomreservation.dao;


import java.sql.Connection;

public interface SuperDAO {
    void setConnection(Connection connection) throws Exception;
}
