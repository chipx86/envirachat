package chcs.common.ui;

import java.awt.*;
import java.util.Vector;

/**
 * A class used to create Windows 95-style Wizard dialogs.  Implement
 * the WizardListener interface in order to handle the WizardDialog's
 * completion.  The WizardDialog may be subclassed if custom page-to-page
 * functionality is required.
 */
public class WizardDialog extends Dialog {

    /**
     * A class implementing WizardListener to call when the finish
     * button is clicked.
     */
    protected WizardListener listener;

    /**
     * The Dialog's parent frame.
     */
    protected Frame parentFrame;

    /**
     * The currently selected panel.
     */
    protected Panel selectedPanel;

    /**
     * The last panel selected.
     */
    protected Panel lastPanel;

    /**
     * The WizardDialog's panels.
     */
    protected Vector panels = new Vector();

    /**
     * The respective images of the WizardDialog's panels.
     */
    protected Vector images = new Vector();

    /**
     * The "Back" button.
     */
    protected Button backButton;

    /**
     * The "Next"/"Finish" button.
     */
    protected Button nextButton;

    /**
     * The "Cancel" button.
     */
    protected Button cancelButton;

    /**
     * The WizardDialogs's primary panel.
     */
    protected WizardPanel tabbingPanel;

    /**
     * Constructs a new WizardDialog.
     */
    public WizardDialog(String title) {
    	this(null, null, title);
    }

    /**
     * Constructs a new WizardDialog.
     */
    public WizardDialog(Frame parentFrame, String title) {
	    this(null, parentFrame, title);
    }

    /**
     * Constructs a new WizardDialog.
     */
    public WizardDialog(WizardListener listener, String title) {
    	this(listener, null, title);
    }

    /**
     * Constructs a new WizardDialog.
     */
    public WizardDialog(WizardListener listener, Frame parentFrame, String title) {
		super(parentFrame, title, true);
		this.listener = listener;
		this.parentFrame = parentFrame;

    	setFont(new Font("Dialog", Font.PLAIN, 9));
    	setResizable(false);
    	setSize(441 + insets().left + insets().right, 323 + insets().top + insets().bottom);

		setLayout(new BorderLayout());
		tabbingPanel = new WizardPanel(this);
    	tabbingPanel.setLayout(null);
		add("Center", tabbingPanel);

	    backButton = new Button("< Back");
    	tabbingPanel.add(backButton);
	    backButton.setBounds(190, 265, 75, 23);
    	backButton.setEnabled(false);

	    nextButton = new Button("Next >");
    	tabbingPanel.add(nextButton);
	    nextButton.setBounds(265, 265, 75, 23);

	    cancelButton = new Button("Cancel");
    	tabbingPanel.add(cancelButton);
	    cancelButton.setBounds(350, 265, 75, 23);

	    Line3DCanvas line = new Line3DCanvas(11, 251, 415);
    	tabbingPanel.add(line);
    }

    /**
     * Returns the WizardDialog's parent Frame.
     */
    protected Frame getParentFrame() {
	    return(parentFrame);
    }

    /**
     * Centers the WizardDialog over its parent Frame.
     */
    public synchronized void show() {
	    if (getParent() != null) {
    	    Rectangle bounds = getParent().getBounds();
        	Rectangle abounds = getBounds();
	        setLocation(bounds.x + (bounds.width - abounds.width) / 2, bounds.y + (bounds.height - abounds.height) /2);
    	}
	    super.setVisible(true);
    	setSelectedPanel(getSelectedPanel()); // Tab into first component when the wizard is showing
    }

    /**
     * Adds a panel to the WizardDialog.
     *
     * @param panel the panel to add as a page of the WizardDialog
     */
    public void addPanel(Panel panel) {
	    addPanel(panel, null);
    }

    /**
     * Returns an array of the WizardDialog's panels.
     */
    public Panel[] getPanels() {
    	Panel[] panelArray = new Panel[panels.size()];
	    panels.copyInto(panelArray);
    	return(panelArray);
    }

    /**
     * Adds a panel to the WizardDialog.
     *
     * @param panel the panel to add as a page of the WizardDialog
     * @param image the image to show to the left of the panel
     */
    public void addPanel(Panel panel, Image image) {
	    if (image == null)
    	    panel.setBounds(7, 6, 436, 245);
	    else
    	    panel.setBounds(143, 6, 300, 245);
	    panel.setVisible(false);
    	panels.addElement(panel);
	    images.addElement(image);
    	tabbingPanel.add(panel);
	    if (panels.getSize() == 1)
    	    setSelectedPanel(panel);
    }

    /**
     * Returns the WizardDialog's current panel.
     */
    public Panel getSelectedPanel() {
	    return(this.selectedPanel);
    }

    /**
     * Returns the WizardDialog's current image.
     */
    public Image getSelectedImage() {
    	return((Image) images.elementAt(getSelectedPanelIndex()));
    }

    /**
     * Returns the index of the selected panel.
     */
    protected int getSelectedPanelIndex() {
	    for (int i = 0; i < panels.size(); i++)
    	    if (panels.elementAt(i) == getSelectedPanel())
        	    return(i);
	    return(-1);
    }

