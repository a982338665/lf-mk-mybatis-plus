package com.lf.mp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lf.mp.dao.UserMapper;
import com.lf.mp.entity.User;
import com.lf.mp.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author : Mr huangye
 * @URL : CSDN 皇夜_
 * @createTime : 2020/7/9 20:07
 * @Description :
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
