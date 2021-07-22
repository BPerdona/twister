package br.com.brunoperdona.twister.entities;

import java.util.ArrayList;
import br.com.brunoperdona.twister.settings.Settings;


public class Account extends User{

    private ArrayList<List> lists;
    private ArrayList<Account> friend;

    public Account(String name, String password, String email, String at) {
        super(name, password, email, at);
        this.lists = new ArrayList<List>();
        this.friend = new ArrayList<Account>();
    }

    //TODO Arruma isso
    public Account(String name, String password, String email, String at, Settings settings) {
        super(name, password, email, at, settings);
        this.lists = new ArrayList<List>();
        this.friend = new ArrayList<Account>();
    }

    public ArrayList<List> getLists() {
        return lists;
    }

    public ArrayList<Account> getFriend() {
        return friend;
    }

    public void deleteFriend(int index){
        if(index>friend.size()){
            return;
        }
        if(index<0){
            return;
        }
        this.friend.remove(index);
    }
}
