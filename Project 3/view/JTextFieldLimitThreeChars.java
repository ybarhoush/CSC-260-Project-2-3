package view;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * Created by David on 3/4/17.
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