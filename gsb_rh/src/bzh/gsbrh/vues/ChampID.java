package bzh.gsbrh.vues;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import bzh.gsbrh.observateurs.Observateur;

/**
 * <p>
 * ChampId est une classe héritant de Champ permettant d'instancier un libellé
 * et une zone de saisie à 4 caractères.
 * </p>
 * 
 * @author Alexandra Fricot
 * @version 1.0
 * @see LimitText
 */
public class ChampID extends Champ {

	/**
	 * Clé de hachage de la classe.
	 */
	private static final long serialVersionUID = -5381986933088843402L;

	/**
	 * Constructeur surchargé
	 * 
	 * @param o
	 *            Observateur par défaut
	 * @param label
	 *            Libellé du champ
	 */
	public ChampID(Observateur o, String label) {
		super(o, label, CHAMP_ID, false);
		this.saisie = new TSaisie(this, valeur, 4, false);
		this.valeur = "";
		JPanel zone = new JPanel(new GridLayout(1, 2));
		this.label.setPreferredSize(new Dimension(170, 20));
		zone.add(saisie);
		JLabel espace = new JLabel();
		zone.add(espace);
		zone.setBackground(COLOR_BACKGROUNG);
		placementChamps(this.label, zone);
	}

	public void razCouleur() {
		saisie.setBackground(null);
	}
}
