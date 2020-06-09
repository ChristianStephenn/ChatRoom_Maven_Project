package Presentation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Singletons {

    private static Gson gsonInstance;

    public static Gson getGson(){
        if(gsonInstance == null){
            gsonInstance = new GsonBuilder()
                    .create();
        }

        return gsonInstance;
    }
}
