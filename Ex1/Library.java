/**
 * This class represents a library, which hold a collection of books.
 * Patrons can register at the library to be able to check out books, if a copy of the requested book is available.
 */
public class Library {

    /** The maximum capacity of books this library can hold. */
    final int libraryMaxBooksCapacity;

    /** The maximum number of books a patron registered to this library can borrow simultaneously. */
    final int libraryMaxBorrowedBooks;

    /** The maximum capacity of patrons that can be registered this the library. */
    final int libraryMaxPatronsCapacity;

    /** An array of book objects that are in this library. */
    Book[] booksArray;

    /** An array of patron objects that are registered in this library. */
    Patron[] patronsArray;

    /** The current number of books in this library. */
    int numOfBooksInLibrary = 0;

    /** The current number of patrons registered to this library. */
    int numOfPatronsInLibrary = 0;

    int[] booksBorrowedByPatrons;

    /*----=  Constructors  =-----*/

    /**
     * Creates a new library with the given characteristic.
     * @param maxBookCapacity the maximum number of books the library can hold.
     * @param maxBorrowedBooks the maximum number of books the library allows each patron to borrow simultaneously.
     * @param maxPatronCapacity the maximum number of patrons that can be registered in the library simultaneously.
     */
    Library(int maxBookCapacity, int maxBorrowedBooks, int maxPatronCapacity){
        libraryMaxBooksCapacity = maxBookCapacity;
        libraryMaxBorrowedBooks = maxBorrowedBooks;
        libraryMaxPatronsCapacity = maxPatronCapacity;
        booksArray = new Book[maxBookCapacity];
        patronsArray = new Patron[maxPatronCapacity];
        booksBorrowedByPatrons = new int[maxPatronCapacity];
    }

    /**
     * Adds the given book to this library, if there is place available, and it isn't already in the library.
     * @param book - The book to add to this library.
     * @return a non-negative id number for the book if there was a spot and the book was successfully added,
     * or if the book was already in the library; a negative number otherwise.
     */
    int addBookToLibrary(Book book){
        for (int i=0; i < booksArray.length; i++) { // first check if book is in library
            if (booksArray[i] == book) {
                return i;
            }
        }
        // we can now try to add book. we have to check for the maximum capacity of books in library!
        if (numOfBooksInLibrary < libraryMaxBooksCapacity){
            booksArray[numOfBooksInLibrary] = book;
            numOfBooksInLibrary += 1;
            return numOfBooksInLibrary - 1; // id's will be the index in the list, starting at 0
        }
        else {
            return -1;
        }
    }

    /**
     * Returns true if the given number is an id of some book in the library, false otherwise.
     * @param bookId - The id to check.
     * @return true if the given number is an id of some book in the library, false otherwise.
     */
    boolean isBookIdValid(int bookId){
        // first check if the book is even in the library
        if (bookId >= libraryMaxBooksCapacity || bookId < 0){
            // bookId will always be < max book capacity and a positive integer
            return false;
        }
        else {
            return (booksArray[bookId] != null ); //true if the given number is an id of *some* book in the library
        }
    }

    /**
     * Returns the non-negative id number of the given book if he is owned by this library, -1 otherwise.
     * @param book - The book for which to find the id number.
     * @returna n on-negative id number of the given book if he is owned by this library, -1 otherwise.
     */
    int getBookId(Book book){
        // go over all the books in the library, if the given book is found, return the index (bookId)
        for (int i = 0; i < booksArray.length ; i++) {
            if (booksArray[i] == book) {
                return i;
            }
        } // else we are out of the loop, book was not found in library
        return -1;
    }

    /**
     * Returns true if the book with the given id is available, false otherwise.
     * @param bookId - The id number of the book to check.
     * @return true if the book with the given id is available, false otherwise.
     */
    boolean isBookAvailable(int bookId){
        if (isBookIdValid(bookId)){ // first check is book is in library
            return (booksArray[bookId].getCurrentBorrowerId() == -1); // check the owner of the book, if such exists
        }
        else{
            return false;
        }
    }

    /**
     * Registers the given Patron to this library, if there is a spot available.
     * @param patron - The patron to register to this library.
     * @return a non-negative id number for the patron if there was a spot and the patron was successfully
     * registered or if the patron was already registered. a negative number otherwise.
     */
    int registerPatronToLibrary(Patron patron){
        for (int i = 0; i < patronsArray.length ; i++) { // first check if patron is registered to library
            if (patronsArray[i] == patron){
                return i; // return patronId
            }
        }
        // we can now try to register patron. we have to check for the maximum capacity of patrons in library!
        if (numOfPatronsInLibrary < libraryMaxPatronsCapacity){
            patronsArray[numOfPatronsInLibrary] = patron;
            numOfPatronsInLibrary += 1;
            return numOfPatronsInLibrary -1; // id's will be the index in the list, starting at 0
        }else{
            return -1;
        }
    }

