import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * A class the run tests in order to analyze the performance of several data structures implementing the
 * SimpleSet interface.
 */
public class SimpleSetPerformanceAnalyzer {

    private static final String openHashSet = "o";
    private static final String closedHashSet = "c";
    private static final String treeSet = "t";
    private static final String linkedList = "l";
    private static final String hashSet = "h";
    private static String stringOne = "hi";
    private static String stringTwo = "-13170890158";
    private static String stringThree = "23";
    private static final String CHOOSE_SET = "Please select any of the following data structures:\n " +
            "o - OpenHashSet\n " +
            "c - ClosedHashSet \n " +
            "t - TreeSet\n " +
            "l - LinkedList\n " +
            "h - HashSet\n ";
    private static final String CHOOSE_TEST = "Please select one of the following tests:\n" +
            "1 - add(data1.txt)\n"  +
            "2 - add(data2.txt)\n" +
            "3 - contains(hi) on data1.txt\n" +
            "4 - contains(-13170890158) on data1.txt\n" +
            "5 - contains(23) on data2\n" +
            "6 - contains(hi) on data2";

    private static final String[] data1Array = Ex4Utils.file2array("data1.txt");
    private static final String[] data2Array = Ex4Utils.file2array("data2.txt");


    private static final long DIVIDE_BY_MIL = 1000000;
    private static final int ITERATIONS = 70000;
    private static final int LINKED_LIST_ITERATIONS = 7000;

    /**
     * Receives an array of strings representing data structures and creates the matching structures wrapped
     * in a SimpleSet[] array.
     * A factory-like method for creating data structures.
     * @param lettersArray array of letter representing data structures
     * @return an array of data structures implementing SimpleSet interface.
     */
    private static SimpleSet[] createArray(String[] lettersArray){
        SimpleSet[] setArray = new SimpleSet[lettersArray.length];
        for (int i = 0; i < lettersArray.length ; i++) {
            switch (lettersArray[i]){
                case openHashSet:
                    setArray[i] = new OpenHashSet();
                    break;
                case closedHashSet:
                    setArray[i] = new ClosedHashSet();
                    break;
                case treeSet:
                    setArray[i] = new CollectionFacadeSet(new TreeSet<String>());
                    break;
                case linkedList:
                    setArray[i] = new CollectionFacadeSet(new LinkedList<String>());
                    break;
                case hashSet:
                    setArray[i] = new CollectionFacadeSet(new HashSet<String>());
                    break;
            }
        }
        return setArray;
    }

    /**
     * Runs the chosen test.
     * @param setArray array of data structures implementing the SimpleSet interface.
     * @param lettersArray array of strings representing data structures.
     * @param test a number representing the choosen test
     */
    private static void runTests(SimpleSet[] setArray,
                                 String[] lettersArray,
                                 int test) {
        if (test == 1) {
                add(setArray, lettersArray, data1Array);
        } else if (test == 2) {
            add(setArray, lettersArray, data2Array);
        } else if (test == 3) {
                contains(setArray, data1Array, lettersArray, stringOne);
        } else if (test == 4) {
                contains(setArray, data1Array, lettersArray, stringTwo);
        } else if (test == 5) {
                contains(setArray, data2Array, lettersArray, stringThree);
        } else if (test == 6) {
            contains(setArray, data2Array, lettersArray, stringOne);
        }
    }

    /**
     * Runs the 'add' tests.
     * @param setArray array of data structures implementing the SimpleSet interface.
     * @param lettersArray array of strings representing data structures.
     * @param stringArray an array representing a data file.
     */
    private static void add(SimpleSet[] setArray, String[] lettersArray, String[] stringArray) {
        for (int i = 0; i < setArray.length; i++) {
            SimpleSet set = setArray[i];
            String setLetter = lettersArray[i];
            long timeBefore = System.nanoTime();
            for (String string : stringArray) {
                set.add(string);
            }
            long difference = System.nanoTime() - timeBefore;
            System.out.println("Adding text to " + setLetter + " took " + translateToMilli(difference) +
                    " milliseconds.");
        }
    }
    /**
     * Runs the 'contains' tests.
     * @param setArray array of data structures implementing the SimpleSet interface.
     * @param lettersArray array of strings representing data structures.
     * @param stringArray an array representing a data file.
     * @param searchValue the string we are searching for in the data structure.
     */
    private static void contains(SimpleSet[] setArray,
                                 String[] stringArray,
                                 String[] lettersArray,
                                 String searchValue
                                 ){
        for(int i=0; i<setArray.length; i++){
            SimpleSet set = setArray[i];
            String setLetter = lettersArray[i];
            for (String string: stringArray){
                set.add(string); // initialize set with the given data text
            }
            long difference;
            if (setLetter.equals(linkedList)){ // run tests without warmup
                difference = calculateContains(set, searchValue, LINKED_LIST_ITERATIONS, false);
            } else{ // run tests with warmup
                difference = calculateContains(set, searchValue,ITERATIONS, true);
            }
            System.out.println("Checking if " + setLetter + " contains " + searchValue +
                    " took " + difference + " nanoseconds.");
    }}

    /**
     * Runs the contain method on a given set.
     * @param set a data structure implementing the SimpleSet interface
     * @param searchValue the string we are searching for in the data structure
     * @param iterations number of iterations to calculate
     */
    private static void runContains(SimpleSet set, String searchValue, int iterations){
        for(int i=0; i< iterations; i++){
            set.contains(searchValue);
        }
    }

    /**
     * Calculates the time it took to run the 'contains' test.
     * @param set a data structure implementing the SimpleSet interface
     * @param searchValue the string we are searching for in the data structure
     * @param iterations number of iterations to calculate
     * @param warmup false if set is a LinkedList, else true
     * @return the time it took to run the tests
     */
    private static long calculateContains(SimpleSet set, String searchValue, int iterations, boolean warmup){
        if(warmup){ // iff data structure is not a LinkedList
            runContains(set, searchValue, iterations);}
        long timeBefore = System.nanoTime();
        runContains(set, searchValue, iterations); // 70,000 iterations tests
        long difference = System.nanoTime() - timeBefore;
        difference /= iterations;
        return difference;
    }

    /**
     * Translates time in nanoseconds to milliseconds.
     * @param timeInNano time in nanoseconds
     * @return time in milliseconds
     */
    private static long translateToMilli(long timeInNano){ return (timeInNano / DIVIDE_BY_MIL); }

    /**
     * Main method of class, receives input of data structures to create and which test to run and
     * operates accordingly.
     * @param args an array of string representing data structures
     */
    public static void main(String[] args){
        Scanner inputReader = new Scanner(System.in);
        System.out.println(CHOOSE_SET); // shows the welcome message with type of data structures
        String letters = inputReader.nextLine();
        String[] lettersArray = letters.split(" "); // create an array of letters
        SimpleSet[] setArray = createArray(lettersArray); // create an array of chosen sets
        System.out.println(CHOOSE_TEST); // shows the tests available
        int test = inputReader.nextInt();
        runTests(setArray, lettersArray, test); // run the desired test with the chosen data structures

    }
}

