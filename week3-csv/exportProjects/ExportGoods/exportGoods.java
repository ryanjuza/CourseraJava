
/**
 * Tester class for exporting goods csv project
 * 
 * @author: Ryan Juza
 * @version: 10142019
 */

import edu.duke.*;
import org.apache.commons.csv.*;

public class exportGoods {
    
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        // String countryExportsValue = countryInfo(parser, "Nauru");
        // System.out.println(countryExportsValue);

        // listExportersTwoProducts(parser, "gold", "diamonds");
        
        // String export = "sugar";
        // int numberOfCountries = numberOfExporters(parser, export);
        // System.out.println(numberOfCountries + " countries export " + export + ".");
        
        String value = "$999,999,999,999";
        bigExporters(parser, value);
    }

    public String countryInfo(CSVParser parser, String country){
        for(CSVRecord record : parser){
            String selectCountry = record.get("Country");
            if(selectCountry.contains(country)){
                String exports = record.get("Exports");
                String value = record.get("Value (dollars)");
                String countryExportsValue = selectCountry + ": " + exports + ": " + value;
                return countryExportsValue;
            }
        }
        return "NOT FOUND";
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        for(CSVRecord record : parser){
            String exports = record.get("Exports");
            if(exports.contains(exportItem1) && exports.contains(exportItem2)){
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem){
        int counter = 0;
        for(CSVRecord record : parser){
            String exports = record.get("Exports");
            if(exports.contains(exportItem)){
                counter++;
            }
        }
        return counter;
    }
    
    public void bigExporters(CSVParser parser, String selectedValue){
        for(CSVRecord record : parser){
            String value = record.get("Value (dollars)");
            String country = record.get("Country");
            if(value.length() > selectedValue.length()){
                System.out.println(country + " " + value);
            }
            
        }
    }
}



