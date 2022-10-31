package atmproject.menu;

import atmproject.operations.ATMOperations;

import java.util.Scanner;

public class MainMenu {

    private static Scanner scanner = new Scanner(System.in);

    public static void menu(){
        ATMOperations.createFile();
        boolean exit = false;
        do {
            System.out.println("1-)Giriş Yap");
            System.out.println("2-)Kayıt Ol");
            System.out.println("3-)Admin Menüsü");
            System.out.println("0-)Çıkış");
            int choice = scanner.nextInt();
            switch (choice){
                case 1:
                    LoginMenu.loginMenu();
                    break;
                case 2:
                    RegisterMenu.registerMenu();
                    break;
                case 3:
                    AdminMenu.adminMenu();
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Geçerli bir işlem yapın");
                    break;
            }
        }while (!exit);
        ATMOperations.removeFile();
    }
}
