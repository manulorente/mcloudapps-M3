package books;

public class Review {

    private Long id;
    private String userName;
    private String text;
    private int rating;
    private Book book;

    public Review() {
    }

    public Review(Long id, String userName, String text, int rating, Book book) {
        this.id = id;
        this.userName = userName;
        this.text = text;
        this.rating = rating;
        this.book = book;
    }

    public Long getReviewId() {
        return this.id;
    }

    public void setReviewId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getRating() {
        return this.rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Book getBook() {
        return this.book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "Review{" +
            " id='" + getReviewId() + '\'' +
            ", userName='" + getUserName() + '\'' +
            ", text='" + getText() + '\'' +
            ", rating='" + getRating() + '\'' +
            ", book='" + getBook() + '\'' +
            "}";
    }

}
