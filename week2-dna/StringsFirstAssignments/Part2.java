
/**
 * Modified class for finding genes in DNA strands
 * 
 * @author: Ryan Juza
 * @version: 052119
 */
public class Part2 {
    
    public String findSimpleGene(String dna, String startCodon, String stopCodon){
        
        //Checks if the 'dna' string is lowercase. If so, makes the codons lower case as well (they're upper by default)
        if(dna.equals(dna.toLowerCase())){
            startCodon = startCodon.toLowerCase();
            stopCodon = stopCodon.toLowerCase();
        }
        //Finds the index position of the start codon “ATG”. If there is no “ATG”, returns the empty string.
        int start = dna.indexOf(startCodon);
        if (start == -1){
            return "";
        }
        
        //Finds the index position of the first stop codon “TAA”, appearing after the “ATG” that was found (the 'start+3').
        //If the length of the substring between the “ATG” and “TAA” is a multiple of 3,
        //returns the substring that starts with that “ATG” and ends with that “TAA”
        int stop = dna.indexOf(stopCodon, start+3);
        
        if ((stop - start) % 3 == 0){
            return dna.substring(start, stop+3);
        }
        //If there is no such “TAA”, return the empty string.
        else {         
                return "";
        }
    }

    public void testSimpleGene() {  

        String noATG = "CTGGCCATTTGATAAACC";
        String noTAA = "CCCATGCGGATTGATGAGTTT";
        String noATGTAA = "GTACTGGCCATTTGA";
        String notThree = "GCTATGGGTCTGACCATTAAGT";
        String proper = "GGGATGGTACGTATCGCATCGTAAAAA";
        
        //Prints the DNA string. 
        System.out.println("DNA string = " + proper.toLowerCase());
        
        //Calls findSimpleGene with chosen string as the parameter
        //(Append '.toLowerCase' to the string variable to test lower case strings)
        String tested = findSimpleGene(proper.toLowerCase(), "ATG", "TAA");
        
        //If a gene exists, prints the gene
        if (tested != ""){
            System.out.println("Gene found! It is: " + tested);
        
        }
        //"otherwise print the empty string" I used a message to be clear to the user.
        else{
            System.out.println("There was no gene in this DNA strand.");
        }
        

    }

}
