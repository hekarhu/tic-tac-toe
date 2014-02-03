/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import ohjelmoinninharjoitustyo.PeliRuutu;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hekarhu
 */
public class PeliRuutuTestit {

    public PeliRuutuTestit() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testaaRuutu() {
        PeliRuutu ruutu = new PeliRuutu();
        assertTrue(ruutu.getRistiVaiNolla() == ' ');

    }

    @Test
    public void testaaOnkoOikeaSisalto() {
        PeliRuutu ruutu = new PeliRuutu();
        ruutu.setNolla();
        assertTrue(ruutu.getRistiVaiNolla() == 'o');
    }
    @Test
    public void testaaOnkoVaaraSisalto() {
        PeliRuutu ruutu = new PeliRuutu();
        ruutu.setNolla();
        assertFalse(ruutu.getRistiVaiNolla() == 'x');
    }
    @Test
    public void testaaJosPelattu() {
        PeliRuutu ruutu = new PeliRuutu();
        ruutu.setNolla();
        assertFalse(ruutu.setRisti());
        
    }
}