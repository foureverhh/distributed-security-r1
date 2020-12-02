package com.foureverhh.security.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @RequestMapping(value = "/login-success",produces = {"text/plain;charset=utf-8"})
    public String loginSuccess(){
        return "log in succeed";
    }

    @RequestMapping(value = "/r/r1",produces = {"text/plain;charset=utf-8"})
    public String r1(){
        return "r1";
    }

    @RequestMapping(value = "/r/r2",produces = {"text/plain;charset=utf-8"})
    public String r2(){
        return "r2";
    }
}
