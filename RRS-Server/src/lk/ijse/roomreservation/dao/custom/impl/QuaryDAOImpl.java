package lk.ijse.roomreservation.dao.custom.impl;

import lk.ijse.roomreservation.dao.CrudUtil;
import lk.ijse.roomreservation.dao.custom.QuaryDAO;
import lk.ijse.roomreservation.entity.Custom;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class QuaryDAOImpl implements QuaryDAO {

    private Connection connection;

    @Override
    public void setConnection(Connection connection) throws Exception {
        this.connection=connection;
    }

    @Override
    public ArrayList<Custom> getAllAvailableRooms(Custom c) throws Exception {
        String sql="SELECT r.* FROM reservationDetail rd,room r WHERE startDate " +
                "NOT BETWEEN \""+c.getStartDate()+"\" AND\""+c.getEndDate()+"\" && " +
                "endDate NOT BETWEEN \""+c.getStartDate()+"\" and \""+c.getEndDate()+"\" && rd.roomId=r.id" +
                "&& rd.roomId=r.id && r.ac=? group by r.id";
        ResultSet rst= CrudUtil.execute(sql, connection,c.isWithAC());
        ArrayList<Custom> arrayList=new ArrayList<>();

        getFreeRoom(arrayList,c.isWithAC());

        while (rst.next()){
            arrayList.add(new Custom(
                    rst.getInt(1),
                    rst.getBoolean(2),
                    rst.getInt(3)
            ));
        }
        return arrayList;
    }

    private void getFreeRoom(ArrayList<Custom> arrayList, boolean withAC) throws Exception {
        String sql="SELECT * FROM room WHERE  ID NOT IN (SELECT roomId FROM reservationdetail) && ac=?";
        ResultSet rst = CrudUtil.execute(sql, connection, withAC);

        while (rst.next()){
            arrayList.add(new Custom(
                    rst.getInt(1),
                    rst.getBoolean(2),
                    rst.getInt(3)
            ));
        }
    }
}
