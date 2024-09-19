package chcs.envirachat.ui;

import netscape.application.*;

public class CharacterButtons extends View implements Target {
	//Vector v;
	Image image[] = new Image[255];
	Button button[] = new Button[255];
	String string[] = new String[255];
	private int curBtnItem = 0;
	private int curX =0, curY = 50;
	private int xFormula;
	public CharacterButtons(int x, int y, int w, int h) {
		super(x,y,w,h);
		//super.rootView().setColor(Color.black);
		//v = new Vector(1);
		xFormula = (width()-158)/2;
		curX = xFormula;
	}
	
	public void addButton(Image img) {
		image[curBtnItem] = img;
		this.addButton();
	}
	
	public void addButton() {
		button[curBtnItem] = new Button(curX, curY, 38, 38);
		if (image[curBtnItem] != null)
			button[curBtnItem].setImage(image[curBtnItem]);
		
		curBtnItem++;
		if (curX != 120+xFormula)
			curX = curX + 40;
		else {
			curX = xFormula;
			curY = curY + 40;
		}
		draw();
	}
	
	public void drawView(Graphics g) {
		for (int a = 0; a < curBtnItem; a++)
			addSubview(button[a]);
	}

	public void performCommand(String str, Object obj) {
	}
}