import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<Account> userTwister = new ArrayList<>();
    static ArrayList<Twist> globalTwists = new ArrayList<>();
    static ArrayList<Topic> globalTopic = new ArrayList<>();

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\r?\n");

        //Inserir função para dicionar alguns dados no sistema
        addData();


        boolean runningCode = true;
        while(runningCode){
            print("\n\n_____Welcome to Twister Login!_____");
            print("\nSelect an option bellow");
            print("(1) Login");
            print("(2) Register");
            print("(9) Exit");
            int optionLogin = sc.nextInt();

            switch (optionLogin) {
                case 1 -> {
                    String auxEmail, auxPassword;
                    print("\nInsert you email: ");
                    auxEmail = sc.next();
                    print("\nInsert your Password: ");
                    auxPassword = sc.next();
                    int indexEmail = -1;
                    int count = 0;
                    for (Account account : userTwister) {
                        if (account.getEmail().equals(auxEmail)) {
                            indexEmail = count;
                        }
                        count++;
                    }

                    //Verificação de Email
                    if (indexEmail == -1) {
                        print("\n>>>Email not found!<<<\n");
                        break;
                    }

                    //Realizando o login
                    if (userTwister.get(indexEmail).getEmail().equals(auxEmail) && userTwister.get(indexEmail).getPassword().equals(auxPassword)) {
                        print("\n>>>Login sucessfully!<<<\n");

                        //Criando um objeto com a conta logada
                        Account user = userTwister.get(indexEmail);

                        boolean login = true;
                        while (login) {
                            print("\n_____Welcome to Twister " + user.getName() + "!______\n");
                            print("Select an option below:");
                            print("(1) Posts");
                            print("(2) Profile");
                            print("(3) Topics");
                            print("(4) Lists");
                            print("(5) Settings");
                            print("(9) Logout");
                            int optionMain = sc.nextInt();

                            switch (optionMain) {
                                case 1 -> {
                                    boolean loginPost = true;
                                    while (loginPost) {
                                        print("\n_____Post_____\n");
                                        print("(1) New Post");
                                        print("(2) List your posts");
                                        print("(3) Delete Post");
                                        print("(4) Time Line");
                                        print("(5) Follow Account");
                                        print("(6) List Follows");
                                        print("(7) Unfollow Account");
                                        print("(9) Back to Main menu");
                                        int postOption = sc.nextInt();

                                        switch (postOption) {
                                            case 1 -> {
                                                System.out.println("\n_____New Post_____\n");
                                                System.out.println("Write your Twist: ");
                                                String tempTwistContent = sc.next();

                                                if (tempTwistContent.length() <= 100) {
                                                    Twist tempTwist = new Twist(user.getAt(), tempTwistContent);
                                                    user.addTwist(tempTwist);
                                                    globalTwists.add(tempTwist);

                                                    print("\n>>> Twist Done! <<<\n");
                                                } else {
                                                    print("\n>>>> You couldn't do this Twist <<<<");
                                                    print(">>>> The limit for a Twist is 100 characters! <<<<");
                                                }
                                            }
                                            case 2 -> listUserTwists(user);

                                            case 3 -> {
                                                listUserTwists(user);
                                                print("\n_____Select one Index to Delete_____\n");
                                                System.out.print("Twist Index: ");
                                                int deleteIndex = sc.nextInt();

                                                user.deleteTwist(deleteIndex);
                                                print("\n>>>Twist Deleted<<<\n");
                                            }
                                            case 4 ->{
                                                System.out.println("\n_____Time Line_____\n");
                                                generateTimeLine(user);
                                            }

                                            case 5 -> {
                                                print("\n_____Users List_____\n");
                                                int countAccount = 0;

                                                for (Account account : userTwister) {
                                                    if (account == user) {
                                                        print("\nUser: [" + countAccount + "]");
                                                        print("You are here!!!");
                                                        countAccount++;
                                                    } else {
                                                        print("\nIndex [" + countAccount + "]");
                                                        print("Name: " + userTwister.get(countAccount).getName());
                                                        print("At: @" + userTwister.get(countAccount).getAt());
                                                        countAccount++;
                                                    }
                                                }
                                                print("\nSelect profile to follow: ");
                                                int followIndex = sc.nextInt();

                                                if (followIndex < userTwister.size() && followIndex > -1) {
                                                    user.addFriend(userTwister.get(followIndex));
                                                }
                                                print("\n>>>You Followed " + userTwister.get(followIndex).getName() + "!<<<\n");
                                            }
                                            case 6 -> listFollows(user);

                                            case 7 -> {
                                                listFollows(user);

                                                print("Select a index to Unfollow: ");
                                                int indexDelete = sc.nextInt();

                                                print("\n>>>You stopped following the " + userTwister.get(indexDelete).getName() + "!<<<\n");
                                                user.deleteFriend(indexDelete);
                                            }
                                            case 9 -> loginPost = false;

                                            default -> print("\n>>>Invalid option<<<\n");
                                        }
                                    }
                                }
                                case 2 -> {
                                    boolean loginProfile = true;
                                    while (loginProfile) {
                                        print("\n_____Profile_____\n");
                                        print("(1) Show Profile");
                                        print("(2) Edit Profile");
                                        print("(9) Back to Main menu");
                                        int profileOption = sc.nextInt();

                                        switch (profileOption) {
                                            case 1 -> {
                                                print("\n_____Your Profile_____\n");
                                                print("Nome: " + user.getName());
                                                print("At: @" + user.getAt());
                                                print("Email: " + user.getEmail());
                                                print("Password: " + user.getPassword());
                                                print("Total of Twists: " + user.twisters().size());
                                            }
                                            case 2 -> {
                                                boolean editProfileLogin = true;
                                                while (editProfileLogin) {
                                                    print("\nWhat information do you want to change: ");
                                                    print("(1) Name");
                                                    print("(2) At(@)");
                                                    print("(3) Email");
                                                    print("(4) Password");
                                                    print("(9) Return\n");
                                                    int editProfileOption = sc.nextInt();

                                                    switch (editProfileOption) {
                                                        case 1 -> {
                                                            print("\nChange the name '" + user.getName() + "' to: ");
                                                            String newName = sc.next();
                                                            user.setName(newName);
                                                        }
                                                        case 2 -> {
                                                            print("\nChange the at '@" + user.getAt() + "' to: ");
                                                            String newAt = sc.next();
                                                            for (Account account : userTwister) {
                                                                if (account.getAt().equals(newAt)) {
                                                                    print("\n>>>This At already exists<<<\n");
                                                                    break;
                                                                }
                                                            }
                                                            user.setAt(newAt);
                                                        }
                                                        case 3 -> {
                                                            print("\nChange the email '" + user.getEmail() + "' to: ");
                                                            String newEmail = sc.next();
                                                            for (Account account : userTwister) {
                                                                if (account.getEmail().equals(newEmail)) {
                                                                    print("\n>>>This Email already exists<<<\n");
                                                                    break;
                                                                }
                                                            }
                                                            user.setEmail(newEmail);
                                                        }
                                                        case 4 -> {
                                                            print("\nChange the Password '" + user.getName() + "' to: ");
                                                            String newPassword = sc.next();
                                                            user.setPassword(newPassword);
                                                        }
                                                        case 9 -> editProfileLogin = false;
                                                        default -> print("\n>>>Invalid Option<<<\n");
                                                    }
                                                }
                                            }
                                            case 9 -> loginProfile = false;
                                            default -> print("\n>>>Invalid option<<<\n");
                                        }

                                    }
                                }
                                case 3 -> {
                                    boolean loginTopic = true;
                                    while (loginTopic) {
                                        print("\n_____Topics_____\n");
                                        print("(1) Show Topics");
                                        print("(2) Crate Topic");
                                        print("(3) Add a Twist to a Topic");
                                        print("(4) See Twist of Topic");
                                        print("(5) Delete Twist of Topic");
                                        print("(9) Back to Main menu");
                                        int optionTopic = sc.nextInt();

                                        switch (optionTopic) {
                                            case 1:
                                                break;

                                            case 2:
                                                break;

                                            case 3:
                                                break;

                                            case 4:
                                                break;

                                            case 5:
                                                break;

                                            case 9:
                                                loginTopic = false;
                                                break;

                                            default:
                                                print("\n>>>Invalid Option<<<\n");
                                                break;
                                        }

                                    }
                                }
                                case 4 -> {
                                    boolean loginLists = true;
                                    while (loginLists) {
                                        print("\n_____Lists_____");
                                        print("(1) Create List");
                                        print("(2) Show Lists");
                                        print("(3) See List Timeline");
                                        print("(4) Change a List");
                                        print("(5) Delete a List");
                                        print("(9) Back to Main menu");
                                        int optionLists = sc.nextInt();

                                        switch (optionLists) {
                                            case 1:
                                                break;

                                            case 2:
                                                break;

                                            case 3:
                                                break;

                                            case 4:
                                                break;

                                            case 5:
                                                break;

                                            case 9:
                                                loginLists = false;
                                                break;

                                            default:
                                                print("\n>>>Invalid Option<<<\n");
                                                break;
                                        }
                                    }
                                }
                                case 5 -> {
                                    boolean loginSetting = true;
                                    while (loginSetting) {
                                        print("\n_____Settings_____\n");
                                        print("(1) Show Setting");
                                        print("(2) Change Setting");
                                        print("(9) Back to Main menu");
                                        int optionSettings = sc.nextInt();

                                        switch (optionSettings) {
                                            case 1 -> {
                                                print("\n_____Yours Settings_____\n");
                                                if (user.getSettings().isDarkTheme()) {
                                                    print("Dark Theme: On");
                                                } else {
                                                    print("Dark Theme: Off");
                                                }
                                                if (user.getSettings().isNotification()) {
                                                    print("Notifications: On");
                                                } else {
                                                    print("Notifications: off");
                                                }
                                            }
                                            case 2 -> {
                                                print("Change Dark Theme to (1=on/2=off):");
                                                int changeDarkTheme = sc.nextInt();
                                                if (changeDarkTheme != 1 && changeDarkTheme != 2) {
                                                    print("\n>>>Invalid Value!<<<\n");
                                                    break;
                                                }
                                                if (changeDarkTheme == 1) {
                                                    user.getSettings().setDarkTheme(true);
                                                }
                                                if (changeDarkTheme == 2) {
                                                    user.getSettings().setDarkTheme(false);
                                                }
                                                print("Change Notifications to (1=on/2=off):");
                                                int changeNotifications = sc.nextInt();
                                                if (changeNotifications != 1 && changeNotifications != 2) {
                                                    print("\n>>>Invalid Value!<<<\n");
                                                    break;
                                                }
                                                if (changeNotifications == 1) {
                                                    user.getSettings().setNotification(true);
                                                }
                                                if (changeNotifications == 2) {
                                                    user.getSettings().setNotification(false);
                                                }
                                            }
                                            case 9 -> loginSetting = false;
                                            default -> print("\n>>>Invalid Option<<<\n");
                                        }
                                    }
                                }
                                case 9 -> login = false;
                                default -> print("\n>>>Invalid option<<<\n");
                            }
                        }


                    } else {
                        print("\n>>>Senha incorreta!<<<\n");
                    }
                }
                case 2 -> {
                    String email, password, name, at;
                    print("Enter your email: ");
                    email = sc.next();
                    boolean emailVerif = true;
                    for (Account account : userTwister) {
                        if (account.getEmail().equals(email)) {
                            emailVerif = false;
                            break;
                        }
                    }
                    if (!emailVerif) {
                        print("\n>>>This Email already Exists<<<\n");
                        break;
                    }
                    print("Enter your password: ");
                    password = sc.next();
                    print("Enter your name: ");
                    name = sc.next();
                    print("Insert your at sign (@): ");
                    at = sc.next();

                    //Arrumar verificação
                    boolean atVerif = true;
                    for (Account account : userTwister) {
                        if (account.getAt().equals(at)) {
                            atVerif = false;
                            break;
                        }
                    }
                    if (!atVerif) {
                        print("\n\n>>>Failed to create account<<<");
                        print(">>>This at (@) already exists<<<\n\n");
                        break;
                    }
                    Settings auxSettings = new Settings(false, true);

                    Account auxAccount = new Account(name, password, email, at, auxSettings);
                    userTwister.add(auxAccount);
                    print("\n\n >>>Account created<<< \n\n");
                }
                case 9 -> runningCode = false;
                default -> print("\n\n>>>Opção invalida!<<<\n\n");
            }
        }

    }
    public static void print(String content){
        System.out.println(content);
    }

    private static void generateTimeLine(Account user) {

        for (Twist globalTwist : globalTwists) {
            for (Account friend : user.getFriend()) {
                if (globalTwist.getTwistOwner().equals(friend.getAt())) {
                    print("\n@" + globalTwist.getTwistOwner());
                    System.out.println(globalTwist.getContent());
                }
            }
        }
    }

    public static void listFollows(Account user){
        print("\n_____You are Following_____\n");
        int countFollowers=0;
        for (Account account : user.getFriend()){
            print("Index["+countFollowers+"]");
            print("Name: " + account.getName());
            print("At: @" + account.getAt()+"\n");
            countFollowers++;
        }
    }


    public static void listUserTwists(Account user){
        System.out.println("\n_____Your's Twists_____\n");
        int countTwists=0;
        for(Twist twist : user.twisters()){
            print("\nTwist ["+countTwists+"]");
            print("@"+twist.getTwistOwner());
            print(twist.getContent());
            print("Chars: ["+twist.getContent().length()+"]\n");
            countTwists++;
        }
    }

    public static void addData(){
        Settings defaultSettings = new Settings(false,true);

        Account accountBruno = new Account("Bruno","senha","bruno", "BPerdona", defaultSettings);
        userTwister.add(accountBruno);

        Account accountEminem = new Account("Marshall","123","marshal.com","Eminem", defaultSettings);
        userTwister.add(accountEminem);

        Account accountRock = new Account("TheRock","4444","rockMovie","TheRock", defaultSettings);
        userTwister.add(accountRock);

        Account accountLucca = new Account("LuccaF","6969","luccabikes","BikeLucca", defaultSettings);
        userTwister.add(accountLucca);

        Topic topicFilme = new Topic("Celebrities", "Films");

        Twist twistFilme1 = new Twist("ProfessorAlbert","Otimo filme");
        Twist twistFilme2 = new Twist("Maria","Não gostei");
        topicFilme.addTwist(twistFilme1);
        topicFilme.addTwist(twistFilme2);

        globalTopic.add(topicFilme);

        Topic topicJogos = new Topic("MIBR", "Games");
        globalTopic.add(topicJogos);
    }
}
