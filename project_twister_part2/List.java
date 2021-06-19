import java.util.ArrayList;

public class List {
    private String name;
    private String description;
    private ArrayList<Account> members = new ArrayList<Account>();
    private ArrayList<Twist> timeLineList = new ArrayList<Twist>();

    public List(String name, String description) {
        this.name = name;
        this.description = description;
    }

    //Está função não foi usada
    public String getName() {
        return name;
    }

    //Está função não foi usada
    public void setName(String name) {
        this.name = name;
    }

    //Está função não foi usada

    public String getDescription() {
        return description;
    }

    //Está função não foi usada

    public void setDescription(String description) {
        this.description = description;
    }

    //Está função não foi usada
    public ArrayList<Account> getMembers() {
        return members;
    }

    //Está função não foi usada
    public void setMembers(ArrayList<Account> members) {
        this.members = members;
    }

    //Está função não foi usada
    public ArrayList<Twist> getTimeLineList() {
        return timeLineList;
    }

    public void setTimeLineList(ArrayList<Twist> timeLineList) {
        this.timeLineList = timeLineList;
    }
}
