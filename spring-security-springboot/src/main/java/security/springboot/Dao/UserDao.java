package security.springboot.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import security.springboot.model.UserDto;

import java.util.List;

@Repository
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public UserDto getUserByUsername(String username){
        String sql = "SELECT * FROM t_user WHERE username=?";
        List<UserDto> list = jdbcTemplate.query(sql,new Object[]{username},new BeanPropertyRowMapper<>(UserDto.class));
        if(list.size() <= 0){
            return null;
        }
        return list.get(0);
    }
}
