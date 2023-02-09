package estoresearch;

import org.junit.Test;
import static org.junit.Assert.*;

public class ElectronicTest {
    @Test public void testElectronic() {
    	Electronic e1 = new Electronic();
    	Electronic e2 = new Electronic();
    	assertTrue(e1.equals(e2));
    	System.out.println(e1.toString());
    	e2.printProduct();

    	e1.setProductID(5);
    	assertTrue(e1.getProductID() == 5);
    	e1.setDescription("TV");
    	assertTrue(e1.getDescription().equals("TV"));
    	e1.setPrice(50.56);
    	assertTrue(e1.getPrice() == 50.56);
    	e1.setYear(2000);
    	assertTrue(e1.getYear() == 2000);
    	e1.setMaker("Sony");
    	assertTrue(e1.getMaker() == "Sony");
        System.out.println(e1.toString());
    	e1.printProduct();
    }
}