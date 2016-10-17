package gs.scopus;

/**
 * 
 * The page is for Scopus search
 * 
 * This is the class to hellp 
 * 
 * @author Hang Zhang
 * 
 * 
 * */

import java.util.ArrayList;

//http://api.elsevier.com/content/abstract/eid/2-s2.0-78649335221?apiKey=f2f228c33b5a670222a5562b44c9ff35&view=FULL&httpAccept=application/xml
//http://api.elsevier.com/content//search/index:SCOPUS?query=TITLE("Studies on heart lymph")&apiKey=83aa66cba1a30f9de1d570ba4695fb84&view=COMPLETE&httpAccept=application/xml
//f2f228c33b5a670222a5562b44c9ff35

public class performCheck {

	public static void main(String[] args) throws Exception {
		
		long start=System.currentTimeMillis();
		
		SearchArticle a = new SearchArticle();
		
		//Set configs
		a.SetApi("12073f3b09b9676bde9e2d7cff098aa0");
		a.SetKeyWord("Heart");
		//a.SetMAX(10000);Set the number of results
		
		//Do Search function
		//a.Search();
		//Write Results to File
		a.WriteToExcel();
		//a.RemoveFile(); Remove all XML file
		System.out.println("Finished");
		long end=System.currentTimeMillis();
		System.out.println("Total time"+(end-start)+"ms");
		
	}
}