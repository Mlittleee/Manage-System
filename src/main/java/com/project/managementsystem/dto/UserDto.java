package com.project.managementsystem.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.project.managementsystem.pojo.User;


/************************
 * Manage-System
 * com.project.managementsystem.dto
 * MHC
 * author : mhc
 * date:  2023/6/13 17:41
 * description : 用来添加token的UserDto
 ************************/

@EqualsAndHashCode(callSuper = true)
@Data
public class UserDto extends User {
    String token;
}
