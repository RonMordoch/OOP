package filesprocessing;

import filesprocessing.filter.Filter;
import filesprocessing.filter.FilterException;
import filesprocessing.filter.FilterFactory;
import filesprocessing.order.Order;
import filesprocessing.order.OrderException;
import filesprocessing.order.OrderFactory;
import filesprocessing.section.Parser;
import filesprocessing.section.ParserException;
import filesprocessing.section.Section;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The manager of the program.
 * Receives a source directory for file and a commands file and runs the program.
 */
public class DirectoryProcessor {

    // Indexes of args in command line
    private static final int SOURCEDIR = 0;
    private static final int COMMANDSFILE = 1;
    private static final int ARGS_NUM = 2;

    // Message constants
    private static final String WARNING = "Warning in line ";
    private static final String INVALID_USAGE =
            "ERROR: Invalid usage error - more than 2 arguments were given.\n";
    private static final String IO_MSG =
            "ERROR: I/O error - commands file is invalid.\n";
    private static final String INVALID_FORMAT_MSG =
            "ERROR: Bad format error - invalid format of commands file.\n";

    // Sorter and Parser objects
    private static Sorter sorter = new Sorter();
    private static Parser parser = new Parser();

    /**
     * Gets all the files in the source dir.
     * Assumes source directory exists per exercise instructions.
     * @param sourceDir sourceDir file which contains files.
     * @return an array list of all files in sourceDir, excluding folders
     */
    private static ArrayList<File> getFileInDir(File sourceDir) throws InvalidUsageException{
        File[] fileInSourceArray = sourceDir.listFiles(); // we may assume sourceDir is an existing directory
        if (fileInSourceArray == null) { // check if folder is empty before iterating over it
            throw new InvalidUsageException();
        }
        ArrayList<File> filesInSourceList = new ArrayList<>();
        for (File file: fileInSourceArray){
            // choose only files, not folders
            if (file.isFile()){
                filesInSourceList.add(file);
            }
        }
        return filesInSourceList;
    }

    /**
     * Parses the sections and iterates over them for printing Type 1 errors and files.
     * @param filesInSourceList all files in sourceDir
     * @param commandFile commands file
     */
    private static void parseSection(ArrayList<File> filesInSourceList, File commandFile){
        ArrayList<Section> sectionsList = new ArrayList<>();
        try {
            sectionsList = parser.parseCommandFile(commandFile);
        } // invalid format of commands file
        catch (ParserException e) {
            System.err.println(INVALID_FORMAT_MSG);
        } // invalid commands file, though we may assume commands file is an existing file, we can not be
        // sure of what type it is, or if it is damaged
        catch (IOException e){
            System.err.println(IO_MSG);
        } // else, command file is valid, process each section with helper function
        for (Section section: sectionsList){
            sectionManager(filesInSourceList, section);
        }
    }

    // creates the filter and order for the section
    // responsible for printing type 1 errors and filtering and ordering files

    /**
     * Creates the filter and order for a given section.
     * Responsible for printing Type 1 errors and filtering and ordering files.
     * @param filesInSourceList all files in sourceDir
     * @param section an object of type Section.
     */
    private static void sectionManager(ArrayList<File> filesInSourceList, Section section){
        Filter filter;
        Order order;
        try{
            filter = FilterFactory.createFilter(section.getFilterName());
        }
        catch (FilterException e){ // invalid filter name, use default AllFilter
            System.err.println(WARNING + section.getFilterLine());
            filter = FilterFactory.createDefault();
        }
        try {
            order = OrderFactory.createOrder(section.getOrderName());
        }
        catch (OrderException e){ //invalid order name, use default AbsOrder
            System.err.println(WARNING + section.getOrderLine());
            order = OrderFactory.createDefault();
        }
        // filter files, sort by order, print
        ArrayList<File> filteredFiles = filterFiles(filesInSourceList, filter);
        sorter.quickSort(filteredFiles, order, 0, filteredFiles.size() - 1);
        for (File file: filteredFiles){
            System.out.println(file.getName());
        }
    }

    /**
     * Filters all files in a given array list of files.
     * @param filesArray array list of file objects
     * @param filter the filter we use for filtering the files
     * @return an array list of filtered files
     */
    private static ArrayList<File> filterFiles (ArrayList<File> filesArray, Filter filter){
        ArrayList<File> filteredFiles = new ArrayList<>();
        for (File file : filesArray){
            // only filter valid files
            if (filter.filterFile(file)){
                filteredFiles.add(file);
            }
        }
        return filteredFiles;
    }

    /**
     * Validates the arguments received from command line.
     * @param args arguments received via command line
     * @throws InvalidUsageException if a different number of requited arguments was received
     */
    private static void validateArguments(String[] args) throws InvalidUsageException{
        if (args.length != ARGS_NUM){
            throw new InvalidUsageException();
        }
    }

    /**
     * Main function for program.
     * Receives arguments and prints the warnings and files.
     * @param args sourceDir and commands file.
     */
    public static void main(String[] args){
        try {
            validateArguments(args);
            File sourceDir = new File(args[SOURCEDIR]);
            File commandsFile = new File(args[COMMANDSFILE]);
            ArrayList<File> fileInSourceDir = getFileInDir(sourceDir);
            parseSection(fileInSourceDir, commandsFile);
        }
        catch (InvalidUsageException e){ // Invalid commands file
            System.err.println(INVALID_USAGE);
        }

    }

}