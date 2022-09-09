package com.masksec.simplewiki.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ICaptcha;
import com.masksec.simplewiki.constant.Attribute;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 验证码
 *
 * @author RichardTang
 */
@Controller
public class CaptchaController {

    @RequestMapping("/captcha")
    public void captcha(HttpServletResponse response, HttpSession session) throws Exception {
        ICaptcha captcha = CaptchaUtil.createCircleCaptcha(
                Attribute.CAPTCHA_CODE_WIDTH, Attribute.CAPTCHA_CODE_HEIGHT,
                Attribute.CAPTCHA_CODE_COUNT, Attribute.CAPTCHA_CODE_CIRCLE
        );
        session.setAttribute(Attribute.CAPTCHA_CODE_KEY, captcha);
        captcha.write(response.getOutputStream());
    }
}