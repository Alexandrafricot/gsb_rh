package bzh.gsbrh.fabriques;

import bzh.gsbrh.modeles.Employe;
import bzh.gsbrh.modeles.Entete;
import bzh.gsbrh.observateurs.Lexique;
import bzh.gsbrh.observateurs.Observateur;
import bzh.gsbrh.vues.FenFormulaire;
import bzh.gsbrh.vues.FenIdentification;
import bzh.gsbrh.vues.FenListeEmployes;

/**
 * Classe spécialiser dans la fabrique des fenêtres.
 * 
 * @author Alexandra Fricot
 * @version 1.0
 */
public class FactFenetre implements Lexique {

	/**
	 * Méthode permettant de fabrique une fenêtre de liste des employés.
	 * 
	 * @param o
	 *            Observateur par défaut de la fenêtre.
	 * @param code
	 *            Code de la fenêtre à fabriquer.
	 * @param listeA
	 *            Liste des employés actifs (dans le cas de la fenêtre liste des
	 *            employés).
	 * @param listeI
	 *            Liste des employés inactifs (dans le cas de la fenêtre liste des
	 *            employés).
	 * @param entete
	 *            Entête des tableaux des listes d'employé (dans le cas de la
	 *            fenêtre liste des employés).
	 * @return La fenêtre liste des employés.
	 * 
	 * @see bzh.gsbrh.observateurs.Lexique
	 */
	public static FenListeEmployes fabriqueFenetre(Observateur o, int code, Object[][] listeA,
			Object[][] listeI, Entete entete) {
		switch (code) {
		case FE_LISTE:
			return FenListeEmployes.creerFenetre(FE_TITRE_LISTE, o, listeA, listeI, entete);
		
		default:
			return null;
		}

	}
	
	/**
	 * Méthode permettant de fabrique une fenêtre de formulaire.
	 * 
	 * @param o
	 *            Observateur par défaut de la fenêtre.
	 * @param code
	 *            Code de la fenêtre à fabriquer.
	 * @param employe
	 *            Employé sur lequel va travailler la fenêtre.
	 * @return La fenêtre de formulaire.
	 * 
	 * @see bzh.gsbrh.observateurs.Lexique
	 */
	public static FenFormulaire fabriqueFenetre(Observateur o, int code, Employe employe) {
		switch (code) {
		case FE_MODIF:
			return FenFormulaire.creerFenetre(FE_TITRE_MODIF, o, employe);
		case FE_AJOUT:
			return FenFormulaire.creerFenetre(FE_TITRE_AJOUT, o, employe);
		default:
			return null;
		}

	}
	
	/**
	 * Méthode permettant de fabrique une fenêtre d'identification.
	 * 
	 * @param o
	 *            Observateur par défaut de la fenêtre.
	 * @param code
	 *            Code de la fenêtre à fabriquer.
	 * @return La fenêtre d'identification.
	 * 
	 * @see bzh.gsbrh.observateurs.Lexique
	 */
	public static FenIdentification fabriqueFenetre(Observateur o, int code) {
		switch (code) {
		case FE_IDENT:
			return FenIdentification.creerFenetre(FE_TITRE_CONNE, o);
		default:
			return null;
		}

	}
}
