package bzh.gsbrh.observateurs;

import java.util.ArrayList;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import bzh.gsbrh.modeles.Employe;
import bzh.gsbrh.vues.Champ;

/**
 * <p>
 * Classe héritant de DefaultCellEditor, permet de definir le contenu des
 * cellules d'un JTable
 * </p>
 * 
 * @author Alexandra Fricot
 * @version 1.0
 */
public abstract class EditeurCellule extends DefaultCellEditor implements Observable {

	/**
	 * Clé de hachage de la classe
	 */
	private static final long serialVersionUID = 3439565440245280161L;

	/**
	 * Collection des Observateurs
	 */
	protected ArrayList<Observateur> tabObservateur = new ArrayList<Observateur>();

	/**
	 * Constructeur de la cellule
	 * 
	 * @param textField
	 *            Texte a afficher dans la cellule
	 */
	public EditeurCellule(JTextField textField) {
		super(textField);
	}

	/**
	 * Constructeur surchargé de la cellule.
	 * 
	 * @param checkBox
	 *            Element interactif dans la cellule.
	 * @param o
	 *            Observateur de la cellule.
	 */
	public EditeurCellule(JCheckBox checkBox, Observateur o) {
		super(checkBox);
		ajouterObservateur(o);
	}

	/**
	 * Constructeur surchargé de la cellule.
	 * 
	 * @param comboBox
	 *            ComboBox à afficher dans la cellule
	 */
	public EditeurCellule(JComboBox<?> comboBox) {
		super(comboBox);
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
		for (int i = 0; i < tabObservateur.size(); i++) {
			tabObservateur.get(i).actualiser(this, code, champs);
		}
	}

	public void notifierObservateur(int code, Champ champ) {
		for (int i = 0; i < tabObservateur.size(); i++) {
			tabObservateur.get(i).actualiser(this, code, champ);
		}
	}

	public void notifierObservateur(int code, Employe employe) {
		for (int i = 0; i < tabObservateur.size(); i++) {
			tabObservateur.get(i).actualiser(this, code, employe);
		}
	}

	public void notifierObservateur(int code, Employe employe, Champ[] champs) {
	}
}
