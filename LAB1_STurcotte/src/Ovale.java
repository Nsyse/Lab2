/******************************************************
Cours:  LOG121
Projet: Squelette du laboratoire #1
Nom du fichier: Ovale.java
Date créé: 2015-01-23
 *******************************************************
Historique des modifications
 *******************************************************
 *@author S�bastien Turcotte
2013-01-23 Version initiale
 *******************************************************/

/**
 * 
 * @author S�bastien
 *
 */
public class Ovale extends Forme {
	private int centreX, centreY, rayon;
	private int rayonH, rayonV;

	/**
	 * Constructeur
	 * 
	 * @param nseq
	 *            Id de la forme
	 * @param nomForme
	 *            Nom de la forme
	 * @param centreX
	 *            Coordonn�e en X du centre
	 * @param centreY
	 *            Coordonn�e en Y du centre
	 * @param rayon
	 *            Rayon du cercle
	 */
	public Ovale(int nseq, String nomForme, int centreX, int centreY, int rayon) {
		super(nseq, nomForme);
		this.centreX = centreX;
		this.centreY = centreY;
		this.rayon = rayon;
		this.rayonH = rayon;
		this.rayonV = rayon;
	}

	/**
	 * Constructeur
	 * 
	 * @param nseq
	 *            Id de la forme
	 * @param nomForme
	 *            Nom de la forme
	 * @param centreX
	 *            Coordonn�e en X du centre
	 * @param centreY
	 *            Coordonn�e en Y du centre
	 * @param rayonH
	 *            Rayon horizontal du cercle
	 * @param rayonV
	 *            Rayon vertical du cercle
	 */
	public Ovale(int nseq, String nomForme, int centreX, int centreY,
			int rayonH, int rayonV) {
		super(nseq, nomForme);
		this.centreX = centreX;
		this.centreY = centreY;
		this.rayonH = rayonH;
		this.rayonV = rayonV;
	}

	/**
	 * 
	 * @return coordonn�e en X du centre
	 */
	public int getCentreX() {
		return centreX;
	}

	/**
	 * 
	 * @return coordonn�e en Y du centre
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