    /**
     * Sets the WizardDialog's current panel.
     *
     * @param panel the panel to set as the current page
     */
    public void setSelectedPanel(Panel panel) {
	    if (selectedPanel != null)
    	    selectedPanel.setVisible(false);
	    lastPanel = selectedPanel;
    	selectedPanel = panel;
	    selectedPanel.setVisible(true);
    	Component[] tabOrder = null;
	    if (selectedPanel instanceof TabbingPanel)
    	    tabOrder = ((TabbingPanel) selectedPanel).getTabOrder();
	    if (tabOrder == null)
    	    tabOrder = selectedPanel.getComponents();
	    Component[] tabOrder2 = new Component[tabOrder.length + 3];
    	int i = 0;
	    for ( ; i < tabOrder.length; i++)
    	    tabOrder2[i] = tabOrder[i];
	    tabOrder2[i++] = backButton;
    	tabOrder2[i++] = nextButton;
	    tabOrder2[i++] = cancelButton;
    	tabbingPanel.setTabOrder(tabOrder2);
	    tabbingPanel.setActiveComponent(null);
    	tabbingPanel.tabForward(); // Select the first component
    }

    /**
     * Sets the WizardDialog's current panel.
     *
     * @param index the zero-based index of the panel to use
     */
    public void setSelectedPanel(int index) {
	    setSelectedPanel((Panel) panels.elementAt(index));
    }

    /**
     * Returns the WizardDialog's last panel.  This may be a different
     * panel than the panel in the order if panels have been set
     * procedurally.
     */
    public Panel getLastPanel() {
	    return(this.lastPanel);
    }

    /**
     * Returns true if the WizardDialog is not on its last page.
     */
    protected boolean isNextButton() {
	    return(nextButton.getLabel().equals("Next >"));
    }

    /**
     * Set to true if the WizardDialog is not on its last page.
     */
    protected void setToNextButton() {
	    nextButton.setLabel("Next >");
    }

    /**
     * Returns true if the WizardDialog is on its last page.
     */
    protected boolean isFinishButton() {
    	return (nextButton.getLabel().equals("Finish"));
    }

    /**
     * Set to true if the WizardDialog is on its last page.
     */
    protected void setToFinishButton() {
    	nextButton.setLabel("Finish");
    }

    /**
     * Tells the WizardDialog it is not on its first page.
     */
    protected void canGoBack() {
    	backButton.setEnabled(true);
    }

    /**
     * Tells the WizardDialog it is on its first page.
     */
    protected void cantGoBack() {
    	backButton.setEnabled(false);
    }

    /**
     * Tells the WizardDialog it is not on its last page.
     */
    protected void canGoNext() {
    	nextButton.setEnabled(true);
    }

    /**
     * Tells the WizardDialog it is on its last page.
     */
    protected void cantGoNext() {
    	nextButton.setEnabled(false);
    }

    /**
     * Handle clicks on the WizardDialog's button bar.
     */
    public boolean handleEvent(Event event) {
    	if (event.id == Event.WINDOW_DESTROY) {
        	cancelButtonClick();
        	return(true);
	    }
    	else if (event.id == Event.ACTION_EVENT && event.target == cancelButton) {
        	cancelButtonClick();
    		return(true);
    	}
    	else if (event.id == Event.ACTION_EVENT && event.target == backButton) {
        	backButtonClick();
    	}
    	else if (event.id == Event.ACTION_EVENT && event.target == nextButton) {
        	if (isFinishButton())
            	finishButtonClick();
        	else
	            nextButtonClick();
    	    return(true);
	    }
    	return(super.handleEvent(event));
    }

    /**
     * Selects the previous panel.
     */
    protected void backButtonClick() {
	    setSelectedPanel(getSelectedPanelIndex() - 1);
    	setToNextButton();
	    canGoNext();
    	if (getSelectedPanelIndex() == 0)
        	cantGoBack();
    }

    /**
     * Selects the next panel.
     */
    protected void nextButtonClick() {
	    setSelectedPanel(getSelectedPanelIndex() + 1);
    	canGoBack();
	    if (getSelectedPanelIndex() == (panels.size() - 1))
    	    setToFinishButton();
	    else
    	    setToNextButton();
	    canGoNext();
    }

    /**
     * Finishes the WizardDialog.
     */
    protected void finishButtonClick() {
	    if (listener != null)
    	    listener.receiveResponse(this);
	    dispose();
    }

    /**
     * Cancels the WizardDialog.
     */
    protected void cancelButtonClick() {
	    dispose();
    }
}

/**
 * Used to paint the WizardDialog's image.
 */
class WizardPanel extends TabbingPanel {
    /**
     * The parent WizardDialog.
     */
    WizardDialog wizardDialog;

    /**
     * Constructs a new WizardPanel with the specified WizardDialog.
     */
    public WizardPanel(WizardDialog wizardDialog) {
	    this.wizardDialog = wizardDialog;
    }

    /**
     * Paints the WizardDialog and its selected image and panel.
     */
    public void paint(Graphics g) {
    	if (wizardDialog.getSelectedImage() != null) {
        	int width = 130;
	        int height = 238;
    	    g.setColor(Color.gray);
        	g.drawLine(11, 11, width - 1, 11);  // Topleft to topright
	        g.drawLine(11, 11, 11, height - 1); // Topleft to bottomleft

    	    g.setColor(Color.white);
        	g.drawLine(width, 11, width, height);  // Topright to bottomright
	        g.drawLine(11, height, width, height); // Bottomleft to bottomright

    	    g.drawImage(wizardDialog.getSelectedImage(), 12, 12, this);
    	}
    	super.paint(g);
    }
}