package com.masksec.simplewiki.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 用户
 *
 * @author RichardTang
 */
@Data
@TableName
public class User {

    // 用户ID
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    // 用户名
    @TableField
    private String username;

    // 用户密码
    @TableField
    private String password;

    // 创建时间
    @TableField
    private Date createTime;

    // 更新时间
    @TableField
    private Date updateTime;

}