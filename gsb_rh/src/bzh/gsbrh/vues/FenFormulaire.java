package bzh.gsbrh.vues;

import java.awt.event.WindowEvent;

import javax.swing.JLabel;
import javax.swing.SpringLayout;

import bzh.gsbrh.fabriques.FactBouton;
import bzh.gsbrh.fabriques.FactChamp;
import bzh.gsbrh.fabriques.FactMessage;
import bzh.gsbrh.modeles.Employe;
import bzh.gsbrh.modeles.Images;
import bzh.gsbrh.modeles.Information;
import bzh.gsbrh.observateurs.Fenetre;
import bzh.gsbrh.observateurs.Observable;
import bzh.gsbrh.observateurs.Observateur;

/**
 * <p>
 * Fenetre formulaire d'ajout et de modification d'un employé
 * </p>
 * 
 * @author Alexandra Fricot
 * @version 1.1
 */
public class FenFormulaire extends Fenetre {

	/**
	 * Clé de hachage de la classe.
	 */
	private static final long serialVersionUID = 1532480987206216778L;

	/**
	 * Bouton de reinitialisation du formulaire
	 */
	private Bouton reinitialise;

	/**
	 * Bouton de validation du formulaire
	 */
	private Bouton valider;

	/**
	 * Message d'introduction du formulaire
	 */
	private JLabel intro;

	/**
	 * Bouton de retour vers la liste des employés
	 */
	private Bouton retour;

	/**
	 * Tableau des champs du formulaire
	 */
	protected Champ[] champs;

	/**
	 * L'employé en cours de traitement, vide si nouvel employé
	 */
	private Employe unEmploye;

	/**
	 * Variable d'espace entre 2 champs
	 */
	private int space = 30;

	/**
	 * Position sur l'axe y des boutons valider et réinitialisé
	 */
	private int yValide = -30;

	/**
	 * Position sur l'axe y du titre du formulaire
	 */
	private int yTitre = 50;

	/**
	 * Position y du premier champ du formulaire
	 */
	private int departChamp = 80;

	/**
	 * Unique instance du formulaire
	 */
	private static FenFormulaire moi;

	/**
	 * Constructeur privé d'une fenetre de formulaire pour ajouter ou modifier un
	 * employé passé en paramètre
	 * 
	 * @param titre
	 *            Titre du formulaire
	 * @param o
	 *            Observateur de la fenetre
	 * @param employe
	 *            Employé à modifier, il sera vide si c'est un nouvel employé
	 */
	private FenFormulaire(String titre, Observateur o, Employe employe) {
		super(titre, o);
		unEmploye = employe;
		fermeture = 1;
		setSize(500, 550);
		setLocationRelativeTo(null);
		initComposant();
		setForm(employe);
	}

	/**
	 * Methode singleton pour intancier un formulaire
	 * 
	 * @param titre
	 *            Titre du formulaire
	 * @param o
	 *            Observateur de la fenêtre
	 * @param employe
	 *            Employé à modifier, il sera vide si c'est un nouvel employé
	 * @return Unique instance de la fenetre
	 */
	public static FenFormulaire creerFenetre(String titre, Observateur o, Employe employe) {
		if (moi == null)
			moi = new FenFormulaire(titre, o, employe);
		else
			moi.setForm(employe, titre);
		return moi;
	}