    /**
     * Returns true if the given number is an id of a patron in the library, false otherwise.
     * @param patronId - The id to check.
     * @return true if the given number is an id of a patron in the library, false otherwise.
     */
    boolean isPatronIdValid(int patronId){
        // first check if the patron is even registered to the library
        if (patronId >= libraryMaxPatronsCapacity || patronId < 0){
            // patronId will always be < max patron capacity and and a positive integer
            return false;
        }
        else{
            return(patronsArray[patronId] != null); //true if the given number is an id of *some* patron in the library
        }
    }

    /**
     * Returns the non-negative id number of the given patron if he is registered to this library, -1 otherwise.
     * @param patron - The patron for which to find the id number.
     * @return a non-negative id number of the given patron if he is registered to this library, -1 otherwise.
     */
    int getPatronId(Patron patron){
        // go over all the patrons registered to the library, if the given patron is found, return the index (patronId)
        for (int i = 0; i < patronsArray.length ; i++) {
            if (patronsArray[i] == patron){
                return i;
            }
        } // else we are out of the loop, patron was not found in library
        return -1;
    }

    /**
     * Marks the book with the given id number as borrowed by the patron with the given patron id,
     * if this book is available, the given patron isn't already borrowing the maximal number of books allowed,
     * and if the patron will enjoy this book.
     * @param bookId - The id number of the book to borrow.
     * @param patronId - The id number of the patron that will borrow the book.
     * @return true if the book was borrowed successfully, false otherwise.
     */
    boolean borrowBook(int bookId, int patronId) {
        // first check book is in library and available and that patron is registered to library.
        if (isBookAvailable(bookId) && (isPatronIdValid(patronId))) {
            Book curBook = booksArray[bookId];
            Patron curPatron = patronsArray[patronId];
            // check if patron has not borrowed the maximum number of books and that patron will enjoy the book
            if ((booksBorrowedByPatrons[patronId] < libraryMaxBorrowedBooks) && (curPatron.willEnjoyBook(curBook))) {
                curBook.setBorrowerId(patronId); // set book's borrower Id to patron's Id
                booksBorrowedByPatrons[patronId] += 1;
                // add the book to the number of books patron is currently borrowing
                return true;
            }
            else{
                return false; // patron either won't enjoy book or patron
                // has borrowed the maximum number of books allowed to borrow
            }
        } else { // either book is not available (borrowed or not in library)
            // or patron is not registered to the library
            return false;
        }
    }

    /**
     * Return the given book.
     * @return bookId - The id number of the book to return.
     */

    void returnBook(int bookId){
        // check if book is in library and that indeed the book is borrowed
        if (isBookIdValid(bookId) && (!isBookAvailable(bookId))){
            Book curBook = booksArray[bookId];
            int curPatronId = curBook.getCurrentBorrowerId();
            curBook.returnBook(); // return book, marks the borrower as -1
            booksBorrowedByPatrons[curPatronId] -= 1; // remove the book from the number of books borrowed by patron
        }
    }

    /**
     * Suggest the patron with the given id the book he will enjoy the most,
     * out of all available books he will enjoy, if any such exist.
     * @param patronId - The id number of the patron to suggest the book to.
     * @return The available book the patron with the given will enjoy the most. Null if no book is available.
     */
    Book suggestBookToPatron(int patronId){
        if ((numOfBooksInLibrary > 0) && (isPatronIdValid(patronId))){
            Patron curPatron = patronsArray[patronId];
            int maxValue = 0; // initialize max value
            Book maxValueBook = null; // initialize the matching book
            for (int i = 0; i < numOfBooksInLibrary ; i++) {
                Book curBook = booksArray[i];
                if (isBookAvailable(i) & curPatron.willEnjoyBook(curBook)){
                    int curValue = curPatron.getBookScore(curBook);
                    if (curValue > maxValue){
                        maxValue = curValue;
                        maxValueBook = curBook;
                        // each time a better book is found , set the book and value to be the maximum
                    }
                }
            }
            return maxValueBook; // after the loop, we either found a book, or book is still null
        }
        else{
            return null;
        }
    }
}





