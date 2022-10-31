package atmproject.operations;

import atmproject.entities.Customer;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class AdminOperations {
    public void reactivateAccount(Customer customer){
        customer.setActive(true);
    }

    public List<Customer> deactivatedAccounts(){
        List<Customer> tempList = new ArrayList<>();
        for (int i = 0; i < ATMOperations.getCustomers().size(); i++){
            if (!ATMOperations.getCustomers().get(i).isActive()){
                tempList.add(ATMOperations.getCustomers().get(i));
            }
        }
        return tempList;
    }

    public void readFile(){
        try {
            FileReader fr=new FileReader(ATMOperations.getLogFile());
            int i;
            while((i=fr.read())!=-1)
                System.out.print((char)i);
            fr.close();
            System.out.println();
        }
        catch (Exception e){
            e.toString();
        }
    }
}
