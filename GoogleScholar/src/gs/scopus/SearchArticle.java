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
	
	public void SetMAX(int i)
	{
		this.MAX = i;
	}
	
	public void SetApi(String api) throws UnsupportedEncodingException
	{
		this.ApiKey = URLEncoder.encode(api, "utf-8");
	}
	
	public void SetKeyWord(String Key) throws UnsupportedEncodingException
	{
		this.Title = Key;
		this.KeyWord = URLEncoder.encode(Key, "utf-8");
	}
	
	public int GetResultsNum()
	{
		return this.results_num;
	}
	
	private void CombineUrl( int order)
	{
		String startCurr= "&start=" + Integer.toString(order * 25);
		this.url = source + FirstPartQuery + KeyWord + SecondPartQuery + ApiKey + startCurr + AcceptForm;
	}
	
	public void Search() throws Exception
	{
		this.SearchOnce(0);
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
			A athread = new A(i);
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
		public A (int i)
		{
			this.i = i;
		}
		
		public void run()
		{
			try 
			{
				//System.out.println(Integer.toString(i*25));
				if (SearchOnce(i))
					AnalyseResults(i);
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	};
	
	
	public boolean SearchOnce(int order) throws Exception
	{
		boolean rev = false;
		
		this.CombineUrl(order);
		
		
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
