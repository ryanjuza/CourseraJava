
/**
 * Project for finding put temperature info
 * 
 * @author Ryan Juza
 * @version 10142019
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class Temperatures {
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord coldestHour = null;
        for(CSVRecord currentRow : parser){
            if (coldestHour == null) {
                coldestHour = currentRow;
            }
            else{
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double lowestTemp = Double.parseDouble(coldestHour.get("TemperatureF"));
                if(currentTemp < lowestTemp){
                    coldestHour = currentRow;
                }
            }
        }
    return coldestHour;
    }
    
    public void testColdestHourInFile(){
        FileResource fr = new FileResource("nc_weather/2012/weather-2012-01-01.csv");
	CSVRecord lowest = coldestHourInFile(fr.getCSVParser());
	System.out.println("Coldest temperature was " + lowest.get("TemperatureF") +
				   " at " + lowest.get("TimeEST"));
    }
    
    public String fileWithColdestTemperature(){
        CSVRecord fileWithColdest = null;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            fileWithColdest = getSmallestOfTwo(currentRow, fileWithColdest);
        }
    return fileWithColdest.getName();
    }
    
    public CSVRecord getSmallestOfTwo(CSVRecord currentRow, CSVRecord smallestSoFar) {
		if (smallestSoFar == null) {
			smallestSoFar = currentRow;
		}
		//Otherwise
		else {
			double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
			double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
			//Check if currentRow’s temperature > largestSoFar’s
			if (currentTemp < smallestTemp) {
				//If so update largestSoFar to currentRow
				smallestSoFar = currentRow;
			}
		}
		return smallestSoFar;
	}
    
    public void testFileWithColdestTemperature(){
        
    }
}
