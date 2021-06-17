public class Settings {
    private boolean darkTheme;
    private boolean notification;

    public Settings(boolean darkTheme, boolean notification) {
        this.darkTheme = darkTheme;
        this.notification = notification;
    }

    public boolean isDarkTheme() {
        return darkTheme;
    }

    public void setDarkTheme(boolean darkTheme) {
        this.darkTheme = darkTheme;
    }

    public boolean isNotification() {
        return notification;
    }

    public void setNotification(boolean notification) {
        this.notification = notification;
    }
}
