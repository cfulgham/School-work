import java.util.Scanner;

/**
 * NameMain interface for finding popular names in United States.
 * This class contains program's main() and is not mean to be instantiated.
 * @author Christopher Fulgham
 * @version 1.0
 */
public class NameMain{
    /**
     * Prints a menu for the program to the console and prompts the user to
     * make a selection.
     * @param scnr for input of menu choice
     * @return a char value representing the user's choice on the menu.
     */
    public static char menu( Scanner scnr ){

        char choice;

        System.out.println("Menu ");
        System.out.println("A: Set Input/Output path (Must be done first)");
        System.out.println("B: Run analyzer to get top names per year");
        System.out.println("C: Guess the year you were born");
        System.out.println("Q: Quit");
        System.out.println("Please make a selection:");

        choice = scnr.next().charAt(0);
        scnr.nextLine();

        return choice;

    }

    /**
     * Asks the user to provide input and output directories. Uses NameAnalyzer class
     * to find the top 10 names for each year in provided input. Uses NameGuess to find
     * top 10 years the user provide name was used.
     * @param args command-lane arguments NOTE: currently not in use.
     */
    public static void main(String[] args) {

        Scanner scnr = new Scanner(System.in);
        String inPath = null;
        String outPath = null;
        String name;
        int numOfNames = 10;
        int year;
        NameAnalyzer analyzer;
        NameGuess guesser;
        char menuChoice = 'o';
        boolean filePathsLoaded = false;

        menuChoice = menu( scnr );

        while ( (menuChoice != 'q') && (menuChoice != 'Q') ){

            if ( (menuChoice == 'a') || (menuChoice == 'A') ){

                System.out.println( "Please enter the file path for input data:" );
                inPath = scnr.nextLine();
                System.out.println( "Please enter the file path for output data:" );
                outPath = scnr.nextLine();
                filePathsLoaded = true;
                System.out.println("File paths loaded you may now select other options.");
                System.out.println("");

            }
            else if ( ( (menuChoice == 'b') || (menuChoice == 'B') ) && filePathsLoaded ){

                analyzer = new NameAnalyzer( inPath, outPath, numOfNames );

                year = 1880;
                while ( year < 2019 ){

                    analyzer.FileReader( String.valueOf(year) );
                    ++year;
                }
                //analyzer.FileReader( String.valueOf(year));

                analyzer.Writer();

            }
            else if ( ( (menuChoice == 'c') || (menuChoice == 'C') ) && filePathsLoaded ){

                System.out.println("");
                System.out.println( "Enter a name and I'll tell you a year." );
                name = scnr.nextLine();


                guesser = new NameGuess( inPath, outPath, numOfNames );
                guesser.Guess( name );

            }
            else{

                if ( filePathsLoaded ) {
                    System.out.println( "Invalid Selection made. Please select again." );
                }
                else{
                    System.out.println( "File paths not loaded, please select option A first" );
                }
            }

            menuChoice = menu( scnr );
        }

    }
}
