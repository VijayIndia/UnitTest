import com.hubberspot.junit5.bookstore.model.Book;
import com.hubberspot.junit5.bookstore.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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

    @Test
    public void basicAssertArrayEqualsFunction(){
        assertAll(
                    ()-> assertArrayEquals(new String[]{"1"},bookService.getBookIdsByPublisher("Publisher1")),
                    ()->assertArrayEquals(new String[1],bookService.getBookIdsByPublisher("Publisher1"),"Assert Array Condition failed"),
                    ()->assertArrayEquals(new String[1],bookService.getBookIdsByPublisher("Publisher1"),supplierMsg));
    }

    @Test
    public void basicAssertIterableEquals(){
        List<String> bookTitles=new ArrayList<>();
        bookTitles.add("Title1");
        bookTitles.add("Title2");

        List<List<String>> arrList=new ArrayList<List<String>>();
        List<String> subList1=new ArrayList<>();
        subList1.add("Publisher1");
        List<String> subList2=new ArrayList<>();
        subList2.add("Publisher2");
        arrList.add(subList1);
        arrList.add(subList2);

        List<List<String>> arrList2=new ArrayList<List<String>>();
        List<String> newSubList1=new ArrayList<>();
        newSubList1.add("Publisher3");
        List<String> newSubList2=new ArrayList<>();
        newSubList2.add("Publisher1");
        arrList2.add(newSubList1);
        arrList2.add(newSubList2);

        List<String> newbBookTitles=new ArrayList<>();
        newbBookTitles.add("Title2");
        newbBookTitles.add("Title1");
        assertIterableEquals(arrList,arrList2);//bookService.getBookTitlesByPublisher(arrList));
      //  assertEquals(bookTitles,newbBookTitles);
       // assertEquals(arrList,arrList2);
    }
}
