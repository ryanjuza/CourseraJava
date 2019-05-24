
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
        //System.out.println(a + " is at " + firstIndex);
        //checks string b for another instance of string a after the first one
        int secondIndex = b.indexOf(a, firstIndex+(a.length()));
        //System.out.println(a + " second occurence is " + secondIndex);
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
        //testing the lastPart method
        String a1 = "No instance";
        String b1 = "String a is not in here";
        String a2 = "Dog";
        String b2 = "Doggie";
        String a3 = "is ";
        String b3 = "That is how it goes";
        String a4 = "Oh";
        String b4 = "Oh boy another example";
        String a5 = "end";
        String b5 = "The instance is at the end";
        
        //calls and prints twoOccurences for each string set
        System.out.println("Checking for two occurences section:");
        System.out.println("String 1, should be false: " + twoOccurrences(ins1, str1));
        System.out.println("String 2, should be false: " + twoOccurrences(ins2, str2));
        System.out.println("String 3, should be true: " + twoOccurrences(ins3, str3));
        System.out.println("String 4, should be true: " + twoOccurrences(ins4, str4));
        //calls and prints lastPart for each string set
        System.out.println("Checking for one and printing the string(s) section:");
        System.out.println("The part of the string after '" + a1 + "' in '" + b1 + "' is '" + lastPart(a1, b1) + "'");
        System.out.println("The part of the string after '" + a2 + "' in '" + b2 + "' is '" + lastPart(a2, b2) + "'");
        System.out.println("The part of the string after '" + a3 + "' in '" + b3 + "' is '" + lastPart(a3, b3) + "'");
        System.out.println("The part of the string after '" + a4 + "' in '" + b4 + "' is '" + lastPart(a4, b4) + "'");
        System.out.println("The part of the string after '" + a5 + "' in '" + b5 + "' is '" + lastPart(a5, b5) + "'");
    }
    
    public String lastPart(String stringa, String stringb){
        //gets the index of string a in string b and assigns it to aINb
        int aINb = stringb.indexOf(stringa);
        //checks if string a is in string b
        if(aINb == -1){
            //if a is not in b, returns b
            return stringb;
        }
        else{
            //if a is in b, returns the text in string b after string a
            return stringb.substring(aINb+stringa.length());
        }
        
        
    }
}
 