package security.springboot.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.plugin.liveconnect.SecurityContextHelper;

@Controller
public class LoginController {

    @RequestMapping(value = "/login-success",produces = {"text/plain;charset=utf-8"})
    @ResponseBody
    public String loginSuccess(){
       String username = getUsername();
        return username + " logged in succeed";
    }

    /*
    @RequestMapping("/loginView")
    public String loginView(){
        return "login";
    }

     */

    @RequestMapping(value = "/r/r1",produces = {"text/plain;charset=utf-8"})
    @ResponseBody
    public String r1(){
        return "r1";
    }

    @RequestMapping(value = "/r/r2",produces = {"text/plain;charset=utf-8"})
    @ResponseBody
    public String r2(){
        return "r2";
    }

    private String getUsername(){
        String username = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal =  authentication.getPrincipal();
        if(principal == null){
            username = "anonymous";
        }
        if(principal instanceof UserDetails){
            username = ((UserDetails) principal).getUsername();
        }else {
            username = principal.toString();
        }

        return username;
    }
}
