package percorsodrone;

import java.io.IOException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author PatrickCumpa
 */
public class PercorsoDrone {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        List<Posizione> posizioni = null;
        ParserPosizione parser = new ParserPosizione();
        
        try {
            posizioni = parser.parseDocument(args[0]);
        }
        catch (ParserConfigurationException | SAXException exception) {
            System.err.println("Errore parsing file XML");
        }
        catch (IOException exception) {
            System.err.println("Errore apertura file XML");
        }
        for (Posizione p : posizioni)
            System.out.println(p);
        parser.stampaDati();
    }
}
