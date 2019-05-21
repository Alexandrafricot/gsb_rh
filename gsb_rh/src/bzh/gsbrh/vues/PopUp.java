package bzh.gsbrh.vues;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import bzh.gsbrh.fabriques.FactBouton;
import bzh.gsbrh.fabriques.FactChamp;
import bzh.gsbrh.fabriques.FactMessage;
import bzh.gsbrh.modeles.Employe;
import bzh.gsbrh.modeles.Images;
import bzh.gsbrh.observateurs.Lexique;
import bzh.gsbrh.observateurs.Observable;
import bzh.gsbrh.observateurs.Observateur;

/**
 * Créer une boite de dialogue bloquante pour demander la saisie d'une date
 * 
 * @author Alexandra Fricot
 * @version 1.0
 */
public class PopUp extends JDialog implements Observateur, Observable, Lexique {

	/**
	 * Clé de hachage de la classe.
	 */
	private static final long serialVersionUID = -8298417191676214970L;

	/**
	 * Collection des Observateurs
	 */
	public ArrayList<Observateur> tabObservateur = new ArrayList<Observateur>();

	/**
	 * Employé à modifier via la boite de dialogue
	 */
	private Employe unEmploye;

	/**
	 * Container de la boite de dialogue
	 */
	private Container contentPane;

	/**
	 * Géstionnaire de mise en forme principal.
	 */
	protected SpringLayout layout = new SpringLayout();

	/**
	 * Position de départ du champ
	 */
	private int nord = 15;

	/**
	 * Dimension de la zone des boutons
	 */
	private Dimension dimensionBout = new Dimension(95, 25);

	/**
	 * Bouton de validation
	 */
	private Bouton valider;

	/**
	 * Bouton de retour
	 */
	private Bouton annuler;

	/**
	 * Bouton pour reintégrer
	 */
	private Bouton deprog;

	/**
	 * Panneau de bouton
	 */
	private JPanel boutons;

	/**
	 * Champ de saisie de la date
	 */
	private Champ date;

	/**
	 * Bouléen pour valider la femeture de la boite de dialogue
	 */
	private boolean ok;

	/**
	 * Constructeur surcharché
	 * 
	 * @param parent
	 *            Fenêtre appelant la boite de dialogue
	 * @param title
	 *            Titre de la fenêtre de dialogue
	 * @param modal
	 *            Bouléen déterminant si la boite de dialogue sera ou non bloquante
	 * @param o
	 *            Observateur par defaut
	 * @param employe
	 *            Employé à modifier via la boite de dialogue
	 */
	public PopUp(JFrame parent, String title, boolean modal, Observateur o, Employe employe) {
		super(parent, title, modal);
		ajouterObservateur(o);
		this.setSize(300, 160);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		unEmploye = employe;
		this.initComposant();
	}

	/**
	 * Methode pour afficher la boite de dialogue
	 */
	public void lancePopUp() {
		this.setVisible(true);
	}

	/**
	 * Instancie les éléments de la boite de dialogue
	 */
	public void initComposant() {

		contentPane = getContentPane();
		contentPane.setLayout(layout);
		valider = FactBouton.fabriqueBouton(this, BO_VALIDER, Images.VALIDER.getIcon());
		annuler = FactBouton.fabriqueBouton(this, BO_RETOUR, Images.RETOUR.getIcon());
		deprog = FactBouton.fabriqueBouton(this, BO_REINIT, null);

		String valeur;
		if (unEmploye.getInfos(12).getValeur().equals(null) || unEmploye.getInfos(12).getValeur() == null
				|| unEmploye.getInfos(12).getValeur().equals("")) {
			valeur = LocalDate.now().toString();
		} else
			valeur = unEmploye.getInfos(12).getValeur();
		date = FactChamp.fabriqueChamp(this, DATED);
		date.setValeur(valeur);
		date.initialiserSaisie();
		placementComposant();
	}

