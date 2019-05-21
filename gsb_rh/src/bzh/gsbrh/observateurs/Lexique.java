package bzh.gsbrh.observateurs;

import java.awt.Color;

/**
 * Dictionnaire des constantes de l'application
 * 
 * @author Alexandra Fricot
 * @version 1.0
 */
public interface Lexique {

	/**
	 * Couleur des bordures des liste d'employé
	 */
	final public static Color COLOR_BORDER = new Color(50, 90, 230);

	/**
	 * Couleur d'arrière plan
	 */
	final public static Color COLOR_BACKGROUNG = new Color(194, 224, 242);

	/**
	 * Couleur du bouton valider
	 */
	final public static Color COLOR_BOUTON_VALIDER = new Color(125, 145, 183);

	/**
	 * Couleur des zones de texte valide
	 */
	final public static Color COLOR_TEXT_VALIDE = new Color(100, 200, 10);

	/**
	 * Couleur des zones de texte invalide
	 */
	final public static Color COLOR_TEXT_IVALIDE = new Color(255, 80, 80);

	/**
	 * Couleur par defaut des zones de texte
	 */
	final public static Color COLOR_TEXT_WHITE = Color.WHITE;

	/**
	 * Couleur des zones de texte verouillé
	 */
	final public static Color COLOR_TEXT_NULL = null;

	// Texte de l'application

	/**
	 * Bouton : Se connecter
	 */
	final public static String BOUTON_CONNE = "Se connecter";

	/**
	 * Bouton : Modifier
	 */
	final public static String BOUTON_MODIF = "Modifier";

	/**
	 * Bouton : Programmer depart
	 */
	final public static String BOUTON_SUPPR = "Programmer départ";

	/**
	 * Bouton : Retour
	 */
	final public static String BOUTON_RETOUR = "Retour";

	/**
	 * Bouton : Réinitialiser
	 */
	final public static String BOUTON_REINIT = "Réinitialiser";

	/**
	 * Bouton : Ajouter
	 */
	final public static String BOUTON_AJOUT = "Ajouter";

	/**
	 * Bouton : Valider
	 */
	final public static String BOUTON_VALIDER = "Valider";

	/**
	 * Bouton : Réintégrer
	 */
	final public static String BOUTON_DEPROG = "Réintégrer";

	/**
	 * Bouton : Se deconnecter
	 */
	final public static String BOUTON_DECO = "Se déconnecter";

	/**
	 * Bouton : Générer mot de passe
	 */
	final public static String BOUTON_GENRER = "Générer mot de passe";

	/**
	 * Bouton : Aujourd'hui
	 */
	final public static String BOUTON_DATE = "Aujourd'hui";

	/**
	 * Titre de fenetre : GSB RH :
	 */
	final public static String APPLI_TITRE = "GSB RH : ";

	/**
	 * Titre de fenetre : Connexion utilisateur
	 */
	final public static String FE_TITRE_CONNE = "Connexion utilisateur";

	/**
	 * Titre de fenetre : Modifier un employé
	 */
	final public static String FE_TITRE_MODIF = "Modifier un employé";

	/**
	 * Titre de fenetre : Ajouter un employé
	 */
	final public static String FE_TITRE_AJOUT = "Ajouter un employé";

	/**
	 * Titre de fenetre : Liste des employés
	 */
	final public static String FE_TITRE_LISTE = "Liste des employés";

	/**
	 * Titre de fenetre : Programmation d'une date
	 */
	final public static String PP_TITRE_DATE = "Programmation d'une date";

	/**
	 * Titre d'onglet : Employés actifs
	 */
	final public static String ONGLET_1 = "Employés actifs";

	/**
	 * Titre d'onglet : Anciens employés
	 */
	final public static String ONGLET_2 = "Anciens employés";

	/**
	 * Titre de message d'erreur !
	 */
	final public static String M_TITRE_E = "Erreur !";

