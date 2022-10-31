package atmproject.entities;

public class Customer {
    private String userName;
    private String password;
    private double money = 0;
    private Bank bank;
    private boolean active = true;

    public Customer(String userName, String password, Bank bank){
        this.userName = userName;
        this.password = password;
        this.bank = bank;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getMoney() {
        return money;
    }

    // Read Only
    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public Bank getBank() {
        return bank;
    }


    public boolean isActive(){
        return active;
    }

    @Override
    public String toString(){
        return userName + " - Bakiye: " + money + "TL - Banka: " + bank.getName();
    }

    // Eğer iki customer objesi aynı userName'e ve aynı bankaya kaydolursa bunlar aynı objelerdir
    @Override
    public boolean equals(Object obj){
        if (((Customer) obj).getUserName().equals(userName) && ((Customer) obj).getBank().getId() == bank.getId()){
            return true;
        }
        else {
            return false;
        }
    }
}
