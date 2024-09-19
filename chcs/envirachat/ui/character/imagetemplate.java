package chcs.envirachat.ui.character;

import chcs.envirachat.ui.character.*;
import netscape.application.*;

public class Image extends CharacterObject {
	String map;
	int row, col;
	
	public Image(String map, int row, int col) {
		super();
		this.map = map;
		this.row = row;
		this.col = col;
	}
	
	public void drawAt(Graphics g, int x, int y) {
		// Draw Ground
		g.setColor(colorGround);
		g.fillRect(x,y,width,height);			

	}

}