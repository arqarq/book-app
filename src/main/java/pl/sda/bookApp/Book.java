package pl.sda.bookApp;

class Book {
    String title;
    String author;
    int reldate;
    int pagecount;
    String publisherr;
    String type;
    String ISBN;
    int linenumberinfile;

    Book(String title, String author, int reldate, int pagecount, String publisher, String type, String ISBN, int linenumberinfile) {
        this.title = title;
        this.author = author;
        this.reldate = reldate;
        this.pagecount = pagecount;
        this.publisher = publisher;
        this.type = type;
        this.ISBN = ISBN;
        this.linenumberinfile = linenumberinfile;
    }

    public String getTitle() {
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
        return linenumberinfile;
    }
}