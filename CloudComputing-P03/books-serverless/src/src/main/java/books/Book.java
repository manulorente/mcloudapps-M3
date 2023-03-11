package books;

import java.util.ArrayList;
import java.util.List;

public class Book {

    private String bookId;
    private String title;
    private String summary;
    private String author;
    private String publisher;
    private String date;

    private List<Review> reviews;

    public Book() {
    }

    public Book(String bookId, String title, String summary, String author, String publisher, String date) {
        this.bookId = bookId;
        this.title = title;
        this.summary = summary;
        this.author = author;
        this.publisher = publisher;
        this.date = date;
        this.reviews = new ArrayList<>();
    }

    public String getBookId() {
        return this.bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return this.summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return this.publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Review> getReviews() {
        return this.reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }    
}
