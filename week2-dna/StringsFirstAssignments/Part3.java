
/**
 * Week 2, problem solving with strings
 * 
 * @author Ryan Juza
 * @version 052119
 */
public class Part3 {
    public boolean twoOccurrences(String a, String b){
        //checks string b for string a
        int firstIndex = b.indexOf(a);
        //checks string b for another instance of string a after the first one
        int secondIndex = b.indexOf(a, firstIndex);
        
        if(secondIndex == -1){
            return false;
        }
        else{
            return true;
        }
    }
    
    public void testing(){
        
        //testing the twoOccurences method
        String ins1 = "not found";
        String str1 = "This instance is not here";
        String ins2 = "found once";
        String str2 = "This should be found once";
        String ins3 = "twice";
        String str3 = "twice this word is here twice";     
        String ins4 = "thrice";
        String str4 = "thrice that is right folks thrice it's thrice this word is here";
        //String ins5 = "";
        //String str5 = "";        
    }
}
 