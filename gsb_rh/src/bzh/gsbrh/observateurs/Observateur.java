package bzh.gsbrh.observateurs;

import bzh.gsbrh.modeles.Employe;
import bzh.gsbrh.vues.Champ;

/**
 * <p>
 * Interface permettant de capter les changements d'état des Observables. La
 * classe qui s'intéresse aux changements d'un de ses objets implémente cette
 * interface et créer cette objet implémentant l'interface observable puis
 * l'observable doit ajouter son ou ses observateur(s) dans sa liste
 * d'observateurs.
 * </p>
 * 
 * <p>
 * Lorsqu'une action est produite par un Observable celui ci doit appeller la
 * méthode actualiser de ses Observateurs.
 * </p>
 * 
 * @author Alexandra Fricot
 * @version 1.0
 * @see Observable
 */
public interface Observateur {

	/**
	 * Methode appelé lors d'un changement d'état d'un orbservable
	 * 
	 * @param o
	 *            Observable qui notifie d'un changement
	 */
	public void actualiser(Observable o);

	/**
	 * Methode appelé lors d'un changement d'état d'un orbservable
	 * 
	 * @param o
	 *            Observable qui notifie d'un changement
	 * @param id
	 *            Identifiant de la notification
	 */
	public void actualiser(Observable o, int id);

	/**
	 * Methode appelé lors d'un changement d'état d'un orbservable
	 * 
	 * @param o
	 *            Observable qui notifie d'un changement
	 * @param valeur
	 *            Chaine de caréctère notifier par le changement d'état
	 */
	public void actualiser(Observable o, String valeur);

	/**
	 * Methode appelé lors d'un changement d'état d'un orbservable
	 * 
	 * @param o
	 *            Observable qui notifie d'un changement
	 * @param valeur
	 *            Chaine de caréctère notifier par le changement d'état
	 * @param code
	 *            Identifiant de la notification
	 */
	public void actualiser(Observable o, String valeur, int code);

	/**
	 * Methode appelé lors d'un changement d'état d'un orbservable
	 * 
	 * @param o
	 *            Observable qui notifie d'un changement
	 * @param code
	 *            Identifiant de la notification
	 * @param champs
	 *            Liste des champs à traiter
	 */
	public void actualiser(Observable o, int code, Champ[] champs);

	/**
	 * Methode appelé lors d'un changement d'état d'un orbservable
	 * 
	 * @param o
	 *            Observable qui notifie d'un changement
	 * @param code
	 *            Identifiant de la notification
	 * @param employe
	 *            Employé à traiter
	 * @param champs
	 *            Liste des champs à traiter
	 */
	public void actualiser(Observable o, int code, Employe employe, Champ[] champs);

	/**
	 * Methode appelé lors d'un changement d'état d'un orbservable
	 * 
	 * @param o
	 *            Observable qui notifie d'un changement
	 * @param code
	 *            Identifiant de la notification
	 * @param champ
	 *            Champ à traiter
	 */
	public void actualiser(Observable o, int code, Champ champ);

	/**
	 * Methode appelé lors d'un changement d'état d'un orbservable
	 * 
	 * @param o
	 *            Observable qui notifie d'un changement
	 * @param code
	 *            Identifiant de la notification
	 * @param employe
	 *            Employé à traiter
	 */
	public void actualiser(Observable o, int code, Employe employe);

}
