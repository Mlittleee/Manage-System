package com.project.managementsystem;

import com.project.managementsystem.pojo.User;
import com.project.managementsystem.service.IUserService;
import com.project.managementsystem.universal.QueryPageParam;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;

@SpringBootTest
class ManageSystemApplicationTests {

    @Autowired
    private IUserService IUserService;

    @Test
    void contextLoads() {
    }
    //单个查询，mybatis-plus中有的方法
    @Test
    void UserServiceTest(){
        System.out.println(IUserService.getById(1));
    }

    //自写方法
    @Test
    void UserServiceTest2(){
        List<User> list = IUserService.listAll();
        for (User user : list) {
            System.out.println(user);
        }
    }

    //测试分页的封装类
    @Test
    void QueryPageParamTest(){
        QueryPageParam query = new QueryPageParam();
        query.setPageNum(1);
        query.setPageSize(2);
        //给空的hashmap添加键值对
        query.setParam(new HashMap());
        query.getParam().put("username","mhc");
        query.getParam().put("password","123456");

        System.out.println("num=="+query.getPageNum());
        System.out.println("size=="+query.getPageSize());

        HashMap param = query.getParam();
        String name = (String)param.get("username");
        System.out.println("name=="+name);
        }

}
