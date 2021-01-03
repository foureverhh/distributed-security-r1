import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class TestBCrypt {
    @Test
    public void testBCrypt(){
        String code = BCrypt.hashpw("secret",BCrypt.gensalt());
        System.out.println("******************************************");
        System.out.println(code);
        System.out.println("******************************************");
        System.out.println(BCrypt.checkpw("secret",code));
    }
}
