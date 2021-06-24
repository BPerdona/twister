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

    /*Após criar um topico nao se pode muda-lo, não faz sentido mudar um topico de filme para esportes e continuar os
    twists que estavam comentando sobre o assunto antigo*/
    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    //Como comentado acima, não faz sentido mudar esse atributo
    public void setCategory(String category) {
        this.category = category;
    }

    public ArrayList<Twist> getTwists() {
        return twists;
    }

    //Não pode adicionar uma lista direta a um topic por conta da regra de negocio
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
