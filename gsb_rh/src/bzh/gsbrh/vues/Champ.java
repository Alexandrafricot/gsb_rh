package bzh.gsbrh.vues;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JLabel;

import bzh.gsbrh.observateurs.Observateur;
import bzh.gsbrh.observateurs.Panneau;

/**
 * <p>
 * Champ est une classe héritant de Panneau permettant d'instancier un libellé
 * et une zone de saisie.
 * </p>
 * 
 * @author Alexandra Fricot
 * @version 1.0
 * @see bzh.gsbrh.observateurs.Panneau
 */
public class Champ extends Panneau {

	/**
	 * Clé de hachage de la classe.
	 */
	private static final long serialVersionUID = 2012110496336624559L;

	/**
	 * Le label associé au champ
	 */
	protected JLabel label;

	/**
	 * Zone de saisie associé au champ
	 * 
	 * @see TSaisie
	 */
	protected TSaisie saisie;

	/**
	 * Valeur par defaut du champ
	 */
	protected String valeur = "";

	/**
	 * Gestionnaire de mise en forme par défaut du champ
	 */
	protected static GridLayout layout = new GridLayout(1, 3);

	/**
	 * Identifiant du type du champ
	 */
	protected int type;

	/**
	 * Dimension par défaut de la zone de saisies
	 */
	protected Dimension dimensionText = new Dimension(170, 20);

	/**
	 * Accesseur du codedu type de champ
	 * 
	 * @return le code type du champ
	 */
	public int getType() {
		return this.type;
	}

	/**
	 * Remet la couleur par défaut du champ
	 */
	public void razCouleur() {
		saisie.setBackground(COLOR_TEXT_WHITE);
	}

	/**
	 * Modifie la couleur du champ en fonction de son état
	 * 
	 * @param etat
	 *            Booléen déterminant la couleur du champ(vrai = couleur par defaut,
	 *            faux = rouge)
	 */
	public void setCouleur(boolean etat) {
		if (etat) {
			saisie.setBackground(COLOR_TEXT_WHITE);
			saisie.setEtat(false);
		} else {
			saisie.setBackground(COLOR_TEXT_IVALIDE);
			saisie.setEtat(true);
		}
	}

	/**
	 * Mutateur de l'état de la zone de saisie.
	 * 
	 * @param etat
	 *            Nouvel etat de la zone de saisie
	 */
	public void setEtat(boolean etat) {
		saisie.setEtat(etat);
	}

	/**
	 * Accesseur du label du champ
	 * 
	 * @return L'objet label du champ
	 */
	public JLabel getLabel() {
		return this.label;
	}

	/**
	 * Accesseur du texte de la zone de saisie
	 * 
	 * @return Le texte de la zone de saisie
	 */
	public String getText() {
		return this.saisie.getText();
	}

	/**
	 * Mutateur du texte de la zone de saisie
	 * 
	 * @param text
	 *            Nouveau texte de la zone de saisie.
	 */
	public void setText(String text) {
		switch (this.type) {
		case CHAMP_TEXT:
			this.saisie.setText(text + "");
		}
	}

	/**
	 * Mutateur pour les champs de type box(liste déroulante)
	 * 
	 * @param item
	 *            Item à ajouter à la liste déroulante
	 * 
	 * @see ChampBox
	 */
	public void setBox(Object item) {
	}

	/**
	 * Mutateur de la valeur du champ
	 * 
	 * @param val
	 *            Nouvel valeur du champ
	 */
	public void setValeur(Object val) {
		switch (type) {
		case CHAMP_TEL:
		case CHAMP_CP:
		case CHAMP_ID:
		case CHAMP_TEXT:
			this.valeur = (String) val;
			break;
		}
	}

	/**
	 * Constructeur surchargé du champ
	 * 
	 * @param o
	 *            Observateur par defaut
	 * @param label
	 *            Libellé du champ
	 * @param type
	 *            Type du champ
	 * @param connect
	 *            booleen pour définir si il s'agit d'un champ de la fenetre de
	 *            connéxion
	 */
	public Champ(Observateur o, String label, int type, boolean connect) {
		super(o, layout);
		this.label = new JLabel(label);
		this.type = type;
		switch (type) {
		case CHAMP_CP:
			this.saisie = new TSaisie(this, valeur, 5, true);
			this.saisie.setPreferredSize(this.dimensionText);
			placementChamps(this.label, this.saisie);
			break;
		case CHAMP_TEL:
			this.saisie = new TSaisie(this, valeur, 10, true);
			placementChamps(this.label, this.saisie);
			this.saisie.setPreferredSize(this.dimensionText);
			break;
		case CHAMP_TEXT:
			this.saisie = new TSaisie(this);
			this.valeur = "";
			if (connect)
				this.saisie.setPreferredSize(new Dimension(100, 20));
			else
				this.saisie.setPreferredSize(this.dimensionText);
			placementChamps(this.label, this.saisie);
			break;
		}
	}

	/**
	 * Efface le contenu de la zone de texte
	 */
	public void effacerSaisie() {
		this.saisie.setText("");
	}

	/**
	 * Initialise la zone de saisie avec la valeur du champ
	 */
	public void initialiserSaisie() {
		this.saisie.setText(this.valeur);
		this.razCouleur();
	}

	/**
	 * Bloque la saisie de la zone de texte
	 */
	public void bloquerChamp() {
		this.saisie.setEditable(false);
		this.saisie.setBackground(null);
	}

	/**
	 * Débloque la saisie de la zone de texte
	 */
	public void deBloquerChamp() {
		this.saisie.setEditable(true);
		this.saisie.setBackground(COLOR_TEXT_WHITE);
	}

	/**
	 * Pace les éléments sur la zone du champ
	 * 
	 * @param label
	 *            Label du champ
	 * @param zone
	 *            Zone de saisie du champ
	 */
	protected void placementChamps(JComponent label, JComponent zone) {
		this.add(label);
		this.add(zone);
		this.setBackground(COLOR_BACKGROUNG);
	}
}
