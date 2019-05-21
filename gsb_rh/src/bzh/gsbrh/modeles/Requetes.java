package bzh.gsbrh.modeles;

import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;

import bzh.gsbrh.modeles.ConnexionBDD;
import bzh.gsbrh.observateurs.Lexique;

/**
 * Classe pour communiquer avec la base de données
 * 
 * @author Alexandra Fricot
 * @version 1.1
 */
public class Requetes implements Lexique {

	/**
	 * Objet de type ResultSet contient les resultats des requetes.
	 */
	private static ResultSet resultat = null;

	/**
	 * Objet de type Statement permet d'éxécuter les requêtes sql.
	 */
	private static Statement requete = null;

	/**
	 * Objet permettant d'extraire les métadonées de la base de données.
	 */
	private static DatabaseMetaData metad = null;

	/**
	 * Objet permettant de parcourir les métadonnées de la base de données.
	 */
	private static ResultSet champTable = null;

	/**
	 * Ajoute un employé en base de données
	 * 
	 * @param employe
	 *            l'employé à ajouter en base de données
	 * @return le nombre d'employé ajouté en base(1 ou 0)
	 */
	public static int ajouterEmploye(Employe employe) {

		int flag = 0;

		String data = "";
		for (int i = 0; i < Information.getTypes().length - 1; i++) {
			if (employe.getInfos(i).getType().equals(SERVICE)) {
				data = data + (Integer.parseInt(employe.getInfos(i).getValeur()) + 1) + "'";
			} else if (employe.getInfos(i).getType().equals(DATEE))
				data = data + Date.valueOf(employe.getInfos(i).getValeur()) + "','";
			else
				data = data + addSlashe(employe.getInfos(i).getValeur()) + "','";
		}
		if (ConnexionEtablie()) {
			try {
				// Création d'un objet Statement
				requete = ConnexionBDD.getInstance().createStatement();

				// Déclaration de la requête
				String sql = "INSERT INTO visiteur(id, nom, prenom, login, mdp, adresse, cp, ville, mail, tel, dateEmbauche, service_id) VALUES ('"
						+ data + ")";
				try {
					flag = requete.executeUpdate(sql);
				} catch (SQLException e) {

				}

			} catch (SQLException e) {
			} catch (ClassNotFoundException e) {
			} finally {
				close();
			}
		}
		return flag;
	}

