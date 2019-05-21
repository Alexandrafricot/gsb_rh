package bzh.gsbrh.vues;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import bzh.gsbrh.modeles.Employe;
import bzh.gsbrh.observateurs.Lexique;
import bzh.gsbrh.observateurs.Observable;
import bzh.gsbrh.observateurs.Observateur;

/**
 * <p>
 * Classe dérivé de JButton permettant d'instancier un bouton.
 * </p>
 * <p>
 * Implément ActionListener et Observable, pour lui permettre de notifier ses
 * Observables d'un clic par l'envoi d'une simple notification d'une part mais
 * aussi d'une notification avec son id d'autre part.
 * </p>
 * 
 * @author Alexandra Fricot
 * @version 1.0
 */
public class Bouton extends JButton implements ActionListener, Observable, Lexique {

	/**
	 * Clé de hachage de la classe
	 */
	private static final long serialVersionUID = 1653059447673602753L;

	/**
	 * Collection des Observateurs
	 */
	protected ArrayList<Observateur> tabObservateur = new ArrayList<Observateur>();

	/**
	 * Identifiant du bouton
	 */
	protected int id = -1;

	/**
	 * Icone du bouton
	 */
	private ImageIcon icone;

	/**
	 * Constructeur par defaut
	 */
	public Bouton() {

	}

	/**
	 * Constructeur surchargé
	 * 
	 * @param o
	 *            Observauteur par défaut
	 * @param titre
	 *            Libelle du bouton
	 * @param id
	 *            Identifiant du bouton
	 * @param icone
	 *            Icone du bouton
	 */
	public Bouton(Observateur o, String titre, int id, ImageIcon icone) {
		ajouterObservateur(o);
		this.setText(titre);
		this.id = id;
		addActionListener(this);
		this.setBackground(COLOR_BOUTON_VALIDER);
		this.setToolTipText(titre);
		this.icone = icone;
		this.setIcon(this.icone);
	}

	/**
	 * Mutateur de l'identifiant du bouton
	 * 
	 * @param id
	 *            Nouvel identifiant du bouton
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Accesseur de l'icone du bouton
	 * 
	 * @return L'icone du bouton
	 */
	public ImageIcon getIcone() {
		return icone;
	}

	/**
	 * Mutateur de l'icone du bouton
	 * 
	 * @param icone
	 *            Nouvel icone du bouton
	 */
	public void setIcone(ImageIcon icone) {
		this.setIcon(this.icone = icone);
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

	public void notifierObservateur(int id) {
		for (int i = 0; i < tabObservateur.size(); i++) {
			tabObservateur.get(i).actualiser(this, id);
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

	public void notifierObservateur(int code, Champ[] champs) {
	}

	public void notifierObservateur(int code, Employe employe) {
	}

	public void notifierObservateur(int code, Employe employe, Champ[] champs) {
	}

	public void notifierObservateur(int code, Champ champs) {
	}

	/**
	 * En cas de clique sur le bouton celui-ci averti ses observateur du clic ainsi
	 * que de son id.
	 */
	public void actionPerformed(ActionEvent e) {
		notifierObservateur();
		notifierObservateur(id);
	}

}
