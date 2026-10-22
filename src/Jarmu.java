// Common parent class for every vehicle type.

public abstract class Jarmu {
    private String rendszam;

    public Jarmu(String rendszam) {
        if (rendszam == null || rendszam.trim().length() == 0) {
            throw new IllegalArgumentException("A rendszam nem lehet ures.");
        }

        this.rendszam = rendszam.trim();
    }

    public String getRendszam() {
        return rendszam;
    }

    // Each vehicle type calculates its own parking fee.
    public abstract double dij(long percek);
}