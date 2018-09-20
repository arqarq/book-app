package pl.sda.bookApp;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static pl.sda.bookApp.Converters.setBookFromLine;

class ConvertersTest {
    @Test
    void setBookFromLineTest() {
        // Given
        Book book;
        String line = " Title ; Author ; 1958 ; 22000 ; Publisher ; Type ; 012-34-567-8901-2 ";
        int linenumber = 20;
        // When
        book = setBookFromLine(line, linenumber);
        // Then
        assertThat(book.getTitle()).isEqualTo("Title");
        assertThat(book.getAuthor()).isEqualTo("Author");
        assertThat(book.getReldate()).isEqualTo(1958);
        assertThat(book.getPagecount()).isEqualTo(22000);
        assertThat(book.getType()).isEqualTo("Type");
        assertThat(book.getISBN()).isEqualTo("012-34-567-8901-2");
        assertThat(book.getLineNumberInFile()).isEqualTo(21);
    }
}