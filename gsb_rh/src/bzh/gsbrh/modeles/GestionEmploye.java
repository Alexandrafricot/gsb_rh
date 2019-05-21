package bzh.gsbrh.modeles;

import java.util.ArrayList;
import java.util.regex.Pattern;

import bzh.gsbrh.observateurs.Lexique;

/**
 * Permet de gerer la liste des employés.
 * 
 * Reduits les interactions avec la base de données, stock tout les employés,
 * créer et manipule des tableaux quand nécéssaire.
 * 
 * @author Alexandra Fricot
 * @version 1.0
 */
public class GestionEmploye implements Lexique {

	/**
	 * Chaine de caractère représentant les minuscules de l'alphabet pour la
	 * génération des id.
	 */
	private static final String min = "abcdefghijklmnopqrstuvwxyz";

	/**
	 * Indice dans la chaine de caractère des miniscule pour la génération des id
	 */
	private int a = 0;

	/**
	 * Collection des employés.
	 */
	private ArrayList<Employe> listeEmployes;

	/**
	 * Tableau des employés actifs.
	 */
	private Object[][] liste;

	/**
	 * Tableau des employés inacifs.
	 */
	private Object[][] listeI;

	/**
	 * Entêtes des colonnes du tabeau a afficher.
	 */
	private Entete entete = new Entete();

	/**
	 * Tableaux des libellés des services.
	 */
	private String lesServices[];

	/**
	 * Compteur des employés actifs.
	 */
	private int rowActif = 0;

	/**
	 * Compteur des employés inactifs.
	 */
	private int rowInactif = 0;

	/**
	 * indice sur lequel réaliser le tri des tableaux des employés.
	 */
	private int codeComp = entete.indiceDEntete(NOM);

	/**
	 * Booléen qui inverse ou non le sens du tri.
	 */
	private boolean inverse = false;

	/**
	 * Unique instance de GestionEmploye
	 */
	private static GestionEmploye moi = null;

	/**
	 * Compte les employé actif et inactif, puis converti la collection.
	 */
	private void importerListe() {
		rowActif = rowInactif = 0;
		for (Employe unEmploye : listeEmployes) {
			if (formDate.dateDepasse(unEmploye.getInfos(DATED).getValeur())
					&& !unEmploye.getInfos(DATED).getValeur().isEmpty())
				rowInactif++;
			else
				rowActif++;
		}
		genererListe();
	}

	/**
	 * Constructeur par defaut privée
	 */
	private GestionEmploye() {

	}

	/**
	 * Constructeur privé surchargé.
	 *
	 * @param lesEmployes
	 *            Collection d'employé.
	 * @param services
	 *            Tableau des libellés des services.
	 */
	private GestionEmploye(ArrayList<Employe> lesEmployes, String[] services) {
		listeEmployes = lesEmployes;
		lesServices = services;
		importerListe();
	}

	/**
	 * Méthode singleton pour instancier l'unique instance de GestionEmploye
	 * 
	 * @param lesEmployes
	 *            Collection d'employé.
	 * @param services
	 *            Tableau des libellés des services.
	 * @return L'unique instance de GestionEmploye
	 */
	public static GestionEmploye gestionEmploye(ArrayList<Employe> lesEmployes, String[] services) {
		if (moi == null && lesEmployes != null) {
			moi = new GestionEmploye(lesEmployes, services);
		}
		return moi;
	}

	/**
	 * Met a jour la liste des employés
	 * 
	 * @param lesEmployes Nouvelle collection des employés
	 */
	public void setListe(ArrayList<Employe> lesEmployes) {
		if (lesEmployes != null)
			listeEmployes = lesEmployes;
		if (lesEmployes != null)
			importerListe();
	}

	/**
	 * Accesseur du nombre d'actif.
	 * 
	 * @return le nombre d'employé actif.
	 */
	public int getActif() {
		return rowActif;
	}

	/**
	 * Accesseur du nombre d'inactif.
	 * 
	 * @return le nombre d'employé inactif.
	 */
	public int getInactif() {
		return rowInactif;
	}

