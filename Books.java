
public class Books {

    private int book_id;
    private  String title;
    private String author;
    private double price;

    public Books(){}

    public Books(int book_id, String title, String author, double price){
        this.book_id = book_id;
        this.title = title;
        this.author = author;
        this.price = price;
    }
    public String getAuthor(){
        return this.author;
    }

    public double getPrice() {
        return this.price;
    }

    public String getTitle() {
        return this.title;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    @Override
    public String toString(){
        return "Book ID: " + book_id + ", Title: " + title + ", Author: " + author +
                ", Price: " + price;
    }
}
