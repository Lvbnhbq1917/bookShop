import java.io.IOException;
import java.sql.SQLException;

public class BookStore {
    public static BookStoreManager bookStoreManager;

    public BookStore() throws SQLException, ClassNotFoundException {
        bookStoreManager = new BookStoreManager();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        ConsoleHelper.writeMassedge(ConsoleHelper.GREETING);
        ConsoleHelper.writeMassedge(ConsoleHelper.MAKE_CHOICE_TOP);
        new BookStore();
        while(true) bookStoreManager.runProgram();
    }
}
