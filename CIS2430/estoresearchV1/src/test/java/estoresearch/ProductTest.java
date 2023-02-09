package estoresearch;

import org.junit.Test;
import static org.junit.Assert.*;

public class ProductTest {
    @Test public void testProduct() {
    	Product p1 = new Product();
    	Product p2 = new Product();
    	assertTrue(p1.equals(p2));
    	System.out.println(p1.toString());
    	p2.printProduct();

    	p1.setProductID(10);
    	assertTrue(p1.getProductID() == 10);
    	p1.setDescription("New Product");
    	assertTrue(p1.getDescription().equals("New Product"));
    	p1.setPrice(56.78);
    	assertTrue(p1.getPrice() == 56.78);
    	p1.setYear(2005);
    	assertTrue(p1.getYear() == 2005);

    	assertTrue(p1.isWithinTimePeriod("-2020"));
    	assertTrue(p1.isWithinTimePeriod("2001-"));
    	assertFalse(p1.isWithinTimePeriod("2006-"));
    	String[] strings = new String[2];
    	strings[0] = "New";
    	strings[1] = "Product";
    	assertTrue(p1.keyWordSearch(strings));
    	strings[1] = "Item";
    	assertFalse(p1.keyWordSearch(strings));

    }
}