package filesprocessing.filter;
import java.io.File;

/**
 * A decorator class for a Filter object, used for negation of the Filter when created with the #NOT suffix.
 */
public class FilterDecorator implements Filter {

    // The Filter object of this decorator
    private Filter decoratedFilter;

    /**
     * Creates a decorated Filter.
     * @param decoratedFilter the filter we decorate.
     */
    public FilterDecorator(Filter decoratedFilter) {this.decoratedFilter = decoratedFilter;}

    /**
     * Negates the decorated Filter's filterFile, i.e., returns the opposite boolean value.
     * @param file a File object
     * @return true if original filter returns false, false if original filter returned true.
     */
    public boolean filterFile(File file) {return !decoratedFilter.filterFile(file);}
}
