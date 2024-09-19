// EnviraChat.java
/*
import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;
*/
import java.io.*;
import java.net.*;
import java.lang.*;
import chcs.envirachat.ui.*;
import chcs.envirachat.ui.character.objects.*;
import chcs.common.ui.*;
import netscape.application.*;
import netscape.util.*;


public class EnviraChat extends Application implements Runnable {
	Thread eThread;
	
	// Connection Stuff
	String log_user;
	String user;
	String host;
	int port;
	volatile boolean connected;
	boolean applet;
	Socket socket;					// Connected to the
	DataInputStream in_stream;		// server by streams
	DataOutputStream out_stream;	// over sockets
	
	// Misc
	ExternalWindow mainExWindow;
	ExternalWindow mapExWindow;
	ExternalWindow chatExWindow;
	ExternalWindow direcExWindow;
	ExternalWindow btnExWindow;
	InternalWindow mainInWindow;
	InternalWindow mapInWindow;
	InternalWindow chatInWindow;
	InternalWindow direcInWindow;
	InternalWindow btnInWindow;
	
	static Map map;
	static String mapstr = "***  ^^^^^ ***\n"+
						   "***  ^   ^ ***\n"+
						   "***^ ^   ^ ***\n"+
						   "***  ^^^^^ ***\n";

	public void init() {
		super.init();
		if (isApplet())
			loadMainInit(mainInWindow, mapInWindow, chatInWindow, direcInWindow, btnInWindow);
		else
			loadMainInit(mainExWindow, mapExWindow, chatExWindow, direcExWindow, btnExWindow);
	}

	public static void main(String args[]) {
		EnviraChat app;

		app = new EnviraChat();
		//isApplication = true;
		app.run();
		System.exit(0);
	}

	public void loadMainInit(Window mainWindow, Window mapWindow, Window chatWindow, Window direcWindow, Window btnWindow) {
		
		if (isApplet()) {
			mainWindow =(InternalWindow)mainWindow;
			mapWindow = (InternalWindow)mapWindow;
			chatWindow =(InternalWindow)chatWindow;
			direcWindow=(InternalWindow)direcWindow;
			btnWindow = (InternalWindow)btnWindow;
			// Set up Main Window
			mainWindow = new InternalWindow(InternalWindow.PALETTE_LAYER,4,4,592,50);
			mainWindow.setCloseable(true);
						
			// Set up Map Window
			mapWindow = new InternalWindow(4,54,403,203);
			mapWindow.setCloseable(true);
			
			// Set up Button Config Window
			btnWindow = new InternalWindow(411,54,185,238);
			btnWindow.setCloseable(true);
			
			// Set up Directional Pad Window
			direcWindow = new InternalWindow(InternalWindow.PALETTE_LAYER,411,296,185,100);
			direcWindow.setCloseable(true);
			
			// Set up Chat Window
			chatWindow = new InternalWindow(4,261,403,135);
			chatWindow.setCloseable(true);
			
			// Show them all
			mainRootView().addSubview(mainWindow);
			mainRootView().addSubview(mapWindow);
			mainRootView().addSubview(btnWindow);
			mainRootView().addSubview(direcWindow);
			mainRootView().addSubview(chatWindow);

		//ContainerView containerView = new ContainerView(10,10,32,32);
		//TreeImage treeimage = new TreeImage();
		//containerView.setImage(treeimage);
		//mapWindow.addSubview(containerView);
		//map = new Map(mapstr, 10, 10, 256, 128);
		//mapWindow.addSubview(map);

		}
		else {
			ExternalWindow mainWindow =(ExternalWindow)mainWindow;
			ExternalWindow mapWindow = (ExternalWindow)mapWindow;
			ExternalWindow chatWindow =(ExternalWindow)chatWindow;
			ExternalWindow direcWindow=(ExternalWindow)direcWindow;
			ExternalWindow btnWindow = (ExternalWindow)btnWindow;

			mainWindow = new ExternalWindow();
			mainWindow.moveTo(4,4);
			mainWindow.sizeTo(592, 50);
			//mainWindow.setMainRootView(mainWindow.rootView());
		
			// Set up Map Window
			mapWindow = new ExternalWindow();
			mapWindow.moveTo(4, 54);
			mapWindow.sizeTo(403, 203);
			//mapWindow.setResizable(false);
			//mapWindow.setCloseable(true);
		
			// Set up Buttons and Config Window
			btnWindow = new ExternalWindow();
			btnWindow.moveTo(411, 54);
			btnWindow.sizeTo(185, 238);
			//btnWindow.setResizable(false);
			//btnWindow.setCloseable(false);

		
			// Set up Directional Pad Window
			direcWindow = new ExternalWindow();
			direcWindow.moveTo(411, 296);
			direcWindow.sizeTo(185, 100);
			//direcWindow.setCloseable(false);
		
			// Set up Chat Window
			chatWindow = new ExternalWindow();
			chatWindow.moveTo(4, 261);
			chatWindow.sizeTo(403, 135);
			//chatWindow.setCloseable(false);
		}		
		mainWindow.setResizable(true);
		mainWindow.setTitle("EnviraChat v1.0 alpha 1");
		mapWindow.setTitle("Map");
		mapWindow.setResizable(true);
		btnWindow.setTitle("Character");
		btnWindow.setResizable(true);
		direcWindow.setTitle("Directional Pad");
		direcWindow.setResizable(false);
		chatWindow.setTitle("Chatbox");
		chatWindow.setResizable(true);

		// Show them all
		mainWindow.show();
		mapWindow.show();
		btnWindow.show();
		direcWindow.show();
		chatWindow.show();

		map = new Map(mapstr, 10, 10, 256, 128);
		mapWindow.addSubview(map);
	}
}