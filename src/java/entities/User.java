package entities;

/// on a real bank application, are applicated a http request for a database
/// so the random import are inutil;
import java.util.Random;
import application.Utilities;

public class User {
    // --- AccountId generator with 'random' lib
    private Random rand = new Random();
    // User Info
    private String name = "";
    private String email = "";
    private String pass = "";
    private String cpf = "";
    // Account Info
    private int AccountId = rand.nextInt(1000001);
    private double cash = 0;
    /// Main Constructor

    public User(String name, String pass, String cpf, String email) {
        // if the name is different of null, the operation returns an error and the
        // account aren't created
        this.name = name;
        this.email = email;
        this.pass = pass;
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getCpf() {
        return cpf;
    }

    @Override
    public String toString() {
        return "------------------------------###" +
                "\n User " + AccountId +
                "\n\nName: " + name +
                "\nEmail: " + email +
                "\nCPF: " + cpf +
                "\nCash: " + cash +
                "\n" +
                "------------------------------###\n";
    }
}
