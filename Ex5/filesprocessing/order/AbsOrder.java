package filesprocessing.order;
import java.io.File;

/**
 * A class which orders file by comparing the string value of their absolute paths.
 */
public class AbsOrder implements Order {

    /**
     * Orders two files by using this order compare's function, which compares the string values of
     * the files' absolute paths.
     * @param file1 first file to compare
     * @param file2 second file to compare
     * @return an int , -1/0/1, depending on the result of the comparison.
     */
    @Override
    public int compare(File file1, File file2){
        String file1AbsPath = file1.getAbsolutePath();
        String file2AbsPath = file2.getAbsolutePath();
        if (file1AbsPath.compareTo(file2AbsPath) < 0){
            return -1;
        }
        else if (file1AbsPath.compareTo(file2AbsPath) > 0){
            return 1;
        }
        else {return 0;}
        }
}
