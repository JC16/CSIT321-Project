package gs.scopus;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

//83aa66cba1a30f9de1d570ba4695fb84
//http://api.elsevier.com/content/search/scopus?query=TITLE("heart")&apiKey=83aa66cba1a30f9de1d570ba4695fb84&view=FULL&httpAccept=application/xml
//http://api.elsevier.com/content/search/scopus?query=TITLE("heart")&apiKey=3440376615883768a3aa21e106fd5096&view=FULL&httpAccept=application/xml

//http://api.elsevier.com/content/abstract/eid/2-s2.0-0031737331?apiKey=f2f228c33b5a670222a5562b44c9ff35&view=FULL&httpAccept=application/xml
//http://api.elsevier.com/content/search/index:SCOPUS?query=TITLE(%22Horizon%202000:%20economic%20viability%20and%20political%20realities%22)&apiKey=43f81841d27681e01e22a01525e5ea90&view=COMPLETE&httpAccept=application/xml


public class SearchArticle{
	private String KeyWord;
	private String ApiKey;
	private String Title;
	private final String source = "http://api.elsevier.com/content/search/scopus?";
	private final String FirstPartQuery = "query=ALL(\"";
	private final String SecondPartQuery = "\")&apiKey=";
	private final String AcceptForm = "&httpAccept=application/xml";
	private String url;
	private int results_num = 0;
	private ArrayList<Result> results;
	private String eid;
	private int status = 0;
	private int total_results = 0;
	private int MAX = 5000;
	private int count = 0;
	private String author;
	private String year;
	private String type;
	
	
	/**
	 * 
	 * The default constructor for the scopus search
	 * 
	 * */
	
	public SearchArticle()
	{
		this.KeyWord = "";
		this.ApiKey = "";
		this.Title = "";
		this.url="";
		this.results = new ArrayList<Result> ();
		File dir = new File("./SearchResult");
		dir.mkdir();
	}
	
	
	/**
	 * 
	 * Set the maximum search reuslt of scopus
	 * Input int
	 * 
	 * */
	public void SetMAX(int i)
	{
		this.MAX = i;
	}
	
	/**
	 * 
	 * Set the api key for scopus
	 * Input String
	 * 
	 * 
	 * */
	public void SetApi(String api) throws UnsupportedEncodingException
	{
		this.ApiKey = URLEncoder.encode(api, "utf-8");
	}
	
	/**
	 * 
	 * Set the key word for search
	 * 
	 * Input String
	 * 
	 * */
	public void SetKeyWord(String Key) throws UnsupportedEncodingException
	{
		this.Title = Key;
		this.KeyWord = URLEncoder.encode(Key, "utf-8");
	}
	
	/**
	 * 
	 * Return the number of results
	 * 
	 * */
	public int GetResultsNum()
	{
		return this.results_num;
	}
	
	/*
	 Mode & Usage:
	 0: Search all for Keyword
	 	a.SetKeyWord("heart");
	 	a.Search(0);
	 1: Search title for Keyword
	 	a.SetKeyWord("heart");
	 	a.Search(1); 
	 2: Search author name for Keyword
	 	a.SetKeyWord("scott");
	 	a.Search(2);
	 3: Search ISSN for Keyword
	 	a.SetKeyWord("123");
	 	a.Search(3);
	 4: Search title for Keyword and only return the article whose publication year is after specific year
	 	a.SetKeyWord("heart");
	 	a.SetYear(2009);
	 	a.Search(4);
	 5: Search DOI for Keyword
	 	a.SetKeyWord("s11-/2007.sa");
	 	a.Search(5);
	 6: Search title for Keyword and only return the article whose author name satisfy the required author
	 	a.SetKeyWord("heart");
	 	a.SetAuthor("scott");
	 	a.Search(6);
	 7: Search title for Keyword and only return the article with specific type
	 	a.SetKeyWord("heart");
	 	a.SetType("j");
	 	a.Search(7);
	 		Type of results:
				j Journal
				b Book
				k Book Series
				p Conference Proceeding
				r Report
				d Trade Publication
				
	 8: Search Publisher for Keyword
	 	a.SetKeyWord("pacific");
	 	a.Search(8);
	 
	 */
	
