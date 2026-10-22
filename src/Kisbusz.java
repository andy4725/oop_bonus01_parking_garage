// A minibus costs more per hour than an ordinary car.

public class Kisbusz extends Jarmu {
    private static final double ORADIJ = 8.0;
    private static final double NAPI_PLAFON = 80.0;

    public Kisbusz(String rendszam) {
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