// EnviraChat.java
/*
import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;
*/
import netscape.application.*;
import netscape.util.*;
import java.io.*;
import java.net.*;
import java.lang.*;
import chcs.envirachat.ui.*;
import chcs.envirachat.ui.character.*;
//import chcs.common.ui.*;
import chcs.common.net.*;

public class EnviraChat extends Application implements Target, WindowOwner, Runnable {
	Thread eThread;
	
	// Connection Stuff
	String log_user;
	String user;
	String host;
	int port;
	volatile boolean connected;
	//boolean applet;
	Socket socket;					// Connected to the
	DataInputStream in_stream;		// server by streams
	DataOutputStream out_stream;	// over sockets
	
	// Windows
	static ExternalWindow ecMainWindow;
	InternalWindow mainWindow;
	InternalWindow mapWindow;
	InternalWindow chatWindow;
	InternalWindow dPadWindow;
	InternalWindow charWindow;
	
	// Menus
	Menu mainMenu;
	MenuItem fileMenu;
		MenuItem fileConnectMenu;
		MenuItem fileDisconnectMenu;
		MenuItem fileSaveLogMenu;
		MenuItem fileExitMenu;
	MenuItem editMenu;
		MenuItem editCutMenu;
		MenuItem editCopyMenu;
		MenuItem editPasteMenu;
	MenuItem optionMenu;
		MenuItem optionDirsMenu;
		MenuItem optionServsMenu;
		MenuItem optionCharMenu;
		MenuItem optionLogMenu;
	MenuItem windowMenu;
		MenuItem windowMapMenu;
		MenuItem windowChatMenu;
		MenuItem windowCharMenu;
		MenuItem windowDPadMenu;
	MenuItem helpMenu;
		MenuItem helpIndexMenu;
		MenuItem helpAboutMenu;
		
	// Buttons
	Button leftButton;
	Button topButton;
	Button rightButton;
	Button bottomButton;
	Button shoutButton;
	Button whisperButton;
	Button talkButton;
	Button sendButton;

	// Text Components
	//TextField recievedTextField;
	TextView recievedTextView;
	TextField namesTextField;
	TextField messageTextField;
	
	//Other controls
	Map map;
	CharacterButtons cbuttons;
	// Misc
	static String mapstr = "***@@@@@@@@@@@@@@@@@******* ^^^^^^^\n"+
						   "***@@@@@@@@@@^^^^^^^^^^^*** ^     ^\n"+
						   "***@@^^^^^@@@*^***^***^**** ^     ^\n"+
						   "***^ ^   ^ ***^***^***^**** ^     ^\n"+
						   "***^ ^   ^ ***^***^***^**** ^     ^\n"+
						   "***^ ^   ^ ***^^d^^^d^^**** ^     ^\n"+
						   "***  ^^^^^ **************** ^^^d^^^\n";

	public void init() {
		super.init();
		loadMainInit();
	}

	public static void main(String args[]) {
		EnviraChat app;
		Size size;
		
		app = new EnviraChat();
		ecMainWindow = new ExternalWindow();
		
		app.setMainRootView(ecMainWindow.rootView());
		
		size = ecMainWindow.windowSizeForContentSize(600, 419);
		ecMainWindow.sizeTo(size.width, size.height);
		ecMainWindow.setTitle("EnviraChat v1.0 alpha 1");
		ecMainWindow.center();
		ecMainWindow.show();
		
		app.run();
		System.exit(0);
	}

	void loadMainInit() {
		
		loadInterface();
		
		
		
	}
	
	void loadInterface() {
		int tempY1 = 57;
		int tempY2 = 0;

		if (!isApplet()) {
			tempY1 = 4;
			tempY2 = 53;
		}
	
		loadCharWindow(tempY1, tempY2);
		loadDPadWindow();
		loadChatWindow();
		loadMainWindow();
		loadMapWindow(tempY1, tempY2);
		
		// Show them all
		if(isApplet())
			mainWindow.show();
		charWindow.show();
		dPadWindow.show();
		chatWindow.show();
		mapWindow.show();

	}	
	
