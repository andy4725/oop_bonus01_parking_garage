// The parking house uses this interface to get the current time.
// Tests can provide a different implementation with a controllable clock.

public interface Ora {
    long most();
}