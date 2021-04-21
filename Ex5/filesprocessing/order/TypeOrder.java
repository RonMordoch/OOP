package filesprocessing.order;
import java.io.File;

/**
 * A class which orders file by comparing the string value of their types.
 */
public class TypeOrder implements Order {

    // Default order
    private AbsOrder absOrder = OrderFactory.createDefault();

    // Constants for search types.
    private static final String EMPTY_STRING = "";
    private static final String DELIMITER = ".";
    private static final int NOT_FOUND = -1;
    private static final int FIRST_IDX = 0;

    /**
     * Gets the type of a given file name.
     * @param fileName A string representing a file name
     * @return an empty string if no dot was found, or if file is hidden ( file name starts with a dot ).
     *          else returns the substring of the file's type.
     */
    private String getType(String fileName) {
        int lastIndexOf = fileName.lastIndexOf(DELIMITER);
        if (lastIndexOf == NOT_FOUND || lastIndexOf == FIRST_IDX) {
            return EMPTY_STRING;
        } else return fileName.substring(lastIndexOf + 1);

    }

    /**
     * Orders two files by using this order compare's function, which compares the string values of
     * the files' names.
     * If names are equal, order by the default order, i.e. AbsOrder.
     * @param file1 first file to compare
     * @param file2 second file to compare
     * @return an int , -1/0/1, depending on the result of the comparison.
     */
    @Override
    public int compare(File file1, File file2) {
        String file1Type = getType(file1.getName());
        String file2Type = getType(file2.getName());
        if (file1Type.compareTo(file2Type) < 0) {
            return -1;
        } else if (file1Type.compareTo(file2Type) > 0) {
            return 1;
        } else { // else, file names are equal, use default AbsOrder
            return absOrder.compare(file1, file2);
        }
    }
 }

