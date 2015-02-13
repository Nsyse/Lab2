/******************************************************
Cours:  LOG121
Projet: Squelette du laboratoire #1
Nom du fichier: SeparateurForme.java
Date crÃ©Ã©: 2015-01-24
 *******************************************************
Historique des modifications
 *******************************************************
 *@author Sébastien Turcotte
2015-01-24 Version initiale
 *******************************************************/

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Sépare l'information de la forme
 * 
 * @author Sébastien
 *
 */
public class SeparateurForme {

	private Pattern pattern;
	private Matcher matcher;

	private int nseq, rayon, rayonH, rayonV, x1, x2, y1, y2, centreX, centreY;
	private String nomForme;

	/**
	 * Constructeur
	 * 
	 * @param informationForme
	 *            Information de la forme
	 */
	public SeparateurForme(String informationForme) {
		decortiquer(fragmenter(informationForme));

	}

	/**
	 * Met dans un tableau les différents éléments de l'information de la forme
	 * 
	 * @param informationForme
	 *            Information de la forme
	 * @return tableau d'information
	 */
	private String[] fragmenter(String informationForme) {
		pattern = Pattern.compile("\\s");
		String[] items = pattern.split(informationForme, 7);
		for (int i = 0; i < items.length; i++) {
			pattern = Pattern.compile("\\W");
			matcher = pattern.matcher(items[i]);
			items[i] = matcher.replaceAll("");
		}
		return items;
	}

	/**
	 * Selon la forme, les valeurs de la forme sont associé à la bonne variable
	 * 
	 * @param items
	 *            tableau d'information de la forme
	 */
	private void decortiquer(String[] items) {
		nseq = Integer.parseInt(items[0]);
		nomForme = items[1];
		switch (nomForme) {
		case "CARRE":
		case "RECTANGLE":
		case "LIGNE":
			x1 = Integer.parseInt(items[2]);
			x2 = Integer.parseInt(items[4]);
			y1 = Integer.parseInt(items[3]);
			y2 = Integer.parseInt(items[5]);
			break;
		case "CERCLE":
			centreX = Integer.parseInt(items[2]);
			centreY = Integer.parseInt(items[3]);
			rayon = Integer.parseInt(items[4]);
			rayonH = rayon;
			rayonV = rayon;
			break;
		case "OVALE":
			centreX = Integer.parseInt(items[2]);
			centreY = Integer.parseInt(items[3]);
			rayonH = Integer.parseInt(items[4]);
			rayonV = Integer.parseInt(items[5]);
			break;
		}
	}

	/**
	 * 
	 * @return id de la forme
	 */
	public int getNseq() {
		return nseq;
	}

	/**
	 * 
	 * @return nom de la forme
	 */
	public String getNomForme() {
		return nomForme;
	}

	/**
	 * 
	 * @return coordonnée en X du premier point
	 */
	public int getX1() {
		return x1;
	}

	/**
	 * 
	 * @return coordonnée en X du deuxième point
	 */
	public int getX2() {
		return x2;
	}

	/**
	 * 
	 * @return coordonnée en Y du premier point
	 */
	public int getY1() {
		return y1;
	}

	/**
	 * 
	 * @return coordonnée en Y du deuxième point
	 */
	public int getY2() {
		return y2;
	}

	/**
	 * 
	 * @return coordonnée en X du centre
	 */
	public int getCentreX() {
		return centreX;
	}

	/**
	 * 
	 * @return coordonnée en Y du centre
	 */
	public int getCentreY() {
		return centreY;
	}

	/**
	 * 
	 * @return longueur du rayon
	 */
	public int getRayon() {
		return rayon;
	}

	/**
	 * 
	 * @return longueur du rayon horizontal
	 */
	public int getRayonH() {
		return rayonH;
	}

	/**
	 * 
	 * @return longueur du rayon vertical
	 */
	public int getRayonV() {
		return rayonV;
	}
}
