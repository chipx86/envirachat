package chcs.common.ui;

import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;

/**
 * A class that displays a Windows 95-style image button.
 */
public class ImageButton extends Canvas {

    /**
     * The image to display.
     */
    protected Image image;

    /**
     * The image to display when the ImageButton is pressed.
     */
    protected Image pressedImage;

    /**
     * The image to display when the ImageButton is disabled.
     */
    protected Image grayImage;

    /**
     * The image to display when the ImageButton is disabled and pressed (sticky).
     */
    protected Image downGrayImage;

    /**
     * True if the button is pressed.
     */
    protected boolean pressed;

    /**
     * True if the button should act as a sticky button that toggles between up
     * and down states.
     */
    protected boolean sticky;

    /**
     * The tool tip to show for the button when it is contained in a ToolTipFrame
     * or ToolTipPanel.
     */
    protected String tip;

    /**
     * True if the mouse is down.  Necessary since the ImageButton needs to know
     * if the mouse is down outside of the MouseDown method.
     */
    protected boolean mouseDown;

    /**
     * Flag to see if the ImageButton is enabled.  Necessary since the Windows 95
     * AWT 1.0.2 does not post a MOUSE_ENTER event when a component is disabled and
     * the ImageButton must trap that event to display the tool tip even when it
     * is disabled.
     */
    protected boolean actAsEnabled = true;

    public ImageButton() {
    }

    /**
     * Constructs a new ImageButton.
     *
     * @param image the image to display
     */
    public ImageButton(Image image) {
    setImage(image);
    }

    /**
     * Constructs a new ImageButton.
     *
     * @param image the image to display
     * @param sticky whether to act as a sticky button
     */
    public ImageButton(Image image, boolean sticky) {
    setImage(image);
    this.sticky = sticky;
    }

    /**
     * Constructs a new ImageButton.
     *
     * @param image the image to display
     * @param sticky whether to act as a sticky button
     */
    public ImageButton(Image image, Image pressedImage) {
    setImage(image);
    setPressedImage(pressedImage);
    }

    /**
     * Constructs a new ImageButton.
     *
     * @param image the image to display
     * @param pressedImage the image to display when the button is down
     * @param sticky whether to act as a sticky button
     */
    public ImageButton(Image image, Image pressedImage, boolean sticky) {
    setImage(image);
    setPressedImage(pressedImage);
    this.sticky = sticky;
    }

    /**
     * Constructs a new ImageButton.
     *
     * @param image the image to display
     * @param tip the string to display in the button's tool tip
     */
    public ImageButton(Image image, String tip) {
    setImage(image);
    this.tip = tip;
    }

    /**
     * Constructs a new ImageButton.
     *
     * @param image the image to display
     * @param tip the string to display in the button's tool tip
     * @param sticky whether to act as a sticky button
     */
    public ImageButton(Image image, String tip, boolean sticky) {
    setImage(image);
    this.tip = tip;
    this.sticky = sticky;
    }

    /**
     * Constructs a new ImageButton.
     *
     * @param image the image to display
     * @param pressedImage the image to display when the button is down
     * @param tip the string to display in the button's tool tip
     */
    public ImageButton(Image image, Image pressedImage, String tip) {
    setImage(image);
    setPressedImage(pressedImage);
    this.tip = tip;
    }

    /**
     * Constructs a new ImageButton.
     *
     * @param image the image to display
     * @param pressedImage the image to display when the button is down
     * @param tip the string to display in the button's tool tip
     * @param sticky whether to act as a sticky button
     */
    public ImageButton(Image image, Image pressedImage, String tip, boolean sticky) {
    setImage(image);
    setPressedImage(pressedImage);
    this.tip = tip;
    this.sticky = sticky;
    }

    /**
     * Returns the ImageButton's image.
     */
    public Image getImage() {
    return this.image;
    }

