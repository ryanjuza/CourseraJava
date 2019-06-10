
/**
 * Newest class for finding genes in DNA strands, with looping
 * 
 * @author: Ryan Juza
 * @version: 061019
 */
public class Part1 {
    
    public int findStopCodon(String dna, int startIndex, String stopCodon){        
        //Checks if the 'dna' string is lowercase. If so, makes the stopCodon lower case as well
        if(dna.equals(dna.toLowerCase())){
            stopCodon = stopCodon.toLowerCase();
        }
        
                //if startIndex is -1, returns dna string
        if (startIndex == -1){
            return startIndex;
        }
        
        //Finds the index position of the first stop codon, appearing after the SsartIndex that was found (the 'start+3').
        //If the length of the substring between the startIndex and stopCodon is a multiple of 3,
        //returns the index of the stopCodon
        int stop = dna.indexOf(stopCodon, startIndex+3);
        
        if ((stop - startIndex) % 3 == 0){
            return dna.indexOf(stopCodon);
        }
        //If there is no such stopCodon, return the length of the dna strand
        else {         
                return dna.length();
        }
    }
    
    public void testFindStopCodon(){
        //examples to test findStopCodon
        String[] stopExamples= new String[]{
        //proper taa
        "CCCATGCCCAAATTTGGGTAA",
        //nothing
        "CCCAAAGGGTTTTTTAAA",
        //has atg but no stop codon
        "atggggtttaaaccctttggg",
        //has atg, has stop codon, but not in multiple of 3
        "atggggtttaaacctaacccttt"};
        
        for(int i = 0; i<stopExamples.length; i++){
            String exampleStartCodon = "ATG";
            //Checks if the 'dna' string is lowercase. If so, makes the exampleStartCodon lower case as well
            if(stopExamples[i].equals(stopExamples[i].toLowerCase())){
            exampleStartCodon = "atg";
            }
            //Finds the index position of the start codon “ATG”.
            int startIndex = stopExamples[i].indexOf(exampleStartCodon);

            //prints out the restults of each example
            System.out.println("Result for " + stopExamples[i] + " = " + findStopCodon(stopExamples[i],startIndex,"TAA"));
        }
    
    }
    
    public String findGene(String dna){
            String startCodon = "ATG";
            //Checks if the 'dna' string is lowercase. If so, makes the exampleStartCodon lower case as well
            if(startCodon.equals(startCodon.toLowerCase())){
            startCodon = "atg";
            }
            //Finds the index position of the start codon “ATG”.
            int startIndex = dna.indexOf(startCodon);
            String stopCodon ="TAA";
            int stopIndexTAA = findStopCodon(dna, startIndex, stopCodon);     
    }
}

