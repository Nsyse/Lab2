/******************************************************
Cours:  LOG121
Projet: Squelette du laboratoire #1
Nom du fichier: CommBase.java
Date cr√©√©: 2013-05-03
 *******************************************************
Historique des modifications
 *******************************************************
 *@author Patrice Boucher
2013-05-03 Version initiale
2015-01-18 Implantation de la communication avec le serveur
2015-01-23 Correctif avec le serveur
 *******************************************************/

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.regex.*;
import javax.swing.SwingWorker;

/**
 * Base d'une communication via un fil d'ex√©cution parall√®le.
 */
public class CommBase {

	private final int DELAI = 1000;
	private SwingWorker threadComm = null;
	private CommunicationServeur commServ = null;
	private FenetreCommunication fen = null;
	private PropertyChangeListener listener = null;
	private Pattern pattern;
	private Matcher matcher;
	private boolean isActif = false, newForme = false;
	private int compteur = 0;
	private String formes;
	private MenuFenetre menu;

	/**
	 * Constructeur
	 */
	public CommBase() {
		fen = new FenetreCommunication();
		creerCommunicationServeur();
	}

	/**
	 * DÈfinir le rÈcepteur de l'information reÁue dans la communication avec le
	 * serveur
	 * 
	 * @param listener
	 *            sera alert√© lors de l'appel de "firePropertyChanger" par le
	 *            SwingWorker
	 */
	public void setPropertyChangeListener(PropertyChangeListener listener) {
		this.listener = listener;
	}

	/**
	 * D√©marre la communication
	 */
	public void start() {
		creerCommunication();
	}

	/**
	 * Arr√™te la communication
	 */
	public void stop() {
		if (threadComm != null)
			threadComm.cancel(true);
		isActif = false;
	}

	/**
	 * Donne accËs au menu
	 * 
	 * @param menu
	 * 
	 */
	public void menuAccess(MenuFenetre menu) {
		this.menu = menu;
	}

	/**
	 * Initialise une connexion avec le serveur
	 */
	public void creerCommunicationServeur() {
		commServ = new CommunicationServeur(this);
		String s = fen.getConnexionInformation();
		int etat = -1;
		try {
			etat = commServ.startSocketConnexion(s.split(":")[0],
					Integer.parseInt(s.split(":")[1]));
			if (etat == 1) {
				fen.nomHostInvalide();
			} else if (etat == 2) {
				fen.serveurIndisponible();
			}
		} catch (NumberFormatException e) {
			fen.nomPortInvalide();
		} catch (ArrayIndexOutOfBoundsException e) {
			fen.syntaxeInvalide();
		} catch (NullPointerException e) {
			System.exit(0);
		}
		if (etat != 0) {
			stopCommunicationServeur();
			creerCommunicationServeur();
		}
	}

	/**
	 * ArrÍte le serveur & demande de recrÈer la connexion avec le serveur
	 */
	private void deductionServeurFermer() {
		stopCommunicationServeur();
		menu.rafraichirMenus();
		fen.serveurIndisponible();
		creerCommunicationServeur();
	}

	/**
	 * ArrÍte la communication avec le serveur
	 */
	public void stopCommunicationServeur() {
		stop();
		commServ.stopSocketConnexion();
		commServ = null;
	}

	/**
	 * Cr√©er le n√©cessaire pour la communication avec le serveur
	 */
	protected void creerCommunication() {
		// Cr√©e un fil d'ex√©cusion parall√®le au fil courant,
		threadComm = new SwingWorker() {
			@Override
			protected Object doInBackground() throws Exception {
				while (true) {
					Thread.sleep(DELAI);
					do {
						if ((commServ != null) && ((compteur == 0))) {
							commServ.envoyerInformation("GET");
						}
						Thread.sleep(10);
						if (compteur == 3) {
							deductionServeurFermer();
							break;
						} else {
							compteur++;
						}
					} while (!newForme);
					compteur = 0;
					// La m√©thode suivante alerte l'observateur
					if (listener != null && newForme) {
						firePropertyChange("FORME", null, (Object) formes);
						newForme = false;
					}
				}
			}
		};
		if (listener != null)
			threadComm.addPropertyChangeListener(listener); // La m√©thode
															// "propertyChange"
															// de
															// ApplicationFormes
															// sera donc
															// appel√©e lorsque
															// le SwinkWorker
															// invoquera la
															// m√©thode
															// "firePropertyChanger"
		threadComm.execute(); // Lance le fil d'ex√©cution parall√®le.
		isActif = true;
	}

	/**
	 * Prend l'information que le serveur ‡ envoyÈ.
	 * 
	 * @param information
	 *            String qui contient l'information pour dessiner une forme
	 */
	public void recoiInformationServeur(String information) {
		// Code repris et modifiÈ ‡ partir du site :
		// http://cyberzoide.developpez.com/tutoriels/java/regex/
		// dÈbut
		pattern = Pattern.compile("commande");
		matcher = pattern.matcher(information);
		if (!matcher.find()) {
			formes = information;
			newForme = true;
		}
		// fin
	}

	/**
	 * @return si le fil d'ex√©cution parall√®le est actif
	 */
	public boolean isActif() {
		return isActif;
	}
}
