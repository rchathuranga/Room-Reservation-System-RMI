package lk.ijse.roomreservation.dao;

import java.util.ArrayList;

public interface CrudDAO<T,ID> extends SuperDAO{
    boolean add(T t) throws Exception;
    boolean delete(ID id) throws Exception;
    boolean update(T t) throws Exception;
    T search(ID id) throws Exception;
    ArrayList<T> getAll() throws Exception;
}
