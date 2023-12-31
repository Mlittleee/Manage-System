package com.project.managementsystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.project.managementsystem.pojo.User;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
* @author MHC
* @description 针对表【user】的数据库操作Service
* @createDate 2023-06-11 14:47:39
*/
public interface IUserService extends IService<User> {
    //用户登录
    User login(@RequestBody Map<String,String> map, HttpSession session);
    List<User> listAll();
}
