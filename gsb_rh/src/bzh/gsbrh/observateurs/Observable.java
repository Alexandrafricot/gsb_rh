package bzh.gsbrh.observateurs;

import bzh.gsbrh.modeles.Employe;
import bzh.gsbrh.vues.Champ;

/**
 * <p>
 * Une classe peut implémenter cette interface pour représenter un objet qu'une
 * autre classe de l'application souhaite observer.
 * </p>
 * <p>
 * Un objet observable peut avoir un ou plusieurs observateurs, il est
 * nécéssaire alors de lui fournir une ArrayList&lt;Observateur&gt;. Un
 * observateur peut être n'importe qu'elle objet implémentant l'interface
 * Observateur. Après la modification d'une instance Observable, une application
 * appelant la méthode notifierObservateur de l'observable fait en sorte que
 * tous ses observateurs soient avertis de la modification par un appel à leur
 * méthode actualiser.
 * </p>
 * 
 * @author Alexandra Fricot
 * @version 1.0
 * @see Observateur
 */
public interface Observable {

	/**
	 * Methode permettant d'ajouter un observateur
	 * 
	 * @param o
	 *            Observateur qui observe l'observable
	 */
	public void ajouterObservateur(Observateur o);

	/**
	 * Methode permettant de supprimer un observateur
	 * 
	 * @param o
	 *            Observateur à supprimer
	 */
	public void supprimerObservateur(Observateur o);

	/**
	 * Methode permettant d'avertir tous les observateur d'un changement d'état
	 */
	public void notifierObservateur();

	/**
	 * Methode permettant d'avertir tous les observateur d'un changement d'état
	 * 
	 * @param id
	 *            Identifiant de la notification
	 */
	public void notifierObservateur(int id);

	/**
	 * Methode permettant d'avertir tous les observateur d'un changement d'état
	 * 
	 * @param valeur
	 *            Chaine de caréctère à notifier par le changement d'état
	 */
	public void notifierObservateur(String valeur);

	/**
	 * Methode permettant d'avertir tous les observateur d'un changement d'état
	 * 
	 * @param valeur
	 *            Chaine de caréctère à notifier par le changement d'état
	 * @param code
	 *            Identifiant de la notification
	 */
	public void notifierObservateur(String valeur, int code);

	/**
	 * Methode permettant d'avertir tous les observateur d'un changement d'état
	 * 
	 * @param code
	 *            Identifiant de la notification
	 * @param champs
	 *            Liste des champs à traiter par l'observateur
	 */
	public void notifierObservateur(int code, Champ[] champs);

	/**
	 * Methode permettant d'avertir tous les observateur d'un changement d'état
	 * 
	 * @param code
	 *            Identifiant de la notification
	 * @param champs
	 *            Champ à traiter par l'observateur
	 */
	public void notifierObservateur(int code, Champ champs);

	/**
	 * Methode permettant d'avertir tous les observateur d'un changement d'état
	 * 
	 * @param code
	 *            Identifiant de la notification
	 * @param employe
	 *            Employé à traiter par l'observateur
	 */
	public void notifierObservateur(int code, Employe employe);

	/**
	 * Methode permettant d'avertir tous les observateur d'un changement d'état
	 * 
	 * @param code
	 *            Identifiant de la notification
	 * @param employe
	 *            Employé à traiter par l'observateur
	 * @param champs
	 *            Liste des champs à traiter par l'observateur
	 */
	public void notifierObservateur(int code, Employe employe, Champ[] champs);
}
