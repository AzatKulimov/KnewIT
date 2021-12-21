import model.Car;
import model.Users;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBSmanager {
    private static Connection connection;
    private static String url = "jdbc:mysql://localhost:3306/carshop";

    static{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, "root", "11111");
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static Car getCar(int id) throws SQLException {
        PreparedStatement statement=connection.prepareStatement("SELECT * from car where id="+id);
        ResultSet resultSet=statement.executeQuery();
        Car c=new Car(
                resultSet.getInt("id"),
                resultSet.getString("mark"),
                resultSet.getString("model"),
                resultSet.getInt("price")
        );
        return c;
    }


    public static List<Users> getUsers() throws SQLException {
        List<Users> usersList=new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement("SELECT * from users");
        ResultSet resultSet = statement.executeQuery();

        while(resultSet.next()){
            Users u = new Users(resultSet.getInt("id"),
                    resultSet.getString("firstname"),
                    resultSet.getString("lastname"),
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getInt("balance"),
                    resultSet.getBoolean("isAdmin")
                    );
            usersList.add(u);
        }
        resultSet.close();
        statement.close();
        return usersList;
    }

    public static List<Car> getCars() throws SQLException{
        List<Car> carArrayList = new ArrayList<>();
        PreparedStatement statement=connection.prepareStatement("SELECT *from car");
        ResultSet resultSet=statement.executeQuery();
        while(resultSet.next()){
            Car c = new Car(resultSet.getInt("id"),
                    resultSet.getString("mark"),
                    resultSet.getString("model"),
                    resultSet.getInt("price"));
            carArrayList.add(c);
        }
        resultSet.close();
        statement.close();
        return carArrayList;
    }

    public static boolean AddUsers(Users u) throws SQLException{
        int rows = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Users VALUES (null, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, u.getFirstname());
            statement.setString(2, u.getLastname());
            statement.setString(3, u.getUsername());
            statement.setString(4, encryptPassword(u.getPassword()));
            statement.setInt(5, u.getBalance());
            statement.setBoolean(6, u.isAdmin());
            rows = statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return rows>0;
    }

    public static String encryptPassword(String input)
    {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean AddCars(Car c) {
        int rows = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO car VALUES (null, ?, ?, ? )");
            statement.setString(1, c.getMark());
            statement.setString(2, c.getModel());
            statement.setInt(3, c.getPrice());
            rows = statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return rows>0;
    }
    public static boolean deleteCar(int id) {
        int rows = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE from car where id= ? ");
            statement.setInt(1, id);
            rows = statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return rows>0;
    }

    public static boolean deleteUser(int id) {
        int rows = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE from users where id= ? ");
            statement.setInt(1, id);
            rows = statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return rows>0;
    }

    public static boolean UpdateCars(Car c) throws SQLException {
        int rows = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("update car set mark=?, model= ?, price= ? where id=" + c.getId());
            statement.setString(1, c.getMark());
            statement.setString(2, c.getModel());
            statement.setInt(3, c.getPrice());
            rows = statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }

        return rows>0;
    }

    public static boolean Updateusers(Users u) throws SQLException {
        int rows = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "update users set firstname =?, lastname= ?, username= ?, password=?, balance=?, isAdmin=?  where id=" + u.getId());
            statement.setString(1, u.getFirstname());
            statement.setString(2, u.getLastname());
            statement.setString(3, u.getUsername());
            statement.setString(4, encryptPassword(u.getPassword()));
            statement.setInt(5, u.getBalance());
            statement.setBoolean(6, u.isAdmin());
            rows = statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }

        return rows>0;
    }

    public static void main(String[] args) throws SQLException {
        Updateusers(new Users(2, "Azamat", "Karimov", "azik", "777", 200000, false));
        Updateusers(new Users(1, "Darhan", "Smailov", "dake", "12345", 0, true));

    }
}
