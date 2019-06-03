import edu.duke.*;
/**
 * THis class gets YouTube links from a URl of links
 * 
 * @author Ryan Juza
 * @version 060319
 */
public class Part4 {
    public void getYouTube(String word){
    String foundLink = "";
        //converts word to lower case in order to find url's easier
        String wordLower = word.toLowerCase();
        String YT = "youtube.com";
        //look for youtube.com
        int YTfound = wordLower.indexOf(YT);
        if(YTfound != -1){
            //look for starting and ending quotation marks
            int startQuote = word.lastIndexOf("\"",YTfound);
            int endQuote = word.indexOf("\"",YTfound);
            foundLink = word.substring(startQuote+1, endQuote);
            System.out.println(foundLink);
        }
   }
    
   
    
    public void main (){
        //calls getYouTube and prints the result
        //loops through each word on the page
        //reads the manylinks page
        //"http://www.dukelearntoprogram.com/course2/data/manylinks.html"
        URLResource ur = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        for (String word : ur.words()) {
            getYouTube(word);
        }
    }
}
