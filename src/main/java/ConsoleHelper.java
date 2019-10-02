import java.io.*;

public class ConsoleHelper {
    public static final String GREETING = "Добро пожаловать в \"Книжный магазин\"!\nПрошу Вас выбрать дальнейшие действия.";
    public static final String GOODBYE = "Окончание программы. Удачного Вам дня!";
    public static final String MAKE_CHOICE_TOP =
            "-----------------------------------------------------------\n" +
            "Для просмотра продукции на складе введите в консоль 1.\n" +
            "Для добавления продукции на склад введите в консоль 2.\n" +
            "Для отгрузки продукции покупателю введите в консоль 3.\n" +
            "Для выхода из программы введите в консоль 0\n" +
            "-----------------------------------------------------------";
    public static final String DELETE_BOOK_1 =
            "Для отгрузки книги покупателю выберите номер книги из открывающегося списка и введите в консоль";
    public static final String DELETE_BOOK_END =
            "Книга отгружена. Спасибо.\n" +
            "Для выхода в главное меню нажмите 1.\n" +
            "Для выхода из программы нажмите 0.";
    public static final String SAVE_BOOK_1 =
            "-----------------------------------------------------------\n" +
            "Вывести данные о всех книгах указаного издателя - введите в консоль 1.\n" +
            "Вывести данные о всех книгах указанного автора - введите в консоль 2. \n" +
            "Установить цену книги указанного издателя - введите в консоль 3.\n" +
            "Для возвращения в главное меню - введите в консоль 4.\n" +
            "-----------------------------------------------------------";
    public static final String SELECT_PRODUCER_BOOKS = "Введите в консоль наименование издателя";
    public static final String SELECT_AUTHORS_BOOKS = "Введите в консоль имя и фамилию автора через пробел";


    public static void writeMassedge(String messadge) {
        System.out.println(messadge);
    }

    public static String readMassedge() throws IOException {
        BufferedReader bufferedReader = null;
        String massedge = null;
       try {
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            while (massedge==null) massedge = bufferedReader.readLine();
            return massedge;
       } catch (Exception e) {
            System.out.println("Ошибка ввода. Попробуйте снова.");
            ConsoleHelper.readMassedge();
       }
       //finally {
       //    bufferedReader.close();
       //}
       return null;
   }
}
