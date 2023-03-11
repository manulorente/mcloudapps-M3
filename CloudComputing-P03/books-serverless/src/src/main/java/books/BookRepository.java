package books;

import java.util.ArrayList;
import java.util.List;

public class BookRepository {

    private final List<Book> books = new ArrayList<>();

    public BookRepository() {
        this.books.add(new Book("1", "The Lord of the Rings", "The Lord of the Rings is an epic high fantasy novel written by English author and scholar J. R. R. Tolkien. The story began as a sequel to Tolkien's 1937 fantasy novel The Hobbit, but eventually developed into a much larger work. Written in stages between 1937 and 1949, The Lord of the Rings is one of the best-selling novels ever written, with over 150 million copies sold.", "J. R. R. Tolkien", "Allen & Unwin", "1954-07-29"));
        this.books.add(new Book("2", "The Hobbit", "The Hobbit, or There and Back Again is a children's fantasy novel by English author J. R. R. Tolkien. It was published on 21 September 1937 to wide critical acclaim, being nominated for the Carnegie Medal and awarded a prize from the New York Herald Tribune for best juvenile fiction. The book remains popular and is recognized as a classic in children's literature.", "J. R. R. Tolkien", "Allen & Unwin", "1937-09-21"));
        this.books.add(new Book("3", "The Hitchhiker's Guide to the Galaxy", "The Hitchhiker's Guide to the Galaxy is a comedy science fiction series created by Douglas Adams. Originally a radio comedy broadcast on BBC Radio 4 in 1978, it was later adapted to other formats, including stage shows, novels, comic books, a 1981 TV series, a 1984 video game, and 2005 feature film.", "Douglas Adams", "Pan Books", "1979-10-12"));
        this.books.add(new Book("4", "The Great Gatsby", "The Great Gatsby is a 1925 novel written by American author F. Scott Fitzgerald that follows a cast of characters living in the fictional town of West Egg on prosperous Long Island in the summer of 1922. The story primarily concerns the young and mysterious millionaire Jay Gatsby and his quixotic passion and obsession with the beautiful former debutante Daisy Buchanan.", "F. Scott Fitzgerald", "Charles Scribner's Sons", "1925-04-10"));
        this.books.add(new Book("5", "The Catcher in the Rye", "The Catcher in the Rye is a 1951 novel by J. D. Salinger. A classic novel originally published for adults, it has since become popular with adolescent readers for its themes of teenage angst and alienation. It has been translated into almost all of the world's major languages. Around 1 million copies are sold each year with total sales of more than 65 million books.", "J. D. Salinger", "Little, Brown and Company", "1951-07-16"));
    }

    public List<Book> getAllBooks() {
        return this.books;
    }
}
