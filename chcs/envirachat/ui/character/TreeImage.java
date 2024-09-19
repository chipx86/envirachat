package chcs.envirachat.ui.character;

import chcs.envirachat.ui.character.*;
import netscape.application.*;

public class TreeImage extends CharacterObject {
	
	public TreeImage() {
		super();
	}
	
	public void drawAt(Graphics g, int x, int y) {
		// Draw Ground
		g.setColor(colorGround);
		g.fillRect(x,y,width,height);			
		// Draw Tree
		g.setColor(Color.black);
		g.drawPoint(x+14,y+4);
		g.drawLine(x+12,y+5,x+16,y+5);
		g.drawLine(x+9,y+6,x+18,y+6);
		g.drawLine(x+8,y+7,x+19,y+7);
		g.drawLine(x+8,y+8,x+20,y+8);
		g.drawLine(x+8,y+9,x+21,y+9);
		g.drawLine(x+7,y+10,x+21,y+10);
		g.fillRect(x+6,y+11,17,2);
		g.drawLine(x+7,y+13,x+21,y+13);
		g.fillRect(x+6,y+14,17,2);
		g.fillRect(x+7,y+16,15,2);
		g.drawLine(x+8,y+18,x+20,y+18);
		g.drawLine(x+8,y+19,x+19,y+19);
		g.drawLine(x+9,y+20,x+18,y+20);
		g.fillRect(x+11,y+21,6,3);
		g.drawLine(x+10,y+24,x+17,y+24);
		g.drawLine(x+9,y+25,x+18,y+25);
		g.drawLine(x+10,y+26,x+20,y+26);
		g.drawLine(x+13,y+27,x+21,y+27);
		g.setColor(new Color(0,51,0));
		g.drawLine(x+16,y+6,x+21,y+11);
		g.drawLine(x+18,y+7,x+20,y+9);
		g.drawPoint(x+21,y+12);
		g.drawLine(x+20,y+11,x+20,y+17);
		g.drawPoint(x+19,y+13);
		g.drawPoint(x+21,y+14);
		g.drawPoint(x+19,y+18);
		g.drawLine(x+19,y+17,x+16,y+20);
		g.drawLine(x+12,y+20,x+14,y+20);
		g.setColor(new Color(0,102,0));
		g.drawLine(x+9,y+19,x+16,y+19);
		g.drawLine(x+17,y+18,x+19,y+16);
		g.drawLine(x+19,y+15,x+19,y+14);
		g.drawPoint(x+18,y+13);
		g.drawLine(x+19,y+12,x+19,y+10);
		g.drawLine(x+18,y+9,x+14,y+5);
		g.setColor(new Color(102,204,51));
		g.fillRect(x+12,y+6,3,13);
		g.fillRect(x+9,y+7,7,12);
		g.fillRect(x+7,y+11,12,2);
		g.fillRect(x+7,y+14,12,2);
		g.fillRect(x+8,y+10,10,8);
		g.drawPoint(x+16,y+8);
		g.drawLine(x+17,y+9,x+18,y+10);
		g.drawPoint(x+18,y+16);
		g.setColor(new Color(0,204,51));
		g.drawLine(x+12,y+6,x+13,y+6);
		g.drawLine(x+9,y+7,x+11,y+7);
		g.drawLine(x+9,y+7,x+9,y+9);
		g.drawLine(x+8,y+10,x+7,y+11);
		g.drawLine(x+7,y+12,x+8,y+13);
		g.drawLine(x+7,y+14,x+8,y+17);
		g.drawPoint(x+9,y+18);
		g.setColor(new Color(204,102,0));
		g.fillRect(x+12,y+22,3,3);
		g.drawLine(x+11,y+24,x+15,y+24);
		g.setColor(new Color(102,51,0));
		g.drawLine(x+15,y+21,x+15,y+23);
		g.drawPoint(x+16,y+24);
		g.setColor(Color.black);
		g.drawPoint(x+12,y+7);
		g.drawPoint(x+10,y+10);
		g.drawPoint(x+14,y+9);
		g.drawPoint(x+16,y+11);
		g.drawPoint(x+14,y+12);
		g.drawPoint(x+10,y+14);
		g.drawPoint(x+13,y+15);
		g.drawPoint(x+16,y+15);
		g.drawPoint(x+11,y+17);
		g.drawPoint(x+14,y+17);
	}

}