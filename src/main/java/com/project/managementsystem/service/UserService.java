package com.project.managementsystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.project.managementsystem.pojo.User;

import java.util.List;

/**
* @author MHC
* @description 针对表【user】的数据库操作Service
* @createDate 2023-06-11 14:47:39
*/
public interface UserService extends IService<User> {
    List<User> listAll();
}
