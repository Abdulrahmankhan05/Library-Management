package repository;

import model.Book;
import model.BorrowRecord;
import model.Member;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BorrowBookRepository {
    private final List<BorrowRecord> borrowedBooks =new ArrayList<>();

    public void add(Member member, Book book){
        borrowedBooks.add(new BorrowRecord(member,book));
    }
    public List<BorrowRecord> findAll(){
        return new ArrayList<>(borrowedBooks);
    }
    public boolean remove(String memberId, String bookId){
        Iterator<BorrowRecord> it = borrowedBooks.iterator();
        while(it.hasNext()) {
            BorrowRecord br = it.next();

            if (br.getMember().getId().equals(memberId) && br.getBook().getId().equals(bookId) && !br.getBook().isAvailable()) {

                it.remove();
                return true;
            }
        }
        return false;
    }
}
