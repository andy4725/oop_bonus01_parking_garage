// An electric car pays the ordinary car parking rate plus a
// charging fee for each started hour.

public class ElektromosAuto extends Jarmu {
    private static final double PARKOLO_ORADIJ = 5.0;
    private static final double TOLTES_ORADIJ = 4.0;
    private static final double NAPI_PLAFON = 70.0;

    public ElektromosAuto(String rendszam) {
        super(rendszam);
    }

    public double dij(long percek) {
        if (percek <= 30) {
            return 0.0;
        }

        long fizetosPerc = percek - 30;
        long orak = (fizetosPerc + 59) / 60;
        double osszeg = orak * (PARKOLO_ORADIJ + TOLTES_ORADIJ);

        if (osszeg > NAPI_PLAFON) {
            osszeg = NAPI_PLAFON;
        }

        return osszeg;
    }
}