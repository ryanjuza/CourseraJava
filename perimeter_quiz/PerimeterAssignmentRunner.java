import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Initializes numPoints variable
        int numPoints = 0;
        // Loops through the points, adding one to numPoints for each point
        for (Point p : s.getPoints()){
            numPoints++;
        }
        return numPoints;
    }

    public double getAverageLength(Shape s) {
        // Initializes avgLength variable
        double avgLength = 0;
        // Uses getPerimeter to get the total length of all sides
        double total = getPerimeter(s);
        // Uses getNumPoints to get the total number of sides
        int sides = getNumPoints(s);
        // Divides the sum of all the sides by the number of sides to get the average
        avgLength = total/sides;
        return avgLength;
    }

    public double getLargestSide(Shape s) {
        // Start with largestSide = 0
        double largestSide = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            //if the current distance is greater than the largest side, it becomes the largest
            if(currDist > largestSide){
            largestSide = currDist;
            }
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        return largestSide;
    }

    public double getLargestX(Shape s) {
        // Initialize variable to store the largest X value
        double largestX = 0.0;
        // Start wth prevPt = the last point 
        for (Point currPt : s.getPoints()){
            //loops through points, setting it as the largest if it is larger than the current largest
            double currX = currPt.getX();
            if (currX > largestX){
               largestX = currX;   
            }
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Initialize variable to store the largest perimeter
        double largestPerimeterMulipleFiles = 0.0;
        // Create directory resource to get files from
        DirectoryResource dr = new DirectoryResource();
        // Loop through selected files and get their perimeters
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double currPerim = getPerimeter(s);
            // Compares perimeters, assigning the largest to the return variable
            if (currPerim > largestPerimeterMulipleFiles){
               largestPerimeterMulipleFiles = currPerim;
        }
    }
        return largestPerimeterMulipleFiles;
    }

    public String getFileWithLargestPerimeter() {
        // Initialize variable to store the largest perimeter 
        double largestPerimeterMulipleFiles = 0.0;
        // Initialize variable to store the largest perimeter file 
        File largestPerimFile = null; 
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double currPerim = getPerimeter(s);
            //assigns the return variable to the current file if it is the largest
            if (currPerim > largestPerimeterMulipleFiles){
               largestPerimeterMulipleFiles = currPerim;
               largestPerimFile = f;
        }
    }
        // Converts the file name to a string and returns it
        return largestPerimFile.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        int numPoints = getNumPoints(s);
        double avgLength = getAverageLength(s);
        double largestSide = getLargestSide(s);
        double largestX = getLargestX(s);
        System.out.println("Perimeter = " + length);
        System.out.println("Total points = " + numPoints);
        System.out.println("Average side length = " + avgLength);
        System.out.println("Largest side = " + largestSide);
        System.out.println("Largest X point = " + largestX);
        testPerimeterMultipleFiles();
        testFileWithLargestPerimeter();
       
    }
    
    public void testPerimeterMultipleFiles() {
        double largestPerimeterMulipleFiles = getLargestPerimeterMultipleFiles();
        System.out.println("The largest perimeter in the selected files is: " + largestPerimeterMulipleFiles);
    }

    public void testFileWithLargestPerimeter() {
        String fileWithLargestPerimeter = getFileWithLargestPerimeter();
        System.out.println("The file with the largest perimeter is: " + fileWithLargestPerimeter);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
    }
}
