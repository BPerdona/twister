import java.util.ArrayList;

public class Topic {
    private String name;
    private String category;
    private ArrayList<Twist> twists = new ArrayList<Twist>();

    public Topic(String name, String category) {
        this.name = name;
        this.category = category;
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
    public String getCategory() {
        return category;
    }

    //Está função não foi usada
    public void setCategory(String category) {
        this.category = category;
    }

    //Está função não foi usada
    public ArrayList<Twist> getTwists() {
        return twists;
    }

    //Está função não foi usada
    public void setTwists(ArrayList<Twist> twists) {
        this.twists = twists;
    }

    public void addTwist(Twist twist){
        this.twists.add(twist);
    }

    public int getSizeTwists(){
        return this.twists.size();
    }
}
