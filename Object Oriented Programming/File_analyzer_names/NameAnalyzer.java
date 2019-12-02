import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.FileInputStream;
import java.util.*;

import java.io.*;

/**
 * NameAnalyzer class for finding the top names for year.
 * Searches through the data in the path provided for a file containing
 * the year supplied to methods. If the year is found will add the top names for that year
 * to analyzed data. Provides methods to print data to console or to files.
 * @author Christopher Fulgham
 * @version 1.0
 */
public class NameAnalyzer{

    private String dataPath; //folder containing input information
    private String resPath; //folder to write results to
    private int t1; //number of names to return
    HashMap< String, HashMap< String, Integer > > yearRecords = new HashMap< String, HashMap< String, Integer > >();

    /**
     * Constructor sets input path and output path for class and sets default
     *  size for results to 10.
     * @param inPath String for full data path to folder containing input files
     * @param outPath String for full data path to folder to store output files
     */
    public NameAnalyzer( String inPath, String outPath ){

            resPath=outPath;
            dataPath=inPath;
            t1=10;
        }

    /**
     * Constructor sets input path and output path for class and sets default
     * size for results to user provided value.
     * @param inPath String for full data path to folder containing input files
     * @param outPath String for full data path to folder to store output files
     * @param count int for setting size of results
     */
    public NameAnalyzer( String inPath, String outPath, int count ){

            resPath=outPath;
            dataPath=inPath;
            t1=count;
        }

    /**
     * Looks for a specific year in data, if year is found will add information
     * to processed data.
     * If year is not found will state "File for year: year not found."
     * @param year String year for data to be printed
     */
    public void FileReader( String year ){

        boolean found = false; //Stores if year is found in data.

        if( yearRecords.containsKey( year ) ){ //the key is already in the hashmap

        }
        else {
            found = this.Analyzer( year );//call analyzer
            if ( found ){

                this.Printer( year );
            }
            else{

                System.out.println( "File for year: " + year + " not found." );
            }

            return;
        }
    }

    /**
     * Prints top names for year provided.
     * Number of names determined when object is instantiated.
     * @param year String for year data to be printed
     */
    public void Printer( String year ){

        System.out.println( "***********************************************" );
        String tmp= "Top " + String.valueOf( t1 ) + " names in " + year + ":";
        System.out.println( tmp );

        //print  results
        for ( String key : yearRecords.get( year ).keySet() ){
            System.out.println( key + ": " + yearRecords.get( year ).get( key ));
        }

        System.out.println( "***********************************************" );
        System.out.println("");
        return;
    }

    /**
     * Analyzer searches through all files in input path for one containing desired year.
     * Opens file if found and analyzes for top names for that year.
     * @param year String year being analyzed for top names
     * @return Boolean returns true if year was added and false if not
     */
    public boolean Analyzer( String year ){

        String tempLineHolder; //temporary holder for a line from file

        File[] files = new File( dataPath ).listFiles();

        for ( File file : files ) {
            if (file.getName().contains( year )) {
                //create a hash map for top t1 (e.g. 10) names of this year
                HashMap< String, Integer > record = new HashMap<>( t1 );

                //read the file line by line and added to yearRecords
                try{

                    FileInputStream fileByteStream = new FileInputStream(dataPath + "\\" + file.getName());
                    Scanner inFS = new Scanner(fileByteStream);

                    //Read line from file, parse into an array and store into yearRecords.
                    while( inFS.hasNext() ){
                        tempLineHolder = inFS.nextLine();
                        String[] tempLineArray = tempLineHolder.split(",");
                        record = this.AddName(record, tempLineArray[0], Integer.parseInt( tempLineArray[2] ) );
                        yearRecords.put(year, record);
                    }


                    fileByteStream.close();

                }
                catch ( FileNotFoundException e ){
                    e.printStackTrace();
                }
                catch ( IOException e ){
                    e.printStackTrace();
                }

                //Entry was added to yearRecords
               return( true );
            }

        }
        //No entry added.
        return( false );
    }

    /**
     *  Adds a name to the provided Hashmap. Only this top most occurring names
     *  will be added. Total number of names determined when object is instantiated.
     * @param nameMap HashMap for names and occurrences in year
     * @param name String name being added
     * @param value Integer number of occurrences
     * @return HashMap HashMap with name added
     */
    private HashMap< String,Integer > AddName( HashMap< String, Integer > nameMap, String name, Integer value ){

        String lowKey;  //Stores lowest key based on value
        int lowestValue = 0; //Stores lowest value in hashmap
        int i = 0; //index for loops
        int j;    //index for loops
        String[] keys = new String[t1]; //Array of keys used to find lowest value
        int[] values = new int[t1];     //Array of values use to find lowest value

        //Hash map still has empty cells
        if( nameMap.size()<t1 ){

            nameMap.put( name, value );

        }
        else { //Hash map is full, we may need to replace one item

            //sort and get the lowest value of the hashmap
            for ( String key : nameMap.keySet()){
                keys[i] = key;
                values[i] = nameMap.get( key );
                ++i;
            }

            lowestValue = values[0];
            lowKey = keys[0];
            for ( j = 1; j < (i); ++j ){
                if ( lowestValue > values[j])
                {
                    lowestValue = values[j];
                    lowKey = keys[j];

                }
            }

            //if the lowest value is still greater than new value, skip
            //otherwise, replace it with the new value
            if ( lowestValue < value ){
                nameMap.remove( lowKey );
                nameMap.put( name, value );
            }

        }
        return nameMap;

    }

    /**
     * Saves data for a give year to a file in output data pata.
     * Names file in format "yobYEAR.txt"
     */
    public void Writer(){

        String fileName; //Stores output file name

        String[] Keys= yearRecords.keySet().toArray( new String[0] );
        for( String K1 : Keys ){
            try{
                //Create file name and open
                fileName = resPath + "\\yob" + K1 + ".txt";
                FileWriter fileStream = new FileWriter(fileName);
                BufferedWriter outFS = new BufferedWriter(fileStream);

                //Write entry to file
                for ( String key : yearRecords.get(K1).keySet())
                {
                    outFS.write( key + ", " + yearRecords.get(K1).get(key) );
                    outFS.newLine();
                }

                //Close file, ensuring information is written to file.
                outFS.flush();
                fileStream.close();
            }
            catch ( Exception e ){
                System.out.println( e );
            }
        }

        //Inform user where the files created are located.
        System.out.print( "All results are saved in:" );
        System.out.print( resPath );

    }

}
