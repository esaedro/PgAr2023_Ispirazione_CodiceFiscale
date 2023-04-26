package Arnaldo;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class LettoreXML {
    private static final String FILE_COMUNI = System.getProperty("user.dir") + "/file_XML/Comuni.xml";
    private static final String FILE_PERSONE = System.getProperty("user.dir") + "/file_XML/InputPersone.xml";
    private static final String FILE_CODICI_FISCALI = System.getProperty("user.dir") + "/file_XML/CodiciFiscali.xml";

    public static void main(String[] args) throws XMLStreamException {
/*         for (Persona persona : creaPersone()) {
            System.out.println(persona);
        }
        System.out.println(trovaCodiceComune("AGLIANO TERME"));
        for (ArrayList<String> vettore : leggiControllaCodiciFiscali()){
            System.out.println();
            for (String stringa : vettore) {
                System.out.println(stringa);
            }
        } */
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

    public static boolean controllaPresenzaComune(String codiceComune) {
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

        try {

            while (xmlr.hasNext()) {
                switch (xmlr.getEventType()) {
                    case XMLStreamReader.START_ELEMENT:
                        tag = xmlr.getLocalName();
                        break;
                    
                    case XMLStreamReader.CHARACTERS:
                        if(tag.equals("codice") && xmlr.getText().trim().equals(codiceComune)) {
                            return true;
                        }

                        break;
                }
                xmlr.next();
            }
        } catch(XMLStreamException e) {
            System.out.println("ERORR");
        }
        return false;
    }

    public static ArrayList<ArrayList<String>> leggiControllaCodiciFiscali() throws XMLStreamException {
        XMLInputFactory xmlif = null;
        XMLStreamReader xmlr = null;
        String tag = "";
        ArrayList<ArrayList<String>> codici = new ArrayList<ArrayList<String>>();
        codici.add(new ArrayList<String>());
        codici.add(new ArrayList<String>());

        try {
            xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader(FILE_CODICI_FISCALI, new FileInputStream(FILE_CODICI_FISCALI));
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
                    if (tag.equals("codice") && !xmlr.getText().trim().isEmpty()) {
                        if (ControllaCodiceFiscale.controllaCodiceIntero(xmlr.getText())) {
                            codici.get(0).add(xmlr.getText());
                        } else {
                            codici.get(1).add(xmlr.getText());
                        }
                    }
                    break;
            }
            xmlr.next();
        }

        return codici;
    }

       public static ArrayList<Persona> creaPersone() throws XMLStreamException {
        ArrayList <Persona> listaPersone = new ArrayList<>();
        String nome = "";
        String cognome = "";
        Sesso sesso = null;
        Calendar dataDiNascita = null;
        String comuneDiNascita = "";

        XMLInputFactory xmlif = null;
        XMLStreamReader xmlr = null;
        String tag = "";

        try {
            xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader(FILE_PERSONE, new FileInputStream(FILE_PERSONE));
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
                    if (!(xmlr.getText().trim().isEmpty())) {
                        switch (tag) {
                            case "nome":
                                nome = xmlr.getText();
                                break;

                            case "cognome":
                                cognome = xmlr.getText();
                                break;

                            case "sesso":
                                if (xmlr.getText().equals("M")) {
                                    sesso = Sesso.Maschio;
                                } else {
                                    sesso = Sesso.Femmina;
                                }
                                break;
                            case "comune_nascita":
                                comuneDiNascita = xmlr.getText();
                                break;
                            case "data_nascita":
                                String data = xmlr.getText();
                                String anno = data.substring(0, 4);
                                String mese = data.substring(5, 7);
                                String giorno = data.substring(8, data.length());
                                dataDiNascita = new GregorianCalendar(Integer.parseInt(anno), Integer.parseInt(mese) - 1, Integer.parseInt(giorno));
                                break;
                            default:
                                break;
                        }
                    }
                    break;
                case XMLStreamReader.END_ELEMENT:
                    tag = xmlr.getLocalName();
                    if (tag.equals("persona")) {
                        listaPersone.add(new Persona(nome, cognome, sesso, dataDiNascita, comuneDiNascita));
                    }
                    break;
            }

            xmlr.next();
        }

        return listaPersone;
    }
    
}