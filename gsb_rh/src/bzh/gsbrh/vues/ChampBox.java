package bzh.gsbrh.vues;

import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import bzh.gsbrh.observateurs.Observateur;

/**
 * <p>
 * ChampBox est une classe héritant de Champ permettant d'instancier un libellé
 * et une liste déroulante.
 * </p>
 * 
 * @author Alexandra Fricot
 * @version 1.0
 * @see Champ
 */
public class ChampBox extends Champ {

	/**
	 * Clé de hachage de la classe.
	 */
	private static final long serialVersionUID = 471355274182663069L;

	/**
	 * La liste déroulante du champ
	 */
	protected JComboBox<Object> box;

	/**
	 * L'index par défaut de la liste déroulante
	 */
	protected int index = 0;

	/**
	 * Constructeur surchargé
	 * 
	 * @param o
	 *            Observateur par defaut
	 * @param label
	 *            Libellé du champ
	 */
	public ChampBox(Observateur o, String label) {
		super(o, label, CHAMP_COMB, false);
		this.label = new JLabel(label);
		this.box = new JComboBox<Object>();
		placementChamps(this.label, this.box);
		this.setPreferredSize(new Dimension(340, 20));
	}

	/**
	 * Séléctionne l'index par défaut dans la liste déroulante
	 */
	public void initialiserSaisie() {
		this.box.setSelectedIndex(index);
	}

	/**
	 * Accesseur de l'index de la séléction de la liste
	 * 
	 * @return L'index séléctionné dans la liste
	 */
	public String getText() {
		return Integer.toString(this.box.getSelectedIndex());
	}

	public void setValeur(Object val) {
		if (val instanceof Integer)
			this.index = (int) val;
		if (val instanceof String) {
			if (((String) val).equals(""))
				this.index = 0;
			else
				this.index = Integer.parseInt((String) val);
		}
	}

	public void setBox(Object item) {
		this.box.addItem(item);
	}

}
