package com.project.managementsystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.project.managementsystem.entity.User;
import org.apache.ibatis.annotations.Mapper;

/************************
 * ManageSystem
 * com.project.managesystem.mapper
 * MHC
 * author : mhc
 * date:  2023/6/11 13:04
 * description : UserMapper接口
 ************************/
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
