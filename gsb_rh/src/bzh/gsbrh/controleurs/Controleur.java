package bzh.gsbrh.controleurs;

import java.time.LocalDate;

import bzh.gsbrh.fabriques.FactFenetre;
import bzh.gsbrh.fabriques.FactMessage;
import bzh.gsbrh.modeles.Employe;
import bzh.gsbrh.modeles.GenerateurMDP;
import bzh.gsbrh.modeles.GestionEmploye;
import bzh.gsbrh.modeles.Information;
import bzh.gsbrh.modeles.Requetes;
import bzh.gsbrh.modeles.formDate;
import bzh.gsbrh.observateurs.Fenetre;
import bzh.gsbrh.observateurs.Lexique;
import bzh.gsbrh.observateurs.Observable;
import bzh.gsbrh.observateurs.Observateur;
import bzh.gsbrh.vues.Champ;
import bzh.gsbrh.vues.FenFormulaire;
import bzh.gsbrh.vues.FenIdentification;
import bzh.gsbrh.vues.FenListeEmployes;
import bzh.gsbrh.vues.PopUp;

/**
 * <p>
 * Controleur de l'application.
 * </p>
 * <p>
 * Controleur est extends de Thread afin de mettre à jour la liste des employés
 * toutes les 5 minutes
 * </p>
 * <p>
 * Le controleur est le moteur de l'application, il implémente l'interface
 * Observateur, il capte les notifications des éléments, traite les données
 * grâce aux différents modèles et envois les ordres et de nouvelles données aux
 * vues en fonction des besoins.
 * </p>
 * 
 * @author Alexandra Fricot
 * @version 1.1
 */
public class Controleur extends Thread implements Observateur, Lexique {

	/**
	 * Fenêtre d'ajout ou de modification d'un employé.
	 * 
	 * @see bzh.gsbrh.vues.FenFormulaire
	 */
	private FenFormulaire fenetre;

	/**
	 * Fenêtre principal, qui liste les employés par catégorie.
	 * 
	 * @see bzh.gsbrh.vues.FenListeEmployes
	 */
	private FenListeEmployes principale;

	/**
	 * Fenêtre de connexion a l'application.
	 * 
	 * @see bzh.gsbrh.vues.FenIdentification
	 */
	private FenIdentification connexion;

	/**
	 * Employé en cours d'ajout ou de modification.
	 * 
	 * @see bzh.gsbrh.modeles.Employe
	 */
	private Employe unEmploye;

	/**
	 * Employé connécté à l'application.
	 * 
	 * @see bzh.gsbrh.modeles.Employe
	 */
	private Employe lUtilisateur;

	/**
	 * Gestionnaire des employés.
	 * 
	 * @see bzh.gsbrh.modeles.GestionEmploye
	 * @see bzh.gsbrh.modeles.Employe
	 */
	private GestionEmploye lesEmployes;

	/**
	 * Constructeur du controleur.
	 */
	public Controleur() {
		lesEmployes = GestionEmploye.gestionEmploye(Requetes.listerEmployer(), Requetes.listerServices());
		this.start();
	}

	/**
	 * Instancie et affiche la fenetre d'identification de l'utilisateur à travers
	 * la fabrique de Fenetre.
	 * 
	 * @see bzh.gsbrh.vues.FenIdentification
	 * @see bzh.gsbrh.fabriques.FactFenetre
	 */
	public void lancerAppli() {
		this.connexion = FactFenetre.fabriqueFenetre(this, FE_IDENT);
		connexion.setVisible(true);
	}

	/**
	 * Met a jour la liste des employés
	 */
	private void mettreAjour() {
		lesEmployes.setListe(Requetes.listerEmployer());
		if (principale != null) {
			principale.actualiserListeEmpActif(lesEmployes.getListe());
			principale.actualiserListeEmpInactif(lesEmployes.getListeI());
		}
	}

	/**
	 * Lance la fenêtre liste des employés.
	 * 
	 * Instancie parrallèlement la fenêtre d'ajout/de modification d'un employé à
	 * travers la fabrique de Fenetre.
	 * 
	 * @see bzh.gsbrh.vues.FenListeEmployes
	 * @see bzh.gsbrh.fabriques.FactFenetre
	 */
	private void appliListe() {
		unEmploye = new Employe();
		if (fenetre != null)
			fenetre.setVisible(false);
		this.principale.setVisible(true);

	}

