package chcs.common.ui;

import java.awt.*;

/**
 * A subclass of Panel which displays a yellow tool tip.  Used by the
 * ToolTipFrame and ToolTipPanel classes.
 */
public class ToolTipPopup extends Panel {

    /**
     * The tip to display.
     */
    protected String tip;

    /**
     * Returns the tip to display.
     */
    public String getTip() {
    return this.tip;
    }

    /**
     * Sets the tip to display.
     *
     * @param tip the tip to display
     */
    public void setTip(String tip) {
    this.tip = tip;
    }

    /**
     * Paints the tip in the Panel's font on a yellow background.
     *
     * @param g the graphics
     */
    public void paint(Graphics g) {
    int width = getSize().width - 1;
    int height = getSize().height - 1;
    g.setColor(new Color(255, 255, 220));
    g.fillRect(0, 0, width, height);
    g.setColor(Color.black);
    g.drawRect(0, 0, width, height);
    g.drawString(tip, 4, height - 3);
    }
}