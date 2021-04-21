package filesprocessing.filter;
import java.io.File;

/**
 * An interface for a file Filter.
 */
public interface Filter {

    /**
     * Filters the given File object according to this class' filter conditions.
     * @param file a File object
     * @return true if file passed the filter's condition, else false.
     */
    boolean filterFile(File file);

}
