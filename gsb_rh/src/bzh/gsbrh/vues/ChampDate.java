package bzh.gsbrh.vues;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.time.LocalDate;

import javax.swing.JPanel;

import bzh.gsbrh.fabriques.FactBouton;
import bzh.gsbrh.observateurs.Observateur;

/**
 * <p>
 * ChampDate est une classe héritant de Champ permettant d'instancier un libellé
 * et trois zone de saisie numérique de 2, 2 et 4 caractères.
 * </p>
 * 
 * @author Alexandra Fricot
 * @version 1.0
 * @see bzh.gsbrh.observateurs.Panneau
 * @see LimitText
 */
public class ChampDate extends Champ {

	/**
	 * Clé de hachage de la classe.
	 */
	private static final long serialVersionUID = 6762529098002780401L;

	/**
	 * Zone de saisie des jours
	 */
	protected TSaisie saisieJours;

	/**
	 * Zone de saisie des mois
	 */
	protected TSaisie saisieMois;

	/**
	 * Dimension des zones de saisie des jours et des mois
	 */
	protected Dimension dimensionDate_JM = new Dimension(50, 20);

	/**
	 * Dimension de la zone de saisie des années
	 */
	protected Dimension dimensionDate_AA = new Dimension(100, 20);

	/**
	 * Bouton permettant de remplir les champs avec la date du jour
	 */
	private Bouton now;

	/**
	 * Constructeur surchargé
	 * 
	 * @param o
	 *            Observateur par defaut
	 * @param label
	 *            Libelle du champ
	 * @param valeur
	 *            Date par defaut contenu dans les champs
	 * @param retourLigne
	 *            Booléen indiquant si le Libelle et les zones de saisie doivent
	 *            être ou non séparé par un retour à la ligne
	 */
	public ChampDate(Observateur o, String label, String valeur, boolean retourLigne) {
		super(o, label, CHAMP_DATE, false);
		if (valeur == null || valeur.equals(null))
			valeur = LocalDate.now().toString();
		if (retourLigne)
			this.setLayout(new GridLayout(2, 1));
		String splitDate[] = valeur.split("-");
		String mois = splitDate[1];
		String jours = splitDate[2];
		String annee = splitDate[0];
		this.saisieJours = new TSaisie(this, jours, 2, true);
		this.saisieJours.setPreferredSize(this.dimensionDate_JM);
		this.saisieMois = new TSaisie(this, mois, 2, true);
		this.saisieMois.setPreferredSize(this.dimensionDate_JM);
		this.saisie = new TSaisie(this, annee, 4, true);
		this.saisie.setPreferredSize(this.dimensionDate_AA);
		this.saisie.setText(annee);
		this.saisieMois.setText(mois);
		this.saisieJours.setText(jours);
		JPanel date = new JPanel(new GridLayout(1, 4));
		JPanel moisJours = new JPanel(new GridLayout(1, 3));
		moisJours.add(this.saisieJours);
		moisJours.add(this.saisieMois);
		date.add(moisJours);

		date.add(this.saisie);
		now = FactBouton.fabriqueBouton(o, BO_DATE_NOW, null);
		now.setText("...");
		date.add(now);

		placementChamps(this.label, date);
		this.setPreferredSize(new Dimension(200, 45));
		this.setBackground(null);
	}