	/**
	 * Titre de message de confirmation ?
	 */
	final public static String M_TITRE_C = "Confirmation ?";

	/**
	 * Titre de message de information.
	 */
	final public static String M_TITRE_M = "Message.";

	/**
	 * Messages : Connecté en tant que :
	 */
	final public static String M_ACCUEIL = "Connecté en tant que :";

	/**
	 * Messages : Confirmer la date de fin de mission de l'employé ?
	 */
	final public static String M_CONFIRM_S = "Confirmer la date de fin de mission de l'employé ?";

	/**
	 * Messages : Confirmer les modifications de l'employé ?
	 */
	final public static String M_CONFIRM_M = "Confirmer les modifications de l'employé ?";

	/**
	 * Messages : En quittant l'application, vous serez déconnecté.\n Continuer ?
	 */
	final public static String M_CONFIRM_F = "En quittant l'application, vous serez déconnecté.\n Continuer ?";

	/**
	 * Messages : Confirmer la programmation de la date de départ de l'employé ?
	 */
	final public static Object M_CONFIRM_MODIFDATE = "Confirmer la programmation de la date de départ de l'employé ?";

	/**
	 * Messages : Confirmer la réintégration de l'employé ?
	 */
	final public static Object M_CONFIRM_REINTEGRE = "Confirmer la réintégration de l'employé ?";

	/**
	 * Messages : Programmer date de depart :
	 */
	final public static String M_PROGRAM_DATED = "Programmer date de départ :";

	/**
	 * Messages : Employe ajouté avec succès.
	 */
	final public static String M_MESSAGE_A = "Employé ajouté avec succès.";

	/**
	 * Messages : Employe retiré avec succès.
	 */
	final public static String M_MESSAGE_S = "Employé retiré avec succès.";

	/**
	 * Messages : Employe modifié avec succès.
	 */
	final public static String M_MESSAGE_M = "Employé modifié avec succès.";

	/**
	 * Messages : Connecté avec succès.
	 */
	final public static String M_MESSAGE_C = "Connecté avec succès.";

	/**
	 * Messages : Erreur de connexion.\nIdentifiant ou mot de passe invalide.
	 */
	final public static String M_ERREUR_CO = "Erreur de connexion.\nIdentifiant ou mot de passe invalide.";

	/**
	 * Messages : Impossible d'établir une connexion avec la base de données.
	 */
	final public static String M_ERREUR_COBDD = "Impossible d'établir une connexion avec la base de données.";

	/**
	 * Messages : Vous n'êtes pas autorisé a vous connecter ici.
	 */
	final public static Object M_ERREUR_SE = "Vous n'êtes pas autorisé à vous connecter ici.";

	/**
	 * Messages : Erreur dans la modification de l'employé.
	 */
	final public static String M_ERREUR_MO = "Erreur dans la modification de l'employé.";

	/**
	 * Messages : Erreur dans l'enregistrement de l'employé.
	 */
	final public static String M_ERREUR_AJ = "Erreur dans l'enregistrement de l'employé.";

	/**
	 * Messages : Date invalide !
	 */
	final public static String M_ERREUR_DA = "Date invalide !";

	/**
	 * Messages : Champ id invalide.
	 */

	final public static String M_ERREUR_ID = "Champ code invalide.";

	/**
	 * Messages : Champ nom invalide.
	 */
	final public static String M_ERREUR_NOM = "Champ nom invalide.";

	/**
	 * Messages : Champ prenom invalide.
	 */
	final public static String M_ERREUR_PRE = "Champ prénom invalide.";

	/**
	 * Messages : Champ identifiant invalide.
	 */
	final public static String M_ERREUR_LOG = "Champ identifiant invalide.";

	/**
	 * Messages : Champ mot de passe invalide.
	 */
	final public static String M_ERREUR_MDP = "Champ mot de passe invalide.";

	/**
	 * Messages : Champ adresse invalide.
	 */
	final public static String M_ERREUR_ADR = "Champ adresse invalide.";

