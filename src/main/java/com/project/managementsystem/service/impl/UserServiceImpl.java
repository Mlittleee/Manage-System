package com.project.managementsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.managementsystem.entity.User;
import com.project.managementsystem.mapper.UserMapper;
import com.project.managementsystem.service.UserService;
import org.springframework.stereotype.Service;

/************************
 * ManageSystem
 * com.project.managesystem.service.impl
 * MHC
 * author : mhc
 * date:  2023/6/11 13:07
 * description : UserServiceImpl
 ************************/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
