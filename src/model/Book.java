package model;

public class Book {
    private final String id;
    private final String title;
    private final String author;
    private Boolean availability;

    public Book(String id, String title, String author){
        this.id=id;
        this.title= title;
        this.author=author;
        this.availability=true;
    }
    public String getId(){
        return id;
    }
    public String getTitle(){
        return title;
    }
    public String getAuthor(){
        return author;
    }
    public void markAsBorrowed(){
        availability=false;
    }

    public void markAsReturned(){
        availability=true;
    }
    public Boolean isAvailable(){
        return availability;
    }
}
