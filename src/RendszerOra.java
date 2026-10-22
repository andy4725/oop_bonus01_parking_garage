// Normal implementation: uses the computer's current time.

public class RendszerOra implements Ora {
    public long most() {
        return System.currentTimeMillis();
    }
}