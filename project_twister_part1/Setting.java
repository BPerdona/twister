import java.util.Scanner;

public class Setting {
    boolean darkTheme;
    boolean notifications;

    public void showSettings() {
        System.out.println("\n_____Account Settings_____\n");
        System.out.println("Dark Theme: "+this.darkTheme);
        System.out.println("Notification: "+this.notifications);
    }

    public void changeSettings(){
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\r?\n");
        System.out.println("\n_____Change Settings_____\n");
        System.out.println("DarkTheme: (1-On 2-Off)");
        int settingsTemp = sc.nextInt();
        if(settingsTemp==1){
            this.darkTheme = true;
        }
        if(settingsTemp==2){
            this.darkTheme = false;
        }
        System.out.println("Notifications: (1-On 2-Off)");
        settingsTemp = sc.nextInt();
        if(settingsTemp==1){
            this.notifications = true;
        }
        if(settingsTemp==2){
            this.notifications = false;
        }
    }
}
