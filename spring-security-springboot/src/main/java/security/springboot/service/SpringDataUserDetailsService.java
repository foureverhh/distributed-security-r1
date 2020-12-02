package security.springboot.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SpringDataUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Connect database
        System.out.println("Username is " +username);
        UserDetails user = User.withUsername("w5")
                .password("$2a$10$Q6/ljzt7ds3/FwsJnM4Yi.QYkGCVK.ypmWM9ev8QFkMcLy84Hc0DO") //"123"
                .authorities("p1","p2")
                .build();
        return user;
    }
}
