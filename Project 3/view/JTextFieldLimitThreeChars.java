package view;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * JTextFieldLimitThreeChars.
 * Limits the user ID to three letters and does not allow the user
 * to enter an ID that is longer than 3 characters.
 */
public class JTextFieldLimitThreeChars extends PlainDocument {
    private final int THREE_CHARS = 3;
    private int limit;

    JTextFieldLimitThreeChars() {
        limit = THREE_CHARS;
    }

    /**
     * Takes a string of three characters and is used to
     * either become the title of a plain text document
     * or gets an existing document of the same title for
     * updating.  This is called as long the player hasn't
     * inputted a three character string yet.
     * @param offset
     * @param str
     * @param attr
     * @throws BadLocationException
     */
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str == null) return;

        if ((getLength() + str.length()) <= limit) {
            super.insertString(offset, str, attr);
        }
    }
}