	/**
	 * Lance la partie ajout d'un employé a travers la fabrique de Fenetre.
	 * 
	 * @see bzh.gsbrh.vues.FenFormulaire
	 * @see bzh.gsbrh.fabriques.FactFenetre
	 */
	private void appliAjout() {
		principale.setVisible(false);
		unEmploye = new Employe();
		this.fenetre = FactFenetre.fabriqueFenetre(this, FE_AJOUT, unEmploye);
		fenetre.setVisible(true);
	}

	/**
	 * Lance la modification d'un employé.
	 * 
	 * @param id
	 *            Identifiant de l'employé à modifier.
	 * 
	 * @see bzh.gsbrh.vues.FenFormulaire
	 * @see bzh.gsbrh.modeles.Employe
	 */
	private void appliModif(String id) {
		principale.setVisible(false);
		unEmploye = lesEmployes.getEmploye(id).copie();
		this.fenetre = FactFenetre.fabriqueFenetre(this, FE_MODIF, unEmploye);
		fenetre.setVisible(true);
	}

	/**
	 * Traite la demande de fermeture de l'application.
	 * 
	 * @param f
	 *            Fenetre avec laquel traiter la demande.
	 * 
	 * @see bzh.gsbrh.observateurs.Fenetre
	 */
	public void fermetureAppli(Fenetre f) {
		if (f.getFermeture()) {
			if (fenetre != null)
				fenetre.finalize();
			if (principale != null)
				principale.finalize();
			principale = null;
			connexion.finalize();
			connexion = null;
			unEmploye = null;
			lUtilisateur = null;
			this.interrupt();
			System.exit(0);
		}
	}

	/**
	 * Verifie les informations de l'utilisateur qui demande à se connecter.
	 * 
	 * @param login
	 *            Login à verifier.
	 * @param mdp
	 *            Mot de passe à comparer.
	 * @return Un booléen qui confirme ou non la connexion.
	 * 
	 *         bzh.gsbrh.modeles.Requetes
	 */
	public boolean verifierMdp(String login, String mdp) {
		return Requetes.verifierMdp(login, mdp);
	}

	/**
	 * Permet de tester si l'utilisateur à accès à l'application, dans le cas où ce
	 * n'est pas le cas il est déconnecté
	 */
	public void testerUtilisateur() {
		Employe utilisateur = Requetes.trouverEmploye(lUtilisateur.getInfos(ID).getValeur(), M_ID);
		if (Integer.parseInt(utilisateur.getInfos(SERVICE).getValeur()) == 2
				&& !formDate.dateDepasse(utilisateur.getInfos(DATED).getValeur())) {
			lUtilisateur = utilisateur;
			if (principale != null) {
				principale.setNomU(
						lUtilisateur.getInfos(PRENOM).getValeur() + " " + lUtilisateur.getInfos(NOM).getValeur());
			}
		} else {
			lUtilisateur = null;
			principale.finalize();
			principale = null;
			if (fenetre != null) {
				fenetre.finalize();
				fenetre = null;
			}
			connexion.reinitialiser();
			lancerAppli();
			FactMessage.fabriqueMessage(Lexique.AP_DECO_FORCE);
		}
	}

	public void run() {
		try {
			while (!interrupted()) {
				Thread.sleep(30000);
				if (lUtilisateur != null) {
					if (Requetes.ConnexionEtablie()) {
						mettreAjour();
						testerUtilisateur();
					} else {
						FactMessage.fabriqueMessage(ID_ERREUR_COBDD);
					}
				}
			}
		} catch (InterruptedException e) {
			return;
		}
	}

	public void actualiser(Observable o) {
	}

