package service;

import model.Member;
import model.Book;
import model.BorrowRecord;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private final List<Member> members;
    private final List<Book> books;
    private final List<BorrowRecord> borrowedbooks;

    public Library(){
        this.members=new ArrayList<>();
        this.books= new ArrayList<>();
        this.borrowedbooks= new ArrayList<>();

    }
    public void addBook(Book book){

        books.add(book);

    }
    public void addMember(Member member){
        members.add(member);
    }
    public boolean borrowBook(String memberid,String bookid){
        Member member= findMemberByid(memberid);
        if(member==null){
            System.out.println("Member not found");
            return false;
        }

        Book book=findBookByid(bookid);
        if(book==null){
            System.out.println("Book not found");
            return false;
        }
        if (!book.isAvailable()) {
            System.out.println("Book is already borrowed");
            return false;
        }
        int curentborrowedbook= countBorrowedBookByMember(member);
        int limit=member.getMembership().getBorrowLimit();
        if(curentborrowedbook>=limit){
            System.out.println(" This Member reached the limit of borrowing");
            return false;
        }
        borrowedbooks.add(new BorrowRecord(member, book));
        book.markAsBorrowed();
        System.out.println("Book is borrowed Successfully");
        return true;


    }
    public Member findMemberByid(String memberid){
        for(Member m :members){
            if(m.getId().equals(memberid)){
                return m;
            }
        }
        return null;
    }

    public Book findBookByid(String bookid){
        for(Book b: books){
            if(b.getId().equals(bookid)){
                return b;
            }
        }
        return null;
    }
    public int countBorrowedBookByMember(Member member){
        int count = 0;
        for(BorrowRecord br : borrowedbooks){
            if(br.getMember().getId().equals(member.getId())&& !br.getBook().isAvailable()){
             count++;
            }
        }
        return count;
    }
    public boolean returnBook(String memberid,String bookid){
        Member member= findMemberByid(memberid);
        if(member==null){
            System.out.println("Member not found");
            return false;
        }

        Book book=findBookByid(bookid);
        if(book==null){
            System.out.println("Book not found");
            return false;
        }
        for(BorrowRecord br: borrowedbooks) {
            if (br.getMember().getId().equals(memberid) && br.getBook().getId().equals(bookid) && !br.getBook().isAvailable()) {
                br.getBook().markAsReturned();
                System.out.println("Book retruned Successfully");
                return true;
            }

        }
        System.out.println("No record of the book borrowed by this member");
        return false;
    }
    public void listBook(){
        for(Book b: books){
            System.out.println(b.getId()+ '|' + b.getTitle() + "|" + b.getAuthor() + "|" + (b.isAvailable()? " Available" : "Borrowed" ));
        }
    }
    public void listMembers(){
        for(Member m: members){
            System.out.println(m.getId()+ '|' + m.getName() + "|" + m.getMembership().getTypeName() );
        }
    }
}
