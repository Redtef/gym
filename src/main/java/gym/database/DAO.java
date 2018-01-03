package gym.database;

import java.util.ArrayList;

public  interface DAO<T> {

    T findById(int id);
    ArrayList<T> findByName(String name);
    ArrayList<T> findAll();
    ArrayList<T> findAllWhere(String col,String comp,String val);

    void insert(T value);
    void update(T value);
    void delete(int id);

}