	public void actualiser(Observable o, int id) {
		switch (id) {
		// L'utilisateur demande à se deconnécter
		case AP_DECO:
			lUtilisateur = null;
			principale.finalize();
			principale = null;
			if (fenetre != null) {
				fenetre.finalize();
				fenetre = null;
			}
			connexion.reinitialiser();
			lancerAppli();
			break;
		// L'utilisateur demande à lancer l'application ajouter
		case LI_AJ:
			appliAjout();
			break;
		// L'ajout de l'employé c'est bien passé, il faut rénitialiser le formulaire
		case LI_VALIDE_AJ:
			fenetre.reinitialiser();
			break;
		case AP_CONFIRM_Q:
			fermetureAppli((Fenetre) o);
			break;
		case ID_VALIDE_CO:
			connexion.setVisible(false);
		case FO_FERMETURE:
		case FO_BA:
			appliListe();
			break;
		}
	}

	public void actualiser(Observable o, String valeur, int code) {
		switch (code) {
		case LI_MO: // Lancer la modification d'un employé
			appliModif(valeur);
			break;
		case LI_PR: // Programmer date depart d'un employe
			unEmploye = lesEmployes.getEmploye(valeur).copie(); // Requetes.trouverEmploye(valeur, M_ID);
			Employe employe = unEmploye.copie();
			PopUp pop = new PopUp(null, PP_TITRE_DATE, true, this, employe);
			pop.lancePopUp();
			break;
		}
	}

	public void actualiser(Observable o, String valeur) {
	}

	public void actualiser(Observable o, int code, Champ[] champs) {
		switch (code) {
		case CONNEXION:
			String login = null;
			String mdp = null;
			for (int i = 0; i < champs.length; i++) {
				int type = champs[i].getType();
				switch (type) {
				case CHAMP_TEXT:
					login = champs[i].getText();
					break;
				case CHAMP_PASS:
					mdp = champs[i].getText();
					break;
				default:
					break;
				}
			}
			if (verifierMdp(login, mdp)) {
				connexion.validerLogin(true);
				connexion.validerMdp(true);
				lUtilisateur = Requetes.trouverEmploye(login, M_LOGIN);
				if (lesEmployes == null)
					lesEmployes = GestionEmploye.gestionEmploye(Requetes.listerEmployer(), Requetes.listerServices());
				if (Integer.parseInt(lUtilisateur.getInfos(SERVICE).getValeur()) == 2
						&& !formDate.dateDepasse(lUtilisateur.getInfos(DATED).getValeur())) {
					this.principale = FactFenetre.fabriqueFenetre(this, FE_LISTE, lesEmployes.getListe(),
							lesEmployes.getListeI(), lesEmployes.getEntete());
					connexion.validerService(true);
					principale.setNomU(
							lUtilisateur.getInfos(PRENOM).getValeur() + " " + lUtilisateur.getInfos(NOM).getValeur());
					;
				} else
					lUtilisateur = null;
			}
			if (Requetes.ConnexionEtablie()) {
				connexion.connexion();
			} else {
				FactMessage.fabriqueMessage(ID_ERREUR_COBDD);
			}
			break;
		case GENERE_LOG:
			String log = champs[1].getText().substring(0, 1).toLowerCase() + champs[0].getText().toLowerCase();
			if (!lesEmployes.verifLogin(log, unEmploye)) {
				if (!log.equals(unEmploye.getInfos(LOGIN).getValeur()))
					log = lesEmployes.formaterLog(log);
				champs[2].setText(log);
			} else if (!log.equals(unEmploye.getInfos(LOGIN).getValeur()))
				champs[2].setText(lesEmployes.formaterLog(log));
			break;
		}
	}

	public void actualiser(Observable o, int code, Employe employe) {
	}

