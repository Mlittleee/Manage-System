package com.project.managementsystem.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.managementsystem.dto.UserDto;
import com.project.managementsystem.exception.HaveDisabledException;
import com.project.managementsystem.exception.PasswordWrongException;
import com.project.managementsystem.pojo.User;
import com.project.managementsystem.service.IUserService;
import com.project.managementsystem.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.project.managementsystem.utils.RedisConstants.LOGIN_USER_KEY;
import static com.project.managementsystem.utils.RedisConstants.LOGIN_USER_TTL;

/**
* @author MHC
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-06-11 14:47:39
*/

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    //将 HTTP 请求体中的 JSON 或 XML 格式的数据绑定到一个 Map<String, String> 类型的变量
    public UserDto login(@RequestBody Map<String,String> map, HttpSession session){
        String userName = map.get("userName");
        String password = map.get("password");
        //查看该用户是否为新用户
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getUserName,userName);
        User user = getOne(userLambdaQueryWrapper);
        if(user==null){
            //是新用户,自动注册
            user = new User();
            user.setUserName(userName);
            user.setPassword(password);
            user.setStatus(1);
            user.setRoleId(1);
            save(user);
            //将用户的信息存到session中，这样可以通过过滤器
            //随机生成token作为登录令牌
            String token = UUID.randomUUID().toString();
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setToken(token);
            userDto.setUserName(userName);
            userDto.setPassword(password);
            userDto.setStatus(1);
            userDto.setRoleId(1);
            Map<String, Object> userMap = BeanUtil.beanToMap(userDto, new HashMap<>(),
                    CopyOptions.create()
                            .setIgnoreNullValue(true)
                            //在setFieldValueEditor中也需要判空
                            .setFieldValueEditor((fieldName,fieldValue) -> {
                                if (fieldValue == null){
                                    fieldValue = "0";
                                }else {
                                    fieldValue = fieldValue + "";
                                }
                                return fieldValue;
                            }));
            //存储
            stringRedisTemplate.opsForHash().putAll(LOGIN_USER_KEY+token,userMap);
            //设置有效期
            stringRedisTemplate.expire(LOGIN_USER_KEY+token,LOGIN_USER_TTL, TimeUnit.MINUTES);
            return userDto;
        }else{
            if(user.getStatus()==0){
                throw new HaveDisabledException("用户已被禁用");
            }else if(!Objects.equals(user.getPassword(), password)){
                throw new PasswordWrongException("密码错误");
            }else{
                //将用户的信息存到session中，这样可以通过过滤器
                //随机生成token作为登录令牌
                String token = UUID.randomUUID().toString();
                session.setAttribute(token,user);
                UserDto userDto = new UserDto();
                userDto.setId(user.getId());
                userDto.setToken(token);
                userDto.setUserName(user.getUserName());
                userDto.setPassword(user.getPassword());
                userDto.setStatus(user.getStatus());
                userDto.setRoleId(user.getRoleId());
                Map<String, Object> userMap = BeanUtil.beanToMap(userDto, new HashMap<>(),
                        CopyOptions.create()
                                .setIgnoreNullValue(true)
                                //在setFieldValueEditor中也需要判空
                                .setFieldValueEditor((fieldName,fieldValue) -> {
                                    if (fieldValue == null){
                                        fieldValue = "0";
                                    }else {
                                        fieldValue = fieldValue + "";
                                    }
                                    return fieldValue;
                                }));
                //存储
                stringRedisTemplate.opsForHash().putAll(LOGIN_USER_KEY+token,userMap);
                //设置有效期
                stringRedisTemplate.expire(LOGIN_USER_KEY+token,LOGIN_USER_TTL, TimeUnit.MINUTES);
                return userDto;
            }
        }
    }

    @Override
    public List<User> listAll() {
        return userMapper.listAll();
    }

}





