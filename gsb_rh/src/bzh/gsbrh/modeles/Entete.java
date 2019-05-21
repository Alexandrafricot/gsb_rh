package bzh.gsbrh.modeles;

import bzh.gsbrh.fabriques.FactBouton;
import bzh.gsbrh.observateurs.Lexique;
import bzh.gsbrh.vues.Bouton;

/**
 * Class définissant l'entête du tableau de la liste des employés
 * 
 * @author Alexandra Fricot
 * @version 1.0
 */
public class Entete implements Lexique {

	/**
	 * Tableau des libellés de l'entête du tableau
	 */
	private String entete[] = { ID, NOM, PRENOM, LOGIN, ADR, CP, VILLE, MAIL, TEL, DATEE, SERVICE, DATED, COLMODIFIE,
			PP_DATE_TX };

	/**
	 * Constructeur de l'entête
	 */
	public Entete() {

	}

	/**
	 * Retourne l'indice dans le tableau d'entête du libellé recherché
	 * 
	 * @param lib
	 *            Libellé recherché
	 * @return L'indice de libellé recherché
	 */
	public int indiceDEntete(String lib) {
		int retour = 0;
		for (int i = 0; i < entete.length; i++)
			if (entete[i].equals(lib))
				return i;
		return retour;
	}

	/**
	 * Vérifie que le libellé demandé est à l'indice demandé
	 * 
	 * @param lib
	 *            Libellé recherché
	 * @param indice
	 *            Indice ou trouver le libellé
	 * @return Bouléen qui confirme ou non que le libellé est bien à l'indice
	 *         demandé
	 */
	public boolean estALIndice(String lib, int indice) {
		for (int i = 0; i < entete.length; i++)
			if (entete[i].equals(lib) && i == indice)
				return true;
		return false;
	}

	/**
	 * Accesseur du tableau des libellés d'entêtes
	 * 
	 * @return Le tableau des libellés d'entête
	 */
	public String[] getEntete() {
		return entete;
	}

	/**
	 * Mutateur du tableau des libellés d'entêtes
	 * 
	 * @param entete
	 *            Nouveau tableau de libellé des entête
	 */
	public void setEntete(String[] entete) {
		this.entete = entete;
	}

	/**
	 * Ajoute des boutons aux colonnes prévu dans la liste passé en paramètre
	 * 
	 * @param liste
	 *            Liste a laquel ajouter des boutons
	 */
	public void ajouterElements(Object[][] liste) {
		int row = liste.length;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < this.entete.length; j++) {
				if (liste[i][j] == null)
					liste[i][j] = "";
				if (entete[j].equals(PP_DATE_TX)) {
					Bouton supprimer = FactBouton.fabriqueBouton(null, BO_SUPPR, Images.PROGRAM.getIcon());
					liste[i][j] = supprimer;
				}
				if (entete[j].equals(COLMODIFIE)) {
					Bouton modifier = FactBouton.fabriqueBouton(null, BO_MODIF, Images.MODIFIE.getIcon());
					modifier.setToolTipText(BOUTON_MODIF + " " + liste[i][this.indiceDEntete(PRENOM)] + " "
							+ liste[i][this.indiceDEntete(NOM)]);
					liste[i][j] = modifier;
				}
			}
		}
	}

	/**
	 * Recherche si un libellé est dans le tableau des libellés
	 * 
	 * @param lib
	 *            Libellé recherché
	 * @return Bouléen qui confirme ou non que le libellé est dans le tableau des
	 *         libellés
	 */
	public boolean estDansEntete(String lib) {
		for (int i = 0; i < this.entete.length; i++)
			if (this.entete[i].equals(lib))
				return true;
		return false;
	}
}
