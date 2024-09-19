package chcs.envirachat.ui.character;

import chcs.envirachat.ui.character.*;
import netscape.application.*;

public class StoneImage extends CharacterObject {
	
	public StoneImage() {
		super();
	}
	
	public void drawAt(Graphics g, int x, int y) {
		int a, b;
		
		// Draw background
		g.setColor(new Color(204, 102, 51));
		g.fillRect(x,y,width,height);			
		
		g.setColor(Color.black);
		g.drawPoint(x+1, y+5);
		g.drawLine(x+2, y+6, x+8, y+6);
		g.drawLine(x+9, y+5, x+10, y+4);
		g.drawPoint(x+9,y+3);
		
		g.drawPoint(x+12,y+5);
		g.drawLine(x+13,y+6,x+19,y+6);
		g.drawPoint(x+20,y+5);
		g.drawPoint(x+20,y+3);
		
		g.drawPoint(x+22,y+5);
		g.drawLine(x+23,y+6,x+29,y+6);
		g.drawLine(x+30,y+5,x+31,y+4);
		g.drawPoint(x+30,y+3);
		
		g.drawPoint(x+1,y+11);
		g.drawLine(x+2,y+12,x+6,y+12);
		g.drawLine(x+7,y+11,x+8,y+10);
		g.drawPoint(x+7,y+9);
		
		g.drawPoint(x+12,y+11);
		g.drawLine(x+13,y+12,x+18,y+12);
		g.drawLine(x+19,y+11,x+20,y+10);
		g.drawPoint(x+19,y+9);
		
		g.drawPoint(x+24,y+11);
		g.drawLine(x+25,y+12,x+29,y+12);
		g.drawLine(x+30,y+11,x+31,y+10);
		g.drawPoint(x+30,y+9);
		
		for (a = 1; a <=25; a=a+8) {
			b = x + a;
			g.drawPoint(b,y+17);
			g.drawLine(b+1,y+18,b+4,y+18);
			g.drawLine(b+5,y+17,b+6,y+16);
			g.drawPoint(b+5,y+15);
			g.drawLine(b,y+22,b+5,y+22);
			g.drawPoint(b+6,y+21);
		}
		
		for (a = 1; a <= 23; a=a+11) {
			b = x + a;
			g.drawLine(b,y+26,b+2,y+26);
			g.drawPoint(b+3,y+25);
			g.drawLine(b+5,y+26,b+7,y+26);
			g.drawPoint(b+8,y+25);
		}
		for (a = 1; a <= 29; a=a+4) {
			b = x + a;
			g.drawLine(b,y+30,b+1,y+30);
			g.drawPoint(b+2,y+29);
		}
	
		g.setColor(new Color(153,102,51));
		
		g.drawLine(x,y+4,x+1,y+1);
		g.drawLine(x+2,y+2,x+9,y+2);
		
		g.drawLine(x+11,y+4,x+12,y+3);
		g.drawLine(x+13,y+2,x+19,y+2);
		
		g.drawLine(x+21,y+4,x+22,y+3);
		g.drawLine(x+23,y+2,x+29,y+2);
		
		g.drawLine(x,y+10,x+1,y+9);
		g.drawLine(x+2,y+8,x+6,y+8);
		
		g.drawLine(x+11,y+10,x+12,y+9);
		g.drawLine(x+13,y+8,x+18,y+8);
		
		g.drawLine(x+23,y+10,x+24,y+9);
		g.drawLine(x+25,y+8,x+29,y+8);
		
		for (a = 0; a<=24; a=a+8) {
			b = x + a;
			g.drawLine(b,y+16,b+1,y+15);
			g.drawLine(b+2,y+14,b+5,y+14);
			g.drawPoint(b,y+21);
			g.drawLine(b+1,y+20,b+6,y+20);
		}
		for (a = 0; a <= 22; a=a+11) {
			b = x + a;
			g.drawPoint(b,y+25);
			g.drawLine(b+1,y+24,b+3,y+24);
			g.drawPoint(b+5,y+25);
			g.drawLine(b+6,y+24,b+8,y+24);
		}
		for (a = 0; a <= 28; a=a+4) {
			b = x + a;
			g.drawPoint(b,y+29);
			g.drawLine(b+1,y+28,b+2,y+28);
		}
		g.drawRect(x+8,y,2,2);
		g.drawLine(x+23,y,x+23,y+1);
		g.drawLine(x+10,y+8,x+10,y+9);
		g.drawLine(x+9,y+9,x+9,y+10);
		g.drawRect(x+9,y+12,2,2);
		g.drawPoint(x+24,y+8);
		g.drawPoint(x+25,y+13);
		g.drawPoint(x+9,y+19);
		g.drawPoint(x+26,y+19);
		g.drawPoint(x+10,y+24);
		g.drawPoint(x+26,y+24);
		g.drawLine(x+11,y+27,x+11,y+28);
		g.drawPoint(x+27,y+28);
		g.drawPoint(x+9,y+31);
		g.drawPoint(x+24,y+31);
		
		g.setColor(new Color(153,102,0));
		g.fillRect(x+2,y+3,7,2);
		g.fillRect(x+13,y+3,7,2);
		g.fillRect(x+23,y+3,5,2);
		g.fillRect(x+2,y+9,5,2);
		g.fillRect(x+13,y+9,6,2);
		g.fillRect(x+25,y+9,5,2);
		
		for (a = 2; a <= 26; a=a+8) {
			b = x + a;
			g.fillRect(b,y+15,4,2);
			g.fillRect(b,y+21,4,1);
		}
		for (a = 2; a <= 24; a=a+11) {
			b = x + a;
			g.drawPoint(b,y+25);
			g.drawPoint(b+5,y+25);
		}
		for (a = 1; a <= 29; a=a+4) {
			b = x + a;
			g.drawPoint(b,y+29);
		}
		
		g.setColor(new Color(102,51,0));
		
		g.drawLine(x+7,y,x+7,y+1);
		g.drawLine(x+22,y,x+22,y+1);
		g.drawPoint(x,y+5);
			
		g.drawLine(x+5,y+4,x+6,y+3);
		g.drawLine(x+8,y+3,x+9,y+4);
		g.drawPoint(x+16,y+4);
		g.drawLine(x+19,y+3,x+20,y+4);
		g.drawLine(x+27,y+3,x+28,y+4);
		g.drawLine(x+29,y+3,x+30,y+4);
		
		g.drawLine(x+2,y+5,x+8,y+5);
		g.drawLine(x+10,y+5,x+11,y+5);
		g.drawLine(x+13,y+5,x+19,y+5);
		g.drawPoint(x+21,y+5);
		g.drawLine(x+23,y+5,x+29,y+5);
		g.drawPoint(x+31,y+5);
		g.drawLine(x+9,y+6,x+10,y+6);
		g.drawLine(x+20,y+6,x+21,y+6);
		g.drawLine(x+30,y+6,x+31,y+6);
		g.drawLine(x+3,y+7,x+9,y+7);
		g.drawLine(x+14,y+7,x+20,y+7);
		g.drawLine(x+24,y+7,x+30,y+7);
		
		g.drawLine(x+7,y+10,x+9,y+8);
		g.drawLine(x+23,y+9,x+23,y+8);
		g.drawLine(x+3,y+9,x+3,y+10);
		g.drawPoint(x+6,y+9);
		g.drawLine(x+14,y+10,x+15,y+9);
		g.drawLine(x+18,y+9,x+20,y+11);
		g.drawLine(x+29,y+9,x+31,y+11);
		g.drawPoint(x,y+11);
		g.drawLine(x+2,y+11,x+6,y+11);
		g.drawLine(x+8,y+11,x+9,y+11);
		g.drawLine(x+13,y+11,x+18,y+11);
		g.drawLine(x+25,y+11,x+29,y+11);
		g.drawPoint(x+27,y+10);
		g.drawLine(x+7,y+12,x+9,y+12);
		g.drawPoint(x+19,y+12);
		g.drawLine(x+30,y+12,x+31,y+12);
		g.drawLine(x+3,y+13,x+8,y+13);
		g.drawLine(x+14,y+13,x+19,y+13);
		g.drawLine(x+20,y+12,x+22,y+10);
		g.drawLine(x+24,y+13,x+25,y+14);
		g.drawLine(x+26,y+13,x+30,y+13);
		g.drawPoint(x+9,y+14);
		g.drawPoint(x+4,y+16);
		g.drawPoint(x+12,y+16);
		g.drawPoint(x+18,y+16);
		g.drawPoint(x+26,y+16);
		
		for (a = 0; a <= 24; a=a+8) {
			b = x + a;
			g.drawPoint(b,y+17);
			g.drawLine(b+2,y+17,b+5,y+17);
			g.drawLine(b+5,y+15,b+7,y+17);
			g.drawLine(b+6,y+18,b+7,y+18);
			g.drawLine(b+3,y+19,b+6,y+19);
			g.drawPoint(b,y+22);
			g.drawPoint(b+6,y+21);
			g.drawPoint(b+7,y+22);
			g.drawLine(b+2,y+23,b+7,y+23);
		}
		g.drawLine(x+8,y+19,x+8,y+20);
		g.drawLine(x+24,y+20,x+25,y+19);
		g.drawPoint(x+3,y+21);
		g.drawPoint(x+12,y+21);
		g.drawPoint(x+20,y+21);
		g.drawPoint(x+26,y+21);
		g.drawPoint(x+9,y+24);
		g.drawPoint(x+27,y+24);
		g.drawPoint(x,y+26);
		g.drawPoint(x+10,y+25);
		
		for (a = 2; a <= 24; a=a+11) {
			b = x + a;
			g.drawPoint(b+1,y+25);
			g.drawPoint(b+6,y+25);
			g.drawLine(b+2,y+26,b+3,y+26);
			if (a != 24)
				g.drawLine(b+7,y+26,b+8,y+26);
			else
				g.drawPoint(b+7,y+26);
			g.drawLine(b,y+27,b+2,y+27);
			//g.drawLine(b+7,y+27,b+8,y+27);
		}
		g.drawPoint(x+10,y+27);
		g.drawPoint(x,y+30);
		g.drawLine(x+7,y+27,x+9,y+27);
		g.drawLine(x+18,y+27,x+20,y+27);
		g.drawLine(x+29,y+27,x+31,y+27);
		
		for (a = 2; a <= 30; a=a+4) {
			b = x + a;
			g.drawPoint(b,y+29);
			if (a != 30)
				g.drawLine(b+1,y+30,b+2,y+30);
			else
				g.drawPoint(b+1,y+30);
			g.drawLine(b,y+31,b+1,y+31);
		}
		g.drawPoint(x+8,y+31);
		
		g.setColor(new Color(204,153,102));
		g.drawPoint(x+9,y);
		g.drawLine(x+24,y,x+24,y+1);
		g.drawLine(x+10,y+1,x+10,y+2);
		g.drawLine(x+6,y+4,x+7,y+3);
		g.drawLine(x+28,y+3,x+29,y+4);
		g.drawPoint(x+17,y+4);
		g.drawLine(x+4,y+9,x+4,y+10);
		g.drawLine(x+11,y+8,x+10,y+11);
		g.drawLine(x+15,y+10,x+16,y+9);
		g.drawPoint(x+24,y+10);
		g.drawPoint(x+28,y+10);
		g.drawLine(x+11,y+12,x+11,y+13);
		g.drawPoint(x+5,y+16);
		g.drawPoint(x+13,y+16);
		g.drawPoint(x+19,y+16);
		g.drawPoint(x+27,y+16);
		g.drawPoint(x+10,y+19);
		g.drawPoint(x+13,y+21);
		g.drawPoint(x+21,y+21);
		g.drawPoint(x+27,y+21);
		g.drawPoint(x+11,y+24);
		g.drawLine(x+12,y+27,x+12,y+28);
		g.drawPoint(x+27,y+27);
		g.drawPoint(x+25,y+31);
	}

}