	/**
	 * Trouve un employé en base de données
	 * 
	 * @param valeur
	 *            code du champs sur lequel on va effectuer la recherche
	 * @param option
	 *            champ sur lequel on effectue la recherche
	 * @return l'employé trouvé en base ou un employé vide
	 */
	public static Employe trouverEmploye(String valeur, int option) {
		Employe employe = new Employe();
		Hashtable<Integer, Information> infos = new Hashtable<Integer, Information>();
		if (ConnexionEtablie()) {
			try {

				// Création d'un objet Statement
				requete = ConnexionBDD.getInstance().createStatement();

				// L'objet ResultSet contient le résultat de la requête SQL
				switch (option) {
				case M_ID:
					resultat = requete.executeQuery("SELECT * FROM visiteur WHERE id = '" + valeur + "';");
					break;
				case M_LOGIN:
					resultat = requete.executeQuery("SELECT * FROM visiteur WHERE login = '" + valeur + "';");
					break;
				}
				resultat.last();
				int row = resultat.getRow();
				resultat.first();
				metad = ConnexionBDD.getInstance().getMetaData();
				champTable = metad.getColumns(null, null, "visiteur", null);
				if (row != 0) {
					int i = 0;
					while (champTable.next()) {
						infos.put(i, new Information(Information.getTypes(i),
								resultat.getString(champTable.getString("COLUMN_NAME"))));
						i++;
					}
					employe.setInfos(infos);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				close();
			}
		}
		return employe;
	}

	/**
	 * Verifie si un employé existe
	 * 
	 * @param compar
	 *            valeur à chercher
	 * @param option
	 *            colonne de la table sur laquel chercher la valeur
	 * @return un booleen qui confirme ou non si l'employé éxiste
	 */
	public static boolean employeExiste(String compar, int option) {
		boolean flag = false;
		if (ConnexionEtablie()) {
			try {

				// Création d'un objet Statement
				requete = ConnexionBDD.getInstance().createStatement();
				switch (option) {
				case M_ID:
					// L'objet ResultSet contient le résultat de la requête SQL
					resultat = requete.executeQuery("SELECT * FROM visiteur WHERE id = '" + compar + "';");
					break;
				case M_LOGIN:
					// L'objet ResultSet contient le résultat de la requête SQL
					resultat = requete.executeQuery("SELECT * FROM visiteur WHERE login = '" + compar + "';");
					break;
				}

				resultat.last();
				int row = resultat.getRow();
				if (row > 0)
					flag = true;
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				close();
			}
		}
		return flag;
	}

	/**
	 * cherche en base de données la liste des services
	 * 
	 * @return liste des libelles des services
	 */
	public static String[] listerServices() {
		String[] liste = null;
		if (ConnexionEtablie()) {
			try {

				// Création d'un objet Statement
				requete = ConnexionBDD.getInstance().createStatement();

				// L'objet ResultSet contient le résultat de la requête SQL
				resultat = requete.executeQuery("SELECT * FROM services ORDER BY service_id;");

				resultat.last();
				int row = resultat.getRow();
				resultat.first();

				liste = new String[row];

				for (int i = 0; i < row; i++) {
					liste[i] = resultat.getString("service_libelle");
					resultat.next();
				}

			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				close();
			}
		}

		return liste;
	}

	/**
	 * Modifie un employé.
	 * 
	 * @param employe
	 *            L'employé avec ses nouvelles informations.
	 * @param codeModif
	 *            Tableau des codes des éléments à modifier.
	 * @return le nombre de resultat enregistré en base (1 ou 0).
	 */
	public static int modifierEmploye(Employe employe, int[] codeModif) {
		int result = 0;
		String data = "";
		if (ConnexionEtablie()) {
			try {
				// Connexion à la base de données et création d'un objet Statement
				requete = ConnexionBDD.getInstance().createStatement();

				metad = ConnexionBDD.getInstance().getMetaData();
				champTable = metad.getColumns(null, null, "visiteur", null);
				// L'objet ResultSet contient le résultat de la requête SQL

				champTable.last();
				int col = champTable.getRow();
				champTable.beforeFirst();
				String[] champs = new String[col];
				int i = 0;
				while (champTable.next()) {
					champs[i] = champTable.getString("COLUMN_NAME");
					i++;
				}
				for (i = 0; i < codeModif.length; i++) {
					for (int j = 0; j < Information.getCodeModif().length; j++) {
						if (codeModif[i] == Information.getCodeModif(j)) {
							if (employe.getInfos(j).getType().equals(SERVICE)) {
								data = data + champs[j] + " = '"
										+ (Integer.parseInt(employe.getInfos(j).getValeur()) + 1) + "'";
							} else if (employe.getInfos(j).getType().equals(DATEE)
									|| employe.getInfos(j).getType().equals(DATED)) {
								if (employe.getInfos(12).getValeur() == null
										&& employe.getInfos(j).getType().equals(DATED))
									data = data + champs[j] + " = null";
								else
									data = data + champs[j] + " = '" + Date.valueOf(employe.getInfos(j).getValeur())
											+ "'";
							} else
								data = data + champs[j] + " = '" + addSlashe(employe.getInfos(j).getValeur()) + "'";
						}

					}
					if (i != (codeModif.length - 1))
						data = data + ",";
				}

				// Déclaration de la requête
				String sql = "UPDATE visiteur SET " + data + " WHERE id = '" + employe.getInfos(0).getValeur() + "'";

				result = requete.executeUpdate(sql);

			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				close();
			}
		}
		return result;
	}

	/**
	 * Retourne une collection de tout les employes
	 * 
	 * @return Une ArrayListe contient la liste de tout les employés
	 */
	public static ArrayList<Employe> listerEmployer() {
		ArrayList<Employe> liste = null;
		if (ConnexionEtablie()) {
			try {
				// Création d'un objet Statement
				requete = ConnexionBDD.getInstance().createStatement();

				// L'objet ResultSet contient le résultat de la requête SQL
				resultat = requete.executeQuery("SELECT * FROM visiteur ORDER BY nom;");
				metad = ConnexionBDD.getInstance().getMetaData();

				resultat.last();
				int row = resultat.getRow();
				resultat.first();

				// Déclaration d'un employé qui va permettre l'ajout des employés
				Employe unEmploye;
				liste = new ArrayList<Employe>();
				champTable = metad.getColumns(null, null, "visiteur", null);
				Hashtable<Integer, Information> infos = new Hashtable<Integer, Information>();
				int j = 0;
				// Boucle qui génère les employés un à un
				for (int i = 0; i < row; i++) {
					unEmploye = new Employe();
					infos = new Hashtable<Integer, Information>();
					j = 0;
					while (champTable.next()) {
						infos.put(j, new Information(Information.getTypes(j),
								resultat.getString(champTable.getString("COLUMN_NAME"))));
						j++;
					}
					champTable.beforeFirst();
					unEmploye.setInfos(infos);

					resultat.next();
					// Ajout de l'employé à la liste des employés
					liste.add(unEmploye);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();

			} finally {
				close();
			}
		}
		return liste;

	}

	/**
	 * Retourne une collection de tout les employes
	 * 
	 * @param where
	 *            Les contraintes de la requete sql
	 * @return Une ArrayListe contient la liste de tous les employés
	 */
	public static ArrayList<Employe> listerEmployer(String where) {
		ArrayList<Employe> liste = null;
		if (ConnexionEtablie()) {
			try {
				// Création d'un objet Statement
				requete = ConnexionBDD.getInstance().createStatement();

				// L'objet ResultSet contient le résultat de la requête SQL
				resultat = requete.executeQuery("SELECT * FROM visiteur " + where + " ORDER BY nom");
				metad = ConnexionBDD.getInstance().getMetaData();

				resultat.last();
				int row = resultat.getRow();
				resultat.first();

				// Déclaration d'un employé qui va permettre l'ajout des employés
				Employe unEmploye;
				liste = new ArrayList<Employe>();
				champTable = metad.getColumns(null, null, "visiteur", null);
				Hashtable<Integer, Information> infos = new Hashtable<Integer, Information>();
				int j = 0;
				// Boucle qui génère les employés un à un
				for (int i = 0; i < row; i++) {
					unEmploye = new Employe();
					infos = new Hashtable<Integer, Information>();
					j = 0;
					while (champTable.next()) {
						infos.put(j, new Information(Information.getTypes(j),
								resultat.getString(champTable.getString("COLUMN_NAME"))));
						j++;
					}
					champTable.beforeFirst();
					unEmploye.setInfos(infos);

					resultat.next();
					// Ajout de l'employé à la liste des employés
					liste.add(unEmploye);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();

			} finally {
				close();
			}
		}
		return liste;
	}

	/**
	 * Verifie login et mot de passe en base
	 * 
	 * @param loginAVerifier
	 *            login saisie par l'utilisateur
	 * @param mdpAVerifier
	 *            mot de passe saisie par l'utilisateur
	 * @return un booleen qui confirme ou non la validité des information saisie
	 */
	public static boolean verifierMdp(String loginAVerifier, String mdpAVerifier) {
		boolean loginOk = false;

		String sql = null;
		if (ConnexionEtablie()) {
			try {
				requete = ConnexionBDD.getInstance().createStatement();
				sql = "SELECT login, mdp FROM visiteur WHERE login='" + addSlashe(loginAVerifier) + "'";
				resultat = requete.executeQuery(sql);
				if (resultat.first()) {
					String aTester = resultat.getString("mdp");
					if (aTester.equals(mdpAVerifier))
						loginOk = true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				close();
			}
		}
		return loginOk;
	}

	/**
	 * Sécurise les chaines de caractère
	 * 
	 * @param s
	 *            chaine de caractère à traité
	 * @return une chaine de caractère sécurisé pour être accépté en base de données
	 */
	public static String addSlashe(String s) {
		s = s.replaceAll("\\\\", "\\\\\\\\");
		s = s.replaceAll("\\n", "\\\\n");
		s = s.replaceAll("\\r", "\\\\r");
		s = s.replaceAll("\\00", "\\\\0");
		return s = s.replaceAll("'", "\\\\'");
	}

	/**
	 * Ferme les instances de connexion a la base de données
	 */
	private static void close() {
		try {
			if (requete != null)
				requete.close();
			if (resultat != null)
				resultat.close();
			ConnexionBDD.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		requete = null;
		resultat = null;
	}

	/**
	 * Test si la connexion à la base de donnée est etablie
	 * 
	 * @return Un bouléen confirmant ou non que la connexion a la base de données
	 *         est établie
	 */
	public static boolean ConnexionEtablie() {
		boolean flag = false;
		try {
			if (ConnexionBDD.getInstance() != null)
				flag = true;
		} catch (SQLException e) {
			flag = false;
		} catch (ClassNotFoundException e) {
			flag = false;
		} finally {
			close();
		}

		return flag;
	}

}
