package chcs.envirachat.ui;

import java.awt.*;
import java.awt.image.*;
import java.io.*;

public class Map extends Component implements Runnable {
	Thread runner;
	String map;
	int x, y;
	int tCurX, tCurY;
	char tCurChr;
	int chrx, chry;
	//int startx, starty;
	public int SCROLL_UP = 0;
	public int SCROLL_RIGHT = 1;
	public int SCROLL_DOWN = 2;
	public int SCROLL_LEFT = 3;
	public int totalRows	= 0;
	public int totalCols	= 0;

	
	private boolean addchar = false;

	private Image osI;
	private Graphics osG;
	private boolean firstTime = true;
	private boolean doResize = true;
	private int topPos		= 0;
	private int leftPos		= 0;
	private int loadNum		= 0;
	
	
	public Map() {
		this("");
	}
	
	public Map(String map) {
		this.map = map.toLowerCase();
	}
	
	public void addNotify() {
		super.addNotify();
	}
	
	public Dimension getPreferredSize() {
		return(new Dimension(160, 96));
	}
	
	public Dimension getMinimumSize() {
		return(getPreferredSize());
	}
	
	//----------Try this Thread stuff!-----------//
	public void start(int x, int y, int row, int col, char chr) {
		if (runner == null) {
			runner = new Thread(this);
			runner.start();
		}
		//run();
		/*
		int x = this.tCurX;
		int y = this.tCurY;
		char chr = this.tCurChr;
		int row = y / 32;
		int col = x / 32;
		*/
		parseChar(osG, chr, x, y, row, col);
	}
	
	public void stop() {
		if (runner != null) {
			runner.stop();
			runner = null;
		}
	}
	
	public void run() {
		// Do Nothing, but LEAVE HERE!!!!!!!
	}
	//-------------------------------------------//
	public void changeMap(String map) {
		this.map = map;
		repaint();
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
		repaint(); // TODO: Make this better and faster!
	}
	
