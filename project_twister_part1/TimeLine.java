public class TimeLine {

    Twister userTwister[] = new Twister[100];
    int numTwister = 0;

    void showTimeLine(Account friend[], int nFriends){
        System.out.println("_____Time Line____");
        for(int i=0; i<numTwister; i++){
            for(int j=0; j<nFriends; j++){
                if(userTwister[i].twistOwner.equals(friend[j].at)) {
                    System.out.println("\n@" + userTwister[i].twistOwner);
                    System.out.println(userTwister[i].twist);
                }
            }
        }
    }
}
