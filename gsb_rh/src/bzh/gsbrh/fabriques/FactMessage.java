package bzh.gsbrh.fabriques;

import javax.swing.JOptionPane;

import bzh.gsbrh.observateurs.Lexique;

/**
 * Classe spécialiser dans la fabrique des messages.
 * 
 * @author Alexandra Fricot
 * @version 1.0
 *  
 * @see bzh.gsbrh.observateurs.Lexique
 */
public class FactMessage implements Lexique {

	/**
	 * En fonction du code passé en paramètre la methode sait qu'elle message
	 * fabriquer.
	 * 
	 * @param code
	 *            int : code du message à fabriquer.
	 * @return dans le cas d'un message de confirmation, retourne 0 pour oui, 1 pour
	 *         non, par defaut 1.
	 * 
	 * @see bzh.gsbrh.observateurs.Lexique
	 */
	public static int fabriqueMessage(int code) {
		int confirm = 1;
		switch (code) {
		case LI_CONFIRM_S:
			confirm = JOptionPane.showConfirmDialog(null, M_CONFIRM_S, M_TITRE_C, JOptionPane.YES_NO_OPTION);
			break;
		case FO_CONFIRM_M:
			confirm = JOptionPane.showConfirmDialog(null, M_CONFIRM_M, M_TITRE_C, JOptionPane.YES_NO_OPTION);
			break;
		case AP_CONFIRM_Q:
			confirm = JOptionPane.showConfirmDialog(null, M_CONFIRM_F, M_TITRE_C, JOptionPane.YES_NO_OPTION);
			break;
		case LI_CONFIRM_MODIFDATE:
			confirm = JOptionPane.showConfirmDialog(null, M_CONFIRM_MODIFDATE, M_TITRE_C, JOptionPane.YES_NO_OPTION);
			break;
		case LI_CONFIRM_REINTEGRE:
			confirm = JOptionPane.showConfirmDialog(null, M_CONFIRM_REINTEGRE, M_TITRE_C, JOptionPane.YES_NO_OPTION);
			break;

		case FO_MESSAGE_A:
			JOptionPane.showMessageDialog(null, M_MESSAGE_A, M_TITRE_M, JOptionPane.INFORMATION_MESSAGE);
			break;
		case LI_MESSAGE_S:
			JOptionPane.showMessageDialog(null, M_MESSAGE_S, M_TITRE_M, JOptionPane.INFORMATION_MESSAGE);
			break;
		case FO_MESSAGE_MO:
			JOptionPane.showMessageDialog(null, M_MESSAGE_M, M_TITRE_M, JOptionPane.INFORMATION_MESSAGE);
			break;
		case ID_MESSAGE_CO:
			JOptionPane.showMessageDialog(null, M_MESSAGE_C, M_TITRE_M, JOptionPane.INFORMATION_MESSAGE);
			break;

		case ID_ERREUR_CO:
			JOptionPane.showMessageDialog(null, M_ERREUR_CO, M_TITRE_E, JOptionPane.ERROR_MESSAGE);
			break;
		case ID_ERREUR_SE:
			JOptionPane.showMessageDialog(null, M_ERREUR_SE, M_TITRE_E, JOptionPane.ERROR_MESSAGE);
			break;
		case FO_ERREUR_AJ:
			JOptionPane.showMessageDialog(null, M_ERREUR_AJ, M_TITRE_E, JOptionPane.ERROR_MESSAGE);
			break;
		case FO_ERREUR_MO:
			JOptionPane.showMessageDialog(null, M_ERREUR_MO, M_TITRE_E, JOptionPane.ERROR_MESSAGE);
			break;
		case LI_ERREUR_MO:
			JOptionPane.showMessageDialog(null, M_ERREUR_DATE, M_TITRE_E, JOptionPane.ERROR_MESSAGE);
			break;
		case FO_ERREUR_ID:
			JOptionPane.showMessageDialog(null, M_ERREUR_ID, M_TITRE_E, JOptionPane.ERROR_MESSAGE);
			break;
		case FO_ERREUR_NOM:
			JOptionPane.showMessageDialog(null, M_ERREUR_NOM, M_TITRE_E, JOptionPane.ERROR_MESSAGE);
			break;
		case FO_ERREUR_PRE:
			JOptionPane.showMessageDialog(null, M_ERREUR_PRE, M_TITRE_E, JOptionPane.ERROR_MESSAGE);
			break;
		case FO_ERREUR_LOG:
			JOptionPane.showMessageDialog(null, M_ERREUR_LOG, M_TITRE_E, JOptionPane.ERROR_MESSAGE);
			break;
		case FO_ERREUR_MDP:
			JOptionPane.showMessageDialog(null, M_ERREUR_MDP, M_TITRE_E, JOptionPane.ERROR_MESSAGE);
			break;
		case FO_ERREUR_ADR:
			JOptionPane.showMessageDialog(null, M_ERREUR_ADR, M_TITRE_E, JOptionPane.ERROR_MESSAGE);
			break;
		case FO_ERREUR_CP:
			JOptionPane.showMessageDialog(null, M_ERREUR_CP, M_TITRE_E, JOptionPane.ERROR_MESSAGE);
			break;
		case FO_ERREUR_VILLE:
			JOptionPane.showMessageDialog(null, M_ERREUR_VILLE, M_TITRE_E, JOptionPane.ERROR_MESSAGE);
			break;
		case FO_ERREUR_MAIL:
			JOptionPane.showMessageDialog(null, M_ERREUR_MAIL, M_TITRE_E, JOptionPane.ERROR_MESSAGE);
			break;
		case FO_ERREUR_TEL:
			JOptionPane.showMessageDialog(null, M_ERREUR_TEL, M_TITRE_E, JOptionPane.ERROR_MESSAGE);
			break;
		case FO_ERREUR_DATE:
			JOptionPane.showMessageDialog(null, M_ERREUR_DATE, M_TITRE_E, JOptionPane.ERROR_MESSAGE);
			break;
		case AP_DECO_FORCE:
			JOptionPane.showMessageDialog(null, M_ERREUR_DECO, M_TITRE_E, JOptionPane.ERROR_MESSAGE);
			break;
		case ID_ERREUR_COBDD:
			JOptionPane.showMessageDialog(null, M_ERREUR_COBDD, M_TITRE_E, JOptionPane.ERROR_MESSAGE);
			break;
		default:
			return confirm;
		}

		return confirm;
	}
}
