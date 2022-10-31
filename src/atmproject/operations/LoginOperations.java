package atmproject.operations;

import atmproject.entities.Customer;

import java.util.ArrayList;
import java.util.List;

public class LoginOperations {

    private int noOfAttempts = 2;
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setNoOfAttempts(int noOfAttempts) {
        this.noOfAttempts = noOfAttempts;
    }

    public int getNoOfAttempts() {
        return noOfAttempts;
    }

    public void selectCustomer(String userName){
        for (Customer customer : ATMOperations.getCustomers()){
            if (customer.getUserName().equals(userName)){
                this.customer = customer;
            }
        }
    }

    public void selectCustomer(String userName, int index){
        for (Customer customer : ATMOperations.getCustomers()){
            if (customer.getUserName().equals(userName) && customer.getBank().getId() == index){
                this.customer = customer;
            }
        }
    }

    public List<String> registeredBanks(String userName){
        List<String> bankNames = new ArrayList<>();
        for (Customer customer : ATMOperations.getCustomers()){
            if (customer.getUserName().equals(userName)){
                bankNames.add(customer.getBank().getName());
            }
        }
        return bankNames;
    }

    public boolean isAccountExist(String username){
        for (Customer customer : ATMOperations.getCustomers()){
            if (customer.getUserName().equals(username)){
                return true;
            }
        }
        return false;
    }

    public boolean checkForPassword(String password){
        if (customer.getPassword().equals(password)){
            return true;
        }
        return false;
    }

    public boolean hasMultipleAccounts(String userName){
        int i = 0;
        for (Customer customer : ATMOperations.getCustomers()){
            if (customer.getUserName().equals(userName)){
                i++;
            }
            if (i >= 2){
                return true;
            }
        }
        return false;
    }

    public boolean isDeactivated(){
        if (customer.isActive()){
            return false;
        }
        return true;
    }

    public void loginAccount(){
        if (!isDeactivated()) {
            ATMOperations.setOnlineCustomer(customer);
        }
    }

    public void deactivateAccount(){
        customer.setActive(false);
    }
}
