package security.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import security.springboot.Dao.UserDao;
import security.springboot.model.UserDto;

@Service
public class SpringDataUserDetailsService implements UserDetailsService {
    @Autowired
    private UserDao userDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Connect database
        UserDto userDto = userDao.getUserByUsername(username);
        if(userDto == null)
            return null;
        System.out.println("Username is " +username);
        UserDetails user = User.withUsername(userDto.getUsername())
                .password(userDto.getPassword()) //"123 "
                .authorities("p1","p2")
                .build();
        return user;
    }
}
