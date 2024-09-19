package chcs.common.ui;

import java.awt.*;

public class CoolButton extends Canvas {
	
	protected String label;
	
	public static final int LEFT = 0;
	public static final int CENTER = 1;
	public static final int RIGHT = 2;
	
	public static final int MODE_NORMAL = 0;
	public static final int MODE_RAISED = 1;
	public static final int MODE_LOWERED = 2;

	protected int btnState = MODE_NORMAL;
	protected int alignment = CENTER;
	
	protected Font curFont = new Font("TimesRoman", Font.PLAIN, 36);
	
	public CoolButton() {
		this("", 1);
	}
	
	public CoolButton(String label) {
		this(label, 1);
	}
	
	public CoolButton(String label, int alignment) {
		this.label = label;
		this.alignment = alignment;
	}
	
	public boolean mouseDown(Event evt, int x, int y) {
		this.btnState = 2;
		repaint();
		return true;
	}

	public boolean mouseUp(Event evt, int x, int y) {
		this.btnState = 1;
		repaint();
		return true;
	}
	
	public boolean mouseEnter(Event evt, int x, int y) {
		this.btnState = 1;
		repaint();
		return true;
	}
	
	public boolean mouseExit(Event evt, int x, int y) {
		this.btnState = 0;
		repaint();
		return true;
	}
	
	
	public void update(Graphics g)
	{
		paint(g);
	}
	
	public Dimension getPreferredSize() {
		/*
		if ((getGraphics() == null) || (this.curFont == null))
			return(new Dimension(0, 0));
		FontMetrics fm = getGraphics().getFontMetrics(this.curFont);
		int width, height;
		if (this.label == null) {
			height = 0;
			width = 0;
		}
		else {
			height = fm.getHeight();
			width = fm.stringWidth(this.label);
		}
		return(new Dimension(width, height));
		*/
		return(new Dimension(43, 23));
	}
	
	public Dimension getMinimumSize() {
		return(getPreferredSize());
	}

	public String getLabel() {
		return(this.label);
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public int getAlignment() {
		return alignment;
	}
	
	public void setAlignment(int alignment) throws IllegalArgumentException {
		if ((alignment < this.LEFT) || (alignment > this.RIGHT))
			throw new IllegalArgumentException(alignment + " is not a valid alignment value for a CoolButton.");
		this.alignment = alignment;
	}
	
	public Font getFont() {
		return(this.curFont);
	}
	
	public void setFont(String fontname, int style, int size) {
		curFont = new Font(fontname, style, size);
	}
	
	public void paint(Graphics g) {
		FontMetrics fm = g.getFontMetrics(this.curFont);
		int h2;
		int h = getSize().height;
		int w = getSize().width;
		int height = fm.getHeight();
		int width = fm.stringWidth(this.label);
		int descent = fm.getDescent();
		int xoffset = 0;
		g.setFont(this.curFont);
		h2 = (this.getSize().height - (fm.getLeading() - fm.getHeight()))/ 2;// - this.curFont.getHeight(); //-2;



		
		g.setColor(Color.lightGray);
		g.fillRect(0,0,w,h);
		
		//if (this.label != null) {
		
		switch (this.alignment) {
			case LEFT:
				xoffset = 2;
				break;
			case CENTER:
				xoffset = (w - width) / 2 + 1;
				break;
			case RIGHT:
				xoffset = (w - width) - 2;
				break;
		}
		
		//h2 = fm.getHeight() + fm.getAscent() + fm.getDescent()+2;
		//h2 = (getSize().height - h2);
		
		switch(this.btnState) {
			case MODE_RAISED:
				g.draw3DRect(0,0,w-1,h-1,true);
				if (this.label != null) {
					g.setColor(Color.black);
					g.drawString(this.label, xoffset-1, h2-1);// - descent-1);
				}
				break;
			case MODE_LOWERED:
				g.draw3DRect(0,0,w-1,h-1,false);
				if (this.label != null) {
					g.setColor(Color.black);
					g.drawString(this.label, xoffset+1, h2+1);// - descent+1);
				}
				break;
			case MODE_NORMAL:
				g.setColor(Color.black);
				g.drawRect(0, 0, w-1, h-1);
				if (this.label != null)
					g.drawString(this.label, xoffset, h2);//height*2);// - descent);
				break;
		}
	}
}