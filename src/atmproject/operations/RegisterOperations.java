package atmproject.operations;

import atmproject.entities.Bank;
import atmproject.entities.Customer;

public class RegisterOperations {

    private String userName;
    private String password;
    private Bank bank;

    public void setUserName(String userName){
        this.userName = userName;
    }
    public void setPassword(String password){
        this.password = password;
    }

    public void setBank(int index){
        for (Bank b : Bank.values()){
            if (b.getId() == index){
                bank = b;
            }
        }
    }

    public boolean validUserName(String userName){
        String[] mailExtensions = {"@gmail.com", "@live.com", "@yahoo.com"};
        for (String extension : mailExtensions){
            if (userName.endsWith(extension)){
                return true;
            }
        }
        return false;
    }

    public boolean validPassword(String password){
        if (password.length() == 6 && digitCheck(password)){
            return true;
        }
        return false;
    }

    public boolean validBank(int index){
        if (index > 0 && index < 8){
            return true;
        }
        return false;
    }

    private boolean digitCheck(String password){
        for (char ch : password.toCharArray()) {
            if (!Character.isDigit(ch)) {
                return false;
            }
        }
        return true;
    }

    public boolean isAlreadyRegistered(){
        if (ATMOperations.getCustomers().contains(new Customer(userName, password, bank))){
            return true;
        }
        return false;
    }

    public void add(){
        ATMOperations.registerCustomer(new Customer(userName, password, bank));
    }
}