	/**
	 * Messages : Champ code postal invalide.
	 */
	final public static String M_ERREUR_CP = "Champ code postal invalide.";

	/**
	 * Messages : Champ mail invalide.
	 */
	final public static Object M_ERREUR_MAIL = "Champ mail invalide.";

	/**
	 * Messages : Champ telephone invalide.
	 */
	final public static Object M_ERREUR_TEL = "Champ téléphone invalide.";

	/**
	 * Messages : Champ ville invalide.
	 */
	final public static String M_ERREUR_VILLE = "Champ ville invalide.";

	/**
	 * Messages : Date invalide.
	 */
	final public static String M_ERREUR_DATE = "Date invalide.";

	/**
	 * Message : Connexion interrompue
	 */
	public static final String M_ERREUR_DECO = "Connexion a l'application interrompue";

	/**
	 * Texte de libelle du champ de connexion :Identifiant :
	 */
	final public static String LOGINCO = "Identifiant :";

	/**
	 * Texte de libelle du champ de connexion : Mot de passe
	 */
	final public static String MDPCO = " Mot de passe :";

	/**
	 * Texte de libelle du champ et de colonne : Code
	 */
	final public static String ID = "Code";

	/**
	 * Texte de libelle du champ et de colonne : Nom
	 */
	final public static String NOM = "Nom";

	/**
	 * Texte de libelle du champ et de colonne : Prenom
	 */
	final public static String PRENOM = "Prénom";

	/**
	 * Texte de libelle du champ et de colonne : Identifiant
	 */
	final public static String LOGIN = "Identifiant";

	/**
	 * Texte de libelle du champ et de colonne : Mot de passe
	 */
	final public static String MDP = "Mot de passe";

	/**
	 * Texte de libelle du champ et de colonne : Adresse
	 */
	final public static String ADR = "Adresse";

	/**
	 * Texte de libelle du champ et de colonne : Code Postal
	 */
	final public static String CP = "Code Postal";

	/**
	 * Texte de libelle du champ et de colonne : Ville
	 */
	final public static String VILLE = "Ville";

	/**
	 * Texte de libelle du champ et de colonne : Mail
	 */
	final public static String MAIL = "Mail";

	/**
	 * Texte de libelle du champ et de colonne : Telephone
	 */
	final public static String TEL = "Téléphone";

	/**
	 * Texte de libelle du champ et de colonne : Date d'embauche
	 */
	final public static String DATEE = "Date d'embauche";

	/**
	 * Texte de libelle du champ et de colonne : Service
	 */
	final public static String SERVICE = "Statut";

	/**
	 * Texte de libelle du champ et de colonne : Date depart
	 */
	final public static String DATED = "Date départ";

	/**
	 * Texte de libelle de colonne : Programmer date
	 */
	final public static String PP_DATE_TX = "Programmer date";

	/**
	 * Texte de libelle de colonne : Modifier
	 */
	final public static String COLMODIFIE = "Modifier";

	/**
	 * Code d'ajout de l'employé envoyé à la liste
	 */
	final public static int LI_VALIDE_AJ = 2;

	/**
	 * identifiant du type de champ texte : CHAMP_TEXT
	 */
	final public static int CHAMP_TEXT = 0;

	/**
	 * identifiant du type de champ password : CHAMP_PASS
	 */
	final public static int CHAMP_PASS = 1;

	/**
	 * identifiant du type de champ liste déroulante : CHAMP_COMB
	 */
	final public static int CHAMP_COMB = 2;

	/**
	 * identifiant du type de champ Id : CHAMP_ID
	 */
	final public static int CHAMP_ID = 3;

	/**
	 * identifiant du type de champ date : CHAMP_DATE
	 */
	final public static int CHAMP_DATE = 4;

	/**
	 * identifiant du type de champ code postal : CHAMP_CP
	 */
	final public static int CHAMP_CP = 5;

