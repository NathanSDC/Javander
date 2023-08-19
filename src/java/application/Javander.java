package application;

import entities.User;

public class Javander {
    private static String processId = Utilities.RandomId(3);
    public static void main(String[] args){
        try {
            User a = new User("jubiscrevaldo", "wasdqwerty12", "12345678900","jubiscrevaldo.tavino@outlook.com");
            System.out.println(processId + "\n" + a.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}