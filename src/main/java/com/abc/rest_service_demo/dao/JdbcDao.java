package com.abc.rest_service_demo.dao;

import com.abc.rest_service_demo.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcDao {


    @Autowired
    JdbcTemplate jdbcTemplate;

    class UserRowMapper implements RowMapper<UserModel> {

        @Override
        public UserModel mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserModel userModel = new UserModel();
            userModel.setId(rs.getInt("id"));
            userModel.setFirstName(rs.getString("first_name"));
            userModel.setLastName(rs.getString("last_name"));
            userModel.setAge(rs.getInt("age"));
            userModel.setEmail(rs.getString("email"));

            return userModel;

        }
    }

    public List<UserModel> findAllUsers() {
        return jdbcTemplate.query("select * from users", new UserRowMapper());
    }

    public UserModel findById(int id) {
        return jdbcTemplate.queryForObject("select * from users where id=?", new Object[]{id},new BeanPropertyRowMapper<>(UserModel.class));
    }

    public int deleteById(int id) {
        return jdbcTemplate.update("delete from users where id=?", new Object[]{id});
    }

    public int createUser(UserModel user) {
        return jdbcTemplate.update("insert into users (id, first_name, last_name, age, email) " +
                "values (?, ?, ?, ?, ?)" , new Object[]{user.getId(), user.getFirstName(), user.getLastName(), user.getAge(), user.getEmail()});
    }


}
