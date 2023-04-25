package Arnaldo;

import java.io.FileInputStream;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class LettoreXML {
    private static final String FILE_COMUNI = System.getProperty("user.dir") + "/file_XML/Comuni.xml";
    private static final String FILE_PERSONE = System.getProperty("user.dir") + "/file_XML/InputPersone.xml";
    private static final String FILE_CODICI_FISCALI = System.getProperty("user.dir") + "/file_XML/CodiciFiscali.xml";

    public static void main(String[] args) throws XMLStreamException {
        System.out.println(trovaCodiceComune("AGLIANO TERME"));
    }
    
    public static String trovaCodiceComune(String nomeComune) throws XMLStreamException {
        XMLInputFactory xmlif = null;
        XMLStreamReader xmlr = null;
        String tag = "";

        try {
            xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader(FILE_COMUNI, new FileInputStream(FILE_COMUNI));
        } catch (Exception e) {
            System.out.println("Errore nell'inizializzazione del reader:");
            System.out.println(e.getMessage());
        }

        while (xmlr.hasNext()) {
            switch (xmlr.getEventType()) {
                case XMLStreamReader.START_ELEMENT:
                    tag = xmlr.getLocalName();
                    break;
                
                case XMLStreamReader.CHARACTERS:
                    if (tag.equals("nome")) {
                        if (xmlr.getText().equals(nomeComune)) {
                            for (int i = 0; i < 4; i++, xmlr.next());
                            return xmlr.getText();
                        }
                    }

                    break;
            }
            xmlr.next();
        }
        return "Non trovato";
    }
}
