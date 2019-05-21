package bzh.gsbrh.modeles;

import java.util.Random;

/**
 * Classe permettant de générer des mot de passe aléatoire.
 * 
 * @author Alexandra Fricot
 * @version 1.0
 */
public class GenerateurMDP {

	/**
	 * Objet random permettant de générer des nombres aléatoire
	 */
	private static final Random rand = new Random();

	/**
	 * Chaine de caractère représentant les minuscules de l'alphabet.
	 */
	private static final String min = "abcdefghijklmnopqrstuvwxyz";

	/**
	 * Chaine de caractère représentant les majuscule de l'alphabet.
	 */
	private static final String maj = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	/**
	 * Chaine de caractère représentant les 10 chriffres.
	 */
	private static final String num = "1234567890";

	/**
	 * Chaine de caractère représentant les symboles spéciaux.
	 */
	private static final String sym = "&~#|`-_)('/?,;:.";

	/**
	 * Entier représentant le nombre de caractère du mot de passe à générer.
	 */
	private static int taille;

	/**
	 * Constructeur par defaut, mis en privé pour ne pas l'instancier.
	 */
	private GenerateurMDP() {
	}

	/**
	 * Génère un mot de passe en fonction des paramètres reçus.
	 * 
	 * @param debut
	 *            Taille minimum du mot de passe à générer.
	 * @param fin
	 *            Taille maximum du mot de passe à générer.
	 * @param spe
	 *            Booléen qui confirme ou non l'utilisation des caractères spéciaux.
	 * @return Un mot de passe généré aléatoirement.
	 */
	public static String generer(int debut, int fin, boolean spe) {
		String pass = "";
		while ((taille = rand.nextInt(fin)) < debut)
			;
		for (int i = 0; i < taille; i++) {
			int type = rand.nextInt(20);
			if (type < 3 && spe) {
				pass += sym.charAt(rand.nextInt(15));
			} else if (type < 6) {
				pass += num.charAt(rand.nextInt(10));
			} else if (type < 10) {
				pass += maj.charAt(rand.nextInt(15));
			} else {
				pass += min.charAt(rand.nextInt(15));
			}
		}
		return pass;
	}
}
