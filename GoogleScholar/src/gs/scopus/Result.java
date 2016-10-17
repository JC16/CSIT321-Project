package gs.scopus;

/**
 * 
 * A result class
 * 
 * It will save the result from Scopus
 * 
 * @author Hang Zhang
 * 
 * */
public class Result {
	private String title;
	private String eid;
	private String author;
	private String year;
	private String url;
	
	
	
	
	/**
	 * 
	 * The constructor of the class
	 * 
	 * */
	public Result(String t, String e, String au, String y, String u)
	{
		this.title = t;
		this.eid = e;
		this.author = au;
		this.year = y;
		this.url = u;
	}
	
	/**
	 * 
	 * The print function
	 * 
	 * */
	public void print()
	{
		System.out.println("---------------------------------------");
		System.out.println("Title: " + this.title);
		System.out.println("Author: " + this.author);
		System.out.println("Year: " + this.year);
		System.out.println("Url: " + this.url);
		System.out.println("Eid: " + this.eid);
	}
	
	
	/**
	 * 
	 * The accessor and mutator for the class
	 * 
	 * 
	 * */
	public String GetTitle()
	{
		return this.title;
	}
	
	public String GetEid()
	{
		return this.eid;
	}
	
	public String GetAuthor()
	{
		return this.author;
	}
	
	public String GetYear()
	{
		if (this.year.length() > 4)
			return this.year.substring(0, 4);
		else
			return year;
	}
	
	public String GetUrl()
	{
		return this.url;
	}
}
