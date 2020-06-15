package dao;

import model.User;

import java.util.List;

public interface IUserDao {
    User selectUser(int id);

    List<User> selectAllUser();

    void deleteUser(int id);

    void updateUser(User user);

    void insertUser(User user);

    User getUserById(int id);

    void insertUserStore(User user);

    void addUserTransaction(User user,int[] permission);

    void insertUpdateWithoutTransaction();

}
