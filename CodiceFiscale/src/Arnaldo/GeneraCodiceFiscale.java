package Arnaldo;

import java.util.HashMap;
import java.util.Calendar;

public class GeneraCodiceFiscale {
    public static final int LUNGHEZZA = 16;
    public static final int INCREMENTO_MESE_DONNA = 40;
    final static HashMap <Character, Integer> TABELLA_CARATTERE_CONTROLLO = new HashMap<>(); 
    final static HashMap<Mese, Character> LETTERE_MESI = new HashMap<>() {{
        put(Mese.GENNAIO, 'A');
        put(Mese.FEBBRAIO, 'B');
        put(Mese.MARZO, 'C');
        put(Mese.APRILE, 'D');
        put(Mese.MAGGIO, 'E');
        put(Mese.GIUGNO, 'H');
        put(Mese.LUGLIO, 'L');
        put(Mese.AGOSTO, 'M');
        put(Mese.SETTEMBRE, 'P');
        put(Mese.OTTOBRE, 'R');
        put(Mese.NOVEMBRE, 'S');
        put(Mese.DICEMBRE, 'T');
    }}; 

    final static HashMap <Mese, Integer> GIORNI_MESI = new HashMap<>() {{
        put(Mese.GENNAIO, 31);
        put(Mese.FEBBRAIO, 28);
        put(Mese.MARZO, 31);
        put(Mese.APRILE, 30);
        put(Mese.MAGGIO, 31);
        put(Mese.GIUGNO, 30);
        put(Mese.LUGLIO, 31);
        put(Mese.AGOSTO, 31);
        put(Mese.SETTEMBRE, 30);
        put(Mese.OTTOBRE, 31);
        put(Mese.NOVEMBRE, 30);
        put(Mese.DICEMBRE, 31);

    }};

    public static int getLunghezza() {
        return LUNGHEZZA;
    }

    public static int getIncrementoMeseDonna() {
        return INCREMENTO_MESE_DONNA;
    }

    public HashMap<Character, Integer> getTABELLA_CARATTERE_CONTROLLO() {
        return TABELLA_CARATTERE_CONTROLLO;
    }

    public HashMap<Mese, Character> getLETTERE_MESI() {
        return LETTERE_MESI;
    }

    public HashMap<Mese, Integer> getGIORNI_MESI() {
        return GIORNI_MESI;
    }

/*     public String calcolaCaratteriNomeCognome (String nome) {
        StringBuffer codiceNome = new StringBuffer();
        
        for (char carattere : nome.toCharArray()) {
            
        }
    } */

    /* 
    public String calcolaCaratteriCognome (String cognome) {
        StringBuffer codiceCognome = new StringBuffer();


        return codiceCognome.toString().toUpperCase();
    } */





    public String calcolaCifreAnno (Calendar data) {
        int anno = data.get(Calendar.YEAR);
        return Integer.toString(anno).substring(2, 4);
    }
    
    public String calcolaCarattereMese (Calendar data) {
        int numeroMese = data.get(Calendar.MONTH) - 1; 
        Mese mese = Mese.values()[numeroMese];

        return LETTERE_MESI.get(mese).toString();
    }

    public String calcolaCifreGiorno (Calendar data, Sesso sesso) {
        int giorno = data.get(Calendar.DAY_OF_MONTH);

        if (sesso.equals(Sesso.Femmina)) {
            giorno += INCREMENTO_MESE_DONNA;
        }
        
        return Integer.toString(giorno);
    }

}
