
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
        
        // String countryExportsValue = countryInfo(parser, "Germany");
        // System.out.println(countryExportsValue);
        
        listExportersTwoProducts(parser, "gold", "diamonds");
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
}



