package bzh.gsbrh.vues;

import javax.swing.table.AbstractTableModel;

import bzh.gsbrh.modeles.Entete;

/**
 * Classe de modèle personnalisée
 * 
 * @author Alexandra Fricot
 * @version 1.0
 */
public class ZModel extends AbstractTableModel {

	/**
	 * Clé de hachage de la classe
	 */
	private static final long serialVersionUID = -3163109800641869333L;

	/**
	 * Tableau des données à intégrer dans le modèle
	 */
	public Object[][] data;

	/**
	 * Entête des colonnes à intégrer dans le model
	 */
	private Entete title;

	/**
	 * Constructeur surchargé
	 * 
	 * @param data
	 *            Tableau des données à intégrer dans le modèle
	 * @param entete
	 *            Entête des colonnes à intégrer dans le model
	 */
	public ZModel(Object[][] data, Entete entete) {

		this.data = data;

		this.title = entete;

	}

	/**
	 * Mutateur du tableau des données à intégrer dans le modèle
	 * 
	 * @param data
	 *            Nouveau tableau des données
	 */
	public void setData(Object[][] data) {
		this.data = data;
	}

	/**
	 * Met à jour l'affichage des données
	 */
	public void modifierEmploye() {
		this.fireTableDataChanged();
	}

	/**
	 * Accesseur du nombre de colonnes du tableau de données
	 */
	public int getColumnCount() {

		return this.title.getEntete().length;

	}

	/**
	 * Accesseur du nombre de ligne du tableau de données
	 */
	public int getRowCount() {

		return this.data.length;

	}

	/**
	 * Accesseur de la valeur du tableau à la position recherché
	 * 
	 * @param row
	 *            Indice de la ligne
	 * @param col
	 *            Indice de la colonne
	 * @return La valeur à la position recherché
	 */
	public Object getValueAt(int row, int col) {

		return this.data[row][col];

	}

	/**
	 * Retourne la classe de la donnée de la colonne
	 */
	public Class<? extends Object> getColumnClass(int col) {
		// On retourne le type de la cellule à la colonne demandée

		// On se moque de la ligne puisque les types de données sont les mêmes quelle
		// que soit la ligne

		// On choisit donc la première ligne
		return this.data[0][col].getClass();
	}

	/**
	 * Retourne vrai si la cellule est éditable
	 */
	public boolean isCellEditable(int row, int col) {

		// On appelle la méthode getValueAt qui retourne la valeur d'une cellule

		// Et on effectue un traitement spécifique si c'est un JButton

		if (getValueAt(0, col) instanceof Bouton)

			return false;

		return true;

	}

	/**
	 * Retourne le titre de la colonne à l'indice spécifié
	 */
	public String getColumnName(int col) {

		return this.title.getEntete()[col];

	}
}
