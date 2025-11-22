import java.util.Scanner;

class Account {
    int accountNumber;
    String accountHolder;
    double balance;

    // Constructor
    Account(int accountNumber, String accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    // Deposit Method
    void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Amount Deposited Successfully.");
        } else {
            System.out.println("Invalid Amount!");
        }
    }

    // Withdraw Method
    void withdraw(double amount) {
        if (amount <= balance && amount > 0) {
            balance -= amount;
            System.out.println("Amount Withdrawn Successfully.");
        } else {
            System.out.println("Insufficient Balance or Invalid Amount!");
        }
    }

    // Display Account Details
    void display() {
        System.out.println("---------------");
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Account Holder : " + accountHolder);
        System.out.println("Balance        : " + balance);
        System.out.println("---------------");
    }
}

public class BankingApp {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Account[] accounts = new Account[100];  // Array to store multiple accounts
        int count = 0;  // Number of accounts created

        int choice;

        do {
            System.out.println("\n===== BANKING APPLICATION =====");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. View Account Details");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {

                case 1:
                    System.out.print("Enter Account Number: ");
                    int accNum = sc.nextInt();
                    sc.nextLine();  

                    System.out.print("Enter Account Holder Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Initial Balance: ");
                    double bal = sc.nextDouble();

                    accounts[count] = new Account(accNum, name, bal);
                    count++;

                    System.out.println("Account Created Successfully!");
                    break;

                case 2:
                    System.out.print("Enter Account Number: ");
                    int depNum = sc.nextInt();

                    boolean foundDep = false;
                    for (int i = 0; i < count; i++) {
                        if (accounts[i].accountNumber == depNum) {
                            System.out.print("Enter Amount to Deposit: ");
                            double damt = sc.nextDouble();
                            accounts[i].deposit(damt);
                            foundDep = true;
                            break;
                        }
                    }
                    if (!foundDep) {
                        System.out.println("Account Not Found!");
                    }
                    break;

                case 3:
                    System.out.print("Enter Account Number: ");
                    int wNum = sc.nextInt();

                    boolean foundW = false;
                    for (int i = 0; i < count; i++) {
                        if (accounts[i].accountNumber == wNum) {
                            System.out.print("Enter Amount to Withdraw: ");
                            double wamt = sc.nextDouble();
                            accounts[i].withdraw(wamt);
                            foundW = true;
                            break;
                        }
                    }
                    if (!foundW) {
                        System.out.println("Account Not Found!");
                    }
                    break;

                case 4:
                    System.out.print("Enter Account Number: ");
                    int vNum = sc.nextInt();

                    boolean foundV = false;
                    for (int i = 0; i < count; i++) {
                        if (accounts[i].accountNumber == vNum) {
                            accounts[i].display();
                            foundV = true;
                            break;
                        }
                    }
                    if (!foundV) {
                        System.out.println("Account Not Found!");
                    }
                    break;

                case 5:
                    System.out.println("Thank you for using the Banking Application!");
                    break;

                default:
                    System.out.println("Invalid Choice! Try Again.");
            }

        } while (choice != 5);

        sc.close();
    }
}
