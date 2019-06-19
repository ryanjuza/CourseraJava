
/**
 * Second class for finding genes in DNA strands, with looping
 * 
 * @author: Ryan Juza
 * @version: 061919
 */
public class Part2 {
    public int howMany(String stringa, String stringb){
        //checks if it is lower case
        if(stringb == stringb.toLowerCase()){
            stringa = stringa.toLowerCase();
        }
        //declare first instance of stringa in stringb and set it to a variable
        int currIndex = stringb.indexOf(stringa);
        //declare count tracking integer
        int counter = 0;
        // find how many times stringa appears in stringb
        while(currIndex != -1){
            //adds to counter if new index found
            counter ++;
            //sets currIndex to the next index of string a in string b AFTER the most recent one
            currIndex = stringb.indexOf(stringa, currIndex+stringa.length());
        }
        return counter;
    }
    
    public void testHowMany(){
        //examples to test howMany
        String[] examples= new String[]{
        // 1 // 3 ATG ina row
        "AAAATGATGATGCCCGGGTTT",
        // 2 // no ATG
        "CCCAAAGGGTTTCCCAAA",
        // 3 // 2 ATG, then one more
        "ATGATGCCCGGGTTTAAAATG",
        // 4 // 3 seperate ATG
        "atggggtttatgccctttatgggg"
        };
        
        String stringa = "ATG";
        
        for(int i = 0; i<examples.length; i++){
            System.out.println("Example " + (i+1) + ": " + howMany(stringa, examples[i]));
        }
    }
}
