package chcs.envirachat.ui;

import netscape.application.*;

public class EnviraChatImage extends Image {
	Bitmap b;
	int width, height;	
	public EnviraChatImage() {
		super();
		b = Bitmap.bitmapNamed("EnviraChat.gif");
		this.width = b.width();
		this.height = b.height();
	}
	
	public void drawAt(Graphics g, int x, int y) {
		b.drawAt(g, x, y);
	}
	
	public int height() {
		return(height);
	}
	
	public int width() {
		return(width);
	}
}
