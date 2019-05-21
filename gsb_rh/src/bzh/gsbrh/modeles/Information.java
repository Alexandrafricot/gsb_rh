package bzh.gsbrh.modeles;

import bzh.gsbrh.observateurs.Lexique;

/**
 * <p>
 * Classe permettant de générer une information d'un type définit. Représente
 * une information sur un employé. Simplifie l'ecriture et la lecture des
 * informations.
 * </p>
 * <p>
 * A chaque type est associé une expression régulière(regex) permettant de
 * vérifier automatiquement si la valeur de l'information coresspond a la norme
 * du type qui lui est associé.
 * </p>
 * 
 * @author Alexandra Fricot
 * @version 1.0
 * @since 2.0
 */
public class Information implements Lexique {

	/**
	 * Représente le type auquel appartient l'information.
	 */
	private String type;

	/**
	 * La donnée de l'information.
	 */
	private String valeur;

	/**
	 * Tableau des types auquel peuvent apartenir une information.
	 */
	private static String[] types = { ID, NOM, PRENOM, LOGIN, MDP, ADR, CP, VILLE, MAIL, TEL, DATEE, SERVICE, DATED };

	/**
	 * Tableau des codes liés aux modifications.
	 */
	private static int[] codeModif = { M_ID, M_NOM, M_PRENOM, M_LOGIN, M_MDP, M_ADRESSE, M_CP, M_VILLE, M_MAIL, M_TEL,
			M_DATEE, M_SERVICEID, M_DATED };

	/**
	 * Tableau de regex liés aux types
	 */
	private static String[] regex = { "^[a-zA-Z]{1}[0-9]{1,3}$", "[^0-9][A-Za-z-. '\\p{L}*]{1,30}$",
			"[^0-9][A-Za-z-. '\\p{L}*]{1,30}$", "[A-Za-z0-9]{5,20}", "[A-Za-z0-9]{6,20}", ".{1,30}", "[0-9]{5}",
			"[A-Za-z-. '\\p{L}*]{1,30}", "^[a-z0-9._-]+@[a-z0-9._-]{2,}\\.[a-z]{2,4}$", "^0[1-8][0-9]{8}$",
			"^(19|20)[0-9]{2}+-[0-9]{2}+-[0-9]{2}", "", "^(19|20)[0-9]{2}+-[0-9]{1,2}+-[0-9]{1,2}" };

	/**
	 * Retourne le regex du type recherché
	 * 
	 * @param type
	 *            Type de valeur recherché
	 * @return Regex du type recherché
	 */
	public static String getRegex(String type) {
		String regle = null;
		regle = regex[Information.getByType(type)];
		return regle;
	}

	/**
	 * Recherche l'id du type dans le tableau
	 * 
	 * @param type
	 *            Libellé à rechercher
	 * @return L'index du libellé dans le tableau des types
	 */
	public static int getByType(String type) {
		for (int i = 0; i < types.length; i++) {
			if (type.equals(types[i]))
				return i;
		}
		return 0;
	}

	/**
	 * Constructeur par defaut.
	 */
	public Information() {

	}

	/**
	 * Constructeur surchargé pemettant d'instancier une information complète.
	 * 
	 * @param type
	 *            Type de l'information reçu(champ à laquel elle corespond).
	 * @param valeur
	 *            Valeur de la donnée reçus.
	 */
	public Information(String type, String valeur) {
		this.setType(type);
		if (type.equals(SERVICE) && !valeur.isEmpty()) {
			int id = Integer.parseInt(valeur) - 1;
			valeur = Integer.toString(id);
		}
		if (valeur == null || valeur.equals(null))
			valeur = "";
		this.setValeur(valeur);
	}

	/**
	 * Accesseur de la valeur de l'information.
	 * 
	 * @return La valeur de l'information.
	 */
	public String getValeur() {
		return valeur;
	}

	/**
	 * Accesseur du type de l'information.
	 * 
	 * @return Le type de l'information.
	 */
	public String getType() {
		return type;
	}

	/**
	 * Accesseur du tableau des informations.
	 * 
	 * @return Le tableau contenant la liste des codes des types d'information.
	 */
	public static String[] getTypes() {
		return types;
	}

	/**
	 * Accesseur d'un élément souhaité du tableau des types d'information.
	 * 
	 * @param i
	 *            Indice du code demandé.
	 * @return Le code du type contenu à l'index demandé.
	 */
	public static String getTypes(int i) {
		return types[i];
	}

	/**
	 * Accesseur du tableau des code lié au modification.
	 * 
	 * @return Le tableau contenant la liste des codes des modification(même index
	 *         que le type)
	 */
	public static int[] getCodeModif() {
		return codeModif;
	}

	/**
	 * Accesseur d'un élément souhaité du tableau des code de modification.
	 * 
	 * @param i
	 *            Indice du code demandé.
	 * @return le code de modification demandé.
	 */
	public static int getCodeModif(int i) {
		return codeModif[i];
	}

	/**
	 * Mutateur de la valeur de l'information.
	 * 
	 * @param valeur
	 *            Nouvel valeur de l'information.
	 */
	public void setValeur(String valeur) {
		this.valeur = valeur;
	}

	/**
	 * Mutateur du type de l'information.
	 * 
	 * @param type
	 *            Nouveau type de l'information.
	 */
	public void setType(String type) {
		this.type = type;
	}
}
