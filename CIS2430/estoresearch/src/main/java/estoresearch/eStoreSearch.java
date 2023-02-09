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

		public static final int WIDTH = 600;
		public static final int HEIGHT = 600;
		public static final int TEXT_FIELD_SIZE = 25;
		public static final int TEXT_AREA_SIZE = 60;
		private static final String[] PRODUCT_TYPES = {"Book", "Electronic"};
		private JPanel initialPanel;
		private JPanel addPanel;
		private JPanel searchPanel;
		private JTextField productidField;
		private JTextField descriptionField;
		private JTextField priceField;
		private JTextField yearField;
		private JTextField makerField;
		private JTextField authorField;
		private JTextField publisherField;
		private JTextArea messageArea;
		private JTextField productIDField;
		private JTextField keywordsField;
		private JTextField startYearField;
		private JTextField endYearField;
		private JTextArea resultArea;
		private JPanel authorPanel;
		private JPanel publisherPanel;
		private JPanel makerPanel;
		private int productIndex;
		private String saveFileName;

		public eStoreSearch()
		{
			super("eStoreSearch");
			setSize(WIDTH, HEIGHT);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
			productIndex = 0;
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
			JMenuItem quit = new JMenuItem("Quit");
			quit.addActionListener(new quitListener());
			commandMenu.add(quit);
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
			productidField = new JTextField("Enter ProductID here", TEXT_FIELD_SIZE);
			productidPanel.add(productidField);
			iPanel.add(productidPanel);

			// Description input
			JPanel descriptionPanel = new JPanel();
			descriptionPanel.setLayout(new FlowLayout());
			JLabel descriptionLabel = new JLabel("Description: ");
			descriptionPanel.add(descriptionLabel);
			descriptionField = new JTextField("Enter description here", TEXT_FIELD_SIZE);
			descriptionPanel.add(descriptionField);
			iPanel.add(descriptionPanel);

			// Price input
			JPanel pricePanel = new JPanel();
			pricePanel.setLayout(new FlowLayout());
			JLabel priceLabel = new JLabel("Price: ");
			pricePanel.add(priceLabel);
			priceField = new JTextField("Enter price here", TEXT_FIELD_SIZE);
			pricePanel.add(priceField);
			iPanel.add(pricePanel);

			// Year input
			JPanel yearPanel = new JPanel();
			yearPanel.setLayout(new FlowLayout());
			JLabel yearLabel = new JLabel("Year: ");
			yearPanel.add(yearLabel);
			yearField = new JTextField("Enter year here", TEXT_FIELD_SIZE);
			yearPanel.add(yearField);
			iPanel.add(yearPanel);

			// Author input
			authorPanel = new JPanel();
			authorPanel.setLayout(new FlowLayout());
			JLabel authorLabel = new JLabel("Author: ");
			authorPanel.add(authorLabel);
			authorField = new JTextField("Enter author here", TEXT_FIELD_SIZE);
			authorPanel.add(authorField);
			iPanel.add(authorPanel);

			// Publisher input
			publisherPanel = new JPanel();
			publisherPanel.setLayout(new FlowLayout());
			JLabel publisherLabel = new JLabel("Publisher: ");
			publisherPanel.add(publisherLabel);
			publisherField = new JTextField("Enter publisher here", TEXT_FIELD_SIZE);
			publisherPanel.add(publisherField);
			iPanel.add(publisherPanel);

			// Maker input
			makerPanel = new JPanel();
			makerPanel.setLayout(new FlowLayout());
			makerPanel.setVisible(false);
			JLabel makerLabel = new JLabel("Maker: ");
			makerPanel.add(makerLabel);
			makerField = new JTextField("Enter maker here", TEXT_FIELD_SIZE);
			makerPanel.add(makerField);
			iPanel.add(makerPanel);

			inputPanel.add(iPanel, BorderLayout.CENTER);

			// Messages
			JPanel messagePanel = new JPanel(new BorderLayout());
			JLabel messageLabel = new JLabel("Messages: ");
			messagePanel.add(messageLabel, BorderLayout.NORTH);
			messageArea = new JTextArea(5, 30);
			messageArea.setEditable(false);
			JScrollPane scroll = new JScrollPane(messageArea);
			messagePanel.add(scroll, BorderLayout.CENTER);

			// Buttons
			JPanel addButtonPanel = new JPanel();
			addButtonPanel.setLayout(new GridLayout(10, 0, 0, 15));
			// Create and add empty panels to take up space
			addButtonPanel.add(new JPanel());
			addButtonPanel.add(new JPanel());
			addButtonPanel.add(new JPanel());
			addButtonPanel.add(new JPanel());
			JPanel resetPanel = new JPanel();
			resetPanel.setLayout(new BorderLayout());
			JButton resetButton = new JButton("Reset");
			resetButton.addActionListener(new resetButtonListener());
			resetPanel.add(resetButton, BorderLayout.CENTER);
			addButtonPanel.add(resetPanel);
			JPanel add_button_panel = new JPanel();
			add_button_panel.setLayout(new BorderLayout());
			JButton addButton = new JButton("Add");
			addButton.addActionListener(new addButtonListener());
			add_button_panel.add(addButton, BorderLayout.CENTER);
			addButtonPanel.add(add_button_panel);

			addPanel.add(addButtonPanel, BorderLayout.CENTER);
			addPanel.add(inputPanel, BorderLayout.WEST);
			addPanel.add(messagePanel, BorderLayout.SOUTH);
			add(addPanel);

			// Search Page
			searchPanel = new JPanel();
			searchPanel.setVisible(false);
			searchPanel.setLayout(new BorderLayout());

			JPanel sInputPanel = new JPanel();
			sInputPanel.setLayout(new BorderLayout());
			JLabel searchInputLabel = new JLabel("Searching products");
			sInputPanel.add(searchInputLabel, BorderLayout.NORTH);

			JPanel sIPanel = new JPanel();
			sIPanel.setLayout(new BoxLayout(sIPanel, BoxLayout.Y_AXIS));

			// Product ID input
			JPanel productIDPanel = new JPanel();
			productIDPanel.setLayout(new FlowLayout());
			JLabel productIDLabel = new JLabel("ProductID: ");
			productIDPanel.add(productIDLabel);
			productIDField = new JTextField("Enter ProductID here", TEXT_FIELD_SIZE);
			productIDPanel.add(productIDField);
			sIPanel.add(productIDPanel);

			// Keywords input
			JPanel keywordsPanel = new JPanel();
			keywordsPanel.setLayout(new FlowLayout());
			JLabel keywordsLabel = new JLabel("Keywords: ");
			keywordsPanel.add(keywordsLabel);
			keywordsField = new JTextField("Enter keywords here", TEXT_FIELD_SIZE);
			keywordsPanel.add(keywordsField);
			sIPanel.add(keywordsPanel);

			// Start year input
			JPanel startYearPanel = new JPanel();
			startYearPanel.setLayout(new FlowLayout());
			JLabel startYearLabel = new JLabel("Start Year: ");
			startYearPanel.add(startYearLabel);
			startYearField = new JTextField("Enter start year here", TEXT_FIELD_SIZE);
			startYearPanel.add(startYearField);
			sIPanel.add(startYearPanel);

			// End year input
			JPanel endYearPanel = new JPanel();
			endYearPanel.setLayout(new FlowLayout());
			JLabel endYearLabel = new JLabel("End year: ");
			endYearPanel.add(endYearLabel);
			endYearField = new JTextField("Enter end year here", TEXT_FIELD_SIZE);
			endYearPanel.add(endYearField);
			sIPanel.add(endYearPanel);

			sInputPanel.add(sIPanel, BorderLayout.CENTER);

			// Search results
			JPanel resultPanel = new JPanel(new BorderLayout());
			JLabel resultLabel = new JLabel("Search Results: ");
			resultPanel.add(resultLabel, BorderLayout.NORTH);
			resultArea = new JTextArea(5, 30);
			resultArea.setEditable(false);
			JScrollPane scrollr = new JScrollPane(resultArea);
			resultPanel.add(scrollr, BorderLayout.CENTER);

			// Buttons
			JPanel searchButtonPanel = new JPanel();
			searchButtonPanel.setLayout(new GridLayout(10, 0, 0, 15));
			// Create and add empty panels to take up space
			searchButtonPanel.add(new JPanel());
			searchButtonPanel.add(new JPanel());
			searchButtonPanel.add(new JPanel());
			searchButtonPanel.add(new JPanel());
			JPanel resPanel = new JPanel();
			resPanel.setLayout(new BorderLayout());
			JButton resButton = new JButton("Reset");
			resButton.addActionListener(new resetButtonListener());
			resPanel.add(resButton, BorderLayout.CENTER);
			searchButtonPanel.add(resPanel);
			JPanel search_button_panel = new JPanel();
			search_button_panel.setLayout(new BorderLayout());
			JButton searchButton = new JButton("Search");
			searchButton.addActionListener(new searchButtonListener());
			search_button_panel.add(searchButton, BorderLayout.CENTER);
			searchButtonPanel.add(search_button_panel);

			searchPanel.add(searchButtonPanel, BorderLayout.CENTER);
			searchPanel.add(sInputPanel, BorderLayout.WEST);
			searchPanel.add(resultPanel, BorderLayout.SOUTH);
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

		private class quitListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		}

		private class resetButtonListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				productidField.setText("");
				descriptionField.setText("");
				priceField.setText("");
				yearField.setText("");
				makerField.setText("");
				authorField.setText("");
				publisherField.setText("");
				productIDField.setText("");
				keywordsField.setText("");
				startYearField.setText("");
				endYearField.setText("");
			}
		}

		private class addButtonListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				int productID;
				double price;
				int year;
				try
				{
					productID = Integer.parseInt(productidField.getText());
				}
				catch(NumberFormatException e)
				{
					messageArea.append("There was an exception. You entered an invalid data type.\n");
					return;
				}
				if(!priceField.getText().isEmpty())
				{
					try
					{
						price = Double.parseDouble(priceField.getText());
					}
					catch(NumberFormatException e)
					{
						messageArea.append("There was an exception. You entered an invalid data type.\n");
						return;
					}
				}
				else
				{
					price = 0.0;
				}
				try
				{
					year = Integer.parseInt(yearField.getText());
				}
				catch(NumberFormatException e)
				{
					messageArea.append("There was an exception. You entered an invalid data type.\n");
					return;
				}
				if(descriptionField.getText().isEmpty())
				{
					messageArea.append("Error: The description cannot be left empty.\n");
					return;
				}
				if(productID > 999999)
				{
					messageArea.append("Error: The product ID cannot be greater than 999999.\n");
					return;
				}
				if(year < 1000 || year > 9999)
				{
					messageArea.append("Error: The year must be a four digit number.\n");
					return;
				}
				if(authorPanel.isVisible())
				{
					addProduct(productID, descriptionField.getText(), price, year, authorField.getText(), publisherField.getText());
				}
				else
				{
					addProduct(productID, descriptionField.getText(), price, year, makerField.getText());
				}
				addHash(productIndex, descriptionField.getText());
				productIndex++;
				if(!saveFileName.isEmpty())
				{
					try
					{
						PrintWriter fileWriter = new PrintWriter(saveFileName, "UTF-8");
						saveProducts(fileWriter);
						fileWriter.close();
					}
					catch(Exception e)
					{
						messageArea.append("Failed to save!");
					}
				}
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

		private class searchButtonListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				int productID, startYear, endYear;
				String timePeriod = "";
				String[] keywords;
				if(!productIDField.getText().isEmpty())
				{
					try
					{
						productID = Integer.parseInt(productIDField.getText());
					}
					catch(NumberFormatException e)
					{
						resultArea.append("There was an exception. You entered an invalid data type.\n");
						return;
					}
				}
				else
				{
					productID = -1;
				}
				if(productID > 999999)
				{
					resultArea.append("Error: The product ID cannot be greater than 999999.\n");
					return;
				}
				if(!startYearField.getText().isEmpty())
				{
					try
					{
						startYear = Integer.parseInt(startYearField.getText());
					}
					catch(NumberFormatException e)
					{
						resultArea.append("There was an exception. You entered an invalid data type.\n");
						return;
					}
					if(startYear < 1000 || startYear > 9999)
					{
						resultArea.append("Error: The start year must be a four digit number.\n");
						return;
					}
					timePeriod = timePeriod.concat(startYearField.getText());
				}
				else
				{
					if(!endYearField.getText().isEmpty())
					{
						timePeriod = timePeriod.concat("-");
					}
				}
				if(!endYearField.getText().isEmpty())
				{
					try
					{
						endYear = Integer.parseInt(endYearField.getText());
					}
					catch(NumberFormatException e)
					{
						resultArea.append("There was an exception. You entered an invalid data type.\n");
						return;
					}
					if(endYear < 1000 || endYear > 9999)
					{
						resultArea.append("Error: The end year must be a four digit number.\n");
						return;
					}
					if(!startYearField.getText().isEmpty())
					{
						timePeriod = timePeriod.concat("-");
					}
					timePeriod = timePeriod.concat(endYearField.getText());
				}
				else
				{
					if(!startYearField.getText().isEmpty())
					{
						timePeriod = timePeriod.concat("-");
					}
				}
				keywords = keywordsField.getText().split(" ");
				hashSearchProducts(keywords, productID, timePeriod);
				resultArea.append("\n");
			}
		}

		public static void main(String[] args) 
		{
			boolean quit = false;
			eStoreSearch eStore = new eStoreSearch();
			eStore.setVisible(true);
			if(args.length == 1) // If the first command line argument isn't empty, load the file specified
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
					boolean isBook = false;
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
							isBook = true;
						}
						else if(data[0].equals("Publisher"))
						{
							if(data.length == 2)
							{
								publisher = data[1];
							}
							isBook = true;
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
							else if(!isBook) // Product is an electronic
							{
								eStore.addProduct(productId, description, price, year, maker_author);
							}
							else // Product is a book
							{
								eStore.addProduct(productId, description, price, year, maker_author, publisher);
							}
							// Add values to our hash map if the description isn't empty
							if(!description.isEmpty())
							{
								eStore.addHash(eStore.productIndex, description);
								eStore.productIndex++;
							}
							productId = 0;
							description = "";
							price = 0;
							year = 0;
							maker_author = "";
							publisher = "";
						}
					}
					eStore.saveFileName = args[0];
				}
				catch(Exception e)
				{
					System.out.println("Unable to load the specified file.");
				}
			}
			/*Scanner keyboard = new Scanner(System.in);
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
		}*/
	}
	/*This function adds the specified product to the products ArrayList in eStoreSearch. If the product ID matches another product ID in the ArrayList, it will print an error message and return before adding the product.*/
	/*public void addProduct(int productID, String description, double price, int year)
	{
		int i = 0;
		while(i < products.size())
		{
			if(products.get(i).getProductID() == productID)
			{
				messageArea.append("Error: productID already exists.\n");
				return;
			}
			i++;
		}
		Product tmp = new Product(productID, description, price, year);
		products.add(tmp);
		messageArea.append("Product Added\n");
	}*/

	/*This function adds the specified electronic to the products ArrayList in eStoreSearch. 
	If the product ID matches another product ID in the ArrayList, it will print an error message and return before adding the product.*/
	public void addProduct(int productID, String description, double price, int year, String maker)
	{
		int i = 0;
		while(i < products.size())
		{
			if(products.get(i).getProductID() == productID)
			{
				messageArea.append("Error: productID already exists.\n");
				return;
			}
			i++;
		}
		Electronic tmp = new Electronic(productID, description, price, year, maker);
		products.add(tmp);
		messageArea.append("Electronic Added\n");
	}
	/*This function adds the specified electronic to the products ArrayList in eStoreSearch. If the product ID matches another product ID in the ArrayList, it will print an error message and return before adding the product.*/
	public void addProduct(Electronic electronic)
	{
		int i = 0;
		while(i < products.size())
		{
			if(products.get(i).getProductID() == electronic.getProductID())
			{
				messageArea.append("Error: productID already exists.\n");
				return;
			}
			i++;
		}
		i = 0;
		products.add(electronic);
		messageArea.append("Electronic Added\n");
	}

	//*This function adds the specified book to the products ArrayList in eStoreSearch. If the product ID matches another product ID in the ArrayList, it will print an error message and return before adding the product.*/
	public void addProduct(int productID, String description, double price, int year, String author, String publisher)
	{
		int i = 0;
		while(i < products.size())
		{
			if(products.get(i).getProductID() == productID)
			{
				messageArea.append("Error: productID already exists.\n");
				return;
			}
			i++;
		}
		Book tmp = new Book(productID, description, price, year, author, publisher);
		products.add(tmp);
		messageArea.append("Book Added\n");
	}
	/*This function adds the specified book to the products ArrayList in eStoreSearch. If the product ID matches another product ID in the ArrayList, it will print an error message and return before adding the product.*/
	public void addProduct(Book book)
	{
		int i = 0;
		while(i < products.size())
		{
			if(products.get(i).getProductID() == book.getProductID())
			{
				messageArea.append("Error: productID already exists.\n");
				return;
			}
			i++;
		}
		products.add(book);
		messageArea.append("Book Added\n");
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
				resultArea.append(products.get(i).printProduct());
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
						resultArea.append(products.get(intersection.get(x)).printProduct());
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