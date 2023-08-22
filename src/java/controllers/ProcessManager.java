package controllers;

import services.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import entities.User;
import application.Utilities;
import application.enums.RegisterProcessEnum;
//import application.enums.LoginProcessEnum;

public class ProcessManager {
    private static String processId = getNewProcessId(3);
    private static RegisterProcessEnum status = RegisterProcessEnum.PREPAIRING_TO_GET_DATA_FROM_INPUT;
    private static DateTimeFormatter fmtr = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private static String moment = fmtr.format(LocalDateTime.now());
    private static Scanner globalScanner = new Scanner(System.in);

    private static String getNewProcessId(int length) {
        // ID cannot contain a character value greater than the limit 5
        int limit = 5;
        try {
            if (length > 0) {
                String result = StringRandomizer.newRString(limit, '*');
                for (int i = 0; i < (length - 1); i++) {
                    result += "-" + StringRandomizer.newRString(limit, '*');
                }
                return result;
            } else {
                throw new Exception("NullValueException");
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public User UserProcess(String type) {
        System.out.println(" * Process ID: " + processId + " Innitiated on " + moment);
        // instantiates the TestResult variable and a list to after receive the input result
        List<Boolean> values = new ArrayList<>();
        List<String> valideInputResultList = new ArrayList<>();
        int vcheckerCount = 0;

        String UserName = "";
        String UserPass = "";
        String UserEmail = "";
        String UserCPF = "";

        // prints the actual processStatus
        System.out.println(" * " + status + " -- " + fmtr.format(LocalDateTime.now()));
        // gets an serverStatusCode for these Process
        String statusCode = UserGlobalIdService.getGlobalId();
        try {
            // changes processStatus to 
            status = RegisterProcessEnum.GETTING_DATA_FROM_INPUT;
            System.out.println("\n * " + status + " -- " + fmtr.format(LocalDateTime.now()));
            // opens the input to receive data from user
            if (type.equalsIgnoreCase("register")) {
                valideInputResultList = userInputRegister('r');
                for (int j = 0; j < valideInputResultList.size(); j++) {
                    if(j<4){
                        values.add(Boolean.valueOf(valideInputResultList.get(j)));
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
            }else if(type.equalsIgnoreCase("login")){
                valideInputResultList = userInputLogin();
                for (int j = 0; j < valideInputResultList.size(); j++) {
                    if(j<4){
                        values.add(Boolean.valueOf(valideInputResultList.get(j)));
                    }
                    else if(j==4) {
                        UserName = valideInputResultList.get(j);
                    }else if(j==5) {
                        UserPass = valideInputResultList.get(j);
                    }else if(j==7) {
                        UserEmail = valideInputResultList.get(j);
                    }
                    //System.out.println(valideInputResultList.get(j));
                }
                
            }
            status = RegisterProcessEnum.VALIDATING_DATA;
            System.out.println("\n * " + status + " -- " + fmtr.format(LocalDateTime.now()));
            for (boolean boolean1 : values) {
                if (boolean1) {
                    Thread.sleep(100);
                    ++vcheckerCount;
                }
            }
            // Verify if the serverStatusCode received from server is equal to 200 or 201,
            // if not, serverStatusCode is changed to failed.
            if (vcheckerCount == 4) {

                status = RegisterProcessEnum.VALIDATION_SUCESS;
                System.out.println("\n * " + status + " -- " + fmtr.format(LocalDateTime.now()));
                if (type.equalsIgnoreCase("register")) {
                    
                    status = RegisterProcessEnum.SENDING_CONFIRMATION_EMAIL;
                    System.out.println("\n * " + status + " -- " + fmtr.format(LocalDateTime.now()));
    
                    String ConfCode = StringRandomizer.newRString(6, 'c');
                    String confMessage = "<!DOCTYPE html><html lang='pt'><head><meta name='format-detection' content='email=no' /><meta name='format-detection' content='date=no' /><style nonce='lmXSz_HSGVKdjIf5jU_8xQ'>.awl a {color: #FFFFFF;text-decoration: none;}.abml a {color: #000000;font-family: Roboto-Medium, Helvetica, Arial, sans-serif;font-weight: bold;text-decoration: none;}.adgl a {color: rgba(0, 0, 0, 0.87);text-decoration: none;}.afal a {color: #b0b0b0;text-decoration: none;}@media screen and (min-width: 600px) {.v2sp {padding: 6px 30px 0px;}.v2rsp {padding: 0px 10px;}}@media screen and (min-width: 600px) {.mdv2rw {padding: 40px 40px;}}</style><link href='//fonts.googlea=pis.com/css?family=Google+Sans' rel='stylesheet' type='text/css' nonce='lmXSz_HSGVKdjIf5jU_8xQ' /></head><body style='margin: 0; padding: 0;height: 100%;' bgcolor='#FFFFFF'><table width='100%' height='100%' style='min-width: 348px;' border='0' cellspacing='0' cellpadding='0'lang='pt'><tr height='40' style='height: 40px;'><td></td></tr><tr align='center'><td><div style='height: 50px;width: 50px;' itemscope itemtype='//schema.org/EmailMessage'><div width='50px' itemprop='=action' itemscope itemtype='//schema.org/ViewAction'><link width='50px' itemprop='url'href='https://accounts.google.com/AccountChooser?Email=javander.bank=@gmail.com&amp;continue=https://myaccount.google.com/alert/nt/16927245741=70?rfn%3D302%26rfnc%3D12%26eid%3D0%26et%3D1' /><meta itemprop='name' content='logo' /></div></div><table border='0' cellspacing='0=' cellpadding=' 0' style='padding-bottom: 20px;max-width:516px; min-width: 220px;'><tr><td width='8' style='width: 8px;'></td><td><div style='border-style: solid; border-width:medium;border-color:#e60000f5;border-radius: 8px;align-items:center;justify-content: center;' class='mdv2rw'><img src='https://raw.githubusercontent.com/NathanJSDev/Javander/49bb03dadef93ffb35676a01f42f00b212a282e9/src/data/Javander.png'width='50' height='50'aria-hidden='true'alt='Javander'style='position:absolute;left:50%;transform:translateX(-50%);'><div style='font-family:Google thin solid #ffffff; color: #000; line-height: 32px; padding-bottom:24px;padding-top: 70px;text-align: center; word-break: break-word;'><div style='font-size: 24p=x;'>" + String.format("Prezado usu\u00E1rio %s</div></div><div style='font-family: Roboto-Regular,Helvetica,Arial,sans-serif; font-size: 14px; color: #fcfcfc; line-height: 20px;padding-top: 20px; text-align: left;'></div>O Javander recebeu uma solicita\u00E7\u00E3o para usar <a style='font-weight:bold;'>%s</a> como o e-mail de login da Conta Javander.<br><br>Use este c\u00F3digo para concluir a configura\u00E7\u00E3o da Conta:<br><div style='text-align: center;font-size: 36px; margin-top: 20px;line-height: 44px;'>%s</div><br>Se voc\u00EA n\u00E3o reconhece esta a\u00E7\u00E3o, ignore este e-mail.</div></div><div style='text-align: left;'><div style='font-family: Roboto-Regular,Helvetica,Arial,sans-serif;color:#000; font-size: 11px; line-height: 18px; padding-top: 12px;text-align: center;'><div>Este e-mail foi enviado a voc\u00EA para informar sobre tentativa de cria\u00E7\u00E3o de uma conta Javander com seu endere\u00E7o de e-mail.</div><div style='direction: ltr;'>&copy; 2023 Javander.</div></div></div></td><td width='8' style='width: 8px;'></td></tr></table></td></tr><tr height='32' style='height: 32px;'><td></td></tr></table><body></html>", UserName,UserEmail,ConfCode);
                    try {
                        EmailSender email = new EmailSender();
                        email.sendEmail("qnuoklgmivrdzknq", "javander.bank@gmail.com", UserEmail, confMessage, "Javander Auth Email Confirmation");
                    } catch (Exception e) {
                        status = RegisterProcessEnum.EMAIL_NOT_ARRIVED_TO_DESTINATION;
                        System.out.println("\n * " + status + " -- " + fmtr.format(LocalDateTime.now()));
    
                        System.err.println(e.getMessage());
                        throw new Exception(e.getMessage());
                    }
    
                    status = RegisterProcessEnum.CONFIRMATION_EMAIL_ARRIVED;
                    System.out.println("\n * " + status + " -- " + fmtr.format(LocalDateTime.now()));
    
                    status = RegisterProcessEnum.WAITING_CONFIRMATION_CODE;
                    System.out.println("\n * " + status + " -- " + fmtr.format(LocalDateTime.now()));
                    System.out.println("Put the six digits code below: ");
                    if (confCodeString().equals(ConfCode)) {
                        status = RegisterProcessEnum.EMAIL_CONFIRMED;
                        System.out.println("\n * " + status + " -- " + fmtr.format(LocalDateTime.now()));
                        return new User(UserName, UserPass, UserCPF, UserEmail);
                    }else{
                        status = RegisterProcessEnum.INVALID_CODE;
                        System.out.println("\n * " + status + " -- " + fmtr.format(LocalDateTime.now()));
                        return new User(null, null, null, null);
                    }
                }else if(type.equalsIgnoreCase("login")){

                    return null;
                }else{
                    return new User(null, null, null, null);
                }
            }else if (Integer.valueOf(statusCode) >= 202){

                status = RegisterProcessEnum.GET_DATA_FAILED;
                System.out.println("\n * " + status + " -- " + fmtr.format(LocalDateTime.now()));
                throw new Exception(String.valueOf(statusCode));
            }
            else if (vcheckerCount < 4){

                status = RegisterProcessEnum.VALIDATION_FAILED;
                System.out.println("\n * " + status + " -- " + fmtr.format(LocalDateTime.now()));

                throw new Exception(String.valueOf(statusCode));
            }else{
                return new User(null, null, null, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new User(null, null, null, null);
        }
    }

    private static List<String> userInputRegister(char r) {
        boolean vn = false, vp = false, vc = false, ve = false;
        List<String> result = new ArrayList<>();
        /// input method
        /**/System.out.print("Name: ");/**/String name=globalScanner.nextLine();/**/
        /**/System.out.print("Password: ");/**/String pass=globalScanner.next();/**/
        /**/System.out.print("CPF: ");/**/String cpf=globalScanner.next();      /**/
        /**/System.out.print("E-mail: ");/**/String email=globalScanner.next(); /**/

        try {
            if (!(name == "") && !(pass == "") && !(cpf == "") && !(email == "")) {
                vn = true;
                vp = true;
                if (pass.length() <= 6) {
                    System.out.println("WARNING: LowPassword");
                }
                if (Utilities.EmailPattern.matcher(email).find() || Utilities.CPFpattern.matcher(cpf).find()) {
                    ve = true;
                    vc = true;
                }
                else throw new Exception("InvalidFormatException");
            }
            else throw new Exception("NullFieldException");
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

    private static List<String> userInputLogin() {
        boolean vn = false, vp = false,  ve = false;
        List<String> result = new ArrayList<>();
        // input method
        /**/System.out.print("Name: ");/**/String name=globalScanner.nextLine();/**/
        /**/System.out.print("Password: ");/**/String pass=globalScanner.next();/**/
        /**/System.out.print("E-mail: ");/**/String email=globalScanner.next(); /**/

        try {
            if (!(name == "") && !(pass == "") && !(email == "")) {
                vn = true;
                vp = true;
                if (pass.length() <= 6) {
                    System.out.println("WARNING: LowPassword");
                }
                if (Utilities.EmailPattern.matcher(email).find()) {
                    ve = true;
                }
                else throw new Exception("InvalidFormatException");
            }
            else throw new Exception("NullFieldException");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        result.add(String.valueOf(vn));
        result.add(String.valueOf(vp));
        result.add(String.valueOf(ve));
        result.add(name);
        result.add(pass);
        result.add(email);
        return result;
    }

    private static String confCodeString() {
        String code = String.valueOf(globalScanner.next());
        // DEBUG: System.out.println(code);
        globalScanner.close();
        return code;
    }
}
