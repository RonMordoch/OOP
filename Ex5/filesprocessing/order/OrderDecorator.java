package filesprocessing.order;
import java.io.File;

/**
 * A decorator class for a Order object, used for reversion of the Order
 * when created with the #REVERSE suffix.
 */
public class OrderDecorator implements Order {

    // The Order object of this decorator
    private Order decoratedOrder;

    /**
     * Creates a decorated Order.
     * @param decoratedOrder the order we decorate.
     */
    public OrderDecorator(Order decoratedOrder){
        this.decoratedOrder = decoratedOrder;
    }

    /**
     * Orders two files by using the the decorated order compare's function, and then reverses the result.
     * @param file1 first file to compare
     * @param file2 second file to compare
     * @return an int, the opposite of the decorated order compare's result, -1/0/1, depending on
     * the result of the comparison.
     */
    @Override
    public int compare(File file1,File file2) {
        int result = decoratedOrder.compare(file1, file2); // returns the opposite value of the result
        if (result < 0) {
            return 1;
        } else if (result > 0) {
            return -1;
        } else {
            return 0;
        }
    }

}
