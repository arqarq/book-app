package pl.sda.bookApp;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static pl.sda.bookApp.Converters.setBookFromLine;

public class ConvertersTest {
    @Test
    public void setBookObjectFromLine() {
        // Given
        Book book;
        String line = " Title ; Author ; 1958 ; 22000 ; Publisher ; Type ; 012-34-567-8901-2 ";
        int linenumber = 20;
        // When
        book = setBookFromLine(line, linenumber);
        // Then
        assertEquals(book.getTitle(), "Title");
        assertThat(book.getTitle()).isEqualTo("Title");
        assertThat(book.getAuthor()).isEqualTo("Author");
        assertThat(book.getReldate()).isEqualTo(1958);
        assertThat(book.getPagecount()).isEqualTo(22000);
        assertThat(book.getType()).isEqualTo("Type");
        assertThat(book.getISBN()).isEqualTo("012-34-567-8901-2");
        assertThat(book.getLineNumberInFile()).isEqualTo(21);
    }
}
