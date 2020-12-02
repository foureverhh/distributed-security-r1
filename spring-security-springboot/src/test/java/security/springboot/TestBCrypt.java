package security.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class TestBCrypt {
    @Test
    public void testBCrypt(){
        //encode
        String hashPassword = BCrypt.hashpw("123",BCrypt.gensalt());
        System.out.println("=============>" + hashPassword);

        //decode
        boolean checkpw = BCrypt.checkpw("123","$2a$10$UEdpivL2FaPwYB3nRlFho.S9DFyImFDpCF8obwC9j6TdXCO5vZBWy");
        System.out.println("=============>" + checkpw);
    }
}
