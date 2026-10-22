public class TesztOra implements Ora {
    private long aktualisIdo;

    public TesztOra(long kezdoIdo) {
        aktualisIdo = kezdoIdo;
    }

    public long most() {
        return aktualisIdo;
    }

    // Move the test clock forward by the given number of milliseconds.
    public void eloreallit(long milliszekundum) {
        aktualisIdo = aktualisIdo + milliszekundum;
    }
}