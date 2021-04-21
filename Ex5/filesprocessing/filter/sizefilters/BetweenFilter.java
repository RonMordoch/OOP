package filesprocessing.filter.sizefilters;
import java.io.File;

/**
 * A Contains File Filter, extends FileNameFilter.
 * Filters files by file's size between two given bounds (inclusive).
 */
public class BetweenFilter extends SizeFilter {

    // upper and lower bounds for comparison (inclusive)
    private double upperBound;
    private double lowerBound;

    /**
     * A constructor for BetweenFilter.
     * @param lowerBound a string representing a lower bound for comparison
     * @param upperBound a string representing an upper bound for comparison
     * @throws BetweenFilterException if any bound is negative or if lower bound is larger than upper bound
     */
    public BetweenFilter(String lowerBound, String upperBound) throws BetweenFilterException{
    double doubleLowerBound = parseDouble(lowerBound);
    double doubleUpperBound = parseDouble(upperBound);
    if (doubleLowerBound < 0 || doubleUpperBound < 0 || doubleUpperBound < doubleLowerBound){
        throw new BetweenFilterException();
    }//else, valid values
    this.lowerBound = doubleLowerBound;
    this.upperBound = doubleUpperBound;
    }

    /**
     * Filters the given File object according to this class' filter conditions.
     * @param file a File object
     * @return true if file's size is between the lower and upper bounds, else false.
     */
    @Override
    public boolean filterFile(File file){
        double fileLength = (double)file.length();
        return ((this.lowerBound <= fileLength) && (fileLength <= this.upperBound));
    }
}
