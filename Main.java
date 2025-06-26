

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("---------Product Menu---------");
            System.out.println("1. Viewing");
            System.out.println("2. Inserting");
            System.out.println("3. Updating Price");
            System.out.println("4. Deleting");
            System.out.println("Please enter a choice");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewingBooks();
                    break;
                case 2:
                    insertingBook();
                    break;
                case 3:
                    updatingPrice();
                    break;
                case 4:
                    deletingBook();
                    break;
            }
        }
        while (choice == 1 || choice == 2 || choice == 3 || choice == 4);
    }

           static void viewingBooks(){
               String sql = "SELECT * FROM books";
               try(
                       Connection con = DbUtil.getConnection();
                       Statement stmt = con.createStatement();
                       ResultSet rs = stmt.executeQuery(sql)
                       ) {
                   List<Books> books = new ArrayList<>();
                   while (rs.next()) {
                       Books book = new Books();
                       book.setBook_id(rs.getInt("book_id"));
                       book.setAuthor(rs.getString("author"));
                       book.setTitle(rs.getString("title"));
                       book.setPrice(rs.getDouble("price"));
                       books.add(book);
                   }
                   for (Books b : books) {
                       System.out.println(b);
                   }
               }
                   catch(Exception e){
                       e.printStackTrace();
               }
           }
           static void insertingBook() {
               String inserSql = "INSERT INTO books(title, author, price) VALUES(?,?,?)";
               Scanner scanner = new Scanner(System.in);
               Connection con = null;
               try {
                    con =  DbUtil.getConnection();
                   con.setAutoCommit(false);

                   try (PreparedStatement psmt = con.prepareStatement(inserSql)) {
                       System.out.println("Please enter book details");
                       System.out.println("Title: ");
                       String title = scanner.nextLine();
                       System.out.println("Author: ");
                       String author = scanner.nextLine();
                       System.out.println("Price: ");
                       Double price = scanner.nextDouble();

                       psmt.setString(1, title);
                       psmt.setString(2, author);
                       psmt.setDouble(3, price);

                       int rowsInserted = psmt.executeUpdate();
                       con.commit();
                       System.out.println("Rows inserted: " + rowsInserted);
                   }
                   con.setAutoCommit(true); //set back to true for good practise
               } catch (SQLException e) {
                   e.printStackTrace();
                   if (con != null) {
                       try {
                           con.rollback();
                           System.out.println("The transaction has been rolled back");
                       } catch (SQLException excep) {
                           excep.printStackTrace();
                       }
                   }
               }
           }


    static void updatingPrice(){
        Scanner scanner = new Scanner(System.in);
        String priceUpdate = "UPDATE books SET price = ?  WHERE title = ?";
        Connection con = null;
        try {
            con =  DbUtil.getConnection();
            con.setAutoCommit(false);

            try (PreparedStatement psment = con.prepareStatement(priceUpdate)) {
                System.out.println("Enter title: ");
                String title = scanner.nextLine();
                System.out.println("Enter new price: ");
                double newPrice = scanner.nextDouble();

                System.out.println("Updating price to " + newPrice + " for title: " + title);

                psment.setDouble(1, newPrice);
                psment.setString(2, title);


                int rowsUpdated = psment.executeUpdate();
                con.commit();
                System.out.println("Updated rows: " + rowsUpdated);
            }
            con.setAutoCommit(true);

        }
        catch (SQLException e) {
            e.printStackTrace();
            if(con != null){
                try{
                    con.rollback();
                    System.out.println("The transaction is rolled backed");
                }
                catch (SQLException excep){
                    excep.printStackTrace();
                }

            }
        }
    }
    static void deletingBook(){
        Scanner scanner = new Scanner(System.in);
        String deleteSql = "DELETE FROM books WHERE title = ?";
        Connection con = null;

        try{
                 con =  DbUtil.getConnection();
                con.setAutoCommit(false);

                try(PreparedStatement pstmt = con.prepareStatement(deleteSql)) {
                    System.out.println("Enter title: ");
                    String deletedTitle = scanner.nextLine();
                    System.out.println("Deleting " + deletedTitle);

                    pstmt.setString(1, deletedTitle);
                    int rowsDeleted = pstmt.executeUpdate();
                    System.out.println("Rows deleted: " + rowsDeleted);
                }
                con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            if(con != null){
                try{
                    con.rollback();
                    System.out.println("The transaction has been rolled back");
                }
                catch (SQLException excep){
                    excep.printStackTrace();
                }
            }
        }
    }

}

