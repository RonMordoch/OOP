/**
 * This class represents a library patron that has a name and assigns values to different literary aspects of books:
 * comic value, dramatic value and educational value. A patron also has an enjoyment threshold for books.
 */
class Patron {
    /** The first name of this patron. */
    final String firstName;

    /** The last name of this patron. */
    final String lastName;

    /** The values this patron assigns to different literary aspects. */
    int patronComicTendency;
    int patronDramaticTendency;
    int patronEducationalTendency;

    /** The enjoyment threshold for books. */
    int enjoymentThreshold;

    /*----=  Constructors  =-----*/

    /**
     * Creates a new patron with the given characteristic.
     * @param patronFirstName The first name of the patron.
     * @param patronLastName The last name of the patron.
     * @param comicTendency The comic tendency of the patron.
     * @param dramaticTendency The dramatic tendency of the patron.
     * @param educationalTendency The educational tendency of the patron.
     * @param patronEnjoymentThreshold The enjoyment threshold of the patron.
     */
    Patron(String patronFirstName, String patronLastName, int comicTendency, int dramaticTendency,
           int educationalTendency, int patronEnjoymentThreshold){
        firstName = patronFirstName;
        lastName = patronLastName;
        patronComicTendency = comicTendency;
        patronDramaticTendency = dramaticTendency;
        patronEducationalTendency = educationalTendency;
        enjoymentThreshold = patronEnjoymentThreshold;
    }

    /*----=  Instance Methods  =-----*/

    /**
     * Returns a string representation of the patron, which is a sequence of its first and last name,
     * separated by a single white space.
     * For example, if the patron's first name is "Ricky" and his last name is "Bobby",
     * this method will return the String "Ricky Bobby".
     * @return the String representation of this patron.
     */
    String stringRepresentation(){
        return firstName + " " + lastName;
    }

    /**
     * Returns the literary value this patron assigns to the given book.
     * @param book - The book to asses.
     * @return the literary value this patron assigns to the given book.
     */
    int getBookScore(Book book){
        return book.getComicValue() * patronComicTendency + book.getDramaticValue() * patronDramaticTendency +
                book.getEducationalValue() * patronEducationalTendency;
    }

    /**
     * Returns true of this patron will enjoy the given book, false otherwise.
     * @param book - The book to asses.
     * @return true of this patron will enjoy the given book, false otherwise.
     */
    boolean willEnjoyBook(Book book){
        return (getBookScore(book) >= enjoymentThreshold);
    }


}
