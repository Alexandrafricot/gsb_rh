package bzh.gsbrh.vues;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import bzh.gsbrh.modeles.Employe;
import bzh.gsbrh.observateurs.Lexique;
import bzh.gsbrh.observateurs.Observable;
import bzh.gsbrh.observateurs.Observateur;

/**
 * <p>
 * Classe héritant de JTextField permettant d'instancier une zone de texte.
 * </p>
 * <p>
 * Implément DocumentListener et Observable pour lui permettre de simplement
 * notifier ses Observateurs lors d'une saisie.
 * </p>
 * 
 * @author Alexandra Fricot
 * @version 1.0
 */
public class TSaisie extends JTextField implements Observable, DocumentListener, FocusListener, Lexique {

	/**
	 * Clé de hachage de la classe
	 */
	private static final long serialVersionUID = 8778703126954140844L;

	/**
	 * Collection des Observateurs
	 */
	public ArrayList<Observateur> tabObservateur = new ArrayList<Observateur>();

	/**
	 * Document sur lequel sera transcris les saisies de la zone de texte
	 */
	private LimitText document;

	/**
	 * Bouléen représentant l'état de la saisie après teste
	 */
	private boolean etat = false;

	/**
	 * Constructeur surchargé
	 * 
	 * @param o
	 *            Observateur par defaut
	 */
	public TSaisie(Observateur o) {
		ajouterObservateur(o);
		getDocument().addDocumentListener(this);
		addFocusListener(this);
	}

	/**
	 * Constructeur surchargé
	 * 
	 * @param o
	 *            Observateur par défaut
	 * @param valeur
	 *            Valeur d'origine du champs
	 * @param colonne
	 *            Nombre de caractère accépté dans la zone de texte
	 * @param nombre
	 *            Bouléen indiquant si la saisie doit être exlusivement numérique ou
	 *            non
	 */
	public TSaisie(Observateur o, String valeur, int colonne, boolean nombre) {
		super(valeur);
		ajouterObservateur(o);
		this.document = new LimitText(colonne, nombre);
		this.setDocument(this.document);
		getDocument().addDocumentListener(this);
		this.setText(valeur);
		addFocusListener(this);
	}

	/**
	 * Methode permettant d'ajouter à une zone de saisie une limitation de la saisie
	 * après son instanciation
	 * 
	 * @param colonne
	 *            Nombre de caractère accépté dans la zone de texte
	 * @param nombre
	 *            Bouléen indiquant si la saisie doit être exlusivement numérique ou
	 *            non
	 */
	public void compterTaille(int colonne, boolean nombre) {
		this.document = new LimitText(colonne, nombre);
		this.setDocument(this.document);
		getDocument().addDocumentListener(this);
	}

	/**
	 * Accesseur de l'etat de la saisie
	 * 
	 * @return Le bouléen de l'état de la saisie
	 */
	public boolean getEtat() {
		return etat;
	}

	/**
	 * Mutateur de l'etat de la saisie
	 * 
	 * @param etat
	 *            Nouvel état de la saisie
	 */
	public void setEtat(boolean etat) {
		this.etat = etat;
	}

	public void changedUpdate(DocumentEvent arg0) {
	}

	public void insertUpdate(DocumentEvent arg0) {
		notifierObservateur();
	}

	public void removeUpdate(DocumentEvent arg0) {
		notifierObservateur();
	}

	public void focusGained(FocusEvent e) {
		if (getEtat()) {
			notifierObservateur();
		}
		if (this.isEditable()) {
			this.selectAll();
		}
	}

	public void focusLost(FocusEvent e) {

	}

	public void ajouterObservateur(Observateur o) {
		tabObservateur.add(o);
	}

	public void supprimerObservateur(Observateur o) {
		tabObservateur.remove(o);
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
