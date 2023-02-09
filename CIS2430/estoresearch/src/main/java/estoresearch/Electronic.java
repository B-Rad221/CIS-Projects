package estoresearch;

public class Electronic extends Product
{
	private String maker;

	// Base constructor
	public Electronic()
	{
		setProductID(000000);
		setDescription("ASUS Laptop");
		setPrice(799.99);
		setYear(2020);
		setMaker("ASUS");
	}
	// Constructor Override
	public Electronic(int productID, String description, double price, int year, String maker)
	{
		setProductID(productID);
		setDescription(description);
		setPrice(price);
		setYear(year);
		setMaker(maker);
	}
	// Copy constructor
	public Electronic(Electronic e)
	{
		setProductID(e.getProductID());
		setDescription(e.getDescription());
		setPrice(e.getPrice());
		setYear(e.getYear());
		setMaker(e.getMaker());
	}
	/*Returns the Electronic's Maker*/
	public String getMaker()
	{
		return this.maker;
	}
	/*Sets the Electronic's Maker*/
	public void setMaker(String maker)
	{
		this.maker = maker;
	}
	/*Returns true if the Electronic specified is identical to this Electronic.*/
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
		Electronic tmp = (Electronic)other;
		return (getProductID() == tmp.getProductID() && getDescription().equals(tmp.getDescription()) && getPrice() == tmp.getPrice() && getYear() == tmp.getYear() && maker.equals(tmp.maker));
	}
	/*Converts Electronic into a string with each variable separated line by line.*/
	public String toString()
	{
		return(super.toString() + "\nMaker = " + maker);
	}
	/*Prints the Electronic to standard out with each variable separated line by line.*/
	public String printProduct()
	{
		return(super.printProduct() + "Maker: " + maker + "\n");
	}
}