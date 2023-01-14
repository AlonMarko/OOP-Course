/**
 * a java class representing a Patron? ( a mashiil? a borrower?)
 * a library patron that has a name and different "tendencies" that are weights to the enjoyment
 * level from a book
 */
class Patron {
    // self-explanatory variable names - final since non are supposed to change
    final String patronFirstName;
    final String patronLastName;
    final int comicTendency;
    final int dramaticTendency;
    final int educationalTendency;
    final int patronEnjoymentThreshold;

    /**
     * the constructor for the patron class
     *
     * @param patronFirstName          the first name - string
     * @param patronLastName           the last name - string
     * @param comicTendency            - the comic tendency weight - int
     * @param dramaticTendency         - the dramatic tendency weight - int
     * @param educationalTendency      - the educational tendency weight - int
     * @param patronEnjoymentThreshold - the threshold from which the patron will enjoy the book - int
     */
    Patron(String patronFirstName, String patronLastName, int comicTendency, int dramaticTendency,
           int educationalTendency, int patronEnjoymentThreshold) {
        this.patronFirstName = patronFirstName;
        this.patronLastName = patronLastName;
        this.comicTendency = comicTendency;
        this.dramaticTendency = dramaticTendency;
        this.educationalTendency = educationalTendency;
        this.patronEnjoymentThreshold = patronEnjoymentThreshold;
    }

    // methods -

    /**
     * Calculate the literary value this patron assigns to the given book, based on the patron literary
     * aspect weights.
     *
     * @param book - Book class object
     * @return the book score according to the patron weights.
     */
    int getBookScore(Book book) {
        return (book.educationalValue * this.educationalTendency) +
                (book.dramaticValue * this.dramaticTendency)
                + (this.comicTendency * book.comicValue);
    }

    /**
     * @return Returns a string representation of the patron, which is a sequence of its first and last
     * name, separated by a single white-space.
     */
    String stringRepresentation() {
        return this.patronFirstName + " " + this.patronLastName;
    }

    /**
     * Returns true of this patron will enjoy the given book, false otherwise.
     *
     * @param book - a Book object
     * @return true or false
     */
    boolean willEnjoyBook(Book book) {
        return this.getBookScore(book) >= this.patronEnjoymentThreshold;
    }
}