	private void CombineUrl( int order, int mode)
	{
		String startCurr= "&start=" + Integer.toString(order * 25);
		if (mode == 0)
		{
			this.url = source + FirstPartQuery + KeyWord + SecondPartQuery + ApiKey + startCurr + AcceptForm;
		}
		else if (mode == 1)
		{
			this.url = source + "query=TITLE(\"" + KeyWord + SecondPartQuery + ApiKey + startCurr + AcceptForm;
		}
		else if (mode == 2)
		{
			this.url = source + "query=AUTHOR-NAME(" + KeyWord + ")&apiKey=" + ApiKey + startCurr + AcceptForm;
		}
		else if (mode == 3)
		{
			this.url = source + "query=ISSN(" + KeyWord + ")&apiKey=" + ApiKey + startCurr + AcceptForm;
		}
		else if (mode == 4)
		{
			this.url = source + "query=TITLE(\"" + KeyWord + "\")and(aft," + this.year + ")&apiKey=" + ApiKey + startCurr + AcceptForm;
		}
		else if (mode == 5)
		{
			this.url = source + "query=DOI(" + KeyWord + ")&apiKey=" + ApiKey + startCurr + AcceptForm;
		}
		else if (mode == 6)
		{
			this.url = source + "query=TITLE(\"" + KeyWord + "\")\"and\"AUTHOR-NAME(" + this.author + ")&apiKey=" + ApiKey + startCurr + AcceptForm;
		}
		else if (mode == 7)
		{
			this.url = source + "query=TITLE(\"" + KeyWord + "\")\"and\"SRCTYPE(" + this.type + ")&apiKey=" + ApiKey + startCurr + AcceptForm;
		}
		else if (mode == 8)
		{
			this.url = source + "query=SRCTITLE(" + KeyWord + ")&apiKey=" + ApiKey + startCurr + AcceptForm;
		}
		
	}
	
	public void SetAuthor(String authorname)
	{
		this.author = authorname;
	}
	
	public void SetYear(String year)
	{
		this.year = year;
	}
	
	public void SetType(String type)
	{
		this.type = type;
	}
	
	public void Search(int mode) throws Exception
	{
		this.SearchOnce(0, mode);
		this.FindResultsNum();
		
		if (this.total_results > MAX)
			count = MAX/25;
		else if (this.total_results >= 25)
			count = this.total_results / 25;
		else
			count = 1;
		
		ArrayList<A> THREADS = new ArrayList<A>();
		
		for (int i = 0; i < count; i++)
		{
			A athread = new A(i, mode);
			athread.start();
			THREADS.add(athread);
		}
		
		for (int i = 0; i < THREADS.size(); i++)
		{
			THREADS.get(i).join();
		}
		
		
		/*
		for (int i = 0; i < count; i++)
		{
			System.out.println(Integer.toString(i*25));
			this.SearchOnce(i);	
			this.AnalyseResults(i);
		}*/
		
	}
	
	class A extends Thread {
		private int i;
		private int mode;
		public A (int i, int mode)
		{
			this.i = i;
			this.mode = mode;
		}
		