    /**
     * Sets the ImageButton's image.
     */
    public void setImage(Image image) {
    this.image = image;
    if (image == null)
        this.grayImage = null;
    else
        this.grayImage = createImage(new FilteredImageSource(image.getSource(), new GrayFilter(0.2)));
    repaint();
    }

    /**
     * Returns the image the ImageButton displays when it is down.
     */
    public Image getPressedImage() {
    return this.pressedImage;
    }

    /**
     * Sets the image the ImageButton displays when it is down.
     */
    public void setPressedImage(Image pressedImage) {
    this.pressedImage = image;
    if (pressedImage == null)
        this.downGrayImage = null;
    else
        this.downGrayImage = createImage(new FilteredImageSource(pressedImage.getSource(), new GrayFilter(0.2)));
    repaint();
    }

    /**
     * Returns true if the ImageButton is in a down state.
     */
    public boolean isPressed() {
    return this.pressed;
    }

    /**
     * Sets the ImageButton to an up or down state.
     */
    public void setPressed(boolean pressed) {
    this.pressed = pressed;
    }

    /**
     * Returns true if the ImageButton is a sticky button.
     */
    public boolean isSticky() {
    return this.sticky;
    }

    /**
     * Sets the ImageButton to a sticky or normal state.
     */
    public void setSticky(boolean sticky) {
    this.sticky = sticky;
    }

    /**
     * Returns true if the ImageButton should act enabled.  Necessary since the Windows 95
     * AWT 1.0.2 does not post a MOUSE_ENTER event when a component is disabled and
     * the ImageButton must trap that event to display the tool tip even when it
     * is disabled.
     */
    protected boolean actAsEnabled() {
    return this.actAsEnabled;
    }

    /**
     * Sets the ImageButton to act as enabled.  Necessary since the Windows 95
     * AWT 1.0.2 does not post a MOUSE_ENTER event when a component is disabled and
     * the ImageButton must trap that event to display the tool tip even when it
     * is disabled.
     */
    protected void setActAsEnabled(boolean actAsEnabled) {
    if (this.actAsEnabled != actAsEnabled) {
        this.actAsEnabled = actAsEnabled;
        repaint();
    }
    }

    /**
     * Returns the tool tip to show for the button when it is contained in a ToolTipFrame
     * or ToolTipPanel.
     */
    public String getTip() {
    return this.tip;
    }

    /**
     * Sets the tool tip to show for the button when it is contained in a ToolTipFrame
     * or ToolTipPanel.
     */
    public void setTip(String tip) {
    this.tip = tip;
    }

    /**
     * Returns the minimum size of the ImageButton.
     */
    public Dimension getMinimumSize() {
    return new Dimension(23, 22);
    }

    /**
     * Returns the preferred size of the ImageButton.
     */
    public Dimension getPreferredSize() {
    return new Dimension(23, 22);
    }

    /**
     * Tell the ImageButton's parent ToolTipFrame or ToolTipPanel to show the
     * ImageButton's tool tip.
     */
    protected void showTip(int x, int y) {
    if (getTip() != null) {
        y += 25;
        Component component = this;
        while ((component != null) && !((component instanceof ToolTipPanel) || (component instanceof ToolTipFrame))) {
            x += component.getLocation().x;
            y += component.getLocation().y;
            component = component.getParent();
        }
        if (component instanceof ToolTipPanel)
            ((ToolTipPanel) component).showTip(this, x, y, getTip());
        else if (component instanceof ToolTipFrame)
            ((ToolTipFrame) component).showTip(this, x, y, getTip());
    }
    }

    /**
     * Tell the ImageButton's parent ToolTipFrame or ToolTipPanel to update the
     * ImageButton's tool tip.
     */
    protected void updateTip(int x, int y) {
    if (getTip() != null) {
        y += 25;
        Component component = this;
        while ((component != null) && !((component instanceof ToolTipPanel) || (component instanceof ToolTipFrame))) {
            x += component.getLocation().x;
            y += component.getLocation().y;
            component = component.getParent();
        }
        if (component instanceof ToolTipPanel)
            ((ToolTipPanel) component).updateTip(this, x, y, getTip());
        else if (component instanceof ToolTipFrame)
            ((ToolTipFrame) component).updateTip(this, x, y, getTip());
    }
    }

