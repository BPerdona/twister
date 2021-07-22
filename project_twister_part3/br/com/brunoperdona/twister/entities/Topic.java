package br.com.brunoperdona.twister.entities;
import java.util.ArrayList;

public class Topic {
    private String name;
    private String category;
    private ArrayList<Twist> twists;

    public Topic(String name, String category) {
        this.name = name;
        this.category = category;
        this.twists = new ArrayList<Twist>();
    }

    //Novo construtor para adicionar tipico sem receber uma categoria
    public Topic(String name){
        this.name = name;
        this.category = "Outros";
        this.twists = new ArrayList<Twist>();
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public ArrayList<Twist> getTwists() {
        return twists;
    }

    public void addTwist(Twist twist){
        this.twists.add(twist);
    }

    public int getSizeTwists(){
        return this.twists.size();
    }
}