	/**
	 * identifiant du type de champ téléphon : CHAMP_TEL
	 */
	final public static int CHAMP_TEL = 6;

	/**
	 * identifiant du type de champ mail : CHAMP_MAIL
	 */
	final public static int CHAMP_MAIL = 7;

	/**
	 * ID du bouton de déconnexion
	 */
	final public static int AP_DECO = 666;

	/**
	 * ID du bouton de connexion
	 */
	final public static int ID_CO = 10;

	/**
	 * ID du bouton ajout de liste des employés
	 */
	final public static int LI_AJ = 11;

	/**
	 * ID du bouton modifer de liste des employés
	 */
	final public static int LI_MO = 12;

	/**
	 * ID du bouton programmer date de liste des employés
	 */
	final public static int LI_PR = 13;

	/**
	 * ID du bouton retour du formulaire
	 */
	final public static int FO_BA = 14;

	/**
	 * ID du bouton reinitialiser du formulaire
	 */
	final public static int FO_RE = 15;

	/**
	 * ID du bouton ajouter du formulaire
	 */
	final public static int FO_AJ = 16;

	/**
	 * ID du bouton modifier du formulaire
	 */
	final public static int FO_MO = 17;

	/**
	 * ID du bouton valider du PopUp date
	 */
	final public static int PP_DATE_VA = 18;

	/**
	 * ID du bouton retour du PopUp date
	 */
	final public static int PP_DATE_BA = 19;

	/**
	 * ID du bouton réintégré du PopUp date
	 */
	final public static int PP_DATE_RE = 20;

	/**
	 * Identifiant de la modif de l'id d'un employé
	 */
	final public static int M_ID = 0;

	/**
	 * Identifiant de la modif du nom d'un employé
	 */
	final public static int M_NOM = 1;

	/**
	 * Identifiant de la modif du prénom d'un employé
	 */
	final public static int M_PRENOM = 2;

	/**
	 * Identifiant de la modif du login d'un employé
	 */
	final public static int M_LOGIN = 3;

	/**
	 * Identifiant de la modif de l'adresse d'un employé
	 */
	final public static int M_ADRESSE = 4;

	/**
	 * Identifiant de la modif du code postal d'un employé
	 */
	final public static int M_CP = 5;

	/**
	 * Identifiant de la modif de la ville d'un employé
	 */
	final public static int M_VILLE = 6;

	/**
	 * Identifiant de la modif de l'adresse mail d'un employé
	 */
	final public static int M_MAIL = 7;

	/**
	 * Identifiant de la modif du téléphone d'un employé
	 */
	final public static int M_TEL = 8;

	/**
	 * Identifiant de la modif de la date d'embauche d'un employé
	 */
	final public static int M_DATEE = 9;

	/**
	 * Identifiant de la modif du service d'un employé
	 */
	final public static int M_SERVICEID = 10;

	/**
	 * Identifiant de la modif de la date de départ d'un employé
	 */
	final public static int M_DATED = 11;

	/**
	 * Identifiant de la modif du mot de passe d'un employé
	 */
	final public static int M_MDP = 12;

	/**
	 * Identifiant d'un bouton de deconnexion dans la fabrique de boutons
	 */
	final public static int BO_DECONNECT = 999;

	/**
	 * Identifiant d'un bouton de connexion dans la fabrique de boutons
	 */
	final public static int BO_CONNECT = 50;

	/**
	 * Identifiant d'un bouton d'ajout dans la fabrique de boutons
	 */
	final public static int BO_AJOUT = 51;

	/**
	 * Identifiant d'un bouton de modification dans la fabrique de boutons
	 */
	final public static int BO_MODIF = 52;

	/**
	 * Identifiant d'un bouton de reinitialisation dans la fabrique de boutons
	 */
	final public static int BO_REINIT = 53;

	/**
	 * Identifiant d'un bouton de suppression dans la fabrique de boutons
	 */
	final public static int BO_SUPPR = 54;

