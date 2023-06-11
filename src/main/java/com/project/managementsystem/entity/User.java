package com.project.managementsystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/************************
 * ManageSystem
 * com.project.managesystem.entity
 * MHC
 * author : mhc
 * date:  2023/6/11 12:23
 * description : 实体类 User
 * 类实现了Serializable接口，表示该类的对象可以在网络上传输或持久化存储
 * 序列化版本号，用于在序列化和反序列化过程中确保类的版本一致性
 ************************/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String userName;

    private String password;

    private Integer status;

    @TableField("role")
    private Integer roleId;
}
