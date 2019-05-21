package bzh.gsbrh.vues;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;

import bzh.gsbrh.modeles.Entete;
import bzh.gsbrh.observateurs.Observateur;
import bzh.gsbrh.observateurs.Panneau;

/**
 * Onglet est un panneau permettant d'afficher un tableau d'employ�
 * 
 * @author Alexandra Fricot
 * @version 1.0
 */
public class Onglet extends Panneau {

	/**
	 * Clé de hachage de la classe.
	 */
	private static final long serialVersionUID = 4609516995077556579L;

	/**
	 * Tableau graphique d'employé
	 */
	public JTable table;

	/**
	 * Model du tableau d'employé
	 */
	private ZModel model;

	/**
	 * Paneau avec avec ascensseur contenant le tableau
	 */
	private JScrollPane scrollpane;

	/**
	 * Liste des employés à intégrer dans le tableau
	 */
	private Object[][] liste;

	/**
	 * Entete du tableau d'employés
	 */
	private Entete entete;

	/**
	 * Constructeur surchargé
	 * 
	 * @param o
	 *            Observateur par defaut
	 * @param layout
	 *            Gestionnaire de mise en forme par défaut
	 * @param liste
	 *            Liste à intégrer dans le tableau
	 * @param entete
	 *            Entête du tableau
	 */
	public Onglet(Observateur o, SpringLayout layout, Object[][] liste, Entete entete) {
		super(o, layout);
		this.liste = liste;
		this.entete = entete;
		creerTableau();

	}

	/**
	 * Accesseur panneau contenant le tableau
	 * 
	 * @return Le panneau du tableau
	 */
	public JScrollPane getScrollpane() {
		return scrollpane;
	}

	/**
	 * Actualise la liste puis tableau avec une nouvelle liste d'employé
	 * 
	 * @param liste
	 *            Nouvelle liste à afficher
	 */
	public void actualiserListeEmploye(Object[][] liste) {
		this.liste = liste;
		entete.ajouterElements(this.liste);
		model.setData(this.liste);
		try {
			model.modifierEmploye();
		} catch (Exception e) {

		}
	}

	/**
	 * Lance la création du tableau
	 */
	public void creerTableau() {
		entete.ajouterElements(liste);
		model = new ZModel(liste, entete) {

			/**
			 * Clé de hachage de la classe.
			 */
			private static final long serialVersionUID = -7155865294622073231L;

			public boolean isCellEditable(int row, int column) {

				return (column == entete.indiceDEntete(COLMODIFIE) || column == entete.indiceDEntete(PP_DATE_TX));
			}
		};

		table = new JTable(model);
		table.getTableHeader().setBackground(COLOR_BOUTON_VALIDER);
		table.getTableHeader().setReorderingAllowed(false);
		table.setAutoCreateRowSorter(true);
		table.setRowHeight(30);
		table.setGridColor(COLOR_BORDER);

		table.setDefaultRenderer(JComponent.class, new TableComponent());
		// Place en ecoute les colonnes avec des boutons grace au methodes de
		// ClientsTableRenderer
		table.getColumnModel().getColumn(entete.indiceDEntete(COLMODIFIE))
				.setCellEditor(new ClientsTableRenderer(new JCheckBox(), this, entete));
		table.getColumnModel().getColumn(entete.indiceDEntete(PP_DATE_TX))
				.setCellEditor(new ClientsTableRenderer(new JCheckBox(), this, entete));

		scrollpane = new JScrollPane(table);

	}

}