	/**
	 * Génère et positionne les champs du formulaire
	 */
	public void genererChamps() {
		champs = new Champ[12];
		for (int i = 0; i < Information.getTypes().length - 1; i++) {
			champs[i] = FactChamp.fabriqueChamp(this, unEmploye.getInfos(i).getType());
		}
		champs[0].bloquerChamp();

		this.notifierObservateur(FO_ATTRCOMB, champs[11]);
		for (Champ unChamp : champs) {
			contentPane.add(unChamp);
			layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, unChamp, 0, SpringLayout.HORIZONTAL_CENTER,
					this.contentPane);
			layout.putConstraint(SpringLayout.NORTH, unChamp, departChamp, SpringLayout.NORTH, this);
			departChamp += space;
		}
	}

	/**
	 * Met à jour les valeurs des champs avec l'employé courant
	 */
	public void majChamps() {
		for (int i = 0; i < Information.getTypes().length - 1; i++) {
			champs[i].setValeur(unEmploye.getInfos(i).getValeur());
		}
	}

	/**
	 * Lis le contenu des champs
	 * 
	 * @return Employé généré par le contenu des champs
	 */
	public Employe lireEmploye() {
		Employe employe = new Employe();
		for (int i = 0; i < Information.getTypes().length - 1; i++) {
			employe.getInfos(i).setValeur(champs[i].getText());
		}
		return employe;
	}

	/**
	 * Met a jour le formulaire d'ajout ou de modification d'un employé
	 * 
	 * @param employe
	 *            L'employé à traiter. Il sera vide si c'est un nouvel employé.
	 * @param titre
	 *            Titre du formulaire.
	 */
	public void setForm(Employe employe, String titre) {
		this.setTitre(titre);
		setTitle(APPLI_TITRE + titre);
		unEmploye = employe;
		majChamps();
		reinitialiser();
		intro.setText(getTitre() + " :");
		switch (titre) {
		case FE_TITRE_AJOUT:
			valider.setText(BOUTON_AJOUT);
			valider.setId(FO_AJ);
			break;
		case FE_TITRE_MODIF:
			valider.setText(BOUTON_MODIF);
			valider.setId(FO_MO);
			break;
		}
	}

	/**
	 * Met a jour le formulaire d'ajout ou de modification d'un employé
	 * 
	 * @param employe
	 *            L'employé à traiter. Il sera vide si c'est un nouvel employé.
	 */
	public void setForm(Employe employe) {
		unEmploye = employe;
		majChamps();
		reinitialiser();
		intro.setText(getTitre() + " :");
		switch (getTitre()) {
		case FE_TITRE_AJOUT:
			valider.setText(BOUTON_AJOUT);
			valider.setId(FO_AJ);
			break;
		case FE_TITRE_MODIF:
			valider.setText(BOUTON_MODIF);
			valider.setId(FO_MO);
			break;
		}
	}

	/**
	 * Affiche un message en fonction du code passé en paramètre
	 * 
	 * @param code
	 *            Numéro du message à afficher(cf Lexique)
	 * @param employe
	 *            Employé modifié
	 */
	public void afficher(int code, Employe employe) { // Le controleur demande à afficher un message
		switch (code) {
		case FO_MESSAGE_A:
			FactMessage.fabriqueMessage(FO_MESSAGE_A);
			this.unEmploye = new Employe();
			reinitialiser();
			notifierObservateur(LI_VALIDE_AJ);
			break;
		case FO_MESSAGE_MO:
			FactMessage.fabriqueMessage(FO_MESSAGE_MO);
			unEmploye = employe;
			majChamps();
			break;
		default:
			FactMessage.fabriqueMessage(code);
			break;
		}
	}

	/**
	 * Place les composants de la fenêtre
	 */
	public void placementComposant() {
		layout.putConstraint(SpringLayout.WEST, retour, 5, SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, retour, 5, SpringLayout.WEST, contentPane);

		layout.putConstraint(SpringLayout.EAST, image, -10, SpringLayout.EAST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, image, 10, SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.WEST, intro, 50, SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, intro, yTitre, SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.EAST, valider, -20, SpringLayout.HORIZONTAL_CENTER, contentPane);
		layout.putConstraint(SpringLayout.BASELINE, valider, yValide, SpringLayout.SOUTH, contentPane);

		layout.putConstraint(SpringLayout.WEST, reinitialise, 60, SpringLayout.HORIZONTAL_CENTER, contentPane);
		layout.putConstraint(SpringLayout.BASELINE, reinitialise, yValide, SpringLayout.SOUTH, contentPane);
	}

	/**
	 * Instancie les éléments de la fenêtre
	 */
	public void initComposant() {
		contentPane = this.getContentPane();
		this.contentPane.setLayout(this.layout);

		contentPane.setBackground(COLOR_BACKGROUNG);

		retour = FactBouton.fabriqueBouton(this, BO_RETOUR, Images.RETOUR.getIcon());
		// Creation et ajout des composant au panneau
		intro = new JLabel(getTitre() + " :");

		valider = null;
		valider = FactBouton.fabriqueBouton(this, BO_AJOUT, Images.VALIDER.getIcon());

		reinitialise = FactBouton.fabriqueBouton(this, BO_REINIT, Images.REINIT.getIcon());

		genererChamps();
		contentPane.add(intro);
		contentPane.add(image);
		contentPane.add(valider);
		contentPane.add(reinitialise);
		contentPane.add(retour);

		placementComposant();
	}

	/**
	 * Reinitialise le contenu des champs avec leur valeur d'origine
	 */
	public void reinitialiser() {
		for (Champ unChamp : champs) {
			if (FE_TITRE_AJOUT.equals(getTitre()) && unChamp.getType() == CHAMP_ID) {
				this.notifierObservateur(FO_ATTRID, champs[0]);
			} else {
				unChamp.initialiserSaisie();
			}
		}
	}

	/**
	 * Réalise des actions en fonction des evenements produit
	 * 
	 * @param o
	 *            Observable envoyant la notification d'evenement
	 */
	public void actualiser(Observable o) {
		if (o == champs[1] || o == champs[2]) {
			if (!champs[2].getText().isEmpty() && !champs[1].getText().isEmpty()) {
				Champ[] lesChamps = new Champ[3];
				lesChamps[0] = champs[1];
				lesChamps[1] = champs[2];
				lesChamps[2] = champs[3];
				this.notifierObservateur(GENERE_LOG, lesChamps);
			}
		}
		if (o instanceof Champ) {
			this.notifierObservateur(CHAMP_TEST, (Champ) o);
		}

	}

	/**
	 * Réalise des actions en fonction du code passé en paramètre
	 * 
	 * @param o
	 *            Observable envoyant la notification d'evenement
	 * @param code
	 *            code de la notification, définit ce qu'il doit être fait.
	 */
	public void actualiser(Observable o, int code) {
		Employe employe = lireEmploye();
		switch (code) {
		case FO_AJ: // L'utilisateur ajoute un employé
			this.notifierObservateur(FO_AJOUT, employe, champs);
			break;
		case FO_MO: // L'utilisateur modifie un employé
			this.notifierObservateur(FO_MODIF, employe, champs);
			break;
		case FO_RE: // L'utilisateur réinitialise le formulaire
			reinitialiser();
			break;
		case FO_BA: // L'utitilsateur quitte le formulaire
			notifierObservateur(FO_BA);
			break;
		case BO_DATE_NOW: // L'utilisateur demande de remplir la date d'embauche avec la date du jour
			notifierObservateur(BO_DATE_NOW, champs[10]);
			break;
		case BO_GENE_PASS: // L'utilisateur demande à générer un mot de passe
			notifierObservateur(BO_GENE_PASS, champs[4]);
			break;
		}
	}

	public void windowClosing(WindowEvent arg0) {
		notifierObservateur(FO_FERMETURE);
	}

}
