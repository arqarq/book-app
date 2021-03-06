package pl.sda.bookApp;

class Book {
    private final String title;
    private final String author;
    private final int reldate;
    private final int pagecount;
    private final String publisher;
    private final String type;
    private final String ISBN;
    private final int line_number_in_file;

    Book(String title, String author, int reldate, int pagecount, String publisher, String type, String ISBN,
         int line_number_in_file) {
        this.title = title;
        this.author = author;
        this.reldate = reldate;
        this.pagecount = pagecount;
        this.publisher = publisher;
        this.type = type;
        this.ISBN = ISBN;
        this.line_number_in_file = line_number_in_file;
    }

    String getTitle() {
        return title;
    }

    String getISBN() {
        return ISBN;
    }

    String getAuthor() {
        return author;
    }

    String getType() {
        return type;
    }

    int getPagecount() {
        return pagecount;
    }

    int getReldate() {
        return reldate;
    }

    int getLineNumberInFile() {
        return line_number_in_file;
    }
}
