package lk.ijse.roomreservation.dao.custom.impl;

import lk.ijse.roomreservation.dao.CrudUtil;
import lk.ijse.roomreservation.dao.custom.RoomDAO;
import lk.ijse.roomreservation.entity.Room;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomDAOImpl implements RoomDAO {

    private Connection connection;

    @Override
    public boolean add(Room room) throws Exception {
        String sql="INSERT INTO room(ac, capacity) VALUE (?,?)";
        return CrudUtil.execute(sql,connection,room.isWithAC(),room.getCapacity());
    }

    @Override
    public boolean delete(Integer integer) throws Exception {
        String sql="DELETE FROM  room WHERE id=?";
        return CrudUtil.execute(sql,connection,integer);
    }

    @Override
    public boolean update(Room room) throws Exception {
        String sql="UPDATE room SET ac=?,capacity=? WHERE id=?";
        return CrudUtil.execute(sql,connection,room.isWithAC(),room.getCapacity(),room.getId());
    }

    @Override
    public Room search(Integer integer) throws Exception {
        String sql="SELECT * FROM room WHERE id=?";
        ResultSet rst=CrudUtil.execute(sql,connection,integer);
        Room room=null;
        if(rst.next()){
            room=new Room(
                    rst.getInt(1),
                    rst.getBoolean(2),
                    rst.getInt(3)
            );
        }
        return room;
    }

    @Override
    public ArrayList<Room> getAll() throws Exception {
        String sql="SELECT * FROM room";
        ResultSet rst=CrudUtil.execute(sql,connection);
        ArrayList<Room> rooms=new ArrayList<>();
        return convertToRoomDTO(rst);
    }


    @Override
    public ArrayList<Room> searchByAC(boolean b) throws Exception {
        String sql="SELECT * FROM room where ac=?";
        ResultSet rst=CrudUtil.execute(sql,connection,b);
        return convertToRoomDTO(rst);
    }

    @Override
    public ArrayList<Room> searchByCapacity(int c) throws Exception {
        String sql="SELECT * FROM room where capacity=?";
        ResultSet rst=CrudUtil.execute(sql,connection,c);
        return convertToRoomDTO(rst);
    }

    @Override
    public ArrayList<Room> search(Room r) throws Exception {
        String sql="SELECT * FROM room where capacity=? && ac=?";
        ResultSet rst=CrudUtil.execute(sql,connection,r.getCapacity(),r.isWithAC());
        return convertToRoomDTO(rst);
    }

    private ArrayList<Room> convertToRoomDTO(ResultSet rst) throws SQLException {
        ArrayList<Room> rooms=new ArrayList<>();
        while (rst.next()){
            rooms.add(new Room(
                    rst.getInt(1),
                    rst.getBoolean(2),
                    rst.getInt(3)
            ));
        }
        return rooms;
    }

    @Override
    public void setConnection(Connection connection) throws Exception {
        this.connection=connection;
    }

}
