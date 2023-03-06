package com.endlessway.test;

import com.endlessway.config.SpringConfig;
import com.endlessway.dao.UserDao;
import com.endlessway.pojo.SysUser;
import com.endlessway.service.UserService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.InputStream;
import java.sql.*;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath:applicationContext.xml"})
public class Test {

    @org.junit.Test
    public void test() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = (UserService) applicationContext.getBean("userServiceImpl");
        System.out.println(userService);
    }

    @org.junit.Test
    public void test1() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserService userService1 = (UserService) applicationContext.getBean("userServiceImpl");
        UserService userService2 = (UserService) applicationContext.getBean("userServiceImpl");
        System.out.println(userService1.hashCode());
        System.out.println(userService2.hashCode());
    }

    @org.junit.Test
    public void test3() throws Exception {
        //加载驱动
        Class.forName("com.mysql.jdbc.Driver");
        //获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jt-cloud-admin?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8", "root", "admin");
        //执行sql
        PreparedStatement preparedStatement = connection.prepareStatement("select * from sys_users");
        ResultSet resultSet = preparedStatement.executeQuery();
        //遍历结果
        while (resultSet.next()) {
            SysUser user = new SysUser();
            user.setId(resultSet.getInt("id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setEmail(resultSet.getString("email"));
            System.out.println(user);
        }
        //关闭资源
        connection.close();
        preparedStatement.close();
        resultSet.close();
    }


    @org.junit.Test
    public void testUser() throws Exception {
        //加载核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMybatisConfig.xml");
        //获得sqlSession工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //获得sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //执行sql语句
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        //打印结果
        userDao.selectUser().forEach(System.out::println);
        //释放资源
        sqlSession.close();
    }

}
