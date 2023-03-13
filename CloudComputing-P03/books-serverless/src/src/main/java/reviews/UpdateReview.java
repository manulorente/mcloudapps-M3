package reviews;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Handler for requests to Lambda function.
 */
public class UpdateReview implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private final ReviewRepository reviewRepository = new ReviewRepository();

    public APIGatewayProxyResponseEvent handleRequest(final APIGatewayProxyRequestEvent input, final Context context) {
                
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("X-Custom-Header", "application/json");

        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent()
                .withHeaders(headers);
        try {
            Long reviewId = input.getPathParameters().get("id") != null ? Long.parseLong(input.getPathParameters().get("id")) : null;
            Review review = reviewRepository.getReviewById(reviewId);
            if (review == null) {
                return response
                        .withBody("{error: Review not found}")
                        .withStatusCode(404);
            }
            Review newReview = new ObjectMapper().readValue(input.getBody(), Review.class);
            reviewRepository.updateReview(review, newReview);
            return response
                    .withBody(new ObjectMapper().writeValueAsString(reviewRepository.getAllReviews()))
                    .withStatusCode(200);
        } catch (JsonProcessingException e) {
            return response
                    .withBody("{error: " + e.getMessage() + "}")
                    .withStatusCode(500);
        }
    }
}
