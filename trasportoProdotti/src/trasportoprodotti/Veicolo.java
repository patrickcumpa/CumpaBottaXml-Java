package trasportoprodotti;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TommasoBotta
 */
public class Veicolo {
    private final String id;
    private final List<Misura> misure;

    public Veicolo(String id) {
        this.id = id;
        this.misure = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public List<Misura> getMisure() {
        return misure;
    }
    
    public void aggiungiMisura(Misura misura) {
        this.misure.add(misura);
    }

}