    /**
     * Tell the ImageButton's parent ToolTipFrame or ToolTipPanel to hide the
     * ImageButton's tool tip.
     */
    protected void hideTip() {
        Component component = this;
        while ((component != null) && !((component instanceof ToolTipPanel) || (component instanceof ToolTipFrame)))
            component = component.getParent();
        if (component instanceof ToolTipPanel)
            ((ToolTipPanel) component).hideTip(this);
        else if (component instanceof ToolTipFrame)
            ((ToolTipFrame) component).hideTip(this);
    }

    /**
     * Paints the ImageButton.
     */
    public void paint(Graphics g) {
    int top = 0;
    int left = 0;
    int bottom = getSize().height - 1;
    int right = left + getSize().width - 1;
    if (pressed) {
        g.setColor(Color.white);
        g.drawLine(left, bottom, right - 1, bottom); // Bottom left to bottom right
        g.drawLine(right, top, right, bottom);       // Topright to bottomright

        g.setColor(Color.darkGray);
        g.drawLine(left, top, left, bottom - 1); // Topleft to bottomleft
        g.drawLine(left, top, right - 1, top);   // Topleft to topright

        g.setColor(Color.gray);
        g.drawLine(left + 1, top + 1, left + 1, bottom - 2); // Topleft to bottomleft
        g.drawLine(left + 1, top + 1, right - 2, top + 1);   // Topleft to topright
    }
    else {
        g.setColor(Color.darkGray);
        g.drawLine(left, bottom, right - 1, bottom); // Bottom left to bottom right
        g.drawLine(right, top, right, bottom);       // Topright to bottomright

        g.setColor(Color.gray);
        g.drawLine(left + 1, bottom - 1, right - 2, bottom - 1); // Bottom left to bottom right
        g.drawLine(right - 1, top + 1, right - 1, bottom - 1);   // Topright to bottomright

        g.setColor(Color.white);
        g.drawLine(left, top, left, bottom - 1); // Topleft to bottomleft
        g.drawLine(left, top, right - 1, top);   // Topleft to topright
    }
    if (image != null) {
        Image drawImage;
        if (actAsEnabled())
            if (isPressed() && (pressedImage != null))
                drawImage = pressedImage;
            else
                drawImage = image;
        else
            if (isPressed() && (pressedImage != null))
                drawImage = downGrayImage;
            else
                drawImage = grayImage;

        if (isPressed())
            g.drawImage(drawImage, left + 2, top + 2, right - 2, bottom - 2, Color.lightGray, this);
        else
            g.drawImage(drawImage, left + 1, top + 1, right - 2, bottom - 2, Color.lightGray, this);
    }
    }

	protected void processMouseMotionEvent(MouseEvent e) {
		int id = e.getID();
		switch(id) {
			case MouseEvent.MOUSE_MOVED:
				doMouseMoved(e, e.getX(), e.getY());
				break;
			case MouseEvent.MOUSE_PRESSED:
				doMouseDown(e, e.getX(), e.getY());
				break;
			case MouseEvent.MOUSE_RELEASED:
				doMouseUp(e, e.getX(), e.getY());
				break;
			case MouseEvent.MOUSE_DRAGGED:
				doMouseDrag(e, e.getX(), e.getY());
				break;
			case MouseEvent.MOUSE_ENTERED:
				doMouseEnter(e, e.getX(), e.getY());
				break;
			case MouseEvent.MOUSE_EXITED:
				doMouseExit(e, e.getX(), e.getY());
				break;
		}
	}
	
    /**
     * Press in the ImageButton when the mouse is clicked and hide its tool tip if the tool tip is
     * showing.
     */
    public boolean doMouseDown(MouseEvent event, int x, int y) {
    if (actAsEnabled && !mouseDown && insideComponent(x, y) && !isPressed()) {
        hideTip();
        if (!isSticky()) {
            mouseDown = true;
            setPressed(true);
            repaint();
        }
    }
    return true;
    }

