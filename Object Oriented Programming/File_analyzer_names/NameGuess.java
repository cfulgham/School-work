import java.io.*;
import java.util.*;

/**
 * NameGuess class for finding most common years a name appears in.
 * Allows user to load data from a specified data path. Sorts data and gives
 * results for name given by user.
 * @author Christopher Fulgham
 * @version 1.0
 */

public class NameGuess {

    private String dataPath;
    private String resPath;
    private int t1;
    HashMap<String, HashMap<String, Integer>> nameCounter = new HashMap<String, HashMap<String, Integer>>();

    /**
     *  Constructor sets input path and output path for class and sets default
     *  size for results to 10.
     * @param inPath String for full data path to folder containing input files
     * @param outPath String for full data path to folder to store output files
     */
    public NameGuess(String inPath, String outPath){

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
    public NameGuess(String inPath, String outPath, int count){

        resPath=outPath;
        dataPath=inPath;
        t1=count;
    }

    /**
     * Receives a name, prints the data for the name or if name is not found
     * analyzes data looking for name.
     * @param name String for name being searched for
     */
    public void Guess(String name){

        boolean wasAdded;

        if (nameCounter.containsKey( name )) {
            //call Printer and return
            this.Printer( name );

        } else {

            wasAdded = this.Analyzer( name );
            if( wasAdded ){
                this.Printer( name );
            }
            else{
                System.out.println( "Name not found in database.");
            }

        }
        return;
    }

    /**
     * Prints to the console the years for the name provided is most common.
     * Number of years determined by value provided when object is instantiated.
     * @param name String for name user is looking to output
     */
    private void Printer(String name){

        System.out.println("***********************************************");
        String tmp = "I guess you were born in one of these " + String.valueOf(t1) + " years: ";
        System.out.println(tmp);
        for ( String key : nameCounter.get( name ).keySet() )
        {
            System.out.println( key );
        }
        System.out.println("***********************************************");
        System.out.println("");
        return;

    }

    /**
     * Uses name provided to search through all files in input data path.
     * Requires documents be .txt with year in file name and formatted:
     * name, sex, number
     * to function. Ignores .pdf in folder.
     * If name is found year it was found in will be stored.
     * @param name String for name user is searching for.
     * @return True if name was found in data and false otherwise.
     */
    public boolean Analyzer(String name){

        File[] files = new File(dataPath).listFiles();
        boolean res=false;
        String tempLineHolder;
        String year;

        //create a hash map for t1 (e.g. 5) years with highest probability
        HashMap<String, Integer> yearMap = new HashMap<>();

        for (File file : files){

            String fileName = file.getName();
            //read the file line by line
            try{
                year = file.getName().substring( 3, 7 );
                if (!file.getName().contains( ".pdf" )) {

                    FileInputStream fileByteStream = new FileInputStream(dataPath + "\\" + file.getName());
                    Scanner inFS = new Scanner(fileByteStream);

                    while( inFS.hasNext() ) {

                        tempLineHolder = inFS.nextLine();
                        String[] tempLineArray = tempLineHolder.split(",");
                        if (tempLineArray[0].contains(name)) {

                            yearMap = this.AddYear(yearMap, year, tempLineArray[2]);
                            res = true;

                        }
                    }

                }

            }

            catch (FileNotFoundException e){

                e.printStackTrace();

            }

        }
        if(res){

            nameCounter.put( name , yearMap );

        }

        return(res);
    }

    /**
     * Compares the number of times a name is used in a year to other years.
     * Creates a set of years where the name is most commonly used in data.
     * @param yearMap Hashmap used to store years and occurrences of name
     * @param year String of year currently being used
     * @param value String for number of occurrences of name
     * @return HashMap containing the updated list of years.
     */
    private HashMap<String,Integer> AddYear(HashMap<String,Integer> yearMap, String year, String value){

        Integer valueToInt = Integer.parseInt( value );
        String[] keys = new String[t1];
        Integer[] values = new Integer[t1];
        String lowKey;
        int lowestValue;
        int i = 0;
        int j;


        //Hash map still has empty cells
        if(yearMap.size()<t1){

            yearMap.put( year, valueToInt );

        }
        else { //Hash map is full, maybe we need to replace one item

            for ( String key : yearMap.keySet()){
                keys[i] = key;
                values[i] = yearMap.get( key );
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

            if ( lowestValue < valueToInt ){
                yearMap.remove( lowKey );
                yearMap.put( year, valueToInt );
            }

        }
        return yearMap;
    }

}