	/**
	 * Ecrit des données dans un tableau avec les informations passé en paramètre
	 * 
	 * @param listeEmployes
	 *            Liste d'employé à partir de laquel implémenter le tableau
	 * @param liste
	 *            Tableau dans lequel placé les informations d'un employé
	 * @param i
	 *            Indice du tableau en cours d'écriture
	 * @param t
	 *            Indice de l'employé dans la liste des employés
	 */
	public void genererListe(ArrayList<Employe> listeEmployes, Object[][] liste, int i, int t) {
		int u = 0;
		for (int j = 0; j < Information.getTypes().length; j++) {
			u = entete.indiceDEntete(listeEmployes.get(t).getInfos(j).getType());
			if (listeEmployes.get(t).getInfos(j).getType().equals(entete.getEntete()[u])) {
				if (listeEmployes.get(t).getInfos(j).getType().equals(SERVICE))
					liste[i][u] = lesServices[Integer.parseInt(listeEmployes.get(t).getInfos(j).getValeur())];
				else if (listeEmployes.get(t).getInfos(j).getType().equals(DATED)
						|| listeEmployes.get(t).getInfos(j).getType().equals(DATEE))
					liste[i][u] = formDate.dateAffichable(listeEmployes.get(t).getInfos(j).getValeur());
				else
					liste[i][u] = listeEmployes.get(t).getInfos(j).getValeur();
				u++;
			}
		}
	}

	/**
	 * Genère un tableau des employé actif et un des employé inactif.
	 */
	public void genererListe() {
		liste = new Object[rowActif][entete.getEntete().length];
		listeI = new Object[rowInactif][entete.getEntete().length];
		int i = 0;
		int a = 0;
		// Boucle qui génère les employés un à un
		for (int t = 0; t < listeEmployes.size(); t++) {
			if (formDate.dateDepasse(listeEmployes.get(t).getInfos(DATED).getValeur())
					&& !listeEmployes.get(t).getInfos(DATED).getValeur().isEmpty()) {
				genererListe(listeEmployes, listeI, i, t);
				i++;
			} else {
				genererListe(listeEmployes, liste, a, t);
				a++;
			}
		}
		triRapide(liste, codeComp, inverse);
		triRapide(listeI, codeComp, inverse);
	}

	/**
	 * Lance le tri d'un tableau à 2 dimension.
	 * 
	 * @param liste
	 *            Tableau a 2 dimension à trier.
	 * @param codeComp
	 *            Index du deuxième tableau par lequel trier l'ensemble.
	 * @param inverse
	 *            Booléen indiquant si le tri doit être croisant ou non.
	 */
	public void triRapide(Object[][] liste, int codeComp, boolean inverse) {
		int longueur = liste.length;
		triRapide(liste, 0, longueur - 1, codeComp, inverse);
	}

	/**
	 * Trieur surchargé recursif qui sépare le tableau pour le trier.
	 * 
	 * @param liste
	 *            Tableau a 2 dimension à trier.
	 * @param debut
	 *            Index de départ du tri.
	 * @param fin
	 *            Index de fin du tri.
	 * @param codeComp
	 *            Index du deuxième tableau par lequel trier l'ensemble.
	 * @param inverse
	 *            Booléen indiquant si le tri doit être croisant ou non.
	 */
	public void triRapide(Object[][] liste, int debut, int fin, int codeComp, boolean inverse) {
		if (debut < fin) {
			int positionPivot = partition(liste, debut, fin, codeComp, inverse);
			triRapide(liste, debut, positionPivot - 1, codeComp, inverse);
			triRapide(liste, positionPivot + 1, fin, codeComp, inverse);
		}
	}

	/**
	 * Effectue le tri à partir d'un élément.
	 * 
	 * @param liste
	 *            Tableau a 2 dimension à trier.
	 * @param debut
	 *            Index de départ du tri.
	 * @param fin
	 *            Index de fin du tri.
	 * @param codeComp
	 *            Index du deuxième tableau par lequel trier l'ensemble.
	 * @param inverse
	 *            Booléen indiquant si le tri doit être croisant ou non.
	 * @return L'index de la valeur la plus forte rencontré dans le test.
	 */
	public int partition(Object[][] liste, int debut, int fin, int codeComp, boolean inverse) {
		int compt = debut;
		Object pivot = ((String) liste[debut][codeComp]).toLowerCase();
		for (int i = debut + 1; i <= fin; i++) {
			if (inverse) {
				if ((((String) liste[i][codeComp]).toLowerCase()).compareTo((String) pivot) > 0) {
					compt++;
					echanger(liste, compt, i);
				}
			} else {
				if (-(((String) liste[i][codeComp]).toLowerCase()).compareTo((String) pivot) > 0) {
					compt++;
					echanger(liste, compt, i);
				}
			}
		}
		echanger(liste, debut, compt);
		return compt;
	}

