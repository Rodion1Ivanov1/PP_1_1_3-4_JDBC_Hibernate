package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() throws SQLException {
        Connection connection = Util.getConnection();
        Statement statement = connection.createStatement();
        try {
            statement.execute("create table users(id int not null auto_increment primary key, name varchar(45), lastName varchar(45), age int)");
        } catch (SQLException e) {}
    }

    public void dropUsersTable() throws SQLException {
        Connection connection = Util.getConnection();
        Statement statement = connection.createStatement();
        try {
            statement.execute("DROP TABLE users");
        } catch (SQLException e) {}

    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        Connection connection = Util.getConnection();
        Statement statement = connection.createStatement();
        statement.execute("INSERT INTO users (name, lastname, age) VALUES('" + name + "', '" + lastName +"', "+ age + ")");
        System.out.println("User with name " + name+ " has been added to the database");
    }

    public void removeUserById(long id) throws SQLException {
        Connection connection = Util.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("delete from users where id="+id);
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> userList = new ArrayList<User>();
        Connection connection = Util.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from users");
        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getLong("id"));
            user.setName(resultSet.getString("name"));
            user.setLastName(resultSet.getString("lastName"));
            user.setAge(resultSet.getByte("age"));
            user.toString();
            userList.add(user);
        }
        return userList;
    }

    public void cleanUsersTable() throws SQLException {
        Connection connection = Util.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("delete from users");
    }
}