	public char charAt(int row, int col) {
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
	
	private boolean charAround(char chr, int row, int col) {
		if (this.charAt(row, col-1) == chr)
			return(true);
		else if (this.charAt(row-1, col) == chr)
			return(true);
		else if (this.charAt(row, col+1) == chr)
			return(true);
		else if (this.charAt(row+1, col) == chr)
			return(true);
		else
			return(false);
	}
	
	//---------------------------------------------------
	// Do some stuff here for adding a character!
	//---------------------------------------------------
	
	private void drawCharacter(Graphics g, int x, int y) {
	}
	
	
	private void parseChar(Graphics g, char chr, int x, int y, int row, int col) {
		Color colorGround = new Color(219, 219, 30);
		char dirChar;
		row++;
		//col++;
		switch(chr)	{
				case ' ': // Ground
					// Draw land
					g.setColor(colorGround);
					g.fillRect(x, y, 32, 32);
					//
                    //g.setColor(Color.black);
                    //g.drawRect(x,y,31,31);
					break;
				
				case '*': // Tree
					// Draw Ground
					g.setColor(colorGround);
					g.fillRect(x,y,32,32);			
					// Draw Tree (bring stump below to tree
					g.setColor(Color.black);
					g.drawLine(x+14,y+4,x+14,y+4);
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
					g.drawLine(x+21,y+12,x+21,y+12);
					g.drawLine(x+20,y+11,x+20,y+17);
					g.drawLine(x+19,y+13,x+19,y+13);
					g.drawLine(x+21,y+14,x+21,y+14);
					g.drawLine(x+19,y+18,x+18,y+19);
					g.drawLine(x+19,y+17,x+16,y+20);
					g.drawLine(x+12,y+20,x+14,y+20);
					g.setColor(new Color(0,102,0));
					g.drawLine(x+9,y+19,x+16,y+19);
					g.drawLine(x+17,y+18,x+19,y+16);
					g.drawLine(x+19,y+15,x+19,y+14);
					g.drawLine(x+18,y+13,x+18,y+13);
					g.drawLine(x+19,y+12,x+19,y+10);
					g.drawLine(x+18,y+9,x+14,y+5);
					g.setColor(new Color(102,204,51));
					g.fillRect(x+12,y+6,3,13);
					g.fillRect(x+9,y+7,7,12);
					g.fillRect(x+7,y+11,12,2);
					g.fillRect(x+7,y+14,12,2);
					g.fillRect(x+8,y+10,10,8);
					g.drawLine(x+16,y+8,x+16,y+18);
					g.drawLine(x+17,y+9,x+18,y+10);
					g.drawLine(x+18,y+16,x+18,y+16);
					g.setColor(new Color(0,204,51));
					g.drawLine(x+12,y+6,x+13,y+6);
					g.drawLine(x+9,y+7,x+11,y+7);
					g.drawLine(x+9,y+7,x+9,y+9);
					g.drawLine(x+8,y+10,x+7,y+11);
					g.drawLine(x+7,y+12,x+8,y+13);
					g.drawLine(x+7,y+14,x+8,y+17);
					g.drawLine(x+9,y+18,x+9,y+18);
					g.setColor(new Color(204,102,0));
					g.fillRect(x+12,y+22,3,3);
					g.drawLine(x+11,y+24,x+15,y+24);
					g.setColor(new Color(102,51,0));
					g.drawLine(x+15,y+21,x+15,y+23);
					g.drawLine(x+16,y+24,x+16,y+24);
					g.setColor(Color.black);
					g.drawLine(x+12,y+7,x+12,y+7);
					g.drawLine(x+10,y+10,x+10,y+10);
					g.drawLine(x+14,y+9,x+14,y+9);
					g.drawLine(x+16,y+11,x+16,y+11);
					g.drawLine(x+14,y+12,x+14,y+12);
					g.drawLine(x+10,y+14,x+10,y+14);
					g.drawLine(x+13,y+15,x+13,y+15);
					g.drawLine(x+16,y+15,x+16,y+15);
					g.drawLine(x+11,y+17,x+11,y+17);
					g.drawLine(x+14,y+17,x+14,y+17);					
					break;
				
				case '^': // Walls
					// Draw Ground
					g.setColor(colorGround);
					g.fillRect(x,y,32,32);

					// Draw Walls
					//dirChar = this.charAt(row, col + 1);
					g.setColor(Color.lightGray);
					g.fillRect(x+14,y+14,3,2);

					
					if(this.charAt(row-1,col) == '^') {
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
					if(this.charAt(row+1,col) == '^') {
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
					
					if(this.charAt(row, col+1) == '^' || this.charAt(row,col+1)=='d') {
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
						if(this.charAt(row, col-1)=='^') {
							g.setColor(Color.darkGray);
							g.drawLine(x+12,y+17,x+12,y+23);
						}
						g.setColor(Color.black);
						g.drawLine(x+17,y+13,x+17,y+24);
					}
					if(this.charAt(row,col - 1) == '^')	{
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
					else if (this.charAt(row, col-1) == 'd') {
						g.setColor(Color.black);
						g.drawLine(x,y+13,x+13,y+13);
						g.drawLine(x,y+16,x+13,y+16);
						g.drawLine(x,y+24,x+13,y+24);
						g.setColor(Color.darkGray);
						g.drawLine(x,y+23,x+12,y+23);
						if (this.charAt(row-1,col)=='^' || this.charAt(row+1,col)=='^') {
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
					
					
					break;
				
				case 'w': // Water
					// Draw Ground
					g.setColor(colorGround);
					g.fillRect(x,y,32,32);

					// Draw the Water
					/*
					if (this.chatAt(row, col + 1) == 'w') {
						if (this.charAt(
					
					
					if (this.charAt(row, col - 1) {
						if (this.charAt(row-1,col-1) {
							if(this.charAt(row+1,col-1)	{
								g.setColor(Color.black);
								g.drawLine(x+12,y,x+12,y+31);
								g.setColor(Color.blue);
								g.fillRect(x+13,y,19,32);
							}
							else {
								g.setColor(Color.black);
								g.drawLine(x+12,y,x+12,y+9);
								g.drawLine(x+13,y+10,x+14,y+13);
								g.drawLine(x+15,y+14,x+16,y+15);
								g.drawLine(x+17,y+16,x+20,y+17);
								g.drawLine(x+21,y+18,x+31,y+18);
								g.setColor(Color.blue);
								g.fillRect(x+21,y,11,18);
								g.fillRect(x+13,y,8,10);
								g.fillRect(x+14,y+10,7,2);
								g.fillRect(x+15,y+12,6,2);
								g.fillRect(x+17,y+14,4,2);
								g.drawLine(x+16,y+14,x+16,y+14);
								g.drawLine(x+19,y+16,x+20,y+16);
							}
						}
						else if(this.charAt(row+1,col-1) {
							g.setColor(Color.black);
							g.drawLine(x+12,y+31,x+12,y+22);
							g.drawLine(x+13,y+21,x+14,y+18);
							g.drawLine(x+15,y+17,x+16,y+16);
							g.drawLine(x+17,y+15,x+20,y+14);
							g.drawLine(x+21,y+13,x+31,y+13);
							g.setColor(Color.blue);
							g.fillRect(x+21,y+14,11,18);
							g.fillRect(x+13,y+22,8,10);
							g.fillRect(x+14,y+20,7,2);
							g.fillRect(x+15,y+18,6,2);
							g.fillRect(x+19,y+15,2,3);
							g.fillRect(x+17,y+16,2,2);
							g.drawLine(x+16,y+17,x+16,y+17);
						}
						
					}
					*/
					break;
				
				case '@': // Bolder
					// Draw bolder
					break;
				
				case '#': // Portal
					// Draw portal
					break;
				
				case 'd': // Door
					// Draw Ground
					g.setColor(colorGround);
					g.fillRect(x,y,32,32);

					// Draw door
					if (this.charAround('^', row, col) == true) {

						if (this.charAt(row, col-1)=='^' || this.charAt(row, col+1)=='^') {
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
						
						
							if (this.charAt(row, col-1) == '^') {
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
							if (this.charAt(row, col+1) == '^')	{
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
						else if (this.charAt(row-1,col)=='^' ||
								 this.charAt(row+1,col)=='^') {
							
						
						}
					
					}
					break;
			}
	}
	
	private void parseMap(Graphics g) {
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
			//parseChar(g, curChar, x, y, row, col);
			//this.tCurChr = curChar;
			//this.tCurX = x;
			//this.tCurY = y;
			loadNum++;
			getGraphics().drawLine(loadNum+20,60,loadNum+20,60);//(row*col)+20,60,(row*col)+20,60);
			this.start(x,y,row,col,curChar);
		}
		x = 0;
		y = 0;

	}

	public void getNumRowsCols() {
		String curMap = this.map;
		char curChar;
		int row = 0, col = 0;
		
		for (int a = 0; a < curMap.length()-1; a++) {
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

	public void update(Graphics g) {
		paint(g);
	}

	public void paint(Graphics g) {

		
		if (firstTime == true) {
			
			getNumRowsCols();						   
			osI = createImage(totalCols*32, totalRows*32+32);//this.getSize().width,this.getSize().height);
			osG = osI.getGraphics();
			firstTime = false;
			//parseMap(osG);
		}
		
		if (addchar == true)
			drawCharacter(osG, this.chrx, this.chry);
		else {
			if (doResize == true) { //This doResize isn't for resizes,
									//but it seems to work.
				g.setFont(new Font("TimesRoman", Font.PLAIN, 36));
				g.drawString("Please Wait. Loading Map...",40,40);
				g.drawLine(20,60,20,60);
				g.drawLine(this.map.length()+20,60,this.map.length()+20,60);
				parseMap(osG);
				doResize = false;
			}
			/*
			g.setColor(new Color(219,219,30));
			g.fillRect(0,0,totalCols*32,totalRows*32);
			g.setColor(Color.black);
			g.fillRect(0,totalRows*32+1,getSize().width,getSize().height);
			*/
			g.drawImage(osI, -(leftPos*32), -(topPos*32), this);
		}

 
	}

	public void scroll(int direction, int amount) { // "amount" is amount of cells
		switch(direction) {
			case 0:		// SCROLL_UP
				if (topPos != 0) {
					topPos = topPos - amount;
					repaint();
				}
				break;

			case 1:		// SCROLL_RIGHT
				leftPos = leftPos + amount;
				repaint();
				break;
				
			case 2:		// SCROLL_DOWN
				topPos = topPos + amount;
				repaint();
				break;
				
			case 3:		// SCROLL_LEFT
				if (leftPos != 0) {
					leftPos = leftPos - amount;
					repaint();
				}
				break;
		}
	}

}