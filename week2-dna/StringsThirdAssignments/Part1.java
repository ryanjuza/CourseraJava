
/**
 * First class in third assignment for finding genes in DNA strands, with looping
 * This one focuses on storing genes.
 * 
 * @author: Ryan Juza
 * @version: 062019
 */

import edu.duke.*;

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
        
        String exampleStartCodon = "ATG";
        for(int i = 0; i<stopExamples.length; i++){
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
            //TEST CODESystem.out.println(startIndex);
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
        "CCCATGCCCAAATTTGGGGTAAGGG",
        //lower case, one gene
        "gggatgccctttaaataagggatgccatg"
        };
        
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
        String currentGene;
        while(true){
            //gets gene from dna
            currentGene = findGene(dna, startIndex);
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
    
        public StorageResource getAllGenes(String dna){
         StorageResource geneList = new StorageResource();
        //repeatedly find genes and 
        //print each one 
        //until there are no more genes
        int startIndex = 0;
        String currentGene;
        while(true){
            //gets gene from dna
             currentGene = findGene(dna, startIndex);
            //if no gene was found, break
            if(currentGene.isEmpty()){
                break;
            }
            //add gene to geneList (this is after the no-gene; check to make sure it breaks)
            geneList.add(currentGene);
            //sets startIndex to next character after found gene
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
        return geneList;
        
    }
    
    public void testGetAllGenes(){
        //test values for printAllGenes method
        String[] dnaExamples= new String[]{
        //empty string
        "",
        //lower case, one gene
        "gggatggggatgccctttaaataagggatgccatg",
        //DNA with one gene
        "CCCATGCCCAAATTTGGGTAACCC",
        //DNA with 3 genes
        "atgaaatttgggtagcccatggggtgagggtagcccaaatgcccgggtgaaaa",
        //DNA with “ATG” and no valid stop codons
        "atggggtttaaaccccctttt",
        //DNA with "ATG" and a valid stop codon, but not multiple of 3
        "CCCATGCCCAAATTTGGGGTAAGGGATGATGATG",
        //file example
        "acaagtttgtacaaaaaagcagaagggccgtcaaggcccaccatgcctattggatccaaagagaggccaacattttttgaaatttttaagacacgctgcaacaaagcagatttaggaccaataagtcttaattggtttgaagaactttcttcagaagctccaccctataattctgaacctgcagaagaatctgaacataaaaacaacaattacgaaccaaacctatttaaaactccacaaaggaaaccatcttataatcagctggcttcaactccaataatattcaaagagcaagggctgactctgccgctgtaccaatctcctgtaaaagaattagataaattcaaattagacttaggaaggaatgttcccaatagtagacataaaagtcttcgcacagtgaaaactaaaatggatcaagcagatgatgtttcctgtccacttctaaattcttgtcttagtgaaagtcctgttgttctacaatgtacacatgtaacaccacaaagagataagtcagtggtatgtgggagtttgtttcatacaccaaagtttgtgaagggtcgtcagacaccaaaacatatttctgaaagtctaggagctgaggtggatcctgatatgtcttggtcaagttctttagctacaccacccacccttagttctactgtgctcatagtcagaaatgaagaagcatctgaaactgtatttcctcatgatactactgctaatgtgaaaagctatttttccaatcatgatgaaagtctgaagaaaaatgatagatttatcgcttctgtgacagacagtgaaaacacaaatcaaagagaagctgcaagtcatggatttggaaaaacatcagggaattcatttaaagtaaatagctgcaaagaccacattggaaagtcaatgccaaatgtcctagaagatgaagtatatgaaacagttgtagatacctctgaagaagatagtttttcattatgtttttctaaatgtagaacaaaaaatctacaaaaagtaagaactagcaagactaggaaaaaaattttccatgaagcaaacgctgatgaatgtgaaaaatctaaaaaccaagtgaaagaaaaatactcatttgtatctgaagtggaaccaaatgatactgatccattagattcaaatgtagcaaatcagaagccctttgagagtggaagtgacaaaatctccaaggaagttgtaccgtctttggcctgtgaatggtctcaactaaccctttcaggtctaaatggagcccagatggagaaaatacccctattgcatatttcttcatgtgaccaaaatatttcagaaaaagacctattagacacagagaacaaaagaaagaaagattttcttacttcagagaattctttgccacgtatttctagcctaccaaaatcagagaagccattaaatgaggaaacagtggtaaataagagagatgaagagcagcatcttgaatctcatacagactgcattcttgcagtaaagcaggcaatatctggaacttctccagtggcttcttcatttcagggtatcaaaaagtctatattcagaataagagaatcacctaaagagactttcaatgcaagtttttcaggtcatatgactgatccaaactttaaaaaagaaactgaagcctctgaaagtggactggaaatacatactgtttgctcacagaaggaggactccttatgtccaaatttaattgataatggaagctggccagccaccaccacacagaattctgtagctttgaagaatgcaggtttaatatccactttgaaaaagaaaacaaataagtttatttatgctatacatgatgaaacatcttataaaggaaaaaaaataccgaaagaccaaaaatcagaactaattaactgttcagcccagtttgaagcaaatgcttttgaagcaccacttacatttgcaaatgctgattcaggtttattgcattcttctgtgaaaagaagctgttcacagaatgattctgaagaaccaactttgtccttaactagctcttttgggacaattctgaggaaatgttctagaaatgaaacatgttctaataatacagtaatctctcaggatcttgattataaagaagcaaaatgtaataaggaaaaactacagttatttattaccccagaagctgattctctgtcatgcctgcaggaaggacagtgtgaaaatgatccaaaaagcaaaaaagtttcagatataaaagaagaggtcttggctgcagcatgtcacccagtacaacattcaaaagtggaatacagtgatactgactttcaatcccagaaaagtcttttatatgatcatgaaaatgccagcactcttattttaactcctacttccaaggatgttctgtcaaacctagtcatgatttctagaggcaaagaatcatacaaaatgtcagacaagctcaaaggtaacaattatgaatctgatgttgaattaaccaaaaatattcccatggaaaagaatcaagatgtatgtgctttaaatgaaaattataaaaacgttgagctgttgccacctgaaaaatacatgagagtagcatcaccttcaagaaaggtacaattcaaccaaaacacaaatctaagagtaatccaaaaaaatcaagaagaaactacttcaatttcaaaaataactgtcaatccagactctgaagaacttttctcagacaatgagaataattttgtcttccaagtagctaatgaaaggaataatcttgctttaggaaatactaaggaacttcatgaaacagacttgacttgtgtaaacgaacccattttcaagaactctaccatggttttatatggagacacaggtgataaacaagcaacccaagtgtcaattaaaaaagatttggtttatgttcttgcagaggagaacaaaaatagtgtaaagcagcatataaaaatgactctaggtcaagatttaaaatcggacatctccttgaatatagataaaataccagaaaaaaataatgattacatgaacaaatgggcaggactcttaggtccaatttcaaatcacagttttggaggtagcttcagaacagcttcaaataaggaaatcaagctctctgaacataacattaagaagagcaaaatgttcttcaaagatattgaagaacaatatcctactagtttagcttgtgttgaaattgtaaataccttggcattagataatcaaaagaaactgagcaagcctcagtcaattaatactgtatctgcacatttacagagtagtgtagttgtttctgattgtaaaaatagtcatataacccctcagatgttattttccaagcaggattttaattcaaaccataatttaacacctagccaaaaggcagaaattacagaactttctactatattagaagaatcaggaagtcagtttgaatttactcagtttagaaaaccaagctacatattgcagaagagtacatttgaagtgcctgaaaaccagatgactatcttaaagaccacttctgaggaatgcagagatgctgatcttcatgtcataatgaatgccccatcgattggtcaggtagacagcagcaagcaatttgaaggtacagttgaaattaaacggaagtttgctggcctgttgaaaaatgactgtaacaaaagtgcttctggttatttaacagatgaaaatgaagtggggtttaggggcttttattctgctcatggcacaaaactgaatgtttctactgaagctctgcaaaaagctgtgaaactgtttagtgatattgagaatattagtgaggaaacttctgcagaggtacatccaataagtttatcttcaagtaaatgtcatgattctgttgtttcaatgtttaagatagaaaatcataatgataaaactgtaagtgaaaaaaataataaatgccaactgatattacaaaataatattgaaatgactactggcacttttgttgaagaaattactgaaaattacaagagaaatactgaaaatgaagataacaaatatactgctgccagtagaaattctcataacttagaatttgatggcagtgattcaagtaaaaatgatactgtttgtattcataaagatgaaacggacttgctatttactgatcagcacaacatatgtcttaaattatctggccagtttatgaaggagggaaacactcagattaaagaagatttgtcagatttaacttttttggaagttgcgaaagctcaagaagcatgtcatggtaatacttcaaataaagaacagttaactgctactaaaacggagcaaaatataaaagattttgagacttctgatacattttttcagactgcaagtgggaaaaatattagtgtcgccaaagagtcatttaataaaattgtaaatttctttgatcagaaaccagaagaattgcataacttttccttaaattctgaattacattctgacataagaaagaacaaaatggacattctaagttatgaggaaacagacatagttaaacacaaaatactgaaagaaagtgtcccagttggtactggaaatcaactagtgaccttccagggacaacccgaacgtgatgaaaagatcaaagaacctactctattgggttttcatacagctagcgggaaaaaagttaaaattgcaaaggaatctttggacaaagtgaaaaacctttttgatgaaaaagagcaaggtactagtgaaatcaccagttttagccatcaatgggcaaagaccctaaagtacagagaggcctgtaaagaccttgaattagcatgtgagaccattgagatcacagctgccccaaagtgtaaagaaatgcagaattctctcaataatgataaaaaccttgtttctattgagactgtggtgccacctaagctcttaagtgataatttatgtagacaaactgaaaatctcaaaacatcaaaaagtatctttttgaaagttaaagtacatgaaaatgtagaaaaagaaacagcaaaaagtcctgcaacttgttacacaaatcagtccccttattcagtcattgaaaattcagccttagctttttacacaagttgtagtagaaaaacttctgtgagtcagacttcattacttgaagcaaaaaaatggcttagagaaggaatatttgatggtcaaccagaaagaataaatactgcagattatgtaggaaattatttgtatgaaaataattcaaacagtactatagctgaaaatgacaaaaatcatctctccgaaaaacaagatacttatttaagtaacagtagcatgtctaacagctattcctaccattctgatgaggtatataatgattcaggatatctctcaaaaaataaacttgattctggtattgagccagtattgaagaatgttgaagatcaaaaaaacactagtttttccaaagtaatatccaatgtaaaagatgcaaatgcatacccacaaactgtaaatgaagatatttgcgttgaggaacttgtgactagctcttcaccctgcaaaaataaaaatgcagccattaaattgtccatatctaatagtaataattttgaggtagggccacctgcatttaggatagccagtggtaaaatcgtttgtgtttcacatgaaacaattaaaaaagtgaaagacatatttacagacagtttcagtaaagtaattaaggaaaacaacgagaataaatcaaaaatttgccaaacgaaaattatggcaggttgttacgaggcattggatgattcagaggatattcttcataactctctagataatgatgaatgtagcacgcattcacataaggtttttgctgacattcagagtgaagaaattttacaacataaccaaaatatgtctggattggagaaagtttctaaaatatcaccttgtgatgttagtttggaaacttcagatatatgtaaatgtagtatagggaagcttcataagtcagtctcatctgcaaatacttgtgggatttttagcacagcaagtggaaaatctgtccaggtatcagatgcttcattacaaaacgcaagacaagtgttttctgaaatagaagatagtaccaagcaagtcttttccaaagtattgtttaaaagtaacgaacattcagaccagctcacaagagaagaaaatactgctatacgtactccagaacatttaatatcccaaaaaggcttttcatataatgtggtaaattcatctgctttctctggatttagtacagcaagtggaaagcaagtttccattttagaaagttccttacacaaagttaagggagtgttagaggaatttgatttaatcagaactgagcatagtcttcactattcacctacgtctagacaaaatgtatcaaaaatacttcctcgtgttgataagagaaacccagagcactgtgtaaactcagaaatggaaaaaacctgcagtaaagaatttaaattatcaaataacttaaatgttgaaggtggttcttcagaaaataatcactctattaaagtttctccatatctctctcaatttcaacaagacaaacaacagttggtattaggaaccaaagtgtcacttgttgagaacattcatgttttgggaaaagaacaggcttcacctaaaaacgtaaaaatggaaattggtaaaactgaaactttttctgatgttcctgtgaaaacaaatatagaagtttgttctacttactccaaagattcagaaaactactttgaaacagaagcagtagaaattgctaaagcttttatggaagatgatgaactgacagattctaaactgccaagtcatgccacacattctctttttacatgtcccgaaaatgaggaaatggttttgtcaaattcaagaattggaaaaagaagaggagagccccttatcttagtgggagaaccctcaatcaaaagaaacttattaaatgaatttgacaggataatagaaaatcaagaaaaatccttaaaggcttcaaaaagcactccagatggcacaataaaagatcgaagattgtttatgcatcatgtttctttagagccgattacctgtgtaccctttcgcacaactaaggaacgtcaagagatacagaatccaaattttaccgcacctggtcaagaatttctgtctaaatctcatttgtatgaacatctgactttggaaaaatcttcaagcaatttagcagtttcaggacatccattttatcaagtttctgctacaagaaatgaaaaaatgagacacttgattactacaggcagaccaaccaaagtctttgttccaccttttaaaactaaatcacattttcacagagttgaacagtgtgttaggaatattaacttggaggaaaacagacaaaagcaaaacattgatggacatggctctgatgatagtaaaaataagattaatgacaatgagattcatcagtttaacaaaaacaactccaatcaagcagcagctgtaactttcacaaagtgtgaagaagaacctttagatttaattacaagtcttcagaatgccagagatatacaggatatgcgaattaagaagaaacaaaggcaacgcgtctttccacagccaggcagtctgtatcttgcaaaaacatccactctgcctcgaatctctctgaaagcagcagtaggaggccaagttccctctgcgtgttctcataaacagctgtatacgtatggcgtttctaaacattgcataaaaattaacagcaaaaatgcagagtcttttcagtttcacactgaagattattttggtaaggaaagtttatggactggaaaaggaatacagttggctgatggtggatggctcataccctccaatgatggaaaggctggaaaagaagaattttatagggctctgtgtgacactccaggtgtggatccaaagcttatttctagaatttgggtttataatcactatagatggatcatatggaaactggcagctatggaatgtgcctttcctaaggaatttgctaatagatgcctaagcccagaaagggtgcttcttcaactaaaatacagatatgatacggaaattgatagaagcagaagatcggctataaaaaagataatggaaagggatgacacagctgcaaaaacacttgttctctgtgtttctgacataatttcattgagcgcaaatatatctgaaacttctagcaataaaactagtagtgcagatacccaaaaagtggccattattgaacttacagatgggtggtatgctgttaaggcccagttagatcctcccctcttagctgtcttaaagaatggcagactgacagttggtcagaagattattcttcatggagcagaactggtgggctctcctgatgcctgtacacctcttgaagccccagaatctcttatgttaaagatttctgctaacagtactcggcctgctcgctggtataccaaacttggattctttcctgaccctagaccttttcctctgcccttatcatcgcttttcagtgatggaggaaatgttggttgtgttgatgtaattattcaaagagcataccctatacagtggatggagaagacatcatctggattatacatatttcgcaatgaaagagaggaagaaaaggaagcagcaaaatatgtggaggcccaacaaaagagactagaagccttattcactaaaattcaggaggaatttgaagaacatgaagaaaacacaacaaaaccatatttaccatcacgtgcactaacaagacagcaagttcgtgctttgcaagatggtgcagagctttatgaagcagtgaagaatgcagcagacccagcttaccttgagggttatttcagtgaagagcagttaagagccttgaataatcacaggcaaatgttgaatgataagaaacaagctcagatccagttggaaattaggaaggccatggaatctgctgaacaaaaggaacaaggtttatcaagggatgtcacaaccgtgtggaagttgcgtattgtaagctattcaaaaaaagaaaaagattcagttatactgagtatttggcgtccatcatcagatttatattctctgttaacagaaggaaagagatacagaatttatcatcttgcaacttcaaaatctaaaagtaaatctgaaagagctaacatacagttagcagcgacaaaaaaaactcagtatcaacaactaccggtttcagatgaaattttatttcagatttaccagccacgggagccccttcacttcagcaaatttttagatccagactttcagccatcttgttctgaggtggacctaataggatttgtcgtttctgttgtgaaaaaaacaggacttgcccctttcgtctatttgtcagacgaatgttacaatttactggcaataaagttttggatagaccttaatgaggacattattaagcctcatatgttaattgctgcaagcaacctccagtggcgaccagaatccaaatcaggccttcttactttatttgctggagatttttctgtgttttctgctagtccaaaagagggccactttcaagagacattcaacaaaatgaaaaatactgttgagaatattgacatactttgcaatgaagcagaaaacaagcttatgcatatactgcatgcaaatgatcccaagtggtccaccccaactaaagactgtacttcagggccgtacactgctcaaatcattcctggtacaggaaacaagcttctgatgtcttctcctaattgtgagatatattatcaaagtcctttatcactttgtatggccaaaaggaagtctgtttccacacctgtctcagcccagatgacttcaaagtcttgtaaaggggagaaagagattgatgaccaaaagaactgcaaaaagagaagagccttggatttcttgagtagactgcctttacctccacctgttagtcccatttgtacatttgtttctccggctgcacagaaggcatttcagccaccaaggagttgtggcaccaaatacgaaacacccataaagaaaaaagaactgaattctcctcagatgactccatttaaaaaattcaatgaaatttctcttttggaaagtaattcaatagctgacgaagaacttgcattgataaatacccaagctcttttgtctggttcaacaggagaaaaacaatttatatctgtcagtgaatccactaggactgctcccaccagttcagaagattatctcagactgaaacgacgttgtactacatctctgatcaaagaacaggagagttcccaggccagtacggaagaatgtgagaaaaataagcaggacacaattacaactaaaaaatatatctagggcctcatgggcccagctttcttgtacaaagtggt"
    };
        
        StorageResource genes = new StorageResource();
        int counter = 0;
        for(int i = 0; i<dnaExamples.length; i++){
        counter++;
        //prints out the restults of each example
        System.out.println("Example " + counter + ": " + dnaExamples[i]);
        genes = getAllGenes(dnaExamples[i]);
            for(String g: genes.data()){
                System.out.println(g);
            }
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
    
    //returns the ratio of C’s and G’s in dna as a fraction of the entire strand of DNA
    public float cgRatio(String dna){
        char c;
        int cgTotal = 0;
        for(int i=0; i < dna.length(); i++){
            c = dna.charAt(i);
            if(c == 'c'||c == 'g'||c == 'C'||c == 'G'){
                cgTotal++;
                //System.out.println(cgTotal);
            }
        }
        //converts cg total to float and divides it by the length of the dna strand
        float ratio = ((float)cgTotal)/dna.length();
        return ratio;
    }
    
    public void testcgRatio(){
        String dna = "AAACCCTTTGGGAAATTTAAATTT";
        //dna string with 25% c's and g's
        System.out.println("AAACCCTTTGGGAAATTTAAATTT total = " + cgRatio(dna));
        //same, but lower case
        System.out.println("aaaccctttgggaaatttaaattt total = " + cgRatio(dna.toLowerCase()));
    }
    
    public int countCTG(String dna){
        String ctgString = "CTG";
        int startIndex = 0;
        int counter = 0;
        //Checks if the 'dna' string is lowercase. If so, makes ctgString lower case as well
        if(dna.equals(dna.toLowerCase())){
        ctgString = "ctg";
        }
        //sets current index to the index of the first ctg occurence
        int currIndex = dna.indexOf(ctgString, startIndex);
        //loops through dna looking for ctg, adding to counter for each one found
        while(currIndex != -1){
                counter ++;
                currIndex = dna.indexOf(ctgString, currIndex+1);
            }
            return counter;
        }
    public void testCountCTG(){
        //prints number of ctg's in the dna strand
        System.out.println("There are 5 \"CTG's\" in the dna strand example.");
        System.out.println(countCTG("CTGAAATTTCTGCTGTTTCTGAACTGC"));
    }
    
    public void processGenes(StorageResource sr){
        //loops through all the strings in sr
        int over9Counter = 0;
        int over35PercentCounter = 0;
        String longestString = "";
        for(String s: sr.data()){
            //print all the Strings in sr that are longer than 9 characters
            if(s.length() > 9){
                System.out.println("Over 9 " + s);
                //adds one for each string over 9
                over9Counter ++;
            }
            //print the Strings in sr whose C-G-ratio is higher than 0.35
            if(cgRatio(s) > 0.35){
                System.out.println("Over .035 " + s);
                //adds one for each string over 9
                over35PercentCounter ++;
            }
            //find the length of the longest gene in sr
            if(s.length() > longestString.length()){
                longestString = s;
            }
        }
        //print the number of Strings in sr that are longer than 9 characters
        System.out.println("Number of strings > 9 characters: " + over9Counter);
        //print the number of strings in sr whose C-G-ratio is higher than 0.35
        System.out.println("Number of strings > 0.35 C-G-ratio: " + over35PercentCounter);
        //print the length of the longest gene in sr
        System.out.println("Length of the longest string is " + longestString.length() + " (" + longestString + ")");
    }
    
    public void testProcessGenes(){
        /*
        //test values for processGenes method
        String[] dnaExamples= new String[]{
        //empty string
        "",
        //DNA string that has some genes longer than 9 characters (3, 1 not >9)
        "CCCATGCCCAAATTTTAGGCCCATGCCCGGGTTTAAATAACCCATGCCCTGACCCATGCCCGGGAAATAA",
        //DNA string that has no genes longer than 9 characters
        "atgaaataaatgtaacccatggggtag",
        //DNA string that has some genes whose C-G-ratio is higher than 0.35 (3, 1 not >0.35)
        "atgcccgggcccgggtaatttatgcccgggcccgggtgaatgaaatttaaataaatgcccgggcccgggcccgggtagaaa",
        //DNA string that has some genes whose C-G-ratio is lower than 0.35 (2 lower, 1 more)
        "CCCATGAAATTTAAATTTTAACCCATGGGGCCCGGGTGACCCATGAAATTTAAATTTTAGCCC"
        };
        
        int counter = 0;
        //stores each gene
        StorageResource geneList = new StorageResource();
        for(int i = 0; i<dnaExamples.length; i++){
        counter++;
        //prints out the restults of each example
        System.out.println("\nprocessGenes example " + counter + ": " + dnaExamples[i]);
        geneList = getAllGenes(dnaExamples[i]);
        processGenes(geneList);
        }
        */
        
        
        FileResource fr = new FileResource("brca1line.fa");
        String dna = fr.asString();

        //stores each gene
        StorageResource geneList = new StorageResource();
        //prints out the restults of each example
        System.out.println("brca1line.fa example: " + dna);
        geneList = getAllGenes(dna);
        //processGenes(geneList);
    }
}

