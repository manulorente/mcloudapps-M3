package books;

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
public class UpdateBook implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private final BookRepository bookRepository = new BookRepository();

    public APIGatewayProxyResponseEvent handleRequest(final APIGatewayProxyRequestEvent input, final Context context) {
                
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("X-Custom-Header", "application/json");

        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent()
                .withHeaders(headers);
        try {
            Long bookId = input.getPathParameters().get("id") != null ? Long.parseLong(input.getPathParameters().get("id")) : null;
            Book book = bookRepository.getBookById(bookId);
            if (book == null) {
                return response
                        .withBody("{error: Book not found}")
                        .withStatusCode(404);
            }
            Book newBook = new ObjectMapper().readValue(input.getBody(), Book.class);
            bookRepository.updateBook(book, newBook);
            return response
                    .withBody(new ObjectMapper().writeValueAsString(bookRepository.getAllBooks()))
                    .withStatusCode(200);
        } catch (JsonProcessingException e) {
            return response
                    .withBody("{error: " + e.getMessage() + "}")
                    .withStatusCode(500);
        }
    }
}
