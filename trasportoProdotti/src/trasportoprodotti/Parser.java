package trasportoprodotti;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author PatrickCumpa
 */
public class Parser {
    
    private final List<Veicolo> veicoli;

    public Parser() {
        this.veicoli = new ArrayList<>();
    }
    
    public List<Veicolo> parseDocument(String filename) 
            throws ParserConfigurationException, SAXException, IOException {
        
        DocumentBuilderFactory factory;
        DocumentBuilder builder;
        Document document;
        Element root, element;
        NodeList nodelist;
        
        // creazione dell'albero DOM dal documento XML
        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
        document = builder.parse(filename);
        root = document.getDocumentElement();
        // generazione della lista degli elementi "posizione"
        nodelist = root.getElementsByTagName("veicolo");
        if (nodelist != null && nodelist.getLength() > 0) {
            for (int i = 0; i < nodelist.getLength(); i++) {
                element = (Element) nodelist.item(i); //accedo al singolo elemento della nodeList
                Veicolo veicolo = getVeicolo(element);
                veicoli.add(veicolo);
            }
        }
        return veicoli;
    }
    
    // restituisce il valore testuale dell'elemento figlio specificato
    private String getTextValue(Element element, String tag) {
        String value = null;
        NodeList nodelist;
        nodelist = element.getElementsByTagName(tag);
        if (nodelist != null && nodelist.getLength() > 0) {
            value = nodelist.item(0).getFirstChild().getNodeValue();
        }
        return value;
    }
    
    // restituisce il valore data-ora dell'elemento figlio specificato
    private Date getDatetimeValue(Element element, String tag) {
        Date datetime;
        try {
            datetime  = DatatypeFactory.newInstance()
                    .newXMLGregorianCalendar(getTextValue(element, tag))
                    .toGregorianCalendar().getTime();
        }
        catch (DatatypeConfigurationException e) {
            datetime = null;   
        }    
        return datetime;
    }
    
    private Veicolo getVeicolo(Element element) {
        NodeList misuraList = element.getElementsByTagName("misura");
        String id = getTextValue(element, "id");
        Veicolo veicolo = new Veicolo(id);
        
        for (int i = 0; i < misuraList.getLength(); i++) {
            double temperatura = Double.parseDouble(
                    getTextValue((Element) misuraList.item(i), "temperatura"));
            Date dataOra = getDatetimeValue((Element) misuraList.item(i), "data_ora");
            
            veicolo.aggiungiMisura(new Misura(temperatura, dataOra.getTime()));
        }
        return veicolo;
    }
    
}
