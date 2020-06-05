package Presentation.Model;

import java.io.Serializable;

public class User implements Serializable {
    private int id ;
    private String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID(){
        return id;
    }
}
