import com.hubberspot.junit5.bookstore.model.Book;
import com.hubberspot.junit5.bookstore.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

public class BasicAssertFunctions {

    int count = 1;
    private BookService bookService;
    private Supplier<String> supplierMsg = () -> {
        String str = "aeiouxyzklm";
        return "Supplier Custom Message ->" + str.charAt((int) (Math.random() * 10)) + count++;
    };

    @BeforeEach
    public void createBook() {
        bookService = new BookService();
    }

    @Test
    public void basicAssertBooleanFunctions() {
        assertAll(
                () -> assertTrue(bookService.getListOfBooks(3) == 3),//assertTrue(booleanCondition)
                () -> assertFalse(bookService.getListOfBooks(3) == 3, "Validated & Custom Error Message to Display"),//assertFalse(booleanCondition,String errorMessage)
                () -> assertTrue(bookService.getListOfBooks(2) == 1, supplierMsg),//assertTrue(booleanCondition,Supplier<String> messageSupplier)
                () -> assertFalse(() -> bookService.getListOfBooks(3) == 3),//assertFalse(booleanSupplier)
                () -> assertTrue(() -> bookService.getListOfBooks(2) == 3, "Custom Error Message is displayed"),//assertTrue(booleanSupplier,String message)
                () -> assertFalse(() -> bookService.getListOfBooks(3) == 3,supplierMsg )//assertFalse(booleanSupplier,Supplier<String> errorMessage)
        );
    }

    @Test
    public void basicAssertEqualsFunctions() {
        assertAll(
                () -> assertEquals(bookService.getBookById("1").getTitle(), "Title2"),//assertEquals(String,String)
                () -> assertEquals(bookService.getBookById("2").getTitle(), "Title1", "Custom Error Message1"),//assertEquals(String,String,String errorMessage)
                () -> assertEquals(bookService.getBookById("3").getTitle(), "Title2", supplierMsg),//assertEquals(String,String,Supplier<String> errorMessage)
                () -> assertNotEquals(bookService.getBookById("1").getTitle(),"Title1"),//assertNotEquals(String,String)
                () -> assertNotEquals(bookService.getBookById("2").getTitle(),"Title2","Custom Error Message"),//assertNotEquals(String,String,String errorMessage)
                () -> assertNotEquals(bookService.getBookById("3").getTitle(),"Title3",supplierMsg)//assertNotEquals(String,String,String errorMessage)
        );
    }

    @Test
    public void basicAssertNullValidation() {
        BookService nullBookService = null;
        assertAll(
                () -> assertNull(bookService),//assertNull(Object)
                () -> assertNull(bookService, "Custom ErrorMsg thrown"),//assertNull(Object,String errorMessage)
                () -> assertNull(bookService, supplierMsg),//assertNull(Object,SupplierMessage)
                () -> assertNotNull(nullBookService),//assertNotNull(Object)
                () -> assertNotNull(nullBookService, "Custom NotNull Error Msg thrown"),//assertNotNull(Object,String)
                () -> assertNotNull(nullBookService, supplierMsg)//assertNotNull(Object,Supplier)
        );
    }

}
