package lk.ijse.roomreservation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class CrudUtil {
    public static <T> T execute(String sql, Connection con,Object... params)throws Exception{
        PreparedStatement stm = con.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            stm.setObject(i+1,params[i]);
        }
        if(sql.startsWith("SELECT")){
            return (T) stm.executeQuery();
        }else {
            return (T)((Boolean)(stm.executeUpdate()>0));
        }
    }
}
