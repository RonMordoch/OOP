package filesprocessing.filter.permissionfilters;
import java.io.File;

/**
 * A Contains File Filter, extends FileNameFilter.
 * Filters files by file's executable permission.
 */
public class WritableFilter extends PermissionFilter {

    /**
     * Constructor, calls the default constructor.
     * @param value the String value representing boolean for comparison.
     * @throws PermissionFilterException if value is invalid
     */
    public WritableFilter(String value) throws PermissionFilterException{
        super(value);
    }

    /**
     * Filters the given File object according to this class' filter conditions.
     * @param file a File object
     * @return true if file passed the filter's writable condition, else false.
     */
    @Override
    public boolean filterFile(File file) {
        if (this.value.equals(YES)){
            return file.canWrite();
        }
        else{ //(this.value.equals(NO))
            return !file.canWrite();
            }
        }
}
