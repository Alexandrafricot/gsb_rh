package bzh.gsbrh.vues;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * <p>
 * Classe captant les entrées dans les zones de saisie permettant d'intercépter
 * et controler les saisies des utilisateurs avant des les envoyé ou non dans la
 * zone de saisie
 * </p>
 * 
 * @author Alexandra Fricot
 * @version 1.0
 */
public class LimitText extends PlainDocument {

	/**
	 * Clé de hachage de la classe.
	 */
	private static final long serialVersionUID = 2665071092876643901L;

	/**
	 * Taille limite de la zone de texte
	 */
	private int limit;

	/**
	 * Bouléen déterminant si la zone
	 */
	private boolean nombre;

	/**
	 * Constructeur surchargé
	 * 
	 * @param limit
	 *            Nombre de caractère maximum de la zone de texte
	 * @param nombre
	 *            Bouléen déterminant si la saisie doit ou non être numérique
	 */
	public LimitText(int limit, boolean nombre) {
		super();
		this.nombre = nombre;
		this.limit = limit;
	}

	public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
		if (str == null)
			return;

		if (nombre) {
			try {
				long number = Long.parseLong(str);
				if (number >= 0 && number < 99999 && (getLength() + str.length()) <= limit) {
					super.insertString(offset, str, attr);
				} else if (number > 0 && number < 9999999999L && (getLength() + str.length()) <= limit) {
					NumberFormat formatterTel = new DecimalFormat("0000000000");
					str = formatterTel.format(number);
					super.insertString(offset, str, attr);
				} else {
					return;
				}
			} catch (Exception e) {
				return;
			}
		} else if ((getLength() + str.length()) <= limit) {
			super.insertString(offset, str, attr);

		} else
			return;

	}

}