	/**
	 * Echange la position de 2 ligne du tableau.
	 * 
	 * @param liste
	 *            Tableau sur lequel opérer l'échange.
	 * @param a
	 *            Index de la ligne première à remplacer.
	 * @param b
	 *            Index de la ligne seconde à remplacer.
	 */
	public void echanger(Object[][] liste, int a, int b) {
		Object[][] temp = new Object[1][entete.getEntete().length];
		temp[0] = liste[b];
		liste[b] = liste[a];
		liste[a] = temp[0];
	}

	/**
	 * Traite la modification ou l'ajout d'un employé
	 * 
	 * Si il à été modifié on vérifie si il est passé d'actif à inactif(ou
	 * l'invrse), dans ce cas on réédite les tableaux. Si il s'agit d'un nouvel
	 * employé, on l'ajoute à l'arraylist et on réédite les tableaux
	 * 
	 * @param unNouvelEmploye
	 *            L'employé à testé puis à ajouter ou modifier.
	 */
	public void ajouterEmploye(Employe unNouvelEmploye) {
		Employe employe = getEmploye(unNouvelEmploye.getInfos(ID).getValeur());
		if (employe != null) { // Si l'employé éxiste dans l'ArrayList
			int indice = 0; // Son indice dans l'un des 2 tableaux
			// Son index dans l'ArrayList
			int index = getEmployeIndex(unNouvelEmploye.getInfos(ID).getValeur());
			// Le programme cherche les modifs à effectué dans l'ArrayList
			int[] codesModif = comparerEmploye(unNouvelEmploye, listeEmployes.get(index));
			// Le programme test si l'ancienne date de départ est dépassé
			boolean avant = formDate.dateDepasse(listeEmployes.get(index).getInfos(DATED).getValeur());
			// Le programme test si la nouvelle date de départ est dépassé
			boolean apres = formDate.dateDepasse(unNouvelEmploye.getInfos(DATED).getValeur());
			// Le programme test qu'il n'y a pas besoin de changer de liste à l'employé
			if ((avant && apres) || (!avant && !apres)) {
				if (avant && apres) {
					// Si les dates de départ sont dépassé le programme met à jour l'employé dans la
					// liste des inactifs
					indice = getIndexListe(unNouvelEmploye.getInfos(ID).getValeur(), listeI);
					// L'employé n'a pas changé de liste, il reste inactif
					modifier(unNouvelEmploye, codesModif, listeI, index, indice);
				} else {
					// Sinon, les dates ne sont pas dépassé, le programme met à jour l'employé dans
					// la liste des actifs
					indice = getIndexListe(unNouvelEmploye.getInfos(ID).getValeur(), liste);
					// L'employé n'a pas changé de liste, il reste actif
					modifier(unNouvelEmploye, codesModif, liste, index, indice);
				}
			} else {
				listeEmployes.set(index, unNouvelEmploye);
				if (avant && !apres) {
					// Si l'employé est passé d'inactif à actif on le met à jour dans l'ArrayListe
					// L'employé a changé de liste, il est maintenant actif
					rowActif++;
					rowInactif--;
				} else {
					// Si l'employé est passé d'actif à inactif on le met à jour dans l'ArrayListe
					// L'employé a changé de liste, il est maintenant inactif
					rowActif--;
					rowInactif++;
				}
				genererListe();
			}
		} else {
			// est un nouvel employé, il est maintenant actif
			listeEmployes.add(unNouvelEmploye);
			rowActif++;
			genererListe();
		}
	}

