package application;

import java.util.Locale;
import java.util.Scanner;

import entities.User;

public class Javander {
    public static void main(String[] args){
        Locale.setDefault(Locale.getDefault());
        Scanner sc = new Scanner(System.in);

        // input method
        /**/System.out.print("Name: ");/**/String name = sc.nextLine();/**/
        /**/System.out.print("Password: ");/**/String pass = sc.next();/**/
        /**/System.out.print("CPF: ");/**/String cpf = sc.next();/**/
        /**/System.out.print("E-mail: ");/**/String email = sc.next();/**/
        sc.close();

        Utilities.Process();

        try {
            User a = new User(name, pass, cpf, email);
            System.out.println(a.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}