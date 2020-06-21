package Presentation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
  * <b>Singletons est une classe qui permet de respecter le design pattern singleton.</b>
  * <p>
  * Singletons contient :
  * <ul>
  * <li>Instance gson</li>
  *</ul>
  * </p>
  */

public class Singletons {

    /**
     * L'instance Gson.
     */
    private static Gson gsonInstance;

    /**
     * Retourne l'instance du Gson.
     *
     * @return les messages de type String sont convertis sous forme Gson et inversement. Cette opération est possible grâce à la méthode create() de Gson.
     */
    public static Gson getGson(){
        if(gsonInstance == null){
            gsonInstance = new GsonBuilder()
                    .create();
        }
        return gsonInstance;
    }
}
