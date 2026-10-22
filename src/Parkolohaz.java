import java.util.HashMap;
import java.util.Map;

// Manages the parking spaces, parked vehicles, and collected fees.
//
// This class does not calculate fees based on vehicle types.
// It asks each vehicle to calculate its own fee by calling dij().
public class Parkolohaz {
    private int szintekSzama;
    private int helyekSzintenkent;
    private Ora ora;

    // The license plate is the key, so we can quickly find a vehicle
    // when its driver asks to leave.
    private Map<String, ParkoloJegy> bentLevoJarmuvek;

    // Stores how many spaces are occupied on each level.
    // Index 0 in this array represents level 1.
    private int[] foglaltHelyek;

    // Total fees collected during this program run.
    private double napiBevetel;

    public Parkolohaz(int szintekSzama, int helyekSzintenkent, Ora ora) {
        if (szintekSzama <= 0) {
            throw new IllegalArgumentException("Legalabb egy szint kell.");
        }

        if (helyekSzintenkent <= 0) {
            throw new IllegalArgumentException("Legalabb egy parkolohely kell.");
        }

        if (ora == null) {
            throw new IllegalArgumentException("Az ora nem lehet null.");
        }

        this.szintekSzama = szintekSzama;
        this.helyekSzintenkent = helyekSzintenkent;
        this.ora = ora;

        // Start with no vehicles parked.
        this.bentLevoJarmuvek = new HashMap<String, ParkoloJegy>();

        // Java fills every element of a new int array with 0.
        this.foglaltHelyek = new int[szintekSzama];

        this.napiBevetel = 0.0;
    }

    // Parks a vehicle and returns its assigned level number.
    // Levels are numbered starting at 1 for people using the program.
    public int behajt(Jarmu jarmu) {
        if (jarmu == null) {
            throw new IllegalArgumentException("A jarmu nem lehet null.");
        }

        String rendszam = jarmu.getRendszam();

        // A single license plate cannot be parked twice at the same time.
        if (bentLevoJarmuvek.containsKey(rendszam)) {
            throw new IllegalArgumentException("Ez a jarmu mar bent van.");
        }

        // Find the first level that still has a free space.
        int szintIndex = keresSzabadSzint();

        if (szintIndex == -1) {
            throw new IllegalStateException("A parkolohaz megtelt.");
        }

        // Record the entry time and the level on the vehicle's ticket.
        ParkoloJegy jegy =
            new ParkoloJegy(jarmu, ora.most(), szintIndex + 1);

        bentLevoJarmuvek.put(rendszam, jegy);

        // Increase the occupied-space count for the assigned level.
        foglaltHelyek[szintIndex]++;

        return szintIndex + 1;
    }

    // Removes a vehicle from the garage and returns its fee.
    public double kihajt(String rendszam) {
        if (rendszam == null || rendszam.trim().length() == 0) {
            throw new IllegalArgumentException("A rendszam nem lehet ures.");
        }

        // Use trim() so accidental spaces do not prevent a lookup.
        String tisztaRendszam = rendszam.trim();
        ParkoloJegy jegy = bentLevoJarmuvek.get(tisztaRendszam);

        if (jegy == null) {
            throw new IllegalArgumentException("Ismeretlen rendszam.");
        }

        // Find the total time parked, in milliseconds.
        long elteltMillis = ora.most() - jegy.getBehajtasIdeje();

        // A clock should move forward, but prevent a negative duration
        // if a test clock or computer clock has moved backwards.
        if (elteltMillis < 0) {
            elteltMillis = 0;
        }

        // Convert milliseconds to minutes.
        // Adding 59999 rounds any partial minute up:
        // 60,000 ms becomes 1 minute, and 60,001 ms becomes 2 minutes.
        long percek = (elteltMillis + 59999) / 60000;

        // The actual vehicle class decides the fee.
        // There is no instanceof or switch statement here.
        double fizetendo = jegy.getJarmu().dij(percek);

        // Free the space on the level stored on the ticket.
        int szintIndex = jegy.getSzint() - 1;
        foglaltHelyek[szintIndex]--;

        // The vehicle is no longer inside the garage.
        bentLevoJarmuvek.remove(tisztaRendszam);

        // Add this payment to the revenue total.
        napiBevetel = napiBevetel + fizetendo;

        return fizetendo;
    }

    // Returns the zero-based index of the first level with free space.
    // Returns -1 if every level is full.
    private int keresSzabadSzint() {
        int i;

        for (i = 0; i < szintekSzama; i++) {
            if (foglaltHelyek[i] < helyekSzintenkent) {
                return i;
            }
        }

        return -1;
    }

    public int getSzintekSzama() {
        return szintekSzama;
    }

    // The public level number starts at 1, but array indexes start at 0.
    public int getFoglaltHelyek(int szint) {
        ellenorizSzint(szint);
        return foglaltHelyek[szint - 1];
    }

    public int getSzabadHelyek(int szint) {
        ellenorizSzint(szint);
        return helyekSzintenkent - foglaltHelyek[szint - 1];
    }

    public int getOsszesFoglaltHely() {
        return bentLevoJarmuvek.size();
    }

    public double getNapiBevetel() {
        return napiBevetel;
    }

    // Check that the requested level exists before using it as an array index.
    private void ellenorizSzint(int szint) {
        if (szint < 1 || szint > szintekSzama) {
            throw new IllegalArgumentException("Nincs ilyen szint.");
        }
    }
}