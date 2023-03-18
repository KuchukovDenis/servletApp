package Syeta;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserSQLRepository  {
    private static final String dbUserName = "postgres";
    private static final String dbPassword = "1234";
    private static final String dbUrl = "jdbc:postgresql://localhost:5432/Lab6";

    private static Connection connection;

    private static UserSQLRepository repository;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void save(People user) {
        try {
            String query = "INSERT INTO users (login, password, email) VALUES(?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public People  find(String login) {
        try {
            String query = "SELECT * FROM users WHERE login = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, login);
            ResultSet result = statement.executeQuery();

            result.next();

            int id = result.getInt("id");
            String resultLogin = result.getString("login");
            String pass = result.getString("password");
            String email = result.getString("email");

            return new People(id, resultLogin, pass, email);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private UserSQLRepository() {
    }

    public static UserSQLRepository getUserRepository() {
        if (repository == null) {
            repository = new UserSQLRepository();
        }

        return repository;
    }
}