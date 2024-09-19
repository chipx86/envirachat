package chcs.common.ui;

import java.awt.*;
import java.awt.event.*;

/**
 * A panel that supports tabbing between its components.  If a specific
 * tab order is not specified, the Z order is used.  Note that the Z
 * order may vary from platform to platform, so it is recommended that
 * a specific tab order be set.  Under Windows 95 and NT, the panel also
 * makes TextFields post LOST_FOCUS and GOT_FOCUS events, since the
 * AWT 1.0.2 does not post these events.  If tabbing should be supported
 * for an entire frame, use the TabbingFrame class instead of adding a
 * TabbingPanel to a Frame.
 */
public class TabbingPanel extends Panel implements ActionListener {

    /**
     * The active component.
     */
    protected Component activeComponent;

    /**
     * The order with which to tab between components.
     */
    protected Component[] tabOrder;


	/*
	public boolean handleEvent(Event event) {
		if ((event.id == Event.MOUSE_DOWN) || (event.id == Event.GOT_FOCUS) || (event.id == Event.ACTION_EVENT)) {
		    if (event.target instanceof Component)
			    setActiveComponent((Component) event.target);
	    }
		else if ((event.id == Event.KEY_PRESS) && (event.key == 9)) {
			if (event.shiftDown())
		    	tabBackward();
			else
		    	tabForward();
			return(true);
		}
		return(super.handleEvent(event));
	}
	*/
	
    /**
     * Returns the order with which to tab between components.
     */
    public Component[] getTabOrder() {
    	return(this.tabOrder);
    }

    /**
     * Sets the order with which to tab between components.
     */
    public void setTabOrder(Component[] components) {
    	this.tabOrder = components;
    }

    /**
     * Returns the active component.
     */
    public Component getActiveComponent() {
    	return(this.activeComponent);
    }

    /**
     * Sets the active component.
     */
	public void setActiveComponent(Component c) {
		if (activeComponent == c) {
		    if (activeComponent instanceof TextField)
	    	    ((TextField) activeComponent).selectAll();
		}
		else {
    		// HACK: Windows 95 AWT 1.0.2 does not handle TextField LOST_FOCUS and GOT_FOCUS, so done here
	        if ((System.getProperty("os.name").length() >= 7) && (System.getProperty("os.name").substring(0, 7).equals("Windows"))) {
    	    	if (activeComponent instanceof TextField)
        	        activeComponent.postEvent(new Event(activeComponent, Event.LOST_FOCUS, null));
	        	if (c != null) {
    	        	c.requestFocus();
        	    	if (c instanceof TextField) {
            			((TextField) c).selectAll();
                	    c.postEvent(new Event(activeComponent, Event.GOT_FOCUS, null));
	            	}
    	        }
        	}
	        if (tabOrder == null)
    	        activeComponent = c;
        	else {
	            activeComponent = null;
    	        for (int i = 0; i < tabOrder.length; i++)
        	        if(tabOrder[i] == c) {
            	        activeComponent = c;
                	    break;
	                }
    	    }
	    }
	}

    /**
     * Handles tabbing to the next component.
     */
	public synchronized void tabForward() {
		int i = getTabComponentIndex(activeComponent);
		int count = 0;
		Component c = null;
		do {
			i++;
			count++;
			if (i >= getTabComponentCount())
				i = 0;
			c = getTabComponent(i);
			if (isComponentAvailable(c)) {
			    setActiveComponent(c);
				break;
			}
		} while (count < getTabComponentCount());
	}

    /**
     * Handles tabbing to the previous component.
     */
	public synchronized void tabBackward() {
		int i = getTabComponentIndex(activeComponent);
		int count = 0;
		Component c = null;
		do {
			i--;
			count++;
			if (i < 0)
				i = getTabComponentCount() - 1;
			c = getTabComponent(i);
			if (isComponentAvailable(c)) {
			    setActiveComponent(c);
				break;
			}
		} while (count < getTabComponentCount());
	}

    /**
     * Returns the number of components to tab between.
     */
    protected int getTabComponentCount() {
	    if (tabOrder == null)
    	    return(countComponents());
	    else
    	    return(tabOrder.length);
    }

    /**
     * Returns true if the component may be active component.
     */
    protected boolean isComponentAvailable(Component c) {
	    return ((c != null) && c.isEnabled() && c.isVisible() && c.getParent().isVisible() && !(c instanceof Label) && !(c instanceof NoMarginLabel));
    }

    /**
     * Returns the component located at the specified index within the tab order.
     */
    protected Component getTabComponent(int index) {
	    if (tabOrder == null)
    	    return(getComponent(index));
	    else
    	    return(tabOrder[index]);
    }

    /**
     * Returns the index of the component within the tab order.
     * Returns -1 if the component is not within the tab order.
     */
    protected int getTabComponentIndex(Component c) {
	    if (tabOrder == null) {
    	    for (int i = 0; i < countComponents(); i++)
        	    if (getComponent(i) == c)
            	    return(i);
	        return(-1);
    }
    	else {
        	for (int i = 0; i < tabOrder.length; i++)
            	if (tabOrder[i] == c)
                	return(i);
	        return(-1);
    	}
    }

	public void processMouseEvent(MouseEvent e) {
		if (e.getID() == MouseEvent.MOUSE_PRESSED) {
			if (e.getSource() instanceof Component)
				setActiveComponent((Component) e.getSource());
		}
	}
	
	protected void processFocusEvent(FocusEvent e) {
		if (e.getID() == FocusEvent.FOCUS_GAINED)
			if (e.getSource() instanceof Component)
				setActiveComponent((Component) e.getSource());
		}
	}
	
	/*
	protected void processEvent(AWTEvent e) {
		if (e.getID() == AWTEvent.ACTION_LISTENER) {
			if (e.getSource() instanceof Component)
				setActiveComponent((Component) e.getSource());
		}
	}
	*/
	
	protected void processKeyEvent(KeyEvent e) {
		if (e.getID() == KeyEvent.KEY_PRESSED) {
			if (e.getKeyCode() == KeyEvent.VK_TAB)
				if (e.getKeyCode() == KeyEvent.VK_SHIFT)
					tabBackward();
				else
					tabForward();
			}
		}
	}
	
	protected void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof Component)
			setActiveComponent((Component e.getSource());
	}

}