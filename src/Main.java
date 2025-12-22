import model.*;
import service.Library;

import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Library library=new Library();
        Scanner scanner=new Scanner(System.in);

        boolean running = true;

        while(running){
            System.out.println("----------------------");
            System.out.println("Library System");
            System.out.println("----------------------");
            System.out.println("1. Add Book");
            System.out.println("2. Register Member");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. List Books");
            System.out.println("6. List Members");
            System.out.println("7. Exit");
            System.out.println("8. List Borrowed Records");
            System.out.println("9. Search Book");
            System.out.println("10. Delete Book");
            System.out.println("------------------------");
            System.out.println("Choice:");


            int choice = Integer.parseInt(scanner.nextLine());
            System.out.println("------------------------");

            switch (choice) {
                case 1: {
                    System.out.print("Book ID: ");
                    String id = scanner.nextLine().trim();
                    System.out.print("Title: ");
                    String title = scanner.nextLine().trim();
                    System.out.print("Author: ");
                    String author = scanner.nextLine().trim();
                    boolean status = library.addBook(id, title, author);
                    if (status) {
                        System.out.println("Book added successfully");
                    } else {
                        System.out.println("Cannot add this book. A book with same id may exist in the library ");
                    }
                    break;
                }

                case 2: {
                    System.out.print("Member ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Membership type (BASIC/PREMIUM): ");
                    String type = scanner.nextLine().trim().toUpperCase();
                    Membership membership = type.equals("PREMIUM") ? new PremiumMembership() : new BasicMembership();
                    boolean status= library.addMember(id, name, membership);
                    if (status) {
                        System.out.println("Member registered successfully");
                    } else {
                        System.out.println("Cannot register this member. This member is already registered or use different ID. ");
                    }

                    break;

                }
                case 3: {
                    System.out.print("Member ID: ");
                    String memberId = scanner.nextLine();
                    System.out.print("Book ID: ");
                    String bookId = scanner.nextLine();
                    library.borrowBook(memberId, bookId);
                    break;

                }
                case 4: {
                    System.out.print("Member ID: ");
                    String memberId = scanner.nextLine();
                    System.out.print("Book ID: ");
                    String bookId = scanner.nextLine();
                    library.returnBook(memberId, bookId);
                    break;

                }
                case 5: {
                    List<Book> books = library.listBooks();
                    if (books.isEmpty()) {
                        System.out.println("No book in the record");
                        break;
                    }
                    for (Book b : books) {
                        System.out.println(b.getId() + '|' + b.getTitle() + '|' + b.getAuthor() + '|' + (b.isAvailable() ? "Available" : "Borrowed"));
                    }

                break;

                }
                case 6: {
                    List<Member> members =library.listMembers();
                    for(Member member:members){
                        System.out.println(member.getId()+ '|' + member.getName() + '|' + member.getMembership().getTypeName() );

                    }
                    break;
                }
                case 7 : {
                    running = false;
                    break;
                }
                case 8: {
                    library.listBorrowedRecord();
                    break;
                }
                case 9: {
                    System.out.println("Enter a keyword related to the book(Title/Author):");
                    String key =scanner.nextLine();
                    List<Book> books= library.searchBookByKeyword(key);
                    if(books==null){
                        System.out.println("No book found with this key word");
                        break ;
                    }
                for(Book book: books) {

                    System.out.println("Title: " + book.getTitle());
                    System.out.println("Author: " + book.getAuthor());
                    System.out.println("Book Id: " + book.getId());
                    System.out.println("Status :" + (book.isAvailable() ? " Available" : "Borrowed"));
                    System.out.println("------------------------");
                }

                    break;

                }
                case 10:{
                    System.out.println("Enter the book Id to delete:" );
                    String bookId = scanner.nextLine().trim();
                    boolean status =library.deleteBook(bookId);
                    if(status){
                        System.out.println("Book is deleted successfully.");
                    }
                    else{
                        System.out.println("Cannot delete book. It may not exist or is currently borrowed.");
                    }
                    break;

                }
                default: System.out.println("Invalid choice");
            }
        }
        scanner.close();
        }
}