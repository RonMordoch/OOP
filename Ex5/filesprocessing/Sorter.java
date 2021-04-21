package filesprocessing;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * A Sorter class which implements quick sort in order to sort files by a given Order objects which
 * extends Comparator.
 */
public class Sorter {

    /**
     * Constructor.
     */
    public Sorter(){}

    /**
     * A modified implementation of quick sort for sorting objects of File type.
     * @param fileList an array list of File objects.
     * @param orderComparator an order object which extends Comparator
     * @param left beginning index
     * @param right ending index
     */
    public void quickSort(ArrayList<File> fileList,
                          Comparator<File> orderComparator,
                          int left, int right){
        if (left < right){
            int partitionIndex = partition(fileList, orderComparator, left, right);
            quickSort(fileList, orderComparator, left, partitionIndex-1);
            quickSort(fileList, orderComparator, partitionIndex+1, right);
        }
    }

    /**
     * A modified implementation of partition for objects of File type.
     * @param fileList an array list of File objects.
     * @param orderComparator an order object which extends Comparator
     * @param left beginning index
     * @param right ending index
     * @return index of partitioning
     */
    private int partition(ArrayList<File> fileList,
                          Comparator<File> orderComparator,
                          int left, int right){
        // get right-most file object as pivot
        File pivot = fileList.get(right);
        int i = left - 1;

        for (int j = left; j < right; j++){
            // use our comparator as a method of ordering files
            if (orderComparator.compare(fileList.get(j), pivot) <= 0){
                i++;
                File tempFile = fileList.get(i);
                fileList.set(i, fileList.get(j));
                fileList.set(j, tempFile);
            }
        }
        File tempFile = fileList.get(i+1);
        fileList.set(i+1, fileList.get(right));
        fileList.set(right, tempFile);

        return i+1;
    }
}
