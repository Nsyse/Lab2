/******************************************************
Cours:  LOG121
Projet: Squelette du laboratoire #1
Nom du fichier: Forme.java
Date créé: 2015-01-23
 *******************************************************
Historique des modifications
 *******************************************************
 *@author S�bastien Turcotte
2015-01-23 Version initiale
 *******************************************************/

/**
 * 
 * @author S�bastien
 *
 */
public abstract class Forme {

	private int nseq;
	private String nomForme;

	/**
	 * Constructeur
	 * 
	 * @param nseq
	 *            Id de la forme
	 * @param nomForme
	 *            Nom de la forme
	 */
	public Forme(int nseq, String nomForme) {
		this.nseq = nseq;
		this.nomForme = nomForme;
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
	 * @return id de la forme
	 */
	public int getNseq() {
		return nseq;
	}
}
