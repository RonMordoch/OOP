package filesprocessing.order;
import java.io.File;

/**
 * A class which orders file by comparing their size.
 */
public class SizeOrder implements Order {

    // Default order
    private AbsOrder absOrder = OrderFactory.createDefault();


    /**
     * Orders two files by using this order compare's function, which compares the size of
     * the files' names.
     * If names are equal, order by the default order, i.e. AbsOrder.
     * @param file1 first file to compare
     * @param file2 second file to compare
     * @return an int , -1/0/1, depending on the result of the comparison.
     */
    @Override
    public int compare(File file1, File file2) {
        long file1Size = file1.length();
        long file2Size = file2.length();
        if (file1Size < file2Size){
            return -1;
        }
        else if (file1Size > file2Size){
            return 1;
        }
        else{ // else, file names are equal, use default AbsOrder
            return absOrder.compare(file1, file2);
        }
    }
}
