package com.masksec.simplewiki.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.masksec.simplewiki.bean.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
