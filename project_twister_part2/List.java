import java.util.ArrayList;

public class List {
    private String name;
    private String description;
    private ArrayList<Account> members = new ArrayList<Account>();
    private ArrayList<Twist> timeLineList = new ArrayList<Twist>();

    public List(String name, String description, ArrayList<Account> members, ArrayList<Twist> timeLineList) {
        this.name = name;
        this.description = description;
        this.members = members;
        this.timeLineList = timeLineList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Account> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<Account> members) {
        this.members = members;
    }

    public ArrayList<Twist> getTimeLineList() {
        return timeLineList;
    }

    public void setTimeLineList(ArrayList<Twist> timeLineList) {
        this.timeLineList = timeLineList;
    }
}
