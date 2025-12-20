package repository;

import model.Book;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BookRepository {
    private final List<Book> books= new ArrayList<>();


    public void addBook(Book book){

        books.add(book);

    }

    public List<Book> findAll(){
        return new ArrayList<>(books);

    }


    public Book findBookById(String bookId){
        for(Book b: books){
            if(b.getId().equals(bookId)){
                return b;
            }
        }
        return null;
    }

    public boolean removeIfAvailable(String bookId){
        Iterator <Book>it =books.iterator();
        while(it.hasNext()){

            Book book=it.next();
            if(book.getId().equals(bookId)){
                if(!book.isAvailable()){

                    return false;
                }
                it.remove();
                return true;
            }

        }
        return false;
    }
}
