package estoresearch;

import java.io.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class eStoreSearchTest {
    @Test public void testEStoreSearch() {
        eStoreSearch store = new eStoreSearch();
        
        int productID = 100101;
		String description = "Testing";
		double price = 0.0;
		int year = 2020;
		String maker = "testEStoreSearch";
		store.addProduct(productID, description, price, year, maker);
		store.addHash(0, description);
		assertNotNull(store.getProducts().get(0));
		store.getProducts().get(0).printProduct();

		productID = 200101;
		description = "Epic";
		price = 26.902183;
		year = 1000;
		String author = "Very Famous Author";
		String publisher = "Unkown Publisher";
		store.addProduct(productID, description, price, year, maker);
		store.addHash(1, description);
		assertNotNull(store.getProducts().get(1));
		store.getProducts().get(1).printProduct();

		// Make sure that duplicate product ID's are rejected
		store.addProduct(productID, description, price, year, maker);

		productID = 2;
		description = "EPIC TesTIng";
		year = 2001;
		store.addProduct(productID, description, price, year);
		store.addHash(2, description);
		assertNotNull(store.getProducts().get(2));
		store.getProducts().get(2).printProduct();

		System.out.println("Adding Done");

		int id = -1;
		String line = "epic testing";
		String[] keywords = line.split(" ");
		String timePeriod = "";
		System.out.println(line + ", Linear Search: ");
		// epic testing key words match
		store.searchProducts(keywords, id, timePeriod);

		System.out.println(line + ", Hash Search: ");
		store.hashSearchProducts(keywords, id, timePeriod);

		id = 200101;
		System.out.println(id + ", Linear Search: ");
		line = "";
		keywords = line.split(" ");
		// ID match test
		store.searchProducts(keywords, id, timePeriod);
		System.out.println(id + ", Hash Search: ");
		store.hashSearchProducts(keywords, id, timePeriod);

		id = -1;
		line = "epic";
		keywords = line.split(" ");
		System.out.println(line + ", Linear Search:");
		// epic keyword test
		store.searchProducts(keywords, id, timePeriod);
		System.out.println(line + ", Hash Search:");
		store.hashSearchProducts(keywords, id, timePeriod);

		line = "";
		timePeriod = "2020";
		System.out.println(timePeriod + ", Linear Search:");
		// 2020 timeperiod test
		store.searchProducts(keywords, id, timePeriod);
		System.out.println(timePeriod + ", Hash Search:");
		store.hashSearchProducts(keywords, id, timePeriod);

		timePeriod = "-2005";
		System.out.println(timePeriod + ", Linear Search:");
		// before 2005 timeperiod test
		store.searchProducts(keywords, id, timePeriod);
		System.out.println(timePeriod + ", Hash Search:");
		store.hashSearchProducts(keywords, id, timePeriod);

		timePeriod = "2005-";
		System.out.println(timePeriod + ", Linear Search:");
		// after 2005 timeperiod test
		store.searchProducts(keywords, id, timePeriod);
		System.out.println(timePeriod + ", Hash Search:");
		store.hashSearchProducts(keywords, id, timePeriod);

		try
		{
			PrintWriter fileWriter = new PrintWriter("eStoreSearch_test.txt", "UTF-8");
			store.saveProducts(fileWriter);
			fileWriter.close();
		}
		catch(Exception e)
		{
			System.out.println("Failed to save!");
		}
    }
}
