package filesprocessing.section;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Parse the commands file and returns an array list of Sections.
 */
public class Parser {

    // Constant names for Order, Filter, and default order
    private static final String FILTER = "FILTER";
    private static final String ORDER = "ORDER";
    private static final String DEFAULT_ORDER = "abs";

    /**
     * Parses a given command file and returns an array list of Sections objects.
     * @param commandFile a text file containing commands for filtering and ordering objects.
     * @return an array list of Section objects
     * @throws IOException if command file was invalid
     * @throws ParserException if command file has invalid format
     */
    public ArrayList<Section> parseCommandFile(File commandFile) throws IOException, ParserException {
        ArrayList<Section> sectionsArrayList = new ArrayList<>();
        FileReader fileReader = new FileReader(commandFile);
        BufferedReader reader = new BufferedReader(fileReader);
        String line = reader.readLine();
        int lineIndex = 1;
        while (line != null) {
            // the values for each iteration? change to empty strings to represent defaults?
            String filterName;
            String orderName;
            if (line.equals(FILTER)){
                // progress to next line
                line = reader.readLine();
                lineIndex++;
            }
            else { // no FILTER sub-section exists, throw exception - CATCH LATER IN MAIN
                throw new ParserException();
            }
            // now we are necessarily at the filter name line
            // divide between cases
            // if ORDER comes right after FILTER, its either a bad FILTER name of type 2 error
            if(line == null){
                throw new ParserException();
            }
            if (line.equals(ORDER)){
                filterName = line;
            }
            // else we have some kind of FILTER name, might be valid or invalid
            else {
                filterName = line;
            }
            // continue to next line
            line = reader.readLine();
            lineIndex++;
            // if line is null, we have no ORDER subsection, throw exception
            if (line == null) {
                throw new ParserException();
            }
            // else, if line = ORDER, we have an ORDER sub-section
            if (line.equals(ORDER)){
                line = reader.readLine();
                lineIndex++;
                // continue to next line, if line is null, use default AbsOrder
                if (line == null){
                    // use default order
                    orderName = DEFAULT_ORDER;
                    Section section = new Section(filterName, orderName, lineIndex - 3);
                    sectionsArrayList.add(section);
                    line = reader.readLine();
                    lineIndex++;
                    // check for nullity after every line increment
                    if (line == null){break;}
                }
                if (line.equals(FILTER)){ // no order defined, use AbsOrder
                    orderName = DEFAULT_ORDER;
                    Section section = new Section(filterName, orderName, lineIndex - 3);
                    sectionsArrayList.add(section);
                }
                else { // else, we have some kind of order name
                    orderName = line;
                    Section section = new Section(filterName, orderName, lineIndex - 3);
                    sectionsArrayList.add(section);
                    line = reader.readLine();
                    lineIndex++;
                    if (line == null){break;}

                }
            }
            else { // else, line is neither null nor ORDER, no ORDER subsection, throw exception
                throw new ParserException();
            }
        }
        return sectionsArrayList;
        }
}
