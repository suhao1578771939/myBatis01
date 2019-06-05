package net.wanho.mapper;

import net.wanho.pojo.User;

import java.util.List;

/**
 * Created by Administrator on 2019/6/5.
 */
public interface UserMapper {

    List<User> getAllUser();

    User getUserById(Integer id);

    void updateUser(User user);

    void deleteUser(User user);

    void addUser(User user);

    List<User> getUserName(String name);

    List<User> getUserName2(User user);

    void addUserReturnKey(User user);

    void addUserReturnKey2(User user);
}