	/**
	 * Réalise la modification d'un employé au sein de l'ArrayList et du tableau
	 * dans lequel il existe
	 * 
	 * @param unNouvelEmploye
	 *            L'employé à modifier avec les données qu'il possède
	 * @param codesModif
	 *            Code des modification à apporté
	 * @param uneListe
	 *            Le tableau dans lequel existe déjà l'employé, sur lequel on
	 *            apportera les modifications.
	 * @param index
	 *            Le numéro de l'employé dans la liste des employés
	 * @param indice
	 *            L'indice de l'employé dans le tableau à modifier
	 */
	public void modifier(Employe unNouvelEmploye, int[] codesModif, Object[][] uneListe, int index, int indice) {
		for (int i = 0; i < codesModif.length; i++) {
			for (int j = 0; j < Information.getCodeModif().length; j++) {
				if (codesModif[i] == Information.getCodeModif(j)) {
					listeEmployes.get(index).getInfos(j).setValeur(unNouvelEmploye.getInfos(j).getValeur());
					int u = entete.indiceDEntete(listeEmployes.get(index).getInfos(j).getType());
					if (!listeEmployes.get(index).getInfos(j).getType().equals(MDP)
							&& entete.estDansEntete(listeEmployes.get(index).getInfos(j).getType())) {
						if (listeEmployes.get(index).getInfos(j).getType().equals(SERVICE))
							uneListe[indice][u] = lesServices[Integer
									.parseInt(unNouvelEmploye.getInfos(j).getValeur())];
						else if (listeEmployes.get(index).getInfos(j).getType().equals(DATEE)
								|| listeEmployes.get(index).getInfos(j).getType().equals(DATED))
							uneListe[indice][u] = formDate.dateAffichable(unNouvelEmploye.getInfos(j).getValeur());
						else
							uneListe[indice][u] = unNouvelEmploye.getInfos(j).getValeur();
					}
				}
			}
		}
	}

	/**
	 * Accesseur du tableau des entête de la liste des employé.
	 * 
	 * @return Le tableau des entête de la liste des employé.
	 */
	public Entete getEntete() {
		return entete;
	}

	/**
	 * Accesseur du tableau des employés inactif.
	 * 
	 * @return Le tableau des employés inactif.
	 */
	public Object[][] getListeI() {
		return listeI;
	}

	/**
	 * Accesseur du tableau des employés actif.
	 * 
	 * @return Le tableau des employés actif.
	 */
	public Object[][] getListe() {
		return liste;
	}

	/**
	 * Accesseur du tableau des services.
	 * 
	 * @return Le tableau des services.
	 */
	public String[] listerServices() {
		return lesServices;

	}

	/**
	 * Accesseur d'un employé contenu dans la liste des employé.
	 * 
	 * @param id
	 *            Code de l'employé recherché.
	 * @return L'employé recherché ou null si il n'existe pas.
	 */
	public Employe getEmploye(String id) {
		for (Employe unEmploye : listeEmployes) {
			if (unEmploye.getInfos(ID).getValeur().equals(id))
				return unEmploye;
		}
		return null;
	}

	/**
	 * Trouve l'index de l'employé recherché dans la liste des employés.
	 * 
	 * @param id
	 *            Code de l'employé rechercher.
	 * @return L'indice dans la liste de l'employé recherché.
	 */
	public int getEmployeIndex(String id) {
		for (int i = 0; i < listeEmployes.size(); i++) {
			if (listeEmployes.get(i).getInfos(ID).getValeur().equals(id))
				return i;
		}
		return -1;
	}

	/**
	 * Trouve l'indice de l'employé recherché dans le tableau passé en paramètre.
	 * 
	 * @param id
	 *            Code de l'employé recherché.
	 * @param liste
	 *            Liste sur laquel rechercher l'employé.
	 * @return L'indice de l'employé dans le tableau passé en paramètre.
	 */
	public int getIndexListe(String id, Object[][] liste) {
		int size = liste.length;
		int index = 0;
		for (int i = 0; i < size; i++) {
			if (liste[i][0].equals(id)) {
				index = i;
			}
		}
		return index;
	}

	/**
	 * Compare les informations de 2 employé.
	 * 
	 * @param employeA
	 *            Un employé à comparer.
	 * @param employeB
	 *            Un employé à comparer.
	 * @return Un tableau des code des modifications.
	 */
	public int[] comparerEmploye(Employe employeA, Employe employeB) {
		int[] codeModif = new int[13];
		int i = 0;
		int row = 0;
		for (i = 0; i < codeModif.length; i++) {
			codeModif[i] = -1;
		}
		for (i = 0; i < Information.getCodeModif().length; i++) {
			if (employeA.getInfos(i).getValeur() == null)
				employeA.getInfos(i).setValeur("");
			if (employeA.getInfos(i).getValeur().compareTo(employeB.getInfos(i).getValeur()) != 0) {
				codeModif[i] = Information.getCodeModif(i);
				row++;
			}
		}
		int[] retour = new int[row];
		row = 0;
		for (i = 0; i < codeModif.length; i++) {
			if (codeModif[i] != -1) {
				retour[row] = codeModif[i];
				row++;
			}
		}
		return retour;
	}

