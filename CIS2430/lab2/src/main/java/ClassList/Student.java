package ClassList;
import java.util.Scanner;

public class Student
{
	private String program;
	private int year;
	private double avg_grade;

	public Student(String program, int year)
	{
		this.program = program;
		this.year = year;
		this.avg_grade = 0.0;
	}
	public Student(String program, int year, double avg_grade)
	{
		this.program = program;
		this.year = year;
		this.avg_grade = avg_grade;
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
}