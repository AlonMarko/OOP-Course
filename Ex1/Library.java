import java.util.*;


class Library {
    final int maxBookCapacity;
    final int maxBorrowedBooks;
    final int maxPatronCapacity;
    List<Book> books = new ArrayList<Book>();
    List<Book> borrowedBooks = new ArrayList<Book>();
    List<Patron> patrons = new ArrayList<Patron>();
    int regulationNum = 0; // a number to alert on a problem. 0 is normal , -1 means a problem.

    Library(int maxBookCapacity, int maxBorrowedBooks, int maxPatronCapacity) {
        this.maxBookCapacity = maxBookCapacity;
        this.maxBorrowedBooks = maxBorrowedBooks;
        this.maxPatronCapacity = maxPatronCapacity;
    }

    //methods -

    /**
     * Adds the given book to this library, if there is place available, and it isn't already in the library.
     *
     * @param book - a given book object to be added to the library
     * @return returns the regulation number, so we can see if the operation was successful or not.
     */
    int addBookToLibrary(Book book) {
        if (this.books.size() >= maxBookCapacity) {
            regulationNum = -1;
        } else if (this.books.contains(book)) {
            return book.hashCode();
        } else {
            this.books.add(book);
            return book.currentBorrowerId;
        }
        return regulationNum;
    }

    /**
     * returns true if the given number is an id of a book in the library.
     *
     * @param bookId - the id to check.
     * @return true if the given number is an id of some book in the library
     * false otherwise.
     */
    boolean isBookIdValid(int bookId) {
        for (Book book : books) {
            if (bookId == book.hashCode()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the id number of the given book if it is owned by this library, -1 otherwise.
     *
     * @param book - a Book object representing the book we want to find the id for.
     * @return the book id if is available or -1 otherwise.
     */
    int getBookId(Book book) {
        if (this.books.contains(book)) {
            return book.hashCode();
        }
        return -1;
    }

    /**
     * returns true if the book with the given id is available, false otherwise
     *
     * @param bookId - the id number of the book to check
     * @return true if avaiable or false otherwise.
     */
    boolean isBookAvailable(int bookId) {
        boolean isValid = this.isBookIdValid(bookId);
        boolean available = false;
        for (Book book : borrowedBooks) {
            if (bookId == book.hashCode()) {
                available = true;
            }
        }
        return (isValid && available);
    }

    /**
     * registers the given patron to the library, if there is spot available
     *
     * @param patron - the given patron
     * @return the id number of the patron if registered or there is place and
     * was registered or -1 otherwise.
     */
    int registerPatronToLibrary(Patron patron) {
        if (this.patrons.size() >= maxPatronCapacity) {
            regulationNum = -1;
        } else if (this.patrons.contains(patron)) {
            return patron.hashCode();
        } else {
            this.patrons.add(patron);
            return patron.hashCode();
        }
        return regulationNum;
    }

    /**
     * checks wheter the patron id is valid
     *
     * @param patronId - the id to check
     * @return true if the given id is an actual id of a patron that is registered.
     */
    boolean isPatronIdValid(int patronId) {
        for (Patron patron : patrons) {
            if (patronId == patron.hashCode()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the id number of the given patron if he is registered to this library, -1 otherwise.
     *
     * @param patron the patron which we have to check whether he is registered to this library.
     * @return the id of the patron or -1, int.
     */
    int getPatronId(Patron patron) {
        if (this.patrons.contains(patron)) {
            return patron.hashCode();
        }
        return -1;
    }

    /**
     * Marks the book with the given id number as borrowed by the patron with the given patron id,
     * if this book is
     * available, the given patron isn't already borrowing the maximal number of books allowed,
     * and if the patron will enjoy this book.
     *
     * @param bookId   int representing the book - which is the hashcode of the object in
     *                 question since we did not get
     *                 an book id.
     * @param patronId the id of the book borrower.
     * @return true if the book was borrowed and everything was alright or false otherwise.
     */
    boolean borrowBook(int bookId, int patronId) {
        for (Book book : books) {
            if (bookId == book.hashCode()) {
                book.currentBorrowerId = patronId;
                this.borrowedBooks.add(book);
                return true;
            }
        }
        return false;
    }

    /**
     * returns the given book to the library.
     *
     * @param bookId - the id number of the book to return
     */
    void returnBook(int bookId) {
        for (Book book : borrowedBooks) {
            if (bookId == book.hashCode()) {
                book.currentBorrowerId = 0;
                borrowedBooks.remove(book);
            }
        }
    }

    /**
     * Suggest the patron with the given id the book he will enjoy the most,
     * out of all available books he will enjoy, if any such exist.
     *
     * @param patronId - the id of the patron to suggest the book to
     * @return the book if needed to return or false otherwise
     */
    Book suggestBookToPatron(int patronId) {
        int maxEnjoyment = 0;
        Book selected = null;
        if (isPatronIdValid(patronId)) {
            Patron patronToSuggest = null;
            for (Patron patron : patrons) {
                if (patronId == patron.hashCode()) {
                    patronToSuggest = patron;
                }
            }
            for (Book book : books) {
                if (isBookAvailable(book.hashCode()) && patronToSuggest.willEnjoyBook(book)) {
                    maxEnjoyment = patronToSuggest.getBookScore(book);
                    if (maxEnjoyment >= patronToSuggest.patronEnjoymentThreshold) {
                        selected = book;
                    }
                }
            }
        }
        if (maxEnjoyment > 0) {
            return selected;
        }
        return null;
    }

}
