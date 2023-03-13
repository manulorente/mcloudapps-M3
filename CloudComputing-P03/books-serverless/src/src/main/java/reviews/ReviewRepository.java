package reviews;

import java.util.ArrayList;
import java.util.List;

public class ReviewRepository {
    
    private final List<Review> reviews = new ArrayList<>();

    public ReviewRepository(){
        this.reviews.add(new Review(1L, "John", "This is a great book", 5, 1L));
        this.reviews.add(new Review(2L, "Mary", "This book is disgusting", 1, 1L));
    }

    public Long getNextId() {
        return this.reviews.stream().mapToLong(Review::getId).max().orElse(0) + 1;
    }

    public List<Review> getAllReviews() {
        return this.reviews;
    }

    public Review getReviewById(Long id) {
        for (Review review : this.reviews) {
            if (review.getId().equals(id)) {
                return review;
            }
        }
        return null;
    }

    public Review createReview(Review review) {
        this.reviews.add(review);
        return review;
    }

    public boolean deleteReview(Long id) {
        if (this.reviews.removeIf(review -> review.getId().equals(id))) {
            return true;
        }
        return false;
    }

    public Review updateReview(Review review, Review updatedReview) {
        review.setUserName(updatedReview.getUserName());
        review.setText(updatedReview.getText());
        review.setRating(updatedReview.getRating());
        review.setBookId(updatedReview.getBookId());
        return review;
    }

}
