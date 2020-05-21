import com.hubberspot.junit5.bookstore.model.Book;
import com.hubberspot.junit5.bookstore.service.BookService;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

public class AssertTrueDemo {

    @Test
    public void assertTest(){
        BookService bookService=new BookService();
        Supplier<String> strSupplier=()->bookService.getString("Vijay");
        assertTrue(strSupplier.get().equalsIgnoreCase("Vijay"));
        // Consumer<String> strConsumer=(b)->bookService.getStringConsumer(b);
        // assertTrue(strSupplier.get().equalsIgnoreCase("Vijay"));
        // assertTrue();
        // strConsumer.accept("Vijay");
    }

    @Test
    public void assertFunctions(){
        //BookService bookService=new BookService();
        //Function<Integer,String> f=()->bookService.getStringConsumer(1);
        //assertEquals(f.apply(1),"str1");
        /*assertAll(
                    a->assertTrue(a);
                     a.accept(true);
                );
        Function<Integer,Boolean> functionObj=(a)->methodToTest(a);
        assertTrue(functionObj.apply(1));*/
    }
}
