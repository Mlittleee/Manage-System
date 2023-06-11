package com.project.managementsystem;

import com.project.managementsystem.pojo.User;
import com.project.managementsystem.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ManageSystemApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
    }
    //单个查询，mybatis-plus中有的方法
    @Test
    void UserServiceTest(){
        System.out.println(userService.getById(1));
    }

    //自写方法
    @Test
    void UserServiceTest2(){
        List<User> list = userService.listAll();
        for (User user : list) {
            System.out.println(user);
        }
    }
}
