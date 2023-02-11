import java.util.Scanner;
// Good amount of commits - Ben

public class Driver {

    public static void main(String[] args) {
        System.out.println("Welcome accountant to the SWD Bank!");
        float conversionRate = 1.0F; //conversion rate is changeable

        BankAccount account = new BankAccount("Alex", 100.0F); //Account for one user functionality
        Scanner OS = new Scanner(System.in);
        ///BankAccount[] accounts = new BankAccount[10]; <-- Implementation for multiple users, store several BankAccount objects into one array

        while (true) {

            System.out.println("/////////////Menu////////////////");// Output user input
            System.out.println("Current conversion rate is " + conversionRate);
            System.out.println("1. Update Customer Information (USD)");
            //System.out.println("Create a new user"); idea to make the program store several users, either with a number or name, not implemented
            //System.out.println("Switch to a different user");
            System.out.println("2. Add to Customer Balance (USD)");
            System.out.println("3. Withdraw Customer Balance (SWD)");
            System.out.println("4. Close Account");
            System.out.println("5. Check Balance (USD)");
            System.out.println("6. Change Conversion Rate");
            System.out.println("7. Close Menu");

            System.out.println("///////////////////////////////// Input Command Below:");

            int command = OS.nextInt();

            switch (command) {
                case 1 -> {
                    System.out.println("Please enter the customers new name:");
                    OS.nextLine(); //fixes the error of the skipped line
                    String userName = OS.nextLine();// get username
                    System.out.println("The customers name is now set to " + userName);
                    account.setUserName(userName);
                }
                case 2 -> {
                    System.out.println("Please enter the desires amount to be added to " + account.getUserName() + "'s balance");
                    int bal = OS.nextInt();
                    account.setBalanceUSD((account.getBalanceUSD()) + bal);
                    System.out.println("the customer " + account.getUserName() +  "'s balance is now " + account.getBalanceUSD() + " USD");
                }
                case 3 -> {//Withdraws SWD dollars from the user's account
                    System.out.println("Please enter the amount for withdrawal, it will be converted to SWD. Current Exchange Rate: " + conversionRate);
                    float withdraw = OS.nextFloat();
                    if (withdraw > account.getBalanceUSD()) { //condition to see if the amount to withdraw is valid
                        System.out.println("Sorry, the amount that the customer requested is more than the amount you currently have!");
                    }else if (withdraw <= account.getBalanceUSD()) {
                        System.out.println(account.withdrawSWD(account.getBalanceUSD(), withdraw, conversionRate)[8] + " USD is " + account.getUserName() + "'s new USD total. They will be receiving " + withdraw + " SWD.");
                        System.out.println("They will receive " + (int) account.withdrawSWD(account.getBalanceUSD(), withdraw, conversionRate)[0] + " Twenty Five SWD bills, " + (int) account.withdrawSWD(account.getBalanceUSD(), withdraw, conversionRate)[1] + " Ten SWD bills, " + (int) account.withdrawSWD(account.getBalanceUSD(), withdraw, conversionRate)[2] + " Five SWD bills, " + (int) account.withdrawSWD(account.getBalanceUSD(), withdraw, conversionRate)[3] + " One SWD bills, " + (int) account.withdrawSWD(account.getBalanceUSD(), withdraw, conversionRate)[4] + " twenty cent SWD coins, " + (int) account.withdrawSWD(account.getBalanceUSD(), withdraw, conversionRate)[5] + " eight cent SWD coins, " + (int) account.withdrawSWD(account.getBalanceUSD(), withdraw, conversionRate)[6] + " five cent SWD coins, and " + (int) account.withdrawSWD(account.getBalanceUSD(), withdraw, conversionRate)[7] + " one cent SWD coins");
                        account.setBalanceUSD(account.withdrawSWD(account.getBalanceUSD(), withdraw, conversionRate)[8]);
                    }
                }
                case 4 -> { //closes the account for the given user
                    account.closeAccount(account.getBalanceUSD());
                    System.out.println(account.getUserName() + "'s account has been closed!");
                    System.out.println("Their final balance was " + account.getBalanceUSD() + " USD");
                    System.out.println("They will receive " + (int)account.closeAccount(account.getBalanceUSD())[0] + " Twenty dollar bills, " + (int)account.closeAccount(account.getBalanceUSD())[1] + " Ten dollar bills, " + (int)account.closeAccount(account.getBalanceUSD())[2] + " Five dollar bills, " + (int)account.closeAccount(account.getBalanceUSD())[3] + " One dollar bills, " + (int)account.closeAccount(account.getBalanceUSD())[4] + " quarters, " + (int)account.closeAccount(account.getBalanceUSD())[5] + " dimes, " + (int)account.closeAccount(account.getBalanceUSD())[6] + " nickels, and " + (int)account.closeAccount(account.getBalanceUSD())[7] + " pennies")  ;
                    account.setBalanceUSD(0);
                    System.exit(0);
                }
                ///If were implemented with multiple users, ask for name of account
                ///Close account by returning money and stating specific amount of balance remaining and how many of each coin will be given, terminating the account and deleting them from said array.

                case 5 ->
                        System.out.println("The current balance of the customer " + account.getUserName() + " is now " + account.getBalanceUSD() + " USD");//balance is always in USD
                case 6 -> {
                    System.out.println("Please enter the new exchange rate for all customers. Please note that the rate is USD to SWD.");
                    conversionRate = OS.nextFloat();

                    System.out.println("The new exchange rate for USD to SWD customers is now " + conversionRate);
                }
                case 7 -> {
                    System.out.println("Closing program, thank you for your service...");
                    System.exit(0);
                }
            }
        }
    }
}