	public void actualiser(Observable o, int code, Champ champ) {
		String contenu;
		switch (code) {
		case BO_DATE_NOW:
			champ.setText(LocalDate.now().toString());
			break;
		case M_DATED:
			contenu = formDate.formatDate(champ.getText());
			if (lesEmployes.verif(DATED, contenu, null)) {
				unEmploye.getInfos(DATED).setValeur(contenu);
				int[] codeModif = { M_DATED };
				if (Requetes.modifierEmploye(unEmploye, codeModif) != 0) {
					lesEmployes.ajouterEmploye(unEmploye);
					principale.actualiserListeEmpActif(lesEmployes.getListe());
					principale.actualiserListeEmpInactif(lesEmployes.getListeI());
					((PopUp) o).setOk(true);
				}
			} else
				((PopUp) o).erreur();
			break;
		case PP_DATE_RE:
			contenu = formDate.formatDate(champ.getText());
			unEmploye.getInfos(DATED).setValeur(null);
			if (((PopUp) o).valider(LI_CONFIRM_REINTEGRE) && lesEmployes.verif(DATED, contenu, null)) {
				unEmploye.getInfos(DATEE).setValeur(contenu);
				int[] codeModif = { M_DATEE, M_DATED };
				Requetes.modifierEmploye(unEmploye, codeModif);
				lesEmployes.ajouterEmploye(unEmploye);
				principale.actualiserListeEmpActif(lesEmployes.getListe());
				principale.actualiserListeEmpInactif(lesEmployes.getListeI());
				((PopUp) o).setOk(true);
			} else if (!lesEmployes.verif(DATED, contenu, null))
				((PopUp) o).erreur();
			break;

		case FO_ATTRID:
			contenu = lesEmployes.formaterId("1");
			champ.setValeur(contenu);
			champ.initialiserSaisie();
			break;
		case FO_ATTRCOMB:
			String[] services = lesEmployes.listerServices();
			for (int i = 0; i < services.length; i++) {
				champ.setBox(services[i]);
			}
			champ.initialiserSaisie();
			break;
		case BO_GENE_PASS:
			String pass = GenerateurMDP.generer(7, 12, false);
			champ.setText(pass);
			break;
		case CHAMP_TEST:
			contenu = champ.getText();
			if (!lesEmployes.verif(champ.getLabel().getText(), contenu, unEmploye) && !contenu.isEmpty())
				champ.setCouleur(false);
			else
				champ.setCouleur(true);
			break;
		}
	}

	public void actualiser(Observable o, int code, Employe employe, Champ[] champs) {
		switch (code) {
		// Modification d'un employé
		case FO_MODIF:
			// Ajout d'un employé
		case FO_AJOUT:
			boolean flag = true;
			int erreur = 0;
			for (int i = 0; i < Information.getTypes().length - 1; i++) {
				if (flag && (erreur = lesEmployes.testChamp(champs[i].getText(), employe.getInfos(i).getType(),
						unEmploye)) != 0) {
					if (employe.getInfos(i).getType() == ID) {
						mettreAjour();
						employe.getInfos(i).setValeur(lesEmployes.formaterId("1"));
						flag = true;

					} else {
						flag = false;
						champs[i].setCouleur(false);
						fenetre.afficher(erreur, null);
					}
				}
			}
			if (flag) {
				switch (code) {
				// Modification d'un employé
				case FO_MODIF:
					employe.getInfos(DATED).setValeur(formDate.formatDate(unEmploye.getInfos(DATED).getValeur()));
					employe.getInfos(DATEE).setValeur(formDate.formatDate(unEmploye.getInfos(DATEE).getValeur()));
					int[] codeModif = lesEmployes.comparerEmploye(unEmploye, employe);
					if (codeModif.length > 0 && FactMessage.fabriqueMessage(FO_CONFIRM_M) == 0) {
						if (Requetes.modifierEmploye(employe, codeModif) == 1) {
							fenetre.afficher(FO_MESSAGE_MO, employe);
							lesEmployes.ajouterEmploye(employe);
							principale.actualiserListeEmpActif(lesEmployes.getListe());
							principale.actualiserListeEmpInactif(lesEmployes.getListeI());
							unEmploye = employe.copie();
						} else {
							fenetre.afficher(FO_ERREUR_MO, employe);
						}
					}
					break;
				// Ajout d'un employé
					
				case FO_AJOUT:
					if (Requetes.ajouterEmploye(employe) == 1) {
						lesEmployes.ajouterEmploye(employe);
						principale.actualiserListeEmpActif(lesEmployes.getListe());
						principale.actualiserListeEmpInactif(lesEmployes.getListeI());
						fenetre.afficher(FO_MESSAGE_A, employe);

					} else {
						fenetre.afficher(FO_ERREUR_AJ, employe);
					}
					break;
				}
			}
			break;
		}
	}
}