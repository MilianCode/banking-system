package main;

import customer.*;
import transaction.Transaction;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class MainWork {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int menu;
        System.out.println("====Choose option====");
        System.out.println("1.Log in\n2.Create account");
        menu = in.nextInt();
        if (menu == 1){
            if(!CustomerLogin.logIn())return;
        }else if(menu == 2){
            CustomerRegistration.singUp();
            return;
        }else{
            System.out.println("ERROR: Bad choose\nEnd of program");
            return;
        }

        System.out.println("====Choose option====");
        System.out.println("1.Check balance\n2.Deposit\n3.Withdrawal\n4.Transfer\n5.Show all transaction\n6.Exit");
        menu = in.nextInt();
        switch (menu){
            case 1:
                System.out.println("\nYour balance: " + Customer.getBalance() +" $\n");
                System.out.println("End of program");
                return;
            case 2:
                CustomerBalanceManaging.deposit();
                System.out.println("End of program");
                return;
            case 3:
                CustomerBalanceManaging.withdrawal();
                System.out.println("End of program");
                return;
            case 4:
                CustomerTransactionManaging.transfer();
                System.out.println("End of program");
                return;
            case 5:
                System.out.println("Amount | From  | To id |             Date             |   Type\n"
                        + CustomerTransactionManaging.showAllTransactions());
                System.out.println("End of program");
                return;
            case 6:
                System.out.println("End of program");
                return;
        }
        System.out.println("End of program");
    }
}