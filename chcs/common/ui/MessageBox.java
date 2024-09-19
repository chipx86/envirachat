package chcs.common.ui;

import java.awt.*;


public class MessageBox extends Frame
{
	
	// Button Styles
	int btn_Ok 					= 0;
	int btn_OkCancel			= 1;
	int btn_AbortRetryIgnore	= 2;
	int btn_YesNoCancel			= 3;
	int btn_YesNo				= 4;
	int btn_RetryCancel			= 5;
	int btn_Custom				= 6;
	// Icon Styles
	int icon_Stop				= 16;
	int icon_Question			= 32;
	int icon_Exclamation		= 48;
	int icon_Info				= 64;
	// Default Button
	int default_1				= 0;
	int default_2				= 256;
	int default_3				= 512;
	
	private Dialog 	dl 			= new Dialog(this, true);
	private Panel 	pnlButtons 	= new Panel();
	private Canvas	cIcon 		= new Canvas();
	private Button	btn1		= new Button();
	private Button	btn2		= new Button();
	private Button	btn3		= new Button();
	/*
	private Button	btnOk 		= new Button("OK");
	private Button	btnCancel 	= new Button("Cancel");
	private Button	btnYes 		= new Button("&Yes");
	private Button	btnNo 		= new Button("&No");
	private Button	btnRetry 	= new Button("&Retry");
	private Button	btnAbort 	= new Button("&Abort");
	private Button	btnIgnore 	= new Button("&Ignore");
	*/

	public MessageBox(String msg)
	{
		this(msg, "", 0, null);
	}
	
	public MessageBox(String msg, int type)
	{
		this(msg, "", type, null);
	}

	public MessageBox(String msg, int type, Panel pnl)
	{
		this(msg, "", type, pnl);
	}

	public MessageBox(String msg, String title)
	{
		this(msg, title, 0, null); //btn_Ok + No Icon + default_1
	}
	
	public MessageBox(String msg, String title, int type)
	{
		this(msg, title, type, null);
	}
	
	public MessageBox(String msg, String title, int type, Panel pnl)
	{
		super(title);
		int type2 = type;
		int btnStyle, iconStyle, defBtn;
		
		dl.setResizable(false);
		dl.setTitle(title);
		dl.setLayout(new BorderLayout());
		dl.add("Center", new Label(msg));
		//dl.add("West", pnlIcon);
		dl.add("West", cIcon);
		
		// ---------- Check Default Button ---------- \\
		if (type2 >= this.default_3) {
			type2 = type2 - this.default_3;
			defBtn = this.default_3;
		}
		else if (type2 >= this.default_2) {
			type2 = type2 - this.default_2;
			defBtn = this.default_2;
		}
		else if (type2 >= this.default_1) {
			type2 = type2 - this.default_1;
			defBtn = this.default_1;
		}
		// --------------- Check Icon --------------- \\
		if (type2 >= this.icon_Info) {
			type2 = type2 - this.icon_Info;
			iconStyle = this.icon_Info;
		}
		else if (type2 >= this.icon_Exclamation) {
			type2 = type2 - this.icon_Exclamation;
			iconStyle = this.icon_Exclamation;
		}
		else if (type2 >= this.icon_Question) {
			type2 = type2 - this.icon_Question;
			iconStyle = this.icon_Question;
		}
		else if (type2 >= this.icon_Stop) {
			type2 = type2 - this.icon_Stop;
			iconStyle = this.icon_Stop;
		}
		// ----------- Check Button Style ----------- \\
		if (type2 != this.btn_Custom) {
			dl.add("South", pnlButtons);
			dl.setLayout(new FlowLayout());
		}
		switch (type2) {
			case 6: //btn_Custom:
				dl.add("South", pnl);
				break;
			case 5: //btn_RetryCancel:
				btn1.setLabel("&Retry");
				btn2.setLabel("Cancel");
				pnlButtons.add(btn1);
				pnlButtons.add(btn2);
				break;
			case 4: //btn_YesNo:
				btn1.setLabel("&Yes");
				btn2.setLabel("&No");
				pnlButtons.add(btn1);
				pnlButtons.add(btn2);
				break;
			case 3: //btn_YesNoCancel:
				btn1.setLabel("&Yes");
				btn2.setLabel("&No");
				btn3.setLabel("Cancel");
				pnlButtons.add(btn1);
				pnlButtons.add(btn2);
				pnlButtons.add(btn3);
				break;
			case 2: //btn_AbortRetryIgnore:
				btn1.setLabel("&Abort");
				btn2.setLabel("&Retry");
				btn3.setLabel("&Ignore");
				pnlButtons.add(btn1);
				pnlButtons.add(btn2);
				pnlButtons.add(btn3);
				break;
			case 1: //btn_OkCancel:
				btn1.setLabel("OK");
				btn2.setLabel("Cancel");
				pnlButtons.add(btn1);
				pnlButtons.add(btn2);
				break;
			case 0: //btn_Ok:
				btn1.setLabel("OK");
				pnlButtons.add(btn1);
				break;
		}
	}
	
	public boolean action(Event evt, Object arg)
	{
		if (evt.target instanceof Button) {
			
			
			return true;
		}
		else return false;
	}
	
	public void paint(Graphics g)
	{
		//switch
	
	}
}