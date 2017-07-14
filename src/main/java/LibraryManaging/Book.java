package LibraryManaging;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by edik on 14.07.2017.
 */
public class Book {
    private int id;
    private String author;
    private String name;
    private int pages;


    public Book(int id, String name, String author, int pages) {
        this.id = id;
        this.author = author;
        this.name = name;
        this.pages = pages;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", name='" + name + '\'' +
                ", pages=" + pages +
                '}';
    }

    public static void createBookTable(Connection connection) throws SQLException {
        connection.prepareStatement("CREATE TABLE IF NOT EXISTS Book(id INT PRIMARY KEY AUTO_INCREMENT, author VARCHAR(20), name VARCHAR(20), pages INT)").execute();
    }
    public static void insertBook(Connection connection, Scanner scanner) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Book( author,name, pages) VALUES (?,?,?)");

        System.out.println("Input Author of a Book:");
        preparedStatement.setString(1, scanner.next());
        System.out.println("Input name of a Book:");
        preparedStatement.setString(2,scanner.next());
        System.out.println("Input page quantity of a Book:");
        preparedStatement.setInt(3, Integer.parseInt(scanner.next()));
        preparedStatement.execute();
    }

    public static void delete(Connection connection) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input name of a book to delete:");
        String name = scanner.nextLine();
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Book WHERE name = ?");
        preparedStatement.setString(1, name);
        preparedStatement.execute();
    }

    public static List<Book> getAllBooks(Connection connection) throws SQLException {
        List<Book> books = new ArrayList<>();
        ResultSet resultSet = connection.prepareStatement("SELECT * FROM Book").executeQuery();
        while (resultSet.next()) {
            books.add(new Book(resultSet.getInt("id"), resultSet.getString("author"), resultSet.getString("name"),resultSet.getInt("pages")));
        }
        return books;
    }




    public static void edit(Connection connection) throws SQLException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Input the old name of the book:");
        String oldBookName = scanner.nextLine();

        System.out.println("Input the new name of the book:");
        String newBookName = scanner.nextLine();

        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Book SET Book.name = ? WHERE name = ?");
        preparedStatement.setString(1, newBookName);
        preparedStatement.setString(2, oldBookName);

        System.out.println(preparedStatement);
        preparedStatement.execute();


    }

}
