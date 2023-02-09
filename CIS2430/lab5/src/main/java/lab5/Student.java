package classlist;

public class Student
{
	private String program;
	private int year;
	private double avg_grade;
	private String lastName;

	public Student(String program, int year, String lastName) throws Exception
	{
		if(program.isEmpty() || year < 0 || lastName.isEmpty())
		{
			throw new Exception("Student data is invalid.");
		}
		this.program = program;
		this.year = year;
		this.avg_grade = 0.0;
		this.lastName = lastName;
	}
	public Student(String program, int year, double avg_grade, String lastName) throws Exception
	{
		if(program.isEmpty() || year < 0 || avg_grade < 0 || avg_grade > 100 || lastName.isEmpty())
		{
			throw new Exception("Student data is invalid.");
		}
		this.program = program;
		this.year = year;
		this.avg_grade = avg_grade;
		this.lastName = lastName;
	}

	public String getProgram()
	{
		return this.program;
	}
	public int getYear()
	{
		return this.year;
	}
	public double getGrade()
	{
		return this.avg_grade;
	}
	public String getLastName()
	{
		return this.lastName;
	}

	public String toString()
	{
		String yr = Integer.toString(year);
		String grade = Double.toString(avg_grade);
		return("\nProgram: " + program + "\nYear: " + yr + "\nAverage Grade: " + grade + "\nLast Name: " + lastName);
	}

	public String getKey()
	{
		return(program.concat(Integer.toString(year)).concat(lastName).toLowerCase());
	}
}