	void loadMainWindow() {
		//---------------------------------
		// Set up Main Window
		//---------------------------------
		
		if (isApplet()) {
			mainWindow = new InternalWindow(4,4,593,50);
			mainWindow.setResizable(true);
			mainWindow.setTitle("EnviraChat v1.0 alpha 1");
			mainWindow.setCloseable(true);
		}
		//---Set up Menus---//
		mainMenu = new Menu(true);

		//--File Menu--//
		fileMenu = mainMenu.addItemWithSubmenu("File");
		fileConnectMenu = new MenuItem("Connect","FileConnect",this);
		fileDisconnectMenu = new MenuItem("Disconnect","FileDisconnect",this);
		fileSaveLogMenu = new MenuItem("Save Log","FileSaveLog",this);
		fileExitMenu = new MenuItem("Exit", "FileExit", this);
		
		fileDisconnectMenu.setEnabled(false);
		fileMenu.submenu().addItemAt(fileConnectMenu,0);
		fileMenu.submenu().addItemAt(fileDisconnectMenu,1);
		fileMenu.submenu().addItemAt(new MenuItem("-","",this),2);
		fileMenu.submenu().addItemAt(fileSaveLogMenu,3);
		fileMenu.submenu().addItemAt(new MenuItem("-","",this),4);
		fileMenu.submenu().addItemAt(fileExitMenu,5);
		
		//--Edit Menu--//
		editMenu = mainMenu.addItemWithSubmenu("Edit");
		
		editCutMenu = new MenuItem("Cut", 'X', "EditCut", this);
		editCopyMenu = new MenuItem("Copy", 'C', "EditCopy", this);
		editPasteMenu = new MenuItem("Paste", 'V', "EditPaste", this);
		
		editMenu.submenu().addItemAt(editCutMenu,0);
		editMenu.submenu().addItemAt(editCopyMenu,1);
		editMenu.submenu().addItemAt(editPasteMenu,2);
		
		//--Options Menu--//
		optionMenu = mainMenu.addItemWithSubmenu("Options");
		
		optionDirsMenu = new MenuItem("Directories...", "OptionsDirs", this);
		optionServsMenu = new MenuItem("Servers...", "OptionsServs", this);
		optionCharMenu = new MenuItem("Character...", "OptionsChar", this);
		optionLogMenu = new MenuItem("Log Conversation","OptionsLog",this,true);
		
		optionMenu.submenu().addItemAt(optionDirsMenu,0);
		optionMenu.submenu().addItemAt(optionServsMenu,1);
		optionMenu.submenu().addItemAt(optionCharMenu,2);
		optionMenu.submenu().addItemAt(new MenuItem("-","",this),3);
		optionMenu.submenu().addItemAt(optionLogMenu,4);
		
		//--Window Menu--//
		windowMenu = mainMenu.addItemWithSubmenu("Window");
		
		windowMapMenu = new MenuItem("Map Window", '1', "WindowMap",this);
		windowChatMenu = new MenuItem("Chat Window", '2', "WindowChat",this);
		windowCharMenu = new MenuItem("Character Window", '3', "WindowChar", this);
		windowDPadMenu = new MenuItem("Directional Pad", '4', "WindowDPad", this);
		
		windowMenu.submenu().addItemAt(windowMapMenu,0);
		windowMenu.submenu().addItemAt(windowChatMenu,1);
		windowMenu.submenu().addItemAt(windowCharMenu,2);
		windowMenu.submenu().addItemAt(windowDPadMenu,3);
		
		//--Help Menu--//
		helpMenu = mainMenu.addItemWithSubmenu("Help");
		
		helpIndexMenu = new MenuItem("Index", "HelpIndex", this);
		helpAboutMenu = new MenuItem("About EnviraChat...", "HelpAbout", this);
		
		helpMenu.submenu().addItemAt(helpIndexMenu,0);
		helpMenu.submenu().addItemAt(new MenuItem("-","",this),1);
		helpMenu.submenu().addItemAt(helpAboutMenu,2);

		if (!isApplet())
			ecMainWindow.setMenu(mainMenu);
		else {
			MenuView mainMenuView = new MenuView(mainMenu);
			mainWindow.setMenuView(mainMenuView);
		}
	}

