package filesprocessing.filter.sizefilters;
import java.io.File;

/**
 * A Contains File Filter, extends FileNameFilter.
 * Filters files by file's size in comparison to a lower bound.
 */
public class GreaterThanFilter extends SizeFilter {

    // lower bound for comparison (exclusive)
    private double lowerBound;

    /**
     * A constructor for GreaterThanFilter.
     * @param lowerBound a string representing a lower bound for comparison
     * @throws NegativeSizeException if lower bound is negative
     */
    public GreaterThanFilter(String lowerBound) throws NegativeSizeException{
        double doubleLowerBound = parseDouble(lowerBound);
        if (doubleLowerBound < 0){
            throw new NegativeSizeException();
        } // else, valid values
        this.lowerBound = doubleLowerBound;
        }

    /**
     * Filters the given File object according to this class' filter conditions.
     * @param file a File object
     * @return true if file's size greater than the lower bound, else false.
     */
    @Override
    public boolean filterFile(File file){
        double fileLength = (double)file.length();
        return fileLength > this.lowerBound;
    }
}
