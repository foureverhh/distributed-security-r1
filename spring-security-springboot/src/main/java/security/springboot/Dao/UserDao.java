package security.springboot.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import security.springboot.model.PermissionDto;
import security.springboot.model.UserDto;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    //find user by id
    public UserDto getUserByUsername(String username){
        String sql = "SELECT * FROM t_user WHERE username=?";
        List<UserDto> list = jdbcTemplate.query(sql,new Object[]{username},new BeanPropertyRowMapper<>(UserDto.class));
        if(list.size() <= 0){
            return null; //spring security will throw an exception
        }
        return list.get(0);
    }

    //find user authorities by user id
    public List<String> findPermissionsByUserId(String userId){
        List<String> permissions = new ArrayList<>();
        String sql = "select * from t_permission where id in("+
                        "select permission_id from t_role_permission where role_id in (" +
                            "select role_id from t_user_role where user_id=?" +
                        ")"+
                     ")";
        List<PermissionDto> permissionDtos = jdbcTemplate.query(sql,new Object[]{userId},new BeanPropertyRowMapper<>(PermissionDto.class));
        /*
        for(PermissionDto permission : permissionDtos){
            permissions.add(permission.getCode());
        }
         */
        permissionDtos.forEach( p -> permissions.add(p.getCode()));
        return permissions;
    }
}
