import bzh.gsbrh.controleurs.Controleur;

/**
 * Classe d'entrée dans l'application
 * 
 * @author Alexandra Fricot
 * @version 1.0
 */
public class GSBRH {

	/**
	 * Methode d'entrée dans l'application
	 * 
	 * @param args
	 *            Passe un tableau d'argument a l'application
	 */
	public static void main(String[] args) {
		Controleur controleur = new Controleur();
		controleur.lancerAppli();
	}

}
