package controllers;

import services.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import entities.User;

import application.Utilities;
import application.enums.Process;

public class ProcessManager {
    private static String processId = getNewProcessId(3);
    private static Process status = Process.PREPAIRING_TO_GET_DATA_FROM_INPUT;
    private static DateTimeFormatter fmtr = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private static String moment = fmtr.format(LocalDateTime.now());

    private static String getNewProcessId(int length) {
        // ID cannot contain a character value greater than the limit 5
        int limit = 5;
        try {
            if (length > 0) {
                String result = StringRandomizer.newRString(limit);
                for (int i = 0; i < (length - 1); i++) {
                    result += "-" + StringRandomizer.newRString(limit);
                }
                return result;
            }else{
                throw new Exception("NullValueException");
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public User Init() {
        System.out.println(" * Process ID: " + processId + " Innitiated on moment " + moment);
        // instantiates the TestResult variable and a list to after receive the input result
        String testResult = "";
        List<Boolean> values = new ArrayList<>();
        List<String> valideInputResultList = new ArrayList<>();
        int vcheckerCount = 0;

        String UserName = "";
        String UserPass = "";
        String UserCPF = "";
        String UserEmail = "";

        // prints the actual processStatus
        System.out.println(" * " + status + " -- " + fmtr.format(LocalDateTime.now()));
        // gets an serverStatusCode for these Process
        String statusCode = UserGlobalIdService.getGlobalId();
        try {
            // changes processStatus to 
            status = Process.GETTING_DATA_FROM_INPUT;
            System.out.println("\n * " + status + " -- " + fmtr.format(LocalDateTime.now()));
            // opens the input to receive data from user
            valideInputResultList = valideInput();
            for (int j = 0; j < valideInputResultList.size(); j++) {
                if(j<4){
                    values.add(Boolean.valueOf(valideInputResultList.get(j)));
                    System.out.println(valideInputResultList.get(j));
                }
                else if(j==4) {
                    UserName = valideInputResultList.get(j);
                }else if(j==5) {
                    UserPass = valideInputResultList.get(j);
                }else if(j==6) {
                    UserCPF = valideInputResultList.get(j);
                }else if(j==7) {
                    UserEmail = valideInputResultList.get(j);
                }
                //System.out.println(valideInputResultList.get(j));
            }
            status = Process.VALIDATING_DATA;
            for (boolean boolean1 : values) {
                if (boolean1) {
                    Thread.sleep(100);
                    ++vcheckerCount;
                }
            }
            // Verify if the serverStatusCode received from server is equal to 200 or 201,
            // if not, serverStatusCode is changed to failed.
            if (vcheckerCount == 4) {
                status = Process.VALIDATION_SUCESS;
            }else if (199 >= 202){
                status = Process.GET_DATA_FAILED;
                throw new Exception(String.valueOf(statusCode));
            }else if (vcheckerCount < 4){
                status = Process.VALIDATION_FAILED;
                throw new Exception(String.valueOf(statusCode));
            }
            testResult += "\n * " + status + " -- " + fmtr.format(LocalDateTime.now());
            if (status == Process.VALIDATION_SUCESS) {
                status = Process.SENDING_CONFIRMATION_EMAIL;
                EmailSender email = new EmailSender();
                email.sendEmail("qnuoklgmivrdzknq", "javander.bank@gmail.com", UserEmail, processId, "Javander Auth Email Confirmation");
                testResult += "\n * " + status + " -- " + fmtr.format(LocalDateTime.now());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(testResult);
        return new User(UserName, UserPass, UserCPF, UserEmail);
    }
    private static List<String> valideInput(){
        boolean vn = false,vp = false,vc = false,ve = false;
        List<String> result = new ArrayList<>();
        //Scanner sc = new Scanner(System.in);
        // input method
        //**/System.out.print("Name: ");/**/String name=sc.nextLine();/**/
        //**/System.out.print("Password: ");/**/String pass=sc.next();/**/
        //**/System.out.print("CPF: ");/**/String cpf=sc.next();      /**/
        //**/System.out.print("E-mail: ");/**/String email=sc.next(); /**/
        //**/sc.close();                                                /**/

        String name = "Nathan";
        String pass = "cadelinha1.";
        String cpf = "123.123.123-22";
        String email = "barbequil14@gmail.com";

        try {
            if (!(name=="") && !(pass=="") && !(cpf=="") && !(email=="")) {
                vn = true;
                vp = true;
                if (pass.length() <= 6) {
                    System.out.println("WARNING: LowPassword");
                }
                if (Utilities.EmailPattern.matcher(email).find() || Utilities.CPFpattern.matcher(cpf).find()){
                    ve = true;
                    vc = true;
                }
                //else throw new Exception("InvalidFormatException");
            }
            //else throw new Exception("NullFieldException");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        result.add(String.valueOf(vn));
        result.add(String.valueOf(vp));
        result.add(String.valueOf(vc));
        result.add(String.valueOf(ve));
        result.add(name);
        result.add(pass);
        result.add(cpf);
        result.add(email);
        return result;
    }
}
