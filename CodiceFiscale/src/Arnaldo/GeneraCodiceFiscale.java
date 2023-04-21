package Arnaldo;

import java.util.HashMap;
import java.util.Calendar;
//import java.util.GregorianCalendar;

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


/*    public static void main(String[] args) {
        String nome = "Luca";

        Calendar data = new GregorianCalendar(1987, 1, 26);

        System.out.println(calcolaCaratteriNomeCognome(nome));

        System.out.println(calcolaCifreGiorno(data, Sesso.Femmina));
        System.out.println(Calendar.JANUARY);
        System.out.println(calcolaCarattereMese(data));
    } */

    public static String calcolaCaratteriNomeCognome (String nome) {
        StringBuffer codiceNome = new StringBuffer();
        String nomeMaiuscolo = nome.toUpperCase();

        for (char carattere : nomeMaiuscolo.toCharArray()) {
            if (String.valueOf(carattere).matches("[^AEIOU ]")) {
                codiceNome.append(carattere);

                if (codiceNome.length() >= 3) {
                    break;
                }
            }
        }
       
        if (codiceNome.length() < 3) {

            for (char carattere : nomeMaiuscolo.toCharArray()) {
                if (String.valueOf(carattere).matches("[AEIOU]")) {
                    codiceNome.append(carattere);
    
                    if (codiceNome.length() >= 3) {
                        break;
                    }
                }
            }
        }

        if (codiceNome.length() < 3) {
            while (codiceNome.length() < 3) {
                codiceNome.append("X");
            }
        }

        return codiceNome.toString();
    }

    public static String calcolaCifreAnno (Calendar data) {
        int anno = data.get(Calendar.YEAR);
        return Integer.toString(anno).substring(2, 4);
    }
    
    public static String calcolaCarattereMese (Calendar data) {
        int numeroMese = data.get(Calendar.MONTH); 
        Mese mese = Mese.values()[numeroMese];

        return LETTERE_MESI.get(mese).toString();
    }

    public static String calcolaCifreGiorno (Calendar data, Sesso sesso) {
        int giorno = data.get(Calendar.DAY_OF_MONTH);

        if (sesso.equals(Sesso.Femmina)) {
            giorno += INCREMENTO_MESE_DONNA;
        }
        
        return Integer.toString(giorno);
    }

}