	void loadMapWindow(int Y1, int Y2) {
		//---------------------------------
		// Set up Map Window
		//---------------------------------
		mapWindow = new InternalWindow(4,Y1,403,201+Y2);
		mapWindow.setTitle("Map");
		mapWindow.setResizable(true);
		mapWindow.setCloseable(true);
		mapWindow.setOwner(this);
		
		map = new Map(mapstr, 5, 5, 388, 193);
		map.setBuffered(true);
		mapWindow.addSubview(map);

	}

	void loadCharWindow(int Y1, int Y2) {
		//---------------------------------
		// Set up Character Window
		//---------------------------------
		charWindow = new InternalWindow(411,Y1,186,235+Y2);
		charWindow.setTitle("Character");
		charWindow.setResizable(true);
		charWindow.setCloseable(true);
		
		cbuttons = new CharacterButtons(0,0,184,233+Y2);
		
		cbuttons.addButton(new StoneImage());
		cbuttons.addButton();
		cbuttons.addButton();
		cbuttons.addButton(new StoneImage());
		cbuttons.addButton();
		cbuttons.addButton();
		cbuttons.addButton();
		cbuttons.addButton();
		cbuttons.addButton();

		
		charWindow.addSubview(cbuttons);
	}
	
	void loadDPadWindow() {
		//---------------------------------
		// Set up Directional Pad Window
		//---------------------------------
		dPadWindow = new InternalWindow(InternalWindow.PALETTE_LAYER,411,295,186,102);
		dPadWindow.setTitle("Directional Pad");
		dPadWindow.setResizable(false);
		dPadWindow.setCloseable(true);

		topButton = new Button(70,2,25,25);
		leftButton = new Button(45,27,25,25);
		rightButton = new Button(95,27,25,25);
		bottomButton = new Button(70,52,25,25);

		topButton.setTarget(this);
		leftButton.setTarget(this);
		rightButton.setTarget(this);
		bottomButton.setTarget(this);
		
		topButton.setCommand("TopButton");
		leftButton.setCommand("LeftButton");
		rightButton.setCommand("RightButton");
		bottomButton.setCommand("BottomButton");

		dPadWindow.addSubview(topButton);
		dPadWindow.addSubview(leftButton);
		dPadWindow.addSubview(rightButton);
		dPadWindow.addSubview(bottomButton);
	}
	
	void loadChatWindow() {
		//---------------------------------
		// Set up Chat Window
		//---------------------------------
		chatWindow = new InternalWindow(4,261,403,136);
		chatWindow.setTitle("Chatbox");
		chatWindow.setResizable(true);
		chatWindow.setCloseable(true);
		
		//namesTextField = new TextField(5,5,80,68);
		//recievedTextField = new TextField(85,5,250,68); //85, x, 250, x
		recievedTextView = new TextView(5,5,330,68);
		messageTextField = new TextField(5,79,330,22);
		
		//namesTextField.setEditable(false);
		//namesTextField.setScrollable(true);
		
		//recievedTextField.setWrapsContents(true);
		recievedTextView.setEditable(false);
		//recievedTextField.setScrollable(true);
		
		messageTextField.setEditable(false);
		
		
		talkButton = new Button(342,5,50,18);
		shoutButton = new Button(342,28,50,18);
		whisperButton = new Button(342,51,50,18);
		sendButton = new Button(342,81,50,18);
		
		sendButton.setCommand("SendButton");
		
		talkButton.setTitle("Talk");
		shoutButton.setTitle("SHOUT!");
		whisperButton.setTitle("whisper");
		sendButton.setTitle("Send");
		
		
		chatWindow.addSubview(namesTextField);
		chatWindow.addSubview(recievedTextView);
		chatWindow.addSubview(messageTextField);
		chatWindow.addSubview(talkButton);
		chatWindow.addSubview(shoutButton);
		chatWindow.addSubview(whisperButton);
		chatWindow.addSubview(sendButton);
	}

	
	public void performCommand(String command, Object obj) {
		if (obj instanceof Button)
			handleButtonEvent(command);
		else if (obj instanceof MenuItem)
			handleMenuItemEvent(command);
	}

	
	
