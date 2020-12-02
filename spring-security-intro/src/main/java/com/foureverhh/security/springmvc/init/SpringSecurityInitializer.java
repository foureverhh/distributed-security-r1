package com.foureverhh.security.springmvc.init;

import com.foureverhh.security.springmvc.config.WebSecurityConfig;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;


public class SpringSecurityInitializer extends AbstractSecurityWebApplicationInitializer {
    public SpringSecurityInitializer(){
        //if there is no spring context, the super syntax is needed
        //super(WebSecurityConfig.class);
    }
}
