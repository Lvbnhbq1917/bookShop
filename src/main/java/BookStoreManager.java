import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class BookStoreManager {
    private Connection con = null;
    private String result = null;
    private String sql = null;
    private List<Book> list = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private long index = 0L;

    public BookStoreManager() throws SQLException, ClassNotFoundException {
        con = ConnectionToBD.getConnectionToBD();
    }

    public void runProgram() throws SQLException, IOException {
        String result = ConsoleHelper.readMassedge();
        switch (result) {
            case ("1"):
                selectBook();
                break;
            case ("2"):
                saveBook();
                ConsoleHelper.writeMassedge(ConsoleHelper.MAKE_CHOICE_TOP);
                break;
            case ("3"):
                deleteBook();
                break;
            case ("0"):
                ConsoleHelper.writeMassedge(ConsoleHelper.GOODBYE);
                con.close();
                System.exit(1);
            default://Todo refactoring later
                ConsoleHelper.writeMassedge("Ошибка ввода.");
                ConsoleHelper.writeMassedge(ConsoleHelper.MAKE_CHOICE_TOP);
                break;
        }
    }

    /*Выполнение поставленных запросов в задании находится ниже.
    * подменю выбора*/

    private void selectBook() throws SQLException, IOException {
        result = null;
        ConsoleHelper.writeMassedge(ConsoleHelper.SAVE_BOOK_1);

        while (result == null) {
            result = ConsoleHelper.readMassedge();
        }
        switch (result) {
            case ("1"):
                selectProducerBooks();
                break;
            case ("2"):
                selectAuthorBooks();
                break;
            case ("3"):
                changePriceProducerBooks();
            case ("4"):
                break;
            default: //Todo refactoring later
                    ConsoleHelper.writeMassedge("Ошибка ввода.");
                    ConsoleHelper.writeMassedge(ConsoleHelper.MAKE_CHOICE_TOP);
                    runProgram();
        }

        ConsoleHelper.writeMassedge(ConsoleHelper.MAKE_CHOICE_TOP);
        runProgram();
    }

    /*Задание - выбрать все книги определенного автора*/

    private void selectAuthorBooks() throws SQLException, IOException{
        ConsoleHelper.writeMassedge(ConsoleHelper.SELECT_AUTHORS_BOOKS);
        result = ConsoleHelper.readMassedge().trim();
        sql = "SELECT * FROM sb_authors";
        index = 0L;
        //Todo refactoring later
        stmt = con.createStatement();
        rs = stmt.executeQuery(sql);
        while(rs.next()) {
            if (result.compareToIgnoreCase(rs.getString("author_name")) == 0) {
                index = rs.getLong("author_id");
                System.out.println(index);
                break;
            }
        }
        stmt.close();
        if (index==0) {
            System.out.println("В базе данных нет книг, написанных данным автором.");
            return;
        }
        sql = "SELECT DISTINCT sb_goods.goods_name " +
                "FROM sb_goods, sb_authorbooks " +
                "WHERE sb_authorbooks.authorsname_id = " + index +
                "AND sb_goods.goods_id = sb_authorbooks.booksname_id";
        //Todo refactoring later
        stmt = con.createStatement();
        rs = stmt.executeQuery(sql);
        System.out.println("На складе имются следующие книги указанного автора:\n");
        while(rs.next()) {
            System.out.println(rs.getString("goods_name"));
        }
        //Todo refactoring later
        ConsoleHelper.writeMassedge(ConsoleHelper.MAKE_CHOICE_TOP);
        runProgram();
    }

    /*Задание - выбрать все книги определенного издателя*/

    private void changePriceProducerBooks() throws SQLException, IOException {
        ConsoleHelper.writeMassedge(ConsoleHelper.SELECT_PRODUCER_BOOKS);
        result = ConsoleHelper.readMassedge().trim();
        stmt = con.createStatement();
        sql = "SELECT * FROM sb_produsers";
        index = 0L;
        rs = stmt.executeQuery(sql);
        while(rs.next()) {
            if (result.compareToIgnoreCase(rs.getString("produser_name")) == 0) {
                index = rs.getLong("produser_id");
                break;
            }
        }
        stmt.close();

        if (index==0) {
            System.out.println("В базе данных нет издательства с таким именем.");
            return;
        }

        sql = "SELECT DISTINCT sb_goods.goods_id AS bookIndex, sb_goods.goods_name AS bookName, sb_goods.goods_price AS bookPrice " +
                "FROM sb_goods " +
                "WHERE sb_goods.goods_produser_id = " + index;
        list = new LinkedList<Book>();
        //Todo refactoring later
        stmt = con.createStatement();
        rs = stmt.executeQuery(sql);
        while(rs.next()) list.add(new Book(rs.getLong("bookIndex"),
                                            rs.getString("bookName"),
                                            index,
                                            rs.getLong("bookPrice")));
        stmt.close();

        ListIterator<Book> iterator = list.listIterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            System.out.println("На складе имеются товар издателя " + result +
                    " с названием: " + book.getBookName() +
                    "\nи ценой: " + book.getBookPrice()/100 + "\nУкажите новую цену для данного товара");
            try {
                int newPrice = Integer.parseInt(ConsoleHelper.readMassedge()) * 100;
                sql = "UPDATE sb_goods SET goods_price = '" + (int)newPrice +
                      "' WHERE goods_id = '" + book.getBookIndex() + "'";
                stmt = con.createStatement();
                rs = stmt.executeQuery(sql);
                System.out.println("Цена была изменена.");
                stmt.close();
            }
            catch(Exception e){};
        }

        //Todo refactoring later
        ConsoleHelper.writeMassedge(ConsoleHelper.MAKE_CHOICE_TOP);
        runProgram();
    }

    /*Задание - выбрать все книги определенного издателя*/

    private void selectProducerBooks() throws SQLException, IOException {
        ConsoleHelper.writeMassedge(ConsoleHelper.SELECT_PRODUCER_BOOKS);

        //Todo refactoring later
        result = ConsoleHelper.readMassedge().trim();
        sql = "SELECT * FROM sb_produsers";
        index = 0L;

        stmt = con.createStatement();
        rs = stmt.executeQuery(sql);
        while(rs.next()) {
            if (result.compareToIgnoreCase(rs.getString("produser_name")) == 0) {
                index = rs.getLong("produser_id");
                System.out.println(index);
                break;
            }
        }
        stmt.close();
        if (index==0) {
            System.out.println("В базе данных нет издательства с таким именем.");
            return;
        }
        sql = "SELECT DISTINCT sb_goods.goods_name FROM sb_goods WHERE sb_goods.goods_produser_id =" + index;
        //Todo refactoring later
        stmt = con.createStatement();
        rs = stmt.executeQuery(sql);
        while(rs.next()) {
            System.out.println(rs.getString("goods_name"));
        }
        //Todo refactoring later
        ConsoleHelper.writeMassedge(ConsoleHelper.MAKE_CHOICE_TOP);
        runProgram();
    }


    /* Дополнительный функционал к программе*/

    private void saveBook() throws IOException, SQLException {
        /*private long bookIndex;
        private String bookName;
        private List<String> authorsName;
        private String publisherName;
        private long bookPrice;
        private long publisherIndex;*/
        Book newBook = new Book();

        ConsoleHelper.writeMassedge("Введите в консоль название книги");
        newBook.setBookName(ConsoleHelper.readMassedge());

        ConsoleHelper.writeMassedge("Введите в консоль имя и фамилию автора книги через пробел");
        //Todo refactoring later
        result = ConsoleHelper.readMassedge().trim();
        sql = "SELECT * FROM sb_authors";
        index = 0L;
        int authorsIndex = 0;
        stmt = con.createStatement();
        rs = stmt.executeQuery(sql);
        while (rs.next()) {
            authorsIndex++;
            if (result.compareToIgnoreCase(rs.getString("author_name")) == 0) {
                index = rs.getLong("author_id");
            }
        }

        if (index == 0L) {
            rs = stmt.executeQuery("INSERT INTO sb_authors (author_id, author_name) VALUES(" + ++authorsIndex + ", '" + result + "') RETURNING author_id;");
            while(rs.next()) authorsIndex = (int)rs.getLong("author_id");
        }

        ConsoleHelper.writeMassedge("Введите в консоль стоимость книги");
        newBook.setBookPrice(Integer.parseInt(ConsoleHelper.readMassedge()));

        ConsoleHelper.writeMassedge("Введите в консоль наименование издателя книги");
        //Todo refactoring later
        result = ConsoleHelper.readMassedge().trim();
        sql = "SELECT * FROM sb_produsers";
        index = 0L;
        int count = 0;
        rs = stmt.executeQuery(sql);
        while (rs.next()) {
            count++;
            if (result.compareToIgnoreCase(rs.getString("produser_name")) == 0) {
                index = rs.getLong("produser_id");
            }
        }

        if (index == 0) {
            ++count;
            rs = stmt.executeQuery("INSERT INTO sb_produsers (produser_id, produser_name) VALUES (" + count + ", '" + result + "');");
            newBook.setPublisherIndex(count);
        } else {
            newBook.setPublisherIndex((int) index);
        }

        try {
            //INSERT INTO sb_goods (goods_name, goods_price, goods_produser_id) VALUES ('радуга', 13000, 13) returning goods_id;
            rs = stmt.executeQuery("INSERT INTO sb_goods (goods_name, goods_price, goods_produser_id) VALUES \n" +
                    "(" + newBook.getBookName() +
                    ", " + newBook.getBookPrice() +
                    ", " + newBook.getPublisherIndex() + ") returning goods_id;");
            while (rs.next()) index = rs.getLong("goods_id");

            rs = stmt.executeQuery("INSERT INTO sb_authorbooks (authorsname_id, booksname_id) VALUES (" + authorsIndex + ", " + index + ");");
        } catch(Exception e) {}
        stmt.close();
        ConsoleHelper.writeMassedge(ConsoleHelper.MAKE_CHOICE_TOP);
        return;
    }


    /* Дополнительный функционал к программе. Предполагается,
    что все книги и проданные и на складе будут храниться под индексом продано/нет.
    В дальнейшем можно использовать для отчетов по продажам за выбранный период или что-то типо того...*/

    private void deleteBook() throws SQLException, IOException {
        System.out.println("Выберите индекс товара из списка");
        stmt = con.createStatement();
        rs = stmt.executeQuery("SELECT sb_goods.goods_id, sb_goods.goods_name FROM sb_goods WHERE sb_goods.goods_status = 0;");
        while (rs.next()) System.out.println("Индекс товара: " + rs.getLong("goods_id") +
                                            "    Нименование товара: " + rs.getString("goods_name"));
        System.out.println("__________________________________________________\nВведите выбранный индекс в консоль:");
        index = Integer.parseInt(ConsoleHelper.readMassedge());
        try {
            rs = stmt.executeQuery("UPDATE sb_goods SET goods_status = 1 WHERE goods_id = " + index + ";");
        } catch (SQLException e) {}
        /*
        если нужно удалить запись, то:
            rs = stmt.executeQuery("DELETE sb_goods WHERE goods_id = " + index + ";");
        не забудь, что здесь необходимо удалять сразу строки и в других зависимых таблицах
        */
        stmt.close();
        ConsoleHelper.writeMassedge(ConsoleHelper.MAKE_CHOICE_TOP);
    }
}