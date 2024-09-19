package chcs.common.net;

import netscape.application.Image;

public class Client {
	private String user;
	private Image img;
	private int row;
	private int col;
	

	public Client(String user, Image img) {
		setUser(user);
		setImage(img);
	}
	
	public Client(String user) {
		this(user, null);
	}
	
	public Client(Image img) {
		this("", null);
	}
	
	public Client() {
		this("", null);
	}
	
	public void setUser(String user) {
		this.user = user;
	}

	public String getUser() {
		return(this.user);
	}

	public void setImage(Image img) {
		this.img = img;
	}

	public Image getImage() {
		return(this.img);
	}
	
	public void setRow(int row) {
		this.row = row;
	}
	
	public int getRow() {
		return(this.row);
	}
	
	public void setCol(int col) {
		this.col = col;
	}
	
	public int getCol() {
		return(this.col);
	}
}