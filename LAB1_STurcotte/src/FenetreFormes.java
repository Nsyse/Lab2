/******************************************************
Cours:  LOG121
Projet: Squelette du laboratoire #1
Nom du fichier: FenetreFormes.java
Date créé: 2013-05-03
 *******************************************************
Historique des modifications
 *******************************************************
 *@author Patrice Boucher
2013-05-03 Version initiale
2015-01-30 Ajout de la communication avec la classe createurFormes
2015-01-31 Rectification de l'affichage des formes
 *******************************************************/

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;

/**
 * Cette fenêtre gère l'affichage des formes
 * 
 * @author Patrice Boucher
 * @date 2013/05/04
 */
public class FenetreFormes extends JComponent {

	private static final long serialVersionUID = -2262235643903749505L;
	public static final int WIDTH = 500;
	public static final int HEIGHT = 500;
	public static final Dimension dimension = new Dimension(500, 500);
	private CreateurFormes createurFormes;

	/**
	 * Constructeur
	 */
	public FenetreFormes() {
		createurFormes = new CreateurFormes();
	}

	/**
	 * Achemine l'information de la forme par le serveur au createurFormes
	 * 
	 * @param String
	 *            informationForme information de la forme
	 */
	public void nouvelleForme(String informationForme) {
		createurFormes.ajouterForme(informationForme);
	}

	/**
	 * Redessine les formes
	 */
	public void refresh() {
		this.repaint();
		this.updateUI();
	}

	/**
	 * Affichage des 10 formes
	 */
	@Override
	public void paintComponent(Graphics g) {
		if (createurFormes.getFormes()[0] != null) {
			for (int i = createurFormes.getFormes().length - 1; i >= 0; i--) {
				if (createurFormes.getFormes()[i] != null) {
					try {
						String nom = createurFormes.getFormes()[i]
								.getNomForme();
						switch (nom) {
						case "CARRE":
							if (nom.contentEquals("CARRE")) {
								g.setColor(Color.red);
							}
						case "RECTANGLE":
							if (nom.contentEquals("RECTANGLE")) {
								g.setColor(Color.blue);
							}
							Rectangle r = (Rectangle) createurFormes
									.getFormes()[i];
							g.fillRect(r.getX1(), r.getY1(), r.getWidth(),
									r.getHeight());
							break;
						case "CERCLE":
							if (nom.contentEquals("CERCLE")) {
								g.setColor(Color.gray);
							}
						case "OVALE":
							if (nom.contentEquals("OVALE")) {
								g.setColor(Color.green);
							}
							Ovale o = (Ovale) createurFormes.getFormes()[i];
							g.fillOval(o.getCentreX(), o.getCentreY(),
									o.getRayonH(), o.getRayonV());
							break;
						case "LIGNE":
							Ligne l = (Ligne) createurFormes.getFormes()[i];
							g.setColor(Color.black);
							g.drawLine(l.getX1(), l.getX2(), l.getY1(),
									l.getY2());
							break;
						}
					} catch (NullPointerException e) {

					}

				}
			}
		}
	}

	/*
	 * Le Layout qui utilise (contient) FenetreFormes doit réserver l'espace
	 * nécessaire à son affichage
	 */
	@Override
	public Dimension getPreferredSize() {
		return dimension;
	}
}
