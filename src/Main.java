import model.*;
import service.Library;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Library library=new Library();
        Scanner scanner=new Scanner(System.in);

        boolean running = true;

        while(running){
            System.out.println("Library System");
            System.out.println("1. Add Book");
            System.out.println("2. Register Member");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. List Books");
            System.out.println("6. List Members");
            System.out.println("7. Exit");
            System.out.println("Choice:");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice){
                case 1:{
                    System.out.print("Book ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Author: ");
                    String author = scanner.nextLine();
                    library.addBook(new Book(id, title, author));
                    break;
                }

                case 2:{
                    System.out.print("Member ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Membership type (BASIC/PREMIUM): ");
                    String type = scanner.nextLine().trim().toUpperCase();
                    Membership membership= type.equals("PREMIUM")? new PremiumMembership(): new BasicMembership();
                    library.addMember(new Member(id, name, membership));
                    break;

                }
                case 3:{
                    System.out.print("Member ID: ");
                    String memberId = scanner.nextLine();
                    System.out.print("Book ID: ");
                    String bookId = scanner.nextLine();
                    library.borrowBook(memberId, bookId);
                    break;

                }
                case 4:{
                    System.out.print("Member ID: ");
                    String memberId = scanner.nextLine();
                    System.out.print("Book ID: ");
                    String bookId = scanner.nextLine();
                    library.returnBook(memberId, bookId);
                    break;

                }
                case 5: {
                    library.listBooks();
                    break;
                }
                case 6: {
                    library.listMembers();
                    break;
                }
                case 7 : {
                    running = false;
                    break;
                }
                default: System.out.println("Invalid choice");
            }
        }
        scanner.close();
        }
}