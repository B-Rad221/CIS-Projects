package classlist;
import java.util.*;
import java.io.*;

public class classList 
{
    private ArrayList<Student> students = new ArrayList<Student>();
    private HashMap<String, Student> hashmap = new HashMap<String, Student>();

    public static void main(String[] args)
    {
    	boolean end = false;
    	classList list = new classList();
        Scanner keyboard = new Scanner(System.in);
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
		}	
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
			System.out.println("Student added!\n");
    	}
		catch(Exception e)
		{
			System.out.println("Error: " + e.getMessage() + " Student was not added.");
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
			System.out.println("Student added!\n");
    	}
		catch(Exception e)
		{
			System.out.println("Error: " + e.getMessage() + " Student was not added.");
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
			System.out.println("Graduate Student added!\n");
    	}
		catch(Exception e)
		{
			System.out.println("Error: " + e.getMessage() + " Student was not added.");
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
			System.out.println("Graduate Student added!\n");
    	}
		catch(Exception e)
		{
			System.out.println("Error: " + e.getMessage() + " Student was not added.");
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
			System.out.println("Graduate Student added!\n");
    	}
		catch(Exception e)
		{
			System.out.println("Error: " + e.getMessage() + " Student was not added.");
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
			System.out.println("Graduate Student added!\n");
    	}
		catch(Exception e)
		{
			System.out.println("Error: " + e.getMessage() + " Student was not added.");
		}
    }
}