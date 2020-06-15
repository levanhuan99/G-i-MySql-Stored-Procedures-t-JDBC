package dao;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements IUserDao {
    private Connection connection;
    private String url = "jdbc:mysql://localhost:3306/demo";
    private String userName = "root";
    private String password = "123456";

    private static final String SELECT_ALL_USERS = "select* from user";
    private static final String SELECT_USER = "select * from user where id = ?;";
    private static final String INSERT_USER = "insert into user values (?,?,?,?)";
    private static final String UPDATE_USER = "update user set name = ?,email= ?, country =? where id = ?;";
    private static final String DELETE_USER = "delete from user where id = ?;";
    private static final String SELECT_USER_BY_NAME = "select * from user where name like ?;";

    public UserDao() {
        getConnection();
    }

    protected Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(url, userName, password);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }

        return connection;
    }

    @Override
    public User selectUser(int id) {
        User user = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String country = resultSet.getString("country");
                user = new User(id, name, email, country);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> selectAllUser() {
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USERS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String country = resultSet.getString("country");
                users.add(new User(id, name, email, country));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }

    @Override
    public void deleteUser(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_USER);
            statement.setInt(1, id);
            statement.executeUpdate();//  các hàm thêm sửa xóa hì không dung được executeQuery() chỉ dùng được với executeUpdate()
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void updateUser(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement(UPDATE_USER);
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getCountry());
            statement.setInt(4, user.getId());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void insertUser(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement(INSERT_USER);
            statement.setInt(1, user.getId());
            statement.setString(2, user.getName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getCountry());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public User getUserById(int id) {
        User user = null;
        String query = "{call get_user_by_id(?)";
        try {
            CallableStatement callableStatement = connection.prepareCall(query);
            callableStatement.setInt(1, id);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String country = resultSet.getString("country");
                user = new User(id, name, email, country);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    @Override
    public void insertUserStore(User user) {
        String query = "{call insert_user(?,?,?)}";
        try {
            CallableStatement callableStatement =connection.prepareCall(query);
            callableStatement.setString(1,user.getName());
            callableStatement.setString(2,user.getEmail());
            callableStatement.setString(3,user.getCountry());
            callableStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void addUserTransaction(User user, int[] permission) {
        PreparedStatement preparedStatement=null;
        PreparedStatement preparedStatementAssignment=null;
        ResultSet resultSet=null;
        try {
            connection.setAutoCommit(false);
            preparedStatement=connection.prepareStatement(INSERT_USER,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getEmail());
            preparedStatement.setString(3,user.getCountry());
            int rowEffected=preparedStatement.executeUpdate();

            resultSet=preparedStatement.getGeneratedKeys();
            int userId=0;
            if (resultSet.next()){
                userId=resultSet.getInt(1);
            }
            if (rowEffected==1) {
                String sqlPivot="insert into user_permisstion(user_id,permission_id)"+"values(?,?)";
                preparedStatementAssignment=connection.prepareStatement(sqlPivot);
                for (int permissionId :permission){
                    preparedStatement.setInt(1,userId);
                    preparedStatement.setInt(2,permissionId);
                    preparedStatementAssignment.executeUpdate();
                }
                connection.commit();
            }else {
                connection.rollback();
            }
        } catch (SQLException throwables) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }finally {
            if (resultSet!=null) {
                try {
                    resultSet.close();
                    if (preparedStatement!=null) preparedStatement.close();
                    if (preparedStatementAssignment!=null)preparedStatementAssignment.close();
                    if (connection!=null) connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

        }
    }

    @Override
    public void insertUpdateWithoutTransaction() {

    }

    public User findUser(String name) {
        User user = null;
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_NAME);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nameResult = resultSet.getString("name");
                String email = resultSet.getString("email");
                String country = resultSet.getString("country");
                user = new User(id, nameResult, email, country);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;

    }
//    public List<User> sortByName(List<User> list){
//        for ( int i=0;i<list.size();i++){
//            for (int j=0;j<list.size()-i-1;j++){
//                if (list.get(j).getName().compareTo(list.get(j+1).getName())>0){
//                    User user=list.get(j);
//                    list.get(j)=list.get(j+1);
//                    list.get(j+1)=user;
//
//                }
//            }
//        }
//    } chua xong
}




