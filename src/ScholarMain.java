/**
 * Created by leon on 6/04/16.
 */
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.lang.String;
import java.io.IOException;

public class ScholarMain{
    private static String[] bookinfo = new String[20];
    private static String[] pubinfo = new String[20];
    private static String[] numinfo = new String[40];
    private static int count = 0;
public static void main(String args[]){
    try {
        for (int j =0; j < 100; j++){
            int upages = j*20;
        String abc = "https://scholar.google.com.au/citations?hl=en&vq=en&view_op=list_hcore&venue=1Cv1bkrS5GIJ.2015&cstart=0&google_abuse=GOOGLE_ABUSE_EXEMPTION%3DID%3D30f5c27a4e262a68:TM%3D1463625946:C%3Dc:IP%3D203.10.91.73-:S%3DAPGng0sL8OtvsVk_YwG66IYzV4fmvMNGoA%3B+path%3D/%3B+domain%3Dgoogle.com%3B+expires%3DThu,+19-May-2016+05:45:46+GMT";
        Document doc = Jsoup.connect(abc+"&cstart="+upages).get();
        String title = doc.title();

        System.out.println(title);
        Elements gstitle = doc.getElementsByTag("span");
        for (Element link:gstitle){
            String url = link.getElementsByClass("gs_authors").text();
            if (url.length()!=0) {
                bookinfo[count] = url;
                count++;
            }
        }
        count=0;
        for (Element link2:gstitle){
        String publication = link2.getElementsByClass("gs_pub").text();
        if (publication.length()!=0){
            pubinfo[count] = publication;
            count++;
        }
        }
        count = 0;
        Elements gsnum = doc.getElementsByTag("td");
        for (Element number:gsnum){
            String pny = number.getElementsByClass("gs_num").text();
            if(pny.length()!=0){
                numinfo[count] = pny;
                count++;
            }
        }
        gstitle = gstitle.not("[class]");
        for (int i =0; i < count/2; i++ ){
            System.out.print(gstitle.get(i+4).text()+"         ->");
            System.out.print(bookinfo[i] + "     ->");
            System.out.print(pubinfo[i] + "     ->");
            System.out.print(numinfo[2 * i] + "     ->");
            System.out.println(numinfo[2*i+1]);
        }
            bookinfo= new String[20];
            pubinfo= new String[20];
            numinfo= new String[40];
            count=0;
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}
}