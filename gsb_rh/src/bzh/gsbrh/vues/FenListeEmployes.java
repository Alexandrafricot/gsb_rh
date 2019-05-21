package bzh.gsbrh.vues;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.SpringLayout;

import bzh.gsbrh.fabriques.FactBouton;
import bzh.gsbrh.modeles.Entete;
import bzh.gsbrh.modeles.Images;
import bzh.gsbrh.observateurs.Fenetre;
import bzh.gsbrh.observateurs.Observable;
import bzh.gsbrh.observateurs.Observateur;

/**
 * <p>
 * Fenetre principale de l'application, affiche un tableau des employés,
 * permettant d'interagir avec.
 * </p>
 * 
 * @author Alexandra Fricot
 * @version 1.0
 */
public class FenListeEmployes extends Fenetre {

	/**
	 * Clé de hachage de la classe.
	 */
	private static final long serialVersionUID = -6916825447286108452L;

	/**
	 * Message d'accueil de l'application
	 */
	private JLabel info;

	/**
	 * Nom de l'utilisateur connécté
	 */
	private JLabel nomU;

	/**
	 * Bouton d'ajout d'un employé
	 */
	private Bouton ajouter;

	/**
	 * Bouton de déconnexion
	 */
	private Bouton deconnect;

	/**
	 * Panneau d'onglet de la liste des employé
	 */
	private JTabbedPane onglet = new JTabbedPane(JTabbedPane.BOTTOM);

	/**
	 * Libellé de l'onglet des employés actifs
	 */
	private JLabel onglet1;

	/**
	 * Libellé de l'onglet des employés inactifs
	 */
	private JLabel onglet2;

	/**
	 * Onglet des employés actifs
	 */
	private Onglet liste;

	/**
	 * Onglet des employés inactifs
	 */
	private Onglet listeI;

	/**
	 * Total des employés actifs
	 */
	int rowActif;

	/**
	 * Total des employés inactifs
	 */
	int rowInactif;

	/**
	 * Tableau des employés actifs
	 */
	private Object[][] listeActif;

	/**
	 * Tableau des employés inactifs
	 */
	private Object[][] listeInactif;

	/**
	 * Entete du tableau de la liste des employés
	 */
	private Entete entete;

	/**
	 * Unique instance de la fenêtre
	 */
	private static FenListeEmployes moi;

	/**
	 * Constructeur privé de la lste des employés
	 * 
	 * @param titre
	 *            Titre de la fenêtre
	 * @param o
	 *            Observateur par défaut
	 * @param listeA
	 *            Tableau des employés actifs
	 * @param listeI
	 *            Tableau des employés inactif
	 * @param entete
	 *            Entete du tableau de la liste des employés
	 */
	private FenListeEmployes(String titre, Observateur o, Object[][] listeA, Object[][] listeI, Entete entete) {
		super(titre, o);
		this.fermeture = 1;
		rowActif = listeA.length;
		rowInactif = listeI.length;
		listeActif = listeA;
		listeInactif = listeI;
		this.entete = entete;

		setSize(largeur, hauteur);
		setLocationRelativeTo(null);
		initComposant();
	}

	/**
	 * Méthode singleton pour instancier la fenêtre liste des employés
	 * 
	 * @param titre
	 *            Titre de la fenêtre
	 * @param o
	 *            Observateur par défaut
	 * @param listeA
	 *            Tableau des employés actifs
	 * @param listeI
	 *            Tableau des employés inactifs
	 * @param entete
	 *            Entete du tableau de la liste des employés
	 * @return La fenetre liste employé
	 */
	public static FenListeEmployes creerFenetre(String titre, Observateur o, Object[][] listeA, Object[][] listeI,
			Entete entete) {
		if (moi == null)
			moi = new FenListeEmployes(titre, o, listeA, listeI, entete);
		return moi;
	}

	/**
	 * Mutateur du nom de l'utilisateur
	 * 
	 * @param nom
	 *            Nouveau nom de l'utilisateur
	 */
	public void setNomU(String nom) {
		nomU.setText(nom);
	}

	/**
	 * Actualise le tableau des employés actifs
	 * 
	 * @param liste
	 *            Nouveau tableau des employés actifs
	 * @see bzh.gsbrh.vues.FenListeEmployes
	 */
	public void actualiserListeEmpActif(Object[][] liste) {
		this.liste.actualiserListeEmploye(liste);
		onglet1.setText(ONGLET_1 + " (" + liste.length + ")");
	}

	/**
	 * Actualise le tableau des employés inactifs
	 * 
	 * @param liste
	 *            Nouveau tableau des employés inactifs
	 * @see bzh.gsbrh.vues.FenListeEmployes
	 */
	public void actualiserListeEmpInactif(Object[][] liste) {
		this.listeI.actualiserListeEmploye(liste);
		onglet2.setText(ONGLET_2 + " (" + liste.length + ")");
	}
	
	/**
	 * Destructeur par defaut.
	 */
	public void finalize() {
		this.removeAll();
		this.dispose();
		moi = null;
	}

	public void initComposant() {
		liste = new Onglet(this, layout, listeActif, entete);
		listeI = new Onglet(this, layout, listeInactif, entete);
		contentPane = this.getContentPane();
		this.contentPane.setLayout(this.layout);

		contentPane.setBackground(COLOR_BACKGROUNG);

		ajouter = FactBouton.fabriqueBouton(this, BO_AJOUT, Images.AJOUTER.getIcon());
		deconnect = FactBouton.fabriqueBouton(this, BO_DECONNECT, Images.DECONNECT.getIcon());
		info = new JLabel(M_ACCUEIL);
		nomU = new JLabel("");

		contentPane.add(ajouter);
		contentPane.add(info);
		contentPane.add(nomU);
		contentPane.add(deconnect);
		onglet1 = new JLabel(ONGLET_1 + " (" + rowActif + ")");

		onglet.add("", this.liste.getScrollpane());
		onglet.setTabComponentAt(0, onglet1);
		onglet2 = new JLabel(ONGLET_2 + " (" + rowInactif + ")");
		onglet.add("", this.listeI.getScrollpane());
		onglet.setTabComponentAt(1, onglet2);

		onglet.setPreferredSize(new Dimension((int) (largeur), ((int) (this.getHeight() * 0.78))));

		contentPane.add(onglet);
		contentPane.add(image);
		placementComposant();
	}

	public void placementComposant() {

		layout.putConstraint(SpringLayout.EAST, ajouter, -30, SpringLayout.EAST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, ajouter, 20, SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.WEST, image, 10, SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, image, 10, SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.WEST, onglet, 1, SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.SOUTH, onglet, -5, SpringLayout.SOUTH, contentPane);

		layout.putConstraint(SpringLayout.WEST, info, 80, SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, info, 17, SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.WEST, nomU, 15, SpringLayout.EAST, info);
		layout.putConstraint(SpringLayout.NORTH, nomU, 17, SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.WEST, deconnect, 15, SpringLayout.EAST, info);
		layout.putConstraint(SpringLayout.NORTH, deconnect, 17, SpringLayout.SOUTH, info);
	}

	public void actualiser(Observable o) {
		notifierObservateur();
	}

	public void actualiser(Observable o, int id) {
		notifierObservateur(id);
	}

	public void actualiser(Observable o, String valeur, int code) {
		notifierObservateur(valeur, code);
	}

}
