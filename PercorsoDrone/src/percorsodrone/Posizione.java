package percorsodrone;

/**
 *
 * @author TommasoBotta
 */
public class Posizione {

    private final double latitudine;
    private final double longitudine;
    private final double altitudine;
    private final long dataOra;

    public Posizione(double latitudine, double longitudine, 
            double altitudine, long dataOra) {

        this.latitudine = latitudine;
        this.longitudine = longitudine;
        this.altitudine = altitudine;
        this.dataOra = dataOra;
    }

    public Posizione(Posizione posizione) {
        this.latitudine = posizione.latitudine;
        this.longitudine = posizione.longitudine;
        this.altitudine = posizione.altitudine;
        this.dataOra = posizione.dataOra;
    }

    public double getLatitudine() {
        return latitudine;
    }

    public double getLongitudine() {
        return longitudine;
    }

    @Override
    public String toString() {
        return "Posizione{" + "latitudine=" + latitudine + ", longitudine=" 
                + longitudine + ", altitudine=" + altitudine + ", dataOra=" + dataOra + '}';
    }

}
