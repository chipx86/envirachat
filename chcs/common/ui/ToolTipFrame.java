package chcs.common.ui;

import java.awt.*;

/**
 * A subclass of Frame that shows Windows 95-style tool tips (little
 * yellow help boxes) for components that have been extended to show
 * tool tips.  If tool tips should be shown only within a portion of
 * the Frame or in an Applet, use the ToolTipPanel class instead.
 * The ImageButton class implements tool tips.  Examine this class
 * if you would like to extend other components to display tool tips.
 */
public class ToolTipFrame extends Frame implements Runnable {

    /**
     * The yellow tool tip box.
     */
    protected ToolTipPopup tipPopup;

    /**
     * The font with which to display tool tips.
     */
    protected Font tipFont = new Font("Dialog", Font.PLAIN, 9);

    /**
     * The component for which a tool tip is currently displayed.  Null
     * if no tool tip is displayed.
     */
    protected Component tipComponent;

    /**
     * Thread used to delay tool tip from displaying until user has
     * lingered over component.
     */
    protected Thread delayThread;

    /**
     * Constructs a new ToolTipFrame.
     */
    public ToolTipFrame() {
    this("");
    }

    /**
     * Constructs a new ToolTipFrame with a title.
     *
     * @param title the Frame's title
     */
    public ToolTipFrame(String title) {
    super(title);
    tipPopup = new ToolTipPopup();
    tipPopup.setFont(tipFont);
    tipPopup.setVisible(false);
    add(tipPopup);
    }

    /**
     * Shows a tool tip for a component.
     *
     * @param component the component the tool tip is describing
     * @param x the x location in the panel's coordinate plane to show the tool tip
     * @param y the y location in the panel's coordinate plane to show the tool tip
     * @param tip the string to display in the tool tip
     */
    public void showTip(Component component, int x, int y, String tip) {
    tipComponent = component;
    FontMetrics metrics = getGraphics().getFontMetrics(tipFont);
    int width = metrics.stringWidth(tip) + 8;
    int height = metrics.getHeight() + 4;
    tipPopup.setTip(tip);
    tipPopup.setBounds(x, y, width, height);
    if (tipPopup.isShowing())
        tipPopup.repaint();
    else {
        delayThread = new Thread(this);
        delayThread.start();
    }
    }

    /**
     * Updates the location of the tool tip.  Called while the tool tip is delayed
     * so that it will show up relative to the mouse location.
     *
     * @param component the component the tool tip is describing
     * @param x the x location in the panel's coordinate plane to show the tool tip
     * @param y the y location in the panel's coordinate plane to show the tool tip
     * @param tip the string to display in the tool tip
     */
    public void updateTip(Component component, int x, int y, String tip) {
    if (!tipPopup.isShowing() && (component == tipComponent))
        tipPopup.setLocation(x, y);
    }

    /**
     * Hides the tool tip.
     *
     * @param component the component the tool tip is describing
     */
    public void hideTip(Component component) {
    if (component == tipComponent) {
        tipPopup.setVisible(false);
        if (delayThread != null)
            delayThread.stop();
    }
    }

    /**
     * Delays tool tip from displaying until user has lingered over component.
     */
    public void run() {
    try {
        delayThread.sleep(500);
        tipPopup.setVisible(true);
        delayThread = null;
    }
    catch (InterruptedException e) {
    }
    }
}