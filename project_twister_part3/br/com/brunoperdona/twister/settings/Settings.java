package br.com.brunoperdona.twister.settings;

public class Settings {
    private boolean darkTheme;
    private boolean notification;

    public Settings(boolean darkTheme, boolean notification) {
        this.darkTheme = darkTheme;
        this.notification = notification;
    }

    //Construtor para inicializar o objeto com opções default
    public Settings(){
        this.darkTheme = true;
        this.notification = false;
    }

    public boolean isDarkTheme() {
        return darkTheme;
    }

    public boolean isNotification() {
        return notification;
    }

}
