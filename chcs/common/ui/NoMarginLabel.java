package chcs.common.ui;

import java.awt.*;

/**
 * A class that displays a label without the 17 pixel border added by
 * the Windows 95 AWT 1.0.2.
 */
public class NoMarginLabel extends Canvas {

    /**
     * The left alignment.
     */
    public static final int LEFT = 0;

    /**
     * The center alignment.
     */
    public static final int CENTER = 1;

    /**
     * The right alignment.
     */
    public static final int RIGHT = 2;

    /**
     * The label text.
     */
    protected String label;

    /**
     * The label alignment.
     */
    protected int alignment = 0;

    /**
     * Constructs an empty label.
     */
    public NoMarginLabel() {
    }

    /**
     * Constructs a new label with the specified String of text.
     *
     * @param the String that makes up the label
     */
    public NoMarginLabel(String label) {
	    this.label = label;
    }

    /**
     * Constructs a new label with the specified String of text and the
     * specified alignment.
     *
     * @param the String that makes up the label
     * @param alignment - the alignment value
     */
    public NoMarginLabel(String label, int alignment) {
	    this.label = label;
    	this.alignment = alignment;
    }

    /**
     * Returns the preferred size of the NoMarginLabel.
     */
    public Dimension getPreferredSize() {
	    if ((getGraphics() == null) || (getFont() == null))
    	    return(new Dimension(0, 0));
	    FontMetrics metrics = getGraphics().getFontMetrics(getFont());
    	int width, height;
	    if (label == null) {
    		height = 0;
        	width = 0;
	    }
    	else {
	    	height = metrics.getHeight();
    	    width = metrics.stringWidth(label);
	    }
    	return(new Dimension(width, height));
    }

    /**
     * Returns the minimum size of the NoMarginLabel.
     */
    public Dimension getMinimumSize() {
 	   return(getPreferredSize());
    }

    /**
     * Returns the text of this label.
     *
     * @see setText()
     */
    public String getText() {
    	return(label);
    }

    /**
     * Sets the text for this label to the specified text.
     *
     * @see getText()
     */
    public void setText(String label) {
	    this.label = label;
    }

    /**
     * Returns the current alignment of this label.
     *
     * @see setAlignment
     */
    public int getAlignment() {
    	return(alignment);
    }

    /**
     * Sets the alignment for this label to the specified alignment.
     *
     * @param alignment - the alignment value
     * @see getAlignment
     */
    public void setAlignment(int alignment) throws IllegalArgumentException {
	    if ((alignment < LEFT) || (alignment > RIGHT))
    	    throw(new IllegalArgumentException(alignment + " is not a valid alignment value for a NoMarginLabel."));
	    this.alignment = alignment;
    }

    /**
     * Paints the label.
     */
    public void paint(Graphics g) {
	    if (label != null) {
    	    FontMetrics metrics = g.getFontMetrics(getFont());
        	int height = metrics.getHeight();
	        int width = metrics.stringWidth(label);
    	    int descent = metrics.getDescent();
        	int xoffset = 0;
	        switch (alignment) {
    	        case LEFT:
        	        xoffset = 0;
            	    break;
	            case CENTER:
    	            xoffset = (getSize().width - width) / 2;
        	        break;
            	case RIGHT:
                	xoffset = getSize().width - width;
	                break;
    	    }
        	g.drawString(label, xoffset, height - descent);
	    }
    }
}