	/**
	 * Vérifie l'identifiant.
	 * 
	 * Test si l'identifiant respecte bien la taille demandé puis qu'il n'existe pas
	 * déjà ou appartient déjà à l'employé.
	 * 
	 * @param id
	 *            Code identifiant à vérifier.
	 * @param unEmploye
	 *            L'employé à qui appartiendra l'identifiant.
	 * @return Un booléen qui confirme ou non que le code identifiant est valide.
	 */
	public boolean verifId(String id, Employe unEmploye) {
		boolean flag = false;
		if ((verif(ID, id, unEmploye)) && (!idExiste(id) || id.equals(unEmploye.getInfos(ID).getValeur()))) {
			flag = true;
		}
		return flag;
	}

	/**
	 * Verifie dans la liste des employé si l'id passé en paramètre existe.
	 * 
	 * @param id
	 *            Code à rechercher.
	 * @return Un booléen qui confirme ou non que l'id existe dans la liste des
	 *         employés.
	 */
	public boolean idExiste(String id) {
		boolean flag = false;
		if (employeExiste(id, M_ID)) {
			flag = true;
		}
		return flag;
	}

	/**
	 * Formate un code identifiant unique valide.
	 * 
	 * @param id
	 *            Base sur laquel travailler.
	 * @return Le code identifiant formaté, unique et valide.
	 */
	public String formaterId(String id) {
		if (idExiste(min.charAt(0) + id))
			id = min.charAt(a) + formaterId(id, 1);
		else
			id = min.charAt(0) + id;
		return id;
	}

	/**
	 * Formate un code identifiant unique valide.
	 * 
	 * @param id
	 *            Base sur laquel travailler.
	 * @param i
	 *            Un élément à ajouter à l'id existant.
	 * @return Le code identifiant formaté, unique et valide.
	 */
	public String formaterId(String id, int i) {
		if (i > 999) {
			i = 1;
			a += 1;
		}
		String temp = Integer.toString(Integer.parseInt(id) + i);
		i += 1;
		if (idExiste(min.charAt(a) + temp))
			temp = formaterId(id, i);
		return temp;
	}

	/**
	 * Vérifie si le login est conforme.
	 * 
	 * @param login
	 *            Login à vérifier.
	 * @return Un booléen qui confirme ou non que le login est conforme.
	 */
	public boolean verifLogin(String login) {
		boolean flag = false;
		if ((verif(LOGIN, login, null)) && (!logExiste(login))) {
			flag = true;
		}
		return flag;
	}

	/**
	 * Vérifie si le login est conforme ou appartient déjà à l'employé.
	 * 
	 * @param login
	 *            Login à vérifier.
	 * @param unEmploye
	 *            Employé à qui pourrait appartenir le login passé en paramètre.
	 * @return Un booléen qui confirme ou non que le login est conforme ou
	 *         appartient bien à l'employé.
	 */
	public boolean verifLogin(String login, Employe unEmploye) {
		boolean flag = false;
		if ((verif(LOGIN, login, unEmploye))
				&& (!logExiste(login) || login.equals(unEmploye.getInfos(LOGIN).getValeur()))) {
			flag = true;
		}
		return flag;
	}

	/**
	 * Verifier si le login passé en paramètre existe dans la liste des employés.
	 * 
	 * @param log
	 *            Login à rechercher.
	 * @return Un booléen qui confirme ou non que le login existe dans la liste des
	 *         employés.
	 */
	public boolean logExiste(String log) {
		boolean flag = false;
		log = StringOperation.sansAccent(log.toLowerCase());
		if (employeExiste(log, M_LOGIN)) {
			flag = true;
		}
		return flag;
	}

