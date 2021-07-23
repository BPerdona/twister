package br.com.brunoperdona.twister.entities;
import java.util.ArrayList;

public class List {
    private String name;
    private String description;
    private ArrayList<Account> members;

    public List(String name, String description) {
        this.name = name;
        this.description = description;
        this.members = new ArrayList<Account>();
    }

    //Construtor para adicionar uma lista sem categoria
    public List(String name){
        this.name = name;
        this.description = " ";
        this.members = new ArrayList<Account>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return description;
    }

    public void setCategory(String category) {
        this.description = category;
    }

    public ArrayList<Account> getMembers() {
        return members;
    }

}
