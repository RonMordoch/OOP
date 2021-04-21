package filesprocessing.section;

/**
 * A class representing a section in Commands File which has a Filter name, an Order name and the line where
 * the section begins.
 */
public class Section {

    // each section has it's own filter, order, and the line where the section begins in the file
    private String filterName;
    private String orderName;
    private int startingLine;

    /**
     * @param filterName name of this section's filter
     * @param orderName name of this section's filter
     * @param startingLine name of this section's filter
     */
    Section(String filterName, String orderName, int startingLine){
        this.filterName = filterName;
        this.orderName = orderName;
        this.startingLine = startingLine;
    }

    /**
     * Getter function for this section's filter name.
     * @return this section's filter name
     */
    public String getFilterName(){return this.filterName;}

    /**
     * Getter function for this section's order name.
     * @return this section's order name
     */
    public String getOrderName(){return this.orderName;}

    /**
     * Getter function for this section's filter line.
     * @return this section's filter line.
     */
    public int getFilterLine(){return this.startingLine + 1;}

    /**
     * Getter function for this order starting line.
     * @return this order filter line.
     */
    public int getOrderLine(){
        return this.startingLine + 3;
    }




}
