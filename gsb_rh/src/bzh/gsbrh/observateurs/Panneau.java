package bzh.gsbrh.observateurs;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.SpringLayout;

import bzh.gsbrh.modeles.Employe;
import bzh.gsbrh.vues.Champ;

/**
 * <p>
 * Panneau est une classe abstraite dérivé de JPanel. Elle permet de manipuler
 * et placer des composant tel des zones de texte, des labels ou des boutons.
 * </p>
 * <p>
 * Implémente Observateur afin d'être notifier des actualisations de ses
 * composants Observable
 * </p>
 * <p>
 * Implémente Observateur afin de notifier ses Observateurs des actualisations
 * de ses Observables.
 * </p>
 * 
 * @author Alexandra Fricot
 * @version 1.0
 */
public abstract class Panneau extends JPanel implements Observateur, Observable, Lexique {

	/**
	 * Clé de hachage de la classe.
	 */
	private static final long serialVersionUID = -1655550505452976814L;

	/**
	 * Collection des Observateurs
	 */
	public ArrayList<Observateur> tabObservateur = new ArrayList<Observateur>();

	/**
	 * Constructeur surchargé.
	 * 
	 * @param o
	 *            Observateur par défaut.
	 * @param layout
	 *            Gestionnaire de mise en forme sur lequel sera disposé le panneau.
	 */
	public Panneau(Observateur o, SpringLayout layout) {
		super(layout);
		ajouterObservateur(o);
	}

	/**
	 * Constructeur surchargé.
	 * 
	 * @param layout
	 *            Gestionnaire de mise en forme sur lequel sera disposé le panneau.
	 */
	public Panneau(GridLayout layout) {
		super(layout);
	}

	/**
	 * Constructeur surchargé.
	 * 
	 * @param o
	 *            Observateur par défaut.
	 * @param layout
	 *            Gestionnaire de mise en forme sur lequel sera disposé le panneau.
	 */
	public Panneau(Observateur o, GridLayout layout) {
		super(layout);
		ajouterObservateur(o);
	}

	public void ajouterObservateur(Observateur o) {
		tabObservateur.add(o);
	}

	public void supprimerObservateur(Observateur o) {
		tabObservateur.remove(o);
	}

	public void actualiser(Observable o) {
		notifierObservateur();
	}

	public void actualiser(Observable o, String valeur) {
		notifierObservateur(valeur);
	}

	public void actualiser(Observable o, String valeur, int code) {
		notifierObservateur(valeur, code);
	}

	public void actualiser(Observable o, int id) {
		notifierObservateur(id);
	}

	public void actualiser(Observable o, int code, Champ[] champs) {
		notifierObservateur(code, champs);
	}

	public void actualiser(Observable o, int code, Employe employe) {
		notifierObservateur(code, employe);
	}

	public void actualiser(Observable o, int code, Champ champs) {
	}

	public void actualiser(Observable o, int code, Employe employe, Champ[] champs) {
	}

	public void notifierObservateur() {
		for (int i = 0; i < tabObservateur.size(); i++) {
			tabObservateur.get(i).actualiser(this);
		}
	}

	public void notifierObservateur(String valeur) {
		for (int i = 0; i < tabObservateur.size(); i++) {
			tabObservateur.get(i).actualiser(this, valeur);
		}
	}

	public void notifierObservateur(String valeur, int code) {
		for (int i = 0; i < tabObservateur.size(); i++) {
			tabObservateur.get(i).actualiser(this, valeur, code);
		}

	}

	public void notifierObservateur(int id) {
		for (int i = 0; i < tabObservateur.size(); i++) {
			tabObservateur.get(i).actualiser(this, id);
		}
	}

	public void notifierObservateur(int code, Champ[] champs) {
		for (int i = 0; i < tabObservateur.size(); i++) {
			tabObservateur.get(i).actualiser(this, code, champs);
		}
	}

	public void notifierObservateur(int code, Employe employe) {
		for (int i = 0; i < tabObservateur.size(); i++) {
			tabObservateur.get(i).actualiser(this, code, employe);
		}
	}

	public void notifierObservateur(int code, Champ champs) {
	}

	public void notifierObservateur(int code, Employe employe, Champ[] champs) {
	}

}
