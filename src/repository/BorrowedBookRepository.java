package repository;

import model.Book;
import model.BorrowRecord;
import model.Member;

import java.util.ArrayList;
import java.util.List;

public class BorrowedBookRepository {
    private final List<BorrowRecord> borrowedBooks =new ArrayList<>();

    public void add(Member member, Book book){
        borrowedBooks.add(new BorrowRecord(member,book));
    }
    public List<BorrowRecord> findAll(){
        return new ArrayList<>(borrowedBooks);
    }

}
