package chcs.envirachat.ui.character;

import chcs.envirachat.ui.character.*;
import netscape.application.*;

public class WaterImage extends CharacterObject {
	String map;
	int row, col;
	
	public WaterImage(String map, int row, int col) {
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
		if(getCharAt(this.map, row-1,col) == 'w') {

		}
		else {

		}
		if(getCharAt(this.map, row+1,col) == 'w') {

		}
		else {

		}

		if(getCharAt(this.map, row, col+1) == 'w') {

		}
		else {
			if(getCharAt(this.map, row, col-1)=='w') {

			}

		}
		if(getCharAt(this.map, row,col - 1) == 'w')	{

		}
		else if (getCharAt(this.map, row, col-1) == 'd') {

			if (getCharAt(this.map, row-1,col)=='^' || getCharAt(this.map, row+1,col)=='^') {

			}
			else {

			}

		}
		else {

		}
	}

	}

}