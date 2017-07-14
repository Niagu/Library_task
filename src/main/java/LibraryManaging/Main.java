package LibraryManaging;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Created by edik on 14.07.2017.
 */
public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/librarymanaging";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) throws SQLException {


        Scanner scanner = new Scanner(System.in);

        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);


        Book.createBookTable(connection);


        Book.insertBook(connection, scanner);


        Book.delete(connection);


        for (Book book : Book.getAllBooks(connection)) {
            System.out.println(book);
        }

        Book.edit(connection);
    }
}