	/**
	 * Identifiant d'un bouton de retour dans la fabrique de boutons
	 */
	final public static int BO_RETOUR = 55;

	/**
	 * Identifiant d'un bouton programmer dans la fabrique de boutons
	 */
	final public static int BO_VALIDER = 56;

	/**
	 * Identifiant d'un bouton pour les date dans la fabrique de boutons
	 */
	final public static int BO_DATE_NOW = 57;

	/**
	 * Identifiant d'un bouton pour generer un mot de passe dans la fabrique de
	 * boutons
	 */
	final public static int BO_GENE_PASS = 58;

	/**
	 * Identifiant d'une fenêtre d'identification dans la fabrique de fenêtre
	 */
	final public static int FE_IDENT = 0;

	/**
	 * Identifiant d'une fenêtre de liste des employés dans la fabrique de fenêtre
	 */
	final public static int FE_LISTE = 1;

	/**
	 * Identifiant d'une fenêtre de modification d'un employé dans la fabrique de
	 * fenêtre
	 */
	final public static int FE_MODIF = 2;

	/**
	 * Identifiant d'une fenêtre d'ajout d'un employé dans la fabrique de fenêtre
	 */
	final public static int FE_AJOUT = 3;

	/**
	 * Code de la notification d'ajout d'un employé
	 */
	final public static int FO_AJOUT = 0;

	/**
	 * Code de la notification de modification d'un employé
	 */
	final public static int FO_MODIF = 1;

	/**
	 * Identifiant d'un message de confirmation d'ajout de la date de départ d'un
	 * employé pour la fabrique de messages
	 */
	final public static int LI_CONFIRM_S = 31;

	/**
	 * Identifiant d'un message de confirmation de modification d'un employé pour la
	 * fabrique de messages
	 */
	final public static int FO_CONFIRM_M = 5;

	/**
	 * Identifiant d'un message de confirmation de fermeture de l'appli pour la
	 * fabrique de messages
	 */
	final public static int AP_CONFIRM_Q = 40;

	/**
	 * Identifiant d'un message de pour la fabrique de messages
	 */
	final public static int LI_CONFIRM_MODIFDATE = 8;

	/**
	 * Identifiant d'un message de pour la fabrique de messages
	 */
	final public static int LI_CONFIRM_REINTEGRE = 9;

	/**
	 * Identifiant d'un message d'information d'ajout d'un employé pour la fabrique
	 * de messages
	 */
	final public static int FO_MESSAGE_A = 4;

	/**
	 * Identifiant d'un messages d'information d'ajout de la date de fin de mission
	 * d'un employé pour la fabrique de message
	 */
	final public static int LI_MESSAGE_S = 30;

	/**
	 * Identifiant d'un message d'information de modification d'un employé pour la
	 * fabrique de messages
	 */
	final public static int FO_MESSAGE_MO = 3;

	/**
	 * Identifiant d'un message d'information de connexion pour la fabrique de
	 * message
	 */
	final public static int ID_MESSAGE_CO = 21;

	/**
	 * Identifiant d'un message d'erreur de saisie lors de la connexion pour la
	 * fabrique de message
	 */
	final public static int ID_ERREUR_CO = 20;

	/**
	 * Identifiant d'un message d'erreur de connexion à la BDD
	 */
	final public static int ID_ERREUR_COBDD = 24;

	/**
	 * Identifiant d'un message d'erreur de refus de connexion pour la fabrique de
	 * message
	 */
	final public static int ID_ERREUR_SE = 1;

	/**
	 * Identifiant d'un message d'erreur d'ajout d'un employé pour la fabrique de
	 * messages
	 */
	final public static int FO_ERREUR_AJ = 7;

	/**
	 * Identifiant d'un message d'erreur de modification d'un employé pour la
	 * fabrique de messages
	 */
	final public static int FO_ERREUR_MO = 2;

