package bzh.gsbrh.observateurs;

import java.awt.Container;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SpringLayout;

import bzh.gsbrh.fabriques.FactMessage;
import bzh.gsbrh.modeles.Employe;
import bzh.gsbrh.modeles.Images;
import bzh.gsbrh.vues.Champ;

/**
 * <p>
 * Fenetre est une classe abstraite dérivé de JFrame permettant d'instancier
 * différent type de fenêtre.
 * </p>
 * <p>
 * Implémente WindowListener et Observable pour notifier ses Observateur d'une
 * demande de fermeture afin de la traiter par le controleur.
 * </p>
 * <p>
 * Implémente Observateur afin d'être actualisé lors d'un changement d'état de
 * ses composants Observable.
 * </p>
 * 
 * @author Alexandra Fricot
 * @version 1.0
 */
public abstract class Fenetre extends JFrame implements Observateur, Observable, WindowListener, Lexique {

	/**
	 * Clé de hachage de la classe.
	 */
	private static final long serialVersionUID = 4672173837576484767L;

	/**
	 * Géstionnaire de mise en forme principal.
	 */
	protected SpringLayout layout = new SpringLayout();

	/**
	 * Container principal de la fenetre.
	 */
	protected Container contentPane;

	/**
	 * Titre de la fenêtre.
	 */
	private String titre;

	/**
	 * icone et image principal de la fenêtres.
	 */
	protected JLabel image = new JLabel(Images.LOGO.getIcon());

	/**
	 * Largeur de l'écran de l'utilisateur.
	 */
	protected int largeur;

	/**
	 * Hauteur de l'écran de l'utilisateur.
	 */
	protected int hauteur;

	/**
	 * Collection des Observateurs.
	 */
	protected ArrayList<Observateur> tabObservateur = new ArrayList<Observateur>();

	/**
	 * Valeur de femeture de la page par clique sur la croix de la fenêtre. 0 pour
	 * oui, 1 pour non.
	 */
	protected int fermeture = 0;
	
	protected Fenetre moi = null;

	/**
	 * Constructeur protégé de Fenetre
	 */
	protected Fenetre() {

	}

	/**
	 * Constructeur surchargé de la Fenêtre
	 * 
	 * @param titre
	 *            Titre de la fenêtre.
	 * @param o
	 *            Observauteur par défaut.
	 */
	protected Fenetre(String titre, Observateur o) {
		ajouterObservateur(o);
		setTitle(APPLI_TITRE + titre);
		this.setTitre(titre);
		addWindowListener(this);
		Image icone = Images.LOGO.getImage();
		setIconImage(icone);

		GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();

		Rectangle maximumWindowBounds = graphicsEnvironment.getMaximumWindowBounds();

		largeur = (int) (maximumWindowBounds.getWidth());
		hauteur = (int) (maximumWindowBounds.getHeight());
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}

	/**
	 * Instancie les éléments de la fenêtre
	 */
	public abstract void initComposant();

	/**
	 * Place les composants de la fenêtre.
	 */
	public abstract void placementComposant();

	/**
	 * Accesseur de la valeur de fermeture de la fenêtre.
	 * 
	 * @return La valeur de femerture de la fenêtre(1 ou 0).
	 */
	public boolean getFermeture() {
		if (fermeture == 0) {
			return true;
		}
		return false;
	}

	/**
	 * Accesseur du titre de la fenêtre
	 * 
	 * @return Le titre de la fenêtre
	 */
	public String getTitre() {
		return titre;
	}

	/**
	 * Mutateur du titre de la fenêtre
	 * 
	 * @param titre
	 *            Nouveau titre de la fenêtre
	 */
	public void setTitre(String titre) {
		this.titre = titre;
	}

	/**
	 * Réinitialiser les champs de la fenêtre.
	 * 
	 * @see bzh.gsbrh.vues.FenIdentification
	 * @see bzh.gsbrh.vues.FenFormulaire
	 */
	public void reinitialiser() {
	}

	/**
	 * Destructeur par defaut.
	 */
	public void finalize() {
		this.dispose();
	}

	public void windowActivated(WindowEvent arg0) {

	}

	public void windowClosed(WindowEvent arg0) {
	}

	public void windowClosing(WindowEvent arg0) {
		fermeture = FactMessage.fabriqueMessage(AP_CONFIRM_Q);
		notifierObservateur(AP_CONFIRM_Q);
	}

	public void windowDeactivated(WindowEvent arg0) {

	}

	public void windowDeiconified(WindowEvent arg0) {

	}

	public void windowIconified(WindowEvent arg0) {

	}

	public void windowOpened(WindowEvent arg0) {

	}

	public void ajouterObservateur(Observateur o) {
		tabObservateur.add(o);
	}

	public void supprimerObservateur(Observateur o) {
		tabObservateur.remove(o);
	}

	public void actualiser(Observable o, String valeur) {

	}

	public void actualiser(Observable o, int id) {

	}

	public void actualiser(Observable o, String valeur, int code) {

	}

	public void actualiser(Observable o, int code, Champ[] champs) {
		notifierObservateur(code, champs);
	}

	public void actualiser(Observable o, int code, Employe employe) {
		notifierObservateur(code, employe);
	}

	public void actualiser(Observable o, int code, Employe employe, Champ[] champs) {
		notifierObservateur(code, employe, champs);
	}

	public void actualiser(Observable o, int code, Champ champs) {
		notifierObservateur(code, champs);
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

	public void notifierObservateur(int code, Champ champs) {
		for (int i = 0; i < tabObservateur.size(); i++) {
			tabObservateur.get(i).actualiser(this, code, champs);
		}
	}

	public void notifierObservateur(int code, Employe employe, Champ[] champs) {
		for (int i = 0; i < tabObservateur.size(); i++) {
			tabObservateur.get(i).actualiser(this, code, employe, champs);
		}
	}
}
