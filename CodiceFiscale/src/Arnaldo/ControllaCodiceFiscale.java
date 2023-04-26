package Arnaldo;

/**
 * Classe non istanziabile che controlla la validità di un codice fiscale intero e nelle sue parti
 */
public class ControllaCodiceFiscale {

    private static final int LUNGHEZZA_CODICE = 16;

    /**
     * Controlla un codice fiscale nella sua interezza restituendo vero se il codice è valido, falso altrimenti
     * @param codice
     * @return boolean
     */
    public static boolean controllaCodiceIntero(String codice) {
        return  controllaLunghezza(codice)          &&
                controllaCaratteriNome(codice)      &&
                controllaCaratteriCognome(codice)   &&
                controllaCifreAnno(codice)          &&
                controllaCarattereMese(codice)      &&
                controllaCifreGiorno(codice)        &&
                controllaCodiceComune(codice)       &&
                controllaCarattereDiControllo(codice);
    }

    /**
     * Controlla se la lungezza di un codice fiscale è corretta
     * @param codice
     * @return boolean
     */
    public static boolean controllaLunghezza(String codice) {
        return codice.length() == LUNGHEZZA_CODICE;
    }

    /**
     * Controlla se i caratteri relativi al nome sono corretti
     * @param codice
     * @return boolean
     */
    public static boolean controllaCaratteriNome(String codice) {
        boolean primaVocale = false;
        boolean primaConsonante = false;

        for (char carattere : codice.substring(3, 6).toCharArray()) {
            if (String.valueOf(carattere).matches("[^A-Z]")) {
                return false;
            }
            else if (primaConsonante == false && String.valueOf(carattere).matches("[AEIOU]")) {
                primaVocale = true;
            }
            else if (primaVocale == false && String.valueOf(carattere).matches("[^AEIOU]")) {
                primaConsonante = true;
            }
            else if (primaVocale == true && String.valueOf(carattere).matches("[^AEIOUX]")) {
                return false;
            }
        }

        return true;
    }

    /**
     * Controlla se i caratteri relativi al cognome sono corretti
     * @param codice
     * @return boolean
     */
    public static boolean controllaCaratteriCognome(String codice) {
        boolean primaVocale = false;
        boolean primaConsonante = false;

        for (char carattere : codice.substring(0, 3).toCharArray()) {
            if (String.valueOf(carattere).matches("[^A-Z]")) {
                return false;
            }
            else if (primaConsonante == false && String.valueOf(carattere).matches("[AEIOU]")) {
                primaVocale = true;
            }
            else if (primaVocale == false && String.valueOf(carattere).matches("[^AEIOU]")) {
                primaConsonante = true;
            }
            else if (primaVocale == true && String.valueOf(carattere).matches("[^AEIOUX]")) {
                return false;
            }
        }

        return true;
    }

    /**
     * Controlla se le cifre relative all'anno sono corrette
     * @param codice
     * @return boolean
     */
    public static boolean controllaCifreAnno(String codice) {
        try {
            int anno = Integer.valueOf(codice.substring(6, 8));
            return anno >= 0 && anno <= 99;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Controlla se il carattere relativo al mese è corretto
     * @param codice
     * @return boolean
     */
    public static boolean controllaCarattereMese(String codice) {
        return GeneraCodiceFiscale.LETTERE_MESI.containsValue(codice.charAt(8));
    }

    /**
     * Controlla se le cifre relative al giorno sono corrette
     * @param codice
     * @return boolean
     */
    public static boolean controllaCifreGiorno(String codice) {
        int anno = 0;
        char mese = '\0';
        int giorno = 0;

        try {
            anno = Integer.valueOf(codice.substring(6, 8));
            mese = codice.charAt(8);
            giorno = Integer.valueOf(codice.substring(9, 11));
        } catch (Exception e) {
            return false;
        }

        switch (mese) {
            case 'S':
            case 'D':
            case 'H':
            case 'P':
                return (giorno >= 1 && giorno <= 30) || (giorno >= 41 && giorno <= 70);

            case 'B':
                if ((anno % 4 == 0 && anno % 100 != 0) || (anno % 400 == 0)) {
                    return (giorno >= 1 && giorno <= 29) || (giorno >= 41 && giorno <= 69);
                } else {
                    return (giorno >= 1 && giorno <= 28) || (giorno >= 41 && giorno <= 68);
                }

            default:
                return (giorno >= 1 && giorno <= 31) || (giorno >= 41 && giorno <= 71);
        }
    }

    /**
     * Controlla se il codice del comune esiste nella lista dei comuni
     * @param codice
     * @return boolean
     */
    public static boolean controllaCodiceComune(String codice) {
        return LettoreXML.controllaPresenzaComune(codice.substring(11, 15));      
    }

    /**
     * Controlla se il carattere di controllo è corretto
     * @param codice
     * @return boolean
     */
    public static boolean controllaCarattereDiControllo(String codice) {
        return codice.charAt(15) == GeneraCodiceFiscale.calcolaCarattereDiControllo(codice.substring(0, 15)).charAt(0);
    }

}
