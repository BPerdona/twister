package br.com.brunoperdona.twister;

import br.com.brunoperdona.twister.entities.*;
import br.com.brunoperdona.twister.settings.Settings;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static ArrayList<Admin> admins = new ArrayList<>();
    static ArrayList<Account> userTwister = new ArrayList<>();
    static ArrayList<User> allUsers = new ArrayList<>();
    static ArrayList<Twist> globalTwists = new ArrayList<>();
    static ArrayList<Topic> globalTopic = new ArrayList<>();

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\r?\n");

        //Inserir função para dicionar alguns dados no sistema
        addData();

        boolean runningCode = true;
        while(runningCode){

            printLogo();
            print("\n\n_____Welcome_to_Twister_Login!_____");
            print("\nSelect an option bellow");
            print("(1) Login");
            print("(2) Register");
            print("(3) Login como Admin");
            print("(4) Registrar novo Admin");
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

                            print("\n_____Welcome_to_Twister " + user.getName() + "!______\n");
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
                                                System.out.println("\n_____New_Post_____\n");
                                                System.out.println("Write your Twist: ");
                                                String tempTwistContent = sc.next();

                                                if (tempTwistContent.length() <= 100) {
                                                    Twist tempTwist = new Twist(user.getAt(), tempTwistContent);
                                                    user.twisters().add(tempTwist);
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
                                                print("\n_____Select_one_Index_to_Delete_____\n");
                                                System.out.print("Twist Index: ");
                                                int deleteIndex = sc.nextInt();

                                                int countIndexGlobal = 0;
                                                for(Twist twist : globalTwists){
                                                    if(twist.getContent().equals(user.twisters().get(deleteIndex).getContent()) && twist.getTwistOwner().equals(user.getAt())){
                                                        globalTwists.remove(countIndexGlobal);
                                                        break;
                                                    }
                                                    countIndexGlobal++;
                                                }
                                                user.twisters().remove(deleteIndex);
                                                print("\n>>>Twist Deleted<<<\n");
                                            }
                                            case 4 ->{
                                                System.out.println("\n_____Time_Line_____\n");
                                                generateTimeLine(user);
                                            }

                                            case 5 -> {
                                                print("\n_____Users_List_____\n");
                                                int countAccount = 0;

                                                for (Account account : userTwister){
                                                    if(!verifyFriend(user, account.getAt())) {
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
                                                    }else{
                                                        countAccount++;
                                                    }
                                                }

                                                print("\nSelect profile to follow: ");
                                                int followIndex = sc.nextInt();

                                                if (followIndex < userTwister.size() && followIndex > -1) {
                                                    if(verifyFriend(user, userTwister.get(followIndex).getAt())){
                                                        print("\n>>>You are already friends!<<<\n");
                                                    }else{
                                                        user.getFriend().add(userTwister.get(followIndex));
                                                        print("\n>>>You Followed " + userTwister.get(followIndex).getName() + "!<<<\n");
                                                    }
                                                }
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
                                                print("\n_____Your_Profile_____\n");
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
                                                            print("\n>>>Name changed!<<<\n");
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
                                                            print("\n>>>At changed!<<<\n");

                                                            //For alterando todos twist com @ do usuario nos topicos
                                                            for(Topic topic : globalTopic){
                                                                for(Twist twist : topic.getTwists()){
                                                                    if(twist.getTwistOwner().equals(user.getAt())){
                                                                        twist.setTwistOwner(newAt);
                                                                    }
                                                                }
                                                            }

                                                            //Mudei o at do usuario depois pq precisava achar ele nso 2 for anteriores
                                                            user.setAt(newAt);

                                                            //For alterando todos twist com @ do usuario
                                                            for (Twist twist : user.twisters()){
                                                                twist.setTwistOwner(user.getAt());
                                                            }


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
                                                            print("\n>>>Email changed!<<<\n");
                                                            user.setEmail(newEmail);
                                                        }
                                                        case 4 -> {
                                                            print("\nChange the Password '" + user.getName() + "' to: ");
                                                            String newPassword = sc.next();
                                                            user.setPassword(newPassword);
                                                            print("\n>>>Password changed!<<<\n");
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
                                            case 1 -> listTopics();

                                            case 2 -> {
                                                print("\n_____Create_Topic_____\n");
                                                print("New topic name: ");
                                                String newTopic = sc.next();

                                                boolean topicExists = false;
                                                for (Topic topic : globalTopic) {
                                                    if (topic.getName().equals(newTopic)) {
                                                        print("\n>>>This Topic already exists!<<<\n");
                                                        topicExists = true;
                                                    }
                                                }

                                                //Adicionando topico sem Categoria
                                                if (!topicExists) {
                                                    print("\nThis topic need category?(1=Yes/0=No)");
                                                    int selectCategory = sc.nextInt();
                                                    if(selectCategory==1){
                                                        print("Topic category: ");
                                                        String newCategory = sc.next();

                                                        Topic tempTopic = new Topic(newTopic, newCategory);
                                                        globalTopic.add(tempTopic);
                                                        print("\n>>>You create the Topic " + newTopic + "!<<<\n");
                                                    }else{
                                                        Topic tempTopic = new Topic(newTopic);
                                                        globalTopic.add(tempTopic);
                                                    }
                                                }
                                            }
                                            case 3 -> {
                                                print("\n_____Add_Twist_____");
                                                listTopics();
                                                print("\nSelect one Topic");
                                                int selectTopic = sc.nextInt();

                                                if(selectTopic>globalTopic.size() && selectTopic<0){
                                                    print("\n>>>This Topic doesn't exists!<<<\n");

                                                }else{
                                                    System.out.println("\nWrite your Twist: ");
                                                    String tempTwistContent = sc.next();

                                                    if (tempTwistContent.length() <= 100) {
                                                        Twist tempTwist = new Twist(user.getAt(), tempTwistContent);
                                                        globalTopic.get(selectTopic).getTwists().add(tempTwist);

                                                        print("\n>>>Twist Done!<<<\n");
                                                    } else {
                                                        print("\n>>>You couldn't do this Twist<<<");
                                                        print(">>>The limit for a Twist is 100 characters!<<<\n");
                                                    }
                                                }
                                            }

                                            case 4 -> {
                                                print("\n_____Topic_Twist_____\n");
                                                print("Select one Topic: \n");
                                                listTopics();
                                                int selectTopic = sc.nextInt();

                                                if(selectTopic>globalTopic.size() && selectTopic<0){
                                                    print("\n>>>This Topic doesn't exists!<<<\n");

                                                }else{
                                                    int countIndexTwist = 0;
                                                    for(Twist twist : globalTopic.get(selectTopic).getTwists()){
                                                        print("\nTwist ["+countIndexTwist+"]");
                                                        print("User: @"+twist.getTwistOwner());
                                                        print(twist.getContent()+"\n");
                                                        countIndexTwist++;
                                                    }
                                                }
                                            }

                                            case 5 -> {
                                                print("\n_____Topic Twist_____\n");
                                                print("Select one Topic: \n");
                                                listTopics();
                                                int selectTopic = sc.nextInt();

                                                if(selectTopic>globalTopic.size() && selectTopic<0){
                                                    print("\n>>>This Topic doesn't exists!<<<\n");

                                                }else{
                                                    int countIndexTwist = 0;

                                                    for(Twist twist : globalTopic.get(selectTopic).getTwists()){
                                                        print("\nTwist ["+countIndexTwist+"]");
                                                        print("User: @"+twist.getTwistOwner());
                                                        print(twist.getContent()+"\n");
                                                        countIndexTwist++;
                                                    }
                                                }
                                                print("\nSelect one Twist to delete: \n");
                                                int selectTwist = sc.nextInt();

                                                if(selectTwist>globalTopic.get(selectTopic).getSizeTwists() && selectTwist<0){
                                                    print("\n>>>This Twist doesn't exists!<<<\n");

                                                }else {
                                                    //If verificando se o usuario é o autor do twist
                                                    if (globalTopic.get(selectTopic).getTwists().get(selectTwist).getTwistOwner().equals(user.getAt())) {
                                                        globalTopic.get(selectTopic).getTwists().remove(selectTwist);
                                                        print("\n>>>Your Twist has been deleted<<<\n");

                                                    } else {
                                                        print("\n>>>You can only delete yours Twists!<<<\n");
                                                    }
                                                }
                                            }

                                            case 9 -> loginTopic = false;

                                            default -> print("\n>>>Invalid Option<<<\n");
                                        }
                                    }
                                }
                                case 4 -> {
                                    boolean loginLists = true;
                                    while (loginLists) {

                                        print("\n_____Lists_____");
                                        print("(1) Create List");
                                        print("(2) Show Lists");
                                        print("(3) Add a member to a List");
                                        print("(4) See List Timeline");
                                        print("(5) Edit a List");
                                        print("(6) Delete a List");
                                        print("(9) Back to Main menu");
                                        int optionLists = sc.nextInt();

                                        switch (optionLists) {
                                            case 1 -> {
                                                print("\n_____Create_List_____\n");
                                                print("New List Name: ");
                                                String newList = sc.next();

                                                boolean listExists = false;
                                                for (List list : user.getLists()){
                                                    if(list.getName().equals(newList)){
                                                        print("\n>>>There is already a list with that name!<<<\n");
                                                        listExists = true;
                                                    }
                                                }

                                                //iniciando uma lista sem descrição
                                                if (!listExists) {
                                                    print("\nYou want a description?(1=Yes/0=No)");
                                                    int selectDescription = sc.nextInt();

                                                    if(selectDescription==1){
                                                        print("\nInsert a Description about " + newList + ":");
                                                        String newDescription = sc.next();

                                                        List tempList = new List(newList, newDescription);
                                                        user.getLists().add(tempList);
                                                    }else {
                                                        List tempList = new List(newList);
                                                        user.getLists().add(tempList);
                                                    }
                                                    print("\n>>>Created List<<<\n");
                                                }
                                            }

                                            case 2 -> {
                                                if(user.getLists().size()!=0){
                                                    showLists(user);
                                                }else{
                                                    print("\n>>>You don't have lists <<<\n");
                                                }

                                            }

                                            case 3 -> {
                                                if(user.getFriend().size()==0){
                                                    print("\n>>>Your Friend's list is Empty<<<\n");
                                                }else {
                                                    showLists(user);
                                                    print("\nSelect a List");
                                                    int selectList = sc.nextInt();

                                                    if (selectList > user.getLists().size() && selectList < 0) {
                                                        print("\n>>>This list doesn't exists<<<\n");

                                                    } else {

                                                        boolean loginMembersAdd = true;
                                                        while (loginMembersAdd) {

                                                            if (user.getFriend().size() == user.getLists().get(selectList).getMembers().size()) {
                                                                print("\n>>>You have already added all your friends!<<<\n");
                                                                loginMembersAdd = false;

                                                            } else {
                                                                listFollows(user);
                                                                print("\nSelect a friend to add or type '-1' to quit:");
                                                                int memberOption = sc.nextInt();

                                                                if (memberOption == -1) {
                                                                    loginMembersAdd = false;

                                                                } else if (memberOption > user.getFriend().size() && memberOption < -2) {
                                                                    print("\n>>>This friend doesn't exists<<<\n");

                                                                } else {
                                                                    boolean alreadyInList = false;
                                                                    for (Account account : user.getLists().get(selectList).getMembers()){
                                                                        if(account.getAt().equals(user.getFriend().get(memberOption).getAt())){
                                                                            alreadyInList = true;
                                                                        }
                                                                    }
                                                                    if(alreadyInList){
                                                                        print("\n>>>This account is already on the list<<<\n");
                                                                    }else {
                                                                        user.getLists().get(selectList).getMembers().add(user.getFriend().get(memberOption));
                                                                        print("\n>>>You added " + user.getFriend().get(memberOption).getName() + " to this list!<<<\n");
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }

                                            case 4 -> {
                                                showLists(user);
                                                print("\nSelect a List");
                                                int selectList = sc.nextInt();

                                                if (selectList > user.getLists().size() && selectList < 0) {
                                                    print("\n>>>This list doesn't exists<<<\n");

                                                } else {
                                                    print("\n_____Time_Line_List_____\n");
                                                    for(Twist twist : globalTwists){
                                                        for(Account member : user.getLists().get(selectList).getMembers()){
                                                            if(twist.getTwistOwner().equals(member.getAt())){
                                                                print("\n@"+twist.getTwistOwner());
                                                                System.out.println(twist.getContent());
                                                            }
                                                        }
                                                    }
                                                }
                                            }

                                            case 5 -> {
                                                showLists(user);
                                                print("\nSelect a List to Edit:");
                                                int selectList = sc.nextInt();

                                                if (selectList > user.getLists().size() && selectList < 0) {
                                                    print("\n>>>This list doesn't exists<<<\n");

                                                }else{

                                                    boolean loginEditList = true;
                                                    while(loginEditList){
                                                        print("\n_____Editing_List_____\n");
                                                        print("(1) Edit name");
                                                        print("(2) Edit description");
                                                        print("(3) Remove a member");
                                                        print("(9) Exit");
                                                        int selectEditOption = sc.nextInt();

                                                        switch (selectEditOption){
                                                            case 1 -> {
                                                                print("\nChange the list name '"+user.getLists().get(selectList).getName()+"' to:");
                                                                String newNameList = sc.next();
                                                                user.getLists().get(selectList).setName(newNameList);
                                                                print("\n>>>Name changed!<<<\n");
                                                            }
                                                            case 2 -> {
                                                                print("\nChange the list description '"+user.getLists().get(selectList).getCategory()+"' to:");
                                                                String newDescriptionList = sc.next();
                                                                user.getLists().get(selectList).setCategory(newDescriptionList);
                                                                print("\n>>>Description changed!<<<\n");
                                                            }
                                                            case 3 -> {
                                                                if (user.getLists().get(selectList).getMembers().size() == 0) {
                                                                    print("\n>>>This list has no members<<<\n");
                                                                }else {
                                                                    print("\n_____List's Members_____\n");
                                                                    int countMembers = 0;
                                                                    for (Account account : user.getLists().get(selectList).getMembers()) {
                                                                        print("\n[" + countMembers + "] @" + account.getAt());
                                                                        countMembers++;
                                                                    }
                                                                    print("\nSelect a member to remove: ");
                                                                    int selectRemoveMember = sc.nextInt();

                                                                    print("\n>>>User " + user.getLists().get(selectList).getMembers().get(selectRemoveMember).getAt() + " has been removed from your list");
                                                                    user.getLists().get(selectList).getMembers().remove(selectRemoveMember);
                                                                }
                                                            }

                                                            case 9 -> loginEditList = false;

                                                            default -> print("\n>>>Invalid Option<<<\n");
                                                        }
                                                    }

                                                }
                                            }

                                            case 6 -> {
                                                showLists(user);
                                                print("\nSelect a List to Delete:");
                                                int selectList = sc.nextInt();

                                                if (selectList > user.getLists().size() && selectList < 0) {
                                                    print("\n>>>This list doesn't exists<<<\n");

                                                }else{
                                                    user.getLists().remove(selectList);
                                                    print("\n>>>You deleted the list<<<\n");
                                                }
                                            }

                                            case 9 -> loginLists = false;

                                            default -> print("\n>>>Invalid Option<<<\n");
                                        }
                                    }
                                }
                                case 5 -> editSettings(user);

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

                    if (verifEmail(email)) {
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

                    print("Do you want default settings?(1=Yes/2=No)");
                    int selectionSettings = sc.nextInt();

                    if(selectionSettings==1){
                        Account auxAccount = new Account(name, password, email, at);
                        userTwister.add(auxAccount);
                        allUsers.add(auxAccount);
                    }else{
                        print("\nNotification -> (1=On/2=Off)");
                        int notificationInt = sc.nextInt();
                        boolean notification = notificationInt == 1;

                        print("\nDarkTheme -> (1=On/2=Off)");
                        int darkThemeInt = sc.nextInt();
                        boolean darkTheme = darkThemeInt == 1;

                        Settings settings = new Settings(darkTheme,notification);
                        Account auxAccount = new Account(name, password, email, at, settings);
                        userTwister.add(auxAccount);
                        allUsers.add(auxAccount);
                    }



                    print("\n\n >>>Account created<<< \n\n");
                }

                case 3 ->{
                    if(admins.size()==0){
                        print("\n\n>>>No admin registered<<<\n\n");
                        break;
                    }
                    String auxEmail, auxPassword;

                    print("\nInsert you email: ");
                    auxEmail = sc.next();
                    print("\nInsert your Password: ");
                    auxPassword = sc.next();
                    int adminIndex = -1;
                    int count = 0;

                    for(Admin aux : admins){
                        if (aux.getEmail().equals(auxEmail)){
                            adminIndex = count;
                        }
                        count++;
                    }

                    if(admins.get(adminIndex).getEmail().equals(auxEmail) && admins.get(adminIndex).getPassword().equals(auxPassword)){
                        print("\n\n>>>Admin login Sucefully!<<<\n\n");

                        //Criando objeto de admin logado
                        Admin admin = admins.get(adminIndex);

                        boolean loginAdmin = true;
                        while(loginAdmin){

                            print("\n_____Welcome_to_Twister_ADMIN_" + admin.getName() + "!______\n");
                            print("Select an option below:");
                            print("(1) Twist for everyone");
                            print("(2) All your admin Twists");
                            print("(3) List all Twists");
                            print("(4) Delete one global Twist");
                            print("(5) List all Twist users");
                            print("(6) Admin Profile");
                            print("(7) Settings");
                            print("(9) Logout");

                            int adminSelection = sc.nextInt();
                            switch (adminSelection){
                                case 1 ->{

                                    print("\n_____New_ADMIN_Post_____\n");
                                    print("Write your Twist: ");
                                    String tempTwistContent = sc.next();
                                    Twist tempTwist = new Twist(admin.getAt(), tempTwistContent, true);
                                    admin.twisters().add(tempTwist);
                                    globalTwists.add(tempTwist);
                                    print("\n>>> ADMIN Twist Done! <<<\n");

                                }

                                case 2->{

                                    print("\n_____Your_ADMIN_Twists_____\n");
                                    int countTwists = 0;
                                    for(Twist twist : admin.twisters()){
                                        print("\nTwist ["+countTwists+"]");
                                        print("@"+twist.getTwistOwner());
                                        print(twist.getContent());
                                        countTwists++;
                                    }

                                }

                                case 3 -> allUsersTwists();

                                case 4 ->{

                                    //percorrendo a lista de usuarias para achar o usuario e a lista de twists para
                                    //achar o twist certo para deletar
                                    allUsersTwists();
                                    print("\nSelect one index to delete: ");
                                    int deleteTwistIndex = sc.nextInt();
                                    int indexUser = 0;
                                    int indexTwistInUser = 0;

                                    int userCount = 0;
                                    for(User user : allUsers){
                                        if(user.getAt().equals(globalTwists.get(deleteTwistIndex).getTwistOwner())){
                                            print("User: "+user.getAt()+" user twist: "+globalTwists.get(deleteTwistIndex).getTwistOwner());
                                            indexUser = userCount;

                                            int twistCount = 0;
                                            for(Twist userTwist : user.getTwisters()){
                                                if(userTwist.getContent().equals(globalTwists.get(deleteTwistIndex).getContent())){
                                                    print("Cont: "+userTwist.getContent()+" cont2: "+globalTwists.get(deleteTwistIndex).getContent());
                                                    indexTwistInUser = twistCount;
                                                    break;
                                                }
                                                twistCount++;
                                            }
                                            break;
                                        }
                                        count++;
                                    }

                                    print("user: "+indexUser+" Twist: "+indexTwistInUser);
                                    allUsers.get(indexUser).getTwisters().remove(indexTwistInUser);
                                    globalTwists.remove(deleteTwistIndex);

                                }

                                case 5 -> printAllUsers();

                                case 6 -> {
                                    boolean loginAdminProfile = true;
                                    while (loginAdminProfile) {

                                        print("\n_____ADMIN_Profile_____\n");
                                        print("(1) Show Profile");
                                        print("(2) Edit Profile");
                                        print("(9) Back to Main menu");
                                        int profileOption = sc.nextInt();

                                        switch (profileOption) {
                                            case 1 -> showAdminProfile(admin);

                                            case 2 -> {
                                                boolean editAdminProfileLogin = true;
                                                while (editAdminProfileLogin) {

                                                    print("\nWhat information do you want to change: ");
                                                    print("(1) Name");
                                                    print("(2) At(@)");
                                                    print("(3) Email");
                                                    print("(4) Password");
                                                    print("(9) Return");
                                                    print(">>>Some infomations cant be changed (id, country, payment) <<<\n");
                                                    int editAdminOption = sc.nextInt();

                                                    switch (editAdminOption) {
                                                        case 1 -> {
                                                            print("\nChange the name '" + admin.getName() + "' to: ");
                                                            String newName = sc.next();
                                                            admin.setName(newName);
                                                            print("\n>>>Name changed!<<<\n");
                                                        }
                                                        case 2 -> {
                                                            print("\nChange the at '@" + admin.getAt() + "' to: ");
                                                            String newAt = sc.next();
                                                            for (User auxUser : allUsers) {
                                                                if (auxUser.getAt().equals(newAt)) {
                                                                    print("\n>>>This At already exists<<<\n");
                                                                    break;
                                                                }
                                                            }
                                                            print("\n>>>At changed!<<<\n");

                                                            //Mudei o at do usuario depois pq precisava achar ele nso 2 for anteriores
                                                            admin.setAt(newAt);

                                                            //For alterando todos twist com @ do usuario
                                                            for (Twist twist : admin.twisters()){
                                                                twist.setTwistOwner(admin.getAt());
                                                            }


                                                        }
                                                        case 3 -> {
                                                            print("\nChange the email '" + admin.getEmail() + "' to: ");
                                                            String newEmail = sc.next();
                                                            for (User auxUser : allUsers) {
                                                                if (auxUser.getEmail().equals(newEmail)) {
                                                                    print("\n>>>This Email already exists<<<\n");
                                                                    break;
                                                                }
                                                            }
                                                            print("\n>>>Email changed!<<<\n");
                                                            admin.setEmail(newEmail);
                                                        }
                                                        case 4 -> {
                                                            print("\nChange the Password '" + admin.getPassword() + "' to: ");
                                                            String newPassword = sc.next();
                                                            admin.setPassword(newPassword);
                                                            print("\n>>>Password changed!<<<\n");
                                                        }
                                                        case 9 -> editAdminProfileLogin = false;
                                                        default -> print("\n>>>Invalid Option<<<\n");
                                                    }
                                                }
                                            }
                                            case 9 -> loginAdminProfile = false;

                                            default -> print("\n>>>Invalid option<<<\n");
                                        }
                                    }
                                }

                                case 7 -> editSettings(admin);

                                case 9 -> loginAdmin = false;
                                default -> print("\n\n>>>Invalid Option!<<<\n\n");
                            }
                        }
                    }
                }
                //Adicionando admim
                case 4 ->{
                    print("\nIf you are a volunteer admin write (1)");
                    print("If you want to work as an admin write (2)\n");
                    int adminSelection = sc.nextInt();

                    if(adminSelection != 1 && adminSelection != 2) {
                        print("\n\n>>>Invalid Option!<<<\n\n");
                        break;
                    }

                    if(adminSelection == 1){
                        String name, email, at, password;
                        int id;

                        print("Enter your email");
                        email = sc.next();

                        //verificando email
                        if(verifEmail(email)){
                            print("\n>>>This Email already Exists<<<\n");
                            break;
                        }
                        print("Enter your password: ");
                        password = sc.next();
                        print("Enter your name: ");
                        name = sc.next();
                        print("Insert your at sign (@): ");
                        at = sc.next();

                        //Usuario recebendo id aleatorio
                        Random r = new Random();
                        id = r.nextInt(100000);

                        Admin auxAdmin = new Admin(name, password, email, at, id);
                        admins.add(auxAdmin);
                        allUsers.add(auxAdmin);

                        print("\n\n >>>Admin Account created<<< \n\n");
                    }

                    if(adminSelection == 2){
                        String name, email, at, password, country;
                        int id;
                        float payment;

                        print("Enter your email");
                        email = sc.next();

                        //verificando email
                        if(verifEmail(email)){
                            print("\n>>>This Email already Exists<<<\n");
                            break;
                        }
                        print("Enter your password: ");
                        password = sc.next();
                        print("Enter your name: ");
                        name = sc.next();
                        print("Insert your at sign (@): ");
                        at = sc.next();
                        print("Insert your country: ");
                        country = sc.next();
                        print("How much do you want to earn? ");
                        payment = sc.nextFloat();

                        //Usuario recebendo id aleatorio
                        Random r = new Random();
                        id = r.nextInt(100000);

                        Admin auxAdmin = new Admin(name, password, email, at, id, country, payment);
                        admins.add(auxAdmin);

                        print("\n\n >>>Admin Account created<<< \n\n");
                    }
                }
                case 9 -> runningCode = false;
                default -> print("\n\n>>>Invalid Option!<<<\n\n");
            }
        }

    }

    public static void print(String content){
        System.out.println(content);
    }

    public static void printLogo(){
        print("");
        print("  _______                  _             _                     ");
        print(" |__   __|                (_)           | |                    ");
        print("    | |      __      __    _     ___    | |_      ___     _ __ ");
        print("    | |      \\ \\ /\\ / /   | |   / __|   | __|    / _ \\   | '__|");
        print("    | |       \\ V  V /    | |   \\__ \\   | |_    |  __/   | |   ");
        print("    |_|        \\_/\\_/     |_|   |___/    \\__|    \\___|   |_|   ");
        print("");

    }

    private static void generateTimeLine(Account user) {

        //Todos os usuarios podem visualizar mensagens de admin mas nao podem adicionalos
        for (Twist globalTwist : globalTwists) {
            for (Account friend : user.getFriend()) {
                if (globalTwist.getTwistOwner().equals(friend.getAt())) {
                    print("\n@" + globalTwist.getTwistOwner());
                    System.out.println(globalTwist.getContent());
                }
            }
            if(globalTwist.isAdminTwist()){
                print("\n@" + globalTwist.getTwistOwner()+"-ADMIN");
                System.out.println(globalTwist.getContent());
            }
        }
    }

    private static boolean verifyFriend(Account user, String at){
        for(Account friend : user.getFriend()){
            if(friend.getAt().equals(at)){
                return true;
            }
        }
        return false;
    }

    private static void listFollows(Account user){
        print("\n_____You_are_Following_____\n");
        int countFollowers=0;
        for (Account account : user.getFriend()){
            print("Index["+countFollowers+"]");
            print("Name: " + account.getName());
            print("At: @" + account.getAt()+"\n");
            countFollowers++;
        }
    }


    private static void listUserTwists(Account user){
        print("\n_____Your_Twists_____\n");
        int countTwists=0;
        for(Twist twist : user.twisters()){
            if(twist.isAdminTwist()){
                print("\nTwist ["+countTwists+"]");
                print("@"+twist.getTwistOwner()+user.typeOf());
                print(twist.getContent());
                print("Chars: ["+twist.getContent().length()+"]\n");
                countTwists++;
            }else{
                print("\nTwist ["+countTwists+"]");
                print("@"+twist.getTwistOwner());
                print(twist.getContent());
                print("Chars: ["+twist.getContent().length()+"]\n");
                countTwists++;
            }
        }
    }

    //função que é utilizada tanto para Acccount quanto para Admin
    static void editSettings(User user) {
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\r?\n");
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
                    boolean darkTheme = false;
                    if (changeDarkTheme != 1 && changeDarkTheme != 2) {
                        print("\n>>>Invalid Value!<<<\n");
                        break;
                    }
                    if (changeDarkTheme == 1) {
                        darkTheme = true;
                    }
                    print("Change Notifications to (1=on/2=off):");
                    int changeNotifications = sc.nextInt();
                    boolean notifications = false;
                    if (changeNotifications != 1 && changeNotifications != 2) {
                        print("\n>>>Invalid Value!<<<\n");
                        break;
                    }
                    if (changeNotifications == 1) {
                        notifications = true;
                    }
                    //Utilizando novo construtor
                    Settings newSettings = new Settings(darkTheme, notifications);
                    user.setSettings(newSettings);
                }
                case 9 -> loginSetting = false;
                default -> print("\n>>>Invalid Option<<<\n");
            }
        }
    }


        private static void listTopics(){
            int indexCount = 0;
            for(Topic topics : globalTopic){
                print("\n["+indexCount+"] Topic: " + topics.getName());
                print("Category: " + topics.getCategory());
                print("Number of Twists: " + topics.getSizeTwists());
                indexCount++;
            }
        }

        private static void showLists(Account user){
            print("\n_____Your_Lists_____\n");
            int indexCount = 0;
            for (List list : user.getLists()){
                print("\n["+indexCount+"] List: " + list.getName());
                print("Description: " + list.getCategory());
                print("Number of Members: " + list.getMembers().size());
            }
        }

    //se existir email igual retorna true
    private static boolean verifEmail(String email){
        for(User user : allUsers){
            if(user.getEmail().equals(email)){
                return true;
            }
        }
        return false;
    }

    //função para listar todos os twists de todos os usuarios
    private static void allUsersTwists(){
        int indexCount = 0;
        for(Twist twist : globalTwists){
            if(twist.isAdminTwist()){

                print("\nTwist ["+indexCount+"]");
                print("@"+twist.getTwistOwner());
                print(twist.getContent());

            }else {

                print("\nTwist ["+indexCount+"]");
                print("@"+twist.getTwistOwner()+"-ACCOUNT");
                print(twist.getContent());
                print("Chars: ["+twist.getContent().length()+"]\n");
            }
            indexCount++;
        }

    }

    //Faz o print de todos os usuarios
    private static void printAllUsers(){
        int count = 0;
        for(User user : allUsers){
            print("\n["+count+"]");
            print("@"+user.getAt()+user.typeOf());
            print("Name: "+user.getName());
            print("Email: "+user.getEmail());
            print("Twists: "+user.getTwisters().size());

            count++;
        }
    }

    //Print do perfil do Admin
    private static void showAdminProfile(Admin admin) {
        print("\n\nADMIN PROFILE\n\n");
        print("Name: "+admin.getName());
        print("@"+admin.getAt()+admin.typeOf());
        print("Email: "+admin.getEmail());
        print("Password: "+admin.getPassword());
        print("ID: "+admin.getId());
        if(admin.getCountry()!=null){
            print("Country: "+admin.getCountry());
            print("Payment: "+admin.getPayment());
        }
    }


    private static void addData(){


        Account accountBruno = new Account("Bruno","senha","bruno", "BPerdona");
        userTwister.add(accountBruno);
        allUsers.add(accountBruno);

        Account accountEminem = new Account("Marshall","123","marshal.com","Eminem");
        userTwister.add(accountEminem);
        allUsers.add(accountEminem);

        Account accountRock = new Account("The Rock","4444","Dwayne Johnson","TheRock");
        userTwister.add(accountRock);
        allUsers.add(accountRock);

        Account accountLucca = new Account("Lucca","6969","lucca.com","Lucca");
        userTwister.add(accountLucca);
        allUsers.add(accountLucca);

        //adicionei
        Admin novoAdmin = new Admin("The Best", "admin", "admin", "admin", 123);
        admins.add(novoAdmin);
        allUsers.add(novoAdmin);

        Topic topicFilme = new Topic("Celebrities", "Films");

        Twist twistFilme1 = new Twist("Flea","Otimo filme");
        Twist twistFilme2 = new Twist("Pedro123","Não gostei");
        topicFilme.addTwist(twistFilme1);
        topicFilme.addTwist(twistFilme2);

        globalTopic.add(topicFilme);

        Topic topicJogos = new Topic("MIBR", "Games");
        globalTopic.add(topicJogos);
    }
}