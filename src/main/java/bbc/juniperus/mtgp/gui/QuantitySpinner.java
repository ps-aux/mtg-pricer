package bbc.juniperus.mtgp.gui;

import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.NumberFormatter;

public class QuantitySpinner extends JSpinner {
	
	private static final long serialVersionUID = 1L;

	public QuantitySpinner(){
		setModel(new SpinnerNumberModel(1,1,99,1));
		setEditor(new JSpinner.NumberEditor(this,"##"));
		JFormattedTextField txt = ((JSpinner.NumberEditor) getEditor()).getTextField();
		((NumberFormatter) txt.getFormatter()).setAllowsInvalid(false);
		setMaximumSize(getPreferredSize());
	}
	
	/*
	@Override
	public Dimension getPreferredSize(){
		return new Dimension(50,super.getPreferredSize().height);
	}
	*/
	
}

class SpinnerFilter extends DocumentFilter {

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
    	System.out.println("insertingString()");
        if (stringContainsOnlyDigits(string)) {
            super.insertString(fb, offset, string, attr);
        }
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
    	System.out.println("iremovingtring()");
        super.remove(fb, offset, length);
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
    	System.out.println("replacing");
        if (stringContainsOnlyDigits(text)) {
            super.replace(fb, offset, length, text, attrs);
        }
    }

    private boolean stringContainsOnlyDigits(String text) {
        for (int i = 0; i < text.length(); i++) {
            if (!Character.isDigit(text.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}