import java.util.ArrayList;

public class Account {

    private String name;
    private String password;
    private String email;
    private String at;
    private ArrayList<Twist> twisters;
    private ArrayList<List> lists;
    private Settings settings;
    private ArrayList<Account> friend;

    public Account(String name, String password, String email, String at,  Settings settings) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.at = at;
        this.settings = settings;
        this.settings = new Settings(false, true);
        this.twisters = new ArrayList<Twist>();
        this.lists = new ArrayList<List>();
        this.friend = new ArrayList<Account>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name.isBlank())
            return;
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

    public ArrayList<Twist> twisters() {
        return twisters;
    }

    public ArrayList<List> getLists() {
        return lists;
    }

    public Settings getSettings() {
        return settings;
    }

    public ArrayList<Account> getFriend() {
        return friend;
    }

    public void deleteFriend(int index){
        if(index>friend.size()){
            return;
        }
        if(index<0){
            return;
        }
        this.friend.remove(index);
    }
}
