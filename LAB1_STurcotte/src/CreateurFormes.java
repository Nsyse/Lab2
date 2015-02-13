/******************************************************
 * Cours: LOG121 
 * Projet: Squelette du laboratoire #1 
 * Nom du fichier: CreateurFormes.java 
 * Date crÃ©Ã©: 2015-01-30
 *******************************************************
 * Historique des modifications
 *******************************************************
 * @author Sébastien Turcotte 
 * 2015-01-30 Version initiale
 *******************************************************/

import ca.etsmtl.log.util.IDLogger;

/**
 * Gère la création de nouvelles formes & gère le tableau des formes créées
 * 
 * @author Sébastien
 *
 */
public class CreateurFormes {

	private IDLogger logger;
	private Forme[] formes;

	/**
	 * Constructeur
	 */
	public CreateurFormes() {
		logger = IDLogger.getInstance();
		formes = new Forme[10];
	}

	/**
	 * 
	 * @param String
	 *            Prend l'information du serveur pour créer une forme et
	 *            l'ajouter au tableau
	 */
	public void ajouterForme(String informationForme) {
		ajouterFormeListe(creerForme(new SeparateurForme(informationForme)));
	}

	/**
	 * Crée une forme
	 * 
	 * @param SeparateurForme
	 *            Prend le SeparateurForme pour obtenir les informations de la
	 *            forme
	 * @return Forme Retourne une forme
	 */
	private Forme creerForme(SeparateurForme sf) {
		Forme forme = null;
		logger.logID(sf.getNseq());
		switch (sf.getNomForme()) {
		case "LIGNE":
			forme = new Ligne(sf.getNseq(), sf.getNomForme(), sf.getX1(),
					sf.getY1(), sf.getX2(), sf.getY2());
			break;
		case "CARRE":
		case "RECTANGLE":
			forme = new Rectangle(sf.getNseq(), sf.getNomForme(), sf.getX1(),
					sf.getY1(), sf.getX2(), sf.getY2());
			break;
		case "CERCLE":
			forme = new Ovale(sf.getNseq(), sf.getNomForme(), sf.getCentreX(),
					sf.getCentreY(), sf.getRayon());
			break;
		case "OVALE":
			forme = new Ovale(sf.getNseq(), sf.getNomForme(), sf.getCentreX(),
					sf.getCentreY(), sf.getRayonH(), sf.getRayonV());
			break;
		}
		return forme;
	}

	/**
	 * Met en ordre le tableau à fois qu'une forme y est ajoutée
	 * 
	 * @param Forme
	 * 			Prend l'objet Forme pour l'ajouter au tableau 
	 */
	private void ajouterFormeListe(Forme forme) {
		for (int i = formes.length - 1; i >= 0; i--) {
			if (formes[i] != null && i == (formes.length - 1)) {
				formes[i] = null;
			} else if (formes[i] != null) {
				formes[i + 1] = formes[i];
				formes[i] = null;
			}
		}
		formes[0] = forme;
	}

	public Forme[] getFormes() {
		return formes;
	}
}
