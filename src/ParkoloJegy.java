public class ParkoloJegy {
    private Jarmu jarmu;
    private long behajtasIdeje;
    private int szint;

    public ParkoloJegy(Jarmu jarmu, long behajtasIdeje, int szint) {
        // Keep the vehicle so we can ask it to calculate its own fee later.
        this.jarmu = jarmu;

        // Save the entry time so we can work out the parking duration.
        this.behajtasIdeje = behajtasIdeje;

        // Save the assigned level so we can free its space when the vehicle leaves.
        this.szint = szint;
    }

    public Jarmu getJarmu() {
        return jarmu;
    }

    public long getBehajtasIdeje() {
        return behajtasIdeje;
    }

    public int getSzint() {
        return szint;
    }
}