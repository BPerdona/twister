import java.util.ArrayList;

public class Account {

    private String name;
    private String password;
    private String email;
    private String at;
    private ArrayList<Twist> twisters = new ArrayList<Twist>();
    private ArrayList<List> lists = new ArrayList<List>();
    private Settings settings = new Settings(false, true);
    private ArrayList<Account> friend = new ArrayList<Account>();

    /*
    Foram retirados do classe contrutor os seguintes atributos: timeLine, twisters, lists, friend.
    Isso porque eu quero essas variaveis sem inicialização podendo fazer uma verificação se elas estão null ou não
    Alem de não fazer sentido adicionar amigos a uma nova conta em sua criação
    */
    public Account(String name, String password, String email, String at,  Settings settings) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.at = at;
        this.settings = settings;
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

    public void setAccountTwister(ArrayList<Twist> accountTwister) {
        this.twisters = twisters;
    }

    public ArrayList<List> getLists() {
        return lists;
    }

    public void setLists(ArrayList<List> lists) {
        this.lists = lists;
    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public ArrayList<Account> getFriend() {
        return friend;
    }

    public void setFriend(ArrayList<Account> friend) {
        this.friend = friend;
    }

    public void addTwist(Twist twist){
        this.twisters.add(twist);
    }

    public void deleteTwist(int index){
        if(index<twisters.size()){
            return;
        }
        if(index<0){
            return;
        }
        this.twisters.remove(index);
    }

    public void addFriend(Account friend){
        this.friend.add(friend);
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
