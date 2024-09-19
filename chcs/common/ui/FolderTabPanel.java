package chcs.common.ui;

import java.awt.*;
import java.util.Vector;
import java.awt.event.*;
/**
 * A panel that displays Windows 95-style folder tabs that displays
 * components when a tab is selected.
 */
public class FolderTabPanel extends Panel implements MouseListener {

    /**
     * The tab labels.
     */
    protected Vector labels = new Vector();

    /**
     * The panels representing each tab.
     */
    protected Vector panels = new Vector();

    /**
     * The widths of the tabs.
     */
    protected int[] tabWidths;

    /**
     * The label of the currently selected tab.
     */
    protected String selectedPanel;

    /**
     * The border within the FolderTabPanel to display between the rectangle
     * and the contained components.
     */
    protected Insets insets;

    /**
     * The height of the tabs.
     */
    protected int tabHeight = 21;

    /**
     * The margin to the left and right of the tab text.
     */
    protected int tabWidthBuffer = 13;

    /**
     * Constructs a new FolderTabPanel.
     */
    public FolderTabPanel() {
	    insets = new Insets(14 + tabHeight, 14, 14, 14);
    	setLayout(new BorderLayout());
    }

    /**
     * Adds a tab to the FolderTabPanel.
     *
     * @param panel the panel to display when the tab is selected
     * @param label the tab label
     */
    public void addPanel(Panel panel, String label) {
	    panels.addElement(panel);
    	labels.addElement(label);
	    add(panel);
    	panel.setBounds(1, 22, getSize().width - 3, getSize().height - 3 - tabHeight);
	    panel.setVisible(false);
    	if (panels.size() == 1)
        	setPanel(label);
    }

    /**
     * Removes a tab from the FolderTabPanel.
     *
     * @param label the label of the tab to remove.
     */
    public void removePanel(String label) {
	    int index = labels.indexOf(label);
    	if (index == -1)
	        throw new IllegalArgumentException(label + " is not a panel in this FolderTabPanel");
    	labels.removeElementAt(index);
    	panels.removeElementAt(index);
	    if (label.equals(selectedPanel)) {
    	    if (labels.size() > 0)
        	    setPanel((String) labels.elementAt(0));
	        else {
    	        selectedPanel = null;
        	    repaint();
	        }
	    }
    }

    /**
     * Sets the FolderTabPanel's active tab.
     *
     * @param label the label of the tab to select.
     */
    public void setPanel(String label) {
	    int index = labels.indexOf(label);
    	if (index == -1)
        	throw new IllegalArgumentException(label + " is not a panel in this FolderTabPanel");
	    selectedPanel = label;
    	Panel panel = (Panel) panels.elementAt(index);
	    for (int i = 0; i < labels.size(); i++) {
    	    if (i != index) {
        	    ((Panel) panels.elementAt(i)).setVisible(true);
	        }
    	}
	    panel.setBounds(insets().left, insets().top, size().width - insets().left - insets().right, size().height - insets().top - insets().bottom);
    	panel.doLayout();
	    panel.setVisible(true);
   	}

    /**
     * Performs a layout() that shapes the current tab into the FolderTabPanel.
     */
    public void doLayout() {
	    super.doLayout();
    	getSelectedPanel().setBounds(insets().left, insets().top, size().width - insets().left - insets().right, size().height - insets().top - insets().bottom);
	    getSelectedPanel().doLayout();
    }

    /**
     * Returns the label of the selected tab.
     */
    public String getSelectedPanelLabel() {
	    return this.selectedPanel;
    }

    /**
     * Returns the panel of the selected tab.
     */
    public Panel getSelectedPanel() {
    	return (Panel) panels.elementAt(labels.indexOf(selectedPanel));
    }

    /**
     * Returns the border within the FolderTabPanel to display between the rectangle
     * and the contained components.
     */
    public Insets insets() {
	    return insets;
    }

    /**
     * Sets the border within the FolderTabPanel to display between the rectangle
     * and the contained components.
     */
    public void setInsets(Insets insets) {
    	this.insets = insets;
    }

    /**
     * Returns the height of the tabs.
     */
    public int getTabHeight() {
	    return this.tabHeight;
    }

    /**
     * Sets the height of the tabs.
     */
    public void setTabHeight(int tabHeight) {
    	this.tabHeight = tabHeight;
    }

    /**
     * Returns the margin to the left and right of the tab text.
     */
    public int getTabWidthBuffer() {
	    return this.tabWidthBuffer;
    }

    /**
     * Sets the margin to the left and right of the tab text.
     */
    public void setTabWidthBuffer(int tabWidthBuffer) {
    	this.tabWidthBuffer = tabWidthBuffer;
    }

