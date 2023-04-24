package Arnaldo;

import java.util.HashMap;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Classe non istanziabile usata per generare il codice fiscale usando i dati conosciuti della persona
 */
public class GeneraCodiceFiscale {
    public static final int LUNGHEZZA = 16;
    public static final int INCREMENTO_MESE_DONNA = 40;
    final static HashMap<Character, Integer> CONVERSIONE_CARATTERI_DISPARI = new HashMap<>() {{
        put('0', 1);
        put('1', 0);
        put('2', 5);
        put('3', 7);
        put('4', 9);
        put('5', 13);
        put('6', 15);
        put('7', 17);
        put('8', 19);
        put('9', 21);
        put('A', 1);
        put('B', 0);
        put('C', 5);
        put('D', 7);
        put('E', 9);
        put('F', 13);
        put('G', 15);
        put('H', 17);
        put('I', 19);
        put('J', 21);
        put('K', 2);
        put('L', 4);
        put('M', 18);
        put('N', 20);
        put('O', 11);
        put('P', 3);
        put('Q', 6);
        put('R', 8);
        put('S', 12);
        put('T', 14);
        put('U', 16);
        put('V', 10);
        put('W', 22);
        put('X', 25);
        put('Y', 24);
        put('Z', 23);
    }}; 
    final static HashMap<Character, Integer> CONVERSIONE_CARATTERI_PARI = new HashMap<>() {{
        put('0', 0);
        put('1', 1);
        put('2', 2);
        put('3', 3);
        put('4', 4);
        put('5', 5);
        put('6', 6);
        put('7', 7);
        put('8', 8);
        put('9', 9);
        put('A', 0);
        put('B', 1);
        put('C', 2);
        put('D', 3);
        put('E', 4);
        put('F', 5);
        put('G', 6);
        put('H', 7);
        put('I', 8);
        put('J', 9);
        put('K', 10);
        put('L', 11);
        put('M', 12);
        put('N', 13);
        put('O', 14);
        put('P', 15);
        put('Q', 16);
        put('R', 17);
        put('S', 18);
        put('T', 19);
        put('U', 20);
        put('V', 21);
        put('W', 22);
        put('X', 23);
        put('Y', 24);
        put('Z', 25);
    }};
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

    public static HashMap<Character, Integer> getCONVERSIONE_CARATTERI_DISPARI() {
        return CONVERSIONE_CARATTERI_DISPARI;
    }

    public static HashMap<Character, Integer> getCONVERSIONE_CARATTERI_PARI() {
        return CONVERSIONE_CARATTERI_PARI;
    }

    public HashMap<Mese, Character> getLETTERE_MESI() {
        return LETTERE_MESI;
    }

    public HashMap<Mese, Integer> getGIORNI_MESI() {
        return GIORNI_MESI;
    }


/*    public static void main(String[] args) {
        String nome = "Luca";
    
        Calendar data = new GregorianCalendar(1987, 12, 26);
    
        System.out.println(calcolaCaratteriNomeCognome(nome));

        System.out.println(calcolaCifreGiorno(data, Sesso.Femmina));

        System.out.println(calcolaCarattereMese(data));

    } */


    /**
     * Genera e restituisce i tre caratteri corrispondenti a nome/cognome nel codice fiscale
     * @param nome
     * @return
     */
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


    /**
     * Restituisce le ultime due cifre dell'anno di nascita da inserire nel codice fiscale
     * @param data 
     * @return
     */
    public static String calcolaCifreAnno (Calendar data) {
        int anno = data.get(Calendar.YEAR);
        return Integer.toString(anno).substring(2, 4);
    }
    
    /**
     * Restituisce il carattere corrispondente al mese di nascita da inserire nel codice fiscale
     * @param data
     * @return
     */
    public static String calcolaCarattereMese (Calendar data) {
        int numeroMese = data.get(Calendar.MONTH); 
        Mese mese = Mese.values()[numeroMese];

        return LETTERE_MESI.get(mese).toString();
    }

    /**
     * Restituisce le due cifre corrispondenti al giorno di nascita, considerato il sesso della persona, da inserire nel codice fiscale
     * @param data
     * @param sesso
     * @return
     */
    public static String calcolaCifreGiorno (Calendar data, Sesso sesso) {
        int giorno = data.get(Calendar.DAY_OF_MONTH);

        if (sesso.equals(Sesso.Femmina)) {
            giorno += INCREMENTO_MESE_DONNA;
        }
        
        return Integer.toString(giorno);
    }

    public static String calcolaCarattereDiControllo(String restoDelCodice){
        char carattereDiControllo;
        int somma = 0;

        for (int i = 0; i < restoDelCodice.length(); i += 2) {
            somma += CONVERSIONE_CARATTERI_PARI.get(restoDelCodice.charAt(i));
        }

        for (int i = 1; i < restoDelCodice.length(); i += 2) {
            somma += CONVERSIONE_CARATTERI_DISPARI.get(restoDelCodice.charAt(i));
        }

        carattereDiControllo = (char)('A' + (somma % 26));        

        return String.valueOf(carattereDiControllo);
    }

}
