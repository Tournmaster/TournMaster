package cat.udl.tidic.amb.tournmaster;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class PerfilTest {
    private Perfil perfil;

    @Before
    public void setUp() { perfil = new Perfil();}
    @Test
    public void validAddressPasses() throws Exception {

        assertTrue(perfil.isValidEmailAddress("xxx@hotmail.com"));
    }

    @Test
    public void invalidAddressFails() throws Exception {

        assertFalse(perfil.isValidEmailAddress("xxx"));
    }

    @Test
    public void invalidAddressFails2() throws Exception {

        assertFalse(perfil.isValidEmailAddress("1253@"));
    }

    @Test
    public void invaliPhoneNumb() throws Exception {

        assertFalse(perfil.isValidPhoneNumber("1253"));
    }

    @Test
    public void validPhoneNumb() throws Exception {
        assertTrue(perfil.isValidPhoneNumber("622345880"));

    }






}
