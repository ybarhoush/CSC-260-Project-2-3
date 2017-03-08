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

    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str == null) return;

        if ((getLength() + str.length()) <= limit) {
            super.insertString(offset, str, attr);
        }
    }
}