package reviews;

public class Review {

    private Long id;
    private String userName;
    private String text;
    private int rating;
    private Long bookId;

    public Review() {
    }

    public Review(Long id, String userName, String text, int rating, Long bookId) {
        this.id = id;
        this.userName = userName;
        this.text = text;
        this.rating = rating;
        this.bookId = bookId;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
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

    public Long getBookId() {
        return this.bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    @Override
    public String toString() {
        return "Review{" +
            " id='" + getId() + '\'' +
            ", userName='" + getUserName() + '\'' +
            ", text='" + getText() + '\'' +
            ", rating='" + getRating() + '\'' +
            ", bookId='" + getBookId() + '\'' +
            "}";
    }
}
