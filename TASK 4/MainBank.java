import java.util.Scanner;

class BankAccount {
    String depositerName, accountType;
    int acc_no;
    double balance;
    static int nextAccNo = 1; // To generate unique account numbers
    Scanner ob = new Scanner(System.in);

    public void createAccount() {
        System.out.println("Enter Account Holder Name: ");
        depositerName = ob.nextLine();

        System.out.println("Enter Account Type (Saving/Current):");
        accountType = ob.nextLine();

        System.out.println("Enter The Initial Balance:");
        balance = ob.nextDouble();

        acc_no = nextAccNo++;
    }

    public void deposit() {
        System.out.println("Enter The Amount For Deposit:");
        double deposit = ob.nextDouble();
        balance += deposit;
        System.out.println("Amount Deposited.\nCurrent Balance: " + balance);
    }

    public void withdraw() {
        System.out.println("Enter The Amount For Withdrawal:");
        double withdraw = ob.nextDouble();
        if (withdraw <= balance) {
            balance -= withdraw;
            System.out.println("Amount Withdrawn.\nCurrent Balance: " + balance);
        } else {
            System.out.println("Insufficient Balance. Withdrawal Failed.");
        }
    }

    public void display() {
        System.out.println();
        System.out.println("ACCOUNT NO.: " + acc_no);
        System.out.println("ACCOUNT NAME: " + depositerName);
        System.out.println("ACCOUNT TYPE: " + accountType);
        System.out.println("BALANCE: " + balance);
    }

    public void balanceInquiry() {
        System.out.println("Your Current Balance: " + balance);
    }
}

public class MainBank {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankAccount b1 = new BankAccount();
        b1.createAccount();

        int choice;
        do {
            System.out.println("\nBank Account Menu");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Display Account Details");
            System.out.println("4. Balance Inquiry");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    b1.deposit();
                    break;
                case 2:
                    b1.withdraw();
                    break;
                case 3:
                    b1.display();
                    break;
                case 4:
                    b1.balanceInquiry();
                    break;
                case 5:
                    System.out.println("Exiting... Thank you for using our services.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);

        sc.close();
    }
}