    /**
     * Paints the FolderTabPanel and its selected tab.
     */
    public void paint(Graphics g) {
	    super.paint(g);

    	int top = tabHeight - 1;
	    int left = 0;
    	int bottom = getSize().height - 1;
	    int right = left + getSize().width - 1;

    	g.setColor(Color.darkGray);
	    g.drawLine(left, bottom, right - 1, bottom); // Bottom left to bottom right
    	g.drawLine(right, top, right, bottom);       // Topright to bottomright

	    g.setColor(Color.gray);
    	g.drawLine(left + 1, bottom - 1, right - 2, bottom - 1); // Bottom left to bottom right
	    g.drawLine(right - 1, top + 1, right - 1, bottom - 1);   // Topright to bottomright

    	g.setColor(Color.white);
	    g.drawLine(left, top, left, bottom - 1); // Topleft to bottomleft

    	tabWidths = new int[labels.size()];
	    int selected = -1;
    	int selectedLoc = 0;
	    int xLoc = 2;
    	for (int i = 0; i < labels.size(); i++) {
        	String label = (String) labels.elementAt(i);
	        FontMetrics metrics = g.getFontMetrics(getFont());
    	    tabWidths[i] = metrics.stringWidth(label) + tabWidthBuffer;
        	if (labels.elementAt(i).equals(selectedPanel)) {
	            selected = i;
    	        selectedLoc = xLoc;
        	}
	        else
    	        paintTab(g, false, xLoc, 0, tabWidths[i], top, label);
        	xLoc += tabWidths[i] - 1;
	    }
	    if (selected > -1) {
    	    paintTab(g, true, selectedLoc, 0, tabWidths[selected], top, (String) labels.elementAt(selected));
        	g.setColor(Color.white);
	        g.drawLine(left, top, selectedLoc - 2, top); // Topleft to topright - left side
    	    g.drawLine(selectedLoc + tabWidths[selected] + 2, top, right - 1, top);       // Topleft to topright - right side
	    }
    	else {
	        g.setColor(Color.white);
    	    g.drawLine(left, top, right - 1, top); // Topleft to topright
	    }
    }

    /**
     * Paints an individual tab.
     */
    private void paintTab(Graphics g, boolean selected, int x, int y, int width, int height, String label) {
    	int left = x;
	    int top = y + 2;
    	int right = x + width - 1;
	    int bottom = y + height - 1;

    	height -= 2;

	    if (selected) {
    	    top -= 2;
        	left -= 2;
	        right += 2;
    	    bottom += 1;
	    }

    	g.setColor(Color.darkGray);
	    g.drawLine(right - 1, top + 2, right - 1, bottom); // Topright to bottomright
    	g.drawRect(right - 2, top + 1, 0, 0);              // Topright corner

	    g.setColor(Color.gray);
    	g.drawLine(right - 2, top + 2, right - 2, bottom); // Topright to bottomright

	    g.setColor(Color.white);
    	g.drawLine(left, top + 2, left, bottom);   // Topleft to bottomleft
	    g.drawLine(left + 2, top, right - 3, top); // Topleft to topright
    	g.drawRect(left + 1, top + 1, 0, 0);       // Topleft corner

	    g.setColor(Color.black);
    	FontMetrics metrics = g.getFontMetrics(getFont());
	    g.drawString(label, x + ((width - metrics.stringWidth(label)) / 2), metrics.getHeight() - metrics.getDescent() + top + 3);

	    if (selected) {
    	    g.setColor(getBackground());
        	g.drawLine(left + 1, bottom, right - 3, bottom); // Bottomleft to bottomright
	        g.drawLine(left + 1, top + 3, left + 1, bottom); // Topleft to bottomleft indented by one
    	}
    }

    /**
     * Sets the selected tab when a tab is clicked.
     */
    public void mousePressed(MouseEvent event) {//Event event, int x, int y) {
    	int x = event.getX();
    	int y = event.getY();
    	
    	if (y < tabHeight) {
        	int xLoc = 0;
	        for (int i = 0; i < labels.size(); i++) {
    	        xLoc += tabWidths[i];
        	    if (x < xLoc) {
            	    setPanel((String) labels.elementAt(i));
                	postEvent(new Event(this, Event.ACTION_EVENT, null)); //MouseEvent(this, MouseEvent.MOUSE_PRESSED, x, y,)); //Event(this, event.ACTION_EVENT, null));
	                repaint();
    	            //return true;
        	    }
	        }
    	}
	    //return super.mousePressed(event);//event, x, y);
    }
    
    public void mouseClicked(MouseEvent event) {
    }
    
    public void mouseEntered(MouseEvent event) {
    }

    public void mouseExited(MouseEvent event) {
    }

    public void mouseReleased(MouseEvent event) {
    }

}