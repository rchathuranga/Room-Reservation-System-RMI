package lk.ijse.roomreservation.db;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;

public class DBConnection {
    private static DBConnection dbConnection;
    private BasicDataSource bds;
    private DBConnection(){
        bds=new BasicDataSource();
        bds.setDriverClassName("com.mysql.jdbc.Driver");
        bds.setUsername("root");
        bds.setPassword("ijse");
        bds.setUrl("jdbc:mysql://localhost:3306/roomreservation");
        bds.setInitialSize(4);
        bds.setMaxTotal(4);
    }

    public static DBConnection getInstance(){
        if(null==dbConnection){
            dbConnection=new DBConnection();
        }
        return dbConnection;
    }

    public Connection getConnection() throws Exception {
        return bds.getConnection();
    }
}
