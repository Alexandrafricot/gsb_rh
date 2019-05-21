package bzh.gsbrh.vues;

import java.awt.event.WindowEvent;

import javax.swing.SpringLayout;

import bzh.gsbrh.fabriques.FactBouton;
import bzh.gsbrh.fabriques.FactChamp;
import bzh.gsbrh.fabriques.FactMessage;
import bzh.gsbrh.modeles.Images;
import bzh.gsbrh.observateurs.Fenetre;
import bzh.gsbrh.observateurs.Observable;
import bzh.gsbrh.observateurs.Observateur;

/**
 * <p>
 * Fenetre d'identification de l'utilisateur
 * </p>
 * 
 * @author Alexandra Fricot
 * @version 1.0
 */
public class FenIdentification extends Fenetre {

	/**
	 * Clé de hachage de la classe
	 */
	private static final long serialVersionUID = -4321737025853119539L;

	/**
	 * Champ de saisie du login
	 */
	private Champ saisieLogin;

	/**
	 * Champ de saisie du mot de passe
	 */
	private Champ saisieMdp;

	/**
	 * Bouton de connexion
	 */
	private Bouton connect;

	/**
	 * Booléen de validation du login
	 */
	private boolean loginOk = false;

	/**
	 * Booléen de validation du mot de passe
	 */
	private boolean mdpOk = false;

	/**
	 * Booléen de validation du service
	 */
	private boolean serviceOk = false;

	/**
	 * Position y du premier champ
	 */
	private int nord = 20;

	/**
	 * Unique instance du formulaire
	 */
	private static FenIdentification moi;

	/**
	 * Constructeur surchargé
	 * 
	 * @param titre
	 *            Titre de la fenêtre
	 * @param o
	 *            Observateur par defaut
	 */
	private FenIdentification(String titre, Observateur o) {
		super(titre, o);
		setSize(350, 160);
		setLocationRelativeTo(null);
		initComposant();
	}

	/**
	 * Méthode singleton pour instancier une fenetre d'identification
	 * 
	 * @param titre
	 *            Titre de la fenêtre
	 * @param o
	 *            Observateur par defaut
	 * @return L'unique instance de la fenêtre d'identification
	 */
	public static FenIdentification creerFenetre(String titre, Observateur o) {
		if (moi == null)
			moi = new FenIdentification(titre, o);
		return moi;
	}

	/**
	 * Mutateur de loginOk
	 * 
	 * @param result
	 *            Nouvelle valeur du booléen
	 */
	public void validerLogin(boolean result) {
		loginOk = result;
	}

	/**
	 * Mutateur de MdpOk
	 * 
	 * @param result
	 *            Nouvelle valeur du booléen
	 */
	public void validerMdp(boolean result) {
		mdpOk = result;
	}

	/**
	 * Mutateur de serviceOk
	 * 
	 * @param result
	 *            Nouvelle valeur du booléen
	 */
	public void validerService(boolean result) {
		serviceOk = result;
	}

	/**
	 * Lance la vérification de la connexion
	 */
	public void connexion() {
		if (loginOk && mdpOk) {
			if (serviceOk) {
				FactMessage.fabriqueMessage(ID_MESSAGE_CO);
				notifierObservateur(ID_VALIDE_CO);
			} else {
				FactMessage.fabriqueMessage(ID_ERREUR_SE);
				loginOk = false;
				mdpOk = false;
			}
		} else
			FactMessage.fabriqueMessage(ID_ERREUR_CO);
	}
	
	public void afficher(int code) {
		FactMessage.fabriqueMessage(code);
	}

	public void reinitialiser() {
		saisieLogin.initialiserSaisie();
		saisieMdp.initialiserSaisie();
		loginOk = false;
		mdpOk = false;
		serviceOk = false;
	}

	public void initComposant() {
		contentPane = this.getContentPane();
		this.contentPane.setLayout(this.layout);
		connect = FactBouton.fabriqueBouton(this, BO_CONNECT, Images.CONNECT.getIcon());
		contentPane.add(image);
		contentPane.setBackground(COLOR_BACKGROUNG);
		this.saisieLogin = FactChamp.fabriqueChamp(this, LOGINCO);
		contentPane.add(this.saisieLogin);
		this.saisieMdp = FactChamp.fabriqueChamp(this, MDPCO);
		contentPane.add(this.saisieMdp);
		contentPane.add(connect);
		placementComposant();
	}

	public void placementComposant() {
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, connect, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
		layout.putConstraint(SpringLayout.BASELINE, connect, -25, SpringLayout.SOUTH, contentPane);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, image, 50, SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, image, 0, SpringLayout.VERTICAL_CENTER, contentPane);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.saisieLogin, 30, SpringLayout.HORIZONTAL_CENTER,
				this.contentPane);
		layout.putConstraint(SpringLayout.NORTH, this.saisieLogin, this.nord, SpringLayout.NORTH, this.contentPane);
		this.nord += 25;
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.saisieMdp, 30, SpringLayout.HORIZONTAL_CENTER,
				this.contentPane);
		layout.putConstraint(SpringLayout.NORTH, this.saisieMdp, this.nord, SpringLayout.NORTH, this.contentPane);
	}

	public void actualiser(Observable o) {
		if (o == connect) {
			Champ[] champs = new Champ[2];
			champs[0] = saisieLogin;
			champs[1] = saisieMdp;
			this.notifierObservateur(CONNEXION, champs);
		}
	}

	public void windowClosing(WindowEvent arg0) {
		notifierObservateur(AP_CONFIRM_Q);
	}
}
