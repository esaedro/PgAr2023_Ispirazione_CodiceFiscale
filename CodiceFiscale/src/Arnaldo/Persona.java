package Arnaldo;

import java.util.Calendar;

import javax.xml.stream.XMLStreamException;

public class Persona {
    private String nome;
    private String cognome;
    private Sesso sesso;
    private Calendar dataDiNascita;
    private String comuneDiNascita;
    private String codiceFiscale;

    public Persona(String nome, String cognome, Sesso sesso, Calendar dataDiNascita, String comuneDiNascita) throws XMLStreamException {
        this.nome = nome;
        this.cognome = cognome;
        this.sesso = sesso;
        this.dataDiNascita = dataDiNascita;
        this.comuneDiNascita = comuneDiNascita;
        this.codiceFiscale = calcolaCodiceFiscale();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public Sesso getSesso() {
        return sesso;
    }

    public void setSesso(Sesso sesso) {
        this.sesso = sesso;
    }

    public Calendar getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(Calendar dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public String getComuneDiNascita() {
        return comuneDiNascita;
    }

    public void setComuneDiNascita(String comuneDiNascita) {
        this.comuneDiNascita = comuneDiNascita;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }


    /**
     * Genera l'intero codice da 16 caratteri generando le parti necessarie e unendole
     */
    public String calcolaCodiceFiscale() throws XMLStreamException {
        StringBuffer codiceFiscale = new StringBuffer();
        
        codiceFiscale.append(GeneraCodiceFiscale.calcolaCaratteriNomeCognome(nome));
        codiceFiscale.append(GeneraCodiceFiscale.calcolaCaratteriNomeCognome(cognome));
        codiceFiscale.append(GeneraCodiceFiscale.calcolaCifreAnno(dataDiNascita));
        codiceFiscale.append(GeneraCodiceFiscale.calcolaCarattereMese(dataDiNascita));
        codiceFiscale.append(GeneraCodiceFiscale.calcolaCifreGiorno(dataDiNascita, sesso));
        codiceFiscale.append(GeneraCodiceFiscale.trovaCodiceComune(comuneDiNascita));
        codiceFiscale.append(GeneraCodiceFiscale.calcolaCarattereDiControllo(codiceFiscale.toString()));

        return codiceFiscale.toString();
    }

}
