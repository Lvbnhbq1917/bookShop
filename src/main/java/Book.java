import java.util.List;

public class Book {
    private long bookIndex;
    private String bookName;
    private String authorName;
    private long authorsIndex;
    private List<String> authorsName;
    private String publisherName;
    private long bookPrice;
    private long publisherIndex;

    public Book( long bookIndex, String bookName, List<String> authorsName, String publisherName,  long bookPrice) {
        this.bookIndex = bookIndex;
        this.bookName = bookName;
        this.authorsName = authorsName;
        this.publisherName = publisherName;
        this.bookPrice = bookPrice;
    }

    public Book() {}

    public Book(long bookIndex, String bookName, long publisherIndex, long bookPrice) {
        this.bookIndex = bookIndex;
        this.bookName = bookName;
        this.publisherIndex = publisherIndex;
        this.bookPrice = bookPrice;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public long getAuthorsIndex() {
        return authorsIndex;
    }

    public void setAuthorsIndex(long authorsIndex) {
        this.authorsIndex = authorsIndex;
    }

    public long getPublisherIndex() {
        return publisherIndex;
    }

    public void setPublisherIndex(long publisherIndex) {
        this.publisherIndex = publisherIndex;
    }

    public long getBookIndex() {
        return bookIndex;
    }

    public void setBookIndex(long bookIndex) {
        this.bookIndex = bookIndex;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public List<String> getAuthorsName() {
        return authorsName;
    }

    public void setAuthorsName(List<String> authorsName) {
        this.authorsName = authorsName;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public long getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(long bookPrice) {
        this.bookPrice = bookPrice;
    }
}
