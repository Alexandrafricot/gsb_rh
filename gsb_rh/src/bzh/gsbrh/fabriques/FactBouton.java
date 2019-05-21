package bzh.gsbrh.fabriques;

import javax.swing.ImageIcon;

import bzh.gsbrh.modeles.Entete;
import bzh.gsbrh.observateurs.Lexique;
import bzh.gsbrh.observateurs.Observateur;
import bzh.gsbrh.vues.Bouton;
import bzh.gsbrh.vues.FenFormulaire;
import bzh.gsbrh.vues.FenListeEmployes;
import bzh.gsbrh.vues.Onglet;
import bzh.gsbrh.vues.PopUp;

/**
 * Classe spécialiser dans la fabrique des boutons.
 * 
 * @author Alexandra Fricot
 * @version 1.0
 */
public class FactBouton implements Lexique {

	/**
	 * En fonction du type passé en paramètre la méthode sait qu'elle bouton
	 * fabriquer.
	 * 
	 * @param o
	 *            Observateur par défaut du bouton.
	 * @param type
	 *            Le type du bouton à fabriquer.
	 * @param image
	 *            Icone à afficher sur le bouton.
	 * @return Le bouton fabriqué
	 * 
	 * @see bzh.gsbrh.observateurs.Lexique
	 */
	public static Bouton fabriqueBouton(Observateur o, int type, ImageIcon image) {
		switch (type) {
		case BO_CONNECT:
			return new Bouton(o, BOUTON_CONNE, ID_CO, image);
		case BO_DECONNECT:
			return new Bouton(o, BOUTON_DECO, AP_DECO, image);
		case BO_AJOUT:
			if (o instanceof FenFormulaire) {
				return new Bouton(o, BOUTON_AJOUT, FO_AJ, image);
			}
			if (o instanceof FenListeEmployes) {
				return new Bouton(o, BOUTON_AJOUT, LI_AJ, image);
			}
			break;
		case BO_MODIF:
			if (o instanceof FenFormulaire)
				return new Bouton(o, BOUTON_MODIF, FO_MO, image);

			if (o instanceof Onglet)
				return new Bouton(o, BOUTON_MODIF, LI_MO, image);

			if (o instanceof Entete)
				return new Bouton(o, BOUTON_MODIF, LI_MO, image);
			else
				return new Bouton(o, BOUTON_MODIF, LI_MO, image);
		case BO_REINIT:
			if (o instanceof PopUp)
				return new Bouton(o, BOUTON_DEPROG, PP_DATE_RE, image);
			else
				return new Bouton(o, BOUTON_REINIT, FO_RE, image);

		case BO_SUPPR:
			return new Bouton(o, BOUTON_SUPPR, LI_PR, image);

		case BO_RETOUR:
			if (o instanceof PopUp)
				return new Bouton(o, BOUTON_RETOUR, PP_DATE_BA, image);
			else
				return new Bouton(o, BOUTON_RETOUR, FO_BA, image);

		case BO_VALIDER:
			return new Bouton(o, BOUTON_VALIDER, PP_DATE_VA, image);
		case BO_DATE_NOW:
			return new Bouton(o, BOUTON_DATE, BO_DATE_NOW, null);
		case BO_GENE_PASS:
			return new Bouton(o, BOUTON_GENRER, BO_GENE_PASS, image);
		}

		return null;
	}
}
