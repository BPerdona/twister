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

    //O sistema nao permite adicionar uma lista de contas para adicionar uma lista
    public void setMembers(ArrayList<Account> members) {
        this.members = members;
    }

}
