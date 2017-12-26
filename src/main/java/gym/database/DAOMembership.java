package gym.database;

import gym.model.Membership;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAOMembership implements DAO<Membership> {
    @Override
    public Membership findById(int id) {
        return null;
    }

    @Override
    public Membership findByName(String name) {
        return null;
    }

    @Override
    public ArrayList<Membership> findAll() {
        ArrayList<Membership> memberships = new ArrayList<>();
        try (Statement statement = DBSingleton.getInstance().getConnection().createStatement();
             ResultSet rs = statement.executeQuery("SELECT * FROM MEMBERSHIP ORDER BY ID")) {

            while (rs.next()) {

                memberships.add(new Membership(rs.getInt("id"), rs.getString("name"), rs.getInt("weeks"), rs.getInt("days"), rs.getDouble("price")));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return memberships;
    }

    @Override
    public void insert(Membership value) {
        System.out.println("added");

    }

    @Override
    public void update(Membership value) {
        System.out.println("updated");

    }

    @Override
    public void delete(int id) {
        System.out.println("deleted where id ="+id);

    }
}