	/**
	 * Formate un login unique et valide.
	 * 
	 * @param log
	 *            Login à formater.
	 * @return Le login formaté, unique et valide.
	 */
	public String formaterLog(String log) {
		log = StringOperation.sansAccent(log.toLowerCase());
		if (logExiste(log) || !verifLogin(log))
			log = formaterLog(log, 1);
		return log;
	}

	/**
	 * Formate un login unique et valide en y ajoutant le numéro passé en paramètre.
	 * 
	 * @param log
	 *            Login à formater.
	 * @param i
	 *            Un élément à ajouter au login
	 * @return Le login formaté, unique et valide.
	 */
	public String formaterLog(String log, int i) {
		String temp = (log + i);
		i += 1;
		if (logExiste(temp) || !verifLogin(temp))
			temp = formaterLog(log, i);
		return temp;
	}

	/**
	 * Verifie une valeur en fonction de son type. Le programme va cherché le regex
	 * lié au type de la valeur passé en paramètre et le test sur cette même valeur
	 * 
	 * @param type
	 *            Type de valeur passé en paramètre
	 * @param valeur
	 *            Valeur à tester
	 * @param unEmploye
	 *            Employé à qui appartient la valeur(peut être null)
	 * @return Un bouléen qui confirme ou non que la valeur est conforme
	 */
	public boolean verif(String type, String valeur, Employe unEmploye) {
		boolean flag = false;

		if (Pattern.matches(Information.getRegex(type), valeur)) {
			if (type.equals(ID) || type.equals(LOGIN)) {
				if (type.equals(ID) && (!idExiste(valeur) || valeur.equals(unEmploye.getInfos(ID).getValeur()))) {
					flag = true;
				} else if (type.equals(LOGIN)
						&& (!idExiste(valeur) || valeur.equals(unEmploye.getInfos(LOGIN).getValeur()))) {
					flag = true;
				}
			} else {
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * Vérifie si un employé existe dans la liste des employés
	 * 
	 * @param valeur
	 *            Valeur de l'élément à rechercher.
	 * @param code
	 *            Code de l'élément à rechercher.
	 * @return Un booléen qui confirme ou nom que l'employé éxiste dans la liste des
	 *         employés.
	 */
	public boolean employeExiste(String valeur, int code) {
		for (Employe unEmploye : listeEmployes) {
			switch (code) {
			case M_LOGIN:
				if (unEmploye.getInfos(LOGIN).getValeur().equals(valeur))
					return true;
				break;
			case M_ID:
				if (unEmploye.getInfos(ID).getValeur().equals(valeur))
					return true;
				break;
			default:
				return false;
			}
		}
		return false;
	}

	/**
	 * Test si un champ est valide.
	 * 
	 * @param valeur
	 *            La valeur du champ à verifier.
	 * @param type
	 *            Le type du champ de la valeur à vérifier.
	 * @param employe
	 *            Employé à qui appartient la valeur, peut être null.
	 * @return Un booléen qui confirme ou non si la valeur est valide en fonction de
	 *         sont type.
	 */
	public int testChamp(String valeur, String type, Employe employe) {
		switch (type) {
		case ID:
			if (!verif(type, valeur, employe))
				return FO_ERREUR_ID;
			break;
		case NOM:
			if (!verif(type, valeur, employe))
				return FO_ERREUR_NOM;
			break;
		case PRENOM:
			if (!verif(type, valeur, employe))
				return FO_ERREUR_PRE;
			break;
		case LOGIN:
			if (!verif(type, valeur, employe))
				return FO_ERREUR_LOG;
			break;
		case MDP:
			if (!verif(type, valeur, employe))
				return FO_ERREUR_MDP;
			break;
		case ADR:
			if (!verif(type, valeur, employe))
				return FO_ERREUR_ADR;
			break;
		case CP:
			if (!verif(type, valeur, employe))
				return FO_ERREUR_CP;
			break;
		case VILLE:
			if (!verif(type, valeur, employe))
				return FO_ERREUR_VILLE;
			break;
		case MAIL:
			if (!verif(type, valeur, employe))
				return FO_ERREUR_MAIL;
			break;
		case TEL:
			if (!verif(type, valeur, employe))
				return FO_ERREUR_TEL;
			break;
		case DATEE:
			if (!verif(type, valeur, employe))
				return FO_ERREUR_DATE;
			break;
		}
		return 0;
	}
}