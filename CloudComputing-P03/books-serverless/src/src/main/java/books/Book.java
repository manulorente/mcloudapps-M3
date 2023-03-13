package books;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import reviews.Review;

public class Book {

    private Long id;
    private String title;
    private String summary;
    private String author;
    private String publisher;
    private String date;

    private List<Review> reviews;

    public Book() {
    }

    public Book(Long id, String title, String summary, String author, String publisher, String date) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.author = author;
        this.publisher = publisher;
        this.date = date;
        this.reviews = new ArrayList<>();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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
    
    @Override
    public String toString() {
        return "Book{" +
            " id='" + getId() + '\'' +
            ", title='" + getTitle() + '\'' +
            ", summary='" + getSummary() + '\'' +
            ", author='" + getAuthor() + '\'' +
            ", publisher='" + getPublisher() + '\'' +
            ", date='" + getDate() + '\'' +
            ", reviews='" + getReviews() + '\'' +
            "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Book)) {
            return false;
        }
        Book book = (Book) o;
        return id.equals(book.id) && title.equals(book.title) && summary.equals(book.summary) && author.equals(book.author) && publisher.equals(book.publisher) && date.equals(book.date) && reviews.equals(book.reviews);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, summary, author, publisher, date, reviews);
    }
}
