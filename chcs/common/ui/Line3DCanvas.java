package chcs.common.ui;

import java.awt.*;

/**
 * A subclass of Canvas which draws a Windows 95-style horizontal
 * 3D line.
 */
public class Line3DCanvas extends Canvas {

    /**
     * Creates a new Line3DCanvas
     */
    public Line3DCanvas() {
    }

    /**
     * Creates a new Line3DCanvas with the specified location and
     * width.
     *
     * @param x the x location of the left side of the line
     * @param y the y location of the left side of the line
     * @param width the width of the line
     */
    public Line3DCanvas(int x, int y, int width) {
	    setBounds(x, y, width, 2);
    }

    /**
     * Paints the line.
     */
    public void paint(Graphics g) {
	    super.paint(g);

	    int width = getSize().width - 1;

	    g.setColor(Color.gray);
    	g.drawLine(0, 0, width - 2, 0);
	    g.setColor(Color.white);
    	g.drawLine(0, 1, width - 1, 1);
	    g.drawLine(width - 1, 0, width - 1, 0);
    }
}