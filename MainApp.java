import java.util.*;
import java.io.*;

public class MainApp {
    static Scanner sc = new Scanner(System.in);
    static Software soft;
    static BST bst = new BST();

    public static void main(String[] args) {
        boolean selection = true;
        FileRead();

        System.out.println();
        System.out.println("(P.S. Customer's Perspective)");
        System.out.println("");
        System.out.println("Welcome to the Software Store!");
        while (selection) {
            System.out.println("--------------------------------------");
            System.out.println("| A: Show Available Softwares        |");
            System.out.println("| D: Add Software                    |");
            System.out.println("| B: Buy Software                    |");
            System.out.println("| S: Sell Software                   |");
            System.out.println("| E: Exit                            |");
            System.out.println("--------------------------------------");
            System.out.print("Enter your choice: ");
            String choice = sc.nextLine();
            System.out.println();

            switch(choice.toUpperCase()) {
                case "A" : ShowSoftware();
                    break;
                case "D" : AddSoftware();
                    break;
                case "B" : BuySoftware();
                    break;
                case "S" : SellSoftware();
                    break;
                case "E" : selection = false;
                           System.out.println("Thank you for shopping at Software Store!");
                    break;
            }
        }

        try {
            PrintWriter pw = new PrintWriter(new File("/Users/adolfocedric/Desktop/1.9 Exercise/src/software.txt"));
            bst.inorderPrint(pw);
            pw.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while trying to save to file.");
            e.printStackTrace();
        }
    }

    public static void ShowSoftware() {
        System.out.printf("%-30s %-15s %-15s %-10s\n", "Software Name", "Version", "Quantity", "Price");
        System.out.println("----------------------------------------------------------------------------");
        bst.inorder();
        System.out.println();
    }

    public static void AddSoftware(){
        System.out.print("Enter the name of the software you want to add: ");
        String name = sc.nextLine();
        System.out.print("Enter the version of the software: ");
        String version = sc.nextLine();
        System.out.print("Enter the quantity of the software: ");
        int quantity = Integer.parseInt(sc.nextLine());
        System.out.print("Enter the price of the software: ");
        int price = Integer.parseInt(sc.nextLine());

        Software newSoftware = new Software(name, version, quantity, price);
        bst.insert(newSoftware);
    }

    public static void BuySoftware() {
        System.out.print("Enter the name of the software you want to buy: ");
        String name = sc.nextLine();
        System.out.print("Enter the version of the software you want to buy: ");
        String version = sc.nextLine();
        
        int quantity = 0;
        if (bst.bolsearch(name, version)) {
            System.out.print("Enter the quantity of the software you want to buy: ");
            quantity = Integer.parseInt(sc.nextLine());
            System.out.println();
    
            System.out.printf("%-30s %-10s %-10s\n", "Software Name", "Version", "Quantity");
            bst.selected(name, version, quantity);
            System.out.println("Confirm purchase? (Y/N)");
            String confirm = sc.nextLine();
        
            if (confirm.equalsIgnoreCase("Y")) {
                bst.buySoftware(name, version, quantity);
    
                if (bst.getQuantity(name, version) == 0) {
                    bst.delete(name, version);
                }
            }
            else if (confirm.equalsIgnoreCase("N")) {
                System.out.println("Purchase cancelled!");
            }
            else {
                System.out.println("Invalid input!");
            }
        }
        else {
            System.out.println("Software not found!");
        }
    }

    public static void SellSoftware() {
        System.out.print("Enter the name of the sofware you want to sell: ");
        String name = sc.nextLine();
        System.out.print("Enter the version of the software you want to sell: ");
        String version = sc.nextLine();
        
        int quantity = 0;
        if(bst.bolsearch(name, version)) {
            System.out.print("Software found! How many software do you want to sell: ");
            quantity = Integer.parseInt(sc.nextLine());
        
            System.out.printf("%-30s %-10s %-10s\n", "Software Name", "Version", "Quantity");
            bst.selected(name, version, quantity);
            System.out.println();
            System.out.println("Confirm sale? (Y/N)");
            String confirm = sc.nextLine();
        
            if (confirm.equalsIgnoreCase("Y")) {
                System.out.println("Sale confirmed!");
                bst.sellSoftware(name, version, quantity);
            }
            else if (confirm.equalsIgnoreCase("N")) {
                System.out.println("Sale cancelled!");
            }
            else {
                System.out.println("Invalid input!");
            }
        }
        else {
            System.out.println("Software not found! Enter the software details to add it to the store.");
            System.out.print("Enter the quantity of the software you want to sell: ");
            int quantityToAdd = Integer.parseInt(sc.nextLine());
            System.out.print("Enter the price of the software you want to sell: ");
            int price = Integer.parseInt(sc.nextLine());

            Software newSoftware = new Software(name, version, quantityToAdd, price);
            bst.insert(newSoftware);

            System.out.println("Software added to the store!\n");
        }
    }

    public static void FileRead() {
        try {
            FileReader fr = new FileReader("/Users/adolfocedric/Desktop/1.9 Exercise/src/software.txt");
            Scanner in = new Scanner(fr);

            while (in.hasNext()) {
                String name = in.nextLine();
                String version = in.nextLine();
                int quantity = Integer.parseInt(in.nextLine());
                int price = Integer.parseInt(in.nextLine());

                Software software = new Software(name, version, quantity, price);
                bst.insert(software);
            }
            in.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}

// Nov. 15, 2023