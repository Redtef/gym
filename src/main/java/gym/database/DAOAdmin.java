package gym.database;

import gym.model.Admin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public  class  DAOAdmin implements DAO<Admin> {
    @Override
    public Admin findById(int id) {
        Admin admin = null;
        try (Statement statement = DBSingleton.getInstance().getConnection().createStatement();
             ResultSet rs = statement.executeQuery("SELECT * FROM ADMIN ")) {

            while (rs.next()) {

                admin = new Admin(
                        rs.getString("username"),
                        rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }

    @Override
    public Admin findByName(String name) {
        Admin admin = null;
        try (Statement statement = DBSingleton.getInstance().getConnection().createStatement();
             ResultSet rs = statement.executeQuery("SELECT * FROM ADMIN ")) {

            while (rs.next()) {

                admin = new Admin(
                        rs.getString("username"),
                        rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }

    @Override
    public ArrayList<Admin> findAll() {
        return null;
    }

    @Override
    public void insert(Admin value) {

    }

    @Override
    public void update(Admin value) {

    }

    @Override
    public void delete(int id) {

    }
}
