package bzh.gsbrh.vues;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.UIManager;

import bzh.gsbrh.modeles.Entete;
import bzh.gsbrh.observateurs.EditeurCellule;
import bzh.gsbrh.observateurs.Lexique;
import bzh.gsbrh.observateurs.Observateur;

/**
 * <p>
 * Classe héritant de EditeurCellule permet de mettre une cellule d'un JTable en
 * écoute grace à un bouton
 * </p>
 * 
 * @author Alexandra Fricot
 * @version 1.0
 * 
 */
public class ClientsTableRenderer extends EditeurCellule implements Lexique {

	/**
	 * Clé de hachage de la classe
	 */
	private static final long serialVersionUID = 2055414065332134422L;

	/**
	 * Bouton permettant l'écoute de la cellule
	 */
	private Bouton button;

	/**
	 * Libellé du bouton
	 */
	private String label;

	/**
	 * Icone du bouton
	 */
	private ImageIcon image;

	/**
	 * Bouléen permettant de définir si la cellule est cliqué
	 */
	private boolean clicked;

	/**
	 * Entete du tableau
	 */
	private Entete entete;

	@SuppressWarnings("unused")
	/**
	 * Nombre de ligne et de colonne du model
	 */
	private int row, col;

	/**
	 * Tableau associé à l'editeur
	 */
	private JTable table;

	/**
	 * Constructeur surchargé
	 * 
	 * @param checkBox
	 *            Element interactif dans la cellule.
	 * @param o
	 *            Observateur par defaut
	 * @param entete
	 *            Entete du tableau
	 */
	public ClientsTableRenderer(JCheckBox checkBox, Observateur o, Entete entete) {
		super(checkBox, o);

		this.entete = entete;
		button = new Bouton();
		button.setOpaque(true);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fireEditingStopped();
			}
		});
	}

	/**
	 * Remplis la cellule en écoute avec un bouton
	 */
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		this.table = table;
		this.row = row;
		this.col = column;

		button.setForeground(Color.black);
		button.setBackground(UIManager.getColor("Button.background"));
		label = (value == null) ? "" : value.toString();
		if (value instanceof Bouton) {
			label = ((Bouton) value).getText();
			image = ((Bouton) value).getIcone();
		}
		button.setBackground(COLOR_BOUTON_VALIDER);
		button.setText(label);
		button.setIcone(image);
		clicked = true;
		return button;
	}

	/**
	 * Verifie si la cellule est éditable, si la cellule contient un bouton,
	 * celui-ci notifie son Observateur
	 */
	public Object getCellEditorValue() {
		String id = "";
		if (clicked) {
			id = (String) table.getValueAt(row, entete.indiceDEntete(ID));
			if (new String(label).equals(BOUTON_MODIF)) {
				notifierObservateur(id, LI_MO);
			}

			if (new String(label).equals(BOUTON_SUPPR)) {
				notifierObservateur(id, LI_PR);
			}

		}
		clicked = false;

		return new String(label);
	}

	public boolean stopCellEditing() {
		clicked = false;
		return super.stopCellEditing();
	}

	protected void fireEditingStopped() {
		try {
			super.fireEditingStopped();
		} catch (Exception e) {

		}
	}

}