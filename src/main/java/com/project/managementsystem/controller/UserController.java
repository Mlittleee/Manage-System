package com.project.managementsystem.controller;

import cn.hutool.core.util.StrUtil;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.managementsystem.mapper.UserMapper;
import com.project.managementsystem.pojo.User;
import com.project.managementsystem.service.UserService;
import com.project.managementsystem.universal.QueryPageParam;
import com.project.managementsystem.universal.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/************************
 * Manage-System
 * com.project.managementsystem.controller
 * MHC
 * author : mhc
 * date:  2023/6/11 15:23
 * description : 
 ************************/
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    //用户新增
    @PostMapping("/add")
    public Result<User> addUser(@RequestBody User user){
        Boolean isAdd = userService.save(user);
        if (isAdd){
            return Result.success(user);
        }else {
            return Result.error("新增用户失败");
        }
    }

    //用户修改
   @PostMapping("/update")
    public Result<User> updateUser(@RequestBody User user){
        boolean isUpdate = userService.updateById(user);
        if (isUpdate){
            return Result.success(user);
        }else {
            return Result.error("修改用户失败");
        }
    }

    //用户新增或者修改
    @PostMapping("/addOrUpdate")
    public Result<User> addUserOrUpdateUser(@RequestBody User user){
        boolean isAddOrUpdate = userService.saveOrUpdate(user);
        if (isAddOrUpdate){
            return Result.success(user);
        }else {
            return Result.error("新增或者修改用户失败");
        }
    }

    //用户删除
    @GetMapping("/delete")
    public Result<String> deleteUser(Integer id){
        boolean isDelete = userService.removeById(id);
        if (!isDelete){
            //返回json数据
            return Result.success("删除用户成功");
        }else {
            return Result.error("删除用户失败");
        }
    }

    //用户查询(模糊查询)
    @GetMapping("/query")
    public Result<List<User>> queryUser(String UserName){
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(User::getUserName,UserName);
        List<User> UserList= userService.list(wrapper);
        if (UserList.size()>0){
            return Result.success(UserList);
        }else {
            return Result.error("没有查询到用户");
        }
    }

    //列出所有用户
    @GetMapping("/listAll")
    public Result<List<User>> listAll(){
        List<User> UserList = userService.list();
        if (UserList.size()>0){
            return Result.success(UserList);
        }else {
            return Result.error("没有查询到用户");
        }
    }

    //分页查询
    @PostMapping("/findPage")
    public Result<List<User>> findPage(@RequestBody QueryPageParam query) {

        HashMap param = query.getParam();
        Page<User> useInfoPage = new Page<>();
        useInfoPage.setSize(query.getPageSize());
        useInfoPage.setCurrent(query.getPageNum());

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        if (param != null) {
            String userName = (String) param.get("userName");
            if (StrUtil.isNotBlank(userName)) {
                wrapper.like(User::getUserName, userName);
            }
        }
        IPage result = userService.page(useInfoPage, wrapper);
        //如果非空，则返回
        if (result.getRecords().size() > 0) {
            return Result.success(result.getRecords());
        } else {
            return Result.error("没有查询到用户");
        }
    }
}