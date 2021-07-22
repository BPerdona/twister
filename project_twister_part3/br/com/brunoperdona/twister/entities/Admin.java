package br.com.brunoperdona.twister.entities;

public class Admin extends User{
    private int id;
    private String country;
    private double payment;

    public Admin(String name, String password, String email, String at, int id, String country, double payment) {
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

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }
}
