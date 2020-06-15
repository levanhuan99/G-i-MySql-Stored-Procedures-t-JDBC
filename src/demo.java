//import java.sql.*;
//
//public class demo {
//    public static void main(String[] args) throws SQLException {
//        String url = "jdbc:mysql://localhost:3306/classicmodels";
//        String userName = "root";
//        String password = "123456789";
//        Connection connection = null;
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            connection = DriverManager.getConnection(url, userName, password);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        Statement statement = connection.createStatement();
//        String query="select customerName,phone,city from customers";
//        ResultSet resultSet=statement.executeQuery(query);
//        while (resultSet.next()){
//            String name=resultSet.getString("customerName");
//            String phone=resultSet.getString("phone");
//            String city=resultSet.getString("city");
//            System.out.println(name+"|"+phone+"|"+city);
//        }
//
//    }
//}
