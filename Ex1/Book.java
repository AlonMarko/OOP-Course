/**
 * a class that represents a simple book that is in a library - has a title, author, year of pub and different literary
 * aspects
 */
class Book {
    //here the book parameters will be defined - final parameters are not meant to be changed
    int currentBorrowerId = 0; // given an initial value of 0 when the book is not borrowed.
    final String author;
    final int comicValue;
    final int dramaticValue;
    final int educationalValue;
    final String title;
    final int yearOfPublication;

    /**
     * the constructor for the book class - all parameters are self exlplanatory.
     */
    Book(String title, String author, int yearOfPublication, int comicValue,
         int dramaticValue, int educationalValue) {
        this.author = author;
        this.comicValue = comicValue;
        this.dramaticValue = dramaticValue;
        this.educationalValue = educationalValue;
        this.title = title;
        this.yearOfPublication = yearOfPublication;
    }

    // methods -

    /**
     * simple method to return the id of the borrower of the book - will be 0 if the book is not borrowed
     *
     * @return int - id of the borrower.
     */
    int getCurrentBorrowerId() {
        return this.currentBorrowerId;
    }

    /**
     * this method returns the sum of the book values - the literary value
     *
     * @return int - sum of educational + comical + dramatic value
     */
    int getLiteraryValue() {
        return this.comicValue + this.dramaticValue + this.educationalValue;
    }

    /**
     * marks the current id as 0 which means the book is not borrowed anymore
     */
    void returnBook() {
        this.currentBorrowerId = 0;
    }

    /**
     * changes the id of the borrower to the new borrower
     *
     * @param borrowerId - int representing an id.
     */
    void setBorrowerId(int borrowerId) {
        this.currentBorrowerId = borrowerId;
    }

    /**
     * returns a string representation of the book - author, title , year , literary value. in a specified
     * format
     *
     * @return String in the format specified above
     */
    String stringRepresentation() {
        return "[" + this.title + "," + this.author + "," + this.yearOfPublication
                + "," + this.getLiteraryValue() + "]";
    }
}
