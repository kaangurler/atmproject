package atmproject.operations;

import atmproject.entities.Customer;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class ATMOperations {

    private static Customer onlineCustomer;
    private static List<Customer> customers = new ArrayList<>();

    private static File logFile = new File("Log File.txt");

    public static void setOnlineCustomer(Customer onlineCustomer) {
        ATMOperations.onlineCustomer = onlineCustomer;
    }

    public static Customer getOnlineCustomer() {
        return onlineCustomer;
    }

    public static List<Customer> getCustomers() {
        return customers;
    }

    public static File getLogFile() { return logFile; }

    public static void registerCustomer(Customer customer){ customers.add(customer); }

    public static void deposit(double deposit){
        increaseAmount(onlineCustomer, deposit);
    }

    public static void withdraw(double withdraw){
        decreaseAmount(onlineCustomer, withdraw);
    }

    public static void transferEFT(Customer customer, double amount) {
        transferMoney(customer, amount, 0.005);
    }

    public static void transferMoneyOrder(Customer customer, double amount){
        transferMoney(customer, amount, 0.01);
    }

    private static void transferMoney(Customer customer, double amount, double commissionRate){
        if (hasEnoughMoney(onlineCustomer, amount)){
            increaseAmount(customer, amount);
            amount *= (1 + commissionRate);
            decreaseAmount(onlineCustomer, amount);
        }
    }

    public static Customer onlineCustomer(Customer customer){
        return customer;
    }

    public static boolean hasEnoughMoney(Customer customer, double amount){
        if (customer.getMoney() - amount >= 0){
            return true;
        }
        return false;
    }

    private static void decreaseAmount(Customer customer, double amount){
        customer.setMoney(customer.getMoney() - amount);
    }

    private static void increaseAmount(Customer customer, double amount){
        customer.setMoney(customer.getMoney() + amount);
    }

    public static void accountInfo(){
        System.out.println(onlineCustomer.toString());
    }

    public static boolean isOnline(){
        if (onlineCustomer != null){
            return true;
        }
        return false;
    }

    public static void createFile(){
        try {
            logFile.createNewFile();
        }catch (Exception e){
            e.toString();
        }
    }

    public static void logToFile(String str) {
        try {
            FileWriter fw = new FileWriter(logFile, true);
            fw.append(str + "\n");
            fw.close();
        }
        catch (Exception e){
            e.toString();
        }
    }

    public static void removeFile() {
        try {
            logFile.delete();
        }catch (Exception e){
            e.toString();
        }
    }
}
