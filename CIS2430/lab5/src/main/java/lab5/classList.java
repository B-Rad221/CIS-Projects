package classlist;
import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class classList extends JFrame
{
	public static final int HEIGHT = 500;
	public static final int WIDTH = 600;
	public static final String[] STUDENT_TYPES = {"Student", "Graduate Student"};
	public static final int TEXT_FIELD_SIZE = 30;
	public static final int TEXT_AREA_SIZE = 50;
    private ArrayList<Student> students = new ArrayList<Student>();
    private HashMap<String, Student> hashmap = new HashMap<String, Student>();
    private JPanel initialPanel;
    private JPanel addPanel;
    private JPanel averagesPanel;
    private JPanel savePanel;
    private JPanel loadPanel;
    private JPanel searchPanel;
    private JPanel supervisorPanel;
    private JPanel isPHDPanel;
    private JPanel schoolPanel;
    private JTextField programField;
    private JTextField yearField;
    private JTextField gradeField;
    private JTextField lastNameField;
    private JTextField supervisorField;
    private JTextField isPHDField;
    private JTextField schoolField;
    private JTextArea messageArea;
    private JTextArea mArea;
    private JTextField saveNameField;
    private JTextArea sMArea;
    private JTextField loadNameField;
    private JTextArea lMArea;
    private JTextArea searchMArea;
    private JTextField programSearchField;

    public classList()
    {
    	super();
    	setSize(WIDTH, HEIGHT);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

    	// Start Page
		initialPanel = new JPanel();
		initialPanel.setVisible(true);
		initialPanel.setLayout(new GridLayout(5, 0));
		JLabel welcome = new JLabel("Welcome!");
		initialPanel.add(welcome);
		// This line creates a multi-line label, code found here: https://stackoverflow.com/questions/2152742/java-swing-multiline-labels
		JLabel instructions = new JLabel("<html>Choose a command from the \"Commands\" menu above for<br>adding a student, finding average grades, saving, loading, or searching students.");
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
		JMenuItem average = new JMenuItem("Averages");
		average.addActionListener(new averagesListener());
		commandMenu.add(average);
		JMenuItem save = new JMenuItem("Save");
		save.addActionListener(new saveListener());
		commandMenu.add(save);
		JMenuItem load = new JMenuItem("Load");
		load.addActionListener(new loadListener());
		commandMenu.add(load);
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
		JLabel addInputLabel = new JLabel("Adding a student");
		inputPanel.add(addInputLabel, BorderLayout.NORTH);

		JPanel iPanel = new JPanel();
		iPanel.setLayout(new BoxLayout(iPanel, BoxLayout.Y_AXIS));

		// Type input
		JPanel typePanel = new JPanel();
		typePanel.setLayout(new FlowLayout());
		JLabel typeLabel = new JLabel("Type: ");
		typePanel.add(typeLabel);
		JComboBox typeCombo = new JComboBox(STUDENT_TYPES);
		typeCombo.addActionListener(new typeComboListener());
		typePanel.add(typeCombo);
		iPanel.add(typePanel);

		// Program input
		JPanel programPanel = new JPanel();
		programPanel.setLayout(new FlowLayout());
		JLabel programLabel = new JLabel("Program: ");
		programPanel.add(programLabel);
		programField = new JTextField("Enter program here", TEXT_FIELD_SIZE);
		programPanel.add(programField);
		iPanel.add(programPanel);

		// Year input
		JPanel yearPanel = new JPanel();
		yearPanel.setLayout(new FlowLayout());
		JLabel yearLabel = new JLabel("Year: ");
		yearPanel.add(yearLabel);
		yearField = new JTextField("Enter year here", TEXT_FIELD_SIZE);
		yearPanel.add(yearField);
		iPanel.add(yearPanel);

		// Grade input
		JPanel gradePanel = new JPanel();
		gradePanel.setLayout(new FlowLayout());
		JLabel gradeLabel = new JLabel("Grade: ");
		gradePanel.add(gradeLabel);
		gradeField = new JTextField("Enter grade here", TEXT_FIELD_SIZE);
		gradePanel.add(gradeField);
		iPanel.add(gradePanel);

		// Last Name input
		JPanel lastNamePanel = new JPanel();
		lastNamePanel.setLayout(new FlowLayout());
		JLabel lastNameLabel = new JLabel("Last Name: ");
		lastNamePanel.add(lastNameLabel);
		lastNameField = new JTextField("Enter last name here", TEXT_FIELD_SIZE);
		lastNamePanel.add(lastNameField);
		iPanel.add(lastNamePanel);

		// Supervisor input
		supervisorPanel = new JPanel();
		supervisorPanel.setLayout(new FlowLayout());
		supervisorPanel.setVisible(false);
		JLabel supervisorLabel = new JLabel("Supervisor: ");
		supervisorPanel.add(supervisorLabel);
		supervisorField = new JTextField("Enter supervisor here", TEXT_FIELD_SIZE);
		supervisorPanel.add(supervisorField);
		iPanel.add(supervisorPanel);

		// isPHD input
		isPHDPanel = new JPanel();
		isPHDPanel.setLayout(new FlowLayout());
		isPHDPanel.setVisible(false);
		JLabel isPHDLabel = new JLabel("PHD: ");
		isPHDPanel.add(isPHDLabel);
		isPHDField = new JTextField("Enter yes or no here", TEXT_FIELD_SIZE);
		isPHDPanel.add(isPHDField);
		iPanel.add(isPHDPanel);

		// Undergraduate school input
		schoolPanel = new JPanel();
		schoolPanel.setLayout(new FlowLayout());
		schoolPanel.setVisible(false);
		JLabel schoolLabel = new JLabel("Undergraduate School: ");
		schoolPanel.add(schoolLabel);
		schoolField = new JTextField("Enter school here", TEXT_FIELD_SIZE);
		schoolPanel.add(schoolField);
		iPanel.add(schoolPanel);

		// Messages
		JPanel mPanel = new JPanel(new BorderLayout());
		JLabel mLabel = new JLabel("Messages: ");
		mPanel.add(mLabel, BorderLayout.NORTH);
		mArea = new JTextArea(5, TEXT_AREA_SIZE);
		mArea.setEditable(false);
		JScrollPane s = new JScrollPane(mArea);
		mPanel.add(s, BorderLayout.CENTER);

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

		inputPanel.add(addButtonPanel, BorderLayout.EAST);
		inputPanel.add(mPanel, BorderLayout.SOUTH);
		inputPanel.add(iPanel, BorderLayout.CENTER);
		addPanel.add(inputPanel, BorderLayout.WEST);
		add(addPanel);

		// Averages Page
		averagesPanel = new JPanel();
		averagesPanel.setVisible(false);
		averagesPanel.setLayout(new BorderLayout());

		// Button
		JButton averagesButton = new JButton("Find");
		averagesButton.addActionListener(new averagesButtonListener());
		averagesPanel.add(averagesButton, BorderLayout.SOUTH);
		JPanel messagePanel = new JPanel(new BorderLayout());
		JLabel messageLabel = new JLabel("Messages: ");
		messagePanel.add(messageLabel, BorderLayout.NORTH);
		messageArea = new JTextArea(5, TEXT_AREA_SIZE - 20);
		messageArea.setEditable(false);
		JScrollPane scroll = new JScrollPane(messageArea);
		messagePanel.add(scroll, BorderLayout.CENTER);

		averagesPanel.add(messagePanel);
		add(averagesPanel);

		// Save Page
		savePanel = new JPanel();
		savePanel.setVisible(false);
		savePanel.setLayout(new BoxLayout(savePanel, BoxLayout.Y_AXIS));

		JPanel saveNamePanel = new JPanel();
		saveNamePanel.setLayout(new FlowLayout());
		JLabel saveNameLabel = new JLabel("File Name: ");
		saveNamePanel.add(saveNameLabel);
		saveNameField = new JTextField("Enter file name here", TEXT_FIELD_SIZE);
		saveNamePanel.add(saveNameField);
		savePanel.add(saveNamePanel);

		JButton saveButton = new JButton("Save");
		saveButton.addActionListener(new saveButtonListener());
		savePanel.add(saveButton);

		JPanel sMPanel = new JPanel(new BorderLayout());
		JLabel sMLabel = new JLabel("Messages: ");
		sMPanel.add(sMLabel, BorderLayout.NORTH);
		sMArea = new JTextArea(5, TEXT_AREA_SIZE - 20);
		sMArea.setEditable(false);
		JScrollPane scr = new JScrollPane(sMArea);
		sMPanel.add(scr, BorderLayout.CENTER);
		savePanel.add(sMPanel);

		add(savePanel);

		// Load Page
		loadPanel = new JPanel();
		loadPanel.setVisible(false);
		loadPanel.setLayout(new BoxLayout(loadPanel, BoxLayout.Y_AXIS));

		JPanel loadNamePanel = new JPanel();
		loadNamePanel.setLayout(new FlowLayout());
		JLabel loadNameLabel = new JLabel("File Name: ");
		loadNamePanel.add(loadNameLabel);
		loadNameField = new JTextField("Enter file name here", TEXT_FIELD_SIZE);
		loadNamePanel.add(loadNameField);
		loadPanel.add(loadNamePanel);

		JButton loadButton = new JButton("Load");
		loadButton.addActionListener(new loadButtonListener());
		loadPanel.add(loadButton);

		JPanel lMPanel = new JPanel(new BorderLayout());
		JLabel lMLabel = new JLabel("Messages: ");
		lMPanel.add(lMLabel, BorderLayout.NORTH);
		lMArea = new JTextArea(5, TEXT_AREA_SIZE - 20);
		lMArea.setEditable(false);
		JScrollPane sc = new JScrollPane(lMArea);
		lMPanel.add(sc, BorderLayout.CENTER);
		loadPanel.add(lMPanel);

		add(loadPanel);

		// Search Page
		searchPanel = new JPanel();
		searchPanel.setVisible(false);
		searchPanel.setLayout(new BorderLayout());

		JPanel sInputPanel = new JPanel();
		sInputPanel.setLayout(new BorderLayout());
		JLabel searchInputLabel = new JLabel("Searching students");
		sInputPanel.add(searchInputLabel, BorderLayout.NORTH);

		JPanel sIPanel = new JPanel();
		sIPanel.setLayout(new BoxLayout(sIPanel, BoxLayout.Y_AXIS));

		JPanel programSearchPanel = new JPanel();
		programSearchPanel.setLayout(new FlowLayout());
		JLabel programSearchLabel = new JLabel("Program: ");
		programSearchPanel.add(programSearchLabel);
		programSearchField = new JTextField("Enter Program here", TEXT_FIELD_SIZE);
		programSearchPanel.add(programSearchField);
		sIPanel.add(programSearchPanel);

		// Buttons
		JPanel searchButtonPanel = new JPanel();
		searchButtonPanel.setLayout(new GridLayout(10, 0, 0, 15));
		// Create and add empty panels to take up space
		searchButtonPanel.add(new JPanel());
		searchButtonPanel.add(new JPanel());
		searchButtonPanel.add(new JPanel());
		searchButtonPanel.add(new JPanel());
		JPanel rstPanel = new JPanel();
		rstPanel.setLayout(new BorderLayout());
		JButton rstButton = new JButton("Reset");
		rstButton.addActionListener(new resetButtonListener());
		rstPanel.add(rstButton, BorderLayout.CENTER);
		searchButtonPanel.add(rstPanel);
		JPanel search_button_panel = new JPanel();
		search_button_panel.setLayout(new BorderLayout());
		JButton searchButton = new JButton("Search Program");
		searchButton.addActionListener(new searchButtonListener());
		search_button_panel.add(searchButton, BorderLayout.CENTER);
		searchButtonPanel.add(search_button_panel);

		JPanel searchMPanel = new JPanel(new BorderLayout());
		JLabel searchMLabel = new JLabel("Messages: ");
		searchMPanel.add(searchMLabel, BorderLayout.NORTH);
		searchMArea = new JTextArea(5, TEXT_AREA_SIZE - 20);
		searchMArea.setEditable(false);
		JScrollPane scrll = new JScrollPane(searchMArea);
		searchMPanel.add(scrll, BorderLayout.SOUTH);
		
		searchPanel.add(sIPanel, BorderLayout.WEST);
		searchPanel.add(searchButtonPanel, BorderLayout.CENTER);
		searchPanel.add(searchMPanel, BorderLayout.SOUTH);
		add(searchPanel);
    }

    private class startPageListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			initialPanel.setVisible(true);
			addPanel.setVisible(false);
			searchPanel.setVisible(false);
			averagesPanel.setVisible(false);
			savePanel.setVisible(false);
			loadPanel.setVisible(false);
		}
	}

	private class addListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			addPanel.setVisible(true);
			initialPanel.setVisible(false);
			searchPanel.setVisible(false);
			averagesPanel.setVisible(false);
			savePanel.setVisible(false);
			loadPanel.setVisible(false);
		}
	}

	private class searchListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			searchPanel.setVisible(true);
			addPanel.setVisible(false);
			initialPanel.setVisible(false);
			averagesPanel.setVisible(false);
			savePanel.setVisible(false);
			loadPanel.setVisible(false);
		}
	}

	private class averagesListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			averagesPanel.setVisible(true);
			addPanel.setVisible(false);
			initialPanel.setVisible(false);
			savePanel.setVisible(false);
			loadPanel.setVisible(false);
			searchPanel.setVisible(false);
		}
	}

	private class saveListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			averagesPanel.setVisible(false);
			addPanel.setVisible(false);
			initialPanel.setVisible(false);
			savePanel.setVisible(true);
			loadPanel.setVisible(false);
			searchPanel.setVisible(false);
		}
	}

	private class loadListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			averagesPanel.setVisible(false);
			addPanel.setVisible(false);
			initialPanel.setVisible(false);
			savePanel.setVisible(false);
			loadPanel.setVisible(true);
			searchPanel.setVisible(false);
		}
	}

	private class typeComboListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			JComboBox cb = (JComboBox)e.getSource();
			String type = (String)cb.getSelectedItem();
			if(type.equals("Graduate Student"))
			{
				supervisorPanel.setVisible(true);
				isPHDPanel.setVisible(true);
				schoolPanel.setVisible(true);
			}
			else
			{
				supervisorPanel.setVisible(false);
				isPHDPanel.setVisible(false);
				schoolPanel.setVisible(false);
			}
		}
	}

	private class addButtonListener implements ActionListener
	{
			public void actionPerformed(ActionEvent event)
			{
				int year;
				double grade;
				try
				{
					year = Integer.parseInt(yearField.getText());
				}
				catch(Exception e)
				{
					mArea.append("There was an exception. Year must be of type integer.");
					return;
				}
				if(!gradeField.getText().isEmpty())
				{
					try
					{
						grade = Double.parseDouble(gradeField.getText());
					}
					catch(Exception e)
					{
						mArea.append("There was an exception. Grade must be of type double.");
						return;
					}
				}
				else
				{
					grade = 0.0;
				}
				if(supervisorPanel.isVisible()) // Graduate Student
				{
					if(isPHDField.getText().equalsIgnoreCase("yes"))
					{
						addStudent(programField.getText(), year, grade, lastNameField.getText(), supervisorField.getText(), true, schoolField.getText());
					}
					else
					{
						addStudent(programField.getText(), year, grade, lastNameField.getText(), supervisorField.getText(), false, schoolField.getText());
					}
				}
				else
				{
					addStudent(programField.getText(), year, grade, lastNameField.getText());
				}
			}
	}

	private class resetButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			programField.setText("");
			yearField.setText("");
			gradeField.setText("");
			lastNameField.setText("");
			supervisorField.setText("");
			isPHDField.setText("");
			schoolField.setText("");
			programSearchField.setText("");
		}
	}

	private class averagesButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			int i;
			double result = 0.0;
			for(i = 0; i < students.size(); i++)
			{
				result += students.get(i).getGrade();
			}
			result /= students.size(); // Divide the result by the number of students in the ArrayList
			messageArea.append("The class average is: " + result + "%\n" + "The total number of students is: " + students.size() + "\n");
		}
	}

	private class saveButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			try
			{
				PrintWriter fileWriter = new PrintWriter(saveNameField.getText(), "UTF-8");
				int i;
				for(i = 0; i < students.size(); i++)
				{
					fileWriter.println(students.get(i).toString());
				}
				fileWriter.print("\n");
				fileWriter.close();
			}
			catch(Exception e)
			{
				sMArea.append("Failed to save!\n");
				return;
			}
			sMArea.append("Save Successful!\n");
		}
	}

	private class loadButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			try
				{
					File f = new File(loadNameField.getText());
					Scanner load = new Scanner(f);
					String program = "";
					int year = 0;
					double avg_grade = 0.0;
					String lastName = "";
					String supervisor = "";
					boolean isPHD = false;
					String undergraduateSchool = "";
					boolean firstPass = true;
					while(load.hasNextLine())
					{
						String[] data = load.nextLine().split(": ");
						if(data[0].equals("Program"))
						{
							if(data.length == 2)
							{
								program = data[1];
							}
						}
						else if(data[0].equals("Year"))
						{
							if(data.length == 2)
							{
								try
								{
									year = Integer.parseInt(data[1]);
								}
								catch(Exception e)
								{
									lMArea.append("Invalid data. Year must be of type integer.\n");
								}
							}
						}
						else if(data[0].equals("Average Grade"))
						{
							if(data.length == 2)
							{
								try
								{
									avg_grade = Double.parseDouble(data[1]);
								}
								catch(Exception e)
								{
									lMArea.append("Invalid data. Grade must be of type double.\n");
								}
							}
						}
						else if(data[0].equals("Last Name"))
						{
							if(data.length == 2)
							{
								lastName = data[1];
							}
						}
						else if(data[0].equals("Supervisor"))
						{
							if(data.length == 2)
							{
								supervisor = data[1];
							}
						}
						else if(data[0].equals("PHD"))
						{
							if(data.length == 2)
							{
								if(data[1].equalsIgnoreCase("yes")) // Anything other than yes, results in isPHD being false.
								{
									isPHD = true;
								}
							}
						}
						else if(data[0].equals("Undergraduate School"))
						{
							if(data.length == 2)
							{
								undergraduateSchool = data[1];
							}
						}
						else // end of Student information
						{
							if(firstPass)
							{
								// Do Nothing
								// This just gets rid of the newline at the top of the save file
							}
							else if(program.isEmpty() || year == 0 || lastName.isEmpty())
							{
								lMArea.append("Program, year or last name not included in file, Student not added.\n");
							}
							else if(supervisor.isEmpty()) // not a graduate student
							{
								addStudent(program, year, avg_grade, lastName);
							}
							else // Graduate Student
							{
								addStudent(program, year, avg_grade, lastName, supervisor, isPHD, undergraduateSchool);
							}
							program = "";
							year = 0;
							avg_grade = 0.0;
							supervisor = "";
							isPHD = false;
							undergraduateSchool = "";
						}
						firstPass = false;
					}
				}
				catch(Exception e)
				{
					lMArea.append("Unable to load file!\n");
				}
				lMArea.append("Load Completed.\n");
		}
	}

	private class searchButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			boolean search = false;
			String[] program = programSearchField.getText().split(" ");
			if(program.length == 1)
			{
				int i;
				for(i = 0; i < students.size(); i++)
				{
					if(program[0].equals(students.get(i).getProgram()) || programSearchField.getText().isEmpty())
					{
						search = true;
						searchMArea.append(students.get(i).toString() + "\n");
					}
				}
				if(search == false)
				{
					searchMArea.append("No students found.\n");
					search = true;
				}
			}
			else
			{
				searchMArea.append("Please enter only a single word.\n");
			}
		}
	}

    public static void main(String[] args)
    {
    	boolean end = false;
    	classList list = new classList();
    	list.setVisible(true);
       /* Scanner keyboard = new Scanner(System.in);
		while(end == false)
		{
			System.out.println("\nWelcome to the main menu!\nPlease enter a number to choose one of the following options:\n\n (1) Enter information for a new student.\n (2) Enter information for a new graduate student.\n (3) Show all student information.\n (4) Print the average grades for the class as well as the total number of students.\n (5) Enter a specific program and show all student information for that program.\n (6) Load student information from an input file.\n (7) Save student information to an output file.\n (8) Lookup via hashmap with program, year and last name.\n (9) End program.\n");
			int answer = 0;
			if(keyboard.hasNextInt())
			{
				answer = keyboard.nextInt();
			}
			else 
			{
				end = true;
			}
			if(answer > 9 || answer < 1)
			{
				System.out.println("Error: Invalid input. You must enter a number from 1 to 8.\n");
			}
			keyboard.nextLine();
			if(answer == 1)
			{
				boolean added = false;
				while(added == false)
				{
					System.out.println("Enter the student's information: (<Program> <Year> <Grade> <Last Name>)");
					String line = keyboard.nextLine();
					String[] information = line.split(" ");
					if(information.length == 3)
					{
						try
						{
							int year = Integer.parseInt(information[1]);
							list.addStudent(information[0], year, information[2]);
							added = true;
						} 
						catch(Exception e)
						{
							System.out.println("Error: Invalid input. Please make sure the information is in order. (<Program> <Year> <Grade> <Last Name>)");
						}
					}
					else if(information.length == 4)
					{
						try
						{
							int year = Integer.parseInt(information[1]);
							double avg_grade = Double.parseDouble(information[2]);
							list.addStudent(information[0], year, avg_grade, information[3]);
							added = true;
						} 
						catch(Exception e)
						{
							System.out.println("Error: Invalid input. Please make sure the information is in order. (<Program> <Year> <Grade> <Last Name>)");
						}
					}
					else
					{
						System.out.println("Error: Invalid input. Please try again.");
					}
				}
			}
			else if(answer == 2)
			{
				boolean added = false;
				while(added == false)
				{
					System.out.println("Enter the student's information: (<Program> <Year> <Grade> <Last Name> <Supervisor> <PHD> <Undergraduate School>)");
					String line = keyboard.nextLine();
					String[] information = line.split(" ");
					if(information.length == 5)
					{
						try
						{
							int year = Integer.parseInt(information[1]);
							if(information[4].equalsIgnoreCase("yes"))
							{
								list.addStudent(information[0], year, information[2], information[3], true);
							}
							else
							{
								list.addStudent(information[0], year, information[2], information[3], false);
							}
							added = true;
						} 
						catch(Exception e)
						{
							System.out.println("Error: Invalid input. Please make sure the information is in order. (<Program> <Year> <Grade> <Last Name> <Supervisor> <PHD> <Undergraduate School>)");
						}
					}
					else if(information.length == 6)
					{
						try // Test if a double was entered for the average grade, if so, no undergraduate school was entered. 
						{
							double avg_grade = Double.parseDouble(information[2]);
							try
							{
								int year = Integer.parseInt(information[1]);
								if(information[5].equalsIgnoreCase("yes"))
								{
									list.addStudent(information[0], year, avg_grade, information[3], information[4], true);
								}
								else
								{
									list.addStudent(information[0], year, avg_grade, information[3], information[4], false);
								}
								added = true;
							} 
							catch(Exception e)
							{
								System.out.println("Error: Invalid input. Please make sure the information is in order. (<Program> <Year> <Grade> <Last Name> <Supervisor> <PHD> <Undergraduate School>)");
							}
						}
						catch(Exception e)
						{
							try
							{
								int year = Integer.parseInt(information[1]);
								if(information[4].equalsIgnoreCase("yes"))
								{
									list.addStudent(information[0], year, information[2], information[3], true, information[5]);
								}
								else
								{
									list.addStudent(information[0], year, information[2], information[3], false, information[5]);
								}
								added = true;
							} 
							catch(Exception ex)
							{
								System.out.println("Error: Invalid input. Please make sure the information is in order. (<Program> <Year> <Grade> <Supervisor> <PHD> <Undergraduate School>)");
							}
						}
						
					}
					else if(information.length == 7)
					{
						try
						{
							int year = Integer.parseInt(information[1]);
							double avg_grade = Double.parseDouble(information[2]);
							if(information[5].equalsIgnoreCase("yes"))
							{
								list.addStudent(information[0], year, avg_grade, information[3], information[4], true, information[6]);
							}
							else
							{
								list.addStudent(information[0], year, avg_grade, information[3], information[4], false, information[6]);
							}
							added = true;
						} 
						catch(Exception e)
						{
							System.out.println("Error: Invalid input. Please make sure the information is in order. (<Program> <Year> <Grade> <Supervisor> <PHD> <Undergraduate School>)");
						}
					}
					else
					{
						System.out.println("Error: Invalid input. Please try again.");
					}
				}
			}
			else if(answer == 3)
			{
				int i;
				for(i = 0; i < list.students.size(); i++)
				{
					System.out.println(list.students.get(i).toString());
				}
				if(list.students.size() == 0)
				{
					System.out.println("You have not entered any student information.");
				}
			}
			else if(answer == 4)
			{
				int i;
				double result = 0.0;
				for(i = 0; i < list.students.size(); i++)
				{
					result += list.students.get(i).getGrade();
				}
				result /= list.students.size(); // Divide the result by the number of students in the ArrayList
				System.out.println("The class average is: " + result + "%\n" + "The total number of students is: " + list.students.size());
			}
			else if(answer == 5)
			{
				boolean search = false;
				while(search == false)
				{
					System.out.println("Please enter the program:");
					String[] program = keyboard.nextLine().split(" ");
					if(program.length == 1)
					{
						int i;
						for(i = 0; i < list.students.size(); i++)
						{
							if(program[0].equals(list.students.get(i).getProgram()))
							{
								search = true;
								System.out.println(list.students.get(i).toString());
							}
						}
						if(search == false)
						{
							System.out.println("No students found.\n");
							search = true;
						}
					}
					else
					{
						System.out.println("Please enter only a single word");
					}
				}
			}
			else if(answer == 6)
			{
				System.out.println("Please enter the name of the input file: ");
				String input = keyboard.nextLine();
				// Load file
				try
				{
					File f = new File(input);
					Scanner load = new Scanner(f);
					String program = "";
					int year = 0;
					double avg_grade = 0.0;
					String lastName = "";
					String supervisor = "";
					boolean isPHD = false;
					String undergraduateSchool = "";
					boolean firstPass = true;
					while(load.hasNextLine())
					{
						String[] data = load.nextLine().split(": ");
						if(data[0].equals("Program"))
						{
							if(data.length == 2)
							{
								program = data[1];
							}
						}
						else if(data[0].equals("Year"))
						{
							if(data.length == 2)
							{
								try
								{
									year = Integer.parseInt(data[1]);
								}
								catch(Exception e)
								{
									System.out.println("Invalid data. Year must be of type integer.");
								}
							}
						}
						else if(data[0].equals("Average Grade"))
						{
							if(data.length == 2)
							{
								try
								{
									avg_grade = Double.parseDouble(data[1]);
								}
								catch(Exception e)
								{
									System.out.println("Invalid data. Grade must be of type double.");
								}
							}
						}
						else if(data[0].equals("Last Name"))
						{
							if(data.length == 2)
							{
								lastName = data[1];
							}
						}
						else if(data[0].equals("Supervisor"))
						{
							if(data.length == 2)
							{
								supervisor = data[1];
							}
						}
						else if(data[0].equals("PHD"))
						{
							if(data.length == 2)
							{
								if(data[1].equalsIgnoreCase("yes")) // Anything other than yes, results in isPHD being false.
								{
									isPHD = true;
								}
							}
						}
						else if(data[0].equals("Undergraduate School"))
						{
							if(data.length == 2)
							{
								undergraduateSchool = data[1];
							}
						}
						else // end of Student information
						{
							if(firstPass)
							{
								// Do Nothing
								// This just gets rid of the newline at the top of the save file
							}
							else if(program.isEmpty() || year == 0 || lastName.isEmpty())
							{
								System.out.println("Program, year or last name not included in file, Student not added.");
							}
							else if(supervisor.isEmpty()) // not a graduate student
							{
								list.addStudent(program, year, avg_grade, lastName);
							}
							else // Graduate Student
							{
								list.addStudent(program, year, avg_grade, lastName, supervisor, isPHD, undergraduateSchool);
							}
							program = "";
							year = 0;
							avg_grade = 0.0;
							supervisor = "";
							isPHD = false;
							undergraduateSchool = "";
						}
						firstPass = false;
					}
				}
				catch(Exception e)
				{
					System.out.println("Unable to load file!");
				}
			}
			else if(answer == 7)
			{
				System.out.println("Please enter the name of the output file: ");
				String output = keyboard.nextLine();
				// Save file
				try
				{
					PrintWriter fileWriter = new PrintWriter(output, "UTF-8");
					int i;
					for(i = 0; i < list.students.size(); i++)
					{
						fileWriter.println(list.students.get(i).toString());
					}
					fileWriter.print("\n");
					fileWriter.close();
				}
				catch(Exception e)
				{
					System.out.println("Failed to save!");
				}
			}
			else if(answer == 8)
			{
				System.out.println("Please enter the program, year, and last name: ");
				String[] input = keyboard.nextLine().split(" ");
				if(input.length == 3)
				{
					try
					{
						Integer.parseInt(input[1]);
						String key = input[0].concat(input[1]).concat(input[2]).toLowerCase();
						if(list.hashmap.get(key) != null)
						{
							System.out.println(list.hashmap.get(key).toString());
						}
						else
						{
							System.out.println("Student not found.");
						}
					}
					catch(Exception e)
					{
						System.out.println("The year must be of type integer.");
					}
				}
				else
				{
					System.out.println("You must enter the program, year and last name.");
				}
			}
			else if(answer == 9)
			{
				end = true;
			}
		}*/	
    }

    public void addStudent(String program, int year, String lastName)
    {
    	try
    	{
    		Student new_student = new Student(program, year, lastName);
    		if(hashmap.get(new_student.getKey()) != null)
    		{
    			throw new Exception("Duplicate student found.");
    		}
	    	hashmap.put(new_student.getKey(), new_student);
			students.add(new_student);
			mArea.append("Student added!\n");
    	}
		catch(Exception e)
		{
			mArea.append("Error: " + e.getMessage() + " Student was not added.\n");
		}
    }
    public void addStudent(String program, int year, double avg_grade, String lastName)
    {
    	try
    	{
    		Student new_student = new Student(program, year, avg_grade, lastName);
    		if(hashmap.get(new_student.getKey()) != null)
    		{
    			throw new Exception("Duplicate student found.");
    		}
    		hashmap.put(new_student.getKey(), new_student);
			students.add(new_student);
			mArea.append("Student added!\n");
    	}
		catch(Exception e)
		{
			mArea.append("Error: " + e.getMessage() + " Student was not added.\n");
		}
    }
    public void addStudent(String program, int year, String lastName, String supervisor, boolean isPHD, String undergraduateSchool)
    {
    	try
    	{
    		GraduateStudent new_student = new GraduateStudent(program, year, lastName, supervisor, isPHD, undergraduateSchool);
    		if(hashmap.get(new_student.getKey()) != null)
    		{
    			throw new Exception("Duplicate student found.");
    		}
    		hashmap.put(new_student.getKey(), new_student);
			students.add(new_student);
			mArea.append("Graduate Student added!\n");
    	}
		catch(Exception e)
		{
			mArea.append("Error: " + e.getMessage() + " Student was not added.\n");
		}
    }
    public void addStudent(String program, int year, String lastName, String supervisor, boolean isPHD)
    {
    	try
    	{
    		GraduateStudent new_student = new GraduateStudent(program, year, lastName, supervisor, isPHD);
    		if(hashmap.get(new_student.getKey()) != null)
    		{
    			throw new Exception("Duplicate student found.");
    		}
    		hashmap.put(new_student.getKey(), new_student);
			students.add(new_student);
			mArea.append("Graduate Student added!\n");
    	}
		catch(Exception e)
		{
			mArea.append("Error: " + e.getMessage() + " Student was not added.\n");
		}
    }
    public void addStudent(String program, int year, double avg_grade, String lastName, String supervisor, boolean isPHD, String undergraduateSchool)
    {
    	try
    	{
    		GraduateStudent new_student = new GraduateStudent(program, year, avg_grade, lastName, supervisor, isPHD, undergraduateSchool);
    		if(hashmap.get(new_student.getKey()) != null)
    		{
    			throw new Exception("Duplicate student found.");
    		}
    		hashmap.put(new_student.getKey(), new_student);
			students.add(new_student);
			mArea.append("Graduate Student added!\n");
    	}
		catch(Exception e)
		{
			mArea.append("Error: " + e.getMessage() + " Student was not added.\n");
		}
    }
    public void addStudent(String program, int year, double avg_grade, String lastName, String supervisor, boolean isPHD)
    {
    	try
    	{
    		GraduateStudent new_student = new GraduateStudent(program, year, avg_grade, lastName, supervisor, isPHD);
    		if(hashmap.get(new_student.getKey()) != null)
	    	{
    			throw new Exception("Duplicate student found.");
    		}
   	 		hashmap.put(new_student.getKey(), new_student);
			students.add(new_student);
			mArea.append("Graduate Student added!\n");
    	}
		catch(Exception e)
		{
			mArea.append("Error: " + e.getMessage() + " Student was not added.\n");
		}
    }
}