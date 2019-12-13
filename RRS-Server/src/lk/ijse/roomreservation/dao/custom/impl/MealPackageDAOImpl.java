package lk.ijse.roomreservation.dao.custom.impl;

import lk.ijse.roomreservation.dao.CrudUtil;
import lk.ijse.roomreservation.dao.custom.MealPackageDAO;
import lk.ijse.roomreservation.entity.MealPackage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MealPackageDAOImpl implements MealPackageDAO {

    private Connection connection;

    @Override
    public boolean add(MealPackage pack) throws Exception {
        String sql="INSERT INTO mealPackage(packageName,breakfast,lunch,dinner) VALUES(?,?,?,?)";
        return CrudUtil.execute(sql,connection,pack.getPackageName(),pack.getBreakfast(),
                pack.getLunch(),pack.getDinner());
    }

    @Override
    public boolean delete(Integer integer) throws Exception {
        String sql="DELETE FROM mealPackage WHERE id=?";
        return CrudUtil.execute(sql,connection,integer);
    }

    @Override
    public boolean update(MealPackage pack) throws Exception {
        String sql="UPDATE mealPackage SET packageName=?,breakfast=?,lunch=?,dinner=? WHERE id=?";
        return CrudUtil.execute(sql,connection,pack.getPackageName(),
                pack.getBreakfast(),pack.getLunch(),pack.getDinner(),pack.getId());
    }

    @Override
    public MealPackage search(Integer integer) throws Exception {
        String sql="SELECT * FROM mealPackage WHERE id=?";
        ResultSet rst=CrudUtil.execute(sql,connection,integer);
        MealPackage pack=null;
        if(rst.next()){
            pack=new MealPackage(
                    rst.getInt(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            );
        }
        return pack;
    }

    @Override
    public ArrayList<MealPackage> getAll() throws Exception {
        String sql="SELECT * FROM mealpackage";
        ResultSet rst=CrudUtil.execute(sql,connection);
        ArrayList<MealPackage> packs=new ArrayList<>();
        while(rst.next()){
            packs.add(new MealPackage(
                rst.getInt(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            ));
        }
        return packs;
    }

    @Override
    public void setConnection(Connection connection) throws Exception {
        this.connection=connection;
    }
}
