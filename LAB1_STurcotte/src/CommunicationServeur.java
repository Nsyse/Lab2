/******************************************************
Cours:  LOG121
Projet: Squelette du laboratoire #1
Nom du fichier: CommunicationServeur.java
Date crÃ©Ã©: 2015-01-17
 *******************************************************
Historique des modifications
 *******************************************************
 *@author Sébastien Turcotte
2015-01-17 Version initiale
2015-01-18 Correctif du retour de l'information par le serveur
 *******************************************************/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Communication avec un serveur.
 * 
 * @author SEBT
 *
 */
public class CommunicationServeur {

	private Socket socket;
	private String dns;
	private int port;
	private volatile boolean connect;
	private PrintWriter envoiInformation;
	private Thread recevoir;
	private BufferedReader recoitInformation;
	private volatile String informationRecu;
	private CommBase comm;

	/**
	 * Constructeur
	 * 
	 * @param comm
	 *            La classe CommBase permet de retourner l'information reçu par
	 *            le serveur à la classe de communication du programme
	 */
	public CommunicationServeur(CommBase comm) {
		//test
		this.comm = comm;
		init();
		closingOperation();
	}

	/**
	 * Remet les variables à null
	 */
	private void init() {
		socket = null;
		envoiInformation = null;
		recevoir = null;
		recoitInformation = null;
		informationRecu = "";
		connect = false;
	}

	/**
	 * La commmunication avec le serveur est fermer à la fermeture de
	 * l'application
	 */
	private void closingOperation() {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				if (connect == true) {
					stopSocketConnexion();
				}
			}
		});
	}

	/**
	 * Actionne le début de la communication du serveur
	 * 
	 * @param dns
	 *            String qui contient le nom de l'hôte du serveur
	 * @param port
	 *            Entier qui correspond au port du serveur
	 * 
	 * @return Retourne un entier entre 0 et 2. 
	 * 			0 : La communication avec le serveur fonctionne 
	 *         	1 : Un problème avec le nom de l'hôte empêche la connexion avec le serveur 
	 *          2 : Un problème avec la syntaxe empêche la connexion avec le serveur
	 * 
	 */
	public int startSocketConnexion(String dns, int port) {
		int etat = 0;
		if (!connect) {
			this.dns = dns;
			this.port = port;
			connect = false;
			try {
				socket = new Socket(dns, port);
				envoiInformation = new PrintWriter(socket.getOutputStream());
				recoitInformation = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
				recevoirInformation();
				connect = true;
			} catch (UnknownHostException e) {
				etat = 1;
			} catch (IllegalArgumentException e) {
				etat = 2;
			} catch (IOException e) {

				etat = 2;
			}
		}
		return etat;
	}

	/**
	 * Arrête la communication avec le serveur
	 */
	public void stopSocketConnexion() {
		if (connect) {
			envoyerInformation("END");
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			connect = false;
			recevoir.interrupted();
			envoiInformation.close();
			try {
				recoitInformation.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			init();
		}
	}

	/**
	 * Envoi une chaîne de charactères au serveur
	 * 
	 * @param information
	 *            String qui contient l'information que le serveur doit recevoir
	 */
	public void envoyerInformation(String information) {
		if (connect) {
			envoiInformation.println(information);
			envoiInformation.flush();
		}
	}

	/**
	 * Recoit une chaîne de charactères du serveur
	 */
	private void recevoirInformation() {
		if (!connect && (recevoir == null)) {
			recevoir = new Thread() {
				@Override
				public void run() {
					do {
						try {
							Thread.sleep(0);
							comm.recoiInformationServeur(recoitInformation
									.readLine());
						} catch (IOException e) {
						} catch (InterruptedException ex) {
							Thread.currentThread().interrupt();
							break;
						}
					} while (connect);
				}
			};
			recevoir.start();
		}
	}

	/**
	 * Définie si la communication avec le serveur est active ou non
	 * 
	 * @return Boolean
	 * 
	 */
	public boolean isConnect() {
		return connect;
	}
}
