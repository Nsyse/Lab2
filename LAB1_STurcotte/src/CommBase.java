/******************************************************
Cours:  LOG121
Projet: Squelette du laboratoire #1
Nom du fichier: CommBase.java
Date créé: 2013-05-03
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
 * Base d'une communication via un fil d'exécution parallèle.
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
	 * D�finir le r�cepteur de l'information re�ue dans la communication avec le
	 * serveur
	 * 
	 * @param listener
	 *            sera alerté lors de l'appel de "firePropertyChanger" par le
	 *            SwingWorker
	 */
	public void setPropertyChangeListener(PropertyChangeListener listener) {
		this.listener = listener;
	}

	/**
	 * Démarre la communication
	 */
	public void start() {
		creerCommunication();
	}

	/**
	 * Arrête la communication
	 */
	public void stop() {
		if (threadComm != null)
			threadComm.cancel(true);
		isActif = false;
	}

	/**
	 * Donne acc�s au menu
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
	 * Arr�te le serveur & demande de recr�er la connexion avec le serveur
	 */
	private void deductionServeurFermer() {
		stopCommunicationServeur();
		menu.rafraichirMenus();
		fen.serveurIndisponible();
		creerCommunicationServeur();
	}

	/**
	 * Arr�te la communication avec le serveur
	 */
	public void stopCommunicationServeur() {
		stop();
		commServ.stopSocketConnexion();
		commServ = null;
	}

	/**
	 * Créer le nécessaire pour la communication avec le serveur
	 */
	protected void creerCommunication() {
		// Crée un fil d'exécusion parallèle au fil courant,
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
					// La méthode suivante alerte l'observateur
					if (listener != null && newForme) {
						firePropertyChange("FORME", null, (Object) formes);
						newForme = false;
					}
				}
			}
		};
		if (listener != null)
			threadComm.addPropertyChangeListener(listener); // La méthode
															// "propertyChange"
															// de
															// ApplicationFormes
															// sera donc
															// appelée lorsque
															// le SwinkWorker
															// invoquera la
															// méthode
															// "firePropertyChanger"
		threadComm.execute(); // Lance le fil d'exécution parallèle.
		isActif = true;
	}

	/**
	 * Prend l'information que le serveur � envoy�.
	 * 
	 * @param information
	 *            String qui contient l'information pour dessiner une forme
	 */
	public void recoiInformationServeur(String information) {
		// Code repris et modifi� � partir du site :
		// http://cyberzoide.developpez.com/tutoriels/java/regex/
		// d�but
		pattern = Pattern.compile("commande");
		matcher = pattern.matcher(information);
		if (!matcher.find()) {
			formes = information;
			newForme = true;
		}
		// fin
	}

	/**
	 * @return si le fil d'exécution parallèle est actif
	 */
	public boolean isActif() {
		return isActif;
	}
}
