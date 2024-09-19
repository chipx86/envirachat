/*
 * @(#)ToolBarPanel.java	1.0a1 1997
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
 * A subclass of Panel that shows a Windows 95-style toolbar.  The toolbar
 * can track images and will automatically track the images of ImageButtons.
 */
public class ToolBarPanel extends Panel {

    /**
     * The panel which contains the toolbar contents.
     */
    protected Panel componentPanel;

    /**
     * The current location within the toolbar with which to place new components.
     */
    protected int locationCounter = 3;

    /**
     * A MediaTracker to automatically track images for the toolbar.
     */
    protected MediaTracker mediaTracker;

    /**
     * Constructs a new ToolBarPanel.
     */
    public ToolBarPanel() {
    super();
    setLayout(null);
    componentPanel = new Panel();
    componentPanel.setLayout(null);
    add(componentPanel);
    componentPanel.reshape(3, 5, 0, 22);
    mediaTracker = new MediaTracker(this);
    }

    /**
     * Adds a new component to the toolbar.
     */
    public void addComponent(Component component) {
    addComponent(component, 0);
    }

    /**
     * Adds a new component to the toolbar using a vertical offset.
     *
     * @param verticalOffset the number of pixels to offset the component vertically
     */
    public void addComponent(Component component, int verticalOffset) {
    if (component instanceof ImageButton) {
        ImageButton button = (ImageButton) component;
        if (button.getImage() != null)
            mediaTracker.addImage(button.getImage(), 0);
        if (button.getPressedImage() != null)
            mediaTracker.addImage(button.getPressedImage(), 0);
    }
    componentPanel.add(component);
    component.move(locationCounter, verticalOffset);
    component.resize(component.preferredSize());
    locationCounter += component.preferredSize().width;
    componentPanel.resize(locationCounter, 22);
    }

    /**
     * Adds a space of standard width to the toolbar so that components may be
     * separated.
     */
    public void addSpace() {
    locationCounter += 8;
    }

    /**
     * Adds a space to the toolbar so that components may be separated.
     *
     * @param space the number of pixels to add as white space
     */
    public void addSpace(int space) {
    locationCounter += space;
    }

    /**
     * Returns the minimum size of the toolbar.
     */
    public Dimension minimumSize() {
    return new Dimension(100, 32);
    }

    /**
     * Returns the preferred size of the toolbar.
     */
    public Dimension preferredSize() {
    return new Dimension(100, 32);
    }

    /**
     * Track an image contained within the toolbar.
     *
     * @param image the image to track
     */
    public void trackImage(Image image) {
    mediaTracker.addImage(image, 0);
    }

    /**
     * Wait until all images tracked by the toolbar have loaded.
     *
     * @exception InterruptedException
     */
    public void waitForImages() throws InterruptedException {
    mediaTracker.waitForAll();
    }

    /**
     * Try to wait until all images tracked by the toolbar have loaded.  If there
     * is an exception, continue processing.
     */
    public void tryToWaitForImages() {
    try {
        waitForImages();
    }
    catch (InterruptedException e) {
    }
    }

    /**
     * Paints the toolbar and its components.
     */
    public void paint(Graphics g) {
    super.paint(g); // Paint the toolbar's components
    int width = size().width - 1;
    int height = size().height - 1;
    g.setColor(Color.gray);
    g.drawLine(0, 0, width - 2, 0); // Top line
    g.setColor(Color.white);
    g.drawLine(0, 1, width - 1, 1);
    g.drawLine(width - 1, 0, width - 1, 0);
    if (locationCounter > 3) {  // Right line
        g.setColor(Color.gray);
        g.drawLine(locationCounter + 9, 2, locationCounter + 9, height - 2);
        g.setColor(Color.white);
        g.drawLine(locationCounter + 10, 2, locationCounter + 10, height - 2);
    }
    g.setColor(Color.gray); // Bottom line
    g.drawLine(0, height - 1, width - 2, height - 1);
    g.setColor(Color.white);
    g.drawLine(0, height, width - 1, height);
    g.drawLine(width - 1, height - 1, width - 1, height - 1);
    }
}
