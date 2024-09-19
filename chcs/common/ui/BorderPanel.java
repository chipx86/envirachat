/*
 * @(#)BorderPanel.java	1.0a1 1997
 *
 * Copyright (c) 1997 JRad Technologies. All Rights Reserved.
 *
 * Permission to use, copy, modify, and distribute this software
 * and its documentation for NON-COMMERCIAL purposes and without
 * fee is hereby granted provided that this copyright notice
 * appears in all copies. Please refer to the file "copyright.html"
 * for further important copyright and licensing information.
 *
 * JRAD MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF
 * THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 * TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. SUN SHALL NOT BE LIABLE FOR
 * ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR
 * DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 */

package jrad.util;

import java.awt.*;

/**
 * A panel that displays Windows 95-style borders.  Raised, sunken,
 * and etched border styles are available.
 */
public class BorderPanel extends Panel {

    /**
     * Static variable indicating a riased border.
     */
    public static final int RAISED = 1;

    /**
     * Static variable indicating a sunken border.
     */
    public static final int SUNKEN = 2;

    /**
     * Static variable indicating an etched border.
     */
    public static final int ETCHED = 3;

    /**
     * The type of border displayed by the BorderPanel.
     */
    protected int type = RAISED;

    /**
     * The border within the BorderPanel to display between the rectangle
     * and the contained components.
     */
    protected Insets insets = new Insets(2, 2, 2, 2);

    /**
     * Constructs a new BorderPanel with a raised border.
     */
    public BorderPanel() {
    }

    /**
     * Constructs a new BorderPanel with the specified border.
     *
     * @param type the type of border to create (RAISED, SUNKEN, or ETCHED)
     */
    public BorderPanel(int type) {
    this.type = type;
    }

    /**
     * Returns the border within the BorderPanel to display between the rectangle
     * and the contained components.
     */
    public Insets insets() {
    return insets;
    }

    /**
     * Sets the border within the BorderPanel to display between the rectangle
     * and the contained components.
     */
    public void setInsets(Insets insets) {
    this.insets = insets;
    }

    /**
     * Returns true if the border is raised.
     */
    public boolean isRaised() {
    return (this.type == RAISED);
    }

    /**
     * Sets the border to raised.
     */
    public void setRaised() {
    this.type = RAISED;
    }

    /**
     * Returns true if the border is sunken.
     */
    public boolean isSunken() {
    return (this.type == SUNKEN);
    }

    /**
     * Sets the border to sunken.
     */
    public void setSunken() {
    this.type = SUNKEN;
    }

    /**
     * Returns true if the border is etched.
     */
    public boolean isEtched() {
    return (this.type == ETCHED);
    }

    /**
     * Sets the border to etched.
     */
    public void setEtched() {
    this.type = ETCHED;
    }

    /**
     * Paints the BorderPanel and its components with the specified border.
     */
    public void paint(Graphics g) {
    super.paint(g);

    int width = size().width - 1;
    int height = size().height - 1;
    if (isRaised()) {
        g.setColor(Color.darkGray);
        g.drawLine(0, height, width - 1, height); // Bottomleft to bottomright
        g.drawLine(width, 0, width, height);      // Topright to bottomright
        g.setColor(Color.gray);
        g.drawLine(0 + 1, height - 1, width - 2, height - 1); // Bottomleft to bottomright
        g.drawLine(width - 1, 0 + 1, width - 1, height - 1);  // Topright to bottomright
        g.setColor(Color.white);
        g.drawLine(0, 0, 0, height - 1); // Topleft to bottomleft
        g.drawLine(0, 0, width - 1, 0);  // Topleft to topright
    }
    else if (isSunken()) {
        g.setColor(Color.gray);
        g.drawLine(0, 0, width - 1, 0);  // Topleft to topright
        g.drawLine(0, 0, 0, height - 1); // Topleft to bottomleft
        g.setColor(Color.black);
        g.drawLine(1, 1, width - 2, 1);  // Topleft to topright
        g.drawLine(1, 1, 1, height - 2); // Topleft to bottomleft
        g.setColor(Color.white);
        g.drawLine(0, height, width, height);             // Bottomleft to bottomright
        g.drawLine(1, height - 1, width - 1, height - 1); // Bottomleft to bottomright
        g.drawLine(width, 0, width, height);              // Topright to bottomright
        g.drawLine(width - 1, 1, width - 1, height - 1);  // Topright to bottomright
    }
    else { // if (isEtched())
        g.setColor(Color.gray);
        g.drawLine(0, 0, 0, height - 1); // Topleft to bottomleft
        g.drawLine(0, 0, width - 1, 0);  // Topleft to topright
        g.setColor(Color.white);
        g.drawLine(1, 1, 1, height - 2); // Topleft to bottomleft
        g.drawLine(1, 1, width - 3, 1);  // Topleft to topright
        g.setColor(Color.gray);
        g.drawLine(0, height - 1, width - 1, height - 1); // Bottomleft to bottomright
        g.drawLine(width - 1, 2, width - 1, height);      // Topright to bottomright
        g.setColor(Color.white);
        g.drawLine(0, height, width, height); // Bottomleft to bottomright
        g.drawLine(width, 0, width, height);  // Topright to bottomright
    }
    }
}