package service;

import model.Member;
import model.Book;
import model.BorrowRecord;

import model.Membership;
import repository.BookRepository;
import repository.MemberRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Library {
    private final BookRepository bookRepository = new BookRepository();
    private final MemberRepository memberRepository = new MemberRepository();



    private final List<BorrowRecord> borrowedBooks;

    public Library(){
        this.borrowedBooks= new ArrayList<>();

    }

    public boolean addBook(String id, String title, String author) {
        if (bookRepository.findBookById(id) != null) {
            return false;
        }
        bookRepository.addBook(new Book(id, title, author));
        return true;

    }

    public boolean addMember(String id , String name, Membership membership){
        if (memberRepository.findMemberById(id) != null) {
            return false;
        }
        memberRepository.addMember(new Member(id, name, membership));
        return true;
    }

    public boolean borrowBook(String memberId,String bookId){
        Member member= memberRepository.findMemberById(memberId);
        if(member==null){
            System.out.println("Member not found");
            return false;
        }

        Book book=bookRepository.findBookById(bookId);
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
        Member member= memberRepository.findMemberById(memberId);
        if(member==null){
            System.out.println("Member not found");
            return false;
        }

        Book book=bookRepository.findBookById(bookId);
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
    public List<Book> listBooks(){
        return bookRepository.findAll();
    }

    public List<Member> listMembers(){
        return memberRepository.findAll();
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
    public List<Book> searchBookByKeyword(String keyword){
        String key = keyword.toLowerCase();
        List<Book> books = new ArrayList<>();
        boolean found = false;
        for(Book book:bookRepository.findAll()){
            if(book.getAuthor().toLowerCase().contains(key) || book.getTitle().toLowerCase().contains(key) ){
                books.add(book);
                found=true;
            }
        }
        if(!found){
            return null;
        }
        return books;
   }
   public boolean deleteBook(String id){
        return bookRepository.removeIfAvailable(id);
    }


}
