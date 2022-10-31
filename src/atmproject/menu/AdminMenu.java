package atmproject.menu;

import atmproject.operations.ATMOperations;
import atmproject.operations.AdminOperations;
import atmproject.entities.Customer;

import java.util.List;
import java.util.Scanner;

public class AdminMenu {

    private static AdminOperations adminOperations;
    private static List<Customer> list = ATMOperations.getCustomers();
    private static Scanner scanner = new Scanner(System.in);

    public static void adminMenu(){
        boolean exit = false;
        adminOperations = new AdminOperations();
        do {
            System.out.println("---ADMIN EKRANI---");
            System.out.println("1-) Müşterileri Listele\n2-) Hesap Hareketlerini Listele\n3-) Dondurulmuş Hesabı Aktive Et\n0-) Çıkış");
            int choice = scanner.nextInt();
            switch (choice){
                case 1:
                    printAllCustomers();
                    break;
                case 2:
                    openLogFile();
                    break;
                case 3:
                    reactivateAccount();
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    break;
            }
        }while (!exit);

    }

    private static void reactivateAccount(){
        printDeactivatedCustomers();
        if (!adminOperations.deactivatedAccounts().isEmpty()) {
            System.out.print("Aktive edilecek kullanıcıyı seçin: ");
            adminOperations.reactivateAccount(list.get(scanner.nextInt()));
        }
        else {
            System.out.println("Tüm hesaplar aktif durumda");
        }
    }

    private static void printDeactivatedCustomers(){
        List<Customer> tempList = adminOperations.deactivatedAccounts();
        for (int i = 0; i < tempList.size(); i++){
            if (!tempList.get(i).isActive()){
                System.out.println((i + 1) + ". " + tempList.get(i));
            }
        }
    }

    private static void printAllCustomers(){
        for (int i = 0; i < list.size(); i++){
            System.out.println((i + 1) + ". " + list.get(i));
        }
    }

    private static void openLogFile(){
        adminOperations.readFile();
    }
}
