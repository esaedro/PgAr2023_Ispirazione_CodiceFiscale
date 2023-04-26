package Arnaldo;

public class ControllaCodiceFiscale {
    public static final int N_CARATTERI_NOME = 3;
    public static final int N_CARATTERI_COGNOME = 3;
    public static final int N_CIFRE_ANNO_NASCITA = 2;
    public static final int N_CIFRE_MESE_NASCITA = 2;
    public static final int N_CARATTERE_MESE_NASCITA = 1;
    public static final int N_CARATTERI_CODICE_COMUNE_NASCITA = 4;
    public static final int N_CARATTERE_DI_CONTROLLO = 1;

    public static int getnCaratteriNome() {
        return N_CARATTERI_NOME;
    }

    public static int getnCaratteriCognome() {
        return N_CARATTERI_COGNOME;
    }

    public static int getnCifreAnnoNascita() {
        return N_CIFRE_ANNO_NASCITA;
    }

    public static int getnCifreMeseNascita() {
        return N_CIFRE_MESE_NASCITA;
    }

    public static int getnCarattereMeseNascita() {
        return N_CARATTERE_MESE_NASCITA;
    }

    public static int getnCaratteriCodiceComuneNascita() {
        return N_CARATTERI_CODICE_COMUNE_NASCITA;
    }

    public static int getnCarattereDiControllo() {
        return N_CARATTERE_DI_CONTROLLO;
    }

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

    public static boolean controllaLunghezza(String codice) {
        return codice.length() == 16;
    }

    public static boolean controllaCaratteriNome(String codice) {
        for (char carattere : codice.substring(0, 3).toCharArray()) {
            if (String.valueOf(carattere).matches("[^A-Z]")) {
                return false;
            }
        }

        return true;
    }

    public static boolean controllaCaratteriCognome(String codice) {
        for (char carattere : codice.substring(3, 6).toCharArray()) {
            if (String.valueOf(carattere).matches("[^A-Z]")) {
                return false;
            }
        }

        return true;
    }

    public static boolean controllaCifreAnno(String codice) {
        try {
            int anno = Integer.valueOf(codice.substring(6, 8));
            return anno >= 0 && anno <= 99;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean controllaCarattereMese(String codice) {
        return GeneraCodiceFiscale.LETTERE_MESI.containsValue(codice.charAt(8));
    }

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
                return giorno >= 1 && giorno <= 30;

            case 'B':
                if ((anno % 4 == 0 && anno % 100 != 0) || (anno % 400 == 0)) {
                    return giorno >= 1 && giorno <= 29;
                } else {
                    return giorno >= 1 && giorno <= 28;
                }

            default:
                return giorno >= 1 && giorno <= 31;
        }
    }

    public static boolean controllaCodiceComune(String codice) {
        return LettoreXML.controllaPresenzaComune(codice.substring(11, 15));      
    }

    public static boolean controllaCarattereDiControllo(String codice) {
        return codice.charAt(15) == GeneraCodiceFiscale.calcolaCarattereDiControllo(codice.substring(0, 15)).charAt(0);
    }

}
