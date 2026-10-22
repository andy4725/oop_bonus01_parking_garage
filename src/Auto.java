// An ordinary car: first 30 minutes are free,
// then each started hour costs 5 lei, with a daily cap of 50 lei.

public class Auto extends Jarmu {
    private static final double ORADIJ = 5.0;
    private static final double NAPI_PLAFON = 50.0;

    public Auto(String rendszam) {
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