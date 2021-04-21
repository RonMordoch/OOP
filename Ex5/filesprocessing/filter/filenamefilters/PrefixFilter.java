package filesprocessing.filter.filenamefilters;
import java.io.File;

/**
 * A Contains File Filter, extends FileNameFilter.
 * Filters files by equality of a String and file's name prefix.
 */
public class PrefixFilter extends FileNameFilter{

    /**
     * Constructor, calls the default constructor.
     * @param value the String value for comparison.
     */
    public PrefixFilter(String value){
        super(value);
    }

    /**
     * Filters the given File object according to this class' filter conditions.
     * @param file a File object
     * @return true if file's name prefix equals this.value, else false.
     */
    @Override
    public boolean filterFile(File file) {
        return file.getName().startsWith(this.value);
    }
}
