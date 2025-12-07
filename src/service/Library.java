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
}
