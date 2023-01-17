package percorsodrone;

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
 * @since 01/17/2023
 */
public class ParserPosizione {
    
    private final List<Posizione> posizioni;
    
    public ParserPosizione() {
        posizioni = new ArrayList<>();
    }
    
    public List<Posizione> parseDocument(String filename) 
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
        nodelist = root.getElementsByTagName("posizione");
        if (nodelist != null && nodelist.getLength() > 0) {
            for (int i = 0; i < nodelist.getLength(); i++) {
                element = (Element) nodelist.item(i); //accedo al singolo elemento della nodeList
                posizioni.add(getPosizione(element));
            }
        }
        return posizioni;
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
            datetime = DatatypeFactory.newInstance()
                    .newXMLGregorianCalendar(getTextValue(element, tag))
                    .toGregorianCalendar().getTime();
        }
        catch (DatatypeConfigurationException e) {
            datetime = null;   
        }    
        return datetime;
    }
    
    private Posizione getPosizione(Element element) {
        Posizione posizione;
        
        double latitudine = Double.parseDouble(getTextValue(element, "latitudine"));
        double longitudine = Double.parseDouble(getTextValue(element, "longitudine"));
        double altitudine = Double.parseDouble(getTextValue(element, "altitudine"));
        Date dataOra = getDatetimeValue(element, "dataOra");
        posizione = new Posizione(latitudine, longitudine, altitudine, dataOra.getTime());
        
        return posizione;
    }
    
    private double getLatitudineMassima() {
        double latitudineMassima = posizioni.get(0).getLatitudine();
        for (Posizione posizione : posizioni) {
            latitudineMassima = Math.max(latitudineMassima, posizione.getLatitudine());
        }
        return latitudineMassima;
    }

    private double getLatitudineMinima() {
        double latitudineMinima = posizioni.get(0).getLatitudine();
        for (Posizione posizione : posizioni) {
            latitudineMinima = Math.min(latitudineMinima, posizione.getLatitudine());
        }
        return latitudineMinima;
    }

    private double getLongitudineMassima() {
        double longitudineMassima = posizioni.get(0).getLongitudine();
        for (Posizione posizione : posizioni) {
            longitudineMassima = Math.max(longitudineMassima, posizione.getLongitudine());
        }
        return longitudineMassima;
    }

    private double getLongitudineMinima() {
        double longitudineMinima = posizioni.get(0).getLongitudine();
        for (Posizione posizione : posizioni) {
           longitudineMinima = Math.min(longitudineMinima, posizione.getLongitudine());
        }
        return longitudineMinima;
    }
    
    public void stampaDati() {
        System.out.println("\nLatitudine massima: " + getLatitudineMassima());
        System.out.println("Latitudine minima: " + getLatitudineMinima());
        System.out.println("\nLongitudine massima: " + getLongitudineMassima());
        System.out.println("Longitudine minima: " + getLongitudineMinima());
    }
}
