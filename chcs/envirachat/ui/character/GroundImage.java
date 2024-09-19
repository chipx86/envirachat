package chcs.envirachat.ui.character;

import chcs.envirachat.ui.character.*;
import netscape.application.*;

public class GroundImage extends CharacterObject {
	
	public GroundImage() {
		super();
	}
	
	public void drawAt(Graphics g, int x, int y) {
		// Draw Ground
		g.setColor(colorGround);
		g.fillRect(x,y,width,height);			
	}

}