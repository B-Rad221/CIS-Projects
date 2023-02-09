package estoresearch;

public class Product
{
	private int productID;
	private String description;
	private double price;
	private int year;

	public Product()
	{
		setProductID(000000);
		setDescription("ASUS Laptop");
		setPrice(799.99);
		setYear(2020);
	}
	public Product(int productID, String description, double price, int year)
	{
		setProductID(productID);
		setDescription(description);
		setPrice(price);
		setYear(year);
	}
	/*Returns the Product's ID.*/
	public int getProductID()
	{
		return this.productID;
	}
	/*Returns the Product's Description.*/
	public String getDescription()
	{
		return this.description;
	}
	/*Returns the Product's Price.*/
	public double getPrice()
	{
		return this.price;
	}
	/*Returns the Product's Year.*/
	public int getYear()
	{
		return this.year;
	}
	/*Sets the Product's ID if it is a valid ID. (Can't be greater than 999999)*/
	public void setProductID(int id)
	{
		if(id > 999999)
		{
			System.out.println("Product ID cannot be greater than 999999");
			System.exit(0);
		}
		this.productID = id;
	}
	/*Sets the Product's Description.*/
	public void setDescription(String description)
	{
		this.description = description;
	}
	/*Sets the Product's Price.*/
	public void setPrice(double price)
	{
		this.price = price;
	}
	/*Sets the Product's Year if it is a valid year. (Must be a value between 1000 and 9999)*/
	public void setYear(int year)
	{
		if(year < 1000 || year > 9999)
		{
			System.out.println("Year must be a four digit number");
			System.exit(0);
		}
		this.year = year;
	}
	/*Returns true if the product specified is identical to this product.*/
	public boolean equals(Product otherProduct)
	{
		if(otherProduct == null)
		{
			return false;
		}
		else
		{
			return (productID == otherProduct.productID && description.equals(otherProduct.description) && price == otherProduct.price && year == otherProduct.year);
		}
	}
	/*Converts Product into a string with each variable separated line by line.*/
	public String toString()
	{
		String id_string = Integer.toString(productID);
		String price_string = Double.toString(price);
		String year_string = Integer.toString(year);
		return("Id = " + id_string + "\nDescription = " + description + "\nPrice = " + price_string + "\nYear = " + year_string);
	}
	/*Prints the product to standard out with each variable separated line by line.*/
	public void printProduct()
	{
		System.out.printf("\nId: %06d\n", productID);
		System.out.println("Description: " + description);
		System.out.printf("Price: %.2f\n", price);
		System.out.printf("Year: %4d\n", year);
	}
	/*Returns true if the words in the description contains all of the words in the keywords array.*/
	public boolean keyWordSearch(String[] keywords)
	{
		String[] words = description.split(" ");
		int i, j;
		int count = 0;
		for(i = 0; i < words.length; i++)
		{
			for(j = 0; j < keywords.length; j++) 
			{
				if(words[i].equalsIgnoreCase(keywords[j]))
				{
					count++; // Count how many words match, if this is greater than or equal to the number of keywords in the array, then the item matches the search.
				}
			}
		}
		if(count >= keywords.length)
		{
			return true;
		}
		return false;
	}
	/*Returns true if the year of the product is the time period specified in the String. If there is a dash before the year, it will return true for everything before that year. If there is a dash after the year, it will return true for everything after that year. If there is a dash in between two years, it will return true for anything in between those two years.*/
	public boolean isWithinTimePeriod(String timePeriod)
	{
		if(timePeriod.isEmpty())
		{
			return true;
		}
		String[] years = timePeriod.split("-");
		int year1 = 0;
		int year2 = 0;
		// years[0] is empty when "-" is the first character of timePeriod, meaning include everything before years[1]
		if(years[0].isEmpty())
		{
			if(!years[1].isEmpty())
			{
				try
				{
					year2 = Integer.parseInt(years[1]);
				}
				catch(Exception e)
				{
					System.out.println("Invalid data type. Year must be an integer.");
					return false;
				}
			}
			if(year <= year2)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else if(years.length == 1) 
		{
			if(!years[0].isEmpty())
			{
				try
				{
					year1 = Integer.parseInt(years[0]);
				}
				catch(Exception e)
				{
					System.out.println("Invalid data type. Year must be an integer.");
					return false;
				}
			}
			if(timePeriod.charAt(timePeriod.length() - 1) == '-') // "-" is the last character of timePeriod, meaning include everything after years[0]
			{
				if(year >= year1)
				{
					return true;
				}
			}
			else // This will occur when only a single year was entered
			{
				if(year == year1)
				{
					return true;
				}
			}
			return false;
		}
		else
		{
			if(!years[0].isEmpty())
			{
				try
				{
					year1 = Integer.parseInt(years[0]);
				}
				catch(Exception e)
				{
					System.out.println("Invalid data type. Year must be an integer.");
					return false;
				}
			}
			if(!years[1].isEmpty())
			{
				try
				{
					year2 = Integer.parseInt(years[1]);
				}
				catch(Exception e)
				{
					System.out.println("Invalid data type. Year must be an integer.");
					return false;
				}
			}
			if(year <= year2 && year >= year1)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
	}
}