	/**
	 * Place les composants
	 */
	public void placementComposant() {
		boutons = new JPanel(new GridLayout(1, 3));
		valider.setPreferredSize(dimensionBout);
		annuler.setPreferredSize(dimensionBout);
		deprog.setPreferredSize(dimensionBout);
		boutons.add(valider);
		boutons.add(annuler);
		if (!unEmploye.getInfos(12).getValeur().equals(""))
			boutons.add(deprog);
		contentPane.add(boutons);
		contentPane.add(date);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, boutons, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
		layout.putConstraint(SpringLayout.BASELINE, boutons, -50, SpringLayout.SOUTH, contentPane);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, date, 0, SpringLayout.HORIZONTAL_CENTER, this.contentPane);
		layout.putConstraint(SpringLayout.NORTH, date, this.nord, SpringLayout.NORTH, this.contentPane);
		this.nord += 25;
	}

	/**
	 * Mutateur de ok et ferme la boite de dialogue si il passe vrai
	 * 
	 * @param valide
	 *            La nouvelle valeur de ok
	 */
	public void setOk(boolean valide) {
		ok = valide;
		if (ok)
			this.setVisible(false);
	}

	/**
	 * Affiche un message d'erreur
	 */
	public void erreur() {
		FactMessage.fabriqueMessage(LI_ERREUR_MO);
	}

	/**
	 * Affiche un message de confirmation
	 * 
	 * @param code
	 *            du message à afficher
	 * @return Un bouléen qui détèrmine si l'utilisateur à validé ou non le message
	 *         de confirmation
	 */
	public boolean valider(int code) {
		int confirme = FactMessage.fabriqueMessage(code);
		if (confirme == 0)
			return true;
		return false;
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

	public void notifierObservateur(String valeur, int code) {
		for (int i = 0; i < tabObservateur.size(); i++) {
			tabObservateur.get(i).actualiser(this, valeur, code);
		}
	}

	public void notifierObservateur(String valeur) {
		for (int i = 0; i < tabObservateur.size(); i++) {
			tabObservateur.get(i).actualiser(this, valeur);
		}
	}

	public void actualiser(Observable o) {
		if(o == date)
			this.notifierObservateur(CHAMP_TEST, (Champ) o);
	}

	public void actualiser(Observable o, int id) {
		switch (id) {
		case BO_DATE_NOW:
			this.notifierObservateur(BO_DATE_NOW, date);
			break;
		case PP_DATE_VA:
			if (valider(LI_CONFIRM_MODIFDATE))
				this.notifierObservateur(M_DATED, date);
			break;
		case PP_DATE_RE:
			this.notifierObservateur(PP_DATE_RE, date);
			break;
		case PP_DATE_BA:
			this.dispose();
		}
	}

	public void actualiser(Observable o, String valeur) {
	}

	public void actualiser(Observable o, String valeur, int code) {
	}

	public void notifierObservateur(int code, Champ[] champs) {
		for (int i = 0; i < tabObservateur.size(); i++) {
			tabObservateur.get(i).actualiser(this, code, champs);
		}
	}

	public void notifierObservateur(int code, Employe employe) {
		for (int i = 0; i < tabObservateur.size(); i++) {
			tabObservateur.get(i).actualiser(this, code, employe);
		}
	}

	public void actualiser(Observable o, int code, Champ[] champs) {
		notifierObservateur(code, champs);
	}

	public void actualiser(Observable o, int code, Employe employe) {
		notifierObservateur(code, employe);
	}

	public void notifierObservateur(int code, Champ champs) {
		for (int i = 0; i < tabObservateur.size(); i++) {
			tabObservateur.get(i).actualiser(this, code, champs);
		}
	}

	public void actualiser(Observable o, int code, Champ champs) {

	}

	public void notifierObservateur(int code, Employe employe, Champ[] champs) {
	}

	public void actualiser(Observable o, int code, Employe employe, Champ[] champs) {
	}
}
