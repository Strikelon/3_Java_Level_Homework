package hmJava3.hm2Chat;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BaseAuthService implements AuthService {

    private static Connection connection;
    private static Statement stmp;

    private List<User> users = new ArrayList<User>();

    public static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:main.db");
        stmp = connection.createStatement();

    }

    public static void disconnect(){
        try {
            stmp.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public BaseAuthService() {

        try {
            connect();
            String sqlQuerry = "SELECT login, pass, nick FROM users;";
            ResultSet rs= stmp.executeQuery(sqlQuerry);
            while (rs.next()){
                String login = rs.getString(1);
                String pass = rs.getString(2);
                String nick = rs.getString(3);
                users.add(new User(login, pass, nick));
            }


        }catch (Exception e){
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }


    public String authByLoginAndPassword(String login, String password) {
        for (User user : users) {
            if (login.equals(user.getLogin())
                    && password.equals(user.getPassword()))
                return user.getNickname();
        }
        return null;
    }



}
