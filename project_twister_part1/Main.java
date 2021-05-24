import java.util.Scanner;

public class Main {
    static Account userTwister[] = new Account[100];
    static TimeLine userTimeLine = new TimeLine();
    static Topic globalTopic[] = new Topic[100];
    static int numTopic = 0;
    static int nAccounts = 0;
    static int userIndex = -1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\r?\n");
        boolean loginVerification = true;

        //Função para adicionar alguns dados e uma conta
        initialData();

        while (loginVerification) {
            println("\n\n_____Welcome to Twister Login!______");
            println("\nSelect an option below: ");
            println("(1) Login");
            println("(2) Register");
            println("(9) Exit");
            println(" ");
            int optionLogin = sc.nextInt();

            switch (optionLogin) {
                case 1:
                    String auxEmail, auxPassword;
                    int indexAccount = -1;

                    System.out.println("Insert your email : ");
                    auxEmail = sc.next();
                    System.out.println("Insert your password: ");
                    auxPassword = sc.next();

                    for (int i = 0; i < nAccounts; i++) {
                        if (userTwister[i].email.equals(auxEmail)) {
                            indexAccount = i;
                        }
                    }

                    if (indexAccount == -1) {
                        println("\n>>>> Email not found! <<<<\n");
                        break;
                    }

                    if (userTwister[indexAccount].email.equals(auxEmail) && userTwister[indexAccount].password.equals(auxPassword)) {
                        println("\n>>>> Login successfully!! <<<<\n");
                        userIndex = indexAccount;

                        //Obtendo um conta que fez login
                        Account user = userTwister[indexAccount];

                        boolean login = true;
                        while (login) {
                            println("\n_____Welcome to Twister "+user.name+"______\n");
                            println("Select an option below:");
                            println("(1) Posts");
                            println("(2) Profile");
                            println("(3) Topics");
                            println("(4) Settings");
                            println("(9) Logout");
                            int optionMain = sc.nextInt();

                            switch (optionMain) {
                                case 1:

                                    boolean loginPost = true;
                                    while (loginPost) {
                                        println("\n_____Post_____\n");
                                        println("(1) New Post");
                                        println("(2) List your posts");
                                        println("(3) Time Line");
                                        println("(4) Add Friend");
                                        println("(5) List Friends");
                                        println("(9) Back to Main menu");
                                        int postOption = sc.nextInt();

                                        switch (postOption) {
                                            case 1:

                                                Twister tempTwist = new Twister();

                                                System.out.println("\n_____New Post_____\n");
                                                System.out.println("Write your Twist: ");
                                                tempTwist.twist = sc.next();
                                                tempTwist.numCaracter = tempTwist.countCaracter();

                                                if (tempTwist.numCaracter <= 100) {
                                                    tempTwist.twistOwner = user.at;
                                                    user.twister[user.numTwist] = tempTwist;
                                                    user.numTwist++;
                                                    userTimeLine.userTwister[userTimeLine.numTwister] = tempTwist;
                                                    userTimeLine.numTwister++;
                                                    println("\n>>>> Twist Done! <<<<\n");
                                                } else {
                                                    println("\n>>>> You couldn't do this Twist <<<<");
                                                    println(">>>> The limit for a Twist is 100 characters! <<<<");
                                                }

                                                break;

                                            case 2:

                                                user.showTwist();

                                                break;

                                            case 3:

                                                userTimeLine.showTimeLine(user.friend, user.numFriend);

                                                break;

                                            case 4:
                                                println("\n_____Users List_____\n");

                                                for(int i=0; i<nAccounts; i++){
                                                    if(user.verifyFriend(userTwister[i])){
                                                        if(userIndex!=i){
                                                            println("\nUser: ["+i+"]");
                                                            println("Name: "+userTwister[i].name);
                                                            println("At: @"+userTwister[i].at);
                                                        }else{
                                                            println("\nUser: ["+i+"]");
                                                            println("You are here!!!");
                                                        }
                                                    }
                                                }

                                                println("\nWhich user do you want to add?");
                                                int selectAccount = sc.nextInt();

                                                if(selectAccount==userIndex){
                                                    println(">>>> You cannot add yourself!  <<<<");
                                                }else if(!user.verifyFriend(userTwister[selectAccount])){
                                                    println(">>>> This user has already been added! <<<<");
                                                }
                                                else{
                                                    user.friend[user.numFriend] = userTwister[selectAccount];
                                                    user.numFriend++;
                                                    println(">>>> User added <<<<");
                                                }
                                                break;

                                            case 5:
                                                user.showFriends();
                                                break;

                                            case 9:

                                                loginPost = false;
                                                break;
                                        }
                                    }

                                    break;

                                case 2:

                                    user.showProfile();

                                    break;
                                case 3:

                                    boolean loginTopic = true;
                                    while (loginTopic) {
                                        println("\n_____Topics_____\n");
                                        println("(1) Show Topics");
                                        println("(2) Crate Topic");
                                        println("(3) Add a Twist to a Topic");
                                        println("(4) See Twist of Topic");
                                        println("(9) Back to Main menu");
                                        int optionTopic = sc.nextInt();

                                        switch (optionTopic) {
                                            case 1:

                                                showTopics();

                                                break;

                                            case 2:

                                                Topic tempTopic = new Topic();
                                                println("\n_____Create Topic_____\n");
                                                println("New topic name: ");
                                                tempTopic.name = sc.next();
                                                println("Topic category: ");
                                                tempTopic.category = sc.next();
                                                tempTopic.numTwistTopic = 0;

                                                globalTopic[numTopic] = tempTopic;
                                                numTopic++;

                                                break;
                                            case 3:

                                                println("\n_____Add Twist_____");
                                                println("Select one Topic: ");
                                                showTopics();
                                                int selectTopic = sc.nextInt();

                                                globalTopic[selectTopic].newTwistTopic(user.at);

                                                break;
                                            case 4:

                                                println("\n_____Topic Twist_____\n");
                                                println("Select one Topic: \n\n");
                                                showTopics();
                                                int seeTopic = sc.nextInt();

                                                globalTopic[seeTopic].showTwistTopic();

                                                break;
                                            case 9:

                                                loginTopic = false;

                                                break;
                                            default:

                                                println(">>>> Invalid Value <<<<");
                                                break;
                                        }
                                    }

                                    break;
                                case 4:

                                    boolean loginSettings = true;
                                    while (loginSettings) {
                                        println("\n_____Settings_____\n");
                                        println("(1) Show Settings");
                                        println("(2) Change Settings");
                                        println("(9) Back to Main menu");
                                        int optionSettings = sc.nextInt();

                                        switch (optionSettings) {
                                            case 1:
                                                user.setting.showSettings();
                                                break;
                                            case 2:
                                                user.setting.changeSettings();
                                                break;
                                            case 9:
                                                loginSettings = false;
                                                break;

                                            default:
                                                println(">>>> Invalid Value <<<<");
                                                break;
                                        }
                                    }

                                    break;
                                case 9:

                                    login = false;
                                    break;

                                default:
                                    break;
                            }

                        }

                        break;
                    } else {
                        println("\n>>>> Failed to login <<<<\n");
                        break;
                    }
                case 2:
                    Account auxAccount = new Account();

                    println("Enter your email: ");
                    auxAccount.email = sc.next();
                    println("Enter your password: ");
                    auxAccount.password = sc.next();
                    println("enter your name: ");
                    auxAccount.name = sc.next();
                    println("insert your at sign (@): ");
                    auxAccount.at = sc.next();

                    userTwister[nAccounts] = auxAccount;

                    //Ao criar a conta deixei as opções de configurações da conta como padrão essas abaixo
                    userTwister[nAccounts].setting.darkTheme = false;
                    userTwister[nAccounts].setting.notifications = true;
                    nAccounts++;

                    break;

                case 9:
                    loginVerification = false;
                    break;

                default:
                    println("\n>>>> Invalid Value!! <<<<\n");

            }
        }
    }

    static void println(String content) {
        System.out.println(content);
    }

    static void showTopics() {
        for (int i = 0; i < numTopic; i++) {

            println("\n["+i+"] Topic: " + globalTopic[i].name);
            println("Category: " + globalTopic[i].category);
            println("Number of Twists: " + globalTopic[i].numTwistTopic);
        }
    }

    static void initialData(){

        Account auxAccount = new Account();

        //Conta 1
        auxAccount.email = "bruno";
        auxAccount.password = "bruno";
        auxAccount.name = "Bruno Perdona";
        auxAccount.at = "BPerdona";

        userTwister[nAccounts] = auxAccount;

        userTwister[nAccounts].setting.darkTheme = false;
        userTwister[nAccounts].setting.notifications = true;
        nAccounts++;

        Topic tempTopic = new Topic();

        tempTopic.name = "The Rock";
        tempTopic.category = "Movie";
        globalTopic[numTopic] = tempTopic;
        numTopic++;
    }
}
