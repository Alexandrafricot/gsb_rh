package bzh.gsbrh.vues;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import bzh.gsbrh.fabriques.FactBouton;
import bzh.gsbrh.modeles.Images;
import bzh.gsbrh.observateurs.Observateur;

/**
 * <p>
 * ChampPass est une classe héritant de Champ permettant d'instancier un libellé
 * et une zone de saisie de password.
 * </p>
 * <p>
 * Implémente DocumentListener dans le but de tester le contenue de la zone de
 * password
 * </p>
 * 
 * @author Alexandra Fricot
 * @version 1.0
 */
public class ChampPass extends Champ implements DocumentListener, FocusListener {

	/**
	 * Clé de hachage de la classe.
	 */
	private static final long serialVersionUID = 5300217359073517116L;

	/**
	 * Bouton permettant de generer un mot de passe dans la zone de saisie de
	 * password
	 */
	private Bouton generer;

	/**
	 * Zone de saisie de password
	 */
	protected JPasswordField pass;

	/**
	 * Constructeur par défaut
	 * 
	 * @param o
	 *            Observateur par défaut
	 * @param label
	 *            Libellé du champ
	 * @param generable
	 *            Booléen indiquant la présence ou non d'un bouton de génération de
	 *            mot de passe
	 */
	public ChampPass(Observateur o, String label, boolean generable) {
		super(o, label, CHAMP_PASS, false);

		this.pass = new JPasswordField();
		this.label.setPreferredSize(new Dimension(100, 20));

		if (generable) {
			JPanel zonPass = new JPanel(new GridLayout(1, 2));
			generer = FactBouton.fabriqueBouton(o, BO_GENE_PASS, Images.ALEATOIR.getIcon());
			generer.setText("");
			generer.setPreferredSize(new Dimension(85, 20));
			zonPass.add(this.pass);
			zonPass.add(generer);
			pass.getDocument().addDocumentListener(this);
			pass.setPreferredSize(new Dimension(85, 20));
			placementChamps(this.label, zonPass);

		} else {
			this.pass.setPreferredSize(new Dimension(100, 20));
			placementChamps(this.label, this.pass);
		}
		pass.addFocusListener(this);
	}

	public String getText() {
		return new String(this.pass.getPassword());
	}

	public void setValeur(Object val) {
		this.valeur = (String) val;
	}

	public void setText(String val) {
		pass.setText(val);
	}

	public void initialiserSaisie() {
		pass.setText(valeur);
		this.razCouleur();
	}

	public void bloquerChamp() {
		pass.setEditable(false);
		pass.setBackground(COLOR_TEXT_NULL);
	}

	public void deBloquerChamp() {
		pass.setEditable(true);
		pass.setBackground(COLOR_TEXT_WHITE);
	}

	public void setCouleur(boolean etat) {
		if (etat) {
			pass.setBackground(COLOR_TEXT_VALIDE);
		} else {
			pass.setBackground(COLOR_TEXT_IVALIDE);
		}
	}

	public void razCouleur() {
		pass.setBackground(COLOR_TEXT_WHITE);
	}

	public void changedUpdate(DocumentEvent arg0) {

	}

	public void insertUpdate(DocumentEvent arg0) {
		notifierObservateur();
	}

	public void removeUpdate(DocumentEvent arg0) {
		notifierObservateur();
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		pass.selectAll();
	}

	@Override
	public void focusLost(FocusEvent arg0) {
	}
}
