package com.foureverhh.security.distributed.order.controller;

import com.foureverhh.security.distributed.order.model.UserDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
public class OrderController {

    @GetMapping(value = "/r1")
    @PreAuthorize("hasAuthority('p1')")
    @ResponseBody
    public String r1(){
        //获取用户身份
        UserDto principal = (UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        return principal.getUsername()+" access Resource 1";
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return "hello";
    }
}
