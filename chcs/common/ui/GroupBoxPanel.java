/*
 * @(#)GroupBoxPanel.java	1.0a1 1997
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
 * A panel that displays a Windows 95-style groupbox.  The groupbox
 * acts as a normal panel and may contain components.
 */
public class GroupBoxPanel extends Panel {

    /**
     * The GroupBoxPanel's label.
     */
    protected String label;

    /**
     * The border within the GroupBoxPanel to display between the rectangle
     * and the contained components.
     */
    protected Insets insets = new Insets(20, 14, 14, 14);

    /**
     * Constructs a new GroupBoxPanel.
     */
    public GroupBoxPanel() {
    }

    /**
     * Constructs a new GroupBoxPanel with a label.
     */
    public GroupBoxPanel(String label) {
    setLabel(label);
    }

    /**
     * Returns the GroupBoxPanel's label.
     */
    public String getLabel() {
    return this.label;
    }

    /**
     * Sets the GroupBoxPanel's label.
     */
    public void setLabel(String label) {
    this.label = label;
    }

    /**
     * Returns the border within the GroupBoxPanel to display between the rectangle
     * and the contained components.
     */
    public Insets insets() {
    return insets;
    }

    /**
     * Sets the border within the GroupBoxPanel to display between the rectangle
     * and the contained components.
     */
    public void setInsets(Insets insets) {
    this.insets = insets;
    }

    /**
     * Paints the GroupBoxPanel and its components.
     */
    public void paint(Graphics g) {
    super.paint(g);

    FontMetrics metrics = g.getFontMetrics(getFont());

    int left = 0;
    int top = metrics.getAscent() - 5;
    int width = size().width - 1;
    int height = size().height - 1;

    g.clearRect(location().x, location().y, size().width, size().height);

    g.setColor(Color.gray);
    g.drawLine(left, top, left, height - 1);  // Topleft to bottomleft
    g.drawLine(left, top, width - 1, top);    // Topleft to topright
    g.setColor(Color.white);
    g.drawLine(left + 1, top + 1, left + 1, height - 2);  // Topleft to bottomleft
    g.drawLine(left + 1, top + 1, width - 3, top + 1);    // Topleft to topright
    g.setColor(Color.gray);
    g.drawLine(left, height - 1, width - 1, height - 1);  // Bottomleft to bottomright
    g.drawLine(width - 1, top + 2, width - 1, height);    // Topright to bottomright
    g.setColor(Color.white);
    g.drawLine(left, height, width, height);  // Bottomleft to bottomright
    g.drawLine(width, top, width, height);    // Topright to bottomright

    g.clearRect(10, 5, metrics.stringWidth(label) + 4, 15);
    g.setColor(Color.black);
    g.drawString(label, 12, metrics.getHeight() - metrics.getDescent());
    }
}
