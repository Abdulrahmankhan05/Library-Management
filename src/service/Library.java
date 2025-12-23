package service;

import model.Member;
import model.Book;
import model.BorrowRecord;

import model.Membership;
import repository.BookRepository;
import repository.BorrowedBookRepository;
import repository.MemberRepository;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Library {

    private final BookRepository bookRepository = new BookRepository();
    private final MemberRepository memberRepository = new MemberRepository();
    private final BorrowedBookRepository borrowedBookRepository=new BorrowedBookRepository();


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

    public BorrowResult borrowBook(String memberId,String bookId){
        Member member= memberRepository.findMemberById(memberId);
        if(member==null){
            return BorrowResult.MEMBER_NOT_FOUND;
        }

        Book book=bookRepository.findBookById(bookId);
        if(book==null){
            return BorrowResult.BOOK_NOT_FOUND;
        }

        if (!book.isAvailable()) {
            return BorrowResult.BOOK_ALREADY_BORROWED;
        }

        int currentBorrowedBook= countBorrowedBookByMember(member);
        int limit=member.getMembership().getBorrowLimit();

        if(currentBorrowedBook>=limit){
            return BorrowResult.BORROW_LIMIT_REACHED;
        }
        borrowedBookRepository.add(member, book);
        book.markAsBorrowed();
        return BorrowResult.SUCCESS;
    }



    private int countBorrowedBookByMember(Member member){
        int count = 0;
        for(BorrowRecord br : borrowedBookRepository.findAll()){
            if(br.getMember().getId().equals(member.getId())&& !br.getBook().isAvailable()){
             count++;
            }
        }
        return count;
    }
    public ReturnResult returnBook(String memberId,String bookId){
        Member member= memberRepository.findMemberById(memberId);
        if(member==null){
            return ReturnResult.MEMBER_NOT_FOUND;
        }

        Book book=bookRepository.findBookById(bookId);
        if(book==null){
            return ReturnResult.BOOK_NOT_FOUND;
        }
        boolean status = borrowedBookRepository.remove(memberId,bookId);

        if (!status){
            return ReturnResult.NO_RECORD_FOUND;
            }

        book.markAsReturned();
        return ReturnResult.SUCCESS;
    }
    public List<Book> listBooks(){
        return bookRepository.findAll();
    }

    public List<Member> listMembers(){
        return memberRepository.findAll();
    }
    public List<BorrowRecord> listBorrowedRecord(){
        return borrowedBookRepository.findAll();

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
1
