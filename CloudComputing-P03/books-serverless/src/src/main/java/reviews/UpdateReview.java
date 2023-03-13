package reviews;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import books.Book;
import books.BookRepository;

/**
 * Handler for requests to Lambda function.
 */
public class UpdateReview implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private final ReviewRepository reviewRepository = new ReviewRepository();
    private final BookRepository bookRepository = new BookRepository();

    public APIGatewayProxyResponseEvent handleRequest(final APIGatewayProxyRequestEvent input, final Context context) {
                
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("X-Custom-Header", "application/json");

        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent()
                .withHeaders(headers);
        try {
            String reviewId = input.getPathParameters().get("id");
            if (reviewRepository.updateReview(reviewId, new ObjectMapper().readValue(input.getBody(), Review.class))) {
                Review review = reviewRepository.getReviewById(reviewId);
                Book book = bookRepository.getBookById(review.getBookId());
                book.addReview(review);
                this.bookRepository.updateBook(review.getBookId(),book);
                return response
                        .withBody("{message: Review updated successfully}")
                        .withStatusCode(200);
            }
            return response
                    .withBody("{error: Review not found}")
                    .withStatusCode(200);
        } catch (JsonProcessingException e) {
            return response
                    .withBody("{error: " + e.getMessage() + "}")
                    .withStatusCode(500);
        }
    }
}
