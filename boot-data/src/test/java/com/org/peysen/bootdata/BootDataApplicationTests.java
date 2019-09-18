package com.org.peysen.bootdata;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.org.peysen.bootdata.dao.UserMapper;
import com.org.peysen.bootdata.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BootDataApplicationTests {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserMapper userMapper;

    @Test
    public void contextLoads() throws SQLException {

        System.out.println(dataSource.getClass());

        Connection connection = dataSource.getConnection();
        System.out.println(connection.getClass());

        connection.close();
    }

    @Test
    public void selectTest(){
        List<User> users = userMapper.selectList(null);

        for(User user:users){
            System.out.println(user.toString());
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        User user = userMapper.selectOne(queryWrapper.eq("name", "张三"));
        System.out.println(user.toString());

        queryWrapper.like("name","三").lt("id","1").between("id",1,10);
        users = userMapper.selectList(queryWrapper);


    }

    @Test
    public void insertTest(){
        User user = new User();
        user.setId(4);
        user.setName("peysen");

        int insert = userMapper.insert(user);
        System.out.println("insert:"+insert);
    }


}
