package com.foureverhh.security.distributed.uaa.service;

import com.foureverhh.security.distributed.uaa.dao.UserDao;
import com.foureverhh.security.distributed.uaa.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class SpringDataUserDetailsService implements UserDetailsService {
    @Autowired
    private UserDao userDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("******************************before get user************************");
        //Connect database
        UserDto userDto = userDao.getUserByUsername(username);

        if(userDto == null) {
            System.out.println("******************************user is null************************");
            return null;
        }
        List<String> permissions = userDao.findPermissionsByUserId(userDto.getId());
        String[] permissionArray = new String[permissions.size()];
        permissions.toArray(permissionArray);
        System.out.println("Username is " +username);
        UserDetails user = User.withUsername(userDto.getUsername())
                .password(userDto.getPassword()) //"123"
                .authorities(permissionArray)
                .build();
        return user;
    }
}
