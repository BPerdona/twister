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

/*
* TODO->>
*  organize seu projeto em pelo menos 2 pacotes (10% da nota);
   seu projeto deve possuir pelo menos uma classe abstrata e duas classes que estendem (herança) esta classe abstrata, além disso, esta herança deve fazer sentido (30% da nota);
   sua classe abstrata deve possuir pelo menos um método abstrato e as classes filhas devem implementá-lo (sobrescrita) (20% da nota)
   suas classes devem possuir pelo menos dois métodos construtores (sobrecarga) (10% da nota);
   deve ser possível, em tempo de execução, listar os objetos que estendem a classe abstrata utilizando referências para a classe mãe abstrata (polimorfismo) (30% da nota).
* */