		public void run()
		{
			try 
			{
				//System.out.println(Integer.toString(i*25));
				if (SearchOnce(i, mode))
					AnalyseResults(i);
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	};
	
	
	public boolean SearchOnce(int order, int mode) throws Exception
	{
		boolean rev = false;
		
		this.CombineUrl(order, mode);
		
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		con.setRequestMethod("GET");

		
		int responseCode = con.getResponseCode();
		
		if (responseCode != 200)
		{
			//System.out.println("Cannot search. Error Code: " + responseCode);
			this.status = responseCode;
			return rev;
		}
		else
			rev = true;

		this.status = responseCode;
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		String filename = this.FilenameCombiner(order);
		FileWriter out = null;
		out = new FileWriter (filename);
		out.write(response.toString());
		out.close();
		
		return rev;
	}
	
	private String FilenameCombiner(int i)
	{
		
		StringBuilder rev = new StringBuilder();
		rev.append("./SearchResult/SearchResults");
		rev.append(i);
		rev.append(".xml");
		return rev.toString();
	}
	
	public void FindResultsNum() throws Exception
	{
		try {
		String filename = this.FilenameCombiner(0);
		File inputFile = new File(filename);
        DocumentBuilderFactory dbFactory 
           = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("search-results");
        Node nNode = nList.item(0);
        
        if (nNode.getNodeType() == Node.ELEMENT_NODE)
    	{
    		Element eElement = (Element) nNode;
    		this.total_results = Integer.parseInt(eElement.getElementsByTagName("opensearch:totalResults").item(0).getTextContent());
    	}
		}
		catch (Exception e)
		{
			
		}
        
	}

	public void AnalyseResults(int order) throws Exception
	{
		try {
		
		String filename = this.FilenameCombiner(order);
		File inputFile = new File(filename);
        DocumentBuilderFactory dbFactory 
           = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        
        //System.out.println(filename);
        
        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();

        NodeList nList = doc.getElementsByTagName("entry");
        
        
        this.results_num = nList.getLength();
        
        
        for (int i = 0; i < nList.getLength(); i++)
        {
        	Node nNode = nList.item(i);
        	
        	//System.out.println("     :" + i);
            
        	if (nNode.getNodeType() == Node.ELEMENT_NODE)
        	{
        		Element eElement = (Element) nNode;
        		NodeList tempList;
        		String tempTitle, tempEid, tempAuthor, tempDate, tempUrl;
        		
  		
        		tempList = eElement.getElementsByTagName("dc:title");
        		if ( tempList != null && tempList.getLength() == 0)
        			tempTitle = "";
        		else
        			tempTitle = eElement.getElementsByTagName("dc:title").item(0).getTextContent();
        			
        		
        		tempList = eElement.getElementsByTagName("eid");
        		if ( tempList != null && tempList.getLength() == 0)
        			tempEid = "";
        		else
        			tempEid = eElement.getElementsByTagName("eid").item(0).getTextContent();
        		
        		tempList = eElement.getElementsByTagName("dc:creator");
        		if ( tempList != null && tempList.getLength() == 0)
        			tempAuthor = "";
        		else
        			tempAuthor = eElement.getElementsByTagName("dc:creator").item(0).getTextContent();
        			
        		
        		tempList = eElement.getElementsByTagName("prism:coverDate");
        		if ( tempList != null && tempList.getLength() == 0)
        			tempDate = "";
        		else
        			tempDate = eElement.getElementsByTagName("prism:coverDate").item(0).getTextContent();
        			
        		
        		tempList = eElement.getElementsByTagName("link");
        		if ( tempList != null && tempList.getLength() == 0)
        			tempUrl = "";
        		else
        				tempUrl = eElement.getElementsByTagName("link").item(2).getAttributes().getNamedItem("href").getNodeValue();
        				
        		
        		Result temp = new Result (tempTitle, tempEid, tempAuthor, tempDate, tempUrl);
        		results.add(temp);
        		//System.out.println(tempTitle + " Added!");
        	}
        }
        
		}
		catch (Exception e)
		{
			//e.printStackTrace();
		}

	}
	
	public ArrayList<Result> GetResults()
	{
		return this.results;
	}
	
	public void PrintResults()
	{
		/*
		for (int i = 0; i < this.results_num; i++)
		{
			System.out.println(i + ": ");
			Result temp = this.results.get(i);
			System.out.println("	Title: " + temp.GetTitle());
			System.out.println("	Eid: " + temp.GetEid());
		}*/
		for (Result x : this.results)
			x.print();
	}
	
	public boolean IfFound()
	{
		boolean rev = false;
		
		for (int i = 0; i < this.results_num; i++)
		{
			Result temp = this.results.get(i);
			if (temp.GetTitle().equals(this.Title))
			{
				this.eid = new String(); 
				this.eid= temp.GetEid();
				return true;
			}
		}
		
		return rev;
		
	}
	
	public String GetEid()
	{
		return this.eid;
	}
	
	public int GetStatus()
	{
		return this.status;
	}
	
	public void WriteToExcel() throws Exception
	{
		FileWriter out = null;
		out = new FileWriter ("Report.csv");
				
		out.write("Year" + "," + "Author" + "," + "Title" + "," + "Url" + "\n");
		
		for (Result x : this.results)
		{
			out.write("\"" + x.GetYear() + "\"" + ",");
			out.write("\"" + x.GetAuthor() + "\"" + ",");
			out.write("\"" + x.GetTitle() + "\"" + ",");
			out.write(x.GetUrl() + "\n");
		}
		
		out.close();

	}
	
	
    private boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }
    
    public void RemoveFile()
    {
    	File afile = new File("SearchResult");
    	
    	 if (deleteDir(afile))
    		 System.out.println("Succeed");
    }
    
    
}
