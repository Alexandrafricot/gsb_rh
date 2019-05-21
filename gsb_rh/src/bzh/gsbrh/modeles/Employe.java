package bzh.gsbrh.modeles;

import java.util.Hashtable;

import bzh.gsbrh.observateurs.Lexique;

/**
 * Classe permettant d'instancier un employé.
 * 
 * @author Alexandra Fricot
 * @version 1.1
 */
public class Employe implements Lexique {

	/**
	 * Collection des informations de l'employé.
	 * 
	 * @see Information
	 */
	private Hashtable<Integer, Information> infos = new Hashtable<Integer, Information>();

	public Employe() {
		for (int i = 0; i < Information.getTypes().length; i++) {
			infos.put(i, new Information(Information.getTypes(i), ""));
		}
	}

	/**
	 * Accesseur de la collection d'information.
	 * 
	 * @return La collection d'information de l'employé.
	 */
	public Hashtable<Integer, Information> getInfos() {
		return infos;
	}

	/**
	 * Accesseur d'un élément de la collection d'information.
	 * 
	 * @param i
	 *            Indice de l'élément demandé.
	 * @return L'information demandé sur l'employé
	 */
	public Information getInfos(int i) {
		return getInfos().get(i);
	}

	/**
	 * Accesseur d'un élément de la collection d'information.
	 * 
	 * @param i
	 *            Type de l'information demandé
	 * @return L'information demandé sur l'employé
	 */
	public Information getInfos(String i) {
		return getInfos().get(Information.getByType(i));
	}

	/**
	 * Mutateur de la collection d'information de l'employé.
	 * 
	 * @param infos
	 *            Collection d'informations remplaçant celui existant.
	 */
	public void setInfos(Hashtable<Integer, Information> infos) {
		this.infos = infos;

	}
	
	/**
	 * Créer une copie de l'employé
	 * 
	 * @return Une copie de l'employé
	 */
	public Employe copie() {
		// On instancie un nouvel employé qui sera la copie de l'employé courant
		Employe copie = new Employe();
		// On instancie un nouveau hashtable d'information pour la copie de l'employé
		Hashtable<Integer, Information> infosCopie = new Hashtable<Integer, Information>();
		// On copie les valeur des informations de l'employé courant vers le hashtable de la copie
		for (int i = 0; i < Information.getTypes().length; i++) {
			infosCopie.put(i, new Information(Information.getTypes(i), new String(this.getInfos(i).getValeur())));
		}
		// On prend en compte le nouveau hashtable
		copie.setInfos(infosCopie);
		// Pour éviter les erreur on vient remplacer le service de la copie par celui de l'employé courant
		copie.getInfos(SERVICE).setValeur(new String(this.getInfos(SERVICE).getValeur()));
		// Enfin, on renvoie la copie
		return copie;
	}

}
