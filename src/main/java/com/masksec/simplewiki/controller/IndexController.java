package com.masksec.simplewiki.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 首页
 *
 * @author RichardTang
 */
@Controller
@RequestMapping(value = {"/", "index"})
public class IndexController {

    @GetMapping
    public String index() {
        return "index";
    }
}