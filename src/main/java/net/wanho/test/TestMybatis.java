package net.wanho.test;


import net.wanho.mapper.UserMapper;
import net.wanho.pojo.User;

import net.wanho.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import org.junit.Before;
import org.junit.Test;

import java.util.List;


/**
 * Created by Administrator on 2019/6/5.
 */
public class TestMybatis {
    private SqlSession sqlSession;
    private UserMapper userMapper;
    @Before
    public void before(){
//      InputStream inputStream= TestMybatis.class.getClassLoader().getResourceAsStream("mybatis-config.xml");
//        SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(inputStream);
//        //创建会话
//        sqlSession = sf.openSession();
        sqlSession= MybatisUtil.getSession();
        userMapper = sqlSession.getMapper(UserMapper.class);

    }
    @Test
    public void testAddUser(){
        User user=new User(null,"yanglei","1234");
        sqlSession.update("net.wanho.mapper.UserMapper.addUser",user);
        sqlSession.commit();
        sqlSession.close();

    }
    @Test
    public void testUpdateUser(){
        User user=new User(5,"yanghao","1234");
        sqlSession.update("net.wanho.mapper.UserMapper.updateUser",user);
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void testDeleteUser(){
        User user=new User(6,"yanglei","1234");
       /* sqlSession.update("net.wanho.mapper.UserMapper.deleteUser",user);
        sqlSession.commit();
        sqlSession.close();*/
        SqlSession session = MybatisUtil.getSession();
        session.update("net.wanho.mapper.UserMapper.deleteUser",user);
        session.commit();
        MybatisUtil.closeSession();
    }
    @Test
    public void testGetAllUser(){
        SqlSession session = MybatisUtil.getSession();
        List<User> users = session.selectList("net.wanho.mapper.UserMapper.getAllUser");
        System.out.println(users);
        MybatisUtil.closeSession();
    }
    @Test
    public void testGetUserById(){
        /*SqlSession session = MybatisUtil.getSession();
        User user = session.selectOne("net.wanho.mapper.UserMapper.getUserById", 2);
        System.out.println(user);
        MybatisUtil.closeSession();*/
        User user = userMapper.getUserById(2);
        System.out.println(user);
        MybatisUtil.closeSession();
    }
    @Test
    public void testGetUserByName(){

        List<User> user = userMapper.getUserName("%杨%");
       // List<User> user = userMapper.getUserName("杨");
        System.out.println(user);
        MybatisUtil.closeSession();
    }
    @Test
    public void testGetUserByName2(){
        User user=new User();
      //  user.setUsername("%杨%");
        List<User> users = userMapper.getUserName2(user);
        System.out.println(users);
        MybatisUtil.closeSession();
    }
    @Test
    public void testAddUserReturnKey(){
        User user=new User(null,"yanglei","1234");
       // userMapper.addUserReturnKey(user);
        userMapper.addUserReturnKey2(user);
        sqlSession.commit();
        System.out.println(user.getId());

    }
}
