package filesprocessing.filter.filenamefilters;
import java.io.File;

/**
 * A Contains File Filter, extends FileNameFilter.
 * Filters files by containment of a String in file's name.
 */
public class ContainsFilter extends FileNameFilter {

    /**
     * Constructor, calls the default constructor.
     * @param value the String value for comparison.
     */
    public ContainsFilter(String value){
        super(value);
    }

    /**
     * Filters the given File object according to this class' filter conditions.
     * @param file a File object
     * @return true if file's name contains this.value, else false.
     */
    @Override
    public boolean filterFile(File file){
        return file.getName().contains(this.value);
    }
}
