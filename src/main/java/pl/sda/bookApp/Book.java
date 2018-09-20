package pl.sda.bookApp;

class Book {
    private final String title;
    private final String author;
    private final int reldate;
    private final int pagecount;
    private final String publisherrr;
    private final String type;
    private final String ISBN;
    private final int linenumberinfile;

    Book(String title, String author, int reldate, int pagecount, String publisherrr, String type, String ISBN, int linenumberinfile) {
        this.title = title;
        this.author = author;
        this.reldate = reldate;
        this.pagecount = pagecount;
        this.publisherrr = publisherrr;
        this.type = type;
        this.ISBN = ISBN;
        this.linenumberinfile = linenumberinfile;
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
        return linenumberinfile;
    }
}