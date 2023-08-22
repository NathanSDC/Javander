package application;

import controllers.ProcessManager;

public class Javander {
    public static void main(String[] args){
        ProcessManager p1 = new ProcessManager();
        
        try {
            p1.Init();
            //User a = new User("name", "123456", "123.456.789-00", "abc@def.ghi");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}