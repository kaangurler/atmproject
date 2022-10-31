package atmproject.menu;

import atmproject.operations.ATMOperations;
import atmproject.operations.LoginOperations;

import java.util.Scanner;

public class LoginMenu {

    private static LoginOperations loginOperations;
    private static Scanner scanner = new Scanner(System.in);

    public static void loginMenu(){
        loginOperations = new LoginOperations();
        enterUserNameMenu();
        if (loginOperations.getCustomer() != null){
            if (loginOperations.isDeactivated()){
                System.out.println("Bu hesap askıya alınmıştır");
            }
            else {
                enterPassword();
                while (ATMOperations.isOnline()) {
                    ATMMenu.atmMenu();
                }
            }
        }
    }

    private static void enterUserNameMenu(){
        String userName;
        System.out.print("Kullanıcı adını girin/Çıkış(e): ");
        do {
            userName = scanner.next();
            if (userName.equals("e")){
                break;
            }
            else if (loginOperations.isAccountExist(userName) && loginOperations.hasMultipleAccounts(userName)){
                loginOperations.selectCustomer(userName, selectBank(userName));
                break;
            }
            else if (loginOperations.isAccountExist(userName)){
                loginOperations.selectCustomer(userName);
                break;
            }
            else {
                System.out.print("Bu mail kayıtlı değil. Lütfen kayıtlı bir mail adresi girin/Çıkış(e): ");
            }
        }while (true);
    }

    private static void enterPassword(){
        String password;
        System.out.print("Şifreyi girin: ");
        do {
            password = scanner.next();
            if (loginOperations.checkForPassword(password)){
                System.out.println("Giriş başarılı");
                loginOperations.loginAccount();
                break;
            }
            else if (loginOperations.getNoOfAttempts() > 0){
                loginOperations.setNoOfAttempts(loginOperations.getNoOfAttempts() - 1);
                System.out.print("Hatalı şifre. " + (loginOperations.getNoOfAttempts() + 1) + " hakkınız kaldı. Şifre Girin: ");
            }
            else {
                loginOperations.deactivateAccount();
                break;
            }
        }while (true);
    }

    private static int selectBank(String userName){
        int index;
        loginOperations.registeredBanks(userName).stream().forEach(System.out::println);
        System.out.println("Bankanızı seçin: ");
        do {
            index = scanner.nextInt();
            if (index > 0 && index < 8){
                return index;
            }
            System.out.print("Lütfen sistemde kayıtlı bir banka seçin: ");
        }while (true);
    }
}
