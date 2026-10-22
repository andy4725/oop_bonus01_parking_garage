import org.junit.Test;
import static org.junit.Assert.*;

public class JarmuTest {

    @Test
    public void nullaPercIngyenes() {
        Auto auto = new Auto("MS01ABC");

        assertEquals(0.0, auto.dij(0), 0.001);
    }

    @Test
    public void harmincPercIngyenes() {
        Auto auto = new Auto("MS02ABC");

        assertEquals(0.0, auto.dij(30), 0.001);
    }

    @Test
    public void harmincegyPercMarFizetos() {
        Auto auto = new Auto("MS03ABC");

        // One minute after the free period starts the first paid hour.
        assertEquals(5.0, auto.dij(31), 0.001);
    }

    @Test
    public void autoDijaNemLehetNagyobbANapiPlafonnal() {
        Auto auto = new Auto("MS04ABC");

        assertEquals(50.0, auto.dij(24 * 60), 0.001);
    }

    @Test
    public void teltHazNemEngedBeUjJarmuvet() {
        // One level with one space makes a small, easy-to-check garage.
        Ora ora = new TesztOra(0);
        Parkolohaz parkolohaz = new Parkolohaz(1, 1, ora);

        parkolohaz.behajt(new Auto("MS05ABC"));

        try {
            parkolohaz.behajt(new Motor("MS06ABC"));
            fail("A masodik jarmu nem ferhet be a telt parkolohazba.");
        } catch (IllegalStateException e) {
            // This exception is expected because the only space is occupied.
        }
    }
}