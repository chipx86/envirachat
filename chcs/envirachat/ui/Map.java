package chcs.envirachat.ui;

import netscape.application.*;
import netscape.util.*;
import chcs.envirachat.ui.character.*;


public class Map extends View { //implements Target {
	public static int UP = 0;
	public static int RIGHT = 1;
	public static int DOWN = 2;
	public static int LEFT = 3;
	
	String map;
	int x, y;
	int totalRows, totalCols;
	int topPos, leftPos;
	private Bitmap osI;
	private Graphics osG;
	private boolean firstTime = true;
	private boolean doParseMap = true;


	public Map(String map, int x, int y, int width, int height) {
		super(x, y, width, height);
		this.map = map.toLowerCase();
	}
	
	public void setMap(String map) {
		this.map = map;
		draw();
	}
	
	public void setCharAt(char chr, int row, int col) {
		int curRow = 0, curCol = 0;
		StringBuffer b = new StringBuffer(this.map);
		
		for (int a = 0; a < b.length(); a++) {
			if (curRow == row-1 && curCol == col-1) {
				b.setCharAt(a, chr);
				break;
			}
		}
		this.map = b.toString();
		draw();

	}

	public char getCharAt(int row, int col) {
		String curMap = this.map;
		char curChar;
		int curRow = 0, curCol = 0;
		
		for (int a = 0; a < curMap.length(); a++) {
			curChar = curMap.charAt(a);
			
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

	private void parseChar(Graphics g, char chr, int x, int y, int row, int col) {
		char dirChar;
		row++;
		switch(chr) {
			case ' ':	// Ground
				//addSubview(new GroundImageView(x, y));
				
				GroundImage groundImage = new GroundImage();
				groundImage.drawAt(g, x, y);
				
				break;
			case '*':	// Tree
				//addSubview(new TreeImageView(x, y));
				TreeImage treeImage = new TreeImage();
				treeImage.drawAt(g, x, y);
				break;
			case '^':	// Walls
				//addSubview(new WallImageView(this.map, x, y, row, col));
				WallImage wallImage = new WallImage(this.map, row, col);
				wallImage.drawAt(g, x, y);
				break;
			case 'w':	// Water
				////addSubview(new WaterImageView(this.map, x, y);
				//WaterImage waterImage = new WaterImage(this.map, row, col);
				//waterImage.drawAt(g,x,y);
				break;
			case '@':	// Stone
				StoneImage stoneImage = new StoneImage();
				stoneImage.drawAt(g, x, y);
				break;
			case '#':
				//addSubview(new PortalImageView(x, y);
				break;
			case 'd':
				DoorImage doorImage = new DoorImage(this.map, row, col);
				doorImage.drawAt(g, x, y);
				break;
		}
	}
	
	private void parseMap(Graphics g) {
		System.out.println("ParseMap");
		String curMap = this.map;
		char curChar;
		int row = 0, col = 0;
		
			
		for (int a = 0; a < curMap.length(); a++)	{
			curChar = curMap.charAt(a);

			if (curChar != '\n') {
				x = col * 32;
				col++;
			}
			else {
				col = 0;
				row++;
				x = 0;
				y = row * 32;
			}
			parseChar(g, curChar, x, y, row, col);
		}
		x = 0;
		y = 0;

	}

	public void getNumRowsCols() {
		String curMap = this.map;
		char curChar;
		int row = 0, col = 0;
		
		for (int a = 0; a < curMap.length(); a++) {
			curChar = curMap.charAt(a);
			
			if (curChar != '\n')
				col++;
			else {
				this.totalCols = col;
				col = 0;
				row++;
				this.totalRows++;
			}
		}		
	}
	
	public void drawView(Graphics g) {
		if (firstTime) {
			getNumRowsCols();
			osI = new Bitmap(totalCols*32, totalRows*32);
			osG = osI.createGraphics();
			firstTime = false;
		}
		if (doParseMap) {
			//g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
			//g.drawString("Please Wait. Loading Map...",20,20);
			parseMap(osG);
			doParseMap = false;
		}
		g.drawBitmapAt(osI, -(leftPos*32), -(topPos*32));
	}

	public void scroll(int direction, int amount) { // "amount" is amount of cells
		switch(direction) {
			case 0:		// SCROLL_UP
				if (topPos != 0) {
					topPos = topPos - amount;
					draw();
				}
				break;

			case 1:		// SCROLL_RIGHT
				leftPos = leftPos + amount;
				draw();
				break;
				
			case 2:		// SCROLL_DOWN
				topPos = topPos + amount;
				draw();
				break;
				
			case 3:		// SCROLL_LEFT
				if (leftPos != 0) {
					leftPos = leftPos - amount;
					draw();
				}
				break;
		}
	}
	
	public void sizeBy(int deltaWidth, int deltaHeight) {
		super.sizeBy(deltaWidth, deltaHeight);
	}
	
	public void moveBy(int deltaX, int deltaY) {
		super.moveTo(deltaX, deltaY);
	}
	
	public void sizeTo(int x, int y) {
		super.sizeTo(x, y);
	}
	
	public void moveTo(int x, int y) {
		super.moveTo(x, y);
	}
}