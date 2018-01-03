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
    public ArrayList<Membership> findByName(String name) {
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
    public ArrayList<Membership> findAllWhere(String col, String comp, String val) {
        return null;
    }

    @Override
    public void insert(Membership value) {
        String q = String.format("INSERT INTO MEMBERSHIP ( NAME, WEEKS, DAYS, PRICE) VALUES ('%s',%d,%d,%.0f)",
                                value.getName(), value.getWeeks(), value.getDays(), value.getPrice());
        try (Statement statement = DBSingleton.getInstance().getConnection().createStatement()) {
            statement.execute(q);
            System.out.println("Added ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Membership value) {
        String q = String.format("UPDATE MEMBERSHIP SET name='%s',weeks=%d,days=%d,price=%f WHERE id = %d", value.getName(), value.getWeeks(), value.getDays(), value.getPrice(), value.getId());
        try (Statement statement = DBSingleton.getInstance().getConnection().createStatement()) {
            Boolean res = statement.execute(q);
            System.out.println("updated " + res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {

        String q = String.format("DELETE FROM MEMBERSHIP WHERE id = %d", id);
        try (Statement statement = DBSingleton.getInstance().getConnection().createStatement()) {
            Boolean res = statement.execute(q);
            System.out.println("deleted " + res);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
