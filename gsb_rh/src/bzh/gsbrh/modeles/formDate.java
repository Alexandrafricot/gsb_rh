package bzh.gsbrh.modeles;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Formate et effectue des tests sur des dates.
 * 
 * @author Alexandra Fricot
 * @version 1.0
 */
public class formDate {

	/**
	 * Formateur des mois et des jours.
	 */
	private static NumberFormat formaterMJ = new DecimalFormat("00");

	/**
	 * Formateur des années.
	 */
	private static NumberFormat formaterAN = new DecimalFormat("0000");

	/**
	 * Formateur des dates comparables.
	 */
	private static DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyyMMdd");

	/**
	 * Formate la date au format AAAA-MM-JJ.
	 * 
	 * @param date
	 *            Chaine de caractère à traiter attendu au format année-mois-jours.
	 * @return Une date au format AAAA-MM-JJ.
	 */
	public static String formatDate(String date) {
		int jours = 0;
		int mois = 0;
		int annees = 0;
		String[] dates = date.split("-");
		if (dates.length < 3)
			return "";
		if (!dates[0].isEmpty())
			annees = Integer.parseInt(dates[0]);
		if (!dates[1].isEmpty())
			mois = Integer.parseInt(dates[1]);
		if (!dates[2].isEmpty())
			jours = Integer.parseInt(dates[2]);

		date = formaterAN.format(annees) + "-" + formaterMJ.format(mois) + "-" + formaterMJ.format(jours);
		return date;
	}

	/**
	 * Formate la date au format JJ-MM-AAAA.
	 * 
	 * @param date
	 *            Chaine de caractère à traiter attendu au format AAAA-MM-JJ.
	 * @return Une date au format JJ-MM-AAAA.
	 */
	public static String dateAffichable(String date) {
		int jours = 0;
		int mois = 0;
		int annees = 0;
		if (!(date.equals(""))) {
			String[] dates = date.split("-");
			if (!dates[0].isEmpty())
				annees = Integer.parseInt(dates[0]);
			if (!dates[1].isEmpty())
				mois = Integer.parseInt(dates[1]);
			if (!dates[2].isEmpty())
				jours = Integer.parseInt(dates[2]);
			date = formaterMJ.format(jours) + "-" + formaterMJ.format(mois) + "-" + formaterAN.format(annees);
		}
		return date;

	}

	/**
	 * Test si la date est dépassé.
	 * 
	 * @param date
	 *            Chaine de caractère au format AAAA-MM-JJ.
	 * @return Un booléen qui confirme ou non que la date est dépassé.
	 */
	public static boolean dateDepasse(String date) {
		if (date.isEmpty() || date == null)
			return false;
		int jours = 0;
		int mois = 0;
		int annees = 0;
		LocalDate compar = LocalDate.now();
		String[] dates = date.split("-");
		if (!dates[0].isEmpty())
			annees = Integer.parseInt(dates[0]);
		if (!dates[1].isEmpty())
			mois = Integer.parseInt(dates[1]);
		if (!dates[2].isEmpty())
			jours = Integer.parseInt(dates[2]);
		LocalDate temp = LocalDate.now();
		try {
			temp = LocalDate.of(annees, mois, jours);
		} catch (Exception e) {

		}

		int dateTemp = Integer.parseInt(temp.format(formater));
		int dateCompar = Integer.parseInt(compar.format(formater));

		if (dateTemp < dateCompar)
			return true;

		return false;
	}

}
