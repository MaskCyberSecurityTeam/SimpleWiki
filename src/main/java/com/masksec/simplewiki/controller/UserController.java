package com.masksec.simplewiki.controller;

import cn.hutool.captcha.ICaptcha;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.masksec.simplewiki.bean.User;
import com.masksec.simplewiki.constant.Attribute;
import com.masksec.simplewiki.service.UserService;
import com.masksec.simplewiki.vo.RespVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 用户
 *
 * @author RichardTang
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("login")
    public String login() {
        return "login";
    }

    @PostMapping("login")
    public String loginForm(String username, String password, String code, HttpServletRequest request) {
        ICaptcha captcha = (ICaptcha) request.getSession().getAttribute(Attribute.CAPTCHA_CODE_KEY);

        if (!captcha.verify(code)) {
            request.setAttribute("msg", "验证码错误!");
            return "login";
        }

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<User>().eq(User::getUsername, username);
        User user = userService.getOne(queryWrapper);

        if (ObjectUtil.isEmpty(user)) {
            request.setAttribute("msg", "用户名或密码错误!");
        } else {
            password = SecureUtil.md5(password);
            if (user.getPassword().equals(password)) {
                request.getSession().setAttribute(Attribute.AUTH_USER_KEY, user);
                return "redirect:/index";
            } else {
                request.setAttribute("msg", "用户名或密码错误!");
            }
        }
        return "login";
    }

    @GetMapping("logout")
    public String logout(HttpSession session) {
        session.removeAttribute(Attribute.AUTH_USER_KEY);
        return "login";
    }

    @ResponseBody
    @PostMapping("changePassword")
    public RespVO changePassword(String oldPassword, String newPassword, HttpSession session) {
        boolean flag = false;
        User user = (User) session.getAttribute(Attribute.AUTH_USER_KEY);

        oldPassword = SecureUtil.md5(oldPassword);
        if (user.getPassword().equals(oldPassword)) {
            newPassword = SecureUtil.md5(newPassword);
            flag = userService.lambdaUpdate().eq(User::getId, user.getId()).set(User::getPassword, newPassword).update();
        }

        if (flag) {
            user.setPassword(newPassword);
            return RespVO.success("修改成功!");
        }

        return RespVO.fail("修改失败!");
    }
}