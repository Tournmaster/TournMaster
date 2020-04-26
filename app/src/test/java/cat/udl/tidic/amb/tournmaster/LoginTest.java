package cat.udl.tidic.amb.tournmaster;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.assertFalse;

public class LoginTest {

    private Login login;
    @Before
    public void setUp() { login = new Login(); }

//    @Test
//    public void ValidAdressPasses () throws Exception{
//        assertTrue (login.isValidEmailAddress("xxx@hotmial.com"));
//    }

    @Test
    public void validPasswordPasses() throws Exception {
        //LoginUtils loginUtils = new LoginUtils();

        //assertTrue(Login.isValidPassword("a1@R1234"));
    }
}
