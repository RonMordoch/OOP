package filesprocessing.filter;
import filesprocessing.filter.filenamefilters.*;
import filesprocessing.filter.sizefilters.*;
import filesprocessing.filter.permissionfilters.*;

/**
 * A factory class for Filter objects.
 */
public class FilterFactory {

    // The single instance of this class
    private static FilterFactory filterFactory = new FilterFactory();

    // Filter Types
    private static final String GREATER_THAN = "greater_than";
    private static final String BETWEEN = "between";
    private static final String SMALLER_THAN = "smaller_than";
    private static final String FILE = "file";
    private static final String CONTAINS = "contains";
    private static final String PREFIX = "prefix";
    private static final String SUFFIX = "suffix";
    private static final String WRITABLE = "writable";
    private static final String EXECUTABLE = "executable";
    private static final String HIDDEN = "hidden";
    private static final String ALL = "all";

    // NOT
    private static final String NOT = "#NOT";
    // DIVIDING CHAR
    private static final String DIVIDER = "#";
    private static final String EMPTY_STRING = "";
    // SPLITTING FILTER COMMAND STRING INTO ARRAY
    private static final int FILTER_NAME_IDX = 0;
    private static final int FIRST_VALUE_IDX = 1;
    private static final int SECOND_VALUE_IDX = 2;

    public static FilterFactory instance(){return filterFactory;}

    /**
     * Private constructor, prevents anyone from instantiating the class.
     */
    private FilterFactory() {
    }

    /**
     * Creates the default filter, i.e. all filter.
     * Used when we need to create the default filter when given a bad filter name
     * without checking the switch cases every time.
     * @return AllFilter object
     */
    public static Filter createDefault() {
        return new AllFilter();
    }

    /**
     * A function that creates a filter.
     * If the filter contains the #NOT value, calls itself recursively to create the regular filter ,and then
     * decorates the filter for the negation.
     * Else, creates the filter normally.
     * @param filterName name for filter
     * @return A filter object of given name
     * @throws FilterException if invalid input was given
     */
    public static Filter createFilter(String filterName) throws FilterException {
        if (filterName.contains(NOT)) { // create a decorated filter
            // replace the #NOT substring with an empty string
            String decoratedFilterName = filterName.replace(NOT, EMPTY_STRING);
            // returns the negated, decorated filter
            return new FilterDecorator(createFilter(decoratedFilterName));
        }// else, attempt to create normally
        String[] filterCommands = filterName.split(DIVIDER); // split the filter name into an array
        String name = filterCommands[FILTER_NAME_IDX]; // get the filter name
        switch (name) {
            case GREATER_THAN:
                if (filterCommands.length > 2){ // more than 2 values for comparison, invalid
                    throw new FilterException();
                }
                return new GreaterThanFilter(filterCommands[FIRST_VALUE_IDX]);
            case BETWEEN:
                return new BetweenFilter(filterCommands[FIRST_VALUE_IDX],
                        filterCommands[SECOND_VALUE_IDX]);
            case SMALLER_THAN:
                if (filterCommands.length > 2){ // more than 2 values for comparison, invalid
                    throw new FilterException();
                }
                return new SmallerThanFilter(filterCommands[FIRST_VALUE_IDX]);
            case FILE:
                return new FileFilter(filterCommands[FIRST_VALUE_IDX]);
            case CONTAINS:
                return new ContainsFilter(filterCommands[FIRST_VALUE_IDX]);
            case PREFIX:
                return new PrefixFilter(filterCommands[FIRST_VALUE_IDX]);
            case SUFFIX:
                return new SuffixFilter(filterCommands[FIRST_VALUE_IDX]);
            case WRITABLE:
                    if (filterCommands.length == 1){ // no YES/NO value for comparision, invalid
                        throw new FilterException();
                    }
                return new WritableFilter(filterCommands[FIRST_VALUE_IDX]);
            case EXECUTABLE:
                if (filterCommands.length == 1){  // no YES/NO value for comparision, invalid
                    throw new FilterException();
                }
                    return new ExecutableFilter(filterCommands[FIRST_VALUE_IDX]);
            case HIDDEN:
                if (filterCommands.length == 1){  // no YES/NO value for comparision, invalid
                    throw new FilterException();
                }
                return new HiddenFilter(filterCommands[FIRST_VALUE_IDX]);
            case ALL:
                return new AllFilter();
             default: // Invalid filter name,exception caught later and used for creating default filter
                throw new FilterException();

        }
    }
}

