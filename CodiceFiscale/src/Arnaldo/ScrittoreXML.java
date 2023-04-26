package Arnaldo;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

public class ScrittoreXML { 
    private static final String FILE_CODICI_PERSONE = System.getProperty("user.dir") + "/file_XML/CodiciPersone.xml";

    public static void main(String[] args) {
        generaFileOutput();
    }

    public static void generaFileOutput() {
        XMLOutputFactory xmlof = null;
        XMLStreamWriter xmlw = null;
        try {
            xmlof = XMLOutputFactory.newInstance();
            xmlw = xmlof.createXMLStreamWriter(new FileOutputStream(FILE_CODICI_PERSONE), "utf-8");
            xmlw.writeStartDocument("utf-8", "1.0");
        } catch (Exception e) {
            System.out.println("Errore nell'inizializzazione del writer:");
            System.out.println(e.getMessage());
        }

        try {
            ArrayList<Persona> listaDellePersone = LettoreXML.creaPersone();
            ArrayList<ArrayList<String>> listaCodiciDaFile = LettoreXML.leggiControllaCodiciFiscali();
            ArrayList<String> codiciValidi = listaCodiciDaFile.get(0);
            ArrayList<String> codiciInvalidi = listaCodiciDaFile.get(1);

            xmlw.writeCharacters("\n");
            xmlw.writeStartElement("output");
            xmlw.writeCharacters("\n\t");
            xmlw.writeStartElement("persone");
            xmlw.writeAttribute("numero", Integer.valueOf(listaDellePersone.size()).toString());

            for (int i = 0; i < listaDellePersone.size(); i++) {
                xmlw.writeCharacters("\n\t\t");
                xmlw.writeStartElement("persona");
                xmlw.writeAttribute("id", Integer.valueOf(i).toString());

                    xmlw.writeCharacters("\n\t\t\t");
                    xmlw.writeStartElement("nome");
                    xmlw.writeCharacters(listaDellePersone.get(i).getNome());
                    xmlw.writeEndElement();
                    xmlw.writeCharacters("\n");
                    xmlw.writeCharacters("\t\t\t");
                    xmlw.writeStartElement("cognome");
                    xmlw.writeCharacters(listaDellePersone.get(i).getCognome());
                    xmlw.writeEndElement();
                    xmlw.writeCharacters("\n");
                    xmlw.writeCharacters("\t\t\t");
                    xmlw.writeStartElement("sesso");
                    xmlw.writeCharacters(String.valueOf(listaDellePersone.get(i).getSesso().toString().charAt(0)));
                    xmlw.writeEndElement();
                    xmlw.writeCharacters("\n");
                    xmlw.writeCharacters("\t\t\t");
                    xmlw.writeStartElement("comune");
                    xmlw.writeCharacters(listaDellePersone.get(i).getComuneDiNascita());
                    xmlw.writeEndElement();
                    xmlw.writeCharacters("\n");
                    xmlw.writeCharacters("\t\t\t");
                    xmlw.writeStartElement("data_nascita");
                    xmlw.writeCharacters(listaDellePersone.get(i).getDataDiNascita().get(Calendar.YEAR) + "-" + String.format("%02d", listaDellePersone.get(i).getDataDiNascita().get(Calendar.MONTH)) + "-" + String.format("%02d", listaDellePersone.get(i).getDataDiNascita().get(Calendar.DAY_OF_MONTH)));
                    xmlw.writeEndElement();
                    xmlw.writeCharacters("\n");
                    xmlw.writeCharacters("\t\t\t");
                    xmlw.writeStartElement("codice_fiscale");
                    if (codiciValidi.contains(listaDellePersone.get(i).getCodiceFiscale())) {
                        xmlw.writeCharacters("\n\t\t\t\t" + listaDellePersone.get(i).getCodiceFiscale() + "\n\t\t\t");
                        codiciValidi.remove(listaDellePersone.get(i).getCodiceFiscale());
                    }
                    else {
                        xmlw.writeCharacters("\n\t\t\t\t" + "ASSENTE" + "\n\t\t\t");
                    }
                    xmlw.writeEndElement();
                    xmlw.writeCharacters("\n");
                
                xmlw.writeCharacters("\t\t"); 
                xmlw.writeEndElement();  // chiusura persona
            }
            
            xmlw.writeCharacters("\t");
             
            xmlw.writeEndElement();  // chiusura persone
            //<codici> 
                xmlw.writeCharacters("\n\t");
                xmlw.writeStartElement("codici");
                xmlw.writeStartElement("invalidi");
                xmlw.writeAttribute("numero", Integer.valueOf(codiciInvalidi.size()).toString());
                for (String codice : codiciInvalidi) {
                    xmlw.writeStartElement("codice");
                    xmlw.writeCharacters(codice);
                    xmlw.writeEndElement();
                }
                xmlw.writeEndElement();

                xmlw.writeStartElement("spaiati");
                xmlw.writeAttribute("numero", Integer.valueOf(codiciValidi.size()).toString());
                for (String codice : codiciValidi) {
                    xmlw.writeStartElement("codice");
                    xmlw.writeCharacters(codice);
                    xmlw.writeEndElement();
                } 
                xmlw.writeEndElement();

                xmlw.writeEndElement();  // chiusura codici

            xmlw.writeEndElement();  // chiusura output

            xmlw.writeEndDocument();
            xmlw.flush();
            xmlw.close();

            
        } catch (Exception e) {
            System.out.println("Errore nella generazione del file");
            System.out.println(e.getMessage());
        }

    }
}
