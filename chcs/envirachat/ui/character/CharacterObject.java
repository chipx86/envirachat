package chcs.envirachat.ui.character;

import netscape.application.*;

public class CharacterObject extends Image {//Image {
	int width, height;
	//int x, y;
	Color colorGround = new Color(219, 219, 30);
	
	public CharacterObject() {
		this.width = 32;
		this.height = 32;
		//this.x = x();
		//this.y = y();
	}
	
	public void drawAt(Graphics g, int x, int y) {
	}
	
	
	public int width() {
		return(width);
	}
	
	public int height() {
		return(height);
	}
	/*
	public int x() {
		return(x);
	}
	
	public int y() {
		return(y);
	}
	*/
	public char getCharAt(String str, int row, int col) {
		char curChar;
		int curRow = 0, curCol = 0;
		
		for (int a = 0; a < str.length(); a++) {
			curChar = str.charAt(a);
			
			if (curRow == row-1 && curCol == col-1)
				return(curChar);
			
			if (curChar != '\n')
				curCol++;
			else {
				curCol = 0;
				curRow++;
			}
		}
		return(0);
	}

	public boolean charAround(String str, char chr, int row, int col) {
		if (getCharAt(str, row, col-1) == chr)
			return(true);
		else if (getCharAt(str, row-1, col) == chr)
			return(true);
		else if (getCharAt(str, row, col+1) == chr)
			return(true);
		else if (getCharAt(str, row+1, col) == chr)
			return(true);
		else
			return(false);
	}
	
}