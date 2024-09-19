package chcs.envirachat.ui.character;

import chcs.envirachat.ui.character.*;
import netscape.application.*;

public class DoorImage extends CharacterObject {
	String map;
	int row, col;
	
	public DoorImage(String map, int row, int col) {
		super();
		this.map = map;
		this.row = row;
		this.col = col;
	}
	
	public void drawAt(Graphics g, int x, int y) {
		// Draw Ground
		g.setColor(colorGround);
		g.fillRect(x,y,width,height);			

		g.setColor(Color.lightGray);
		g.fillRect(x+14,y+14,3,2);
					
		if(charAround(map,'^',row,col)) {
			if (getCharAt(map,row, col-1)=='^' || getCharAt(map,row, col+1)=='^') {
				g.setColor(Color.lightGray);
				g.fillRect(x+8,y+4,16,20);
				g.setColor(Color.darkGray);
				g.drawLine(x+5,y+23,x+5,y+23);
				g.drawLine(x+6,y+23,x+6,y+9);
				g.drawLine(x+7,y+8,x+7,y+7);
				g.drawLine(x+8,y+6,x+10,y+4);
				g.drawLine(x+11,y+3,x+12,y+3);
				g.drawLine(x+13,y+2,x+18,y+2);
				g.drawLine(x+19,y+3,x+19,y+3);
				g.setColor(Color.gray);
				g.drawLine(x+5,y+17,x+5,y+22);						
				g.drawLine(x+6,y+8,x+6,y+8);
				g.drawLine(x+7,y+6,x+10,y+3);
				g.drawLine(x+12,y+2,x+12,y+2);
				g.drawLine(x+19,y+2,x+25,y+8);
				g.drawLine(x+21,y+3,x+24,y+6);
				g.drawLine(x+24,y+8,x+24,y+8);
				g.drawLine(x+25,y+9,x+25,y+22);
				g.drawLine(x+26,y+17,x+26,y+22);
				g.setColor(Color.black);
				g.drawLine(x+5,y+16,x+5,y+8);
				g.drawLine(x+6,y+7,x+6,y+6);
				g.drawLine(x+7,y+5,x+9,y+3);
				g.drawLine(x+10,y+2,x+11,y+2);
				g.drawLine(x+12,y+1,x+19,y+1);
				g.drawLine(x+20,y+2,x+21,y+2);
				g.drawLine(x+22,y+3,x+24,y+5);
				g.drawLine(x+25,y+6,x+25,y+7);
				g.drawLine(x+26,y+8,x+26,y+16);
				g.drawLine(x+7,y+24,x+24,y+24);
				g.drawLine(x+7,y+23,x+7,y+9);
				g.drawLine(x+8,y+8,x+8,y+7);
				g.drawLine(x+9,y+6,x+10,y+5);
				g.drawLine(x+11,y+4,x+12,y+4);
				g.drawLine(x+13,y+3,x+18,y+3);
				g.drawLine(x+19,y+4,x+20,y+4);
				g.drawLine(x+21,y+5,x+22,y+6);
				g.drawLine(x+23,y+7,x+23,y+8);
				g.drawLine(x+24,y+9,x+24,y+23);
				g.drawOval(x+18,y+13,3,3);
			
			
				if (getCharAt(map,row, col-1) == '^') {
					g.setColor(Color.black);
					g.drawLine(x,y+13,x+4,y+13);
					g.drawLine(x,y+16,x+4,y+16);
					g.drawLine(x,y+24,x+6,y+24);
					g.setColor(Color.lightGray);
					g.fillRect(x,y+14,5,2);
					g.setColor(Color.gray);
					g.fillRect(x,y+17,5,6);
					g.setColor(Color.darkGray);
					g.drawLine(x,y+23,x+4,y+23);
				}
				else {
					g.setColor(Color.black);
					g.drawLine(x+3,y+13,x+4,y+13);
					g.drawLine(x+3,y+14,x+3,y+24);
					g.drawLine(x+4,y+24,x+6,y+24);
					g.drawLine(x+4,y+16,x+4,y+16);
					g.setColor(Color.lightGray);
					g.drawLine(x+4,y+14,x+4,y+15);
					g.setColor(Color.darkGray);
					g.drawLine(x+4,y+23,x+4,y+23);
					g.setColor(Color.gray);
					g.drawLine(x+4,y+17,x+4,y+22);
				}
				if (getCharAt(map,row, col+1) == '^')	{
					g.setColor(Color.black);
					g.drawLine(x+27,y+13,x+31,y+13);
					g.drawLine(x+27,y+16,x+31,y+16);
					g.drawLine(x+25,y+24,x+31,y+24);
					g.setColor(Color.lightGray);
					g.fillRect(x+27,y+14,5,2);
					g.setColor(Color.gray);
					g.fillRect(x+27,y+17,5,6);
					g.setColor(Color.darkGray);
					g.drawLine(x+25,y+23,x+31,y+23);
			
				}
				else {
					g.setColor(Color.black);
					g.drawLine(x+27,y+13,x+28,y+13);
					g.drawLine(x+28,y+14,x+28,y+24);
					g.drawLine(x+27,y+16,x+27,y+16);
					g.drawLine(x+25,y+24,x+28,y+24);
					g.setColor(Color.lightGray);
					g.drawLine(x+27,y+14,x+27,y+15);
					g.setColor(Color.gray);
					g.drawLine(x+27,y+17,x+27,y+22);
					g.setColor(Color.darkGray);
					g.drawLine(x+25,y+23,x+27,y+23);
						
				}
			}
			else if (getCharAt(map,row-1,col)=='^' ||
					 getCharAt(map,row+1,col)=='^') {
				
				
			}
		
		}

	}

}