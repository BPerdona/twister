package br.com.brunoperdona.twister.entities;

import br.com.brunoperdona.twister.settings.Settings;
import java.util.ArrayList;

public abstract class User {

    private String name;
    private String password;
    private String email;
    private String at;
    private Settings settings;
    private ArrayList<Twist> twisters;

    public User(String name, String password, String email, String at) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.at = at;
        this.settings = new Settings();
        this.twisters = new ArrayList<Twist>();
    }

    //construtor para iniciar um conta com settings expecificas
    public User(String name,String password, String email, String at, Settings settings){
        this.name = name;
        this.email = email;
        this.password = password;
        this.at = at;
        this.settings = settings;
        this.twisters = new ArrayList<Twist>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if(password.isBlank())
            return;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(email.isBlank())
            return;
        this.email = email;
    }

    public String getAt() {
        return at;
    }

    public void setAt(String at) {
        if(at.isBlank())
            return;
        this.at = at;
    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public ArrayList<Twist> getTwisters() {
        return twisters;
    }

    public abstract String typeOf();

}

