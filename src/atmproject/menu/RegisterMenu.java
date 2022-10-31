package atmproject.menu;

import atmproject.operations.RegisterOperations;

import java.util.Scanner;

public class RegisterMenu {
    private static RegisterOperations registerOperations;
    private static Scanner scanner = new Scanner(System.in);

    public static void registerMenu(){
        registerOperations = new RegisterOperations();
        userNameMenu();
        passwordMenu();
        bankSelectionMenu();
        if (!registerOperations.isAlreadyRegistered()){
            registerOperations.add();
            System.out.println("Hesap Oluşturuldu");
        }
        else {
            System.out.println("Bu hesap zaten sistemde kayıtlı");
        }
    }
    private static void userNameMenu(){
        boolean valid;
        String userName;
        System.out.print("Kullanıcı adı belirleyin(gmail/live/yahoo uzantılı bir mail adresi): ");
        do {
            userName = scanner.next();
            if (!(valid = registerOperations.validUserName(userName))){
                System.out.print("Lütfen geçerli bir mail adresi girin: ");
            }
        }while (!valid);
        registerOperations.setUserName(userName);
    }

    private static void passwordMenu(){
        boolean valid;
        String password;
        System.out.print("Şifre Belirleyin(6 tane rakam): ");
        do {
            password = scanner.next();
            if (!(valid = registerOperations.validPassword(password))){
                System.out.print("Lütfen geçerli bir şifre girin: ");
            }
        }while (!valid);
        registerOperations.setPassword(password);
    }

    private static void bankSelectionMenu(){
        boolean valid;
        int index;
        System.out.print("1-)Yapı Kredi\n2-)İş Bankası\n3-)QNB\n4-)Akbank\n5-)Vakıfbank\n6-)Garanti\n7-)ING\nBankanızı seçin: ");
        do {
            index = scanner.nextInt();
            if (!(valid = registerOperations.validBank(index))){
                System.out.print("Lütfen sistemde kayıtlı bir banka seçin: ");
            }
        }while (!valid);
        registerOperations.setBank(index);
    }
}
