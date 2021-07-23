package br.com.brunoperdona.twister.entities;

import java.util.ArrayList;

public class Admin extends User{
    private int id;
    private String country;
    private float payment;

    public Admin(String name, String password, String email, String at, int id, String country, float payment) {
        super(name, password, email, at);
        this.id = id;
        this.country = country;
        this.payment = payment;
    }

    //construtor para admins voluntarios
    public Admin(String name, String password, String email, String at, int id) {
        super(name, password, email, at);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public float getPayment() {
        return payment;
    }

    public ArrayList<Twist> twisters(){
        return super.getTwisters();
    }

    //Implementado de User
    public String typeOf(){
        return "-ADMIN";
    }
}
