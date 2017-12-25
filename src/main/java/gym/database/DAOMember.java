package gym.database;

import gym.model.Member;

import java.util.ArrayList;

public class DAOMember implements DAO<Member> {
    @Override
    public Member findById(int id) {
        return null;
    }

    @Override
    public Member findByName(String name) {
        return null;
    }

    @Override
    public ArrayList<Member> findAll() {
        return null;
    }

    @Override
    public void insert(Member value) {

    }

    @Override
    public void update(int id) {

    }

    @Override
    public void delete(int id) {

    }
}
