package filesprocessing.filter.sizefilters;
import java.io.File;

/**
 * A Contains File Filter, extends FileNameFilter.
 * Filters files by file's size in comparison to an upper bound.
 */
public class SmallerThanFilter extends SizeFilter {

    // upper bound for comparison (exclusive)
    private double upperBound;

    /**
     * A constructor for SmallerThanFilter.
     * @param upperBound a string representing an upper bound for comparison
     * @throws NegativeSizeException if upper bound is negative
     */
    public SmallerThanFilter(String upperBound) throws NegativeSizeException {
        double doubleUpperBound = parseDouble(upperBound);
        if (doubleUpperBound < 0){
            throw new NegativeSizeException();
        }
        this.upperBound = doubleUpperBound;
    }

    /**
     * Filters the given File object according to this class' filter conditions.
     * @param file a File object
     * @return true if file's size smaller than the upper bound, else false.
     */
    @Override
    public boolean filterFile(File file) {
        double fileLength = (double)file.length();
        return fileLength < this.upperBound;
    }

}
