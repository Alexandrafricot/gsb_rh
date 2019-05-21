package bzh.gsbrh.fabriques;

import java.time.LocalDate;

import bzh.gsbrh.observateurs.Lexique;
import bzh.gsbrh.observateurs.Observateur;
import bzh.gsbrh.vues.Champ;
import bzh.gsbrh.vues.ChampBox;
import bzh.gsbrh.vues.ChampDate;
import bzh.gsbrh.vues.ChampID;
import bzh.gsbrh.vues.ChampPass;

/**
 * Classe spécialiser dans la fabrique des champs.
 * 
 * @author Alexandra Fricot
 * @version 1.0
 */
public class FactChamp implements Lexique {

	/**
	 * En fonction du code passé en paramètre la méthode sait qu'elle champ
	 * fabriquer.
	 * 
	 * @param o
	 *            Observateur par défaut du champ.
	 * @param text
	 *            Libellé associé au champ à fabriquer.
	 * @return Le champ fabriqué.
	 * 
	 * @see bzh.gsbrh.observateurs.Lexique
	 */
	public static Champ fabriqueChamp(Observateur o, String text) {
		switch (text) {
		case ID:
			return new ChampID(o, ID);
		case MDP:
			return new ChampPass(o, MDP, true);
		case CP:
			return new Champ(o, CP, CHAMP_CP, false);
		case TEL:
			return new Champ(o, TEL, CHAMP_TEL, false);
		case DATEE:
			return new ChampDate(o, DATEE);
		case DATED:
			String valeur = LocalDate.now().toString();
			return new ChampDate(o, DATED, valeur, true);
		case SERVICE:
			return new ChampBox(o, SERVICE);
		case LOGINCO:
			return new Champ(o, LOGINCO, CHAMP_TEXT, true);
		case MDPCO:
			return new ChampPass(o, MDPCO, false);
		case NOM:
		case PRENOM:
		case LOGIN:
		case ADR:
		case VILLE:
		case MAIL:
			return new Champ(o, text, CHAMP_TEXT, false);
		default:
			return null;
		}
	}
}
