package classlist;

public class GraduateStudent extends Student
{
	private String supervisor;
	private boolean isPHD;
	private String undergraduateSchool;

	public GraduateStudent(String program, int year, String lastName, String supervisor, boolean isPHD, String undergraduateSchool) throws Exception
	{
		super(program, year, lastName);
		if(supervisor.isEmpty())
		{
			throw new Exception("Supervisor was not specified");
		}
		this.supervisor = supervisor;
		this.isPHD = isPHD;
		this.undergraduateSchool = undergraduateSchool;
	}
	public GraduateStudent(String program, int year, String lastName, String supervisor, boolean isPHD) throws Exception
	{
		super(program, year, lastName);
		if(supervisor.isEmpty())
		{
			throw new Exception("Supervisor was not specified");
		}
		this.supervisor = supervisor;
		this.isPHD = isPHD;
		this.undergraduateSchool = "";
	}
	public GraduateStudent(String program, int year, double avg_grade, String lastName, String supervisor, boolean isPHD, String undergraduateSchool) throws Exception
	{
		super(program, year, avg_grade, lastName);
		if(supervisor.isEmpty())
		{
			throw new Exception("Supervisor was not specified");
		}
		this.supervisor = supervisor;
		this.isPHD = isPHD;
		this.undergraduateSchool = undergraduateSchool;
	}
	public GraduateStudent(String program, int year, double avg_grade, String lastName, String supervisor, boolean isPHD) throws Exception
	{
		super(program, year, avg_grade, lastName);
		if(supervisor.isEmpty())
		{
			throw new Exception("Supervisor was not specified");
		}
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