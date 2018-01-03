package gym.database;

import gym.model.Member;
import gym.model.Membership;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAOMember implements DAO<Member> {
    @Override
    public Member findById(int id) {
        return null;
    }

    public ArrayList<Member> findBanedByName(String name) {
        ArrayList<Member> members = new ArrayList<>();
        try (Statement statement = DBSingleton.getInstance().getConnection().createStatement();
             ResultSet rs = statement.executeQuery("SELECT * FROM MEMBER WHERE LOWER (NAME) LIKE LOWER ('%" + name + "%') AND BANED=1 ORDER BY ID")) {

            while (rs.next()) {

                Member member = new Member(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("cin"),
                        rs.getString("avatar"),
                        rs.getDate("birth_date"),
                        rs.getBoolean("handicap"));

                members.add(member);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return members;
    }
    @Override
    public ArrayList<Member> findByName(String name) {
        ArrayList<Member> members = new ArrayList<>();
        try (Statement statement = DBSingleton.getInstance().getConnection().createStatement();
             ResultSet rs = statement.executeQuery("SELECT * FROM MEMBER WHERE LOWER (NAME) LIKE LOWER ('%" + name + "%') AND BANED=0 ORDER BY ID")) {

            while (rs.next()) {

                Member member = new Member(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("cin"),
                        rs.getString("avatar"),
                        rs.getDate("birth_date"),
                        rs.getBoolean("handicap"));

                members.add(member);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return members;
    }

    @Override
    public ArrayList<Member> findAll() {
        ArrayList<Member> members = new ArrayList<>();
        try (Statement statement = DBSingleton.getInstance().getConnection().createStatement();
             ResultSet rs = statement.executeQuery("SELECT * FROM MEMBER ORDER BY ID")) {

            while (rs.next()) {

                Member member = new Member(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("cin"),
                        rs.getString("avatar"),
                        rs.getDate("birth_date"),
                        rs.getBoolean("handicap"));
//                member.setMembership(new Membership(1, "Gold", 4, 4, 50.));
//                member.getMembership().setPaid(true);
                members.add(member);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return members;
    }

    @Override
    public ArrayList<Member> findAllWhere(String col, String comp, String val) {
        ArrayList<Member> members = new ArrayList<>();
//        String builder = "SELECT * FROM MEMBER JOIN BOOKING ON MEMBER.ID = BOOKING.MEMBER_ID JOIN MEMBERSHIP ON BOOKING.MEMBRESHIP_ID = MEMBERSHIP.ID WHERE " + col + comp + val + " ORDER BY ID";

        String builder="SELECT  MEMBER .ID as ID , MEMBER.NAME as NAME , CIN,BIRTH_DATE,AVATAR,HANDICAP,BANED,PAID,BOOKING_DATE,MEMBRESHIP_ID,MEMBERSHIP.NAME AS MEMBERSHIP_NAME , WEEKS, DAYS, PRICE\n" +
                "FROM MEMBER,BOOKING,MEMBERSHIP " +
                "WHERE MEMBER.ID=BOOKING.MEMBER_ID(+) " +
                "AND BOOKING.MEMBRESHIP_ID=MEMBERSHIP.ID(+) " +
                "AND " + col + comp + val +" " +
                "ORDER BY ID";
        try (Statement statement = DBSingleton.getInstance().getConnection().createStatement();
             ResultSet rs = statement.executeQuery(builder)) {

            while (rs.next()) {
                Member member = new Member(rs);
                Membership membership=new Membership(rs);
                member.setMembership(membership);
                members.add(member);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return members;
    }


    @Override
    public void insert(Member value) {
        String q = String.format("INSERT INTO MEMBER (NAME,CIN,BIRTH_DATE,AVATAR,HANDICAP) VALUES ('%s','%s',TO_DATE('%s','YYYY/MM/DD'),'%s',%d)",
                value.getName(),
                value.getCin(),
                value.getBirth_date().toString(),
                value.getAvatarPath(),
                value.getHandicap() ? 1 : 0);
        try (Statement statement = DBSingleton.getInstance().getConnection().createStatement()) {
            statement.execute(q);
            System.out.println("Added ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Member value) {

    }

    @Override
    public void delete(int id) {

    }

    public void avatar(int id, String path) {
        String q = String.format("UPDATE MEMBER SET AVATAR='%s' WHERE id = %d", path, id);
        try (Statement statement = DBSingleton.getInstance().getConnection().createStatement()) {
            Boolean res = statement.execute(q);
            System.out.println("updated " + res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ban(boolean b, int id) {
        String q = String.format("UPDATE MEMBER SET BANED=%d WHERE id = %d",b?1:0, id);
        try (Statement statement = DBSingleton.getInstance().getConnection().createStatement()) {
            Boolean res = statement.execute(q);
            System.out.println("Baned " + res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
