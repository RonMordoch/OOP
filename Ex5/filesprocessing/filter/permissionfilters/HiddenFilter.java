package filesprocessing.filter.permissionfilters;
import java.io.File;

/**
 * A Contains File Filter, extends FileNameFilter.
 * Filters files by file's executable permission.
 */
public class HiddenFilter extends PermissionFilter {

    /**
     * Constructor, calls the default constructor.
     * @param value the String value representing boolean for comparison.
     * @throws PermissionFilterException if value is invalid
     */
    public HiddenFilter(String value) throws PermissionFilterException{
        super(value);
    }

    /**
     * Filters the given File object according to this class' filter conditions.
     * @param file a File object
     * @return true if file passed the filter's hidden condition, else false.
     */
    @Override
    public boolean filterFile(File file) {
        if (this.value.equals(YES)){
            return file.isHidden();
        }
        else{ //(this.value.equals(NO))
            return !file.isHidden();
        }
    }
}