	/**
	 * Identifiant d'un message d'erreur dans l'ajout de la date départ d'un employé
	 * pour la fabrique de messages
	 */
	final public static int LI_ERREUR_MO = 6;

	/**
	 * Identifiant d'un message de deconnexion forcé de l'utilisateur pour la
	 * fabrique de messages
	 */
	final public static int AP_DECO_FORCE = 0;

	/**
	 * Identifiant d'un message d'erreur sur le champ id du formulaire pour la
	 * fabrique de messages
	 */
	final public static int FO_ERREUR_ID = 10;

	/**
	 * Identifiant d'un message d'erreur sur le champ nom du formulaire pour la
	 * fabrique de messages
	 */
	final public static int FO_ERREUR_NOM = 11;

	/**
	 * Identifiant d'un message d'erreur sur le champ prenom du formulaire pour la
	 * fabrique de messages
	 */
	final public static int FO_ERREUR_PRE = 12;

	/**
	 * Identifiant d'un message d'erreur sur le champ login du formulaire pour la
	 * fabrique de messages
	 */
	final public static int FO_ERREUR_LOG = 13;

	/**
	 * Identifiant d'un message d'erreur sur le champ mot de passe du formulaire
	 * pour la fabrique de messages
	 */
	final public static int FO_ERREUR_MDP = 14;

	/**
	 * Identifiant d'un message d'erreur sur le champ adresse du formulaire pour la
	 * fabrique de messages
	 */
	final public static int FO_ERREUR_ADR = 15;

	/**
	 * Identifiant d'un message d'erreur sur le champ code postal du formulaire pour
	 * la fabrique de messages
	 */
	final public static int FO_ERREUR_CP = 16;

	/**
	 * Identifiant d'un message d'erreur sur le champ ville du formulaire pour la
	 * fabrique de messages
	 */
	final public static int FO_ERREUR_VILLE = 17;

	/**
	 * Identifiant d'un message d'erreur sur le champ mail du formulaire pour la
	 * fabrique de messages
	 */
	final public static int FO_ERREUR_MAIL = 18;

	/**
	 * Identifiant d'un message d'erreur sur le champ téléphone du formulaire pour
	 * la fabrique de messages
	 */
	final public static int FO_ERREUR_TEL = 22;

	/**
	 * Identifiant d'un message d'erreur sur le champ date du formulaire pour la
	 * fabrique de messages
	 */
	final public static int FO_ERREUR_DATE = 23;

	/**
	 * Code de fermeture d'un formulaire
	 */
	final public static int FO_FERMETURE = 5;

	/**
	 * Codes de l'action de connexion
	 */
	/**
	 * Code de lancement du test sur login et mdp
	 */
	final public static int CONNEXION = 1;

	/**
	 * Code de validation de la connexion, lance la suite du programme
	 */
	final public static int ID_VALIDE_CO = 7;

	/**
	 * Code de l'action de generation de login dans le champ du formulaire
	 */
	final public static int GENERE_LOG = 2;

	/**
	 * Code de l'action de generation d'id dans le champ du formulaire
	 */
	final public static int FO_ATTRID = 10;

	/**
	 * Code de l'action d'attribution des services dans la liste du formulaire
	 */
	final public static int FO_ATTRCOMB = 4;

	/**
	 * Code de teste de coloration du login dans le formulaire
	 */
	final public static int COLOR_LOG = 1;

	/**
	 * Code de teste de coloration du mot de passe dans le formulaire
	 */
	final public static int COLOR_MDP = 2;

	/**
	 * Code de teste de coloration de l'id dans le formulaire
	 */
	final public static int COLOR_ID = 3;

	/**
	 * Code de teste de coloration des autres types de champ dans le formulaire
	 */
	final public static int CHAMP_TEST = 0;

	/**
	 * Extension de l'adrese email des employés
	 */
	final public static String MAIL_DOMAINE = "@swiss-galaxy.com";

}
