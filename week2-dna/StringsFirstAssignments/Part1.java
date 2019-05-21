
/**
 * Class for finding genes in DNA strands
 * 
 * @author: Ryan Juza
 * @version: 052119
 */
public class Part1 {

    public String findSimpleGene(String dna){
        
        //Finds the index position of the start codon “ATG”. If there is no “ATG”, returns the empty string.
        int start = dna.indexOf("ATG");
        if (start == -1){
            return "";
        }
        
        //Finds the index position of the first stop codon “TAA”, appearing after the “ATG” that was found (the 'start+3').
        //If the length of the substring between the “ATG” and “TAA” is a multiple of 3,
        //returns the substring that starts with that “ATG” and ends with that “TAA”
        int stop = dna.indexOf("TAA", start+3);
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
        System.out.println("DNA string = " + proper);
        
        //Calls findSimpleGene with chosen string as the parameter
        String tested = findSimpleGene(proper);
        
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
    



