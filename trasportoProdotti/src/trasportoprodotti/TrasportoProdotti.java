package trasportoprodotti;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author TommasoBotta
 */
public class TrasportoProdotti {
    
    private static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        
        boolean check = true;
        List<Veicolo> veicoli = null;
        Parser parser = new Parser();
        List<Long> timeStamp = new ArrayList<>();
        
        try {
            veicoli = parser.parseDocument(args[0]);
        } catch (ParserConfigurationException | SAXException ex) {
            System.err.println("Errore parsing file XML");
        } catch (IOException ex) {
            System.err.println("Errore apertura file XML");
        }
        
        System.out.print("Inserire il valore della soglia: ");
        double soglia = Double.parseDouble(input.readLine());
        
        for (Veicolo veicolo: veicoli) {
            for (Misura misura : veicolo.getMisure()) {
                if (misura.getTemperatura() > soglia) {
                    check = false;
                    timeStamp.add(misura.getDataOra());
                }
            }
            System.out.println("Id veicolo: " + veicolo.getId() + " Soglia: " + check);
            System.out.println("TimeStamp: " + timeStamp);
        }
        
    }
    
}
