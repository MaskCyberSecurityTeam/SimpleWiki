package com.masksec.simplewiki.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.masksec.simplewiki.bean.User;
import com.masksec.simplewiki.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {
}
