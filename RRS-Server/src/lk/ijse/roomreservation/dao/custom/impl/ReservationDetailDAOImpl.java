package lk.ijse.roomreservation.dao.custom.impl;

import lk.ijse.roomreservation.dao.CrudUtil;
import lk.ijse.roomreservation.dao.custom.ReservationDetailDAO;
import lk.ijse.roomreservation.entity.ReservationDetail;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ReservationDetailDAOImpl implements ReservationDetailDAO {

    private Connection connection;

    @Override
    public boolean add(ReservationDetail rd) throws Exception {
        String sql="INSERT INTO reservationdetail(reservationId, roomId, startDate, endDate) VALUE (?,?,?,?)";
        return CrudUtil.execute(sql,connection,rd.getReservationId(),rd.getRoomId(), Date.valueOf(rd.getStartDate()),Date.valueOf(rd.getEndDate()));
    }

    @Override
    public boolean delete(Integer integer) throws Exception {
        String sql="DELETE FROM reservationdetail WHERE id=?";
        return CrudUtil.execute(sql,connection,integer);
    }

    @Override
    public boolean update(ReservationDetail rd) throws Exception {
        String sql="UPDATE reservationdetail SET startDate=?,endDate=?,roomId=? WHERE id=?";
        return CrudUtil.execute(sql,connection,rd.getStartDate(),rd.getEndDate(),rd.getRoomId(),rd.getId());
    }

    @Override
    public ReservationDetail search(Integer integer) throws Exception {
        String sql="SELECT * FROM reservationdetail WHERE id=?";
        ResultSet rst = CrudUtil.execute(sql, connection, integer);
        ReservationDetail detail=null;
        if(rst.next()){
            detail=new ReservationDetail(
                    rst.getInt(1),
                    rst.getInt(2),
                    rst.getInt(3),
                    rst.getDate(4).toLocalDate(),
                    rst.getDate(5).toLocalDate()
            );
        }
        return detail;
    }

    @Override
    public ArrayList<ReservationDetail> getAll() throws Exception {
        String sql="SELECT * FROM reservationdetail";
        ResultSet rst = CrudUtil.execute(sql, connection);
        ArrayList<ReservationDetail> details=null;
        while (rst.next()){
            details.add(new ReservationDetail(
                    rst.getInt(1),
                    rst.getInt(2),
                    rst.getInt(3),
                    rst.getDate(4).toLocalDate(),
                    rst.getDate(5).toLocalDate()
            ));
        }
        return details;
    }

    @Override
    public void setConnection(Connection connection) throws Exception {
        this.connection=connection;
    }
}
