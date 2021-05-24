import java.util.Scanner;

public class Topic {
    String name;
    String category;
    Twister topicTwister[] = new Twister[100];
    int numTwistTopic=0;


    public void showTwistTopic(){
        System.out.println("\n_____Topic Twist of "+name+"_____\n");
        for(int i=0; i<numTwistTopic; i++){
            System.out.println("User: @"+topicTwister[i].twistOwner);
            System.out.println(topicTwister[i].twist);
            System.out.println(" ");
        }
    }

    public void newTwistTopic(String Owner){
        Scanner sc = new Scanner(System.in);
        Twister tempTwist = new Twister();

        System.out.println("Write the Twist: ");
        tempTwist.twist = sc.nextLine();
        tempTwist.twistOwner = Owner;

        this.topicTwister[numTwistTopic] = tempTwist;
        this.numTwistTopic++;
    }
}


