package service;

import model.Member;
import model.Book;
import model.BorrowRecord;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Library {
    private final List<Member> members;
    private final List<Book> books;
    private final List<BorrowRecord> borrowedBooks;

    public Library(){
        this.members=new ArrayList<>();
        this.books= new ArrayList<>();
        this.borrowedBooks= new ArrayList<>();

    }
    public void addBook(Book book){

        books.add(book);

    }
    public void addMember(Member member){
        members.add(member);
    }
    public boolean borrowBook(String memberId,String bookId){
        Member member= findMemberById(memberId);
        if(member==null){
            System.out.println("Member not found");
            return false;
        }

        Book book=findBookById(bookId);
        if(book==null){
            System.out.println("Book not found");
            return false;
        }
        if (!book.isAvailable()) {
            System.out.println("Book is already borrowed");
            return false;
        }
        int currentBorrowedBook= countBorrowedBookByMember(member);
        int limit=member.getMembership().getBorrowLimit();
        if(currentBorrowedBook>=limit){
            System.out.println(" This Member reached the limit of borrowing");
            return false;
        }
        borrowedBooks.add(new BorrowRecord(member, book));
        book.markAsBorrowed();
        System.out.println("Book is borrowed Successfully");
        return true;


    }
    private Member findMemberById(String memberId){
        for(Member m :members){
            if(m.getId().equals(memberId)){
                return m;
            }
        }
        return null;
    }

    private Book findBookById(String bookId){
        for(Book b: books){
            if(b.getId().equals(bookId)){
                return b;
            }
        }
        return null;
    }
    private int countBorrowedBookByMember(Member member){
        int count = 0;
        for(BorrowRecord br : borrowedBooks){
            if(br.getMember().getId().equals(member.getId())&& !br.getBook().isAvailable()){
             count++;
            }
        }
        return count;
    }
    public boolean returnBook(String memberId,String bookId){
        Member member= findMemberById(memberId);
        if(member==null){
            System.out.println("Member not found");
            return false;
        }

        Book book=findBookById(bookId);
        if(book==null){
            System.out.println("Book not found");
            return false;
        }
        Iterator<BorrowRecord> it = borrowedBooks.iterator();
        while(it.hasNext()) {
            BorrowRecord br =it.next();

            if (br.getMember().getId().equals(memberId) && br.getBook().getId().equals(bookId) && !br.getBook().isAvailable()) {
                br.getBook().markAsReturned();
                it.remove();
                System.out.println("Book returned Successfully");
                return true;
            }

        }
        System.out.println("No record of the book borrowed by this member");
        return false;
    }
    public void listBooks(){
        for(Book b: books){
            System.out.println(b.getId()+ '|' + b.getTitle() + '|' + b.getAuthor() + '|' + (b.isAvailable()? "Available" : "Borrowed" ));
        }
    }
    public void listMembers(){
        for(Member m: members){
            System.out.println(m.getId()+ '|' + m.getName() + '|' + m.getMembership().getTypeName() );
        }
    }
    public void listBorrowedRecord(){
        if(borrowedBooks.isEmpty()){
            System.out.println("No borrow records");
        }
        for(BorrowRecord br: borrowedBooks){
            if(!br.getBook().isAvailable()){
                System.out.println(br.getBook().getTitle() + "book is borrowed by " + br.getMember().getName() +"(" + br.getMember().getId() + ")");
            }
        }

    }
    public void searchBookByKeyword(String keyword){
        String key = keyword.toLowerCase();
        boolean found = false;
        for(Book book:books){
            if(book.getAuthor().toLowerCase().contains(key) || book.getTitle().toLowerCase().contains(key) ){

                System.out.println("Title: "+book.getTitle());
                System.out.println("Author: "+book.getAuthor());
                System.out.println("Book Id: "+book.getId());
                System.out.println("Status :"+(book.isAvailable() ? " Available": "Borrowed"));
                System.out.println("------------------------");


                 found=true;
            }
        }
        if(!found){
            System.out.println("The book is not found");
        }
   }
}
