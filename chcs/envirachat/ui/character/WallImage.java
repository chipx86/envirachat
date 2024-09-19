package chcs.envirachat.ui.character;

import chcs.envirachat.ui.character.*;
import netscape.application.*;

public class WallImage extends CharacterObject {
	String map;
	//int row = y / 32, col = x / 32;
	int row, col;
	
	public WallImage(String map, int row, int col) {
		super();
		this.map = map;
		this.row = row;
		this.col = col;
	}
	
	public void drawAt(Graphics g, int x, int y) {
		//System.out.println(row+"     ---     "+col);
		// Draw Ground
		g.setColor(colorGround);
		g.fillRect(x+0,y+0,32,32);
		// Draw Walls
		g.setColor(Color.lightGray);
		g.fillRect(x+14,y+14,3,2);
		if(getCharAt(this.map, row-1,col) == '^') {
			g.setColor(Color.black);
			g.drawLine(x+13,y,x+13,y+13);
			g.drawLine(x+17,y,x+17,y+13);
			g.setColor(Color.lightGray);
			g.fillRect(x+14,y,3,14);
		}
		else {
			g.setColor(Color.black);
			g.drawLine(x+13,y+13,x+17,y+13);
		}
		if(getCharAt(this.map, row+1,col) == '^') {
			g.setColor(Color.black);
			g.drawLine(x+13,y+16,x+13,y+31);
			g.drawLine(x+17,y+16,x+17,y+31);
			g.setColor(Color.lightGray);
			g.fillRect(x+14,y+16,3,18);
		}
		else {
			g.setColor(Color.black);
			g.drawLine(x+13,y+24,x+17,y+24);
			g.drawLine(x+13,y+16,x+17,y+16);
			g.setColor(Color.darkGray);
			g.drawLine(x+13,y+23,x+17,y+23);
			g.setColor(Color.gray);
			g.fillRect(x+13,y+17,5,6);
		}

		if(getCharAt(this.map, row, col+1) == '^' || getCharAt(this.map, row,col+1)=='d') {
			g.setColor(Color.black);
			g.drawLine(x+18,y+13,x+31,y+13);
			g.drawLine(x+18,y+16,x+31,y+16);
			g.drawLine(x+18,y+24,x+31,y+24);
			g.setColor(Color.darkGray);
			g.drawLine(x+18,y+23,x+31,y+23);
			g.setColor(Color.gray);
			g.fillRect(x+18,y+17,14,6);
			g.setColor(Color.lightGray);
			g.fillRect(x+17,y+14,15,2);
		}
		else {
			if(getCharAt(this.map, row, col-1)=='^') {
				g.setColor(Color.darkGray);
				g.drawLine(x+12,y+17,x+12,y+23);
			}
			g.setColor(Color.black);
			g.drawLine(x+17,y+13,x+17,y+24);
		}
		if(getCharAt(this.map, row,col - 1) == '^')	{
			g.setColor(Color.black);
			g.drawLine(x,y+13,x+12,y+13);
			g.drawLine(x,y+16,x+12,y+16);
			g.drawLine(x,y+24,x+12,y+24);
			g.setColor(Color.darkGray);
			g.drawLine(x,y+23,x+12,y+23);
			g.setColor(Color.gray);
			g.fillRect(x,y+17,13,6);
			g.setColor(Color.lightGray);
			g.fillRect(x,y+14,14,2);
		}
		else if (getCharAt(this.map, row, col-1) == 'd') {
			g.setColor(Color.black);
			g.drawLine(x,y+13,x+13,y+13);
			g.drawLine(x,y+16,x+13,y+16);
			g.drawLine(x,y+24,x+13,y+24);
			g.setColor(Color.darkGray);
			g.drawLine(x,y+23,x+12,y+23);
			if (getCharAt(this.map, row-1,col)=='^' || getCharAt(this.map, row+1,col)=='^') {
				g.drawLine(x+12,y+17,x+12,y+23);
				g.setColor(Color.gray);							
			}
			else {
				g.setColor(Color.gray);
				g.drawLine(x+12,y+17,x+12,y+22);
			}
			g.fillRect(x,y+17,12,6);
			g.setColor(Color.lightGray);
			g.fillRect(x,y+14,14,2);
		}
		else {
			g.setColor(Color.black);
			g.drawLine(x+13,y+13,x+13,y+24);
		}
	}

}