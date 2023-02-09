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
	public boolean equals(Electronic otherElectronic)
	{
		if(otherElectronic == null)
		{
			return false;
		}
		else
		{
			return (getProductID() == otherElectronic.getProductID() && getDescription().equals(otherElectronic.getDescription()) && getPrice() == otherElectronic.getPrice() && getYear() == otherElectronic.getYear() && maker.equals(otherElectronic.maker));
		}
	}
	/*Converts Electronic into a string with each variable separated line by line.*/
	public String toString()
	{
		return(super.toString() + "\nMaker = " + maker);
	}
	/*Prints the Electronic to standard out with each variable separated line by line.*/
	public void printProduct()
	{
		super.printProduct();
		System.out.println("Maker: " + maker);
	}
}