	private void handleButtonEvent(String command) {
		if ("LeftButton".equals(command))
			map.scroll(Map.LEFT,1);
			
		else if ("RightButton".equals(command))
			map.scroll(Map.RIGHT,1);
			
		else if ("TopButton".equals(command))
			map.scroll(Map.UP,1);
			
		else if ("BottomButton".equals(command))
			map.scroll(Map.DOWN,1);
		
		else if ("SendButton".equals(command)) {
			sendChatLine(messageTextField.stringValue(),0);
		}
	}
	
	private void handleMenuItemEvent(String command) {
		System.out.println("handleMenuItemEvent");
		
		if ("FileConnect".equals(command)) {
		}
		else if ("FileDisconnect".equals(command)) {
		}
		else if ("FileSaveLog".equals(command))
			;
		else if ("FileExit".equals(command))
			System.exit(0);
		else if ("EditCut".equals(command)) {
			if (messageTextField.hasSelection())
				messageTextField.cut();
			//else if (recievedTextField.hasSelection())
				//recievedTextField.cut();
		}
		else if ("EditCopy".equals(command)) {
			if (messageTextField.hasSelection())
				messageTextField.copy();
			//else if (recievedTextField.hasSelection())
				//recievedTextField.copy();
		}
		else if ("EditPaste".equals(command)) {
			if (messageTextField.hasSelection())
				messageTextField.paste();
			//else if (recievedTextField.hasSelection())
				//recievedTextField.paste();
		}
		else if ("OptionsDirs".equals(command)) {
		}
		else if ("OptionsServs".equals(command)) {
		}
		else if ("OptionsChar".equals(command)) {
		}
		else if ("OptionsLog".equals(command)) {
		}
		else if ("WindowMap".equals(command))
			mapWindow.show();
		else if ("WindowChat".equals(command))
			chatWindow.show();
		else if ("WindowChar".equals(command))
			charWindow.show();
		else if ("WindowDPad".equals(command))
			dPadWindow.show();
		else if ("HelpIndex".equals(command)) {
		}
		else if ("HelpAbout".equals(command)) {
			int alerttemp = Alert.runAlertExternally(new EnviraChatImage(),"EnviraChat","EnviraChat v1.0 alpha 1","Okay",null,null);
		}
	}



	synchronized boolean sendChatLine(String chatmsg, int type) {
		String msg = chatmsg.trim();
		int distance;
		if ((chatmsg.length() <= 0) || (!connected)) return false;
		
		switch(type) {
			case 0:	// Talk
				distance = 4;
				break;
			case 1:	// SHOUT			
				distance = 10;
				break;
			case 2:	// whisper
				distance = 1;
				break;
		}
		try {
			out_stream.writeBytes(msg + '\n');
			out_stream.flush();
		}
		catch(IOException i) {
			int alerttemp = Alert.runAlertInternally(Alert.warningImage(),"EnviraChat","Unable to write to the server!","Okay",null,null);
			//disconnect();
		}
		messageTextField.setStringValue("");
		return true;
	}




	public boolean windowWillShow(Window aWindow) {
		return(true);
	}
	
	public void windowDidShow(Window aWindow) {
	
	}
	
	public boolean windowWillHide(Window aWindow) {
		return(true);
	}
	
	public void windowDidHide(Window aWindow) {
	}
	
	public void windowDidBecomeMain(Window aWindow) {
	}
	
	public void windowDidResignMain(Window aWindow) {
	}
	
	public void windowWillSizeBy(Window aWindow, Size deltaSize) {
		
	}
}