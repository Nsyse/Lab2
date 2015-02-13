/******************************************************
Cours:  LOG121
Projet: Squelette du laboratoire #1
Nom du fichier: FenetreCommunication.java
Date crÃ©Ã©: 2015-01-18
 *******************************************************
Historique des modifications
 *******************************************************
 *@author Sébastien Turcotte
2015-01-18 Version initiale
2015-02-01 Ajout du "multilangue"
 *******************************************************/

import javax.swing.JOptionPane;

/**
 * Message d'information pour l'utilisateur concernant le serveur
 * 
 * @author Sébastien
 *
 */
public class FenetreCommunication {

	private JOptionPane communication;

	private static final String ERREUR_HOST_MESSAGE = "app.frame.message.erreur.host.message",
			ERREUR_HOST_TITRE = "app.frame.message.erreur.host.title",
			ERREUR_PORT_MESSAGE = "app.frame.message.erreur.port.message",
			ERREUR_PORT_TITRE = "app.frame.message.erreur.port.title",
			ERREUR_SYNTAXE_MESSAGE = "app.frame.message.erreur.syntaxe.message",
			ERREUR_SYNTAXE_TITRE = "app.frame.message.erreur.syntaxe.title",
			INFORMATION_SERVEUR_MESSAGE = "app.frame.message.information.serveur.message",
			INFORMATION_SERVEUR_TITRE = "app.frame.message.information.serveur.title",
			CONNEXION_SERVEUR_MESSAGE = "app.frame.message.serveur.connexion.message",
			CONNEXION_SERVEUR_TITRE = "app.frame.message.serveur.connexion.title";

	/**
	 * Constructeur
	 */
	public FenetreCommunication() {
		communication = new JOptionPane();
	}

	/**
	 * Demande à l'utilisateur de rentrer le nom de l'hôte et le numéro du port.
	 * Valeur par défaut du nom de l'hôte : 127.0.0.1 et du numéro de port :
	 * 10000
	 * 
	 * @return String Retourne le nom de l'hôte et le numéro de port sous la
	 *         forme hôte:port ex : localhost:10000
	 * 
	 */
	public String getConnexionInformation() {
		return (String) communication.showInputDialog(null,
				LangueConfig.getResource(CONNEXION_SERVEUR_MESSAGE),
				LangueConfig.getResource(CONNEXION_SERVEUR_TITRE),
				JOptionPane.PLAIN_MESSAGE, null, null, "localhost:10000");
	}

	/**
	 * Informe l'utilisateur que le nom de l'hôte est invalide
	 */
	public void nomHostInvalide() {
		setInformationMessage(LangueConfig.getResource(ERREUR_HOST_MESSAGE),
				LangueConfig.getResource(ERREUR_HOST_TITRE));
	}

	/**
	 * Informe l'utilisateur que le port est invalide
	 */
	public void nomPortInvalide() {
		setInformationMessage(LangueConfig.getResource(ERREUR_PORT_MESSAGE),
				LangueConfig.getResource(ERREUR_PORT_TITRE));
	}

	/**
	 * Informe l'utilisateur que la syntaxe est incorrect
	 */
	public void syntaxeInvalide() {
		setInformationMessage(LangueConfig.getResource(ERREUR_SYNTAXE_MESSAGE),
				LangueConfig.getResource(ERREUR_SYNTAXE_TITRE));
	}

	/**
	 * Informe l'utilisateur que le serveur est indisponible
	 */
	public void serveurIndisponible() {
		setInformationMessage(
				LangueConfig.getResource(INFORMATION_SERVEUR_MESSAGE),
				LangueConfig.getResource(INFORMATION_SERVEUR_TITRE));
	}

	/**
	 * Affiche un message d'information à l'utilisateur
	 * 
	 * @param String
	 *            message Affiche le message
	 * @param String
	 *            title Affiche le titre
	 */
	private void setInformationMessage(String message, String title) {
		communication.showConfirmDialog(null, message, title,
				JOptionPane.PLAIN_MESSAGE);
	}

	/**
	 * Affiche un message d'erreur à l'utilisateur
	 * 
	 * @param String
	 *            message Affiche le message
	 * @param String
	 *            title Affiche le titre
	 */
	private void setErrorMessage(String message, String title) {
		communication.showConfirmDialog(null, message, title,
				JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Affiche un message d'avertissement à l'utilisateur
	 * 
	 * @param String
	 *            message Affiche le message
	 * @param String
	 *            title Affiche le titre
	 */
	private void setWarningMessage(String message, String title) {
		communication.showConfirmDialog(null, message, title,
				JOptionPane.WARNING_MESSAGE);
	}
}