    /**
     * Display the ImageButton in its standard state when the mouse is released and
     * post an action event.
     */
    public boolean doMouseUp(MouseEvent event, int x, int y) {
    if (actAsEnabled) {
        mouseDown = false;
        if (isSticky() && insideComponent(x, y)) {
            setPressed(!isPressed());
            paint(this.getGraphics());
            if (actAsEnabled)
                deliverEvent(new Event(this, event.when, Event.ACTION_EVENT, event.x, event.y, event.key, event.modifiers, null));
        }
        else if (isPressed()) {
            setPressed(false);
            paint(this.getGraphics());
            if (actAsEnabled && insideComponent(x, y))
                deliverEvent(new Event(this, event.when, Event.ACTION_EVENT, event.x, event.y, event.key, event.modifiers, null));
        }
        return true;
    }
    return false;
    }

    /**
     * Toggle the up and down state of the ImageButton when the mouse is dragged in and out
     * while the mouse button is pressed.
     */
    public boolean doMouseDrag(MouseEvent event, int x, int y) {
    if (actAsEnabled) {
        if (!isSticky()) {
            if (insideComponent(x, y)) {
                if (!isPressed()) {
                    setPressed(true);
                    repaint();
                }
            }
            else {
                if (isPressed()) {
                    setPressed(false);
                    repaint();
                }
            }
        }
        return true;
    }
    return false;
    }

    /**
     * Update the ImageButton's tooltip location if the mouse is moved in order to position
     * the tool tip below the mouse when it appears.
     */
    public boolean doMouseMove(MouseEvent event, int x, int y) {
    updateTip(x, y);
    return true;
    }

    /**
     * Show the ImageButton's tool tip when the mouse enters the ImageButton.
     */
   public boolean doMouseEnter(MouseEvent event, int x, int y) {
    if (!mouseDown) {
        showTip(x, y);
        return true;
    }
    else
        return false;
    }

    /**
     * Hide the ImageButton's tool tip when the mouse leaves the ImageButton.
     */
    public boolean doMouseExit(MouseEvent event, int x, int y) {
    hideTip();
    return true;
    }

    /**
     * Returns true if the local x and y coordinates are within the ImageButton.
     */
    protected boolean insideComponent(int x, int y) {
    return ((x >= 0) && (x < size().width) && (y >= 0) && (y < size().height));
    }

    /**
     * Enables the ImageButton.
     */
    public void enable() {
    setActAsEnabled(true);
    }

    /**
     * Disables the ImageButton.
     */
    public void disable() {
    setActAsEnabled(false);
    }

    /**
     * Enables or disables the ImageButton.
     */
    public void enable(boolean enable) {
    setActAsEnabled(enable);
    }
}

/**
 * A class that grays out an Image.  Written by someone else, but we did not
 * make note of who it was.  If it was you, please let us know so we can
 * attribute your code to you!  Thanks for releasing the code!
 */
class GrayFilter extends RGBImageFilter {

    protected double weight = 0.0;   // weight to put on white in the graylevel
    protected int ignore = -1;       // the color not to gray out

    public GrayFilter(double w, int ig) {
    weight = w;
    ignore = ig;
    canFilterIndexColorModel = true;
    }

    public GrayFilter(double w) {
    this(w, 0);
    }

    public GrayFilter() {
    this(0, 0);
    }

    public int filterRGB(int x, int y, int rgb) {
    if (rgb == ignore) return rgb;
        int r = (rgb >> 16) & 0xff;
    int g = (rgb >> 8) & 0xff;
    int b = (rgb >> 0) & 0xff;
    int z = (int)Math.round(weight * 255 + (1-weight) * (r + g + b)/3);
    return (rgb & 0xff000000) | (z << 16) | (z << 8) | (z << 0);
    }
}