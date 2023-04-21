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

}
