
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
        
        //Finds the index position of the first stop codon, appearing after the SsartIndex that was found (the 'start+3').
        //If the length of the substring between the startIndex and stopCodon is a multiple of 3,
        //returns the index of the stopCodon
        int stop = dna.indexOf(stopCodon, startIndex+3);
        
        if (startIndex != -1){
            if ((stop - startIndex) % 3 == 0){
            //returns index of the stop codon, starting AFTER the start codon
            return dna.indexOf(stopCodon, startIndex+3);
            }
            //If there is no such stopCodon, return the length of the dna strand
            else{return dna.length();}
        }
        //if startIndex is -1, returns dna string
        else{return startIndex;}
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
            String gene;
            String startCodon = "ATG";
            //Checks if the 'dna' string is lowercase. If so, makes the exampleStartCodon lower case as well
            if(dna.equals(dna.toLowerCase())){
            startCodon = "atg";
            }
            //Finds the index position of the start codon “ATG”.
            int startIndex = dna.indexOf(startCodon);
            
            if(startIndex != -1){
                int stopIndexTAA = findStopCodon(dna, startIndex, "TAA");
                int stopIndexTAG = findStopCodon(dna, startIndex, "TAG");
                int stopIndexTGA = findStopCodon(dna, startIndex, "TGA");

                if((stopIndexTAA < stopIndexTAG) && (stopIndexTAA < stopIndexTGA)){
                    gene = dna.substring(startIndex, stopIndexTAA+3);
                }
                else if((stopIndexTAG < stopIndexTAA) && (stopIndexTAG < stopIndexTGA)){
                    gene = dna.substring(startIndex, stopIndexTAG+3);
                }
                else if((stopIndexTGA < stopIndexTAA) && (stopIndexTGA < stopIndexTAG)){
                    gene = dna.substring(startIndex, stopIndexTGA+3);
                }
                else{gene = dna + " has ATG, but no gene";}
            }
            else{return dna + " has no ATG";}
            return gene;
    }
    
    public void testFindGene(){
        //test values for findGene method
        String[] geneExamples= new String[]{
        //DNA with no “ATG”
        "CCCAAAGGGTTTTTTAAA",
        //DNA with “ATG” and one valid stop codon
        "CCCATGCCCAAATTTGGGTAA",
        //DNA with “ATG” and multiple valid stop codons
        "atgaaatttgggtagccctttgggtaagggtgaccc",
        //DNA with “ATG” and no valid stop codons
        "atggggtttaaaccccctttt",
        //DNA with "ATG" and a valid stop codon, but not multiple of 3
        "CCCATGCCCAAATTTGGGGTAAGGG"};
        
        for(int i = 0; i<geneExamples.length; i++){
        //prints out the restults of each example
        System.out.println("Result for " + geneExamples[i] + " = " + findGene(geneExamples[i]));
        }
    }
    
    public void printAllGenes(String dna){
        //repeatedly find genes and 
        //print each one 
        //until there are no more genes
    }
}

