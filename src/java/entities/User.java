package entities;

/// on a real bank application, are applicated a http request for a database
/// so the random import are inutil;
import java.util.Random;
import application.Utilities;

public class User{
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

    public User(String name, String pass, String cpf, String email){
        // if the name  is different of null, the operation returns an error and the account aren't created
        if(valideName(name)){
            this.name = name;
        }
        if(valideEmail(email)){
            this.email = email;
        }
        if(validePass(pass)){
            this.pass = pass;
        }
        if(valideCpf(cpf)){
            this.cpf = cpf;
        }
    }
    
    private static boolean valideName(String name){
        boolean v = false;
        try {
            if (name != "") {
                v = true;
            }
            else{
                throw new Exception("NullField: --- The name field cannot be a null value");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return v;
    }

    private static boolean validePass(String pass){
        boolean v = false;
        try {
            if (pass != "") {
                if (pass.length() >= 6){
                    v = true;
                }
                else{
                    v = false;
                    throw new Exception("Low password: --- The password cannot be less of 6 characters");
                }
            }
            else{
                throw new Exception("NullField: --- The password field cannot be a null value");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return v;
    }
    private static boolean valideCpf(String cpf){
        boolean v = false;
        try {
            if (cpf != "") {
                if (Utilities.CPFpattern.matcher(cpf).find()){
                    v = true;
                }
                else{
                    throw new Exception("InvalidFormat: --- The CPF field cannot contain a less value of eleven");
                }
            }
            else{
                throw new Exception("NullField: --- The cpf field cannot be a null value");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return v;
    }
     private static boolean valideEmail(String email){
        boolean v = false;
        try {
            if (email != "") {
                if (Utilities.EmailPattern.matcher(email).find()){
                    v = true;
                }
                else{
                    throw new Exception("InvalidFormat: --- The E-mail field must have an E-mail with domain from a valid provider.");
                }
            }
            else{
                throw new Exception("NullField: --- The E-mail field cannot be a null value");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return v;
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
        return 
        "------------------------------###"+
        "\n User " + AccountId +
        "\n\nName: " + name +
        "\nEmail: " + email +
        "\nCPF: " + cpf +
        "\nCash: " + cash +
        "\n" +
        "------------------------------###\n";
    }
}
