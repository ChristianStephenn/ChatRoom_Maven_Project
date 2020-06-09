package Presentation.Model;

import java.io.Serializable;

/**
 * <b>Message est une classe qui permet d'obtenir toutes les informations necessaires lorsque le message est envoyé sur Chat Room.</b>
 * <p>
 * Message contient :
 * <ul>
 * <li>Nom de expediteur</li>
 * <li>Port</li>
 * <li>Texte du message</li>
 * <li>Date</li>
 * </p>
 */
public class Message implements Serializable {

    /**
     * Le nom du l'expediteur du Message.
     */
    private String SenderName;
    
    /**
     * Le port du Message.
     */
    private int port;
    
    /**
     * Le texte du Message.
     */
    private String Text;
    
    /**
     * La date du Message.
     */
    private String date;

    /**
     * Constructeur Message.
     *
     * @param senderName
     *            Le nom de l'expediteur du Message.
     * @param port
     *            Le port du Message.
     * @param text
     *            Le texte du Message.
     * @param date
     *            La date du Message.
     */
    public Message(String senderName, int port, String text, String date) {
        this.SenderName = senderName;
        this.port = port;
        this.Text = text;
        this.date = date;
    }

    /**
     * Retourne le port du Message.
     *
     * @return Le port correspondant, entier.
     */
    public int getPort() {
        return port;
    }

    /**
     * Retourne le nom de l'expediteur du Message.
     *
     * @return Le nom de l'expediteur correspondant, chaîne de caractères.
     */
    public String getSenderName() {
        return SenderName;
    }

    /**
     * Retourne le texte du Message.
     *
     * @return Le texte correspondant, chaîne de caractères.
     */
    public String getText() {
        return Text;
    }

    /**
     * Retourne la date du Message.
     *
     * @return La date correspondante, chaîne de caractères.
     */
    public String getDate() {
        return date;
    }
}
