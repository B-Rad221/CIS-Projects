package ClassList;
import java.util.Scanner;
import java.util.ArrayList;

public class ClassList 
{

    public static void main(String[] args) 
    {
    	boolean end = false;
		ArrayList<Student> students = new ArrayList<Student>();
        Scanner keyboard = new Scanner(System.in);
		while(end == false)
		{
			System.out.println("Welcome to the main menu!\nPlease enter a number to choose one of the following options:\n\n (1) Enter information for a new student.\n (2) Show all student information.\n (3) Print the average grades for the class as well as the total number of students.\n (4) Enter a specific program and show all student information for that program.\n (5) End program.\n");
			int answer = 0;
			if(keyboard.hasNextInt())
			{
				answer = keyboard.nextInt();
			}
			else 
			{
				end = true;
			}
			if(answer > 5 || answer < 1)
			{
				System.out.println("Error: Invalid input. You must enter a number from 1 to 5.\n");
			}
			keyboard.nextLine();
			if(answer == 1)
			{
				boolean added = false;
				while(added == false)
				{
					System.out.println("Enter the student's information: ");
					String line = keyboard.nextLine();
					String[] information = line.split(" ");
					if(information.length == 2)
					{
						try
						{
							int year = Integer.parseInt(information[1]);
							Student new_student = new Student(information[0], year);
							students.add(new_student);
							System.out.println("Student added!\n");
							added = true;
						} 
						catch(Exception e)
						{
							System.out.println("Error: Invalid input. Please make sure the information is in order. (<Program> <Year> <Grade>)");
						}
					}
					else if(information.length == 3)
					{
						try
						{
							int year = Integer.parseInt(information[1]);
							double avg_grade = Double.parseDouble(information[2]);
							Student new_student = new Student(information[0], year, avg_grade);
							students.add(new_student);
							System.out.println("Student added!\n");
							added = true;
						} 
						catch(Exception e)
						{
							System.out.println("Error: Invalid input. Please make sure the information is in order. (<Program> <Year> <Grade>)");
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
				int i;
				for(i = 0; i < students.size(); i++)
				{
					System.out.println("Program: " + students.get(i).getProgram() + "\n Year: " + students.get(i).getYear() + "\n Average Grade: " + students.get(i).getGrade() + "\n");
				}
				if(students.size() == 0)
				{
					System.out.println("You have not entered any student information.");
				}
			}
			else if(answer == 3)
			{
				int i;
				double result = 0.0;
				for(i = 0; i < students.size(); i++)
				{
					result += students.get(i).getGrade();
				}
				result /= students.size(); // Divide the result by the number of students in the ArrayList
				System.out.println("The class average is: " + result + "%\n" + "The total number of students is: " + students.size() + "\n");
			}
			else if(answer == 4)
			{
				boolean search = false;
				while(search == false)
				{
					System.out.println("Please enter the program:");
					String[] program = keyboard.nextLine().split(" ");
					if(program.length == 1)
					{
						int i;
						for(i = 0; i < students.size(); i++)
						{
							if(program[0].equals(students.get(i).getProgram()))
							{
								search = true;
								System.out.println("Program: " + students.get(i).getProgram() + "\n Year: " + students.get(i).getYear() + "\n Average Grade: " + students.get(i).getGrade() + "\n");
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
			else if(answer == 5)
			{
				end = true;
			}
		}
    }
}
