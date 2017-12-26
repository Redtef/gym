package gym.database;

import java.util.ArrayList;

public  interface DAO<T> {

    T findById(int id);
    T findByName(String name);
    ArrayList<T> findAll();
    void insert(T value);
    void update(T value);
    void delete(int id);

}
