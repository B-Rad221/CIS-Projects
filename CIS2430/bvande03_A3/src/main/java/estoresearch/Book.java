package estoresearch;

public class Book extends Product
{
	private String author;
	private String publisher;

	// Base constructor
	public Book()
	{
		setProductID(000001);
		setDescription("Percy Jackson And The Lightning Theif");
		setPrice(59.99);
		setYear(2010);
		setAuthor("Rick Riordan");
		setPublisher("Puffin Books");
	}
	// Constructor Override
	public Book(int productID, String description, double price, int year, String author, String publisher)
	{
		setProductID(productID);
		setDescription(description);
		setPrice(price);
		setYear(year);
		setAuthor(author);
		setPublisher(publisher);
	}
	// Copy Constructor
	public Book(Book b)
	{
		setProductID(b.getProductID());
		setDescription(b.getDescription());
		setPrice(b.getPrice());
		setYear(b.getYear());
		setAuthor(b.getAuthor());
		setPublisher(b.getPublisher());
	}
	/*Returns the Book's Author*/
	public String getAuthor()
	{
		return this.author;
	}
	/*Returns the Book's Publisher*/
	public String getPublisher()
	{
		return this.publisher;
	}
	/*Sets the Book's Author*/
	public void setAuthor(String author)
	{
		this.author = author;
	}
	/*Sets the Book's Publisher*/
	public void setPublisher(String publisher)
	{
		this.publisher = publisher;
	}
	/*Returns true if the Book specified is identical to this Book.*/
	public boolean equals(Object other)
	{
		if(this == other)
		{
			return true;
		}
		if(other == null || getClass() != other.getClass())
		{
			return false;
		}
		Book tmp = (Book)other;
		return (getProductID() == tmp.getProductID() && getDescription().equals(tmp.getDescription()) && getPrice() == tmp.getPrice() && getYear() == tmp.getYear() && author.equals(tmp.author) && publisher.equals(tmp.publisher));
	}
	/*Converts Book into a string with each variable separated line by line.*/
	public String toString()
	{
		return(super.toString() + "\nAuthor = " + author + "\nPublisher = " + publisher);
	}
	/*Prints the Book to standard out with each variable separated line by line.*/
	public String printProduct()
	{
		return(super.printProduct() + "Author: " + author + "\nPublisher: " + publisher + "\n");
	}
}