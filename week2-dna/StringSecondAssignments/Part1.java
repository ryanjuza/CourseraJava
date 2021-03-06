
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
        //how many times stringa appears in stringb
        //Finds the index position of the first stop codon, appearing after the SsartIndex that was found (the 'start+3').
        //If the length of the substring between the startIndex and stopCodon is a multiple of 3,
        //returns the index of the stopCodon
        int currIndex = dna.indexOf(stopCodon, startIndex+3);
        while(currIndex != -1){
            if ((currIndex - startIndex) % 3 == 0){
            //returns index of the stop codon, starting AFTER the start codon
            return currIndex;
            }
            else{
                //updates currIndex to the index of the next stopCodon AFTER the current one 
                currIndex = dna.indexOf(stopCodon, currIndex+1);
            }
        }
        //if there is no stopcodon (or startcodon), returns -1
        return -1;
    }
    
    public void testFindStopCodon(){
        //examples to test findStopCodon
        String[] stopExamples= new String[]{
        //proper taa
        "CCCATGCCCAAATTTGGGTAA",
        //no ATG
        "CCCAAAGGGTTTCCCAAA",
        //no start codon, but has stop codon
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
    
    public String findGene(String dna, int where){
            String startCodon = "ATG";
            //Checks if the 'dna' string is lowercase. If so, makes the exampleStartCodon lower case as well
            if(dna.equals(dna.toLowerCase())){
            startCodon = "atg";
            }
            //Finds the index position of the start codon “ATG”.
            int startIndex = dna.indexOf(startCodon, where);
            System.out.println(startIndex);//TEST CODE
            //Ensures there is a start codon
            if(startIndex == -1){
                return "";
            }
        
            //Finds first occurence of each stop codon
            int stopIndexTAA = findStopCodon(dna, startIndex, "TAA");
            int stopIndexTAG = findStopCodon(dna, startIndex, "TAG");
            int stopIndexTGA = findStopCodon(dna, startIndex, "TGA");
            
            int minIndex = 0;
            
            //Finds the smaller between TAA and TAG
            if(stopIndexTAA == -1 || (stopIndexTAG != -1 && stopIndexTAG < stopIndexTAA)){
                minIndex = stopIndexTAG;
            }
            else{
                minIndex = stopIndexTAA;
            }
            //Finds smaller between previous smaller and TGA
            if(minIndex == -1 || (stopIndexTGA != -1 && stopIndexTGA < minIndex)){
                minIndex = stopIndexTGA;
            }
            
            //if none found by the time you reach the end of the dna string, return message
            if(minIndex == -1){
                return "No stop codon and/or no stop codon that is a multiple of 3";
            }
            //returns found gene!
            return dna.substring(startIndex, minIndex+3);
}
    
    public void testFindGene(){
        //test values for findGene method
        String[] geneExamples= new String[]{
        //DNA with no “ATG”
        "CCCAAAGGGTTTTTTAAA",
        //DNA with “ATG” and one valid stop codon
        "CCCATGCCCAAATTTGGGTAA",
        //DNA with “ATG” and multiple valid stop codons (should find first one, tag)
        "atgaaatttgggtagccctttgggtaagggtgaccc",
        //DNA with “ATG” and no valid stop codons
        "atggggtttaaaccccctttt",
        //DNA with "ATG" and a valid stop codon, but not multiple of 3
        "CCCATGCCCAAATTTGGGGTAAGGG"};
        
        for(int i = 0; i<geneExamples.length; i++){
        //prints out the restults of each example
        System.out.println("Result for " + geneExamples[i] + " = " + findGene(geneExamples[i], 0));
        }
    }
    
    public void printAllGenes(String dna){
        //repeatedly find genes and 
        //print each one 
        //until there are no more genes
        int startIndex = 0;
        while(true){
            //gets gene from dna
            String currentGene = findGene(dna, startIndex);
            //if no gene was found, break
            if(currentGene.isEmpty()){
                break;
            }
            //print the gene (this is after the no-gene; check to make sure it breaks)
            System.out.println(currentGene);
            //sets startIndex to next character after found gene
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
    }
    
    public void testPrintAllGenes(){
        //test values for printAllGenes method
        String[] dnaExamples= new String[]{
        //empty string
        "",
        //DNA with one gene
        "CCCATGCCCAAATTTGGGTAACCC",
        //DNA with 3 genes
        "atgaaatttgggtagcccatggggtgagggtagcccaaatgcccgggtgaaaa",
        //DNA with “ATG” and no valid stop codons
        "atggggtttaaaccccctttt",
        //DNA with "ATG" and a valid stop codon, but not multiple of 3
        "CCCATGCCCAAATTTGGGGTAAGGG"};
        
        int counter = 0;
        for(int i = 0; i<dnaExamples.length; i++){
        counter++;
        //prints out the restults of each example
        System.out.println("Example " + counter + ": " + dnaExamples[i]);
        printAllGenes(dnaExamples[i]);
        }
    }
}

