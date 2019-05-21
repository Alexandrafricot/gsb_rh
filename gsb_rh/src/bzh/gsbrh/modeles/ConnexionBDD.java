package bzh.gsbrh.modeles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe de connexion à la BDD.
 * 
 * Conçus sur un pattern singleton pour instancier une seul connexion.
 * 
 * @author Alexandra Fricot
 * @version 1.0
 *  
 * @see bzh.gsbrh.observateurs.Lexique
 */
public class ConnexionBDD {
	/**
	 * Objet de connexion à la base de données
	 */
	private static Connection connexion;

	/**
	 * Adresse de connexion à la base de données
	 */
	private String url = "jdbc:mysql://localhost/gsb_rh";

	/**
	 * Login de connexion à la base de données
	 */
	private String login = "root";

	/**
	 * Password de connexion à la base de données
	 */
	private String password = "";

	/**
	 * Constructeur privé du singleton.
	 */
	private ConnexionBDD() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connexion = DriverManager.getConnection(this.url, this.login, this.password);

		} catch (SQLException e) {
			connexion = null;
		} catch (ClassNotFoundException e) {
			connexion = null;
		}
	}

	/**
	 * Accès au singleton.
	 * 
	 * @return Unique connexion a la base de données.
	 * @throws ClassNotFoundException
	 *             Exception d'erreur de type classe non trouvé.
	 * @throws SQLException
	 *             Exception d'erreur de type SQL.
	 */
	public static Connection getInstance() throws ClassNotFoundException, SQLException {
		if (connexion == null)
			new ConnexionBDD();
		return connexion;
	}

	/**
	 * Ferme la connexion avec la bdd.
	 */
	public static void close() {
		try {
			if (connexion != null)
				connexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		connexion = null;
	}

}
