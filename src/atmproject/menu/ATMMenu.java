package atmproject.menu;

import atmproject.operations.ATMOperations;
import atmproject.entities.Customer;

import java.util.List;
import java.util.Scanner;

public class ATMMenu {

    private static Scanner scanner = new Scanner(System.in);
    private static Customer onlineCustomer;

    public static void print(){
        for (Customer customer : ATMOperations.getCustomers()){
            System.out.println(customer);
        }
    }

    public static void atmMenu(){
        onlineCustomer = ATMOperations.getOnlineCustomer();
        System.out.println("Güncel Kullanıcı: " + onlineCustomer);
        System.out.println("-----ATM-----");
        System.out.println("1-) Para Yatır\n2-) Para Çek\n3-) Para Transferi(EFT/Havale)\n4-) Hesap Bilgileri\n5-) Hesap Hareketleri\n0-) Çıkış Yap");
        int choice = scanner.nextInt();
        switch (choice){
            case 1:
                deposit();
                break;
            case 2:
                withdraw();
                break;
            case 3:
                transferMoney();
                break;
            case 4:
                accountInfo();
                break;
            case 5:
                print();
                break;
            case 0:
                logout();
                break;
            default:
                System.out.print("Lütfen geçerli bir işlem seçin: ");
                break;
        }
    }

    private static void deposit(){
        System.out.print("Yatırılmak istenen miktar: ");
        double money = scanner.nextDouble();
        ATMOperations.deposit(money);
        System.out.println("Para yatırma işlemi başarılı. Güncel bakiye: " + onlineCustomer.getMoney() + "TL");
        ATMOperations.logToFile(onlineCustomer.getUserName() + " - " + onlineCustomer.getBank().getName() + " hesabına " + money + "TL yatırıldı.");
    }

    private static void withdraw(){
        System.out.print("Çekilmek istenen miktar: ");
        double money = scanner.nextDouble();
        if (ATMOperations.hasEnoughMoney(onlineCustomer, money)){
            ATMOperations.withdraw(money);
            System.out.println("Para çekme işlemi başarılı. Güncel bakiye: " + onlineCustomer.getMoney() + "TL");
            ATMOperations.logToFile(onlineCustomer.getUserName() + " - " + onlineCustomer.getBank().getName() + " hesabından " + money + "TL çekildi.");
        }
        else {
            System.out.println("Hesabınızda yeterli bakiye bulunmamaktadır.");
        }
    }

    private static void transferMoney() {
        Customer customer = selectCustomer();
        if (onlineCustomer.getBank().getId() == customer.getBank().getId()){
            transferEFT(customer);
        }
        else {
            transferMoneyOrder(customer);
        }
    }

    private static void transferEFT(Customer customer){
        System.out.print("EFT miktarı: ");
        double amount = scanner.nextDouble();
        if (ATMOperations.hasEnoughMoney(onlineCustomer, (1.005 * amount))){
            ATMOperations.transferEFT(customer, amount);
            System.out.println("EFT işlemi başarılı. Bu işlem için " + (0.005 * amount) + "TL (%0.5) komisyon kesilmiştir. Güncel bakiye: " + onlineCustomer.getMoney() + "TL");
            ATMOperations.logToFile(onlineCustomer.getUserName() + " - " + onlineCustomer.getBank().getName() + " hesabından " +
                    customer.getUserName() + " - " + customer.getBank().getName() + " hesabına " + amount + "TL EFT yapıldı." +
                    "Banka tarafından " + (0.005 * amount) + "TL komisyon ücreti alındı");
        }
        else {
            System.out.println("Hesabınızda yeterince bakiye bulunmamaktadır.");
        }
    }

    private static void transferMoneyOrder(Customer customer) {
        System.out.print("Havale miktarı: ");
        double amount = scanner.nextDouble();
        if (ATMOperations.hasEnoughMoney(onlineCustomer, (1.01 * amount))){
            ATMOperations.transferMoneyOrder(customer, amount);
            System.out.println("Havale işlemi başarılı. Bu işlem için " + (0.01 * amount) + "TL (%1) komisyon kesilmiştir. Güncel bakiye: " + onlineCustomer.getMoney() + "TL");
            ATMOperations.logToFile(onlineCustomer.getUserName() + " - " + onlineCustomer.getBank().getName() + " hesabından " +
                    customer.getUserName() + " - " + customer.getBank().getName() + " hesabına " + amount + "TL havale yapıldı." +
                    "Banka tarafından " + (0.01 * amount) + "TL komisyon ücreti alındı");
        }
        else {
            System.out.println("Hesabınızda yeterince bakiye bulunmamaktadır.");
        }
    }

    private static Customer selectCustomer(){
        List<Customer> tempList = ATMOperations.getCustomers();
        printCustomers();
        System.out.print("Para transferi yapılacak hesabı seçin: ");
        do {
            int index = scanner.nextInt();
            if (index > 0 && index <= tempList.size()) {
                return tempList.get(index - 1);
            }
            else {
                printCustomers();
                System.out.print("Geçerli bir hesap seçin: ");
            }
        }while (true);
    }

    private static void printCustomers(){
        List<Customer> tempList = ATMOperations.getCustomers();
        for (int i = 1; i <= tempList.size(); i++){
            System.out.println(i + ". Kullanıcı: " + tempList.get(i - 1).getUserName() + " - Banka: " + tempList.get(i - 1).getBank().getName());
        }
    }

    private static void logout(){ATMOperations.setOnlineCustomer(null);}

    private static void accountInfo(){
        ATMOperations.accountInfo();
    }
}
