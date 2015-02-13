/******************************************************
Cours:  LOG121
Projet: Squelette du laboratoire #1
Nom du fichier: Rectangle.java
Date crÃ©Ã©: 2015-01-23
 *******************************************************
Historique des modifications
 *******************************************************
 *@author Sébastien Turcotte
2015-01-23 Version initiale
 *******************************************************/

/**
 * 
 * @author Sébastien
 *
 */
public class Rectangle extends Forme {

	private int x1, x2, y1, y2;

	/**
	 * Constructeur
	 * 
	 * @param nseq
	 *            Id de la forme
	 * @param nomForme
	 *            nom de la forme
	 * @param x1
	 *            Premier point, coordonnée en X
	 * @param y1
	 *            Premier point, coordonnée en Y
	 * @param x2
	 *            Deuxième point, coordonnée en X
	 * @param y2
	 *            Deuxième point, coordonéée en Y
	 */
	public Rectangle(int nseq, String nomForme, int x1, int y1, int x2, int y2) {
		super(nseq, nomForme);
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
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
	 * @return longueur du rectangle
	 */
	public int getWidth() {
		return x2 - x1;
	}

	/**
	 * 
	 * @return hauteur du rectangle
	 */
	public int getHeight() {
		return y2 - y1;
	}
}
