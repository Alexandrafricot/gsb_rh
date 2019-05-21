package bzh.gsbrh.modeles;

import java.awt.Image;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * Enumération d'image permet d'instancier des images présent dans le projet sou
 * la forme d'image ou d'image icone
 * 
 * @author Alexandra Fricot
 * @version 1.0
 *  
 * @see bzh.gsbrh.observateurs.Lexique
 */
public enum Images {
	/**
	 * nom et extension du logo/icone de l'application
	 */
	LOGO("logo.png"),

	/**
	 * nom et extension de l'image retour
	 */
	RETOUR("retour.png"),

	/**
	 * nom et extension de l'image ajouter
	 */
	AJOUTER("ajouter.png"),

	/**
	 * nom et extension de l'image deconnexion
	 */
	DECONNECT("deconnexion.png"),

	/**
	 * nom et extension de l'image aléatoire
	 */
	ALEATOIR("alea.png"),

	/**
	 * nom et extension de l'image valider
	 */
	VALIDER("valider.png"),

	/**
	 * nom et extension de l'image réinitialiser
	 */
	REINIT("reinit.png"),

	/**
	 * nom et extension de l'image connexion
	 */
	CONNECT("connexion.png"),

	/**
	 * nom et extension de l'image modifier
	 */
	MODIFIE("modifier.png"),

	/**
	 * nom et extension de l'image programmation
	 */
	PROGRAM("programme.png");

	/**
	 * nom et extension de l'image demandé lors de l'appel de l'enumération
	 */
	private String value;

	/**
	 * Url de l'image demandé
	 */
	private URL url;

	/**
	 * L'imageIcon de l'image demandé a l'appel de l'enumération
	 */
	private ImageIcon icon;

	/**
	 * L'image demandé a l'appel de l'enumération
	 */
	private Image image;

	/**
	 * Constructeur privée de l'enumération
	 * 
	 * @param value Valeur de la constante de l'enumération demandé
	 */
	private Images(String value) {
		this.value = value;
		try {
			this.url = getClass().getResource("/images/" + this.value);
			this.image = ImageIO.read(this.url);
			this.icon = new ImageIcon(this.url);
		} catch (Exception e) {
		}
	}

	/**
	 * Accesseur du nom de l'image
	 * 
	 * @return Une chaine de caractère de type image.ext
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Accesseur de l'imageIcon de l'enumération
	 * 
	 * @return Un objet ImageIcon
	 */
	public ImageIcon getIcon() {
		return icon;
	}

	/**
	 * Accesseur de l'image énuméré
	 * 
	 * @return Un objet Image
	 */
	public Image getImage() {
		return image;
	}

}
