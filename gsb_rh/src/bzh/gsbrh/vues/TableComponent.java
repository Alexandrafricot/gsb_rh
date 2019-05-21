package bzh.gsbrh.vues;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Permet de placer un bouton dans une cellule
 * 
 * @author Alexandra Fricot
 * @version 1.0
 */
public class TableComponent extends DefaultTableCellRenderer {

	/**
	 * Clé de hachage de la classe
	 */
	private static final long serialVersionUID = 7521667302038648395L;

	/**
	 * Modifie le contenu de la cellule
	 * 
	 * @return Le contenu de la cellule
	 */
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		// Si la valeur de la cellule est un Nouton, on transtype cette valeur
		if (value instanceof Bouton)
			return (Bouton) value;

		else
			return this;
	}
}
