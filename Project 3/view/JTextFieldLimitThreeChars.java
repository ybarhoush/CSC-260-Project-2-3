package view;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * This class is what times the user id to 3 letters.
 * This class doesn't allow the user to enter an id that
 *  is longer than 3 characters.
 */
public class JTextFieldLimitThreeChars extends PlainDocument {
    private final int THREE_CHARS = 3;
    private int limit;

    JTextFieldLimitThreeChars() {
        limit = THREE_CHARS;
    }

    public void insertString( int offset, String  str, AttributeSet attr ) throws BadLocationException {
        if (str == null) return;

        if ((getLength() + str.length()) <= limit) {
            super.insertString(offset, str, attr);
        }
    }
}