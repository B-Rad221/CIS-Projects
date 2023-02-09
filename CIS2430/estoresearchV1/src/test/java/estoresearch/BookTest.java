package estoresearch;

import org.junit.Test;
import static org.junit.Assert.*;

public class BookTest {
    @Test public void testBook() {
    	Book b1 = new Book();
    	Book b2 = new Book();
    	assertTrue(b1.equals(b2));
    	System.out.println(b1.toString());
    	b2.printProduct();

    	b1.setProductID(5);
    	assertTrue(b1.getProductID() == 5);
    	b1.setDescription("Lord Of The Rings");
    	assertTrue(b1.getDescription().equals("Lord Of The Rings"));
    	b1.setPrice(55.56);
    	assertTrue(b1.getPrice() == 55.56);
    	b1.setYear(2000);
    	assertTrue(b1.getYear() == 2000);
    	b1.setAuthor("J.R.R. Tolkien");
    	assertTrue(b1.getAuthor() == "J.R.R. Tolkien");
    	b1.setPublisher("Allen & Unwin");
    	assertTrue(b1.getPublisher() == "Allen & Unwin");
        System.out.println(b1.toString());
    	b1.printProduct();
    }
}