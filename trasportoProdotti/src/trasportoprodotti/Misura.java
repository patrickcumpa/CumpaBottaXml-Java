package trasportoprodotti;

/**
 *
 * @author TommasoBotta
 */
public class Misura {
    
    private final double temperatura;
    private final long dataOra;

    public Misura(double temperatura, long dataOra) {
        this.temperatura = temperatura;
        this.dataOra = dataOra;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public long getDataOra() {
        return dataOra;
    }
    
}