	/**
	 * Constructeur surchargé
	 * 
	 * @param o
	 *            Observateur par defaut
	 * @param label
	 *            Libellé du champ
	 */
	public ChampDate(Observateur o, String label) {
		super(o, label, CHAMP_DATE, false);
		if (valeur == null || valeur.equals(null))
			valeur = LocalDate.now().toString();
		String splitDate[] = valeur.split("-");
		String mois;
		String jours;
		String annee;
		if (splitDate[0].isEmpty()) {
			valeur = LocalDate.now().toString();
			splitDate = valeur.split("-");
			mois = splitDate[1];
			jours = splitDate[2];
			annee = splitDate[0];
		} else {
			mois = splitDate[1];
			jours = splitDate[2];
			annee = splitDate[0];
		}

		this.saisieJours = new TSaisie(this, jours, 2, true);
		this.saisieJours.setPreferredSize(this.dimensionDate_JM);
		this.saisieMois = new TSaisie(this, mois, 2, true);
		this.saisieMois.setPreferredSize(this.dimensionDate_JM);
		this.saisie = new TSaisie(this, annee, 4, true);
		this.saisie.setPreferredSize(this.dimensionDate_AA);
		this.saisie.setText(annee);
		this.saisieMois.setText(mois);
		this.saisieJours.setText(jours);
		JPanel date = new JPanel(new GridLayout(1, 4));
		JPanel moisJours = new JPanel(new GridLayout(1, 2));
		moisJours.add(this.saisieJours);
		moisJours.add(this.saisieMois);
		date.add(moisJours);
		date.add(this.saisie);
		now = FactBouton.fabriqueBouton(o, BO_DATE_NOW, null);
		now.setText("...");
		date.add(now);
		placementChamps(this.label, date);
		this.setPreferredSize(new Dimension(340, 20));
	}

	public void bloquerChamp() {
		this.saisie.setEditable(false);
		this.saisie.setBackground(COLOR_TEXT_NULL);
		this.saisieMois.setEditable(false);
		this.saisieMois.setBackground(COLOR_TEXT_NULL);
		this.saisieJours.setEditable(false);
		this.saisieJours.setBackground(COLOR_TEXT_NULL);
		this.now.setEnabled(false);
	}

	public void deBloquerChamp() {
		this.saisie.setEditable(true);
		this.saisie.setBackground(Color.WHITE);
		this.saisieMois.setEditable(true);
		this.saisieMois.setBackground(Color.WHITE);
		this.saisieJours.setEditable(true);
		this.saisieJours.setBackground(Color.WHITE);
		this.now.setEnabled(true);
	}

	public void initialiserSaisie() {
		if (valeur == null)
			valeur = LocalDate.now().toString();
		String splitDate[] = valeur.split("-");
		if (splitDate.length < 3) {
			valeur = LocalDate.now().toString();
			splitDate = valeur.split("-");
		}
		String annee = splitDate[0];
		String mois = splitDate[1];
		String jours = splitDate[2];
		this.saisie.setText(annee);
		this.saisieMois.setText(mois);
		this.saisieJours.setText(jours);
		this.razCouleur();
	}

	public String getText() {
		return this.saisie.getText() + "-" + this.saisieMois.getText() + "-" + this.saisieJours.getText();
	}

	public void setValeur(Object val) {
		this.valeur = (String) val;
	}

	public void setText(String val) {
		String splitDate[] = val.split("-");
		String annee = splitDate[0];
		String mois = splitDate[1];
		String jours = splitDate[2];
		this.saisie.setText(annee);
		this.saisieMois.setText(mois);
		this.saisieJours.setText(jours);
	}

	public void setCouleur(boolean etat) {
		if (etat) {
			saisieJours.setBackground(COLOR_TEXT_VALIDE);
			saisieMois.setBackground(COLOR_TEXT_VALIDE);
			saisie.setBackground(COLOR_TEXT_VALIDE);
		} else {
			saisieJours.setBackground(COLOR_TEXT_IVALIDE);
			saisieMois.setBackground(COLOR_TEXT_IVALIDE);
			saisie.setBackground(COLOR_TEXT_IVALIDE);
		}
	}

	public void razCouleur() {
		if (saisieJours.isEditable())
			saisieJours.setBackground(COLOR_TEXT_WHITE);
		else
			saisieJours.setBackground(COLOR_TEXT_NULL);
		if (saisieMois.isEditable())
			saisieMois.setBackground(COLOR_TEXT_WHITE);
		else
			saisieMois.setBackground(COLOR_TEXT_NULL);
		if (saisie.isEditable())
			saisie.setBackground(COLOR_TEXT_WHITE);
		else
			saisie.setBackground(COLOR_TEXT_NULL);
	}

}