public class Account {
    String name;
    String password;
    String email;
    String at;
    int numTwist=0;
    int numFriend=0;
    Twister[] twister = new Twister[100];
    Setting setting = new Setting();
    Account friend[] = new Account[100];


    public void showProfile(){
        System.out.println("\n_____Profile_____\n");
        System.out.println("Name: " + this.name);
        System.out.println("Password: " + this.password);
        System.out.println("Email: " + this.email);
        System.out.println("@" + this.at);

    }

    public void showTwist(){
        System.out.println("\n_____Your's Twists_____\n");

        for(int i=0; i<this.numTwist; i++){
            System.out.println("Twist: ["+i+"]");
            System.out.println(this.twister[i].twist);
            System.out.println("Characters: ["+this.twister[i].numCaracter+"]\n");
        }
    }

    public void showFriends(){
        System.out.println("\n_____Friends_____\n");
        for(int i=0; i<this.numFriend; i++){
            System.out.println("\nFriend: ["+i+"]");
            System.out.println("Name: "+this.friend[i].name);
            System.out.println("at: @"+this.friend[i].at);
        }
    }

    public boolean verifyFriend(Account verify){
        for(int i=0; i<numFriend; i++){
            if(verify.name.equals(this.friend[i].name)){
                return false;
            }
        }
        return true;
    }

}
