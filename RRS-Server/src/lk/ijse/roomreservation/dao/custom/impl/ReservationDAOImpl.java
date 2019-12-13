package lk.ijse.roomreservation.dao.custom.impl;

import lk.ijse.roomreservation.dao.CrudUtil;
import lk.ijse.roomreservation.dao.custom.ReservationDAO;
import lk.ijse.roomreservation.entity.Reservation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ReservationDAOImpl implements ReservationDAO {

    private Connection connection;

    @Override
    public boolean add(Reservation r) throws Exception {
        String sql="INSERT INTO reservation(customerNIC,adult,kids,packageId) VALUES(?,?,?,?)";
        return CrudUtil.execute(sql,connection,r.getCustomerNIC(),r.getNoOFAdult(),r.getNoOfKids(),r.getPackageId());
    }

    @Override
    public boolean delete(Integer integer) throws Exception {
        String sql="DELETE FROM reservation WHERE id=?";
        return false;
    }

    @Override
    public boolean update(Reservation r) throws Exception {
        String sql="UPDATE reservation SET customerNIC=?,adult=?,kids=?,packageId=? WHERE ID=?";
        return CrudUtil.execute(sql,connection,r.getCustomerNIC(),r.getNoOFAdult(),r.getNoOfKids(),r.getPackageId(),r.getId());
    }

    @Override
    public Reservation search(Integer integer) throws Exception {
        String sql="SELECT * FROM reservation WHERE id=?";
        ResultSet rst = CrudUtil.execute(sql, connection, integer);
        Reservation r=null;
        if(rst.next()){
            r=new Reservation(
                    rst.getInt(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getInt(4),
                    rst.getInt(5)
            );
        }
        return r;
    }

    @Override
    public ArrayList<Reservation> getAll() throws Exception {
        String sql="SELECT * FROM reservation";
        ResultSet rst = CrudUtil.execute(sql, connection);
        ArrayList<Reservation> r=new ArrayList<>();
        while(rst.next()){
            r.add(new Reservation(
                    rst.getInt(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getInt(4),
                    rst.getInt(5)
            ));
        }
        return r;
    }

    @Override
    public int getLastID() throws Exception {
        ArrayList<Reservation> all = getAll();
        if(all.size()>0){
            return all.get(all.size()-1).getId();
        }
        return 0;
    }

    @Override
    public void setConnection(Connection connection) throws Exception {
        this.connection=connection;
    }
}
