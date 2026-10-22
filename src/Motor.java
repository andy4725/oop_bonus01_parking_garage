// A motorcycle has a lower hourly rate and a lower daily cap.

public class Motor extends Jarmu {
    private static final double ORADIJ = 3.0;
    private static final double NAPI_PLAFON = 30.0;

    public Motor(String rendszam) {
        super(rendszam);
    }

    public double dij(long percek) {
        if (percek <= 30) {
            return 0.0;
        }

        long fizetosPerc = percek - 30;
        long orak = (fizetosPerc + 59) / 60;
        double osszeg = orak * ORADIJ;

        if (osszeg > NAPI_PLAFON) {
            osszeg = NAPI_PLAFON;
        }

        return osszeg;
    }
}