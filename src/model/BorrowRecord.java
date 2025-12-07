package model;

public class BorrowRecord {
    private final Member member;
    private final Book book;

    public BorrowRecord(Member member, Book book){
        this.member =member;
        this.book=book;
    }
    public Member getMember(){
        return member;
    }
    public Book getBook(){
        return book;
    }
    

}
