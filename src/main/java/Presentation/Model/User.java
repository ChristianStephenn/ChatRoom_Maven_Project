package Presentation.Model;

import java.io.Serializable;

/**
  * <b>User est une classe qui permet d'obtenir toutes les informations nécessaires lorsqu'il se connecte sur Chat Room.</b>
  * <p>
  * User contient :
  * <ul>
  * <li>Nom</li>
  * <li>Ip</li>
  * <li>Port</li>
  *</ul>
  * </p>
  */
public class User implements Serializable {
    
    /**
     * Le nom du User.
     */
    private String name;
    
    /**
     * L'ip du User.
     */
    private String ip;
    
    /**
     * Le port du User.
     */
    private int port;

    /**
     * Constructeur User.
     *
     * @param name
     *            Le nom du User.
     * @param ip
     *            L'ip du User.
     * @param port
     *            Le port du User.
     */
    public User(String name, String ip, int port) {
        this.name = name;
        this.ip = ip;
        this.port = port;
    }
    
    /**
     * Retourne le nom du User.
     *
     * @return Le nom correspondant, chaîne de caractères.
     */
    public String getName() {
        return name;
    }

    /**
     * Retourne l'ip du User.
     *
     * @return L'ip correspondant, entier.
     */
    public String getIp() {
        return ip;
    }
    /**
     * Retourne le port du User.
     *
     * @return Le port correspondant, entier.
     */
    public int getPort() {
        return port;
    }
}
