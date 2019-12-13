package lk.ijse.roomreservation.dao.custom.impl;

import lk.ijse.roomreservation.dao.CrudUtil;
import lk.ijse.roomreservation.dao.custom.CustomerDAO;
import lk.ijse.roomreservation.entity.Customer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    private Connection connection;

    @Override
    public boolean add(Customer c) throws Exception {
        String sql="INSERT INTO customer VALUES(?,?,?,?)";
        return CrudUtil.execute(sql,connection,c.getNic(),c.getName(),c.getContact(),c.getAddress());
    }

    @Override
    public boolean delete(String s) throws Exception {
        String sql="DELETE customer WHERE nic=?";
        return CrudUtil.execute(sql,connection,s);
    }

    @Override
    public boolean update(Customer c) throws Exception {
        String sql="UPDATE customer SET name=?, contact=?, address=? where nic=?";
        return CrudUtil.execute(sql,connection,c.getName(),c.getContact(),c.getAddress(),c.getNic());
    }

    @Override
    public Customer search(String s) throws Exception {
        String sql="SELECT * FROM customer WHERE nic=?";
        ResultSet rst = CrudUtil.execute(sql, connection,s);
        Customer customer=null;
        if(rst.next()){
            customer=new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            );
        }
        return customer;
    }

    @Override
    public ArrayList<Customer> getAll() throws Exception {
        String sql="SELECT * FROM customer";
        ResultSet rst = CrudUtil.execute(sql, connection);
        ArrayList<Customer> customers=new ArrayList<>();
        while(rst.next()){
            customers.add(new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            ));
        }
        return customers;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

//    @Override
//    public String getLastID() throws Exception {
//        ArrayList<Customer> all = getAll();
//        if(all.size()>0){
//            return all.get(0).getNic();
//        }
//        return "";
//    }
}
