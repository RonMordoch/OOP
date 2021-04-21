package filesprocessing.filter;
import java.io.File;

/**
 * A FileFilter object, always returns true for any given file.
 */
public class AllFilter implements Filter {

    /**
     * Filters the given File object according to this class' filter conditions.
     * @param file a File object
     * @return always true, by default.
     */
    public boolean filterFile(File file){
        return true;
    }
}
