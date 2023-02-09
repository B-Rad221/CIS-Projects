package estoresearch;
import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class eStoreSearch extends JFrame
{
		private ArrayList<Product> products = new ArrayList<Product>();
		private HashMap<String, ArrayList<Integer>> hashIndex = new HashMap<String, ArrayList<Integer>>();

		public static final int WIDTH = 500;
		public static final int HEIGHT = 400;
		public static final int TEXT_FIELD_SIZE = 30;
		public static final int TEXT_AREA_SIZE = 50;
		private static final String[] PRODUCT_TYPES = {"Book", "Electronic"};
		private JPanel initialPanel;
		private JPanel addPanel;
		private JPanel searchPanel;
		private JPanel authorPanel;
		private JPanel publisherPanel;
		private JPanel makerPanel;
		private JPanel messagePanel;

		public eStoreSearch()
		{
			super("eStoreSearch");
			setSize(WIDTH, HEIGHT);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
			// Start Page
			initialPanel = new JPanel();
			initialPanel.setVisible(true);
			initialPanel.setLayout(new GridLayout(5, 0));
			JLabel welcome = new JLabel("Welcome to eStoreSearch");
			initialPanel.add(welcome);
			// This line creates a multi-line label, code found here: https://stackoverflow.com/questions/2152742/java-swing-multiline-labels
			JLabel instructions = new JLabel("<html>Choose a command from the \"Commands\" menu above for<br>adding a product, searching products or quitting the program.");
			initialPanel.add(instructions);
			add(initialPanel);

			// Toolbar
			JMenu commandMenu = new JMenu("Commands");
			JMenuItem start = new JMenuItem("Start Page");
			start.addActionListener(new startPageListener());
			commandMenu.add(start);
			JMenuItem add = new JMenuItem("Add");
			add.addActionListener(new addListener());
			commandMenu.add(add);
			JMenuItem search = new JMenuItem("Search");
			search.addActionListener(new searchListener());
			commandMenu.add(search);
			JMenuBar bar = new JMenuBar();
			bar.add(commandMenu);
			setJMenuBar(bar);

			// Add Page
			addPanel = new JPanel();
			addPanel.setVisible(false);
			addPanel.setLayout(new BorderLayout());

			JPanel inputPanel = new JPanel();
			inputPanel.setLayout(new BorderLayout());
			JLabel addInputLabel = new JLabel("Adding a product");
			inputPanel.add(addInputLabel, BorderLayout.NORTH);

			JPanel iPanel = new JPanel();
			iPanel.setLayout(new BoxLayout(iPanel, BoxLayout.Y_AXIS));

			// Type input
			JPanel typePanel = new JPanel();
			typePanel.setLayout(new FlowLayout());
			JLabel typeLabel = new JLabel("Type: ");
			typePanel.add(typeLabel);
			JComboBox typeCombo = new JComboBox(PRODUCT_TYPES);
			typeCombo.addActionListener(new typeComboListener());
			typePanel.add(typeCombo);
			iPanel.add(typePanel);

			// Product ID input
			JPanel productidPanel = new JPanel();
			productidPanel.setLayout(new FlowLayout());
			JLabel productidLabel = new JLabel("ProductID: ");
			productidPanel.add(productidLabel);
			JTextField productidField = new JTextField("Enter ProductID here", TEXT_FIELD_SIZE);
			productidPanel.add(productidField);
			iPanel.add(productidPanel);

			// Description input
			JPanel descriptionPanel = new JPanel();
			descriptionPanel.setLayout(new FlowLayout());
			JLabel descriptionLabel = new JLabel("Description: ");
			descriptionPanel.add(descriptionLabel);
			JTextField descriptionField = new JTextField("Enter description here", TEXT_FIELD_SIZE);
			descriptionPanel.add(descriptionField);
			iPanel.add(descriptionPanel);

			// Price input
			JPanel pricePanel = new JPanel();
			pricePanel.setLayout(new FlowLayout());
			JLabel priceLabel = new JLabel("Price: ");
			pricePanel.add(priceLabel);
			JTextField priceField = new JTextField("Enter price here", TEXT_FIELD_SIZE);
			pricePanel.add(priceField);
			iPanel.add(pricePanel);

			// Year input
			JPanel yearPanel = new JPanel();
			yearPanel.setLayout(new FlowLayout());
			JLabel yearLabel = new JLabel("Year: ");
			yearPanel.add(yearLabel);
			JTextField yearField = new JTextField("Enter year here", TEXT_FIELD_SIZE);
			yearPanel.add(yearField);
			iPanel.add(yearPanel);

			// Author input
			authorPanel = new JPanel();
			authorPanel.setLayout(new FlowLayout());
			JLabel authorLabel = new JLabel("Author: ");
			authorPanel.add(authorLabel);
			JTextField authorField = new JTextField("Enter author here", TEXT_FIELD_SIZE);
			authorPanel.add(authorField);
			iPanel.add(authorPanel);

			// Publisher input
			publisherPanel = new JPanel();
			publisherPanel.setLayout(new FlowLayout());
			JLabel publisherLabel = new JLabel("Publisher: ");
			publisherPanel.add(publisherLabel);
			JTextField publisherField = new JTextField("Enter publisher here", TEXT_FIELD_SIZE);
			publisherPanel.add(publisherField);
			iPanel.add(publisherPanel);

			// Maker input
			makerPanel = new JPanel();
			makerPanel.setLayout(new FlowLayout());
			makerPanel.setVisible(false);
			JLabel makerLabel = new JLabel("Maker: ");
			makerPanel.add(makerLabel);
			JTextField makerField = new JTextField("Enter maker here", TEXT_FIELD_SIZE);
			makerPanel.add(makerField);
			iPanel.add(makerPanel);

			inputPanel.add(iPanel, BorderLayout.CENTER);

			// Messages
			messagePanel = new JPanel(new BorderLayout());
			JLabel messageLabel = new JLabel("Messages: ");
			messagePanel.add(messageLabel, BorderLayout.NORTH);
			JTextArea messageArea = new JTextArea(5, 30);
			messageArea.setEditable(false);
			messagePanel.add(messageArea, BorderLayout.CENTER);

			addPanel.add(inputPanel, BorderLayout.WEST);
			addPanel.add(messagePanel, BorderLayout.SOUTH);
			add(addPanel);

			searchPanel = new JPanel();
			searchPanel.setVisible(false);
			add(searchPanel);
		}

		private class startPageListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				initialPanel.setVisible(true);
				addPanel.setVisible(false);
				searchPanel.setVisible(false);
			}
		}

		private class addListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				addPanel.setVisible(true);
				initialPanel.setVisible(false);
				searchPanel.setVisible(false);
			}
		}

		private class searchListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				searchPanel.setVisible(true);
				addPanel.setVisible(false);
				initialPanel.setVisible(false);
			}
		}

		private class typeComboListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				JComboBox cb = (JComboBox)e.getSource();
				String type = (String)cb.getSelectedItem();
				if(type.equals("Book"))
				{
					authorPanel.setVisible(true);
					publisherPanel.setVisible(true);
					makerPanel.setVisible(false);
				}
				else
				{
					authorPanel.setVisible(false);
					publisherPanel.setVisible(false);
					makerPanel.setVisible(true);
				}
			}
		}

		public static void main(String[] args) 
		{
			boolean quit = false;
			eStoreSearch eStore = new eStoreSearch();
			eStore.setVisible(true);
			int productIndex = 0;
			if(!args[0].isEmpty()) // If the first command line argument isn't empty, load the file specified
			{
				try
				{
					File f = new File(args[0]);
					Scanner scanner = new Scanner(f);
					int productId = 0;
					String description = "";
					double price = 0;
					int year = 0;
					String maker_author = "";
					String publisher = "";
					while(scanner.hasNextLine())
					{
						String[] data = scanner.nextLine().split(" = ");
						if(data[0].equals("Id"))
						{
							try
							{
								productId = Integer.parseInt(data[1]);
							}
							catch(Exception ex)
							{
								System.out.println("Invalid data. Id must be of type Integer");
							}
							
						}
						else if(data[0].equals("Description"))
						{
							if(data.length == 2)
							{
								description = data[1];
							}
							
						}
						else if(data[0].equals("Price"))
						{
							if(data.length == 2)
							{
								try
								{
									price = Double.parseDouble(data[1]);
								}
								catch(Exception ex)
								{
									System.out.println("Invalid data. Price must be of type Double");
								}
							}
							
						}
						else if(data[0].equals("Year"))
						{
							try
							{
								year = Integer.parseInt(data[1]);
							}
							catch(Exception ex)
							{
								System.out.println("Invalid data. Year must be of type Integer");
							}
							
						}
						else if(data[0].equals("Author"))
						{
							if(data.length == 2)
							{
								maker_author = data[1];
							}
						}
						else if(data[0].equals("Publisher"))
						{
							if(data.length == 2)
							{
								publisher = data[1];
							}
							
						}
						else if(data[0].equals("Maker"))
						{
							if(data.length == 2)
							{
								maker_author = data[1];
							}
							
						}
						else
						{
							// If there is an empty line, add the product to the ArrayList and reset the variables
							if(description.isEmpty())
							{
								System.out.println("Description not included in file. Product not added.");
							}
							else if(publisher.isEmpty() && !maker_author.isEmpty()) // Product is an electronic
							{
								eStore.addProduct(productId, description, price, year, maker_author);
							}
							else if(!publisher.isEmpty() && !maker_author.isEmpty()) // Product is a book
							{
								eStore.addProduct(productId, description, price, year, maker_author, publisher);
							}
							else
							{
								eStore.addProduct(productId, description, price, year);
							}
							// Add values to our hash map if the description isn't empty
							if(!description.isEmpty())
							{
								eStore.addHash(productIndex, description);
								productIndex++;
							}
							productId = 0;
							description = "";
							price = 0;
							year = 0;
							maker_author = "";
							publisher = "";
						}
					}
				}
				catch(Exception e)
				{
					System.out.println("Unable to load the specified file.");
				}
			}
			Scanner keyboard = new Scanner(System.in);
			while(quit == false)
			{
				System.out.println("\nWelcome to eStoreSearch.\nPlease enter the command you wish to run. (add, search, quit)\n");
				String answer = keyboard.nextLine();
				if(answer.equalsIgnoreCase("add") || answer.equalsIgnoreCase("a"))
				{
					boolean added = false;
					while(added == false)
					{
						System.out.println("What type of item would you like to add? (book, electronic)");
						String type = keyboard.nextLine();
						if(type.equalsIgnoreCase("book") || type.equalsIgnoreCase("b"))
						{
							boolean identified = false;
							while(identified == false)
							{
								System.out.println("Please enter the product ID:");
								if(keyboard.hasNextInt())
								{
									int id = keyboard.nextInt();
									identified = true;
									keyboard.nextLine();
									boolean described = false;
									String desc = "";
									while(described == false)
									{
										System.out.println("Please enter the description:");
										desc = keyboard.nextLine();
										if(!desc.isEmpty())
										{
											described = true;
										}
										else
										{
											System.out.println("Please enter a valid description.");
										}
									}
									System.out.println("Please enter the price (optional):");
									double price = 0.0;
									String price_line = keyboard.nextLine();
									if(!price_line.isEmpty())
									{
										try
										{
											price = Double.parseDouble(price_line);
										}
										catch(NumberFormatException e)
										{
											System.out.println("There was an exception. You entered an invalid data type.");
										}
									}
									boolean y = false;
									int yr = 0;
									while(y == false)
									{
										System.out.println("Please enter the year:");
										if(keyboard.hasNextInt())
										{
											yr = keyboard.nextInt();
											keyboard.nextLine();
											y = true;
										}
										else
										{
											System.out.println("Please enter a valid year.");
										}
									}
									System.out.println("Please enter the author (optional):");
									String auth = keyboard.nextLine();
									System.out.println("Please enter the publisher (optional):");
									String publ = keyboard.nextLine();
									eStore.addProduct(id, desc, price, yr, auth, publ);
									eStore.addHash(productIndex, desc);
									productIndex++;
									added = true;
								}	
								else
								{
									System.out.println("Invalid input.\n");
									keyboard.nextLine();
								}
							}
						}
						else if(type.equalsIgnoreCase("electronic") || type.equalsIgnoreCase("e"))
						{
							boolean identified = false;
							while(identified == false)
							{
								System.out.println("Please enter the product ID:");
								if(keyboard.hasNextInt())
								{
									int id = keyboard.nextInt();
									identified = true;
									keyboard.nextLine();
									boolean described = false;
									String desc = "";
									while(described == false)
									{
										System.out.println("Please enter the description:");
										desc = keyboard.nextLine();
										if(!desc.isEmpty())
										{
											described = true;
										}
										else
										{
											System.out.println("Please enter a valid description.");
										}
									}
									System.out.println("Please enter the price (optional):");
									double price = 0.0;
									String price_line = keyboard.nextLine();
									if(!price_line.isEmpty())
									{
										try
										{
											price = Double.parseDouble(price_line);
										}
										catch(NumberFormatException e)
										{
											System.out.println("There was an exception. You entered an invalid data type.");
										}
									}
									boolean y = false;
									int yr = 0;
									while(y == false)
									{
										System.out.println("Please enter the year:");
										if(keyboard.hasNextInt())
										{
											yr = keyboard.nextInt();
											keyboard.nextLine();
											y = true;
										}
										else
										{
											System.out.println("Please enter a valid year.");
										}
									}
									System.out.println("Please enter the maker (optional):");
									String maker = keyboard.nextLine();
									eStore.addProduct(id, desc, price, yr, maker);
									eStore.addHash(productIndex, desc);
									productIndex++;
									added = true;
								}
								else
								{
									System.out.println("Invalid input.\n");
									keyboard.nextLine();
								}
							}
						}
						else
						{
							System.out.println("Invalid input. Please enter either book or electronic.\n");
						}
				}
			}
			else if(answer.equalsIgnoreCase("search") || answer.equalsIgnoreCase("s"))
			{
				System.out.println("Please enter the product ID to search (optional):");
				int id = -1;
				String id_line = keyboard.nextLine();
				if(!id_line.isEmpty())
				{
					try
					{
						id = Integer.parseInt(id_line);
					}
					catch(NumberFormatException e)
					{
						System.out.println("There was an exception. You entered an invalid data type.");
					}
				}
				System.out.println("Please enter some keywords to search (optional):");
				String line = keyboard.nextLine();
				String[] keywords = line.split(" ");
				System.out.println("Please enter a time period to search (optional):");
				String timePeriod = keyboard.nextLine();
				boolean typeChosen = false;
				while(typeChosen == false)
				{
					System.out.println("Would you like to use linear, or hash search?");
					String type = keyboard.nextLine();
					if(type.equalsIgnoreCase("linear") || type.equalsIgnoreCase("l"))
					{
						eStore.searchProducts(keywords, id, timePeriod);
						typeChosen = true;
					}
					else if(type.equalsIgnoreCase("hash") || type.equalsIgnoreCase("h"))
					{
						eStore.hashSearchProducts(keywords, id, timePeriod);
						typeChosen = true;
					}
					else
					{
						System.out.println("Please enter either linear or hash.");
					}
				}
			}
			else if(answer.equalsIgnoreCase("quit") || answer.equalsIgnoreCase("q"))
			{
				quit = true;
			}
			else
			{
				System.out.println("Please enter a valid command.\n");
			}
		}
		if(!args[0].isEmpty())
		{
			try
			{
				PrintWriter fileWriter = new PrintWriter(args[0], "UTF-8");
				eStore.saveProducts(fileWriter);
				fileWriter.close();
			}
			catch(Exception e)
			{
				System.out.println("Failed to save!");
			}
		}
	}
	/*This function adds the specified product to the products ArrayList in eStoreSearch. If the product ID matches another product ID in the ArrayList, it will print an error message and return before adding the product.*/
	public void addProduct(int productID, String description, double price, int year)
	{
		int i = 0;
		while(i < products.size())
		{
			if(products.get(i).getProductID() == productID)
			{
				System.out.println("Error: productID already exists.");
				return;
			}
			i++;
		}
		Product tmp = new Product(productID, description, price, year);
		products.add(tmp);
	}

	/*This function adds the specified electronic to the products ArrayList in eStoreSearch. If the product ID matches another product ID in the ArrayList, it will print an error message and return before adding the product.*/
	public void addProduct(int productID, String description, double price, int year, String maker)
	{
		int i = 0;
		while(i < products.size())
		{
			if(products.get(i).getProductID() == productID)
			{
				System.out.println("Error: productID already exists.");
				return;
			}
			i++;
		}
		Electronic tmp = new Electronic(productID, description, price, year, maker);
		products.add(tmp);
	}
	/*This function adds the specified electronic to the products ArrayList in eStoreSearch. If the product ID matches another product ID in the ArrayList, it will print an error message and return before adding the product.*/
	public void addProduct(Electronic electronic)
	{
		int i = 0;
		while(i < products.size())
		{
			if(products.get(i).getProductID() == electronic.getProductID())
			{
				System.out.println("Error: productID already exists.");
				return;
			}
			i++;
		}
		i = 0;
		products.add(electronic);
	}

	//*This function adds the specified book to the products ArrayList in eStoreSearch. If the product ID matches another product ID in the ArrayList, it will print an error message and return before adding the product.*/
	public void addProduct(int productID, String description, double price, int year, String author, String publisher)
	{
		int i = 0;
		while(i < products.size())
		{
			if(products.get(i).getProductID() == productID)
			{
				System.out.println("Error: productID already exists.");
				return;
			}
			i++;
		}
		Book tmp = new Book(productID, description, price, year, author, publisher);
		products.add(tmp);
	}
	/*This function adds the specified book to the products ArrayList in eStoreSearch. If the product ID matches another product ID in the ArrayList, it will print an error message and return before adding the product.*/
	public void addProduct(Book book)
	{
		int i = 0;
		while(i < products.size())
		{
			if(products.get(i).getProductID() == book.getProductID())
			{
				System.out.println("Error: productID already exists.");
				return;
			}
			i++;
		}
		products.add(book);
	}
	/*This function adds the keywords in the description to the HashMap if they aren't already there, and adds the integer productIndex to each of the corresponding ArrayLists*/
	public void addHash(int productIndex, String description)
	{
		String[] keywords = description.toLowerCase().split(" ");
		int i;
		for (i = 0; i < keywords.length; i++) 
		{
			if(hashIndex.get(keywords[i]) == null) // If the keyword doesn't already exist, it creates a new space for it along with an integer arraylist
			{
				hashIndex.put(keywords[i], new ArrayList<Integer>());
			}
			hashIndex.get(keywords[i]).add(productIndex);
		}
	}
	/*This Function linearly searches the products ArrayList for products with the specified keywords, id and timePeriod*/
	public void searchProducts(String[] keywords, int id, String timePeriod)
	{
		int i = 0;
		while(i < products.size())
		{
			if((products.get(i).keyWordSearch(keywords) || (keywords.length == 1 && keywords[0].isEmpty())) && (products.get(i).getProductID() == id || id == -1) && products.get(i).isWithinTimePeriod(timePeriod))
			{
				products.get(i).printProduct();
			}
			i++;
		}
	}
	/*This function uses the HashMap to search the products ArrayList for products with the specified keywords, id and timePeriod*/
	public void hashSearchProducts(String[] keywords, int id, String timePeriod)
	{
		if((keywords.length == 1 && keywords[0].isEmpty()) || hashIndex.isEmpty())
		{
			searchProducts(keywords, id, timePeriod);
		}
		else
		{
			ArrayList<Integer> intersection = new ArrayList<Integer>();
			if(hashIndex.get(keywords[0].toLowerCase()) != null)
			{
				intersection.addAll(hashIndex.get(keywords[0].toLowerCase()));
			}
			else
			{
				searchProducts(keywords, id, timePeriod);
				return;
			}
			int i;
			for (i = 1; i < keywords.length; i++) 
			{
				if(hashIndex.get(keywords[i].toLowerCase()) != null)
				{
					// If the hashMap ArrayList doesn't contain a number from the intesection ArrayList, remove that number from the intersection ArrayList
					int n;
					for(n = 0; n < intersection.size(); n++)
					{
						if(!hashIndex.get(keywords[i].toLowerCase()).contains(intersection.get(n)))
						{
							intersection.remove(n);
						}
					}
				}
			}
			if(intersection == null)
			{
				searchProducts(keywords, id, timePeriod);
			}
			else
			{
				int x;
				for(x = 0; x < intersection.size(); x++)
				{
					if((products.get(intersection.get(x)).getProductID() == id || id == -1) && products.get(intersection.get(x)).isWithinTimePeriod(timePeriod))
					{
						products.get(intersection.get(x)).printProduct();
					}
				}
			}
		}
	}
	/*Returns the products ArrayList*/
	public ArrayList<Product> getProducts()
	{
		return products;
	}
	/*Writes the product ArrayList using the specified PrintWriter*/
	public void saveProducts(PrintWriter writer)
	{
		int i;
		for(i = 0; i < products.size(); i++)
		{
			writer.println(products.get(i).toString());
			writer.print("\n");
		}
	}
}