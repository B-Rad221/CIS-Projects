package ClassList;

public class GraduateStudent extends Student
{
	private String supervisor;
	private boolean isPHD;
	private String undergraduateSchool;

	public GraduateStudent(String program, int year, String supervisor, boolean isPHD, String undergraduateSchool)
	{
		super(program, year);
		this.supervisor = supervisor;
		this.isPHD = isPHD;
		this.undergraduateSchool = undergraduateSchool;
	}
	public GraduateStudent(String program, int year, String supervisor, boolean isPHD)
	{
		super(program, year);
		this.supervisor = supervisor;
		this.isPHD = isPHD;
		this.undergraduateSchool = "";
	}
	public GraduateStudent(String program, int year, double avg_grade, String supervisor, boolean isPHD, String undergraduateSchool)
	{
		super(program, year, avg_grade);
		this.supervisor = supervisor;
		this.isPHD = isPHD;
		this.undergraduateSchool = undergraduateSchool;
	}
	public GraduateStudent(String program, int year, double avg_grade, String supervisor, boolean isPHD)
	{
		super(program, year, avg_grade);
		this.supervisor = supervisor;
		this.isPHD = isPHD;
		this.undergraduateSchool = "";
	}

	public String toString()
	{
		String student = super.toString();
		if(isPHD)
		{
			return(student + "\nSupervisor: " + supervisor + "\nPHD: YES\nUndergraduate School: " + undergraduateSchool);
		}
		else
		{
			return(student + "\nSupervisor: " + supervisor + "\nPHD: NO\nUndergraduate School: " + undergraduateSchool);
		}
	}

}