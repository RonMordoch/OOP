package filesprocessing.order;
import java.io.File;
import java.util.Comparator;

/**
 * An interface for Order which extends a Comparator of File objects.
 */
public interface Order extends Comparator<File> {

    /**
     * Compare two files, overrides the Comparator's compare.
     * @param file1 first file to compare
     * @param file2 second file to compare
     * @return an int , -1/0/1 depending on the result of comparison.
     */
    @Override
    int compare(File file1, File file2);

}
