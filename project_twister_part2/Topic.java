import java.util.ArrayList;

public class Topic {
    private String name;
    private String category;
    private ArrayList<Twist> twists = new ArrayList<Twist>();

    public Topic(String name, String category) {
        this.name = name;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ArrayList<Twist> getTwists() {
        return twists;
    }

    public void setTwists(ArrayList<Twist> twists) {
        this.twists = twists;
    }

    public void addTwist(Twist twist){
        this.twists.add(twist);
    }
}
