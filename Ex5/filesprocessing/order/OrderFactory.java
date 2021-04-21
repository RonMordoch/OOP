package filesprocessing.order;

/**
 * A factory class for Order objects.
 */
public class OrderFactory {

    // The single instance of this class
    private static OrderFactory orderFactory = new OrderFactory();

    // Order types
    private static final String ABS = "abs";
    private static final String TYPE = "type";
    private static final String SIZE = "size";
    private static final String REVERSE = "#REVERSE";
    private static final String EMPTY_STRING = "";

    public static OrderFactory instance(){return orderFactory;}

    /**
     * Private constructor, prevents anyone from instantiating the class.
     */
    private OrderFactory(){}

    /**
     * Creates the default order, i.e. abs order.
     * Used when we need to create the default order when given a bad order name
     * without checking the switch cases every time.
     * @return AbsOrder object
     */
    public static AbsOrder createDefault(){return new AbsOrder();}

    /**
     * A function that creates a order.
     * If the order contains the #REVERSE value, calls itself recursively to create the regular order
     * ,and then decorates the order for the reverse order.
     * Else, creates the order normally.
     * @param orderName name for order
     * @return An order object of given name
     * @throws OrderException if invalid input was given
     */
    public static Order createOrder(String orderName) throws OrderException{
        if (orderName.contains(REVERSE)){ // create a decorated order
            // replace the #REVERSE substring with an empty string
            String decoratedOrderName = orderName.replace(REVERSE, EMPTY_STRING);
            // returns the reversed, decorated order
            return new OrderDecorator(createOrder(decoratedOrderName));
        } // else, attempt to create normally
        switch (orderName){
            case ABS:
                return new AbsOrder();
            case TYPE:
                return new TypeOrder();
            case SIZE:
                return new SizeOrder();
            default: // Invalid order name,exception caught later and used for creating default order
                throw new OrderException();
        }

    }

}
